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
import com.poundland.retail.databinding.LayoutCellMyOrderNewInnerCopyBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.CancelListner;
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

public class MyOrderNewInnerAdapter extends RecyclerView.Adapter<MyOrderNewInnerAdapter.ViewResource> {

    private Context context;
    private MyOrderListner myOrderListner;
    private CancelListner cancelListner;
    private List<MyOrderResponseModel.OrdersBean.DataBean.OrderDetailsBean> list;
    private String venue_name;
    private String address_1;
    private String venue_contact;
    private String venue_id;
    private String venue_images;
    private String order_date;
    private String deliveryType;
    private String collectTime;
    private String unique_code;
    private String estDeliveryDays;
    private String expected_shipping_date;
    private int status;
    private int is_hospitality;
    private MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address;
    private List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData;

    public MyOrderNewInnerAdapter(Context context, List<MyOrderResponseModel.OrdersBean.DataBean.OrderDetailsBean> list,
                                  DrawerListner drawerListner, String venue_name, String address_1, int status,
                                  MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address,
                                  String venue_id, String order_date, String venue_images, String venue_contact, String deliveryType,
                                  String collectTime, List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData,
                                  String unique_code, int is_hospitality, String estDeliveryDays, String expected_shipping_date) {
        this.context = context;
        this.list = list;
        this.trackingData = trackingData;
        this.venue_name = venue_name;
        this.venue_contact = venue_contact;
        this.venue_images = venue_images;
        this.address_1 = address_1;
        this.status = status;
        this.is_hospitality = is_hospitality;
        this.customer_address = customer_address;
        this.venue_id = venue_id;
        this.order_date = order_date;
        this.collectTime = collectTime;
        this.unique_code = unique_code;
        this.estDeliveryDays = estDeliveryDays;
        this.expected_shipping_date = expected_shipping_date;
        this.deliveryType = deliveryType;
        //        this.drawerListner = drawerListner;
        this.myOrderListner = (MyOrderActivity) context;
        this.cancelListner = (MyOrderActivity) context;
    }

    @NonNull
    @Override
    public MyOrderNewInnerAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_new_inner_copy, parent, false);

        return new MyOrderNewInnerAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderNewInnerAdapter.ViewResource holder, int position) {


        if (deliveryType.equalsIgnoreCase(context.getString(R.string.home_delivery))) {
            holder.binding.tvShowQr.setVisibility(View.GONE);
        } else {
            if (status == 0 || status == 8) {
                holder.binding.tvShowQr.setVisibility(View.GONE);
            } else
                holder.binding.tvShowQr.setVisibility(View.GONE);
        }

        if (list.get(position).getAllergens() != null && list.get(position).getAllergens().size() > 0) {
            holder.binding.llAllergy.setVisibility(View.VISIBLE);
            holder.binding.tvAllergyName.setText("ALLERGENS: "+String.join(", ",list.get(position).getAllergens()));
        }

        if (TextUtils.isEmpty(list.get(position).getAttributes()) || list.get(position).getAttributes().equalsIgnoreCase("S")){
            holder.binding.tvProductName.setText(list.get(position).getProduct_details().getProduct_name());
        }else holder.binding.tvProductName.setText(list.get(position).getProduct_details().getProduct_name()+" ("+list.get(position).getAttributes()+")");


        if (list.get(position).getProduct_details().getProduct_description() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.tvDesc.setText(Html.fromHtml(list.get(position).getProduct_details().getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.binding.tvDesc.setText(Html.fromHtml(list.get(position).getProduct_details().getProduct_description()));
            }
        }
        holder.binding.tvVenueName.setText(venue_name);
        holder.binding.tvVenueAddress.setText(address_1);
        if (list.get(position).getCase_qty() > 0) {
            holder.binding.tvPrice.setText(context.getString(R.string.pound) + list.get(position).getBulk_sale_price());
        } else
            holder.binding.tvPrice.setText(context.getString(R.string.pound) + list.get(position).getNet_amount());


        Glide.with(context).load(TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImg);


        holder.binding.ivProductImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_hospitality == 0) {
                    Intent product = new Intent(context, ProductDetailActivity.class);
                    product.putExtra(STORE_ID, venue_id);
                    product.putExtra(PRODUCT_ID, String.valueOf(list.get(position).getProduct_id()));
                    product.putExtra(OFFER_ID, String.valueOf(list.get(position).getOffer_id()));
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

                if (!TextUtils.isEmpty(list.get(holder.getAdapterPosition()).getOrder_track_status())) {
                    cancelStatus = list.get(holder.getAdapterPosition()).getOrder_track_status();
                } else {
                    if (list.get(holder.getAdapterPosition()).getAcknowledgement() == null) {
                        cancelStatus = context.getString(R.string.cancel);
                    } else if (list.get(holder.getAdapterPosition()).getAcknowledgement().size() == 0) {
                        cancelStatus = context.getString(R.string.cancel);
                    } else if (status == 1 && list.get(holder.getAdapterPosition()).getAcknowledgement().get(list.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 0) {
                        cancelStatus = context.getString(R.string.cancel_pending);
                    } else if (status == 1 && list.get(holder.getAdapterPosition()).getAcknowledgement().get(list.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 2) {
                        cancelStatus = context.getString(R.string.cancel_rejected);
                    } else if (status == 1 && list.get(holder.getAdapterPosition()).getAcknowledgement().get(list.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 1) {
                        cancelStatus = context.getString(R.string.cancelled);
                    }
                }
                ///   ack status 1 then venue action check
                myOrderListner.OnSelectOption(TRACK_PACKAGE, String.valueOf(status),
                        String.valueOf(list.get(holder.getAdapterPosition()).getProduct_qty()),
                        TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images(),
                        customer_address, venue_id, list.get(holder.getAdapterPosition()).getOffer_id(),
                        list.get(holder.getAdapterPosition()).getProduct_id(),
                        "" + list.get(holder.getAdapterPosition()).getId(), order_date, address_1, venue_images,
                        "", list.get(holder.getAdapterPosition()).getRattings(),
                        list.get(holder.getAdapterPosition()).getReview(), "",
                        "" + list.get(holder.getAdapterPosition()).getOrder_id(), venue_name
                        , venue_contact, cancelStatus, deliveryType, collectTime, trackingData, is_hospitality, estDeliveryDays, expected_shipping_date);

                ////////////////////////// getOrder_id  is being send in callback instead of productDetailId ,bcz we need to send it TrackPackage
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
                        TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images(), null, venue_id,
                        null, null, String.valueOf(list.get(holder.getAdapterPosition()).getOrder_id()), order_date,
                        address_1, venue_images, "", list.get(holder.getAdapterPosition()).getRattings(),
                        list.get(holder.getAdapterPosition()).getReview(), "", "", venue_name,
                        venue_contact, "", deliveryType, collectTime, trackingData, is_hospitality, estDeliveryDays, expected_shipping_date);

            }
        });

        holder.binding.llReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent review = new Intent(context, ReviewListActivity.class);
                review.putExtra(PRODUCT_ID, list.get(holder.getAdapterPosition()).getProduct_id());
                context.startActivity(review);*/

              /*  myOrderListner.OnSelectOption(SHOW_REVIEW,null,null,null,null,null,
                        null,list.get(holder.getAdapterPosition()).getProduct_id(),null, order_date, address_1,venue_images, "");
*/
            }
        });

        holder.binding.tvShowQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.scanLoyaltyDialog(context, unique_code, context.getString(R.string.qr_code_message));
            }
        });

        holder.binding.llRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperClass.shareImageWithText(context, list.get(holder.getAdapterPosition()).getProduct_details().getProduct_name(),
                        TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images(),
                        holder.binding.ivProductImg, list.get(holder.getAdapterPosition()).getProduct_id(),
                        list.get(holder.getAdapterPosition()).getOffer_id());
            }
        });

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
        public LayoutCellMyOrderNewInnerCopyBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
