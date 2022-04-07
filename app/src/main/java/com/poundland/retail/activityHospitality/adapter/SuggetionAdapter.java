package com.poundland.retail.activityHospitality.adapter;

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

public class SuggetionAdapter extends RecyclerView.Adapter<SuggetionAdapter.ViewResource> {

    private Context mContext;
    private List<ReservationBookingDate> mItem;
    private LayoutInflater mInflater;
    private OnDateSelectListner drawerListner;

    public SuggetionAdapter(Context mContext) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mItem = new ArrayList<>();
        this.drawerListner = drawerListner;

    }

    public void addItems(List<ReservationBookingDate> mItem) {
        this.mItem = mItem;
        notifyItemInserted(this.mItem.size());
    }


    @NonNull
    @Override
    public SuggetionAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_booking_date_item, parent, false);

        return new SuggetionAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggetionAdapter.ViewResource holder, int position) {

        holder.binding.tvDayText.setText(HelperClass.formatDate(mItem.get(position).getDate(), HelperClass.dayText));

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

       }
    }


}
