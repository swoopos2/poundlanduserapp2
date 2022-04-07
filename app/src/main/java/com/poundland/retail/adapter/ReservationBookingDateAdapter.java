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
import com.poundland.retail.databinding.ReservationBookingDateItemBinding;
import com.poundland.retail.interfaces.OnDateSelectListner;
import com.poundland.retail.model.ReservationBookingDate;

import java.util.ArrayList;
import java.util.List;

public class ReservationBookingDateAdapter extends RecyclerView.Adapter<ReservationBookingDateAdapter.ViewResource> {

    private Context mContext;
    private List<ReservationBookingDate> mItem;
    private LayoutInflater mInflater;
    private int lastSelectedPosition = 0;
    private ReservationBookingDateItemBinding lastSelectedBinding;
    private View lastSelectedView;
    private OnDateSelectListner drawerListner;
    private String searchDate;

    public ReservationBookingDateAdapter(Context mContext, OnDateSelectListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mItem = new ArrayList<>();
        this.drawerListner = drawerListner;

    }

    public ReservationBookingDate setSelectedDate() {
        return mItem.get(lastSelectedPosition);
    }

    public void clear() {
        if (mItem != null) {
            this.mItem.clear();
            notifyDataSetChanged();
        }
    }

    public ReservationBookingDate getSelectedDate() {
        if (mItem.size() == 0)
            return null;
        return mItem.get(lastSelectedPosition);
    }

    public void addItems(List<ReservationBookingDate> mItem) {
        this.mItem = mItem;
        notifyItemInserted(this.mItem.size());
    }

    public void setSelectedDate(String date) {
        this.searchDate = date;
    }

    @NonNull
    @Override
    public ReservationBookingDateAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_booking_date_item, parent, false);

        return new ReservationBookingDateAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationBookingDateAdapter.ViewResource holder, int position) {

        holder.binding.tvDayText.setText(HelperClass.formatDate(mItem.get(position).getDate(), HelperClass.dayText));
        holder.binding.tvDayMonth.setText(HelperClass.formatDate(mItem.get(position).getDate(), HelperClass.dayMonth));
        holder.binding.tvTodayTomorrow.setText(HelperClass.date2TodayTomorrow(mItem.get(position).getDate(), mItem.get(position).isEnable() ? "" : "OFF"));

        if (!mItem.get(position).isEnable()) {
            holder.itemView.setEnabled(false);
        } else {
            holder.itemView.setEnabled(true);
        }
        if (searchDate != null) {
            if (searchDate.contains(HelperClass.formatDate(mItem.get(position).getDate(), HelperClass.dayMonthyyyy))) {
                if (!mItem.get(position).isEnable()) {
                    holder.itemView.setEnabled(false);
                    lastSelectedPosition = position;
                    lastSelectedBinding = holder.binding;
                    lastSelectedView = holder.itemView;
                    holder.binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                    holder.binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                    holder.binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                } else {
                    holder.itemView.setSelected(true);
                    lastSelectedBinding = holder.binding;
                    lastSelectedView = holder.itemView;
                    holder.binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.color_white));
                }
            } else {
                holder.itemView.setSelected(false);
                if (!mItem.get(position).isEnable()) {
                    holder.itemView.setEnabled(false);
                    holder.binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                    holder.binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                    holder.binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                } else {
                    holder.binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.color_black));
                    holder.binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.color_black));
                    holder.binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.color_black));
                }
            }
        } else {

            if (lastSelectedPosition == position) {
                if (!mItem.get(position).isEnable()) {
                    //lastSelectedPosition++;
                    holder.itemView.setEnabled(false);
                    holder.binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                    holder.binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                    holder.binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                } else {
                    holder.itemView.setSelected(true);
                    lastSelectedBinding = holder.binding;
                    lastSelectedView = holder.itemView;
                    holder.binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.color_white));
                    holder.binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.color_white));
                }
            } else {
                holder.itemView.setSelected(false);
                if (!mItem.get(position).isEnable()) {
                    holder.itemView.setEnabled(false);
                    holder.binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                    holder.binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                    holder.binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                } else {
                    holder.binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.color_black));
                    holder.binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.color_black));
                    holder.binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.color_black));
                }
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
        public ReservationBookingDateItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (lastSelectedBinding != null) {
                if (lastSelectedView.isEnabled()) {
                    lastSelectedBinding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.color_black));
                    lastSelectedBinding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.color_black));
                    lastSelectedBinding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.color_black));
                }
                lastSelectedView.setSelected(false);

                lastSelectedPosition = getLayoutPosition();
                itemView.setSelected(true);
                lastSelectedBinding = binding;
                lastSelectedView = itemView;
                binding.tvDayText.setTextColor(mContext.getResources().getColor(R.color.color_white));
                binding.tvDayMonth.setTextColor(mContext.getResources().getColor(R.color.color_white));
                binding.tvTodayTomorrow.setTextColor(mContext.getResources().getColor(R.color.color_white));
            }
            drawerListner.onDrawerSelect(getLayoutPosition(), /*HelperClass.formatDate(*/mItem.get(getLayoutPosition()).getDate().toString()/*, HelperClass.dayText)*/);
        }
    }


}
