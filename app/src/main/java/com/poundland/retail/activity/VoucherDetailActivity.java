package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityVoucherDetailBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.model.responseModel.GetCustomerVouchersResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SIGN_UP_REQU_MODEL;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class VoucherDetailActivity extends BaseActivity implements View.OnClickListener {
    private ActivityVoucherDetailBinding binding;
    private PrefManager prefManager;
    private VoucherDetailActivity instance=null;
    private GetCustomerVouchersResponseModel.VoucherBean.DataBean dataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_voucher_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        setListeners();
    }

    private void init() {
        instance = this;
        dataBean = getIntent().getExtras().getParcelable(SIGN_UP_REQU_MODEL);
        prefManager = PrefManager.getInstance(this);

        binding.tvVoucherName.setText(dataBean.getVoucher_number());
        binding.tvVenueName.setText  (dataBean.getVenue_name());
        binding.tvValidOnDays.setText(getString(R.string.valid_on)+":["+dataBean.getDays_available()+"]");
        binding.tvExpire.setText(String.format("%s:%s", getString(R.string.expire), dataBean.getEnd_date()));
        binding.tvPriceCount.setText(String.format("%s%s", getString(R.string.pound), dataBean.getAmount()));   ////  getActualPrice();

        Glide.with(instance).load(ApiRequestUrl.BASE_URL + dataBean.getVoucher_qrcode_image()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(binding.ivQrCode);

       /* QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null, Contents.Type.TEXT,
                BarcodeFormat.QR_CODE.toString(), smallerDimension);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            Bitmap overlay = BitmapFactory.decodeResource(context.getResources(), R.drawable.qr_code_resised);   ////  qr_bg_image

            //setting bitmap to image view
            iv_qr_code.setPadding(5, 5, 5, 5);
            iv_qr_code.setImageBitmap(mergeBitmaps(overlay, bitmap));

            //  iv_qr_code.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_my_cart;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String timestamp = extras.getString("timestamp");
                String title = extras.getString("title");
                String message = extras.getString("message");
                String image = extras.getString("image");
                String data = extras.getString("data");

                if (data.equals("DISC_OFFER_NOTIFY")){
                    DialogUtils.offerNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent topStore = new Intent(this, NearByDealsActivity.class);
                        topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                        startActivity(topStore);
                    });
                } else if (!TextUtils.isEmpty(data)){
                    NotificationModel model = new Gson().fromJson(data, NotificationModel.class);
                    DialogUtils.offerNotificationDialog(this, title, message, image, new Gson().fromJson(data, NotificationModel.class), (position, clickId) -> {
                        Intent topProduct = new Intent(this, SpecialOfferDetailsActivity.class);
                        topProduct.putExtra(PRODUCT_ID, "");
                        topProduct.putExtra(OFFER_ID, "");
                        topProduct.putExtra(SPECIAL_OFFER_ID, String.valueOf(model.getSpecial_offer_id()));
                        startActivity(topProduct);
                    });
                } else {
                    DialogUtils.simpleNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent myOrder = new Intent(this, MyOrderActivity.class);
                        startActivity(myOrder);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
