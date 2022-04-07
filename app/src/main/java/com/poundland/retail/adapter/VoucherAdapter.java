package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellVoucherBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.GetCustomerLoyaltyResponseModel;

import java.util.ArrayList;
import java.util.List;

import static com.poundland.retail.interfaces.Constants.VOUCHER;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewResource> {

   private Context context;
    DrawerListner drawerListner;
    private List<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> rollAccessList;
    public VoucherAdapter(Context context, ArrayList<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> rollAccessList, DrawerListner drawerListner) {
        this.context = context;
        this.rollAccessList = rollAccessList;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public VoucherAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_voucher, parent, false);

        return new VoucherAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VoucherAdapter.ViewResource holder, int position) {

       /* holder.binding.textViewName.setText(data[position].name);
        holder.binding.imageViewIcon.setImageResource(data[position].icon);

       */

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(),VOUCHER);
            }
        });
    }


    @Override
    public int getItemCount() {
        return rollAccessList == null ? 0 : rollAccessList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellVoucherBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
