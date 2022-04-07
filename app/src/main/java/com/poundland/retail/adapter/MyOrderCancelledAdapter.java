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
import com.poundland.retail.databinding.LayoutCellMyOrderCancelledBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;

import java.util.List;

public class  MyOrderCancelledAdapter extends RecyclerView.Adapter<MyOrderCancelledAdapter.ViewResource> {

    private Context context;
    private List<MyOrderResponseModel.OrdersBean.DataBean> list;

    public MyOrderCancelledAdapter(Context context, List<MyOrderResponseModel.OrdersBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyOrderCancelledAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_cancelled, parent, false);

        return new MyOrderCancelledAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderCancelledAdapter.ViewResource holder, int position) {

        if (list.get(position).getStatus() == 0) {
            if (list.get(position).getDelivery_type().equalsIgnoreCase(context.getString(R.string.home_delivery))) {
                setColor(holder, R.color.app_header_color, context.getString(R.string.waiting_for_ack));
            } else {
                setColor(holder, R.color.app_header_color, context.getString(R.string.waiting_for_dispatch));
            }
        } else if (list.get(position).getStatus() == 8) {
            setColor(holder, R.color.color_red, context.getString(R.string.cancel_pending));
        } else if (list.get(position).getStatus() == 1 || list.get(position).getStatus() == 5) {
            setColor(holder, R.color.color_red, context.getString(R.string.cancelled));
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
        }
/*blue color for waiting. orange being prepared and despatch.
         Then green when completed or delivered or ready for collection*/

        
        if (!TextUtils.isEmpty(list.get(position).getCanceled_date()) ) {
            holder.binding.tvCancelledDate.setText(context.getString(R.string.cancelled) + " " + HelperClass.formatddMMMMyyyy(list.get(position).getCanceled_date()) +" At "+HelperClass.formatDateTimeTO_Time(list.get(position).getCanceled_date()));
        } else {
            holder.binding.tvCancelledDate.setText(context.getString(R.string.cancelled));
        }
        holder.binding.tvOrderNo.setText(context.getString(R.string.order_no)+" "+list.get(position).getUnique_code());
        MyOrderCancelledAInnerdapter myOrderNewAdapter = new MyOrderCancelledAInnerdapter(context,
                list.get(position).getOrder_details(), new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        }, list.get(position).getVenue().getVenue_name(),
                list.get(position).getVenue().getAddress_1(),
                list.get(position).getOrder_date(),
                list.get(position).getCanceled_date(),
                list.get(position).getStatus(),
                list.get(position).getCustomer_address(),
                list.get(position).getVenue().getVenue_id(),
                list.get(position).getVenue().getVenue_images(),
                list.get(position).getVenue().getPhone_number(),list.get(position).getDelivery_type(),
                "(" + list.get(position).getVenue().getCollection_time() + " Mins)",
                list.get(position).getTrackingData(),list.get(position).getUnique_code(),list.get(position).getIs_hospitality(), list.get(position).getExpected_shipping_date());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
      //  holder.binding.rvRecycle.setLayoutManager(layoutManager);
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

    private void setColor(MyOrderCancelledAdapter.ViewResource holder, int color, String status) {
        holder.binding.rlArriving.setBackgroundResource(color);
        holder.binding.llOrderId.setBackgroundResource(color);

      //  holder.binding.tvOrderStatus.setText(status);

        holder.binding.tvCancelledDate.setTextColor(ContextCompat.getColor(context, R.color.color_white));
       // holder.binding.tvOrderStatus.setTextColor(ContextCompat.getColor(context, R.color.color_white));
        holder.binding.tvOrderNo.setTextColor(ContextCompat.getColor(context, R.color.color_white));

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
        public LayoutCellMyOrderCancelledBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
