package com.poundland.retail.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.RetailModifierSelectAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.DialogModifierSelectionBinding;
import com.poundland.retail.interfaces.ModifierAddListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.AddToCartComboRequestModel;
import com.poundland.retail.model.requestModel.ProductDetailByModifierRequestModel;
import com.poundland.retail.model.responseModel.ModifierListByProductIdModel;

import java.util.ArrayList;
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

public class DialogRetailModifierSelect extends Dialog implements ModifierSelectionListner, ModifierAddListner {
    private Context context;
    private LayoutInflater inflater;
    private DialogModifierSelectionBinding binding;
    private PrefManager prefManager;
    private ModifierAddListner listner;
    private int productId;
    private String product_name;
    private RetailModifierSelectAdapter adapter;
    private List<ModifierListByProductIdModel.ModifiersBean> modifierList;

    public DialogRetailModifierSelect(Context context, int productId, String product_name, ModifierAddListner listner) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listner = listner;
        prefManager = PrefManager.getInstance(context);
        this.productId = productId;
        this.product_name = product_name;
        modifierList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_modifier_selection, null, true);
        binding.tvApplyButton.setVisibility(View.GONE);
        getModifiers();    //  Get Modifier based on id
        setContentView(binding.getRoot());
        setCancelable(false);
        Window window = getWindow();
        WindowManager.LayoutParams wmlp = getWindow().getAttributes();
        // wmlp.gravity = Gravity.CENTER;
        window.setAttributes(wmlp);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        binding.tvTitle.setText(String.format("%s  %s", product_name, context.getString(R.string.select_variant)));
        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.tvApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<AddToCartComboRequestModel.CartsBean> comboRequestModelList = new ArrayList<>();
                for (int i = 0; i < modifierList.size(); i++) {
                    if (modifierList.get(i).isMChecked) {

                        comboRequestModelList.add(new AddToCartComboRequestModel.CartsBean(modifierList.get(i).getVenue_id(),
                                "" + modifierList.get(i).getMerchant_id(),
                                String.valueOf(prefManager.getPreference(LOGIN_ID, 0)),
                                "" + modifierList.get(i).getProduct_id(),
                                "" + modifierList.get(i).getModifier_id(),
                                "" + modifierList.get(i).getOffer_id(),
                                modifierList.get(i).getQuantity(),
                                modifierList.get(i).getSelling_price(), "", "",
                                "", modifierList.get(i).getSelling_price()));
                    }
                }

                AddToCartComboRequestModel comboRequestModel = new AddToCartComboRequestModel(comboRequestModelList);

                listner.onModifierSelect(comboRequestModel);
                dismiss();


            }
        });
    }

    private void setAdapter() {
        adapter = new RetailModifierSelectAdapter(context, modifierList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        binding.rvModifierList.setLayoutManager(layoutManager);
        binding.rvModifierList.setAdapter(adapter);
    }


    @Override
    public void onModifierSelect(int pos_1st, int pos_2nd) {

    }

    @Override
    public void onModifierDeselect(int pos_1st, int pos_2nd) {
    }

    private void getModifiers() {
        if (isInternetOn(context)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(context);
            if (dialog != null)
                dialog.show();

            ProductDetailByModifierRequestModel requestModel = new ProductDetailByModifierRequestModel(String.valueOf(productId));

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<ModifierListByProductIdModel> call = apiInterface.getModifiersByProductId(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<ModifierListByProductIdModel>() {
                @Override
                public void onResponse(Call<ModifierListByProductIdModel> call, Response<ModifierListByProductIdModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            ModifierListByProductIdModel responseModel = response.body();
                            if (responseModel.isStatus()) {

                                if (responseModel.getModifiers() != null &&
                                        responseModel.getModifiers().size() > 0) {

                                    modifierList.clear();
                                    modifierList.addAll(responseModel.getModifiers());

                                    setAdapter();
                                    binding.tvApplyButton.setVisibility(View.VISIBLE);

                                } else {
                                    modifierList.clear();
                                    setAdapter();
                                }
                            } else {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
                            }


                        } else {
                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(context, httpCode == 401 ? context.getString(R.string.season_expired) : context.getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

                                    if (httpCode == 401) {
                                        logOut((Activity) context);
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
                public void onFailure(Call<ModifierListByProductIdModel> call, Throwable t) {
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
    public void onModifierSelect(AddToCartComboRequestModel s) {

    }
}
