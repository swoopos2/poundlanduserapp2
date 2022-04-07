package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellAllGuestBinding;
import com.poundland.retail.model.responseModel.MyOrderReservationResponseModel;

import java.util.List;

public class GuestNameAdapter extends RecyclerView.Adapter<GuestNameAdapter.ViewResource> {

    private Context mContext;
    private List<MyOrderReservationResponseModel.ReservationsBean.DataBean.ReservationGuestBean> list;
    private LayoutInflater mInflater;

    public GuestNameAdapter(Context mContext, List<MyOrderReservationResponseModel.ReservationsBean.DataBean.ReservationGuestBean> reservation_guest) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.list = reservation_guest;

    }


    @NonNull
    @Override
    public GuestNameAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_all_guest, parent, false);

        return new GuestNameAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestNameAdapter.ViewResource holder, int position) {

        holder.binding.tvCustName.setText(list.get(position).getFirst_name());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LayoutCellAllGuestBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.cvMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
