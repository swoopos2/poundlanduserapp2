package com.poundland.retail.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
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
import com.poundland.retail.databinding.XxLayoutCellTopCateProductInnerItemBinding;
import com.poundland.retail.dialog.DialogRetailModifierSelect;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierAddListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AddToCartComboRequestModel;
import com.poundland.retail.model.requestModel.AddToCartRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.LandingPageBottomResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_RETAIL;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class TopCategoryProductInnerAdapter extends RecyclerView.Adapter<TopCategoryProductInnerAdapter.ViewResource> {

    private Context mContext;
    private DrawerListner drawerListner;
    private SuccessActionListner successActionListner;
    private ModifierSelectionListner modifierSelectionListner;
    private List<LandingPageBottomResponseModel.TopCatProductsBean.ProductsBean> data;
    private LayoutInflater mInflater;
    private PrefManager prefManager;


    public TopCategoryProductInnerAdapter(Context mContext, List<LandingPageBottomResponseModel.TopCatProductsBean.ProductsBean> data, ModifierSelectionListner modifierSelectionListner, DrawerListner drawerListner, SuccessActionListner successActionListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
        this.successActionListner = successActionListner;
        this.modifierSelectionListner = modifierSelectionListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public TopCategoryProductInnerAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xx_layout_cell_top_cate_product_inner_item, parent, false);

        return new TopCategoryProductInnerAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopCategoryProductInnerAdapter.ViewResource holder, int position) {

        if (data.get(holder.getAdapterPosition()).getMod_count() > 1) {
            holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option));
        } else holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_to_cart));

        holder.binding.tvStoreName.setText(data.get(position).getVenue_name());
        holder.binding.tvProductName.setText(data.get(position).getProduct_name());
        holder.binding.tvPrice.setText(mContext.getString(R.string.pound) + data.get(position).getSelling_price());


        if (!TextUtils.isEmpty(data.get(position).getStars().getRating_value()) && Float.parseFloat(data.get(position).getStars().getRating_value()) > 0) {
            holder.binding.rbRating.setRating(Float.parseFloat(data.get(position).getStars().getRating_value()));
        } else holder.binding.rbRating.setRating(5f);


        Glide.with(mContext).load(TextUtils.isEmpty(data.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + data.get(position).getImages() : ApiRequestUrl.BASE_URL + data.get(position).getModifier_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImg);

        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifierSelectionListner.onModifierSelect(-1, holder.getAdapterPosition());
            }
        });

        holder.binding.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.add_to_cart)) || holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.go_to_bag)) || holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.select_option))) {

                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(data.get(holder.getAdapterPosition()).getVenue_id()) ||
                            prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                        prefManager.savePreference(VENUE_ID_IN_CART, data.get(holder.getAdapterPosition()).getVenue_id());
                        if (data.get(holder.getAdapterPosition()).getMod_count() > 1) {
                            new DialogRetailModifierSelect(mContext,
                                    data.get(holder.getAdapterPosition()).getId(),
                                    data.get(holder.getAdapterPosition()).getProduct_name(), new ModifierAddListner() {
                                @Override
                                public void onModifierSelect(AddToCartComboRequestModel s) {
                                    addToCartCombo(s, false, holder);
                                }
                            }).show();
                        } else addToCart(holder);
                    } else {
                        DialogUtils.showAlertDialog(mContext, mContext.getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, data.get(holder.getAdapterPosition()).getVenue_id());
                                if (data.get(holder.getAdapterPosition()).getMod_count() > 1) {
                                    new DialogRetailModifierSelect(mContext,
                                            data.get(holder.getAdapterPosition()).getId(),
                                            data.get(holder.getAdapterPosition()).getProduct_name(), new ModifierAddListner() {
                                        @Override
                                        public void onModifierSelect(AddToCartComboRequestModel s) {
                                            addToCartCombo(s, false, holder);
                                        }
                                    }).show();
                                } else addToCart(holder);
                            }

                            @Override
                            public void onNegativeClick() {
                            }
                        });
                    }
                }
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
        public XxLayoutCellTopCateProductInnerItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private void addToCart(ViewResource holder) {
        if (isInternetOn(mContext)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
            if (dialog != null)
                dialog.show();
            AddToCartRequestModel requestModel = null;

            requestModel = new AddToCartRequestModel
                    (String.valueOf(data.get(holder.getAdapterPosition()).getMerchant_id()), data.get(holder.getAdapterPosition()).getVenue_id(),
                            String.valueOf(data.get(holder.getAdapterPosition()).getId()), String.valueOf(data.get(holder.getAdapterPosition()).getModifier_id()),
                            "0", "1",
                            data.get(holder.getAdapterPosition()).getSelling_price(), "", "", "0", prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""));

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
                                prefManager.savePreference(CART_ENTRY_TYPE, CART_RETAIL);
                                holder.binding.tvAddToCart.setText(mContext.getString(R.string.go_to_bag));
                                // holder.binding.tvAddToCart.setBackgroundResource(R.drawable.saffron_filled_rect);
                                holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));

                                successActionListner.onSuccessActionListner();

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

    private void addToCartCombo(AddToCartComboRequestModel comboRequestModel, boolean b, ViewResource holder) {
        if (isInternetOn(mContext)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addMultipleCarts(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), comboRequestModel);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            prefManager.savePreference(CART_ENTRY_TYPE, CART_RETAIL);
                            AddToCartResponseModel cartResponseModel = response.body();
                            holder.binding.tvAddToCart.setText(mContext.getString(R.string.go_to_bag));
                            holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
                            successActionListner.onSuccessActionListner();

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
