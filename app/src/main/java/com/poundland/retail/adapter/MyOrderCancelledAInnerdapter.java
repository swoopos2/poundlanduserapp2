package com.poundland.retail.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.activity.MyOrderActivity;
import com.poundland.retail.activity.ProductDetailActivity;
import com.poundland.retail.activity.VenueDetailActivity;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.databinding.LayoutCellMyOrderCancelledInnerCopyBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.MyOrderListner;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.BARCODE_ID;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.MESSAGE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TRACK_PACKAGE;

public class MyOrderCancelledAInnerdapter extends RecyclerView.Adapter<MyOrderCancelledAInnerdapter.ViewResource> {

    private Context context;
    private MyOrderListner myOrderListner;
    private List<MyOrderResponseModel.OrdersBean.DataBean.OrderDetailsBean> orderCancelledList;
    private String venue_name;
    private String venue_images;
    private String address_1;
    private String order_date;
    private String cancel_date;
    private String venue_id;
    private String venue_contact;
    private String collectTime;
    private String deliveryType;
    private String unique_code;
    private String expected_shipping_date;
    private int status;
    private int is_hospitality;
    private List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData;

    private MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address;

    public MyOrderCancelledAInnerdapter(Context context, List<MyOrderResponseModel.OrdersBean.DataBean.OrderDetailsBean> orderCancelledList,
                                        DrawerListner drawerListner, String venue_name, String address_1, String order_date, String cancel_date, int status,
                                        MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address,
                                        String venue_id, String venue_images, String venue_contact, String deliveryType, String collectTime,
                                        List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData,
                                        String unique_code, int is_hospitality, String expected_shipping_date) {
        this.context = context;
        this.orderCancelledList = orderCancelledList;
        this.trackingData = trackingData;
        this.venue_name = venue_name;
        this.venue_contact = venue_contact;
        this.venue_images = venue_images;
        this.venue_id = venue_id;
        this.address_1 = address_1;
        this.order_date = order_date;
        this.cancel_date = cancel_date;
        this.collectTime = collectTime;
        this.status = status;
        this.is_hospitality = is_hospitality;
        this.unique_code = unique_code;
        this.expected_shipping_date = expected_shipping_date;
        this.deliveryType = deliveryType;
        this.customer_address = customer_address;
        this.myOrderListner = (MyOrderActivity) context;
    }

    @NonNull
    @Override
    public MyOrderCancelledAInnerdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_cancelled_inner_copy, parent, false);

        return new MyOrderCancelledAInnerdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderCancelledAInnerdapter.ViewResource holder, int position) {

        /*if (deliveryType.equalsIgnoreCase(context.getString(R.string.home_delivery))) {
            holder.binding.tvShowQr.setVisibility(View.GONE);
        } else {
            holder.binding.tvShowQr.setVisibility(View.VISIBLE);
        }*/

        if (orderCancelledList.get(position).getAllergens() != null && orderCancelledList.get(position).getAllergens().size() > 0) {
            holder.binding.llAllergy.setVisibility(View.VISIBLE);
            holder.binding.tvAllergyName.setText("ALLERGENS: "+String.join(", ",orderCancelledList.get(position).getAllergens()));
        }

        holder.binding.tvProductName.setText(orderCancelledList.get(position).getProduct_details().getProduct_name());
        holder.binding.tvProductName.setText(orderCancelledList.get(position).getProduct_details().getProduct_name());

        if (orderCancelledList.get(position).getProduct_details().getProduct_description() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.tvDesc.setText(Html.fromHtml(orderCancelledList.get(position).getProduct_details().getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.binding.tvDesc.setText(Html.fromHtml(orderCancelledList.get(position).getProduct_details().getProduct_description()));
            }
        }
        holder.binding.tvOrderedDate.setText(context.getString(R.string.order_placed) + HelperClass.formatddMMMMyyyy(order_date)  +" At "+HelperClass.formatDateTimeTO_Time(order_date));
        holder.binding.tvVenueName.setText(venue_name);
        holder.binding.tvVenueAddress.setText(address_1);
        holder.binding.tvPrice.setText(context.getString(R.string.pound) + orderCancelledList.get(position).getSelling_price());
        holder.binding.tvPriceTotal.setText(context.getString(R.string.total) + orderCancelledList.get(position).getSelling_price());
        Glide.with(context).load(TextUtils.isEmpty(orderCancelledList.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getModifier_images()).apply(new RequestOptions()
                // Glide.with(context).load(ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getProduct_details().getImages()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImg);


        holder.binding.ivProductImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_hospitality == 0) {
                    Intent product = new Intent(context, ProductDetailActivity.class);
                    product.putExtra(STORE_ID, venue_id);
                    product.putExtra(PRODUCT_ID, String.valueOf(orderCancelledList.get(position).getProduct_id()));
                    product.putExtra(OFFER_ID, String.valueOf(orderCancelledList.get(position).getOffer_id()));
                    product.putExtra(BARCODE_ID, "");
                    context.startActivity(product);
                } else if (is_hospitality == 1) {
                    Intent topStore = new Intent(context, VenueDetailActivity.class);
                    topStore.putExtra(STORE_ID, venue_id);
                    topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                    topStore.putExtra(CATEGORY_ID, "");
                    context.startActivity(topStore);
                }

            }
        });

        holder.binding.llTrackPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cancelStatus = "";
                if (orderCancelledList.get(holder.getAdapterPosition()).getAcknowledgement() == null) {
                    cancelStatus = context.getString(R.string.cancel);
                } else if (orderCancelledList.get(holder.getAdapterPosition()).getAcknowledgement().size() == 0) {
                    cancelStatus = context.getString(R.string.cancel);
                } else if (orderCancelledList.get(holder.getAdapterPosition()).getAcknowledgement().get(orderCancelledList.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 0) {
                    cancelStatus = context.getString(R.string.cancel_pending);
                } else if (orderCancelledList.get(holder.getAdapterPosition()).getAcknowledgement().get(orderCancelledList.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 2) {
                    cancelStatus = context.getString(R.string.cancel_rejected);
                } else if (orderCancelledList.get(holder.getAdapterPosition()).getAcknowledgement().get(orderCancelledList.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 1) {
                    cancelStatus = context.getString(R.string.cancelled);
                }


                myOrderListner.OnSelectOption(TRACK_PACKAGE, String.valueOf(status),
                        String.valueOf(orderCancelledList.get(holder.getAdapterPosition()).getProduct_qty()),
                        TextUtils.isEmpty(orderCancelledList.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getModifier_images(),
                        customer_address, venue_id, orderCancelledList.get(holder.getAdapterPosition()).getOffer_id(),
                        orderCancelledList.get(holder.getAdapterPosition()).getProduct_id(),
                        "" + orderCancelledList.get(holder.getAdapterPosition()).getId(), order_date, address_1, venue_images, cancel_date,
                        orderCancelledList.get(holder.getAdapterPosition()).getRattings(),
                        orderCancelledList.get(holder.getAdapterPosition()).getReview(), "",
                        "" + ""+orderCancelledList.get(position).getProduct_details().getId(),
                        venue_name,venue_contact,cancelStatus,deliveryType,collectTime, trackingData, is_hospitality, "", expected_shipping_date);
            }
        });

        holder.binding.llMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent chat = new Intent(context, ChatDetailActivity.class);
                chat.putExtra(VENUE_ID, venue_id);
                chat.putExtra(ORDER_ID, String.valueOf(list.get(holder.getAdapterPosition()).getOrder_id()));
                context.startActivity(chat);*/

                myOrderListner.OnSelectOption(MESSAGE, null, null,
                        TextUtils.isEmpty(orderCancelledList.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getModifier_images(),
                        null, venue_id, null, null,
                        String.valueOf(orderCancelledList.get(holder.getAdapterPosition()).getOrder_id()),
                        order_date, address_1, venue_images, cancel_date,
                        orderCancelledList.get(holder.getAdapterPosition()).getRattings(),
                        orderCancelledList.get(holder.getAdapterPosition()).getReview(), "", "",
                        venue_name,venue_contact,"",deliveryType,collectTime, trackingData, is_hospitality, "", expected_shipping_date);

            }
        });

        holder.binding.llReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  myOrderListner.OnSelectOption(SHOW_REVIEW, null, null, null, null, null, null, orderCancelledList.get(holder.getAdapterPosition()).getProduct_id(), null, order_date, address_1, venue_images, cancel_date);
            }
        });

        holder.binding.llRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperClass.shareImageWithText(context, orderCancelledList.get(holder.getAdapterPosition()).getProduct_details().getProduct_name(),
                        TextUtils.isEmpty(orderCancelledList.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + orderCancelledList.get(position).getModifier_images(),
                        holder.binding.ivProductImg, orderCancelledList.get(holder.getAdapterPosition()).getProduct_id(), orderCancelledList.get(holder.getAdapterPosition()).getOffer_id());
            }
        });

       /* holder.binding.tvShowQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.scanLoyaltyDialog(context, unique_code, context.getString(R.string.qr_code_message));
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return orderCancelledList == null ? 0 : orderCancelledList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellMyOrderCancelledInnerCopyBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
