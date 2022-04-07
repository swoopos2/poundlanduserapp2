package com.poundland.retail.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellAllProductBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.NotifyUpcomingVenueProductReq;
import com.poundland.retail.model.responseModel.NotifyUpcomingVenueProductResp;
import com.poundland.retail.model.responseModel.UpcomingVenueDetailResponseModel;

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
import static com.poundland.retail.interfaces.Constants.NOTIFY_UPCOMING_VENUE;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER;


public class UpcomingVenueProductAdapter extends RecyclerView.Adapter<UpcomingVenueProductAdapter.ViewResource> {
    private Context mContext;
    private LayoutInflater mInflater;
    private DrawerListner drawerListner;
    private List<UpcomingVenueDetailResponseModel.ProductListBean.DataBean> offerList;
    private PrefManager prefManager;
    private String venueName;
    private String lat, longi;
    private String city_name = "";

    public UpcomingVenueProductAdapter(Context mContext, List<UpcomingVenueDetailResponseModel.ProductListBean.DataBean> offerList, DrawerListner drawerListner, String venueName, String lat, String longi, String city_name) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.offerList = offerList;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(mContext);
        this.venueName = venueName;
        this.lat = lat;
        this.longi = longi;
        if (city_name != null) {
            this.city_name = city_name;
        }

    }

    @NonNull
    @Override
    public UpcomingVenueProductAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_all_product, parent, false);
        return new UpcomingVenueProductAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UpcomingVenueProductAdapter.ViewResource holder, int position) {


        holder.binding.tvItemName.setText(offerList.get(position).getName());
        holder.binding.tvNewPrice.setText(mContext.getString(R.string.pound) + offerList.get(position).getPrice());   ////  getActualPrice();
        /*if (offerList.get(position).getProduct_description() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.tvDescription.setText(Html.fromHtml(offerList.get(position).getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.binding.tvDescription.setText(Html.fromHtml(offerList.get(position).getProduct_description()));
            }
        }*/
        holder.binding.tvDescription.setVisibility(View.INVISIBLE);
        holder.binding.tvDistance.setVisibility(View.INVISIBLE);

        holder.binding.tvAddToCart.setText(mContext.getString(R.string.notify_me));

        holder.binding.tvStoreName.setText(venueName);
        holder.binding.tvDistance.setText(offerList.get(position).getDistance() != null ? offerList.get(position).getDistance() + mContext.getString(R.string.miles) : "" + mContext.getString(R.string.miles));
        holder.binding.rbRating.setRating(offerList.get(position).getStars());

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + offerList.get(position).getImage()).apply(new RequestOptions().placeholder(R.drawable.ic_app_icon)).into(holder.binding.ivItemImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), TOP_OFFER);
            }
        });


        holder.binding.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.notify_me))) {
                   /* notifyMe(holder, offerList.get(holder.getAdapterPosition()).getId(),
                            String.valueOf(offerList.get(holder.getAdapterPosition()).getVenue_id()));*/
                    drawerListner.onDrawerSelect(holder.getAdapterPosition(), NOTIFY_UPCOMING_VENUE);
                }
            }
        });
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
        public LayoutCellAllProductBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private void notifyMe(final ViewResource holder, int product_id, String venue_id) {
        if (isInternetOn(mContext)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
            if (dialog != null)
                dialog.show();

            final NotifyUpcomingVenueProductReq requestModel = new NotifyUpcomingVenueProductReq(
                    prefManager.getPreference(EMAIL, ""), String.valueOf(product_id), venue_id,
                    Long.parseLong(String.valueOf(prefManager.getPreference(LOGIN_ID, 0))), lat, longi, city_name, "::1");

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<NotifyUpcomingVenueProductResp> call = apiInterface.notifyUpcomingVenueProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<NotifyUpcomingVenueProductResp>() {
                @Override
                public void onResponse(Call<NotifyUpcomingVenueProductResp> call, Response<NotifyUpcomingVenueProductResp> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            NotifyUpcomingVenueProductResp responseModel = response.body();

                            if (responseModel.isStatus()) {
                                // showSnackBar(holder.binding.getRoot(), responseModel.getMessage());
                                // successActionListner.onSuccessActionListner(responseModel.getMessage());
                            } else
                                showSnackBar(holder.binding.getRoot(), responseModel.getMessage());

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
                        HelperClass.log("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<NotifyUpcomingVenueProductResp> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.no_internet_available_msg));
        }
    }

   /* private void likeDislike(final ViewResource holder, final int pos, int merchant_id, String venue_id, int product_id, int modifier_id, String offer_id, String new_price, String couponCode) {
        if (isInternetOn(mContext)) {
            LikeDislikeProductRequestModel requestModel = new LikeDislikeProductRequestModel(String.valueOf(merchant_id), venue_id,
                    String.valueOf(prefManager.getPreference(LOGIN_ID, "")), String.valueOf(product_id),
                    String.valueOf(modifier_id),
                    String.valueOf(offer_id), new_price, couponCode);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LikeDislikeProductResponseModel> call = apiInterface.likeDislikeProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<LikeDislikeProductResponseModel>() {
                @Override
                public void onResponse(Call<LikeDislikeProductResponseModel> call, Response<LikeDislikeProductResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            LikeDislikeProductResponseModel venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {
                                if (offerList.get(pos).isWishlisted()) {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                                    offerList.get(pos).setWishlisted(false);
                                } else {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
                                    offerList.get(pos).setWishlisted(true);
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

    private void addToCart(boolean openCart, boolean isCombo, ViewResource holder) {
        if (isInternetOn(mContext)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
            if (dialog != null)
                dialog.show();
            AddToCartRequestModel requestModel = null;

            requestModel = new AddToCartRequestModel
                    (String.valueOf(offerList.get(holder.getAdapterPosition()).getMerchant_id()), offerList.get(holder.getAdapterPosition()).getVenue_id(),
                            String.valueOf(offerList.get(holder.getAdapterPosition()).getId()), String.valueOf(offerList.get(holder.getAdapterPosition()).getModifier_id()),
                            offerList.isEmpty() ? "0" : String.valueOf(offerList.get(holder.getAdapterPosition()).getOffer_id()), "1",
                            offerList.get(holder.getAdapterPosition()).getSelling_price(), "", "", "0");

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addToCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {
                                if (openCart) {
                                    Intent goToCart = new Intent(mContext, MyCartActivity.class);
                                    mContext.startActivity(goToCart);
                                } else {
                                    if (!isCombo) {
                                        holder.binding.tvAddToCart.setText(mContext.getString(R.string.go_to_bag));
                                        // holder.binding.tvAddToCart.setBackgroundResource(R.drawable.saffron_filled_rect);
                                        holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
                                    }
                                }
                                successActionListner.onSuccessActionListner();
                                // getTotalCartCount();

                            } else {
                                showSnackBar(holder.binding.getRoot(), cartResponseModel.getMessage());
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
                public void onFailure(Call<AddToCartResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.no_internet_available_msg));

        }
    }*/
}
