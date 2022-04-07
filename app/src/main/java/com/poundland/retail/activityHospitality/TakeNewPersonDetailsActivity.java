package com.poundland.retail.activityHospitality;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.adapter.TakeNewPersonDetailsListAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityTakeNewPersonDetailsBinding;
import com.poundland.retail.dialog.DialogUtils;
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

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.isValidEmail;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AMOUNT_SEND;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.IS_SMOKING;
import static com.poundland.retail.interfaces.Constants.NO_OF_GUEST;
import static com.poundland.retail.interfaces.Constants.RESERVATION_ID;
import static com.poundland.retail.interfaces.Constants.SLOT_ID;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class TakeNewPersonDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = TakeNewPersonDetailsActivity.class.getName();
    private TakeNewPersonDetailsActivity instance = null;
    private ActivityTakeNewPersonDetailsBinding mBinding;
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
    private TakeNewPersonDetailsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_take_new_person_details);
        instance = this;
        prefManager = PrefManager.getInstance(instance);
        noOfPersonCount = getIntent().getIntExtra(NO_OF_GUEST, 2);
        slotId = getIntent().getIntExtra(SLOT_ID, 0);
        resservation_id = getIntent().getStringExtra(RESERVATION_ID);
        amount = getIntent().getStringExtra(AMOUNT_SEND);
        is_smoking = getIntent().getIntExtra(IS_SMOKING, 0);
        onTakeNewPerson = (OnTakeNewPerson) getIntent().getSerializableExtra("INTERFACE");
        setCheckInData();


        initPersonList();
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
                if (((CheckBox) view).isChecked()) {
                    is_smoking = 1;
                } else is_smoking = 0;
            }
        });
    }

    private void setCheckInData() {
     /*   mBinding.tvVenueName.setText(responseModel.getData().getVenue_name());

        mBinding.tvCheckinTime.setText(formatDateTimeTO_Time(getIntent().getIntExtra("")));
        mBinding.tvCheckinDay.setText(getDaysNameFromYYYY_MM_DD(responseModel.getData().getCheck_in()));
        mBinding.tvDateMonth.setText(formatDateMM(responseModel.getData().getCheck_in()));

        mBinding.tvCheckoutTime.setText(formatDateTimeTO_Time(responseModel.getData().getCheck_out()));
        mBinding.tvCheckoutDay.setText(getDaysNameFromYYYY_MM_DD(responseModel.getData().getCheck_out()));
        mBinding.tvCheckoutDateMonth.setText(formatDateMM(responseModel.getData().getCheck_out()));

        Glide.with(instance).load(ApiRequestUrl.BASE_URL_VENUE + responseModel.getData().getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.default_image))
                .into(mBinding.ivVenueImg);

        */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                if (mBinding.cbTc.isChecked()) {
                    validateAndSend();
                } else {
                    Toast.makeText(instance, getResources().getString(R.string.please_select_tc), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancel:
                finish();
                break;

            case R.id.ll_view_tc:

              /*  DialogBookingTermsCondition bottomSheetDialog = DialogBookingTermsCondition.newInstance(instance,);
                bottomSheetDialog.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
*/
                break;
        }
    }

    private void initPersonList() {
        mAdapter = new TakeNewPersonDetailsListAdapter(instance);
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

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            return false;
        });
    }


    private void validateAndSend() {
        List<ReservationGuestReqModel.GuestBean> mPersonList = new ArrayList<>();
        boolean isAllDone = true;

        int loopPosition = 0;

        for (Map.Entry<Integer, ReservationGuestReqModel.GuestBean> model : mAdapter.getAdapterItems().entrySet()) {
            ReservationGuestReqModel.GuestBean data = model.getValue();

            if (!HelperClass.isValidName(data.getGuestfname())) {
                mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                Toast.makeText(instance, getResources().getString(R.string.please_enter_a_valid_first_name), Toast.LENGTH_SHORT).show();
                isAllDone = false;
                break;
            } else if (loopPosition == 0 && validateAllField) {

                if (!HelperClass.isValidName(data.getGuestlname())) {
                    mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                    Toast.makeText(instance, getResources().getString(R.string.please_enter_a_valid_last_name), Toast.LENGTH_SHORT).show();
                    isAllDone = false;
                    break;
                } else if (data.getGuestmobile() == null || TextUtils.isEmpty(data.getGuestmobile())) {
                    mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                    Toast.makeText(instance, getResources().getString(R.string.please_enter_mobile_number), Toast.LENGTH_SHORT).show();
                    isAllDone = false;
                    break;
                } else if (data.getGuestEmail() == null || TextUtils.isEmpty(data.getGuestEmail()) || !isValidEmail(data.getGuestEmail())) {
                    mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                    Toast.makeText(instance, getResources().getString(R.string.please_enter_a_valid_email), Toast.LENGTH_SHORT).show();
                    isAllDone = false;
                    break;
                }
            } else if (loopPosition > 0 && validateAllField) {
                if (!TextUtils.isEmpty(data.getGuestlname()) && !HelperClass.isValidName(data.getGuestlname())) {
                    mBinding.personListView.getLayoutManager().scrollToPosition(loopPosition);
                    Toast.makeText(instance, getResources().getString(R.string.please_enter_a_valid_last_name), Toast.LENGTH_SHORT).show();
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
                    String.valueOf(slotId), is_smoking, mBinding.etMessage.getText().toString(),
                    mPersonList);
            venue_reservation_addguest(mItems);
        }
    }

    private void venue_reservation_addguest(ReservationGuestReqModel mItems) {
        if (isInternetOn(instance)) {
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
                                    Intent intent = new Intent(instance, MyCartReservationActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    DialogUtils.reservationOrderSuccesDialog(instance, responseModel.getMessage(),
                                            true, responseModel.getReservation_data().getAmount(), prefManager.getPreference(EMAIL, ""),
                                            String.valueOf(responseModel.getReservation_data().getReservation_id()), "", responseModel.getReservation_data().getCheck_in(), responseModel.getReservation_data().getTotal_guest(),
                                            responseModel.getReservation_data().getReservation_id(), new OnDialogClickListener() {
                                                @Override
                                                public void onPositiveClick() {
                                                    prefManager.savePreference(VENUE_ID_IN_CART, "");
                                                    finish();
                                                    Intent intent = new Intent(instance, MyOrderReservationActivity.class);
                                                    startActivity(intent);
                                                }

                                                @Override
                                                public void onNegativeClick() {

                                                }
                                            });
                                }


                            } else {
                                showSnackBar(mBinding.getRoot(), getString(R.string.something_went_wrong));
                            }
                        } else {
                            showSnackBar(mBinding.getRoot(), getString(R.string.msg_please_try_later));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AddGuestResponseModel> call, Throwable t) {
                    showSnackBar(mBinding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(mBinding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

}