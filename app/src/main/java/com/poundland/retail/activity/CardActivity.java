package com.poundland.retail.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.PaymentDetailsListAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.model.requestModel.StripeAddCardRequestModel;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityCardBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.RecyclerItemSwipeHelper;
import com.poundland.retail.model.requestModel.DeleteSavedCardRequestModel;
import com.poundland.retail.model.responseModel.GetStripeCardDBModel;
import com.poundland.retail.model.responseModel.SaveUserCardDetailsResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CARD_BRAND_NAME;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER_TO_SHOW;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_PAYMENT_DETAIL_ADD_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_VERIFY_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PAYMENT_DETAIL_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.POSITION;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.UNIVERSAL_CODE;

public class CardActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,
        DrawerListner, RecyclerItemSwipeHelper.RecyclerItemTouchHelperListener {
    private ActivityCardBinding binding;
    private PrefManager prefManager;
    private String paymentIntentClientSecret;
   // private List<StripeGetCardDetailsResponseModel.DataBean> cardList;
    private List<GetStripeCardDBModel.CardsBean> cardListsDb;
    private CardActivity instance = null;
    private PaymentDetailsListAdapter paymentDetailsListAdapter;
   // private StripeCardListAdapter stripeCardListAdapter;
    private String fromWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card);

        if (ApiRequestUrl.BASE_URL.equals("https://swoopelocaltesting.com/admin/public/")) {
            paymentIntentClientSecret = getString(R.string.paymentIntentClientSecret_testing);
            Log.e("STRIPE TEST", "" + paymentIntentClientSecret);
        } else {
            paymentIntentClientSecret = getString(R.string.paymentIntentClientSecret);
            Log.e("STRIPE TEST", "" + paymentIntentClientSecret);
        }


        init();
        setListeners();
        getCard();
        getStripeCard();

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemSwipeHelper(0, ItemTouchHelper.LEFT,
                this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvPaymentDetailsList);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0, /*ItemTouchHelper.LEFT |*/ ItemTouchHelper.LEFT /*| ItemTouchHelper.UP*/) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition(); //swiped position
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        // attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(binding.rvPaymentDetailsList);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof PaymentDetailsListAdapter.ViewResource) {
            //  final StripeGetCardDetailsResponseModel.DataBean deletedItem = cardLists.get(viewHolder.getAdapterPosition());
            final GetStripeCardDBModel.CardsBean deletedItem = cardListsDb.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            DialogUtils.showAlertDialog(instance, getString(R.string.delete_card), new OnDialogClickListener() {
                @Override
                public void onPositiveClick() {
                    deleteCard(viewHolder.getAdapterPosition());
                }

                @Override
                public void onNegativeClick() {
                    paymentDetailsListAdapter.refreshItem();
                }
            });


        }
    }

    private void init() {
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        instance = this;
        cardListsDb = new ArrayList<>();
     //   cardList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        binding.title.setText(getString(R.string.payment_details));
        binding.tvResetPin.setVisibility(View.VISIBLE);
    }

    private void setAdapter() {
        paymentDetailsListAdapter = new PaymentDetailsListAdapter(this, cardListsDb, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvPaymentDetailsList.setLayoutManager(layoutManager);
        binding.rvPaymentDetailsList.setAdapter(paymentDetailsListAdapter);
    }



    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.fabAddPayment.setOnClickListener(this);
        binding.ivAddAnother.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.tvResetPin.setOnClickListener(this);
//        binding.ivNotification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.fab_add_payment:

                Intent in = new Intent(instance, CardAddActivity.class);
                startActivityForResult(in, OPEN_PAYMENT_DETAIL_ADD_ACTIVITY);
                break;
            case R.id.iv_add_another:

                Intent inn = new Intent(instance, CardAddActivity.class);
                startActivityForResult(inn, OPEN_PAYMENT_DETAIL_ADD_ACTIVITY);
                break;

            case R.id.iv_cart:
                if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                    Intent intentH = new Intent(this, MyCartHospitalityActivity.class);
                    startActivity(intentH);
                } else {
                    Intent intentR = new Intent(this, MyCartActivity.class);
                    startActivity(intentR);
                }
                break;
            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);

                break;
            case R.id.tv_reset_pin:
                Intent resetPin = new Intent(this, VerifyPinActivity.class);
                resetPin.putExtra(FROM_WHERE, getString(R.string.card));
                resetPin.putExtra(POSITION, -1);
                startActivity(resetPin);

                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getCard() {  /// From saved card in STRIPE
      /*  if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            String stripe = prefManager.getPreference(STRIPE_CUST_ID, "");
            ApiInterface apiInterface = ApiClientForStripe.getClientForStripe().create(ApiInterface.class);
            Call<StripeGetCardDetailsResponseModel> call = apiInterface.getStripeCard("Bearer " + paymentIntentClientSecret, stripe);

            call.enqueue(new Callback<StripeGetCardDetailsResponseModel>() {
                @Override
                public void onResponse(Call<StripeGetCardDetailsResponseModel> call, Response<StripeGetCardDetailsResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            StripeGetCardDetailsResponseModel cardResponseModel = response.body();

                            if (cardResponseModel.getData() != null && cardResponseModel.getData().size() > 0) {
                                binding.tvNoDataFound.setVisibility(View.GONE);
                             //   cardList.clear();
                               // cardList.addAll(cardResponseModel.getData());
                                setAdapter();
                            } else {
                                binding.tvNoDataFound.setVisibility(View.GONE);
                             //   cardList.clear();
                                setAdapter();

                                Intent in = new Intent(instance, CardAddActivity.class);
                                startActivityForResult(in, OPEN_PAYMENT_DETAIL_ADD_ACTIVITY);
                            }
                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(instance, jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(instance, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<StripeGetCardDetailsResponseModel> call, Throwable t) {
//                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
//            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }*/
    }

    private void getStripeCard() {  /// From saved card in DB
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            StripeAddCardRequestModel requestModel = new StripeAddCardRequestModel("stripe");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<GetStripeCardDBModel> call = apiInterface.getStripeCardSwoope(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""),requestModel);
            call.enqueue(new Callback<GetStripeCardDBModel>() {
                @Override
                public void onResponse(Call<GetStripeCardDBModel> call, Response<GetStripeCardDBModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            GetStripeCardDBModel responseModel = response.body();

                            if (responseModel.getCards() != null && responseModel.getCards().size() > 0) {
                                binding.tvNoDataFound.setVisibility(View.GONE);
                                cardListsDb.clear();
                                cardListsDb.addAll(responseModel.getCards());
                                setAdapter();
                            } else {
                                binding.tvNoDataFound.setVisibility(View.GONE);
                                cardListsDb.clear();
                                setAdapter();

                                Intent in = new Intent(instance, CardAddActivity.class);
                                startActivityForResult(in, OPEN_PAYMENT_DETAIL_ADD_ACTIVITY);
                            }

                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(instance, jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(instance, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<GetStripeCardDBModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }


    @Override
    public void onRefresh() {
       /* notificationBean.clear();
        pageNumber = 1;
        getNotificationList(false);*/
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        if (clickType == UNIVERSAL_CODE) {
            if (fromWhere != null && fromWhere.equals(getString(R.string.cart))) {
               /* DialogUtils.dialogLogOut(instance, getString(R.string.use_card_message), getString(R.string.card), new OnDialogClickListener() {
                    @Override
                    public void onPositiveClick() {*/
                        Intent stamp = new Intent(instance, VerifyPinActivity.class);
                        stamp.putExtra(FROM_WHERE, getString(R.string.payment_details));
                        stamp.putExtra(POSITION, position);
                        startActivityForResult(stamp, OPEN_PIN_VERIFY_ACTIVITY);
                    /*}

                    @Override
                    public void onNegativeClick() {
                    }
                });*/
            }
        } else deleteCard(position);
    }

    private void deleteCard(int position) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            DeleteSavedCardRequestModel requestModel = new DeleteSavedCardRequestModel(String.valueOf(cardListsDb.get(position).getStripe_card_id()));
            Call<SaveUserCardDetailsResponseModel> call = apiInterface.deleteUserCardDetails(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<SaveUserCardDetailsResponseModel>() {
                @Override
                public void onResponse(Call<SaveUserCardDetailsResponseModel> call, Response<SaveUserCardDetailsResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SaveUserCardDetailsResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {


                                    DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            paymentDetailsListAdapter.removeCard(position);
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });

                                } else {
                                    DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                }

                            }
                        } else {
                            showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later) + response.code());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveUserCardDetailsResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getTotalCartCount();
    }

    private void getTotalCartCount() {
        if (isInternetOn(this)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetTotalCartResponseModel> call = apiInterface.getTotalCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""));
            call.enqueue(new Callback<GetTotalCartResponseModel>() {
                @Override
                public void onResponse(Call<GetTotalCartResponseModel> call, Response<GetTotalCartResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            GetTotalCartResponseModel totalCartResponseModel = response.body();
                            if (totalCartResponseModel.isStatus()) {
                                if (totalCartResponseModel.getTotal_carts() == 0)
                                    binding.tvCartCount.setVisibility(View.INVISIBLE);
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                            } else {
                                showSnackBar(binding.getRoot(), totalCartResponseModel.getMessage());
                            }
                        } else {
                            //  showSnackBar(binding.getRoot(), getString(R.string.total_cart_item_not_found));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetTotalCartResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_PIN_VERIFY_ACTIVITY && resultCode == RESULT_OK && data != null) {
            int pos = data.getIntExtra(POSITION, -1);
            Intent intent = new Intent();
            intent.putExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK, String.valueOf(cardListsDb.get(pos).getStripe_card_id()));

            ////////////////////////// FOR SWIPE TO BUY ONLY ////////////////////////////////////////////////
            intent.putExtra(CARD_NUMBER_TO_SHOW, "************" + cardListsDb.get(pos).getCard_number());
            intent.putExtra(CARD_BRAND_NAME, cardListsDb.get(pos).getCard_type());
            ////////////////////////////////////////////////////////////////////////////////////////

            setResult(RESULT_OK, intent);
            finish();


        } else if (requestCode == OPEN_PAYMENT_DETAIL_ADD_ACTIVITY && resultCode == RESULT_OK && data != null) {
            getCard();
            getStripeCard();
        } else if (requestCode == OPEN_PAYMENT_DETAIL_ADD_ACTIVITY && resultCode == RESULT_CANCELED) {

        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_my_cart;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String timestamp = extras.getString("timestamp");
                String title = extras.getString("title");
                String message = extras.getString("message");
                String image = extras.getString("image");
                String data = extras.getString("data");

                if (data.equals("DISC_OFFER_NOTIFY")) {
                    DialogUtils.offerNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent topStore = new Intent(this, NearByDealsActivity.class);
                        topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                        startActivity(topStore);
                    });
                } else if (!TextUtils.isEmpty(data)) {
                    NotificationModel model = new Gson().fromJson(data, NotificationModel.class);
                    DialogUtils.offerNotificationDialog(this, title, message, image, new Gson().fromJson(data, NotificationModel.class), (position, clickId) -> {
                        Intent topProduct = new Intent(this, SpecialOfferDetailsActivity.class);
                        topProduct.putExtra(PRODUCT_ID, "");
                        topProduct.putExtra(OFFER_ID, "");
                        topProduct.putExtra(SPECIAL_OFFER_ID, String.valueOf(model.getSpecial_offer_id()));
                        startActivity(topProduct);
                    });
                } else {
                    DialogUtils.simpleNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent myOrder = new Intent(this, MyOrderActivity.class);
                        startActivity(myOrder);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
