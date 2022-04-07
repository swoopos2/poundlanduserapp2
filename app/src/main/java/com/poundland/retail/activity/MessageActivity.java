package com.poundland.retail.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.MesageAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityMessageBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.RecyclerSwipeDeleteMessage;
import com.poundland.retail.model.requestModel.NotificationDeleteRequestModel;
import com.poundland.retail.model.responseModel.DeleteMessageResponseModel;
import com.poundland.retail.model.responseModel.GetAllMessageEcomResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.IMAGE_LINK;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;

public class MessageActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner, RecyclerSwipeDeleteMessage.RecyclerItemTouchHelperListener {
    private ActivityMessageBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String userId;
    private MesageAdapter mesageAdapter;
    private List<GetAllMessageEcomResponseModel.MessagesBean.DataBean> messageList;
    private MessageActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_message);

    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        setListeners();
        getAllMessage(false);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerSwipeDeleteMessage(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvMessage);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0, /*ItemTouchHelper.LEFT |*/ ItemTouchHelper.LEFT /*| ItemTouchHelper.UP*/) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition(); //swiped position
                /*  if (direction == ItemTouchHelper.LEFT) { //swipe left
                 yourarraylist.remove(position);
                    youradapter.notifyItemRemoved(position);
               Toast.makeText(getApplicationContext(),"Swipped to left"+position,Toast.LENGTH_SHORT).show();
                }else if(direction == ItemTouchHelper.RIGHT){//swipe right
                  yourarraylist.remove(position);
                    youradapter.notifyItemRemoved(position);
                    Toast.makeText(getApplicationContext(),"Swipped to right"+position,Toast.LENGTH_SHORT).show();   }*/
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        // attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(binding.rvMessage);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof MesageAdapter.ViewResource) {
            final GetAllMessageEcomResponseModel.MessagesBean.DataBean deletedItem = messageList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            DialogUtils.dialogLogOut(instance, getString(R.string.delete_message_note), getString(R.string.delete_message), new OnDialogClickListener() {
                @Override
                public void onPositiveClick() {

                    deleteMessage(viewHolder.getAdapterPosition());
                    //  Snackbar snackbar = Snackbar.make(binding.ivBack, "message has been removed !", Snackbar.LENGTH_SHORT);

                    /*snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            messageAdapter.restoreItem(deletedItem, deletedIndex);
                        }
                    });
                    snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();*/
                }

                @Override
                public void onNegativeClick() {
                    mesageAdapter.refreshItem();
                }
            });


        }
    }

    private void init() {
        instance = this;
        messageList = new ArrayList<>();
        isLoading = true;
        pageNumber = 1;
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getString(R.string.message));
    }

    private void setAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        mesageAdapter = new MesageAdapter(this, messageList, this);
        binding.rvMessage.setLayoutManager(layoutManager);
        binding.rvMessage.setAdapter(mesageAdapter);
        binding.rvMessage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && pageNumber <= totalPage) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isLoading = true;
                            getAllMessage(true);
                        }
                    }
                }
            }
        });

    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRefresh() {
        isLoading = true;
        pageNumber = 1;
        messageList.clear();
        if (mesageAdapter != null)
            mesageAdapter.notifyDataSetChanged();
        getAllMessage(false);
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        Intent intent = new Intent(this, ChatDetailActivity.class);
        intent.putExtra(ORDER_ID, String.valueOf(messageList.get(position).getOrder_id()));
        intent.putExtra(VENUE_ID, messageList.get(position).getVenueid());
        intent.putExtra(CONTACT_NO, messageList.get(position).getPhone_number());
        intent.putExtra(VENUE, messageList.get(position).getVenue_name());
        intent.putExtra(IMAGE_LINK, ApiRequestUrl.BASE_URL_VENUE + messageList.get(position).getVenue_images());
        startActivity(intent);

    }

    private void getAllMessage(final boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<GetAllMessageEcomResponseModel> call = apiInterface.getAllMessageEcom(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), String.valueOf(pageNumber++));
            call.enqueue(new Callback<GetAllMessageEcomResponseModel>() {
                @Override
                public void onResponse(Call<GetAllMessageEcomResponseModel> call, Response<GetAllMessageEcomResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            GetAllMessageEcomResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    isLoading = false;
                                    totalPage = responseModel.getMessages().getTotal();
                                    if (responseModel.getMessages().getData() != null && responseModel.getMessages().getData().size() > 0) {
                                        binding.tvNoDataFound.setVisibility(View.GONE);

                                        messageList.addAll(responseModel.getMessages().getData());
                                        if (isMore) {
                                            if (mesageAdapter != null)
                                                mesageAdapter.notifyDataSetChanged();
                                        } else {
                                            setAdapter();
                                        }
                                    } else {
                                        if (!isMore)
                                            binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
                                }
                            }

                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetAllMessageEcomResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void deleteMessage(int adapterPosition) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            NotificationDeleteRequestModel beanX = new NotificationDeleteRequestModel(messageList.get(adapterPosition).getVenue_id(), messageList.get(adapterPosition).getOrder_id(), messageList.get(adapterPosition).getId());
            Call<DeleteMessageResponseModel> call = apiInterface.deleteMessage(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), beanX);
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<DeleteMessageResponseModel>() {
                @Override
                public void onResponse(Call<DeleteMessageResponseModel> call, Response<DeleteMessageResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            DeleteMessageResponseModel responseModel = response.body();
                            if (responseModel.getStatus()) {
                                mesageAdapter.removeItem(adapterPosition);
                                //showSnackBar(binding.ivBack, "" + responseModel.getMessage());
                            } else {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }

                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<DeleteMessageResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

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

                if (data.equals("DISC_OFFER_NOTIFY")){
                    DialogUtils.offerNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent topStore = new Intent(this, NearByDealsActivity.class);
                        topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                        startActivity(topStore);
                    });
                } else if (!TextUtils.isEmpty(data)){
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
