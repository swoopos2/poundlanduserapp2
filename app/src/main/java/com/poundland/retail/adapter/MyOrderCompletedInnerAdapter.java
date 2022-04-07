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
import com.poundland.retail.databinding.LayoutCellMyOrderCompletedInnerCopyBinding;
import com.poundland.retail.dialog.DialogUtils;
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
import static com.poundland.retail.interfaces.Constants.REPLACE_REFUND;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TRACK_PACKAGE;

public class MyOrderCompletedInnerAdapter extends RecyclerView.Adapter<MyOrderCompletedInnerAdapter.ViewResource> {

    private Context context;
    MyOrderListner myOrderListner;
    private List<MyOrderResponseModel.OrdersBean.DataBean.OrderDetailsBean> list;
    private List<MyOrderResponseModel.ReturnReasonsBean> returnReasonsList;
    String venue_name;
    String venue_images;
    String order_date;
    String address_1;
    private int status;
    private MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address;
    private String venue_id;
    private String venue_contact;
    private String deliverDate;
    private String deliveryType;
    private String collectTime;
    private String orderNo;
    private String unique_code;
    private String expected_shipping_date;
    private int is_hospitality;
    private List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData;

    public MyOrderCompletedInnerAdapter(Context context, List<MyOrderResponseModel.OrdersBean.DataBean.OrderDetailsBean> list, List<MyOrderResponseModel.ReturnReasonsBean> returnReasonsList, DrawerListner drawerListner, String venue_name,
                                        String address_1, String order_date, int status,
                                        MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address,
                                        String venue_id, String venue_images, String venue_contact, String deliverDate,
                                        String orderNo, String deliveryType, String collectTime,
                                        List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData, String unique_code, int is_hospitality, String expected_shipping_date) {
        this.context = context;
        this.list = list;
        this.trackingData = trackingData;
        this.returnReasonsList = returnReasonsList;
        this.venue_name = venue_name;
        this.venue_contact = venue_contact;
        this.venue_id = venue_id;
        this.venue_images = venue_images;
        this.status = status;
        this.order_date = order_date;
        this.deliverDate = deliverDate;
        this.address_1 = address_1;
        this.orderNo = orderNo;
        this.is_hospitality = is_hospitality;
        this.unique_code = unique_code;
        this.expected_shipping_date = expected_shipping_date;
        this.deliveryType = deliveryType;
        this.collectTime = collectTime;
        this.customer_address = customer_address;
        this.myOrderListner = (MyOrderActivity) context;
    }

    @NonNull
    @Override
    public MyOrderCompletedInnerAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_completed_inner_copy, parent, false);

        return new MyOrderCompletedInnerAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderCompletedInnerAdapter.ViewResource holder, int position) {
        if (deliveryType != null && deliveryType.equalsIgnoreCase(context.getString(R.string.home_delivery))) {
            holder.binding.tvShowQr.setVisibility(View.GONE);
        } else {
            if (status == 0 || status == 8) {
                holder.binding.tvShowQr.setVisibility(View.GONE);
            } else
                holder.binding.tvShowQr.setVisibility(View.GONE);
        }

        if (list.get(position).getAllergens() != null && list.get(position).getAllergens().size() > 0) {
            holder.binding.llAllergy.setVisibility(View.VISIBLE);
            holder.binding.tvAllergyName.setText("ALLERGENS: " + String.join(", ", list.get(position).getAllergens()));
        }

        holder.binding.tvNote.setText(context.getString(R.string.replace_refund_note_start) + " " + list.get(position).getRemaining_return_days() + " " + context.getString(R.string.replace_refund_note_end));

        if (list.get(position).getRemaining_return_days() > 0) {
            holder.binding.tvReplaceRefund.setVisibility(View.VISIBLE);
            holder.binding.llNote.setVisibility(View.VISIBLE);
        } else {
            // holder.binding.tvReplaceRefund.setText("No " + context.getString(R.string.return_refund));
            holder.binding.tvReplaceRefund.setVisibility(View.GONE);
            holder.binding.llNote.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(list.get(holder.getAdapterPosition()).getOrder_track_status())) {
            String refundStatus = list.get(holder.getAdapterPosition()).getOrder_track_status();

            if (refundStatus.equals(context.getString(R.string.refund))) {
                holder.binding.tvReplaceRefund.setVisibility(View.VISIBLE);
                holder.binding.llNote.setVisibility(View.VISIBLE);
                holder.binding.tvReplaceRefund.setText(context.getString(R.string.return_refund));
                holder.binding.tvNote.setText(context.getString(R.string.replace_refund_note_start) + " " + list.get(position).getRemaining_return_days() + " " + context.getString(R.string.replace_refund_note_end));
            } else if (refundStatus.equals(context.getString(R.string.return_pending))) {
                holder.binding.tvReplaceRefund.setVisibility(View.VISIBLE);
                holder.binding.llNote.setVisibility(View.VISIBLE);
                holder.binding.tvReplaceRefund.setText(context.getString(R.string.return_pending));
                holder.binding.tvNote.setText("Refund request is in progress. We will inform you when it done.");
            } else if (refundStatus.equals(context.getString(R.string.return_rejected))) {
                if (list.get(position).getRemaining_return_days() > 0) {
                    holder.binding.tvReplaceRefund.setVisibility(View.GONE);
                    holder.binding.tvReplaceRefund.setText("  " + context.getString(R.string.return_rejected) + "  ");

                    holder.binding.tvReplaceRejected.setVisibility(View.VISIBLE);


                    // holder.binding.tvReplaceRefund.setText(context.getString(R.string.return_refund));
                    holder.binding.llNote.setVisibility(View.GONE);
                    // its rejected but since we can place refund so we are showing return/refund here instead of Refund rejected
                } else {
                    // holder.binding.tvReplaceRefund.setText("No " + context.getString(R.string.return_refund));
                    holder.binding.tvReplaceRefund.setVisibility(View.GONE);
                    holder.binding.llNote.setVisibility(View.GONE);
                }
            } else if (refundStatus.equals(context.getString(R.string.refunded))) {
                holder.binding.tvReplaceRefund.setVisibility(View.VISIBLE);
                holder.binding.tvReplaceRefund.setText("  " + context.getString(R.string.refunded) + "  ");   // Refunded
                holder.binding.llNote.setVisibility(View.GONE);
            }

        } else {
            if (list.get(holder.getAdapterPosition()).getAcknowledgement() != null && list.get(holder.getAdapterPosition()).getAcknowledgement().size() > 0) {
                if (status == 5 && list.get(holder.getAdapterPosition()).getAcknowledgement().get(list.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 0) {
                    holder.binding.tvReplaceRefund.setVisibility(View.VISIBLE);
                    holder.binding.llNote.setVisibility(View.VISIBLE);
                    holder.binding.tvReplaceRefund.setText(context.getString(R.string.return_pending));
                    holder.binding.tvNote.setText("Refund request is in progress. We will inform you when it done.");

                } else if (status == 5 && list.get(holder.getAdapterPosition()).getAcknowledgement().get(list.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 1) {
                    holder.binding.tvReplaceRefund.setVisibility(View.VISIBLE);
                    holder.binding.tvReplaceRefund.setText("  " + context.getString(R.string.refunded) + "  ");   // Refunded
                    holder.binding.llNote.setVisibility(View.GONE);
                } else if (status == 5 && list.get(holder.getAdapterPosition()).getAcknowledgement().get(list.get(holder.getAdapterPosition()).getAcknowledgement().size() - 1).getVenue_action() == 2) {

                    if (list.get(position).getRemaining_return_days() > 0) {
                        holder.binding.tvReplaceRefund.setVisibility(View.VISIBLE);
                        holder.binding.tvReplaceRefund.setText("  " + context.getString(R.string.return_rejected) + "  ");   // Refund rejected
                        holder.binding.llNote.setVisibility(View.GONE);
                    } else {
                        // holder.binding.tvReplaceRefund.setText("No " + context.getString(R.string.return_refund));
                        holder.binding.tvReplaceRefund.setVisibility(View.GONE);
                        holder.binding.llNote.setVisibility(View.GONE);
                    }
                }
            }
        }

/////// status is 5 and then check venue action
        holder.binding.tvProductName.setText(list.get(position).getProduct_details().getProduct_name());
        if (list.get(position).getProduct_details().getProduct_description() != null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.tvDesc.setText(Html.fromHtml(list.get(position).getProduct_details().getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.binding.tvDesc.setText(Html.fromHtml(list.get(position).getProduct_details().getProduct_description()));
            }

        holder.binding.tvOrderedDate.setText(context.getString(R.string.order_placed) + HelperClass.formatddMMMMyyyy(order_date) + " At " + HelperClass.formatDateTimeTO_Time(order_date));
        holder.binding.tvVenueName.setText(venue_name);
        holder.binding.tvVenueAddress.setText(address_1);
        holder.binding.tvPrice.setText(context.getString(R.string.pound) + list.get(position).getSelling_price());
        holder.binding.tvPriceTotal.setText(context.getString(R.string.total) + list.get(position).getSelling_price());
        Glide.with(context).load(TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images()).apply(new RequestOptions()
                // Glide.with(context).load(ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImg);

        holder.binding.llTrackPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cancelStatus = "";
                cancelStatus = holder.binding.tvReplaceRefund.getText().toString().trim();

                myOrderListner.OnSelectOption(TRACK_PACKAGE, String.valueOf(list.get(holder.getAdapterPosition()).getStatus()), String.valueOf(list.get(holder.getAdapterPosition()).getProduct_qty()),
                        TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images(),
                        customer_address, venue_id, list.get(holder.getAdapterPosition()).getOffer_id(),
                        list.get(holder.getAdapterPosition()).getProduct_id(), "" + list.get(holder.getAdapterPosition()).getId(),
                        order_date, address_1, venue_images,
                        deliverDate, list.get(holder.getAdapterPosition()).getRattings(),
                        list.get(holder.getAdapterPosition()).getReview(), list.get(position).getProduct_details().getProduct_name(),
                        "" + list.get(position).getProduct_details().getId(), venue_name, venue_contact,
                        cancelStatus, deliveryType, collectTime, trackingData, is_hospitality, "", expected_shipping_date);
            }
        });

        holder.binding.tvReplaceRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.binding.tvReplaceRefund.getText().toString().trim().equals(context.getString(R.string.return_rejected)) || holder.binding.tvReplaceRefund.getText().toString().trim().equals(context.getString(R.string.return_refund))) {
                    try {
                        myOrderListner.OnSelectOption(REPLACE_REFUND, null,
                                String.valueOf(list.get(holder.getAdapterPosition()).getProduct_qty()),
                                TextUtils.isEmpty(list.get(holder.getAdapterPosition()).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(holder.getAdapterPosition()).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(holder.getAdapterPosition()).getModifier_images(),
                                null, venue_name, null,
                                String.valueOf(list.get(holder.getAdapterPosition()).getId()),
                                String.valueOf(list.get(holder.getAdapterPosition()).getOrder_id()),
                                returnReasonsList.get(position).getReason(), null, null,
                                "", list.get(holder.getAdapterPosition()).getRattings(),
                                list.get(holder.getAdapterPosition()).getReview(),
                                list.get(holder.getAdapterPosition()).getProduct_details().getProduct_name(),
                                "" + list.get(holder.getAdapterPosition()).getProduct_details().getId(),
                                venue_name, venue_contact, "", deliveryType, collectTime, trackingData, is_hospitality, "", expected_shipping_date);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        HelperClass.toast(context, exc.getMessage());
                    }
                }

            }
        });

        holder.binding.tvReplaceRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.binding.tvReplaceRefund.getText().toString().trim().equals(context.getString(R.string.return_rejected)) || holder.binding.tvReplaceRefund.getText().toString().trim().equals(context.getString(R.string.return_refund))) {
                    try {
                        myOrderListner.OnSelectOption(REPLACE_REFUND, null,
                                String.valueOf(list.get(holder.getAdapterPosition()).getProduct_qty()),
                                TextUtils.isEmpty(list.get(holder.getAdapterPosition()).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(holder.getAdapterPosition()).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(holder.getAdapterPosition()).getModifier_images(),
                                null, venue_name, null,
                                String.valueOf(list.get(holder.getAdapterPosition()).getId()),
                                String.valueOf(list.get(holder.getAdapterPosition()).getOrder_id()),
                                returnReasonsList.get(position).getReason(), null, null,
                                "", list.get(holder.getAdapterPosition()).getRattings(),
                                list.get(holder.getAdapterPosition()).getReview(),
                                list.get(holder.getAdapterPosition()).getProduct_details().getProduct_name(),
                                "" + list.get(holder.getAdapterPosition()).getProduct_details().getId(),
                                venue_name, venue_contact, "", deliveryType, collectTime, trackingData, is_hospitality, "", expected_shipping_date);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        HelperClass.toast(context, exc.getMessage());
                    }
                }

            }
        });

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


        holder.binding.llMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent chat = new Intent(context, ChatDetailActivity.class);
                chat.putExtra(VENUE_ID, venue_id);
                chat.putExtra(ORDER_ID, String.valueOf(list.get(holder.getAdapterPosition()).getOrder_id()));
                context.startActivity(chat);*/

                myOrderListner.OnSelectOption(MESSAGE, null, null,
                        TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images(), null, venue_id, null, null,
                        String.valueOf(list.get(holder.getAdapterPosition()).getOrder_id()), order_date, address_1, venue_images,
                        "", list.get(holder.getAdapterPosition()).getRattings(),
                        list.get(holder.getAdapterPosition()).getReview(), list.get(position).getProduct_details().getProduct_name(),
                        "" + list.get(position).getProduct_details().getId(),
                        venue_name, venue_contact, "", deliveryType, collectTime, trackingData, is_hospitality, "", expected_shipping_date);
            }
        });


        holder.binding.llReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  myOrderListner.OnSelectOption(REVIEW_TO_PRODUCT, String.valueOf(holder.getAdapterPosition()), null,
                        TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images(),
                        null, null, null, "" + list.get(holder.getAdapterPosition()).getId(),
                        "" + list.get(holder.getAdapterPosition()).getOrder_id(), order_date, address_1, venue_images,
                        "", list.get(holder.getAdapterPosition()).getRattings(),
                        list.get(holder.getAdapterPosition()).getReview(),
                        list.get(position).getProduct_details().getProduct_name(),
                        "" + list.get(position).getId(), venue_name, venue_contact, "", deliveryType, collectTime, trackingData, is_hospitality, "", expected_shipping_date);
           */
            }

        });
 /* if (holder.binding.tvReplaceRefund.getText().toString().equals(context.getString(R.string.return_refund))) {
                    DialogUtils.dialogReasonForCancellation(context, returnReasonsList, new DrawerListner() {
                        @Override
                        public void onDrawerSelect(int position, int clickId) {
                            try {


                            myOrderListner.OnSelectOption(REPLACE_REFUND, null,
                                    String.valueOf(list.get(holder.getAdapterPosition()).getProduct_qty()),
                                    TextUtils.isEmpty(list.get(holder.getAdapterPosition()).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(holder.getAdapterPosition()).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(holder.getAdapterPosition()).getModifier_images(),
                                    null, venue_name, null,
                                    String.valueOf(list.get(holder.getAdapterPosition()).getId()),
                                    String.valueOf(list.get(holder.getAdapterPosition()).getOrder_id()),
                                    returnReasonsList.get(position).getReason(), null, null,
                                    "", list.get(holder.getAdapterPosition()).getRattings(),
                                    list.get(holder.getAdapterPosition()).getReview(),
                                    list.get(holder.getAdapterPosition()).getProduct_details().getProduct_name(),
                                    "" + list.get(holder.getAdapterPosition()).getProduct_details().getId(), venue_name, venue_contact, "");
                            } catch (Exception exc) {
                                exc.printStackTrace();
                                HelperClass.toast(context,exc.getMessage());
                            }

                        }

                    });
                }*/
        holder.binding.llRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperClass.shareImageWithText(context, list.get(holder.getAdapterPosition()).getProduct_details().getProduct_name(),
                        TextUtils.isEmpty(list.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + list.get(position).getProduct_details().getImages() : ApiRequestUrl.BASE_URL + list.get(position).getModifier_images(),
                        holder.binding.ivProductImg, list.get(holder.getAdapterPosition()).getProduct_id(), list.get(holder.getAdapterPosition()).getOffer_id());
            }
        });


        holder.binding.tvShowQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.scanLoyaltyDialog(context, unique_code, context.getString(R.string.qr_code_message));
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
        public LayoutCellMyOrderCompletedInnerCopyBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
