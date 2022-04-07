package com.poundland.retail.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.MyCartActivity;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellCategorywiseProductBinding;
import com.poundland.retail.dialog.DialogRetailModifierSelect;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierAddListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AddMobileCartQuantityRequestModel;
import com.poundland.retail.model.requestModel.AddToCartComboRequestModel;
import com.poundland.retail.model.requestModel.AddToCartRequestModel;
import com.poundland.retail.model.requestModel.AddressRequestModel;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.GetCartSummaryHospResponseModel;
import com.poundland.retail.model.responseModel.HomeFilterResponseModel;
import com.poundland.retail.model.responseModel.LikeDislikeProductResponseModel;
import com.poundland.retail.model.responseModel.NotifyMeResponseModel;

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
import static com.poundland.retail.interfaces.Constants.CART_RETAIL;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.PRODUCT;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class HomeProductFilterAdapter extends RecyclerView.Adapter<HomeProductFilterAdapter.ViewResource> {

    private Context mContext;
    private LayoutInflater mInflater;
    private DrawerListner drawerListner;
    private SuccessActionListner successActionListner;
    private List<HomeFilterResponseModel.ProductsBean.DataBean> list;
    private PrefManager prefManager;

    public HomeProductFilterAdapter(Context mContext, List<HomeFilterResponseModel.ProductsBean.DataBean> list,
                                    DrawerListner drawerListner, SuccessActionListner successActionListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.list = list;
        this.drawerListner = drawerListner;
        this.successActionListner = successActionListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public HomeProductFilterAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_categorywise_product, parent, false);

        return new HomeProductFilterAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeProductFilterAdapter.ViewResource holder, int position) {

        if (list.get(position).getIsCart() == 1) {
            if (list.get(holder.getAdapterPosition()).getMod_count() > 1) {
                holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option_another));
            } else {
                //  holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_another));

                holder.binding.llUpdateQty.setVisibility(View.VISIBLE);
                holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.red_colored_filled_redi_1dp));
                holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
                holder.binding.tvQtyCount.setText(String.valueOf(list.get(holder.getAdapterPosition()).getCart().getQuantities()));
                holder.binding.tvAddToCart.setVisibility(View.GONE);


            }
            holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.red_colored_filled_redi_1dp));
        } else {
            if (list.get(holder.getAdapterPosition()).getMod_count() > 1) {
                holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option));
            } else holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_to_cart));
        }


        if (list.get(position).isWishlisted())
            holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);

        if (list.get(position).getSameDayShipping() == 1) {
            holder.binding.ivSameDayDelivery.setVisibility(View.VISIBLE);
        } else holder.binding.ivSameDayDelivery.setVisibility(View.GONE);

        holder.binding.tvItemName.setText(list.get(position).getProduct_name());

        holder.binding.tvNewPrice.setText(mContext.getString(R.string.pound) + list.get(position).getSelling_price());   ////  getActualPrice();

        if (list.get(position).getProduct_description() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.tvDescription.setText(Html.fromHtml(list.get(position).getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.binding.tvDescription.setText(Html.fromHtml(list.get(position).getProduct_description()));
            }
        }

        if (list.get(position).getAvl_quantity() < 1) {
            holder.binding.ivItemOutOfStock.setVisibility(View.VISIBLE);
            holder.binding.tvAddToCart.setText(mContext.getString(R.string.notify_me));
        }

        holder.binding.tvStoreName.setText(list.get(position).getVenue_name());

        holder.binding.tvDistance.setText(list.get(position).getDistance() != null ? list.get(position).getDistance() + mContext.getString(R.string.miles) : "" + mContext.getString(R.string.miles));

        holder.binding.rbRating.setRating(Float.parseFloat(list.get(position).getStars()));


        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + list.get(position).getImages()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivItemImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), PRODUCT);
            }
        });

        holder.binding.ivFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                likeDislike(holder, holder.getAdapterPosition(), list.get(holder.getAdapterPosition()).getMerchant_id(),
                        list.get(holder.getAdapterPosition()).getVenue_id(),
                        list.get(holder.getAdapterPosition()).getId(),
                        list.get(holder.getAdapterPosition()).getModifier_id(),
                        "",
                        list.get(holder.getAdapterPosition()).getSelling_price(),
                        "");
            }
        });


        holder.binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInternetOn(mContext)) {
                    try {
                        if (list.size() > 0) {
                            if (Integer.parseInt(holder.binding.tvQtyCount.getText().toString()) >= 0) {
                                int count = Integer.parseInt(holder.binding.tvQtyCount.getText().toString());
                                --count;
                                holder.binding.tvQtyCount.setText(String.valueOf(count));
                                updateQuantity(holder.binding.tvQtyCount.getText().toString(), String.valueOf(list.get(position).getId()),
                                        String.valueOf(list.get(holder.getAdapterPosition()).getModifier_id()), "0", holder);

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                    showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.no_internet_available_msg));


            }
        });

        holder.binding.btnPlush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInternetOn(mContext)) {
                    try {
                        if (list.size() > 0) {
                            int count = Integer.parseInt(holder.binding.tvQtyCount.getText().toString());
                            ++count;

                            // if (dataBeanList.get(holder.getAdapterPosition()).getIn_stock() >= count) {
                            holder.binding.tvQtyCount.setText(String.valueOf(count));
                            updateQuantity(holder.binding.tvQtyCount.getText().toString(), String.valueOf(list.get(position).getId()),
                                    String.valueOf(list.get(holder.getAdapterPosition()).getModifier_id()), "0", holder);
                       /* } else {
                            showSnackBar(holder.binding.getRoot(), mContext.getResources().getString(R.string.out_of_stock));
                            --count;
                        }
*/
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                    showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.no_internet_available_msg));


            }
        });

        /////


        holder.binding.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.notify_me))) {
                    notifyMe(holder, list.get(holder.getAdapterPosition()).getMerchant_id(),
                            list.get(holder.getAdapterPosition()).getVenue_id(),
                            list.get(holder.getAdapterPosition()).getId(),
                            list.get(holder.getAdapterPosition()).getModifier_id());
                } else if (holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.add_to_cart)) || holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.add_another))
                        || holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.select_option)) || holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.select_option_another))) {
                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(list.get(holder.getAdapterPosition()).getVenue_id()) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                        prefManager.savePreference(VENUE_ID_IN_CART, list.get(holder.getAdapterPosition()).getVenue_id());

                        if (list.get(holder.getAdapterPosition()).getMod_count() > 1) {
                            new DialogRetailModifierSelect(mContext,
                                    list.get(holder.getAdapterPosition()).getId(),
                                    list.get(holder.getAdapterPosition()).getProduct_name(), new ModifierAddListner() {
                                @Override
                                public void onModifierSelect(AddToCartComboRequestModel s) {
                                    addToCartCombo(s, false, holder);
                                }
                            }).show();
                        } else addToCart(false, false, holder);

                    } else {
                        DialogUtils.showAlertDialog(mContext, mContext.getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, list.get(holder.getAdapterPosition()).getVenue_id());

                                if (list.get(holder.getAdapterPosition()).getMod_count() > 1) {
                                    new DialogRetailModifierSelect(mContext, list.get(holder.getAdapterPosition()).getId(), list.get(holder.getAdapterPosition()).getProduct_name(), new ModifierAddListner() {
                                        @Override
                                        public void onModifierSelect(AddToCartComboRequestModel s) {
                                            addToCartCombo(s, false, holder);
                                        }
                                    }).show();
                                } else addToCart(false, false, holder);

                            }

                            @Override
                            public void onNegativeClick() {
                            }
                        });
                    }
                } /*else if (holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.go_to_bag))) {
                    Intent goToCart = new Intent(mContext, MyCartActivity.class);
                    mContext.startActivity(goToCart);
                }
*/
            }
        });

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellCategorywiseProductBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    private void notifyMe(final HomeProductFilterAdapter.ViewResource holder, int merchant_id, String venue_id, int product_id, int modifier_id) {
        if (isInternetOn(mContext)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
            if (dialog != null)
                dialog.show();

            final AddressRequestModel requestModel = new AddressRequestModel(prefManager.getPreference(EMAIL, ""), String.valueOf(product_id), venue_id, String.valueOf(merchant_id), String.valueOf(modifier_id));

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<NotifyMeResponseModel> call = apiInterface.notifyMe(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<NotifyMeResponseModel>() {
                @Override
                public void onResponse(Call<NotifyMeResponseModel> call, Response<NotifyMeResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            NotifyMeResponseModel responseModel = response.body();

                            if (responseModel.isStatus()) {
                                showSnackBar(holder.binding.getRoot(), responseModel.getMessage());
                            } else {
                                showSnackBar(holder.binding.getRoot(), responseModel.getMessage());
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
                public void onFailure(Call<NotifyMeResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.no_internet_available_msg));
        }
    }

    private void likeDislike(final HomeProductFilterAdapter.ViewResource holder, final int pos, int merchant_id, String venue_id, int product_id, int modifier_id, String offer_id, String new_price, String couponCode) {
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
                                if (list.get(pos).isWishlisted()) {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                                    list.get(pos).setWishlisted(false);
                                } else {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
                                    list.get(pos).setWishlisted(true);
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

    private void addToCart(boolean openCart, boolean isCombo, HomeProductFilterAdapter.ViewResource holder) {
        if (isInternetOn(mContext)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
            if (dialog != null)
                dialog.show();
            AddToCartRequestModel requestModel = null;

            requestModel = new AddToCartRequestModel
                    (String.valueOf(list.get(holder.getAdapterPosition()).getMerchant_id()), list.get(holder.getAdapterPosition()).getVenue_id(),
                            String.valueOf(list.get(holder.getAdapterPosition()).getId()),
                            String.valueOf(list.get(holder.getAdapterPosition()).getModifier_id()),
                            "0", "1", list.get(holder.getAdapterPosition()).getSelling_price(),
                            "", "", "0", prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""));

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
                                if (openCart) {
                                    if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                                        Intent intentH = new Intent(mContext, MyCartHospitalityActivity.class);
                                        mContext.startActivity(intentH);
                                    } else {
                                        Intent intentR = new Intent(mContext, MyCartActivity.class);
                                        mContext.startActivity(intentR);
                                    }
                                } else {
                                    if (!isCombo) {

                                        if (list.get(holder.getAdapterPosition()).getMod_count() > 1) {
                                            holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option_another));
                                        } else
                                            holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_another));

                                        holder.binding.llUpdateQty.setVisibility(View.VISIBLE);
                                        holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.red_colored_filled_redi_1dp));
                                        holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));

                                        holder.binding.tvQtyCount.setText(String.valueOf(list.get(holder.getAdapterPosition()).getCart().getQuantities() + 1));

                                        holder.binding.tvAddToCart.setVisibility(View.GONE);

                                    }
                                }
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
                            ///
                            if (list.get(holder.getAdapterPosition()).getMod_count() > 1) {
                                holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option_another));
                            } else
                                holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_another));
                            holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.red_colored_filled_redi_1dp));
                            holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
                            successActionListner.onSuccessActionListner();
                            ////


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


    private void updateQuantity(String quantities, String product_id, String modifier_id, String offer_id, ViewResource holder) {
        if (quantities.equalsIgnoreCase("0")) {
            holder.binding.llUpdateQty.setVisibility(View.GONE);
            holder.binding.tvAddToCart.setVisibility(View.VISIBLE);
            holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_to_cart));
            holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
            holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.app_colored_filled_rect_100));

        }

        final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
        if (dialog != null)
            dialog.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetCartSummaryHospResponseModel> call = null;

        final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(
                quantities, product_id, modifier_id, offer_id);
        call = apiInterface.updateCartQuantities(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);

        call.enqueue(new Callback<GetCartSummaryHospResponseModel>() {
            @Override
            public void onResponse(Call<GetCartSummaryHospResponseModel> call, Response<GetCartSummaryHospResponseModel> response) {
                try {
                    if (dialog != null)
                        dialog.dismiss();

                    if (response.isSuccessful()) {

                        if (response.body().isStatus()) {


                            list.get(holder.getAdapterPosition()).getCart().setQuantities(Integer.parseInt(quantities));
                            successActionListner.onSuccessActionListner();


                        } else {
                            showSnackBar(holder.binding.getRoot(), response.body().getMessage());
                        }
                    } else {
                        final int httpCode = response.code();

                        DialogUtils.showAlertDialogWithSingleButton(mContext, httpCode == 401 ? mContext.getString(R.string.season_expired) : mContext.getString(R.string.something_went_wrong), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                if (httpCode == 401) {
                                    //  logOut(instance);
                                }
                            }

                            @Override
                            public void onNegativeClick() {

                            }
                        });
                    }

                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetCartSummaryHospResponseModel> call, Throwable t) {
                // binding.swipeRefresh.setRefreshing(false);
                if (dialog != null)
                    dialog.dismiss();
                showSnackBar(holder.binding.getRoot(), mContext.getString(R.string.msg_please_try_later));
            }
        });
    }

}
