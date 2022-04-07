package com.poundland.retail.activityHospitality.adapter;

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
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellUpsellingProductBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.AddToCartHospitalityRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;


/* for setting item for the recycler view for */
public class UpsellingProductAdapter extends RecyclerView.Adapter<UpsellingProductAdapter.ViewResource> {

    private static final int IS_HOSPITALITY = 1;
    private Context mContext;
    private DrawerListner drawerListner;
    private List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.RelatedProductsBean> data;
    private LayoutInflater mInflater;
    private PrefManager prefManager;
    private String venue;
    private AddToCartHospitalityRequestModel requestModelPre;
    private String reservationId;

    public UpsellingProductAdapter(Context context, List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.RelatedProductsBean> related_products, String venue, AddToCartHospitalityRequestModel requestModel, String reservationId, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.data = related_products;
        this.venue = venue;
        this.requestModelPre = requestModel;
        this.reservationId = reservationId;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(context);
    }

    @NonNull
    @Override
    public UpsellingProductAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_upselling_product, parent, false);

        return new UpsellingProductAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UpsellingProductAdapter.ViewResource holder, int position) {

        holder.binding.tvItemName.setText(data.get(position).getProduct_name());
        holder.binding.tvStoreName.setText(venue);
        holder.binding.tvNewPrice.setText(mContext.getString(R.string.pound) + data.get(position).getFinal_sell_price());


        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + data.get(position).getImages()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivItemImage);


        holder.binding.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(holder.getAdapterPosition(),holder);
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
        public LayoutCellUpsellingProductBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private void addToCart(int position, ViewResource holder) {
        if (isInternetOn(mContext)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
            if (dialog != null)
                dialog.show();
            List<Integer> addon_idsList = null;
            List<AddToCartHospitalityRequestModel.AddOnsBean> listAdonReq =null;

            AddToCartHospitalityRequestModel request = new AddToCartHospitalityRequestModel
                    (reservationId,requestModelPre.getMerchant_id(), requestModelPre.getVenue_id(),
                            prefManager.getPreference(LOGIN_ID, 0),
                            data.get(position).getProduct_id(),
                            data.get(position).getModifier_id(), 1, 0, 0,
                            String.valueOf(data.get(position).getFinal_sell_price()),
                            String.valueOf(data.get(position).getFinal_sell_price()),
                            "", listAdonReq, addon_idsList, "",IS_HOSPITALITY);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.hospitality_addCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), request);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {
                                prefManager.savePreference(CART_ENTRY_TYPE, CART_HOSPITALITY);
                                holder.binding.tvAddToCart.setText(mContext.getString(R.string.go_to_bag));
                                holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
                                //successActionListner.onSuccessActionListner();
                                showSnackBar(holder.binding.getRoot(), cartResponseModel.getMessage());
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
    }

}
