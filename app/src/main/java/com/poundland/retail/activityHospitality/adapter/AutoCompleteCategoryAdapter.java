package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellAutoCompleteCategoryAdapterBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.HeaderSearchResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.AUTO_COMPLETE_CATEGORY;

public class AutoCompleteCategoryAdapter extends RecyclerView.Adapter<AutoCompleteCategoryAdapter.ViewResource> {

    private List<HeaderSearchResponseModel.CategoriesNameBean> categoriesName;
    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;

    public AutoCompleteCategoryAdapter(Context context, List<HeaderSearchResponseModel.CategoriesNameBean> categoriesName, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.categoriesName = categoriesName;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public AutoCompleteCategoryAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_auto_complete_category_adapter, parent, false);

        return new AutoCompleteCategoryAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AutoCompleteCategoryAdapter.ViewResource holder, int position) {

        holder.binding.textViewName.setText(categoriesName.get(position).getCat_name());

        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), AUTO_COMPLETE_CATEGORY);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesName == null ? 0 : categoriesName.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellAutoCompleteCategoryAdapterBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
