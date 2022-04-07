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
import com.poundland.retail.databinding.LayoutCellHomeLocalCategoryAdapterBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.LandingPageTopResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.LOCAL_SHOP_CATEGORY;

public class HomeCategoryHospitalityAdapter extends RecyclerView.Adapter<HomeCategoryHospitalityAdapter.ViewResource> {

    private List<LandingPageTopResponseModel.LocalshopBean> data;
    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;

    public HomeCategoryHospitalityAdapter(Context mContext, List<LandingPageTopResponseModel.LocalshopBean> data, DrawerListner drawerListner, boolean isShowMore) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public HomeCategoryHospitalityAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_home_local_category_adapter, parent, false);

        return new HomeCategoryHospitalityAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeCategoryHospitalityAdapter.ViewResource holder, int position) {

        holder.binding.textViewName.setSelected(true);
        if (data.get(position).getName().contains("Restaurants, Cafes & Bars")) {
            holder.binding.textViewName.setText("Health & Beauty");
        } else if (data.get(position).getName().contains("Alcohol")) {
            holder.binding.textViewName.setText("Home & Pet");
        } else
            holder.binding.textViewName.setText(data.get(position).getName());

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + data.get(position).getImage()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivArrow);


        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), LOCAL_SHOP_CATEGORY);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (data != null && data.size() > 6) {
            return 6;
        } else return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellHomeLocalCategoryAdapterBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
