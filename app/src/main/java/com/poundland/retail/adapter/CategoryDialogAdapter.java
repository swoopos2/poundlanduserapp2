package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellHomeCategoryAdapterBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.ProductBySearchResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.HOME_CATEGORY;
import static com.poundland.retail.interfaces.Constants.SHOW_ALL_CATEGORY;

public class CategoryDialogAdapter extends RecyclerView.Adapter<CategoryDialogAdapter.ViewResource> {

    private List<ProductBySearchResponseModel.CategoryBean> data;
    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;


    public CategoryDialogAdapter(Context mContext, List<ProductBySearchResponseModel.CategoryBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public CategoryDialogAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_home_category_adapter, parent, false);

        return new CategoryDialogAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryDialogAdapter.ViewResource holder, int position) {

        if (position == data.size() - 1) {
            holder.binding.textViewName.setText(mContext.getString(R.string.see_more));
            holder.binding.textViewName.setTextColor(ContextCompat.getColor(mContext, R.color.color_white));
            holder.binding.ivArrow.setVisibility(View.VISIBLE);
            holder.binding.rlMain.setBackground(ContextCompat.getDrawable(mContext, R.drawable.app_colored_filled_rounded_rect));
        } else holder.binding.textViewName.setText(data.get(position).getCat_name());

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
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellHomeCategoryAdapterBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}