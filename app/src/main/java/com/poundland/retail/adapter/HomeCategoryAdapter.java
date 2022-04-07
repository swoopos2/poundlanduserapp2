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
import com.poundland.retail.databinding.LayoutCellHomeTopCategoryAllAdapterBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.LandingPageBottomResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.HOME_CATEGORY;
import static com.poundland.retail.interfaces.Constants.SHOW_ALL_CATEGORY;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewResource> {

    private List<LandingPageBottomResponseModel.CategoriesBean> data;
    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;

    public HomeCategoryAdapter(Context mContext, List<LandingPageBottomResponseModel.CategoriesBean> data, DrawerListner drawerListner, boolean isShowMore) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public HomeCategoryAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_home_top_category_all_adapter, parent, false);

        return new HomeCategoryAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeCategoryAdapter.ViewResource holder, int position) {

        /*if (position == data.size() - 1) {
            holder.binding.textViewName.setText(mContext.getString(R.string.see_more));
            holder.binding.textViewName.setTextColor(ContextCompat.getColor(mContext, R.color.color_white));
            holder.binding.ivArrow.setVisibility(View.VISIBLE);
            holder.binding.rlMain.setBackground(ContextCompat.getDrawable(mContext, R.drawable.app_colored_filled_rounded_rect));
        } else {*/

        holder.binding.textViewName.setSelected(true);
            holder.binding.textViewName.setText(data.get(position).getCat_name());
       // }
        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_IMAGE_CATEGORY + data.get(position).getImage()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivArrow);
        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getAdapterPosition() == data.size() - 1) {
                    drawerListner.onDrawerSelect(holder.getAdapterPosition(), SHOW_ALL_CATEGORY);
                } else drawerListner.onDrawerSelect(holder.getAdapterPosition(), HOME_CATEGORY);

            }
        });
    }


    @Override
    public int getItemCount() {
        if (data!=null && data.size()>8){
           return 8;
        }else   return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellHomeTopCategoryAllAdapterBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
