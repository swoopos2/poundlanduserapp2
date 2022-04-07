package com.poundland.retail.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
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
import com.poundland.retail.databinding.LayoutCellHomeTopOffersBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.responseModel.LikeDislikeProductResponseModel;
import com.poundland.retail.model.responseModel.SpecialOffersResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFERS;


/*for setting item for the recycler view for   */
public class SpecialOffersAdapter extends RecyclerView.Adapter<SpecialOffersAdapter.ViewResource> {

    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;
    private List<SpecialOffersResponseModel.ProductOffersBean.DataBean> specialOfferList;
    private PrefManager prefManager;

    public SpecialOffersAdapter(Context mContext, List<SpecialOffersResponseModel.ProductOffersBean.DataBean> specialOfferList, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.specialOfferList = specialOfferList;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public SpecialOffersAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_home_top_offers, parent, false);

        return new SpecialOffersAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SpecialOffersAdapter.ViewResource holder, int position) {
        holder.binding.tvOldPrice.setPaintFlags(holder.binding.tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.binding.ivFavo.setVisibility(View.GONE);
        if (specialOfferList.get(position).isIsWishlisted())
            holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);

        if (specialOfferList.get(position).getSameDayShipping()==1){
            holder.binding.ivSameDayDelivery.setVisibility(View.VISIBLE);
        }else holder.binding.ivSameDayDelivery.setVisibility(View.GONE);


        if (specialOfferList.get(position).getAvl_quantity() < 1) {
            holder.binding.ivItemOutOfStock.setVisibility(View.VISIBLE);
        }

        holder.binding.tvItemName.setText(specialOfferList.get(position).getProduct_name());
        holder.binding.tvOldPrice.setText(mContext.getString(R.string.pound) + specialOfferList.get(position).getSelling_price());
        holder.binding.tvNewPrice.setText(mContext.getString(R.string.pound) + specialOfferList.get(position).getNew_price());   ////  getActualPrice();
        // holder.binding.tvItemDesc.setText(specialOfferList.get(position).getProduct_description());
        if (specialOfferList.get(position).getProduct_description()!=null)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.binding.tvItemDesc.setText(Html.fromHtml(specialOfferList.get(position).getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.binding.tvItemDesc.setText(Html.fromHtml(specialOfferList.get(position).getProduct_description()));
        }
        holder.binding.tvStoreName.setText(specialOfferList.get(position).getVenue_name());
        if (specialOfferList.get(position).getOffer_title() != null)
            holder.binding.tvOffers.setText(specialOfferList.get(position).getOffer_title());
        holder.binding.tvDistance.setText(specialOfferList.get(position).getDistance() + mContext.getString(R.string.miles));
        if (specialOfferList.get(position).getStars()!=null)
        holder.binding.rbRating.setRating(Float.parseFloat(specialOfferList.get(position).getStars()));


        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + specialOfferList.get(position).getProduct_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivCatImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), SPECIAL_OFFERS);
            }
        });

        holder.binding.ivFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                likeDislike(holder, holder.getAdapterPosition(), specialOfferList.get(holder.getAdapterPosition()).getMerchant_id(),
                        specialOfferList.get(holder.getAdapterPosition()).getVenue_id(),
                        specialOfferList.get(holder.getAdapterPosition()).getProduct_id(),
                        specialOfferList.get(holder.getAdapterPosition()).getModifier_id(),
                        specialOfferList.get(holder.getAdapterPosition()).getOffer_id(),
                        specialOfferList.get(holder.getAdapterPosition()).getNew_price(),
                        "");

            }
        });

    }


    @Override
    public int getItemCount() {
        return specialOfferList == null ? 0 : specialOfferList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellHomeTopOffersBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private void likeDislike(final ViewResource holder, final int pos, int merchant_id, String venue_id, int product_id, int modifier_id, int offer_id, String new_price, String couponCode) {
        if (isInternetOn(mContext)) {
            LikeDislikeProductRequestModel requestModel = new LikeDislikeProductRequestModel(String.valueOf(merchant_id), venue_id,
                    String.valueOf(prefManager.getPreference(LOGIN_ID, "")), String.valueOf(product_id),
                    String.valueOf(modifier_id),
                    String.valueOf(offer_id), new_price, couponCode);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LikeDislikeProductResponseModel> call = apiInterface.likeDislikeProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<LikeDislikeProductResponseModel>() {
                @Override
                public void onResponse(Call<LikeDislikeProductResponseModel> call, Response<LikeDislikeProductResponseModel> response) {
                    try {
                       /* if (dialog != null)
                            dialog.dismiss();*/

                        if (response.isSuccessful()) {
                            LikeDislikeProductResponseModel venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {
                                if (specialOfferList.get(pos).isIsWishlisted()) {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                                    specialOfferList.get(pos).setIsWishlisted(false);
                                } else {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
                                    specialOfferList.get(pos).setIsWishlisted(true);
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
                            final int httpCode =response.code();

                            DialogUtils.showAlertDialogWithSingleButton(mContext, httpCode==401? mContext.getString(R.string.season_expired): mContext.getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode==401){
                                        logOut((Activity)mContext);
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
                    /*if (dialog != null)
                        dialog.dismiss();*/
                    showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.no_internet_available_msg));
        }
    }

}
