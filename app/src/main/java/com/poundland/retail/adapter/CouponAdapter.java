package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.databinding.LayoutCellCouponBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.CouponResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.SHOP_NOW;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewResource> {

    private Context context;
    DrawerListner drawerListner;
    private List<CouponResponseModel.CouponDataBean> couponList;

    public CouponAdapter(Context context, List<CouponResponseModel.CouponDataBean> couponList, DrawerListner drawerListner) {
        this.context = context;
        this.couponList = couponList;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public CouponAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_coupon, parent, false);

        return new CouponAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CouponAdapter.ViewResource holder, int position) {

        holder.binding.tvCouponCode.setText(couponList.get(position).getCoupon_code());
        if (couponList.get(position).getOffer_type() == 1) {  // percent
            holder.binding.tvInfo.setText("Get " + couponList.get(position).getOffer_amount() + "% off on min order " +
                    context.getString(R.string.pound) + couponList.get(position).getMinimum_spend() + ". Max discount " +
                    context.getString(R.string.pound) + couponList.get(position).getMax_discount()+".");
        } else {  // pound
            holder.binding.tvInfo.setText("Get " +context.getString(R.string.pound) + couponList.get(position).getOffer_amount() + " off on min order " +
                    context.getString(R.string.pound) + couponList.get(position).getMinimum_spend() + ". Max discount " +
                    context.getString(R.string.pound) + couponList.get(position).getMax_discount()+".");
        }

        Glide.with(context).load(ApiRequestUrl.BASE_URL_VENUE + "couponList.get(position).getVenue_images()").apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivVenueImage);


        holder.binding.tvShopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), SHOP_NOW);
            }
        });
    }


    @Override
    public int getItemCount() {
        return couponList == null ? 0 : couponList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellCouponBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
