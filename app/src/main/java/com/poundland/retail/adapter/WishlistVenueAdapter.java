package com.poundland.retail.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
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
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellHomeTopVenueBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.responseModel.LikeDislikeProductResponseModel;
import com.poundland.retail.model.responseModel.WishlistedProductResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.getTime;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.TOP_VENUE;

public class WishlistVenueAdapter extends RecyclerView.Adapter<WishlistVenueAdapter.ViewResource> {
    private Context mContext;
    private List<WishlistedProductResponseModel.VenuesBean.DataBean> data;
    private LayoutInflater mInflater;
    private DrawerListner drawerListner;
    private PrefManager prefManager;

    public WishlistVenueAdapter(Context mContext, List<WishlistedProductResponseModel.VenuesBean.DataBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public WishlistVenueAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_home_top_venue, parent, false);
        return new WishlistVenueAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WishlistVenueAdapter.ViewResource holder, int position) {

        holder.binding.tvVenueName.setText(data.get(position).getVenue().getVenue_name());
        if (data.get(position).getVenue().isIsWishlisted())
            holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);

        if (data.get(position).getVenue().getTotal_offers() < 1) {
            holder.binding.tvOffers.setText(mContext.getString(R.string.no_offers));
        } else if (data.get(position).getVenue().getTotal_offers() == 1) {
            holder.binding.tvOffers.setText(data.get(position).getVenue().getTotal_offers() + " " + mContext.getString(R.string.offer));
        } else
            holder.binding.tvOffers.setText(data.get(position).getVenue().getTotal_offers() + " " + mContext.getString(R.string.offers));

        holder.binding.tvDistance.setText(data.get(position).getVenue().getDistance() != null ? data.get(position).getVenue().getDistance() + mContext.getString(R.string.miles) : "" + mContext.getString(R.string.miles));
        if (data.get(position).getVenue().getDelivery() == 1) {
            holder.binding.tvHomeDelivery.setVisibility(View.VISIBLE);
        }
        if (data.get(position).getVenue().getCollection() == 1) {
            holder.binding.tvClickCollect.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(data.get(position).getVenue().getStars()) && Float.parseFloat(data.get(position).getVenue().getStars()) > 0) {
            holder.binding.rbRating.setRating(Float.parseFloat(data.get(position).getVenue().getStars()));
        } else holder.binding.rbRating.setRating(5f);

        if (data.get(position).getVenue().getHome_delivery_mov() != null)
            holder.binding.tvMinOrder.setText(mContext.getString(R.string.min_order) + data.get(position).getVenue().getHome_delivery_mov());
        try {
            holder.binding.tvTiming.setText(getTime(data.get(position).getVenue().getOpening_time()) + " | " + getTime(data.get(position).getVenue().getClosing_time()));
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

       /* if (data.get(position).getVenue().getIsClose().equals("0")){
            holder.binding.tvStatus.setText(mContext.getString(R.string.open));
            holder.binding.ivStatus.setColorFilter(ContextCompat.getColor(mContext, R.color.drawer_tint_color), android.graphics.PorterDuff.Mode.SRC_IN);
        }else {
            holder.binding.tvStatus.setText(mContext.getString(R.string.close));
            holder.binding.ivStatus.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
        }*/
        if (data.get(position).getVenue().getIsClose().equals("0")) {
            holder.binding.tvStatus.setText(mContext.getString(R.string.open_caps));
            holder.binding.tvStatus.setBackgroundResource(R.drawable.green_filled_rect);
            // holder.binding.ivStatus.setColorFilter(ContextCompat.getColor(mContext, R.color.drawer_tint_color), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.binding.tvStatus.setText(mContext.getString(R.string.close_caps));
            holder.binding.tvStatus.setBackgroundResource(R.drawable.app_colored_filled_rect_100);
            //  holder.binding.ivStatus.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + data.get(position).getVenue().getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivVenueImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), TOP_VENUE);
            }
        });

        holder.binding.ivFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeDislike(holder, holder.getAdapterPosition(), data.get(holder.getAdapterPosition()).getVenue_id());
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
        public LayoutCellHomeTopVenueBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private void likeDislike(WishlistVenueAdapter.ViewResource holder, int adapterPosition, String venue_id) {
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
                                /*if (data.get(adapterPosition).getVenue().isIsWishlisted()) {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                                    data.get(adapterPosition).getVenue().setIsWishlisted(false);
                                } else {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
                                    data.get(adapterPosition).getVenue().setIsWishlisted(true);
                                }*/

                                DialogUtils.showAlertDialogWithSingleButton(mContext, venueDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                                data.remove(adapterPosition);
                                notifyItemRemoved(adapterPosition);

                            } else {
                                showSnackBar(holder.binding.getRoot(), venueDetailResponseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(mContext, httpCode == 401 ? mContext.getString(R.string.season_expired) : mContext.getString(R.string.something_went_wrong), new OnDialogClickListener() {
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
