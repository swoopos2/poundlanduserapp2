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
import com.poundland.retail.databinding.LayoutCellAllCategoryBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.AllCategoryResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.SHOW_ALL_CATEGORY;


/*for setting item for the recycler view for   */
public class AllCategoryListAdapter extends RecyclerView.Adapter<AllCategoryListAdapter.ViewResource> {

    private Context mContext;
    private List<AllCategoryResponseModel.CategoriesBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;


    public AllCategoryListAdapter(Context mContext, List<AllCategoryResponseModel.CategoriesBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public AllCategoryListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_all_category, parent, false);

        return new AllCategoryListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllCategoryListAdapter.ViewResource holder, int position) {

        holder.binding.tvCatName.setText(data.get(position).getCat_name());
        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_IMAGE_CATEGORY + data.get(position).getImage()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivCatImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), SHOW_ALL_CATEGORY);
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
        public LayoutCellAllCategoryBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
