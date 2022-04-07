package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellStampCardBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.GetCustomerLoyaltyResponseModel;

import java.util.ArrayList;
import java.util.List;

import static com.poundland.retail.interfaces.Constants.STAMP;

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.ViewResource> implements DrawerListner {

   private Context context;
    DrawerListner drawerListner;
    private List<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> rollAccessList;
    public StampAdapter(Context context, ArrayList<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> rollAccessList, DrawerListner drawerListner) {
        this.context = context;
        this.rollAccessList = rollAccessList;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public StampAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_stamp_card, parent, false);

        return new StampAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StampAdapter.ViewResource holder, int position) {


        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(),STAMP);
            }
        });

            StampInnerCardAdapter loyaltyAdapter = new StampInnerCardAdapter(context, rollAccessList, this);
            final GridLayoutManager layoutManager = new GridLayoutManager(context, 4);
            holder.binding.rvStampList.setLayoutManager(layoutManager);
            holder.binding.rvStampList.setAdapter(loyaltyAdapter);

    }


    @Override
    public int getItemCount() {
        return rollAccessList == null ? 0 : rollAccessList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {

    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellStampCardBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
