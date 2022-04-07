package com.poundland.retail.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.adapter.AllergyAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellHospitalityVenueProductBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.dialog.HospitalityProductModifierDetailsDialog;
import com.poundland.retail.interfaces.AllergyListener;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.OnSeasonExpireListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AddMobileCartQuantityRequestModel;
import com.poundland.retail.model.requestModel.AddToCartHospitalityRequestModel;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.GetCartSummaryHospResponseModel;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;
import com.poundland.retail.model.responseModel.LikeDislikeProductResponseModel;
import com.poundland.retail.model.responseModel.VenueThemeResponse;

import java.util.ArrayList;
import java.util.HashMap;
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
import static com.poundland.retail.interfaces.Constants.FROM_HOSPITALITY_PRODUCT_LIST;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;


public class VenueProductsHospitalityAdapter extends RecyclerView.Adapter<VenueProductsHospitalityAdapter.ViewResource> {
    private static final int IS_HOSPITALITY = 1;
    private Context mContext;
    private LayoutInflater mInflater;
    private DrawerListner drawerListner;
    private List<HospitalityVenueProductResponseModel.ProductsBean.DataBean> dataBeanList;
    private PrefManager prefManager;
    private String venue_name;
    private SuccessActionListner successActionListner;
    private VenueThemeResponse.Data themeColor;
    private String reservationId;

    public VenueProductsHospitalityAdapter(Context mContext, VenueThemeResponse.Data themeColor,
                                           List<HospitalityVenueProductResponseModel.ProductsBean.DataBean> productList, DrawerListner drawerListner,
                                           SuccessActionListner successActionListner, String venue_name, String reservationId) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.themeColor = themeColor;
        this.dataBeanList = productList;
        this.reservationId = reservationId;
        this.venue_name = venue_name;
        this.drawerListner = drawerListner;
        this.successActionListner = successActionListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public VenueProductsHospitalityAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_venue_product, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_hospitality_venue_product, parent, false);
        return new VenueProductsHospitalityAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueProductsHospitalityAdapter.ViewResource holder, int position) {

        if (dataBeanList.get(position).getIs_like() == 1)
            holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);

        if (themeColor != null) {
            //  int colorFont = Color.parseColor(themeColor.getHeaderColor());  // TODO : THIS CAN BE NULL SO DO NULL CHECK IF IT IS IN USE
            // int colorFont = Color.parseColor(themeColor.getHeaderFontColor());
            if (themeColor.getAddToCartColor() != null) {
                holder.binding.tvAddToCart.setBackgroundColor(Color.parseColor(themeColor.getAddToCartColor()));
                holder.binding.btnMinus.setBackgroundColor(Color.parseColor(themeColor.getAddToCartColor()));
                holder.binding.btnPlush.setBackgroundColor(Color.parseColor(themeColor.getAddToCartColor()));
            }

            if (themeColor.getAddToCartFontColor() != null)
                holder.binding.tvAddToCart.setTextColor(Color.parseColor(themeColor.getAddToCartFontColor()));
           /* HelperClass.setTextViewTintDrawableColorInAdapter(holder.binding.tvDistance, colorFont);
            HelperClass.setTextViewTintDrawableColorInAdapter(holder.binding.tvStoreName, colorFont);*/
        }

        if (dataBeanList.get(position).getIsCart() == 1) {
            if (dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 1) {
                holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option_another));
            } else {
                holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_another));
                holder.binding.llUpdateQty.setVisibility(View.VISIBLE);
                holder.binding.tvAddToCart.setVisibility(View.GONE);

                try {
                    holder.binding.tvQtyCount.setText(String.valueOf(dataBeanList.get(holder.getAdapterPosition()).getModifier_list().get(0).getCart().getQuantities()));
                } catch (Exception e) {
                    Log.e("sssssss", "" + e.getMessage());
                }

            }
            holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.red_colored_filled_redi_1dp));

        } else {
            if (dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 1) {
                holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option));
            } else holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_to_cart));
        }


        if (dataBeanList.get(position).getAllergies_list() != null && dataBeanList.get(position).getAllergies_list().size() > 0) {
            if (dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 1) {
                holder.binding.llAllergy.setVisibility(View.GONE);
            } else {
                holder.binding.llAllergy.setVisibility(View.VISIBLE);
            }
        } else holder.binding.llAllergy.setVisibility(View.GONE);


        setAllergyAdapter(holder, holder.getAdapterPosition());

        holder.binding.tvItemName.setText(dataBeanList.get(position).getProduct_name());

        if (dataBeanList.get(position).getDiscount_type() == 1) {   //// 1 : Per , 2 : amount
            holder.binding.tvOldPrice.setPaintFlags(holder.binding.tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            double amtDisc = dataBeanList.get(position).getFinal_sell_price() * Float.parseFloat(dataBeanList.get(position).getDiscount_amount()) / 100;
            double amtSell = dataBeanList.get(position).getFinal_sell_price() - amtDisc;
            holder.binding.tvOldPrice.setText(mContext.getString(R.string.pound) + String.format("%.2f", dataBeanList.get(position).getFinal_sell_price()));
            holder.binding.tvNewPrice.setText(mContext.getString(R.string.pound) + String.format("%.2f", amtSell));

        } else if (dataBeanList.get(position).getDiscount_type() == 2) {
            holder.binding.tvOldPrice.setPaintFlags(holder.binding.tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            float amtDisc = Float.parseFloat(dataBeanList.get(position).getDiscount_amount());
            double amtSell = dataBeanList.get(position).getFinal_sell_price() - amtDisc;

            holder.binding.tvOldPrice.setText(mContext.getString(R.string.pound) + String.format("%.2f", dataBeanList.get(position).getFinal_sell_price()));
            holder.binding.tvNewPrice.setText(mContext.getString(R.string.pound) + String.format("%.2f", amtSell));
        } else {
            holder.binding.tvOldPrice.setVisibility(View.GONE);
            holder.binding.tvNewPrice.setText(mContext.getString(R.string.pound) + String.format("%.2f", dataBeanList.get(position).getFinal_sell_price()));
        }

       /* holder.binding.tvStoreName.setText(venue_name);
        holder.binding.tvDistance.setText(distance);
        holder.binding.rbRating.setRating(dataBeanList.get(position).getStars());

        if (dataBeanList.get(position).getStars() > 0) {
            holder.binding.rbRating.setRating(dataBeanList.get(position).getStars());
        } else holder.binding.rbRating.setRating(5f);*/


        if (dataBeanList.get(position).getProduct_description() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.tvDescription.setText(Html.fromHtml(dataBeanList.get(position).getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.binding.tvDescription.setText(Html.fromHtml(dataBeanList.get(position).getProduct_description()));
            }
        }


       /* Glide.with(mContext).load
                (dataBeanList.get(position).getImages() != null ? ApiRequestUrl.BASE_URL + dataBeanList.get(position).getImages() : ApiRequestUrl.BASE_URL + dataBeanList.get(position).getImage())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.default_image)).
                into(holder.binding.ivItemImage);*/

        holder.binding.ivFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                likeDislike(holder, holder.getAdapterPosition(), dataBeanList.get(holder.getAdapterPosition()).getMerchant_id(),
                        dataBeanList.get(holder.getAdapterPosition()).getVenue_id(),
                        dataBeanList.get(holder.getAdapterPosition()).getId(),
                        dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 0 ? dataBeanList.get(holder.getAdapterPosition()).getModifier_list().get(0).getId() : 0
                        , "",
                        String.valueOf(dataBeanList.get(holder.getAdapterPosition()).getFinal_sell_price()), "");
            }
        });

        holder.binding.tvAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.notify_me))) {

                } else if (holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.add_to_cart)) || holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.go_to_bag))
                        || holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.select_option)) || holder.binding.tvAddToCart.getText().toString().equalsIgnoreCase(mContext.getString(R.string.select_option_another))) {
                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(dataBeanList.get(holder.getAdapterPosition()).getVenue_id()) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                        prefManager.savePreference(VENUE_ID_IN_CART, dataBeanList.get(holder.getAdapterPosition()).getVenue_id());
                        if (dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 1) {
                            double amtDisc = 0;
                            if (dataBeanList.get(position).getDiscount_type() == 1) {   //// 1 : Per , 2 : amount
                                amtDisc = dataBeanList.get(position).getFinal_sell_price() * Float.parseFloat(dataBeanList.get(position).getDiscount_amount()) / 100;
                            } else if (dataBeanList.get(position).getDiscount_type() == 2) {
                                amtDisc = Float.parseFloat(dataBeanList.get(position).getDiscount_amount());
                            }

                            HospitalityProductModifierDetailsDialog hospitalityProductModifierDetailsDialog = HospitalityProductModifierDetailsDialog.newInstance(mContext, FROM_HOSPITALITY_PRODUCT_LIST, venue_name, amtDisc, reservationId,
                                    dataBeanList.get(holder.getAdapterPosition()), new OnSeasonExpireListener() {
                                        @Override
                                        public void onSeasonExpire() {
                                            HelperClass.logOut((Activity) mContext);
                                        }
                                    }, new SuccessActionListner() {
                                        @Override
                                        public void onSuccessActionListner() {
                                            if (dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 1) {
                                                holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option_another));
                                            } else
                                                holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_another));
                                            holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.red_colored_filled_redi_1dp));
                                            successActionListner.onSuccessActionListner();
                                        }
                                    });
                            hospitalityProductModifierDetailsDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");


                        } else addToCart(null, holder);
                    } else {
                        DialogUtils.showAlertDialog(mContext, mContext.getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, dataBeanList.get(holder.getAdapterPosition()).getVenue_id());

                                if (dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 1) {
                                    double amtDisc = 0;
                                    if (dataBeanList.get(position).getDiscount_type() == 1) {   //// 1 : Per , 2 : amount
                                        amtDisc = dataBeanList.get(position).getFinal_sell_price() * Float.parseFloat(dataBeanList.get(position).getDiscount_amount()) / 100;
                                    } else if (dataBeanList.get(position).getDiscount_type() == 2) {
                                        amtDisc = Float.parseFloat(dataBeanList.get(position).getDiscount_amount());
                                    }
                                    /*new DialogHospitalityProductModifierDetail(mContext, FROM_HOSPITALITY_PRODUCT_LIST, venue_name, amtDisc, reservationId,
                                            dataBeanList.get(holder.getAdapterPosition()), new OnSeasonExpireListener() {
                                        @Override
                                        public void onSeasonExpire() {
                                            HelperClass.logOut((Activity) mContext);
                                        }
                                    }).show();*/


                                    HospitalityProductModifierDetailsDialog hospitalityProductModifierDetailsDialog = HospitalityProductModifierDetailsDialog.newInstance(mContext, FROM_HOSPITALITY_PRODUCT_LIST, venue_name, amtDisc, reservationId,
                                            dataBeanList.get(holder.getAdapterPosition()), new OnSeasonExpireListener() {
                                                @Override
                                                public void onSeasonExpire() {
                                                    HelperClass.logOut((Activity) mContext);
                                                }
                                            }, new SuccessActionListner() {
                                                @Override
                                                public void onSuccessActionListner() {
                                                    if (dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 1) {
                                                        holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option_another));
                                                    } else
                                                        holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_another));
                                                    holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.red_colored_filled_redi_1dp));
                                                    successActionListner.onSuccessActionListner();
                                                }
                                            });
                                    hospitalityProductModifierDetailsDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");


                                } else addToCart(null, holder);
                            }

                            @Override
                            public void onNegativeClick() {
                            }
                        });
                    }
                }

            }
        });


        holder.binding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInternetOn(mContext)) {
                    try {
                        if (dataBeanList.size() > 0) {
                            if (Integer.parseInt(holder.binding.tvQtyCount.getText().toString()) >= 0) {
                                int count = Integer.parseInt(holder.binding.tvQtyCount.getText().toString());
                                --count;
                                holder.binding.tvQtyCount.setText(String.valueOf(count));
                                updateQuantity(holder.binding.tvQtyCount.getText().toString(), String.valueOf(dataBeanList.get(position).getId()),
                                        dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 0 ? String.valueOf(dataBeanList.get(holder.getAdapterPosition()).getModifier_list().get(0).getId()) : "0",
                                        String.valueOf(dataBeanList.get(position).getOffer_id()), holder);

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
                        if (dataBeanList.size() > 0) {
                            int count = Integer.parseInt(holder.binding.tvQtyCount.getText().toString());
                            ++count;

                            // if (dataBeanList.get(holder.getAdapterPosition()).getIn_stock() >= count) {
                            updateQuantity(String.valueOf(count), String.valueOf(dataBeanList.get(position).getId()),
                                    dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 0 ? String.valueOf(dataBeanList.get(holder.getAdapterPosition()).getModifier_list().get(0).getId()) : "0",
                                    String.valueOf(dataBeanList.get(position).getOffer_id()), holder);
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
    }

    private void setAllergyAdapter(ViewResource holder, int adapterPosition) {
        AllergyAdapter innerAdapter = new AllergyAdapter(mContext, dataBeanList.get(adapterPosition).getAllergies_list(), new AllergyListener() {
            @Override
            public void onAllergySelect(HashMap<Integer, String> hashMap) {

                List<String> list = new ArrayList<String>(hashMap.values());
                String name = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    name = String.join(",", list);
                }
                dataBeanList.get(adapterPosition).setmSelectedAllergy(name);

            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.binding.rvAllergyList.setLayoutManager(layoutManager);
        holder.binding.rvAllergyList.setAdapter(innerAdapter);

    }


    @Override
    public int getItemCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        //  public LayoutCellVenueProductBinding binding;
        public LayoutCellHospitalityVenueProductBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


    private void addToCart(AddToCartHospitalityRequestModel request, ViewResource holder) {
        if (isInternetOn(mContext)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(mContext);
            if (dialog != null)
                dialog.show();
            AddToCartHospitalityRequestModel requestModel = null;
            requestModel = new AddToCartHospitalityRequestModel
                    (reservationId, dataBeanList.get(holder.getAdapterPosition()).getMerchant_id(),
                            dataBeanList.get(holder.getAdapterPosition()).getVenue_id(),
                            prefManager.getPreference(LOGIN_ID, 0),
                            dataBeanList.get(holder.getAdapterPosition()).getId(),
                            dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 0 ? dataBeanList.get(holder.getAdapterPosition()).getModifier_list().get(0).getId() : 0,
                            1, dataBeanList.isEmpty() ? 0 : dataBeanList.get(holder.getAdapterPosition()).getOffer_id(), 0,
                            String.valueOf(dataBeanList.get(holder.getAdapterPosition()).getFinal_sell_price()),
                            String.valueOf(dataBeanList.get(holder.getAdapterPosition()).getFinal_sell_price()), null, null, null, dataBeanList.get(holder.getAdapterPosition()).getmSelectedAllergy(),IS_HOSPITALITY);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.hospitality_addCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
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
                                prefManager.savePreference(CART_ENTRY_TYPE, CART_HOSPITALITY);


                                if (dataBeanList.get(holder.getAdapterPosition()).getModifier_list().size() > 1) {
                                    holder.binding.tvAddToCart.setText(mContext.getString(R.string.select_option_another));
                                } else
                                    holder.binding.tvAddToCart.setText(mContext.getString(R.string.add_another));

                                holder.binding.llUpdateQty.setVisibility(View.VISIBLE);
                                try {
                                    holder.binding.tvQtyCount.setText(String.valueOf(dataBeanList.get(holder.getAdapterPosition()).getModifier_list().get(0).getCart().getQuantities() + 1));
                                } catch (Exception e) {
                                    Log.e("sssssss", "" + e.getMessage());
                                }
                                holder.binding.tvAddToCart.setVisibility(View.GONE);

                                holder.binding.tvAddToCart.setTextColor(mContext.getResources().getColor(R.color.color_white));
                                holder.binding.tvAddToCart.setBackground(ContextCompat.getDrawable(mContext, R.drawable.red_colored_filled_redi_1dp));
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
                            holder.binding.tvQtyCount.setText(quantities);
                            dataBeanList.get(holder.getAdapterPosition()).getModifier_list().get(0).getCart().setQuantities(Integer.parseInt(quantities));
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

    private void likeDislike(final VenueProductsHospitalityAdapter.ViewResource holder, final int pos, int merchant_id, String venue_id, int product_id, int modifier_id, String offer_id, String new_price, String couponCode) {
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
                                if (dataBeanList.get(pos).getIs_like() == 1) {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                                    dataBeanList.get(pos).setIs_like(0);
                                } else {
                                    holder.binding.ivFavo.setColorFilter(ContextCompat.getColor(mContext, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
                                    dataBeanList.get(pos).setIs_like(1);
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

}
