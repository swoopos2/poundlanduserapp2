package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.databinding.LayoutCellSingleStoreCategoryListBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.VenueDetailResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.CATEGORY_RETAIL;


/*for setting item for the recycler view for   */
public class SingleStoreCategoryListAdapter extends RecyclerView.Adapter<SingleStoreCategoryListAdapter.ViewResource> {

    private Context mContext;
    private List<VenueDetailResponseModel.VenueDetailsBean.CategoriesBean> data;
    private LayoutInflater mInflater;
    private int sameDayShipping;
    private String venueType;
    private DrawerListner drawerListner;
    private int scroolPosition;
    private int preSelected = -1;
    private LayoutCellSingleStoreCategoryListBinding lastSelectedBinding;


    public SingleStoreCategoryListAdapter(Context mContext, List<VenueDetailResponseModel.VenueDetailsBean.CategoriesBean> data, int sameDayShipping, DrawerListner drawerListner, int scroolPosition) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.sameDayShipping = sameDayShipping;
        this.drawerListner = drawerListner;
        this.scroolPosition = scroolPosition;
    }

    @NonNull
    @Override
    public SingleStoreCategoryListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_single_store_category_list, parent, false);

        return new SingleStoreCategoryListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SingleStoreCategoryListAdapter.ViewResource holder, int position) {

       /* if (sameDayShipping==1){
            holder.binding.ivSameDayDelivery.setVisibility(View.VISIBLE);
        }else  holder.binding.ivSameDayDelivery.setVisibility(View.GONE);*/
        // holder.binding.tvItemQty.setText(String.valueOf(data.get(position).getTotal_products() + " " + mContext.getString(R.string.more_products)));
        holder.binding.tvCatName.setSelected(true);
        holder.binding.tvCatName.setText(data.get(position).getCat_name() + " (" + data.get(position).getTotal_products() + ")");
        if (data.get(position).getCat_name().equalsIgnoreCase("My Fav")) {
            int paddingDp = 15;
            float density = mContext.getResources().getDisplayMetrics().density;
            int paddingPixel = (int) (paddingDp * density);
            holder.binding.ivCatImage.setPadding(paddingPixel, paddingPixel, paddingPixel, paddingPixel);

            Glide.with(mContext).load(R.drawable.ic_favo_prod).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(holder.binding.ivCatImage);
        } else {
            Glide.with(mContext).load(ApiRequestUrl.BASE_URL_IMAGE_CATEGORY + data.get(position).getImage()).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(holder.binding.ivCatImage);
        }
    }


    @Override
    public int getItemCount() {

        return data == null ? 0 : data.size();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LayoutCellSingleStoreCategoryListBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.rlMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            drawerListner.onDrawerSelect(getAdapterPosition(), CATEGORY_RETAIL);
            //////////////////////////////////////////////

            if (preSelected == -1) {
                binding.llImg.setBackgroundResource(R.drawable.white_filled_blue_border_rounded_rect);
                binding.tvCatName.setTextColor(ContextCompat.getColor(mContext, R.color.app_header_color));
                preSelected = getLayoutPosition();
            } else {
                if (preSelected == getLayoutPosition()) {
                    lastSelectedBinding.llImg.setBackgroundResource(R.drawable.white_filled_rounded_rect);
                    lastSelectedBinding.tvCatName.setTextColor(ContextCompat.getColor(mContext, R.color.color_black));
                    preSelected = getLayoutPosition();
                } else {
                    lastSelectedBinding.llImg.setBackgroundResource(R.drawable.white_filled_rounded_rect);
                    lastSelectedBinding.tvCatName.setTextColor(ContextCompat.getColor(mContext, R.color.color_black));

                    binding.llImg.setBackgroundResource(R.drawable.white_filled_blue_border_rounded_rect);
                    binding.tvCatName.setTextColor(ContextCompat.getColor(mContext, R.color.app_header_color));


                    preSelected = getLayoutPosition();
                }
            }

            lastSelectedBinding = binding;
        }
    }

}
