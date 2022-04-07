package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellDealsOnVenueBinding;
import com.poundland.retail.interfaces.DrawerListner;

import java.util.List;


public class DealsOnVenueAdapter extends RecyclerView.Adapter<DealsOnVenueAdapter.ViewResource> {

    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;
    private List<String> offerList;
    private PrefManager prefManager;


    public DealsOnVenueAdapter(Context mContext, /*List<VenueDetailResponseModel.BestOffersBean> bestOffersList,*/ DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
      //  this.offerList = bestOffersList;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public DealsOnVenueAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_deals_on_venue, parent, false);

        return new DealsOnVenueAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DealsOnVenueAdapter.ViewResource holder, int position) {
       /* holder.binding.tvOldPrice.setPaintFlags(holder.binding.tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        *//*if (offerList.get(position).isWishlisted())
            holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
*//*
        if (offerList.get(position).getSameDayShipping()==1){
            holder.binding.ivSameDayDelivery.setVisibility(View.VISIBLE);
        }else holder.binding.ivSameDayDelivery.setVisibility(View.GONE);;

        if (offerList.get(position).getAvl_quantity() < 1) {
            holder.binding.ivItemOutOfStock.setVisibility(View.VISIBLE);
        }

        holder.binding.tvItemName.setText(offerList.get(position).getProduct_name());
        holder.binding.tvOldPrice.setText(mContext.getString(R.string.pound) + offerList.get(position).getSell_price());
        holder.binding.tvNewPrice.setText(mContext.getString(R.string.pound) + offerList.get(position).getNew_price());   ////  getActualPrice();
        if (offerList.get(position).getProduct_description() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.tvItemDesc.setText(Html.fromHtml(offerList.get(position).getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.binding.tvItemDesc.setText(Html.fromHtml(offerList.get(position).getProduct_description()));
            }
        }
        holder.binding.tvStoreName.setText(offerList.get(position).getVenue_name());
        if (offerList.get(position).getOffer_title() != null)
            holder.binding.tvOffers.setText(offerList.get(position).getOffer_title());
        holder.binding.tvDistance.setText(offerList.get(position).getDistance() + mContext.getString(R.string.miles));
        if (offerList.get(position).getStars() != null)
            holder.binding.rbRating.setRating(Float.parseFloat(offerList.get(position).getStars().getRating_value()));


        Glide.with(mContext).load(offerList.get(position).getModifier_images() != null ? ApiRequestUrl.BASE_URL + offerList.get(position).getModifier_images() : ApiRequestUrl.BASE_URL + offerList.get(position).getImages())
                .apply(new RequestOptions()
                .placeholder(R.drawable.default_image))
                .into(holder.binding.ivCatImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), TOP_OFFER);
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return offerList == null ? 0 : offerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellDealsOnVenueBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
