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
import com.poundland.retail.model.responseModel.HospitalityVenueDetailResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.CATEGORY_HOSPITALITY;


public class SingleStoreCategoryHospitalityListAdapter extends RecyclerView.Adapter<SingleStoreCategoryHospitalityListAdapter.ViewResource> {

    private Context mContext;
    private List<HospitalityVenueDetailResponseModel.CategoryBean> hospitalityData;
    private LayoutInflater mInflater;
    private int sameDayShipping;
    private DrawerListner drawerListner;
    private int scroolPosition;
    private int preSelected=-1;
    private LayoutCellSingleStoreCategoryListBinding lastSelectedBinding;

    public SingleStoreCategoryHospitalityListAdapter(Context mContext, List<HospitalityVenueDetailResponseModel.CategoryBean> categoryListHospitality, int sameDayShipping, DrawerListner drawerListner, int scroolPosition) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.hospitalityData = categoryListHospitality;
        this.sameDayShipping = sameDayShipping;
        this.scroolPosition = scroolPosition;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public SingleStoreCategoryHospitalityListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_single_store_category_list, parent, false);

        return new SingleStoreCategoryHospitalityListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SingleStoreCategoryHospitalityListAdapter.ViewResource holder, int position) {

        holder.binding.tvCatName.setSelected(true);
        holder.binding.tvCatName.setText(hospitalityData.get(position).getCat_name() + " (" + hospitalityData.get(position).getProduct() + ")");


        if (hospitalityData.get(position).getCat_name().equalsIgnoreCase("My Fav")) {
            Glide.with(mContext).load(R.drawable.ic_favo_prod).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(holder.binding.ivCatImage);
        }else {
            Glide.with(mContext).load(ApiRequestUrl.BASE_URL_IMAGE_CATEGORY + hospitalityData.get(position).getImage()).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(holder.binding.ivCatImage);
        }



    }


    @Override
    public int getItemCount() {
        return hospitalityData == null ? 0 : hospitalityData.size();
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
        public void onClick(View view) {
            drawerListner.onDrawerSelect(getAdapterPosition(), CATEGORY_HOSPITALITY);

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
