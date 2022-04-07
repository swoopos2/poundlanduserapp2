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
import com.poundland.retail.model.responseModel.ProductByCategoryResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.HOME_CATEGORY;
import static com.poundland.retail.interfaces.Constants.SHOW_ALL_CATEGORY;

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.ViewResource> {

    private List<ProductByCategoryResponseModel.CategoryBean> data;
    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;
    private boolean isShowMore;
    private String search;

    public CategoryDetailsAdapter(Context mContext, List<ProductByCategoryResponseModel.CategoryBean> data, DrawerListner drawerListner, boolean isShowMore, String search) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.search = search;
        this.isShowMore = isShowMore;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public CategoryDetailsAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_home_category_adapter, parent, false);

        return new CategoryDetailsAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryDetailsAdapter.ViewResource holder, int position) {

        if (search.equals(data.get(position).getCat_name()))
            holder.binding.rlMain.setBackground(ContextCompat.getDrawable(mContext, R.drawable.app_colored_filled_rounded_rect));


        holder.binding.textViewName.setText(data.get(position).getCat_name());

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
