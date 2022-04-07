package com.poundland.retail.activityHospitality.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.MyCartActivity;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.activityHospitality.MyOrderReservationActivity;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellMyOrderReservInnerItemBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.dialog.DialogWriteReviews;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AddToCartComboRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.MyOrderReservationResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class MyOrderReservInnerItemAdapter extends RecyclerView.Adapter<MyOrderReservInnerItemAdapter.ViewResource> {

    private Context context;
    private SuccessActionListner onSuccessActionListner;
    private List<MyOrderReservationResponseModel.ReservationsBean.DataBean.OrdersMastersBean.OrderDetailsBean> orders_masters;
    private String unique_code;
    private PrefManager prefManager;
    private int merchantId;
    private String venueId;

    public MyOrderReservInnerItemAdapter(Context context,
                                         List<MyOrderReservationResponseModel.ReservationsBean.DataBean.OrdersMastersBean.OrderDetailsBean> orders_masters, String unique_code,
                                         int merchantId, String venueId) {
        this.context = context;
        prefManager = PrefManager.getInstance(context);
        this.orders_masters = orders_masters;
        this.unique_code = unique_code;
        this.merchantId = merchantId;
        this.venueId = venueId;
        this.onSuccessActionListner = (MyOrderReservationActivity) context;
    }

    @NonNull
    @Override
    public MyOrderReservInnerItemAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_reserv_inner_item, parent, false);

        return new MyOrderReservInnerItemAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderReservInnerItemAdapter.ViewResource holder, int position) {

        if (orders_masters.size() > 0 && position == orders_masters.size() - 1)
            holder.binding.divider.setVisibility(View.GONE);

        holder.binding.tvSrNo.setText("S.No. " + (position + 1));

        Glide.with(context).load(TextUtils.isEmpty(orders_masters.get(position).getModifier_image()) ? ApiRequestUrl.BASE_URL + orders_masters.get(position).getProduct_details().getProduct_image() : ApiRequestUrl.BASE_URL + orders_masters.get(position).getModifier_image()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImage);

        holder.binding.tvProductName.setText(" : " + orders_masters.get(position).getProduct_details().getProduct_name() + " (" + orders_masters.get(position).getAttributes() + ")");
        holder.binding.tvAmount.setText(" : " + orders_masters.get(position).getNet_amount());
        holder.binding.tvQuantity.setText(" : " + orders_masters.get(position).getProduct_qty());

        if (orders_masters.get(position).getStatus_name().equalsIgnoreCase(context.getResources().getString(R.string.in_progress))) {
            // change message in progress to Accepted your order. It will be served soon.
            holder.binding.divPlaced.setVisibility(View.VISIBLE);
            holder.binding.tvProgress.setVisibility(View.VISIBLE);
            holder.binding.tvProgress.setChecked(true);
            holder.binding.tvProgress.setTextColor(context.getResources().getColor(R.color.app_header_color));
            holder.binding.tvOrderStatusMsg.setText(R.string.order_accepted_served_soon);
        } else if (orders_masters.get(position).getStatus_name().equalsIgnoreCase(context.getResources().getString(R.string.delivered))) {
            holder.binding.divPlaced.setVisibility(View.VISIBLE);
            holder.binding.tvProgress.setVisibility(View.VISIBLE);
            holder.binding.tvProgress.setChecked(true);
            holder.binding.tvProgress.setTextColor(context.getResources().getColor(R.color.app_header_color));
            holder.binding.divDispatch.setVisibility(View.VISIBLE);
            holder.binding.tvServed.setVisibility(View.VISIBLE);
            holder.binding.tvServed.setChecked(true);
            holder.binding.tvServed.setTextColor(context.getResources().getColor(R.color.app_header_color));
            holder.binding.llOrderStatus.setVisibility(View.VISIBLE);
            holder.binding.tvOrderStatusMsg.setText(R.string.order_served);

        } else if (orders_masters.get(position).getStatus_name().equalsIgnoreCase(context.getResources().getString(R.string.rejected))) {
            holder.binding.divPlaced.setVisibility(View.VISIBLE);
            holder.binding.tvRejected.setVisibility(View.VISIBLE);
            holder.binding.tvRejected.setChecked(true);
            holder.binding.tvRejected.setTextColor(context.getResources().getColor(R.color.color_pink));
            holder.binding.tvOrderStatusMsg.setText(R.string.order_rejected);
        }

        holder.binding.tvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DialogWriteReviews(context, String.valueOf(orders_masters.get(position).getOrder_id()),
                        String.valueOf(orders_masters.get(position).getOrder_id()),
                        0f, "",
                        orders_masters.get(position).getProduct_details().getProduct_name() + " (" + orders_masters.get(position).getAttributes() + ")",
                        TextUtils.isEmpty(orders_masters.get(position).getModifier_image()) ? ApiRequestUrl.BASE_URL + orders_masters.get(position).getProduct_details().getProduct_image() : ApiRequestUrl.BASE_URL + orders_masters.get(position).getModifier_image(),
                        true, String.valueOf(orders_masters.get(position).getProduct_details().getId()), orders_masters.get(position).getId()).show();
            }
        });

        holder.binding.tvReorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Integer> addon_idsList = new ArrayList<>();  /// To get addon_ids
                List<Integer> addon_qty_json = new ArrayList<>(); /// To get addon_qty_json
                List<AddToCartComboRequestModel.CartsBean.AddOnsBean> listAdonReq = new ArrayList<>(); /// To get adOns list
                String addon_ids = android.text.TextUtils.join(",", addon_idsList);

                List<AddToCartComboRequestModel.CartsBean> reorderCartReq = new ArrayList<>();

                AddToCartComboRequestModel.CartsBean requestModel = new AddToCartComboRequestModel.CartsBean
                        (venueId, String.valueOf(merchantId), String.valueOf(prefManager.getPreference(LOGIN_ID, 0)),
                                orders_masters.get(position).getProduct_id(),
                                String.valueOf(orders_masters.get(position).getModifier_id()), 1,
                                String.valueOf(orders_masters.get(position).getOffer_id()), 0,
                                orders_masters.get(position).getSelling_price(), orders_masters.get(position).getSelling_price(),
                                addon_ids, listAdonReq, addon_qty_json, 1, "", " ");
                reorderCartReq.add(requestModel);
                AddToCartComboRequestModel reorderReq = new AddToCartComboRequestModel(reorderCartReq);

                if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(venueId) ||
                        prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                    prefManager.savePreference(VENUE_ID_IN_CART, venueId);

                    addToCartCombo(reorderReq, holder);

                } else {
                    DialogUtils.showAlertDialog(context, context.getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                        @Override
                        public void onPositiveClick() {
                            prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                            addToCartCombo(reorderReq, holder);
                        }

                        @Override
                        public void onNegativeClick() {
                        }
                    });
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return orders_masters == null ? 0 : orders_masters.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellMyOrderReservInnerItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    private void addToCartCombo(AddToCartComboRequestModel comboRequestModel, MyOrderReservInnerItemAdapter.ViewResource holder) {
        if (isInternetOn(context)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(context);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addMultipleCarts(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), comboRequestModel);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            prefManager.savePreference(CART_ENTRY_TYPE, CART_HOSPITALITY);
                            AddToCartResponseModel cartResponseModel = response.body();
                            onSuccessActionListner.onSuccessActionListner();
                            DialogUtils.showAlertDialogWithSingleButton(context, cartResponseModel.getMessage(), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                                        Intent intentH = new Intent(context, MyCartHospitalityActivity.class);
                                        intentH.putExtra(FROM_WHERE,context.getResources().getString(R.string.reorder));
                                        context.startActivity(intentH);
                                    } else {
                                        Intent intentR = new Intent(context, MyCartActivity.class);
                                        intentR.putExtra(FROM_WHERE,context.getResources().getString(R.string.reorder));
                                        context.startActivity(intentR);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });

                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(context, httpCode == 401 ? context.getString(R.string.season_expired) : context.getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut((Activity) context);
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
                public void onFailure(Call<AddToCartResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(holder.binding.getRoot(), context.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(holder.binding.getRoot(), context.getString(R.string.no_internet_available_msg));

        }
    }

}
