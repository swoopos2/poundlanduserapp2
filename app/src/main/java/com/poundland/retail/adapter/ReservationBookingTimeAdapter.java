package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.databinding.ReservationBookingTimeItemBinding;
import com.poundland.retail.model.responseModel.ReservationOptionDataResponseModel;

import java.util.ArrayList;
import java.util.List;

public class ReservationBookingTimeAdapter extends RecyclerView.Adapter<ReservationBookingTimeAdapter.ViewResource> {

    private Context mContext;
    private List<ReservationOptionDataResponseModel.DataBean.ReservationSlotBean> mItem;
    private LayoutInflater mInflater;
    private int lastSelectedPosition = 0;
    private ReservationBookingTimeItemBinding lastSelectedBinding;
    private View lastSelectedView;
    private String searchTime;

    public ReservationBookingTimeAdapter(Context mContext) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mItem = new ArrayList<>();
    }

    public ReservationOptionDataResponseModel.DataBean.ReservationSlotBean getSelectedTime() {
        if (mItem.size() == 0)
            return null;
        return mItem.get(lastSelectedPosition);
    }

    public void clear() {
        this.mItem.clear();
        notifyDataSetChanged();
    }
    public void addItems(List<ReservationOptionDataResponseModel.DataBean.ReservationSlotBean> mItem) {
        this.mItem = mItem;
        notifyItemInserted(this.mItem.size());
    }

    public void setSelectedTime(String time) {
        this.searchTime = time;
       // notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReservationBookingTimeAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_booking_time_item, parent, false);

        return new ReservationBookingTimeAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationBookingTimeAdapter.ViewResource holder, int position) {

        holder.binding.tvTime.setText(HelperClass.formatHrMinSec_TO_hrMin(mItem.get(position).getTime()));

        if (searchTime != null) {
            if (searchTime.substring(0, 5).contains(HelperClass.formatHrMinSec_TO_hrMin(mItem.get(position).getTime()))) {
                searchTime = null;
                holder.itemView.setSelected(true);
                lastSelectedBinding = holder.binding;
                lastSelectedView = holder.itemView;
                holder.binding.tvTime.setTextColor(mContext.getResources().getColor(R.color.color_white));
                lastSelectedPosition = position;
            } else {
                if (searchTime == null && lastSelectedPosition == position) {
                    lastSelectedPosition = position;
                    holder.itemView.setSelected(true);
                    lastSelectedBinding = holder.binding;
                    lastSelectedView = holder.itemView;
                    holder.binding.tvTime.setTextColor(mContext.getResources().getColor(R.color.color_white));
                } else {

                    holder.itemView.setSelected(false);
                    holder.binding.tvTime.setTextColor(mContext.getResources().getColor(R.color.color_black));
                }
            }
        } else {
            if (lastSelectedPosition == position) {
                lastSelectedPosition = position;
                holder.itemView.setSelected(true);
                lastSelectedBinding = holder.binding;
                lastSelectedView = holder.itemView;
                holder.binding.tvTime.setTextColor(mContext.getResources().getColor(R.color.color_white));
            } else {
                holder.itemView.setSelected(false);
                holder.binding.tvTime.setTextColor(mContext.getResources().getColor(R.color.color_black));
            }
        }
    }


    @Override
    public int getItemCount() {
        return mItem.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ReservationBookingTimeItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (lastSelectedBinding != null) {
                lastSelectedBinding.tvTime.setTextColor(mContext.getResources().getColor(R.color.color_black));

                lastSelectedView.setSelected(false);

                lastSelectedPosition = getLayoutPosition();
                itemView.setSelected(true);
                lastSelectedBinding = binding;
                lastSelectedView = itemView;
                binding.tvTime.setTextColor(mContext.getResources().getColor(R.color.color_white));
            }
        }

    }


}
