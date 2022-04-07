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
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellWishListExclusiveBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.responseModel.GetMatchWishlistProductResponseModel;
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
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.UNIVERSAL_CODE;

public class WishListExclusiveAdapter extends RecyclerView.Adapter<WishListExclusiveAdapter.ViewResource> {

    private Context mContext;
    private List<GetMatchWishlistProductResponseModel.MatchProductsBean.DataBean> data;
    private LayoutInflater mInflater;
    private DrawerListner drawerListner;
    private PrefManager prefManager;


    public WishListExclusiveAdapter(Context mContext, List<GetMatchWishlistProductResponseModel.MatchProductsBean.DataBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
        prefManager= PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public WishListExclusiveAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_wish_list_exclusive, parent, false);

        return new WishListExclusiveAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WishListExclusiveAdapter.ViewResource holder, int position) {


        holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);

        holder.binding.tvItemName.setText(data.get(position).getProduct_name());
        holder.binding.tvStoreName.setText(data.get(position).getVenue_name());
        holder.binding.tvAddress.setText(data.get(position).getAddress_1());
        holder.binding.tvPrice.setText((mContext.getString(R.string.pound)+data.get(position).getSelling_price()));
        holder.binding.tvDistance.setText(/*String.valueOf(*/data.get(position).getDistance()+"\n"+mContext.getString(R.string.miles));

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + data.get(position).getImages()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivCatImage);
        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(),UNIVERSAL_CODE);
            }
        });

        /*holder.binding.ivFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeDislike(holder, holder.getAdapterPosition(), "",
                        data.get(holder.getAdapterPosition()).getVenue_id(),
                        data.get(holder.getAdapterPosition()).getId(),
                        data.get(holder.getAdapterPosition()).getModifier_id(),
                       "",
                        data.get(holder.getAdapterPosition()).getSelling_price(),
                        "");

            }
        });*/
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
        public LayoutCellWishListExclusiveBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private void likeDislike( ViewResource holder,final int pos, String merchant_id, String venue_id, int product_id, int modifier_id,
                             String offer_id, String new_price, String couponCode) {
        if (isInternetOn(mContext)) {
            LikeDislikeProductRequestModel requestModel = new LikeDislikeProductRequestModel(merchant_id, venue_id,
                    String.valueOf(prefManager.getPreference(LOGIN_ID, "")), String.valueOf(product_id),
                    String.valueOf(modifier_id),
                    offer_id, new_price, couponCode);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LikeDislikeProductResponseModel> call = apiInterface.likeDislikeProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<LikeDislikeProductResponseModel>() {
                @Override
                public void onResponse(Call<LikeDislikeProductResponseModel> call, Response<LikeDislikeProductResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                       /* if (dialog != null)
                            dialog.dismiss();*/

                        if (response.isSuccessful()) {
                            LikeDislikeProductResponseModel venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {

                                DialogUtils.showAlertDialogWithSingleButton(mContext, venueDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                                data.remove(pos);
                                notifyItemRemoved(pos);
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
//                    binding.swipeRefresh.setRefreshing(false);
                    /*if (dialog != null)
                        dialog.dismiss();*/
                    showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                }
            });
        } else {
//            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.no_internet_available_msg));

        }
    }

}

