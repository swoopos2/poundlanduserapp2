package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LoyaltyPointsItemViewBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.GetCustomerLoyaltyResponseModel;

import java.util.ArrayList;
import java.util.List;

public class LoyaltyAdapter extends RecyclerView.Adapter<LoyaltyAdapter.ViewResource> {

    private Context context;
    DrawerListner drawerListner;
    private List<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> loyaltyBeans = new ArrayList<>();

    public LoyaltyAdapter(Context context, ArrayList<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> loyaltyBeans, DrawerListner drawerListner) {
        this.context = context;
        this.loyaltyBeans = loyaltyBeans;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public LoyaltyAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loyalty_points_item_view, parent, false);

        return new LoyaltyAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LoyaltyAdapter.ViewResource holder, int position) {


        if (position == 0) {
            holder.binding.tvEarnType.setText("Purchases");
            holder.binding.tvEarnType.setBackgroundResource(R.color.app_color);
            holder.binding.percentage.setDonut_progress("95");

        } else if (position == 1) {
            holder.binding.tvEarnType.setText("Check-ins");
            holder.binding.tvEarnType.setBackgroundResource(R.color.app_color_2);
            holder.binding.percentage.setDonut_progress("45");
        } else if (position == 2) {
            holder.binding.tvEarnType.setText("Feedback");
            holder.binding.tvEarnType.setBackgroundResource(R.color.light_green);
            holder.binding.percentage.setProgress(0.5f);
        } else if (position == 3) {
            holder.binding.tvEarnType.setText("social Medial");
            holder.binding.tvEarnType.setBackgroundResource(R.color.color_chocolate);
            holder.binding.percentage.setDonut_progress("65");
        } else if (position == 4) {
            holder.binding.tvEarnType.setText("Invite Friends");
            holder.binding.percentage.setDonut_progress("55");
            holder.binding.tvEarnType.setBackgroundResource(R.color.colorAccent);
        } else holder.binding.tvEarnType.setText(loyaltyBeans.get(position).getVenue_name());
        holder.binding.tvPointsCount.setText("" + loyaltyBeans.get(position).getLoyalty_point() + " PTS");

    }


    @Override
    public int getItemCount() {
        return loyaltyBeans.size() > 5 ? 5 : loyaltyBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LoyaltyPointsItemViewBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
