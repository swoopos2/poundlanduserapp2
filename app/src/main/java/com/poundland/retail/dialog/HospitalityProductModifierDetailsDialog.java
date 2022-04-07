package com.poundland.retail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.MyCartActivity;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.activityHospitality.MyCartReservationActivity;
import com.poundland.retail.activityHospitality.adapter.AllergyAdapter;
import com.poundland.retail.activityHospitality.adapter.HospitalityIngredientAdapter;
import com.poundland.retail.activityHospitality.adapter.HospitalityModiItemInnerAdapterNew;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.DialogHospitalityProductModifierDetailBinding;
import com.poundland.retail.interfaces.AllergyListener;
import com.poundland.retail.interfaces.CancelListner;
import com.poundland.retail.interfaces.HospIngredientSelectionListener;
import com.poundland.retail.interfaces.ModifierAddHospitalityListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.OnSeasonExpireListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AddToCartHospitalityRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.BaseIngredientsModel;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CART_RESERVATION;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_HOSPITALITY_PRODUCT_LIST;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;

public class HospitalityProductModifierDetailsDialog extends BottomSheetDialogFragment implements View.OnClickListener, ModifierSelectionListner, HospIngredientSelectionListener {
    private static final int IS_HOSPITALITY = 1;
    private DialogHospitalityProductModifierDetailBinding binding;
    private LayoutInflater inflater;
    private PrefManager prefManager;
    private HospitalityVenueProductResponseModel.ProductsBean.DataBean dataBeans;
    private Map<String, BaseIngredientsModel> addonMap;
    private List<BaseIngredientsModel> prodAddOnList;
    private int modifierId;
    private Map<Integer, AddToCartHospitalityRequestModel.AddOnsBean> addonMapRq = new HashMap<>();
    private String venueName;
    HospitalityModiItemInnerAdapterNew modifierAdapter;
    HospitalityIngredientAdapter ingredientAdapter;
    private double discPrice;
    // private double costPerProduct = 0;
    private String reservationId;
    private String fromWhere;
    private OnSeasonExpireListener onSeasonExpireListener;
    private SuccessActionListner successActionListner;
    private Context context;
    private float dX;
    private float dY;
    private double amtToShow;

    public static HospitalityProductModifierDetailsDialog newInstance(Context mContext, String fromHospitalityProductList, String venue_name, double amtDisc, String reservationId,
                                                                      HospitalityVenueProductResponseModel.ProductsBean.DataBean dataBean,
                                                                      OnSeasonExpireListener onSeasonExpireListener, SuccessActionListner successActionListner) {
        HospitalityProductModifierDetailsDialog fragment = new HospitalityProductModifierDetailsDialog();

        fragment.context = mContext;
        fragment.inflater = LayoutInflater.from(mContext);
        fragment.prefManager = PrefManager.getInstance(mContext);
        fragment.fromWhere = fromHospitalityProductList;
        fragment.venueName = venue_name;
        fragment.reservationId = reservationId;
        fragment.discPrice = amtDisc;
        fragment.dataBeans = dataBean;
        fragment.onSeasonExpireListener = onSeasonExpireListener;
        fragment.successActionListner = successActionListner;

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        // View contentView = View.inflate(getContext(), R.layout.dialog_hospitality_product_modifier_detail, null);
        //  dialog.setContentView(contentView);


        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_hospitality_product_modifier_detail, null, false);
        dialog.setContentView(binding.getRoot());
        ((View) binding.getRoot().getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        // BottomSheetDialog d = (BottomSheetDialog) dialog;
        FrameLayout bottomSheet = (FrameLayout) dialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);


        init();
        initAllergy();

        binding.rlClose.setOnClickListener(this);
        binding.tvAddQty.setOnClickListener(this);
        binding.tvSubtractQty.setOnClickListener(this);
        binding.tvAddToCart.setOnClickListener(this);

        binding.fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                    Intent intent = new Intent(context, MyCartHospitalityActivity.class);
                    context.startActivity(intent);
                } else if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_RESERVATION) {
                    Intent intent = new Intent(context, MyCartReservationActivity.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, MyCartActivity.class);
                    context.startActivity(intent);
                }
            }
        });


    }

    private void initAllergy() {
        if (dataBeans.getAllergies_list() != null && dataBeans.getAllergies_list().size() > 0) {
            binding.llAllergy.setVisibility(View.VISIBLE);
        } else binding.llAllergy.setVisibility(View.GONE);


        AllergyAdapter innerAdapter = new AllergyAdapter(context, dataBeans.getAllergies_list(), new AllergyListener() {
            @Override
            public void onAllergySelect(HashMap<Integer, String> hashMap) {

                List<String> list = new ArrayList<String>(hashMap.values());
                String name = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    name = String.join(",", list);
                }
                dataBeans.setmSelectedAllergy(name);

            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        binding.rvAllergyList.setLayoutManager(layoutManager);
        binding.rvAllergyList.setAdapter(innerAdapter);


    }


    private void init() {
        binding.fabCart.hide();
        binding.tvProdCount.setVisibility(View.GONE);

        addonMap = new HashMap<>();
        if (fromWhere.equals(FROM_HOSPITALITY_PRODUCT_LIST)) {

            binding.tvProductName.setText(dataBeans.getProduct_name());

            if (dataBeans.getProduct_description() != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.tvProDescription.setText(Html.fromHtml(dataBeans.getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.tvProDescription.setText(Html.fromHtml(dataBeans.getProduct_description()));
                }
            }

            if (dataBeans.getImages() != null) {
                Glide.with(context).load(dataBeans.getImages() != null ? ApiRequestUrl.BASE_URL + dataBeans.getImages() : ApiRequestUrl.BASE_URL + dataBeans.getImage())
                        .apply(RequestOptions.circleCropTransform()).into(binding.ivItemImage);
            } else {
                binding.llImage.setVisibility(View.GONE);
            }

            binding.rbRating.setRating(dataBeans.getStars());
            binding.tvVenueName.setText(venueName);


        }
        if (dataBeans != null) {
            getProductAddOns(dataBeans.getProduct_addon());
        }

        if (dataBeans != null && dataBeans.getModifier_list() != null) {
            int preSelectModi = 0;
            showModifierThumbnail(dataBeans.getModifier_list(), preSelectModi);
        }
        if (dataBeans != null && dataBeans.getModifier_list() != null) {
            setAdapterAdons();
            setModifierAdapter(dataBeans.getModifier_list(), discPrice);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_to_cart:
                if (isValid()) {
                    createDataForRequest();
                } else {
                    //toast(instance, getString(R.string.please_add_ingredient_first));
                }

                break;

            case R.id.rl_close:
                dismiss();
                break;

            case R.id.tv_add_qty:
                int countA = Integer.parseInt(binding.tvModifierItemCount.getText().toString());
                binding.tvModifierItemCount.setText(String.valueOf(++countA));
                updatePrice();
                break;
            case R.id.tv_subtract_qty:
                int countM = Integer.parseInt(binding.tvModifierItemCount.getText().toString());
                if (countM > 1)
                    binding.tvModifierItemCount.setText(String.valueOf(--countM));
                updatePrice();
                break;
        }
    }

    private void getProductAddOns(List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> product_addon) {

        for (int i = 0; i < product_addon.size(); i++) {
            if (addonMap.containsKey(product_addon.get(i).getGroup_id())) {
                BaseIngredientsModel model = addonMap.get(product_addon.get(i).getGroup_id());
                List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> temItems = model.getAddonsList();
                temItems.add(product_addon.get(i));
                model.setAddonsList(temItems);
                addonMap.put(product_addon.get(i).getGroup_id(), model);
            } else {
                List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> temItems = new ArrayList<>();
                temItems.add(product_addon.get(i));
                BaseIngredientsModel model = new BaseIngredientsModel();
                model.setProductAddonsHeader(product_addon.get(i));
                model.setAddonsList(temItems);
                addonMap.put(product_addon.get(i).getGroup_id(), model);

            }
        }
    }

    private void showModifierThumbnail(List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean> modifierList, int preSelectModi) {
        modifierId = dataBeans.getModifier_list().get(preSelectModi).getId();
    }

    private void setModifierAdapter(List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean> modifierList, double discPrice) {
        modifierAdapter = new HospitalityModiItemInnerAdapterNew(context, 0, modifierList, discPrice, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        binding.rvModifierList.setLayoutManager(layoutManager);
        binding.rvModifierList.setAdapter(modifierAdapter);
    }

    private void setAdapterAdons() {
        prodAddOnList = new ArrayList<>();

        for (Map.Entry<String, BaseIngredientsModel> entry : addonMap.entrySet()) {
            prodAddOnList.add(entry.getValue());
        }
        for (BaseIngredientsModel data : prodAddOnList) {   // To set qty 0 when open Dialog
            for (HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean addons : data.getAddonsList()) {
                addons.setSelectedQty(0);
            }
        }

        ingredientAdapter = new HospitalityIngredientAdapter(context, prodAddOnList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        binding.rvAddonList.setLayoutManager(layoutManager);
        binding.rvAddonList.setAdapter(ingredientAdapter);
    }

    double updatePrice() {
        List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean> selectedModifier = modifierAdapter.getItems();
        double amount = 0;
        // costPerProduct = 0;
        if (selectedModifier != null) {
            for (HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean modifier : selectedModifier) {
                if (modifier.isSelected()) {
                    amount = (Double.parseDouble(modifier.getSell_price()) - discPrice) * Integer.parseInt(binding.tvModifierItemCount.getText().toString());
                    //  costPerProduct = Double.parseDouble(modifier.getSell_price());
                }
            }
        }
        for (BaseIngredientsModel items : ingredientAdapter.getItems()) {
            HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean addonsHeader = items.getProductAddonsHeader();
            //  int freeQty = addonsHeader.getFree_modifier_num();
            int freeQty = addonsHeader.getFree_modifier_num() * Integer.parseInt(binding.tvModifierItemCount.getText().toString());
            for (HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean addon : items.getAddonsList()) {
                if (addon.getSelectedQty() > 0) {
                    int selectedQty = addon.getSelectedQty() * Integer.parseInt(binding.tvModifierItemCount.getText().toString());
                    if (freeQty != 0) {
                        if (selectedQty > freeQty) {
                            amount += (Double.parseDouble(addon.getSell_price()) * (selectedQty - freeQty));
                            freeQty = 0;
                        } else {
                            freeQty = freeQty - selectedQty;
                        }
                    } else {
                        amount += (Double.parseDouble(addon.getSell_price()) * (selectedQty - freeQty));
                    }
                }
            }
        }

        binding.tvAddToCart.setText(context.getString(R.string.add_to_cart) + "   " + context.getString(R.string.pound) + String.format("%.2f", amount));
        amtToShow = amount;
        return amount;
    }


    private void createDataForRequest() {
        List<Integer> addon_idsList = new ArrayList<>();  /// To get addon_ids
        List<Integer> addon_qty_json = new ArrayList<>(); /// To get addon_qty_json
        List<AddToCartHospitalityRequestModel.AddOnsBean> listAdonReq = new ArrayList<>(); /// To get adOns list
        String basePrice = null;
        List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean> selectedModifier = modifierAdapter.getItems();
        boolean isSelected = false;
        if (selectedModifier != null) {
            for (HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean modifier : selectedModifier) {
                if (modifier.isSelected()) {
                    isSelected = true;
                    basePrice = modifier.getSell_price();
                }
            }
        }

        for (BaseIngredientsModel items : ingredientAdapter.getItems()) {
            HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean addonsHeader = items.getProductAddonsHeader();
            int freeQty = addonsHeader.getFree_modifier_num();
            for (HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean addon : items.getAddonsList()) {
                Log.e("TAG", addon.getAddon().getName() + " | QTY : " + addon.getSelectedQty() + " | ID : " + addon.getId());
                if (addon.getSelectedQty() > 0) {
                    AddToCartHospitalityRequestModel.AddOnsBean addOnsBean = null;
                    Double amount = 0d;
                    int selectedQty = addon.getSelectedQty();
                    if (freeQty != 0) {
                        if (selectedQty > freeQty) {
                            amount += (Float.parseFloat(addon.getSell_price()) * (selectedQty - freeQty));
                            freeQty = 0;
                        } else {
                            freeQty = freeQty - selectedQty;
                        }
                    } else {
                        amount += (Float.parseFloat(addon.getSell_price()) * selectedQty);
                    }

                    addOnsBean = new AddToCartHospitalityRequestModel.AddOnsBean(
                            String.valueOf(addon.getId()),
                            String.valueOf(addon.getSelectedQty()),
                            addon.getSell_price(),
                            String.valueOf(addon.getAddon().getId()),
                            addon.getAddon().getName(),
                            addon.getGroup_id(),
                            String.format("%.2f", amount)
                    );

                    listAdonReq.add(addOnsBean);  /// For key entry.getKey()
                    addon_qty_json.add(addon.getId() + addon.getSelectedQty());
                    addon_idsList.add(addon.getId());
                }
            }
        }
        String addon_ids = android.text.TextUtils.join(",", addon_idsList);

        double costPerProduct = 0;
        for (int i = 0; i < listAdonReq.size(); i++) {
            costPerProduct = costPerProduct + Double.parseDouble(listAdonReq.get(i).getTot_price());
        }
        costPerProduct = costPerProduct + Double.parseDouble(basePrice);


        AddToCartHospitalityRequestModel requestModel = new AddToCartHospitalityRequestModel
                (reservationId, dataBeans.getMerchant_id(), dataBeans.getVenue_id(), prefManager.getPreference(LOGIN_ID, 0),
                        dataBeans.getId(), modifierId, Integer.parseInt(binding.tvModifierItemCount.getText().toString()),
                        dataBeans == null ? 0 : dataBeans.getOffer_id(), 0,
                        String.valueOf(costPerProduct),
                        basePrice/* String.valueOf(dataBeans.getFinal_sell_price())*/, addon_ids, listAdonReq, addon_qty_json,
                        dataBeans.getmSelectedAllergy(),IS_HOSPITALITY);

        addToCart(requestModel);
    }

    private void addToCart(AddToCartHospitalityRequestModel request) {
        if (isInternetOn(context)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(context);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.hospitality_addCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), request);
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
                                binding.tvAddToCart.setText(context.getString(R.string.added_to_cart) + "   " + context.getString(R.string.pound) + String.format("%.2f", amtToShow));

                                binding.tvAddToCart.setTextColor(context.getResources().getColor(R.color.color_white));
                                if (cartResponseModel.getTotal_carts() > 0) {
                                    binding.fabCart.show();
                                    binding.tvProdCount.setVisibility(View.VISIBLE);
                                    binding.tvProdCount.setText("" + cartResponseModel.getTotal_carts());
                                } else {
                                    binding.fabCart.hide();
                                    binding.tvProdCount.setVisibility(View.GONE);
                                }

                                successActionListner.onSuccessActionListner();
                                if (dataBeans.getRelated_products() != null && dataBeans.getRelated_products().size() > 0) {
                                    new DialogUpsellingProduct(context, venueName, dataBeans.getRelated_products(), request, reservationId, new ModifierAddHospitalityListner() {
                                        @Override
                                        public void onModifierSelect(AddToCartHospitalityRequestModel s) {

                                        }
                                    }, new CancelListner() {
                                        @Override
                                        public void onCancelOrder(int order_id, int order_details_id) {
                                            dismiss();
                                        }
                                    }).show();
                                } else {


                                    DialogUtils.showAlertDialogEndSelf(context, cartResponseModel.getMessage(),false, new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            dismiss();
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });


                                }

                            } else {
                                showSnackBar(binding.getRoot(), cartResponseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(context, httpCode == 401 ? context.getString(R.string.season_expired) : context.getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        onSeasonExpireListener.onSeasonExpire();
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
                    showSnackBar(binding.getRoot(), context.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), context.getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    public void onIngredientSelect(int parentPosition, int childPosition) {
        updatePrice();
    }


    @Override
    public void onModifierSelect(int parentPosition, int childPosition) {
        modifierId = dataBeans.getModifier_list().get(childPosition).getId();
        showModifierThumbnail(dataBeans.getModifier_list(), childPosition);

        updatePrice();
    }

    @Override
    public void onModifierDeselect(int pos_1st, int pos_2nd) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private boolean isValid() {
        List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean> selectedModifier = modifierAdapter.getItems();
        boolean isSelected = false;
        if (selectedModifier != null) {
            for (HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean modifier : selectedModifier) {
                if (modifier.isSelected()) {
                    isSelected = true;
                }
            }
        }
        if (!isSelected) {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            return false;
        }

        for (BaseIngredientsModel items : ingredientAdapter.getItems()) {
            HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean addonsHeader = items.getProductAddonsHeader();
            int addonSelected = 0;
            for (HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean addon : items.getAddonsList()) {
                Log.e("TAG", addon.getAddon().getName() + " | QTY : " + addon.getSelectedQty() + " | ID : " + addon.getId());
                if (addon.getSelectedQty() > 0) {
                    addonSelected++;
                }
            }

            if (addonsHeader.getIs_mandatory() == 1) {
                if (addonSelected < addonsHeader.getChoose_modifier_num()) {
                    String msg = "Please select " + addonsHeader.getChoose_modifier_num() + " modifiers for " + addonsHeader.getAddon().getAddon_category().getCat_name() + ".";
                 //   Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    showMessage(msg);
                    return false;
                }
                if (addonSelected > addonsHeader.getChoose_modifier_num() || addonSelected < addonsHeader.getChoose_modifier_num()) {
                    String msg = "You can select only " + addonsHeader.getChoose_modifier_num() + " modifiers for " + addonsHeader.getAddon().getAddon_category().getCat_name() + ".";
                 //   Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    showMessage(msg);
                    return false;
                }
            }
        }
        return true;
    }

    public void showMessage(String msg) {
        DialogUtils.showAlertDialogEndSelf(context, msg,true, new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {
               // dismiss();
            }

            @Override
            public void onNegativeClick() {

            }
        });

    }
}
