package com.poundland.retail.zzznewPoundland.adapter;

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
import com.poundland.retail.databinding.LayoutCellFavouriteVenueViewBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.AllCategoryResponseModel;

import java.util.ArrayList;
import java.util.List;


/*for setting item for the recycler view for   */
public class FavoVenueAdapter extends RecyclerView.Adapter<FavoVenueAdapter.ViewResource> {

    private Context mContext;
    private List<AllCategoryResponseModel.CategoriesBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;

    public FavoVenueAdapter(Context mContext, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = new ArrayList<>();
        this.drawerListner = drawerListner;
    }

    public void clear() {
        this.data.clear();
        notifyDataSetChanged();
    }


    public void addData(List<AllCategoryResponseModel.CategoriesBean> fav_venue_content) {
        this.data.addAll(fav_venue_content);
        notifyItemChanged(this.data.size());
    }


    @NonNull
    @Override
    public FavoVenueAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_favourite_venue_view, parent, false);

        return new FavoVenueAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoVenueAdapter.ViewResource holder, int position) {

        holder.binding.tvNoOrders.setText(data.get(position).getId()+" Order");
        holder.binding.tvVenueName.setText(data.get(position).getCat_name());
        holder.binding.rbRating.setRating(data.get(position).getMenu_level());
        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_IMAGE_CATEGORY + data.get(position).getImage()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.venueImg);
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
        public LayoutCellFavouriteVenueViewBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
