package com.poundland.retail.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartReservationActivity;
import com.poundland.retail.activityHospitality.MyOrderReservationActivity;
import com.poundland.retail.activityHospitality.adapter.TakeNewPersonDetailsListAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.DialogTakeNewPersonDetailsBinding;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.OnTakeNewPerson;
import com.poundland.retail.model.requestModel.ReservationGuestReqModel;
import com.poundland.retail.model.responseModel.AddGuestResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.formatDateMM;
import static com.poundland.retail.appUtils.HelperClass.formatDateTimeTO_Time;
import static com.poundland.retail.appUtils.HelperClass.getDaysNameFromYYYY_MM_DD;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.isValidEmail;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class TakeNewPersonDetailsDialog extends Dialog implements View.OnClickListener, OnDialogClickListener {
    private static final String TAG = TakeNewPersonDetailsDialog.class.getName();
    private Context mContext;
    private Activity mActivity;
    private DialogTakeNewPersonDetailsBinding mBinding;
    private PrefManager prefManager;
    private boolean validateAllField = true;
    private int position = -1;
    private int noOfPersonCount = 1;
    private ArrayList<ReservationGuestReqModel.GuestBean> mPersonData;
    private int slotId;
    private String resservation_id;
    private String amount;
    private OnTakeNewPerson onTakeNewPerson;
    private AddGuestResponseModel responseModel;
    private int is_smoking;
    private String check_in;
    private String check_out;
    private String venueImage;
    private String venueName;
    private TakeNewPersonDetailsListAdapter mAdapter;

    public TakeNewPersonDetailsDialog(Activity mActivity, @NonNull Context context, int noFGuest, int slotId, String resservation_id,
                                      String amount, int is_smoking, String check_in, String check_out, String venueImage, String venueName, OnTakeNewPerson onTakeNewPerson) {
        super(context);
        prefManager = PrefManager.getInstance(mContext);
        this.mActivity = mActivity;
        this.noOfPersonCount = noFGuest;
        this.slotId = slotId;
        this.amount = amount;
        this.is_smoking = is_smoking;
        this.resservation_id = resservation_id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.venueImage = venueImage;
        this.venueName = venueName;
        this.onTakeNewPerson = onTakeNewPerson;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_take_new_person_details, null, false);
        setContentView(mBinding.getRoot());
        setCancelable(true);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 10);
        getWindow().setBackgroundDrawable(inset);
        initPersonList();
        setData();
        if (is_smoking == 0) {
            mBinding.cbSmoking.setVisibility(View.GONE);
            mBinding.tvSmoking.setVisibility(View.GONE);
        } else {
            mBinding.cbSmoking.setVisibility(View.VISIBLE);
            mBinding.tvSmoking.setVisibility(View.VISIBLE);
        }


        mBinding.btnSubmit.setOnClickListener(this);
        mBinding.btnCancel.setOnClickListener(this);
        mBinding.llViewTc.setOnClickListener(this);

        mBinding.cbSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (((CheckBox) view).isChecked()) {
                    is_smoking = 1;
                } else is_smoking = 0;*/
            }
        });
    }

    private void setData() {
        mBinding.tvVenueName.setText(venueName);
        mBinding.tvCheckinTime.setText(formatDateTimeTO_Time(check_in));
        mBinding.tvCheckinDay.setText(getDaysNameFromYYYY_MM_DD(check_in));
        mBinding.tvDateMonth.setText(formatDateMM(check_in));
        mBinding.tvCheckoutTime.setText(formatDateTimeTO_Time(check_out));
        mBinding.tvCheckoutDay.setText(getDaysNameFromYYYY_MM_DD(check_out));
        mBinding.tvCheckoutDateMonth.setText(formatDateMM(check_out));

        Glide.with(getContext()).load(venueImage).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(mBinding.ivVenueImg);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if (mBinding.cbTc.isChecked()) {
                    validateAndSend();
                } else {
                    Toast.makeText(mContext, getContext().getResources().getString(R.string.please_select_tc), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancel:
                dismiss();
                break;

            case R.id.ll_view_tc:

                DialogBookingTermsCondition bottomSheetDialog = DialogBookingTermsCondition.newInstance(getContext(), this);
                bottomSheetDialog.show(((AppCompatActivity) mActivity).getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

                break;
        }
    }

    private void initPersonList() {
        mAdapter = new TakeNewPersonDetailsListAdapter(mContext);
        mBinding.personListView.setAdapter(mAdapter);

        if (mPersonData != null && !mPersonData.isEmpty()) {
            mAdapter.addItem(mPersonData);
        } else {
            if (noOfPersonCount == 0) {
                mAdapter.addItem(new ReservationGuestReqModel.GuestBean());
            } else {
                for (int i = 0; i < noOfPersonCount; i++) {
                    mAdapter.addItem(new ReservationGuestReqModel.GuestBean());
                }
            }
        }

        mBinding.personListView.setOnTouchListener((v, event) -> {

            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            return false;
        });
    }


    private void validateAndSend() {
        /*if (mCallback == null || mAdapter == null) {
            return;
        }*/
        List<ReservationGuestReqModel.GuestBean> mPersonList = new ArrayList<>();
        boolean isAllDone = true;

        int loopPosition = 0;

        for (Map.Entry<Integer, ReservationGuestReqModel.GuestBean> model : mAdapter.getAdapterItems().entrySet()) {
            ReservationGuestReqModel.GuestBean data = model.getValue();

            if (!HelperClass.isValidName(data.getGuestfname())) {
                mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                Toast.makeText(mContext, getContext().getResources().getString(R.string.please_enter_a_valid_first_name), Toast.LENGTH_SHORT).show();
                isAllDone = false;
                break;
            } else if (loopPosition == 0 && validateAllField) {

                if (!HelperClass.isValidName(data.getGuestlname())) {
                    mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                    Toast.makeText(mContext, getContext().getResources().getString(R.string.please_enter_a_valid_last_name), Toast.LENGTH_SHORT).show();
                    isAllDone = false;
                    break;
                } else if (data.getGuestmobile() == null || TextUtils.isEmpty(data.getGuestmobile())) {
                    mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                    Toast.makeText(mContext, getContext().getResources().getString(R.string.please_enter_mobile_number), Toast.LENGTH_SHORT).show();
                    isAllDone = false;
                    break;
                } else if (data.getGuestEmail() == null || TextUtils.isEmpty(data.getGuestEmail()) || !isValidEmail(data.getGuestEmail())) {
                    mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                    Toast.makeText(mContext, getContext().getResources().getString(R.string.please_enter_a_valid_email), Toast.LENGTH_SHORT).show();
                    isAllDone = false;
                    break;
                }
            } else if (loopPosition > 0 && validateAllField) {
                if (!TextUtils.isEmpty(data.getGuestlname()) && !HelperClass.isValidName(data.getGuestlname())) {
                    mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                    Toast.makeText(mContext, getContext().getResources().getString(R.string.please_enter_a_valid_last_name), Toast.LENGTH_SHORT).show();
                    isAllDone = false;
                    break;
                }
            }
            ReservationGuestReqModel.GuestBean mItems = new ReservationGuestReqModel.GuestBean();
            mItems.setGuestfname(TextUtils.isEmpty(data.getGuestfname()) ? "" : data.getGuestfname());
            mItems.setGuestlname(TextUtils.isEmpty(data.getGuestlname()) ? "" : data.getGuestlname());
            mItems.setGuestmobile((TextUtils.isEmpty(data.getGuestmobile()) ? "" : data.getGuestmobile()));
            mItems.setGuestEmail((TextUtils.isEmpty(data.getGuestEmail()) ? "" : data.getGuestEmail()));
            mItems.setSame_house_hold(data.getSame_house_hold());
            mPersonList.add(mItems);
            loopPosition++;
        }
        if (isAllDone) {
            ReservationGuestReqModel mItems = new ReservationGuestReqModel(resservation_id,
                    prefManager.getPreference(EMAIL, ""), prefManager.getPreference(FIRST_NAME, ""),
                    String.valueOf(slotId), mBinding.cbSmoking.isChecked() ? 1 : 0, mBinding.etMessage.getText().toString(),
                    mPersonList);
            venue_reservation_addguest(mItems);
        }
    }

    private void venue_reservation_addguest(ReservationGuestReqModel mItems) {
        if (isInternetOn(mContext)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            // AddGuestRequestModel requestModel = new AddGuestRequestModel(mItems);
            Call<AddGuestResponseModel> call = apiInterface.venue_reservation_addguest(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), mItems);
            call.enqueue(new Callback<AddGuestResponseModel>() {
                @Override
                public void onResponse(Call<AddGuestResponseModel> call, Response<AddGuestResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            responseModel = response.body();
                            if (responseModel.isStatus()) {
                                // toast(mContext, responseModel.getMessage());
                                onTakeNewPerson.onTakeNewPersonCallBack(true, mItems);
                                if (Double.parseDouble(amount) > 0) {
                                    Intent intent = new Intent(getContext(), MyCartReservationActivity.class);
                                    getContext().startActivity(intent);
                                    dismiss();
                                } else {
                                    DialogUtils.reservationOrderSuccesDialog(mContext, responseModel.getMessage(),
                                            true, responseModel.getReservation_data().getAmount(), prefManager.getPreference(EMAIL, ""),
                                            String.valueOf(responseModel.getReservation_data().getReservation_id()), venueImage, responseModel.getReservation_data().getCheck_in(), responseModel.getReservation_data().getTotal_guest(),
                                            responseModel.getReservation_data().getReservation_id(), new OnDialogClickListener() {
                                                @Override
                                                public void onPositiveClick() {
                                                    prefManager.savePreference(VENUE_ID_IN_CART, "");
                                                    dismiss();
                                                    Intent intent = new Intent(getContext(), MyOrderReservationActivity.class);
                                                    getContext().startActivity(intent);
                                                }

                                                @Override
                                                public void onNegativeClick() {

                                                }
                                            });
                                }


                            } else {
                                showSnackBar(mBinding.getRoot(), mContext.getString(R.string.something_went_wrong));
                            }
                        } else {
                            showSnackBar(mBinding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AddGuestResponseModel> call, Throwable t) {
                    showSnackBar(mBinding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(mBinding.getRoot(), mContext.getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    public void onPositiveClick() {
        mBinding.cbTc.setChecked(true);
    }

    @Override
    public void onNegativeClick() {

    }
}
