package com.poundland.retail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.activityHospitality.adapter.UpsellingProductAdapter;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.DialogUpsellingProductBinding;
import com.poundland.retail.interfaces.CancelListner;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierAddHospitalityListner;
import com.poundland.retail.model.requestModel.AddToCartHospitalityRequestModel;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;

import java.util.List;

public class DialogUpsellingProduct extends Dialog implements DrawerListner {
    private Context mContext;
    private LayoutInflater inflater;
    private DialogUpsellingProductBinding binding;
    private PrefManager prefManager;
    private ModifierAddHospitalityListner listner;
    private CancelListner cancelListner;
    private String venue;
    private List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.RelatedProductsBean> relatedProducts;
    private AddToCartHospitalityRequestModel requestModel;
    private String reservationId;

    public DialogUpsellingProduct(Context context, String venue, List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.RelatedProductsBean> relatedProducts, AddToCartHospitalityRequestModel requestModel, String reservationId, ModifierAddHospitalityListner listner, CancelListner cancelListner) {
        super(context);
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        prefManager = PrefManager.getInstance(context);
        this.listner = listner;
        this.venue = venue;
        this.requestModel = requestModel;
        this.reservationId = reservationId;
        this.relatedProducts = relatedProducts;
        this.cancelListner = cancelListner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_upselling_product, null, true);
        // binding.tvAddToCart.setVisibility(View.GONE);
        setContentView(binding.getRoot());
        setCancelable(false);

        if (this.getWindow() != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((AppCompatActivity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int displayWidth = displayMetrics.widthPixels;
            int displayHeight = displayMetrics.heightPixels;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(this.getWindow().getAttributes());
            int dialogWindowWidth = (int) (displayWidth * 0.96f);
            int dialogWindowHeight = (int) (displayHeight * 0.4f);
            layoutParams.width = dialogWindowWidth;
//        layoutParams.height = dialogWindowHeight;
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            this.getWindow().setAttributes(layoutParams);
            this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //////////////////////////////////////////
            // getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
            InsetDrawable inset = new InsetDrawable(back, 15);
            getWindow().setBackgroundDrawable(inset);

        }

        if (relatedProducts != null) {
            setAdapter();
        }
        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelListner.onCancelOrder(0, 0);
                dismiss();
            }
        });
        binding.tvGoToItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancelListner.onCancelOrder(0, 0);
                dismiss();


            }
        });

        binding.tvGoToCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelListner.onCancelOrder(0, 0);
                Intent intentH = new Intent(mContext, MyCartHospitalityActivity.class);
                mContext.startActivity(intentH);
                dismiss();


            }
        });
    }

    private void setAdapter() {
        UpsellingProductAdapter adapter = new UpsellingProductAdapter(mContext, relatedProducts, venue, requestModel, reservationId, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        binding.rvUpsellingList.setLayoutManager(layoutManager);
        binding.rvUpsellingList.setAdapter(adapter);
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {
        //  addToCart(position);
    }


}
