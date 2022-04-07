package com.poundland.retail.activityHospitality.adapter;

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
import com.poundland.retail.databinding.LayoutCellAutoCompleteVenueAdapterBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.HeaderSearchResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.AUTO_COMPLETE_VENUE;

public class AutoCompleteVenueAdapter extends RecyclerView.Adapter<AutoCompleteVenueAdapter.ViewResource> {

    private List<HeaderSearchResponseModel.VenuesSearchDataBean> venuesSearchData;
    Context mContext;
    private LayoutInflater mInflater;

    DrawerListner drawerListner;

    public AutoCompleteVenueAdapter(Context context,  List<HeaderSearchResponseModel.VenuesSearchDataBean> venuesSearchData, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.venuesSearchData = venuesSearchData;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public AutoCompleteVenueAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_auto_complete_venue_adapter, parent, false);

        return new AutoCompleteVenueAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AutoCompleteVenueAdapter.ViewResource holder, int position) {

        holder.binding.textViewName.setText(venuesSearchData.get(position).getVenue_name());

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + venuesSearchData.get(position).getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivVenue);

        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), AUTO_COMPLETE_VENUE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return venuesSearchData == null ? 0 : venuesSearchData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellAutoCompleteVenueAdapterBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
