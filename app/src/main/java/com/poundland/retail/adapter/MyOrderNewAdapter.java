package com.poundland.retail.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.LayoutCellMyOrderNewBinding;
import com.poundland.retail.interfaces.CancelListner;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;

import java.util.List;

public class MyOrderNewAdapter extends RecyclerView.Adapter<MyOrderNewAdapter.ViewResource> {

    private Context context;
    CancelListner cancelListner;
    private List<MyOrderResponseModel.OrdersBean.DataBean> list;

    public MyOrderNewAdapter(Context context, List<MyOrderResponseModel.OrdersBean.DataBean> list, CancelListner cancelListner) {
        this.context = context;
        this.list = list;
        this.cancelListner = cancelListner;
    }

    @NonNull
    @Override
    public MyOrderNewAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_new, parent, false);

        return new MyOrderNewAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderNewAdapter.ViewResource holder, int position) {

        if (list.get(position).getStatus() != 3) {
            holder.binding.tvArrivingDate.setVisibility(View.INVISIBLE);
        }

        if (list.get(position).getDelivery_type().equalsIgnoreCase(context.getString(R.string.home_delivery))) {
            holder.binding.tvArrivingLabel.setText(context.getString(R.string.arriving));
            holder.binding.tvArrivingDate.setText("(" + list.get(position).getVenue().getStart_days() + "-" + list.get(position).getVenue().getEnd_days() + " Days)");
        } else if (list.get(position).getDelivery_type().equalsIgnoreCase(context.getString(R.string.reservation))) {
            holder.binding.tvArrivingLabel.setText(context.getString(R.string.table_order) + ":" + list.get(position).getTable_no());
            holder.binding.tvArrivingDate.setText("(" + list.get(position).getVenue().getStart_days() + "-" + list.get(position).getVenue().getEnd_days() + " Mins)");
            holder.binding.tvTableNo.setVisibility(View.INVISIBLE);

        } else {
            holder.binding.tvArrivingLabel.setText(context.getString(R.string.collect_after));
            holder.binding.tvArrivingDate.setText("(" + list.get(position).getVenue().getCollection_time() + " Mins)");
        }

        if (list.get(position).getStatus() == 0 || list.get(position).getStatus() == 8) {
            if (list.get(position).getDelivery_type().equalsIgnoreCase(context.getString(R.string.home_delivery))) {
                setColor(holder, R.color.app_header_color, context.getString(R.string.waiting_for_ack));
            } else {
                setColor(holder, R.color.app_header_color, context.getString(R.string.waiting_for_dispatch));
            }
        } else if (list.get(position).getStatus() == 1 || list.get(position).getStatus() == 5) {
            setColor(holder, R.color.color_red, context.getString(R.string.cancelled));
        } else if (list.get(position).getStatus() == 2) {


            if (list.get(position).getDelivery_type().equalsIgnoreCase(context.getString(R.string.reservation))) {
                setColor(holder, R.color.color_saffron_lite, context.getString(R.string.acknowledged));
            } else {
                setColor(holder, R.color.color_saffron_lite, context.getString(R.string.being_prepared));
            }


        } else if (list.get(position).getStatus() == 3) {
            if (list.get(position).getDelivery_type().equalsIgnoreCase(context.getString(R.string.home_delivery))) {
                setColor(holder, R.color.color_saffron_lite, context.getString(R.string.dispatch));
            } else {
                setColor(holder, R.color.light_green, context.getString(R.string.ready));
            }
        } else if (list.get(position).getStatus() == 4) {
            setColor(holder, R.color.light_green, context.getString(R.string.completed));
        }

        /*blue color for waiting. orange being prepared and despatch.
         Then green when completed or delivered or ready for collection*/

        holder.binding.tvOrderDate.setText(HelperClass.formatddMMMMyyyy(list.get(position).getOrder_date()) + "\n At " + HelperClass.formatDateTimeTO_Time(list.get(position).getOrder_date()));

        holder.binding.tvOrderNo.setText(context.getString(R.string.order_no) + " " + list.get(position).getUnique_code());

        if (!TextUtils.isEmpty(list.get(position).getLoyelty_used_amount()) && Double.parseDouble(list.get(position).getLoyelty_used_amount()) > 0) {
            double amt = Double.parseDouble(list.get(position).getNet_amount()) - Double.parseDouble(list.get(position).getLoyelty_used_amount());
            holder.binding.tvSubTotal.setText(context.getString(R.string.total) + String.format("%.2f", amt) + " + " + context.getString(R.string.pound) + list.get(position).getLoyelty_used_amount() + "\n(Loyalty point)");
        } else {
            holder.binding.tvSubTotal.setText(context.getString(R.string.total) + list.get(position).getNet_amount());
        }

        MyOrderNewInnerAdapter myOrderNewAdapter = new MyOrderNewInnerAdapter(context, list.get(position).getOrder_details(),
                new DrawerListner() {
                    @Override
                    public void onDrawerSelect(int order_id, int order_details_id) {
                        cancelListner.onCancelOrder(order_id, order_details_id);
                    }
                }, list.get(position).getVenue().getVenue_name(),
                list.get(position).getVenue().getAddress_1(),
                list.get(position).getStatus(),
                list.get(position).getCustomer_address(),
                list.get(position).getVenue().getVenue_id(),
                list.get(position).getOrder_date(),
                list.get(position).getVenue().getVenue_images(),
                list.get(position).getVenue().getPhone_number(),
                list.get(position).getDelivery_type(),
                "(" + list.get(position).getVenue().getCollection_time() + " Mins)",
                list.get(position).getTrackingData(),
                list.get(position).getUnique_code(),
                list.get(position).getIs_hospitality(),
                list.get(position).getVenue().getStart_days() + " - " + list.get(position).getVenue().getEnd_days(),
                list.get(position).getExpected_shipping_date());

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
    }

    private void setColor(ViewResource holder, int color, String status) {

        holder.binding.rlArriving.setBackgroundResource(color);
        holder.binding.llOrderId.setBackgroundResource(color);

        holder.binding.tvOrderStatus.setText(status);

        holder.binding.tvOrderStatus.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvArrivingLabel.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvArrivingDate.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvOrderDate.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvOrderDateLabel.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvOrderNo.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvSubTotal.setTextColor(ContextCompat.getColor(context, R.color.color_white));

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
        public LayoutCellMyOrderNewBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
