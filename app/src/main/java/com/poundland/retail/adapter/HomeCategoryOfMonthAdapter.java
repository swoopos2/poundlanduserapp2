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
import com.poundland.retail.databinding.LayoutCellHomeTopCategoryAdapterBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.LandingPageBottomResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.TOP_CAT_OF_MONTH;

public class HomeCategoryOfMonthAdapter extends RecyclerView.Adapter<HomeCategoryOfMonthAdapter.ViewResource> {

    private List<LandingPageBottomResponseModel.TopcategoriesMonthBean> data;
    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;

    public HomeCategoryOfMonthAdapter(Context mContext, List<LandingPageBottomResponseModel.TopcategoriesMonthBean> data, DrawerListner drawerListner, boolean isShowMore) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public HomeCategoryOfMonthAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_home_top_category_adapter, parent, false);

        return new HomeCategoryOfMonthAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeCategoryOfMonthAdapter.ViewResource holder, int position) {


        holder.binding.textViewName.setSelected(true);
            holder.binding.textViewName.setText(data.get(position).getCat_name());
        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_IMAGE_CATEGORY + data.get(position).getImage()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivArrow);
        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), TOP_CAT_OF_MONTH);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellHomeTopCategoryAdapterBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
