package com.poundland.retail.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.MyCartActivity;
import com.poundland.retail.activity.MyOrderActivity;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.LayoutCellMyOrderCompletedBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.MyOrderListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AddToCartComboRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;

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
import static com.poundland.retail.interfaces.Constants.CART_RETAIL;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.REVIEW_TO_OVERALL;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class MyOrderCompletedAdapter extends RecyclerView.Adapter<MyOrderCompletedAdapter.ViewResource> {

    private Context context;
    private MyOrderListner myOrderListner;
    private List<MyOrderResponseModel.OrdersBean.DataBean> list;
    private List<MyOrderResponseModel.ReturnReasonsBean> returnReasonsList;
    private PrefManager prefManager;
    private SuccessActionListner onSuccessActionListner;


    public MyOrderCompletedAdapter(Context context, List<MyOrderResponseModel.OrdersBean.DataBean> list,
                                   List<MyOrderResponseModel.ReturnReasonsBean> returnReasonsList, MyOrderListner myOrderListner) {
        this.context = context;
        this.list = list;
        this.returnReasonsList = returnReasonsList;
        this.myOrderListner = myOrderListner;
        prefManager = PrefManager.getInstance(context);
        this.onSuccessActionListner = (MyOrderActivity) context;
    }

    @NonNull
    @Override
    public MyOrderCompletedAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_completed, parent, false);

        return new MyOrderCompletedAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderCompletedAdapter.ViewResource holder, int position) {

        if (list.get(position).getStatus() == 0 || list.get(position).getStatus() == 8) {

            if (list.get(position).getDelivery_type().equalsIgnoreCase(context.getString(R.string.home_delivery))) {
                setColor(holder, R.color.app_header_color, context.getString(R.string.waiting_for_ack));
            } else {
                setColor(holder, R.color.app_header_color, context.getString(R.string.waiting_for_dispatch));
            }
        } else if (list.get(position).getStatus() == 1 || list.get(position).getStatus() == 5) {
            setColor(holder, R.color.color_pink, context.getString(R.string.refunded));
        } else if (list.get(position).getStatus() == 2) {
            setColor(holder, R.color.color_saffron_lite, context.getString(R.string.being_prepared));
        } else if (list.get(position).getStatus() == 3) {
            if (list.get(position).getDelivery_type().equalsIgnoreCase(context.getString(R.string.home_delivery))) {
                setColor(holder, R.color.color_saffron_lite, context.getString(R.string.dispatch));
            } else {
                setColor(holder, R.color.color_saffron_lite, context.getString(R.string.ready));
            }
        } else if (list.get(position).getStatus() == 4) {
            setColor(holder, R.color.light_green, context.getString(R.string.completed));
        } else if (list.get(position).getStatus() == 9) {
            setColor(holder, R.color.color_pink, context.getString(R.string.return_pending));
        }

        if (list.get(position).getIs_rated() == 1) {
             holder.binding.tvReview.setText(context.getResources().getString(R.string.rated));
            holder.binding.tvReview.setAlpha(.5f);
        } else {
            //  holder.binding.tvReview.setVisibility(View.VISIBLE);
            holder.binding.tvReview.setAlpha(1f);
        }

/*blue color for waiting. orange being prepared and despatch.
         Then green when completed or delivered or ready for collection*/

        if (!TextUtils.isEmpty(HelperClass.formatddMMMMyyyy(list.get(position).getDelivery_date()))) {
            holder.binding.tvDeliveryDate.setText(context.getString(R.string.delivered) + " " + HelperClass.formatddMMMMyyyy(list.get(position).getDelivery_date()) + " At " + HelperClass.formatDateTimeTO_Time(list.get(position).getDelivery_date()));
        } else {
            holder.binding.tvDeliveryDate.setText(context.getString(R.string.delivered));
        }

        holder.binding.tvOrderNo.setText(context.getString(R.string.order_no) + " " + list.get(position).getUnique_code());
        MyOrderCompletedInnerAdapter myOrderNewAdapter = new MyOrderCompletedInnerAdapter(context, list.get(position).getOrder_details(), returnReasonsList, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        }, list.get(position).getVenue().getVenue_name(), list.get(position).getVenue().getAddress_1(),
                list.get(position).getOrder_date(), list.get(position).getStatus(),
                list.get(position).getCustomer_address(), list.get(position).getVenue().getVenue_id(),
                list.get(position).getVenue().getVenue_images(), list.get(position).getVenue().getPhone_number(),
                HelperClass.formatddMMMMyyyy(list.get(position).getDelivery_date()),
                list.get(position).getUnique_code(), list.get(position).getDelivery_type() == null ? "" : list.get(position).getDelivery_type(),
                "(" + list.get(position).getVenue().getCollection_time() + " Mins)", list.get(position).getTrackingData(),
                list.get(position).getUnique_code(), list.get(position).getIs_hospitality(), list.get(position).getExpected_shipping_date());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        // holder.binding.rvRecycle.setLayoutManager(layoutManager);
        holder.binding.rvRecycle.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        holder.binding.rvRecycle.setAdapter(myOrderNewAdapter);

        holder.binding.rlArriving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.binding.rvRecycle.getVisibility() == View.GONE) {
                    holder.binding.rvRecycle.setVisibility(View.VISIBLE);
                } else {
                    holder.binding.rvRecycle.setVisibility(View.GONE);
                }
            }
        });

        holder.binding.tvReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).getIs_rated() == 1)
                    return;


                myOrderListner.OnSelectOption(REVIEW_TO_OVERALL, String.valueOf(holder.getAdapterPosition()), "", "", null, ""
                        , "", "", String.valueOf(list.get(position).getId()), "", "", "",
                        "", 0f, "", "", "", list.get(position).getVenue().getVenue_name(),
                        list.get(position).getVenue().getPhone_number(), "", "",
                        "(" + list.get(position).getVenue().getCollection_time() + " Mins)", null, 0, "", "");
            }
        });


        holder.binding.tvReorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Integer> addon_idsList = new ArrayList<>();  /// To get addon_ids
                List<Integer> addon_qty_json = new ArrayList<>(); /// To get addon_qty_json
                String addon_ids = android.text.TextUtils.join(",", addon_idsList);
                List<AddToCartComboRequestModel.CartsBean.AddOnsBean> listAdonReq = new ArrayList<>(); /// To get adOns list
                List<AddToCartComboRequestModel.CartsBean> reorderCartReq = new ArrayList<>();

                for (int i = 0; i < list.get(holder.getAdapterPosition()).getOrder_details().size(); i++) {
                    AddToCartComboRequestModel.CartsBean requestModel = new AddToCartComboRequestModel.CartsBean
                            (list.get(holder.getAdapterPosition()).getVenue().getVenue_id(), String.valueOf(list.get(holder.getAdapterPosition()).getMerchant_id()), String.valueOf(prefManager.getPreference(LOGIN_ID, 0)),
                                    list.get(holder.getAdapterPosition()).getOrder_details().get(i).getProduct_id(),
                                    String.valueOf(list.get(holder.getAdapterPosition()).getOrder_details().get(i).getModifier_id()), 1,
                                    String.valueOf(list.get(holder.getAdapterPosition()).getOrder_details().get(i).getOffer_id()), 0,
                                    list.get(holder.getAdapterPosition()).getOrder_details().get(i).getSelling_price(),
                                    list.get(holder.getAdapterPosition()).getOrder_details().get(i).getSelling_price(),
                                    addon_ids, listAdonReq, addon_qty_json, 1, "", " ");
                    reorderCartReq.add(requestModel);
                }


                AddToCartComboRequestModel reorderReq = new AddToCartComboRequestModel(reorderCartReq);


                if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(list.get(holder.getAdapterPosition()).getVenue().getVenue_id()) ||
                        prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                    prefManager.savePreference(VENUE_ID_IN_CART, list.get(holder.getAdapterPosition()).getVenue().getVenue_id());

                    addToCartCombo(reorderReq, holder, list.get(holder.getAdapterPosition()).getVenue().getVenue_type());


                } else {
                    DialogUtils.showAlertDialog(context, context.getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                        @Override
                        public void onPositiveClick() {
                            prefManager.savePreference(VENUE_ID_IN_CART, list.get(holder.getAdapterPosition()).getVenue().getVenue_id());
                            addToCartCombo(reorderReq, holder, list.get(holder.getAdapterPosition()).getVenue().getVenue_type());
                        }

                        @Override
                        public void onNegativeClick() {
                        }
                    });
                }
            }
        });
    }

    private void addToCartCombo(AddToCartComboRequestModel comboRequestModel, ViewResource holder, int venueType) {
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
                            if (venueType == 1) {
                                prefManager.savePreference(CART_ENTRY_TYPE, CART_RETAIL);
                            } else prefManager.savePreference(CART_ENTRY_TYPE, CART_HOSPITALITY);
                            AddToCartResponseModel cartResponseModel = response.body();
                            onSuccessActionListner.onSuccessActionListner();
                            DialogUtils.showAlertDialogWithSingleButton(context, cartResponseModel.getMessage(), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                                        Intent intentH = new Intent(context, MyCartHospitalityActivity.class);
                                        intentH.putExtra(FROM_WHERE, context.getResources().getString(R.string.reorder));
                                        context.startActivity(intentH);
                                    } else {
                                        Intent intentR = new Intent(context, MyCartActivity.class);
                                        intentR.putExtra(FROM_WHERE, context.getResources().getString(R.string.reorder));
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


    private void setColor(MyOrderCompletedAdapter.ViewResource holder, int color, String status) {

        holder.binding.rlArriving.setBackgroundResource(color);
        holder.binding.llOrderId.setBackgroundResource(color);

        holder.binding.tvOrderStatus.setText(status);

        holder.binding.tvOrderStatus.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvDeliveryDate.setTextColor(ContextCompat.getColor(context, R.color.color_white));

        holder.binding.tvOrderNo.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvHandedTo.setTextColor(ContextCompat.getColor(context, R.color.color_white));

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellMyOrderCompletedBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
