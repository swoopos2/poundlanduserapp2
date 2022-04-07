package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.LayoutCellMyOrderReservInnerBinding;
import com.poundland.retail.interfaces.MyOrderListner;
import com.poundland.retail.model.responseModel.MyOrderReservationResponseModel;

import java.util.List;

public class MyOrderReservInnerAdapter extends RecyclerView.Adapter<MyOrderReservInnerAdapter.ViewResource> {

    private Context context;
    private MyOrderListner myOrderListner;
    private List<MyOrderReservationResponseModel.ReservationsBean.DataBean.OrdersMastersBean> orders_masters;

    public MyOrderReservInnerAdapter(Context context, List<MyOrderReservationResponseModel.ReservationsBean.DataBean.OrdersMastersBean> orders_masters) {
        this.context = context;
        this.orders_masters = orders_masters;
        //  this.myOrderListner = (MyOrderActivity) context;
    }

    @NonNull
    @Override
    public MyOrderReservInnerAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_reserv_inner, parent, false);

        return new MyOrderReservInnerAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrderReservInnerAdapter.ViewResource holder, int position) {


        holder.binding.tvOrderNo.setText(orders_masters.get(position).getUnique_code());
        holder.binding.tvAmount.setText(orders_masters.get(position).getNet_amount());
        holder.binding.tvDateTime.setText(orders_masters.get(position).getOrder_date());
        holder.binding.tvOrderType.setText(orders_masters.get(position).getDelivery_type());

        MyOrderReservInnerItemAdapter myOrderNewAdapter = new MyOrderReservInnerItemAdapter(context,orders_masters.get(position).getOrder_details(),orders_masters.get(position).getUnique_code(),
                orders_masters.get(position).getMerchant_id(),orders_masters.get(position).getVenue_id());
        holder.binding.rvOrderMasterDetail.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.binding.rvOrderMasterDetail.setAdapter(myOrderNewAdapter);

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
        public LayoutCellMyOrderReservInnerBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
