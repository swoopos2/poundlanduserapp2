package com.poundland.retail.adapter;

import android.app.Activity;
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
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellAllVenueBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.responseModel.AllVenueResponseModel;
import com.poundland.retail.model.responseModel.LikeDislikeProductResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.TOP_VENUE;


/* for setting item for the recycler view for */
public class AllVenueAdapter extends RecyclerView.Adapter<AllVenueAdapter.ViewResource> {

    Context mContext;
    DrawerListner drawerListner;
    private List<AllVenueResponseModel.VenuesBean.DataBean> data;
    private LayoutInflater mInflater;
    private PrefManager prefManager;

    public AllVenueAdapter(Context mContext, List<AllVenueResponseModel.VenuesBean.DataBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public AllVenueAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_all_venue, parent, false);

        return new AllVenueAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllVenueAdapter.ViewResource holder, int position) {

        holder.binding.tvVenueName.setText(data.get(position).getVenue_name());
        if (data.get(position).isWishlisted())
            holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);

        if (data.get(position).getTotal_offers()<1){
            holder.binding.tvOffers.setText( mContext.getString(R.string.no_offers));
        }else if (data.get(position).getTotal_offers()==1){
            holder.binding.tvOffers.setText(data.get(position).getTotal_offers() + " " + mContext.getString(R.string.offer));
        }else holder.binding.tvOffers.setText(data.get(position).getTotal_offers() + " " + mContext.getString(R.string.offers));

        holder.binding.tvDistance.setText(data.get(position).getDistance() !=null  ? data.get(position).getDistance() + mContext.getString(R.string.miles) : "" + mContext.getString(R.string.miles));
        if (data.get(position).getHome_delivery() == 1) {
            holder.binding.tvHomeDelivery.setVisibility(View.VISIBLE);
        }
        if (data.get(position).getClick_collect() == 1) {
            holder.binding.tvClickCollect.setVisibility(View.VISIBLE);
        }
        holder.binding.rbRating.setRating(Float.parseFloat(data.get(position).getStars()));
        if (data.get(position).getHome_delivery_mov() != null)
            holder.binding.tvMinOrder.setText(mContext.getString(R.string.min_order) + data.get(position).getHome_delivery_mov());
        try {
            holder.binding.tvTiming.setText(HelperClass.getTime(data.get(position).getOpening_time() )+ "-" + HelperClass.getTime(data.get(position).getClosing_time()));
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        /*if (data.get(position).getIsClose().equals("0")){
            holder.binding.tvStatus.setText(mContext.getString(R.string.open));
            holder.binding.ivStatus.setColorFilter(ContextCompat.getColor(mContext, R.color.drawer_tint_color), android.graphics.PorterDuff.Mode.SRC_IN);
        }else {
            holder.binding.tvStatus.setText(mContext.getString(R.string.close));
            holder.binding.ivStatus.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
        }*/
        if (data.get(position).getIsClose().equals("0")) {
            holder.binding.tvStatus.setText(mContext.getString(R.string.open_caps));
            holder.binding.tvStatus.setBackgroundResource(R.drawable.green_filled_rect);
            // holder.binding.ivStatus.setColorFilter(ContextCompat.getColor(mContext, R.color.drawer_tint_color), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.binding.tvStatus.setText(mContext.getString(R.string.close_caps));
            holder.binding.tvStatus.setBackgroundResource(R.drawable.app_colored_filled_rect_100);
            //  holder.binding.ivStatus.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
        }


        if (data.get(position).getVenue_images().size() > 0) {
            Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + data.get(position).getVenue_images().get(0)).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(holder.binding.ivVenueImage);
        }

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), TOP_VENUE);
            }
        });

        holder.binding.ivFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeDislike(holder, holder.getAdapterPosition(), data.get(holder.getAdapterPosition()).getVenue_id() );
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
        public LayoutCellAllVenueBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private void likeDislike(AllVenueAdapter.ViewResource holder, int adapterPosition, String venue_id) {
        if (isInternetOn(mContext)) {
            LikeDislikeProductRequestModel requestModel = new LikeDislikeProductRequestModel(venue_id);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LikeDislikeProductResponseModel> call = apiInterface.likeDislikeVenue(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<LikeDislikeProductResponseModel>() {
                @Override
                public void onResponse(Call<LikeDislikeProductResponseModel> call, Response<LikeDislikeProductResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            LikeDislikeProductResponseModel venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {
                                if (data.get(adapterPosition).isWishlisted()) {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                                    data.get(adapterPosition).setWishlisted(false);
                                } else {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
                                    data.get(adapterPosition).setWishlisted(true);
                                }

                                DialogUtils.showAlertDialogWithSingleButton(mContext, venueDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });


                            } else {
                                showSnackBar(holder.binding.getRoot(), venueDetailResponseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(mContext, httpCode==401? mContext.getString(R.string.season_expired): mContext.getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut((Activity) mContext);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LikeDislikeProductResponseModel> call, Throwable t) {
                    showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.no_internet_available_msg));
        }
    }
}
