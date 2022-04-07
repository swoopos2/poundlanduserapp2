package com.poundland.retail.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.ChatDetailAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.requestModel.MessageDetailRequestModel;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityChatDetailBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.SendMessageRequestModel;
import com.poundland.retail.model.responseModel.MessageDetailResponseModel;
import com.poundland.retail.model.responseModel.SendMessageResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.Telephony.BaseMmsColumns.MESSAGE_ID;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_TYPE;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.IMAGE_LINK;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_ID;
import static com.poundland.retail.interfaces.Constants.POST_ID;
import static com.poundland.retail.interfaces.Constants.CUST;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;

public class ChatDetailActivity extends BaseActivity implements View.OnClickListener {
    private ActivityChatDetailBinding binding;
    private PrefManager prefManager;
    private ChatDetailAdapter chatDetailAdapter;
    private String authToken, userId;
    private String postID, otherId, messageId;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private boolean isNoti = false;
    private boolean isCallExecuting = true;
    private static ChatDetailActivity instance = null;
    private boolean isStarMarked = false;
    private String venueId;
    private String venueName;
    private String orderId = "";
    private List<MessageDetailResponseModel.MessagesBean.DataBean> chatList;
    private String imageLinkVenue;
    private String venueContact="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_detail);
        instance = this;
        /*IntentFilter intentFilter = new IntentFilter("GET_CHAT");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);*/
        getDataFromIntent();
        init();
        setOpacity();
        setListeners();
        getMessageList(false, true);
    }

    /*BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           *//* if (!isCallExecuting) {
                isCallExecuting = true;*//*
            //chatList.clear();
            pageNumber = 1;
            // getMessageList(false, false);
            //}
        }
    };*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }


    public static ChatDetailActivity getInstance() {
        return instance;
    }

    private void getDataFromIntent() {
        if (getIntent().getExtras() != null) {
            imageLinkVenue = getIntent().getExtras().getString(IMAGE_LINK);
            orderId = getIntent().getExtras().getString(ORDER_ID);
            venueId = getIntent().getExtras().getString(VENUE_ID);
            venueName = getIntent().getExtras().getString(VENUE);
            venueContact = getIntent().getExtras().getString(CONTACT_NO);
            //isNoti = true;
            if (orderId == null || venueId == null) {
                postID = getIntent().getStringExtra(POST_ID);
                messageId = getIntent().getStringExtra(MESSAGE_ID);
                isNoti = getIntent().getBooleanExtra("IS_FROM_NOTI", false);
            }
        }

    }

    private void init() {
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.tvTitle.setText(venueName);
        Glide.with(this).load(imageLinkVenue).apply(new RequestOptions()
                .placeholder(R.drawable.profile_placeholder))
                .into(binding.ivVenueImageHead);
        // userId = prefManager.getPreference(LOGIN_ID, "");
        chatList = new ArrayList<>();

        isLoading = true;
        pageNumber = 1;
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivCall.setOnClickListener(this);
       /* binding.ivProfile.setOnClickListener(this);
        binding.ivMarkStar.setOnClickListener(this);
        binding.ivDeleteChat.setOnClickListener(this);*/
        binding.ivSendMsg.setOnClickListener(this);
        binding.etChatText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setOpacity();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setOpacity();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setOpacity();
            }
        });
    }

    private void setAdapter() {
        chatDetailAdapter = new ChatDetailAdapter(this, chatList, imageLinkVenue);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(true);    it makes the list top to bottom
        layoutManager.findFirstVisibleItemPosition();

        binding.rvChat.setLayoutManager(layoutManager);
        binding.rvChat.setAdapter(chatDetailAdapter);

       /* binding.rvChat.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            getMessageList(true, false);
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        });
*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (isNoti) {
                    finish();
                   /* Intent intent = new Intent(ChatDetailActivity.this, MainActivity.class);
                    intent.putExtra(IS_CHAT_NOTI, true);
                    intent.putExtra(IS_BLOG, false);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);*/
                } else {

                    finish();
                }
                break;

            case R.id.iv_send_msg:
                binding.ivSendMsg.setEnabled(false);
                binding.ivSendMsg.setAlpha((float) 0.3);
                postChat();
                if (chatDetailAdapter != null)
                    chatDetailAdapter.notifyDataSetChanged();
                binding.etChatText.setText(null);
                setOpacity();
                HelperClass.hideKeyboard(this);
                break;
            case R.id.iv_call:
               //if (chatList.size() > 0){
                    Intent inCall = new Intent(Intent.ACTION_DIAL);
                    inCall.setData(Uri.parse("tel:"+venueContact));
                    startActivity(inCall);
               // }

                break;
        }
    }

    private void setOpacity() {
        if (binding.etChatText.getText().toString().trim().isEmpty()) {
            binding.ivSendMsg.setEnabled(false);
            binding.ivSendMsg.setAlpha((float) 0.3);
        } else {
            binding.ivSendMsg.setEnabled(true);
            binding.ivSendMsg.setAlpha((float) 1.0);
        }
    }


    public void getMessageList(final boolean isMore, final boolean isFirst) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore && isFirst)
                dialog.show();

            MessageDetailRequestModel requestModel = new MessageDetailRequestModel(venueId, orderId);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MessageDetailResponseModel> call = apiInterface.messageDetails(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<MessageDetailResponseModel>() {
                @Override
                public void onResponse(Call<MessageDetailResponseModel> call, Response<MessageDetailResponseModel> response) {
                    try {

                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            MessageDetailResponseModel messageListBean = response.body();

                            if (messageListBean != null) {
                                if (messageListBean.isStatus()) {
                                    // messageId = messageListBean.getMessages().getData().get();
                                    isLoading = false;
                                    totalPage = messageListBean.getMessages().getTotal();
                                    if (messageListBean.getMessages().getData() != null && messageListBean.getMessages().getData().size() > 0) {
                                        chatList.addAll(messageListBean.getMessages().getData());
                                        if (isMore) {
                                            if (chatDetailAdapter != null)
                                                chatDetailAdapter.notifyDataSetChanged();
                                            binding.rvChat.scrollToPosition(0);
                                        } else {
                                            setAdapter();
                                            binding.rvChat.scrollToPosition(0);
                                        }
                                    } else {
                                        if (!isMore) {
                                            setAdapter();
                                            binding.rvChat.scrollToPosition(0);
                                        }
                                    }
                                } else {
                                    showSnackBar(binding.getRoot(), messageListBean.getMessage());
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
                public void onFailure(Call<MessageDetailResponseModel> call, Throwable t) {

                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {

            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void postChat() {
        try {
            if (isInternetOn(this)) {
                SendMessageRequestModel requestModel = new SendMessageRequestModel(CUSTOMER_TYPE, CUST,
                        binding.etChatText.getText().toString().trim(), orderId, VENUE, venueId);

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<SendMessageResponseModel> call = apiInterface.sendMessage(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
                call.enqueue(new Callback<SendMessageResponseModel>() {
                    @Override
                    public void onResponse(Call<SendMessageResponseModel> call, Response<SendMessageResponseModel> response) {
                        try {
                            if (response.isSuccessful()) {
                                binding.ivSendMsg.setEnabled(true);
                                binding.ivSendMsg.setAlpha((float) 1.0);
                                SendMessageResponseModel SendMessageResponseModel = response.body();
                                if (SendMessageResponseModel != null) {
                                    if (SendMessageResponseModel.getStatus()) {
                                        chatList.clear();
                                        pageNumber = 1;
                                        getMessageList(false, false);
                                        binding.etChatText.setText(null);
                                        setOpacity();
                                        // binding.ivSendMsg.setEnabled(true);
                                    } else {
                                        showSnackBar(binding.getRoot(), SendMessageResponseModel.getSuccess());
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
                    public void onFailure(Call<SendMessageResponseModel> call, Throwable t) {
                        showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                    }
                });
            } else {
                binding.ivSendMsg.setEnabled(true);
                binding.ivSendMsg.setAlpha((float) 1.0);
                showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*private void deleteOrMarkChat(final boolean isDElete) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            Call<SendMessageResponseModel> call;
            JsonObject jsonObject = createStarMarkRequest();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            String userCredentials = "steerzy_app_admin:1234";
            String basicAuth = "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.NO_WRAP));
            if (isDElete) {
                call = apiInterface.deleteMessage(basicAuth, authToken, jsonObject);
            } else {
                call = apiInterface.starMarkChat(basicAuth, authToken, jsonObject);
            }

            Log.e("Request", "" + jsonObject);
            call.enqueue(new Callback<SendMessageResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<SendMessageResponseModel> call, @NonNull Response<SendMessageResponseModel> response) {
                    try {

                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SendMessageResponseModel SendMessageResponseModel = response.body();
                            if (SendMessageResponseModel.getResponseCode() == 200) {

                                if (isDElete) {

                                    DialogUtils.showAlertDialogWithSingleButton(ChatDetailActivity.this, SendMessageResponseModel.getResponseMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            ChatFragment.getInstance().callApi();
                                            finish();
                                        }
                                    });


                                } else {


                                    DialogUtils.showAlertDialogWithSingleButton(ChatDetailActivity.this, SendMessageResponseModel.getResponseMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            isStarMarked = true;
                                            ChatFragment.getInstance().callApi();
                                            DialogUtils.dismissAll();

                                        }
                                    });
                                }

                            } else if (SendMessageResponseModel.getResponseCode() == 402) {
                                DialogUtils.showAlertDialogWithSingleButton(ChatDetailActivity.this, SendMessageResponseModel.getResponseMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {
                                        Helper.logoutUser(ChatDetailActivity.this);
                                    }
                                });

                            } else {
                                showSnackBar(binding.getRoot(), SendMessageResponseModel.getResponseMessage());
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SendMessageResponseModel> call, @NonNull Throwable throwable) {
                    Log.e("Exception", "" + throwable);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }*/


    @Override
    public void onBackPressed() {
        if (isNoti) {
            finish();
            /*Intent intent = new Intent(ChatDetailActivity.this, MainActivity.class);
            intent.putExtra(IS_CHAT_NOTI, true);
            intent.putExtra(IS_BLOG, false);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);*/
        } else {

            finish();
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
