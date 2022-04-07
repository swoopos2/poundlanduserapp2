package com.poundland.retail.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.ReservationBookingDateAdapter;
import com.poundland.retail.adapter.ReservationBookingTimeAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.LayoutDialogReservatonTimeDateEntryBinding;
import com.poundland.retail.interfaces.OnDateSelectListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.OnSeasonExpireListener;
import com.poundland.retail.interfaces.OnTakeNewPerson;
import com.poundland.retail.model.ReservationBookingDate;
import com.poundland.retail.model.requestModel.CancelOrderRequestModel;
import com.poundland.retail.model.requestModel.ReservationGuestReqModel;
import com.poundland.retail.model.requestModel.ReservationSlotAvailRequestModel;
import com.poundland.retail.model.requestModel.VenueReservatioRequestModel;
import com.poundland.retail.model.responseModel.ReservationOptionDataResponseModel;
import com.poundland.retail.model.responseModel.ReservationSlotAvailResponseModel;
import com.poundland.retail.model.responseModel.VenueReservationResponseModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.toast;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;


public class DialogReservatioTimeDateEntry extends BottomSheetDialogFragment implements View.OnClickListener, OnDateSelectListner {
    private Context context;
    private Activity mActivity;
    private LayoutInflater inflater;
    private LayoutDialogReservatonTimeDateEntryBinding binding;
    private PrefManager prefManager;
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private int tymId;
    private String tymText;
    private String venue_id;
    private ReservationOptionDataResponseModel reservationOptionDataResponseModel;
   // private ReservationSlotAvailResponseModel reservationSlotResponse;
    private String dateOfBooking;
    private int slotId;
    private int noFGuest = 1;
    private int resDay;
    private String slotIdPrice = "";
    private String searchDate = "";
    private Date currDate;
    private DateFormat formate_HHmm = new SimpleDateFormat("HH:mm");
    private String localTime;
    private ReservationBookingDateAdapter bookingDateAdapter;
    private ReservationBookingTimeAdapter bookingTimeAdapter;
    private String tomorrowAsString;
    private String venueImage;
    private String venueName;
    private String addressVenue;
    private OnSeasonExpireListener onSeasonExpireListener;

    public static DialogReservatioTimeDateEntry newInstance(Activity mActivity, Context context, String tymText, String venue_id, String searchDate, int searchGuest, int slotId,
                                                            String venueImage, String addressVenue, String venueName, OnSeasonExpireListener onSeasonExpireListener) {
        DialogReservatioTimeDateEntry fragment = new DialogReservatioTimeDateEntry();
        fragment.inflater = LayoutInflater.from(context);
        fragment.prefManager = PrefManager.getInstance(context);
        fragment.context = context;
        fragment.mActivity = mActivity;
        fragment.tymText = tymText;
        fragment.venue_id = venue_id;
        fragment.searchDate = searchDate;
        fragment.venueImage = venueImage;
        fragment.addressVenue = addressVenue;
        fragment.venueName = venueName;
        fragment.noFGuest = searchGuest;
        fragment.slotId = slotId;
        fragment.onSeasonExpireListener = onSeasonExpireListener;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_dialog_reservaton_time_date_entry, null, false);
        dialog.setContentView(binding.getRoot());
        ((View) binding.getRoot().getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        // BottomSheetDialog d = (BottomSheetDialog) dialog;
        FrameLayout bottomSheet = (FrameLayout) dialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);

        initDateList();

        Glide.with(context).load(venueImage).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(binding.ivVenueImg);
        binding.tvVenueName.setText(venueName);
        binding.tvVenueAddress.setText(addressVenue);

        binding.rlClose.setOnClickListener(this);
        binding.btnMinus.setOnClickListener(this);
        binding.btnPlush.setOnClickListener(this);
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        resDay = HelperClass.getDayOfWeekInInt(HelperClass.formatddMMMyyyy_EEE_dd_MMM(searchDate).substring(0, 3));
        reservation_option_data(resDay); // if searchDate not available  use Calendar.getInstance().get(Calendar.DAY_OF_WEEK)  instead of resday

        currDate = calendar.getTime();
        tomorrowAsString = yearFormat.format(currDate);  /// gives year like ...2021
        localTime = formate_HHmm.format(currDate);  //  gives time
        binding.tvGuestCount.setText(String.format("%d", noFGuest));
        dateOfBooking = HelperClass.formatDateyMMdd(HelperClass.formatddMMMyyyy_EEE_dd_MMM(searchDate) + " " + tomorrowAsString);

        binding.tvBookIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bookingDateAdapter.getSelectedDate().isEnable()) {
                    if (bookingTimeAdapter.getSelectedTime() != null) {
                        ReservationOptionDataResponseModel.DataBean.ReservationSlotBean selectedTime = bookingTimeAdapter.getSelectedTime();
                        if (selectedTime != null) {
                            slotId = selectedTime.getId();
                            slotIdPrice = selectedTime.getPrice();
                        } else {
                            slotId = 0;
                            toast(context, context.getString(R.string.time_not_available));
                            return;
                        }
                    } else {
                        slotId = 0;
                        toast(context, context.getString(R.string.time_not_available));
                        return;
                    }

                    if (reservationOptionDataResponseModel.getData().getVenue() != null && reservationOptionDataResponseModel.getData().getVenue().getIs_booking_allow() == 1) {
                        if (dateOfBooking != null) {
                            if (resDay != 0) {
                                if (slotId != 0) {
                                    reservation_slot_available();
                                } else
                                    toast(context, context.getString(R.string.please_select_time));
                            } else
                                toast(context, context.getString(R.string.please_select_time));
                        } else
                            toast(context, context.getString(R.string.please_select_date));
                    } else
                        toast(context, context.getString(R.string.booking_not_allowed));

                } else {
                    toast(context, context.getString(R.string.please_select_available_date));
                }
            }
        });

    }

    private void initDateList() {
        bookingDateAdapter = new ReservationBookingDateAdapter(getContext(), this);
        binding.listDate.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.listDate.setAdapter(bookingDateAdapter);

        bookingTimeAdapter = new ReservationBookingTimeAdapter(getContext());
        binding.listTimeSlot.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.listTimeSlot.setAdapter(bookingTimeAdapter);

        bookingDateAdapter.setSelectedDate(searchDate);
        bookingTimeAdapter.setSelectedTime(null);

    }

    private void reservation_option_data(int i) {
        binding.layoutProgressBar.setVisibility(View.VISIBLE);
        if (isInternetOn(context)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            CancelOrderRequestModel requestModel = new CancelOrderRequestModel(venue_id, i);
            Call<ReservationOptionDataResponseModel> call = apiInterface.reservation_option_data(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<ReservationOptionDataResponseModel>() {
                @Override
                public void onResponse(Call<ReservationOptionDataResponseModel> call, Response<ReservationOptionDataResponseModel> response) {
                    binding.layoutProgressBar.setVisibility(View.GONE);
                    try {
                        if (response.isSuccessful()) {
                            reservationOptionDataResponseModel = response.body();
                            // bookingDateAdapter.clear();
                            bookingDateAdapter.addItems(creatingBookingDate(reservationOptionDataResponseModel.getData().getVenue().getMax_booking_duration(), reservationOptionDataResponseModel.getReservationcloseday()));
                            bookingTimeAdapter.clear();
                            bookingTimeAdapter.addItems(creatingTimeSlot(reservationOptionDataResponseModel.getData().getReservation_slot(), reservationOptionDataResponseModel.getData().getVenue().getDefault_reservation_length()));

                        } else {
                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(context, httpCode == 401 ? context.getString(R.string.season_expired) : context.getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

                                    if (httpCode == 401) {
                                        onSeasonExpireListener.onSeasonExpire();
                                        dismiss();
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
                public void onFailure(Call<ReservationOptionDataResponseModel> call, Throwable t) {
                    toast(context, context.getString(R.string.msg_please_try_later));
                    binding.layoutProgressBar.setVisibility(View.GONE);
                }
            });
        } else {
            toast(context, context.getString(R.string.no_internet_available_msg));
            binding.layoutProgressBar.setVisibility(View.GONE);

        }
    }

    private void reservation_slot_available() {
        if (isInternetOn(context)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(context);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            ReservationSlotAvailRequestModel requestModel = new ReservationSlotAvailRequestModel(String.valueOf(noFGuest),
                    dateOfBooking, slotId, resDay, venue_id);
            Call<ReservationSlotAvailResponseModel> call = apiInterface.reservation_slotIsAvailable(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<ReservationSlotAvailResponseModel>() {
                @Override
                public void onResponse(Call<ReservationSlotAvailResponseModel> call, Response<ReservationSlotAvailResponseModel> response) {

                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            //reservationSlotResponse = response.body();
                            if (response.body().isStatus()) {
                                venue_reservation();
                            } else {
                                toast(context, response.body().getMessage());
                            }
                        } else {
                            toast(context, context.getString(R.string.msg_please_try_later));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ReservationSlotAvailResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    toast(context, context.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            toast(context, context.getString(R.string.no_internet_available_msg));

        }
    }

    private void takePersonDetails(int reservation_id, String amount, int is_smoking, String check_in, String check_out) {
        new TakeNewPersonDetailsDialog(mActivity, context, noFGuest, slotId,
                String.valueOf(reservation_id), amount, is_smoking,check_in,check_out,venueImage,venueName, new OnTakeNewPerson() {
            @Override
            public void onTakeNewPersonCallBack(boolean clickType, ReservationGuestReqModel reservationGuest) {
                dismiss();
            }
        }).show();
    }

   /* private static class OnCreateListenerImpl implements OnTakeNewPerson {
        @Override
        public void onTakeNewPersonCallBack(boolean clickType, ReservationGuestReqModel reservationGuest) {
            dismiss();
            Log.d("pltk", "dfgbfdgh");
        }
    }*/

    private void venue_reservation() {
        if (isInternetOn(context)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(context);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            VenueReservatioRequestModel requestModel = new VenueReservatioRequestModel(noFGuest, venue_id, dateOfBooking,
                    String.valueOf(slotId), String.valueOf(reservationOptionDataResponseModel.getData().getVenue().getDefault_reservation_length())
                    , String.valueOf(reservationOptionDataResponseModel.getData().getVenue().getRestaurant_capacity()),
                    prefManager.getPreference(LOGIN_ID, 0),
                    String.valueOf(reservationOptionDataResponseModel.getData().getVenue().getMax_guest()),
                    prefManager.getPreference(EMAIL, ""), prefManager.getPreference(FIRST_NAME, ""), "LOCAL",
                    prefManager.getPreference(CONTACT_NO, ""));
            Call<VenueReservationResponseModel> call = apiInterface.venue_reservation(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<VenueReservationResponseModel>() {
                @Override
                public void onResponse(Call<VenueReservationResponseModel> call, Response<VenueReservationResponseModel> response) {

                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            VenueReservationResponseModel venueReservationResponseModel = response.body();
                            if (venueReservationResponseModel.isStatus()) {
                                takePersonDetails(venueReservationResponseModel.getReservation_id(),
                                        venueReservationResponseModel.getReservation_data().getAmount(),
                                        venueReservationResponseModel.getReservation_data().getIs_smoking_allow(),
                                        venueReservationResponseModel.getReservation_data().getCheck_in(),
                                        venueReservationResponseModel.getReservation_data().getCheck_out());
                            } else {
                                toast(context, venueReservationResponseModel.getMessage());
                            }
                        } else {

                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(context, httpCode == 401 ? context.getString(R.string.season_expired) : context.getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

                                    if (httpCode == 401) {
                                        onSeasonExpireListener.onSeasonExpire();
                                        dismiss();
                                        // logOut((Activity) context.getApplicationContext());
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
                public void onFailure(Call<VenueReservationResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    toast(context, context.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            toast(context, context.getString(R.string.no_internet_available_msg));

        }
    }

    public List<ReservationOptionDataResponseModel.DataBean.ReservationSlotBean> creatingTimeSlot(List<ReservationOptionDataResponseModel.DataBean.ReservationSlotBean> reservation_slot, int reservationLength) {
        List<ReservationOptionDataResponseModel.DataBean.ReservationSlotBean> reservationSlots = new ArrayList<>();
        boolean isTime = false;
        if (reservation_slot != null) {
            for (int i = 0; i < reservation_slot.size(); i++) {

                if (HelperClass.getComparedDate(dateOfBooking)) {
                    if (HelperClass.getCheckTimings(localTime, HelperClass.formatHrMinSec_TO_hrMin(reservation_slot.get(i).getTime()).substring(0, 5))) {
                        reservationSlots.add(reservation_slot.get(i));
                    }
                } else {
                    reservationSlots.add(reservation_slot.get(i));
                }

            }
        }
        for (ReservationOptionDataResponseModel.DataBean.ReservationSlotBean data : reservation_slot) {
            if (HelperClass.formatHrMinSec_TO_hrMin(data.getTime()).equals(tymText.substring(0, 5))) {

                if (HelperClass.getComparedDate(dateOfBooking)) {
                    if (HelperClass.getCheckTimings(localTime, HelperClass.formatHrMinSec_TO_hrMin(data.getTime()).substring(0, 5))) {
                        isTime = true;
                    }
                } else isTime = true;

            }
        }

        if (isTime) {
            bookingTimeAdapter.setSelectedTime(tymText);
        } else {
            bookingTimeAdapter.setSelectedTime(null);
        }
        return reservationSlots;
    }

    public List<ReservationBookingDate> creatingBookingDate(int max_booking_duration, List<ReservationOptionDataResponseModel.ReservationclosedayBean> reservationcloseday) {
        DateFormat dateFormat = new SimpleDateFormat("EEE dd MMM");
        List<ReservationBookingDate> reservationBookingDates = new ArrayList<>();
        int max_booking_durationIn = max_booking_duration;

        for (int i = 0; i < max_booking_durationIn; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, i);
            Date tomorrow = calendar.getTime();
            String tomorrowAsString = dateFormat.format(tomorrow);

            boolean isEnable = true;
            for (int j = 0; j < reservationcloseday.size(); j++) {
                if (tomorrowAsString.contains(HelperClass.getDayOfWeekString(reservationcloseday.get(j).getDay_id()))) {
                    max_booking_durationIn = max_booking_durationIn + 1;
                    isEnable = false;
                }
            }
            ReservationBookingDate bookingDate = new ReservationBookingDate(calendar.getTime(), isEnable);
            reservationBookingDates.add(bookingDate);
        }
        return reservationBookingDates;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_minus:
                if (Integer.parseInt(binding.tvGuestCount.getText().toString()) > 1) {
                    noFGuest = (Integer.parseInt(binding.tvGuestCount.getText().toString()) - 1);
                    binding.tvGuestCount.setText(String.valueOf(noFGuest));
                }
                break;
            case R.id.btn_plush:
                if (reservationOptionDataResponseModel.getData().getVenue().getMax_guest() > Integer.parseInt(binding.tvGuestCount.getText().toString())) {
                    noFGuest = (Integer.parseInt(binding.tvGuestCount.getText().toString()) + 1);
                    binding.tvGuestCount.setText(String.valueOf(noFGuest));
                }
                break;
            case R.id.rl_close:
                dismiss();
                break;
        }
    }

    @Override
    public void onDrawerSelect(int position, String clickId) {
        Date aa = HelperClass.formatDateIntoString(clickId, HelperClass.dayText);
        dateOfBooking = HelperClass.formatDateEEEtype(clickId);
        bookingDateAdapter.setSelectedDate(null);
        // bookingTimeAdapter.setSelectedTime(tymText);
        resDay = HelperClass.getDayOfWeekInInt(HelperClass.formatDate(aa, HelperClass.dayText));
        reservation_option_data(HelperClass.getDayOfWeekInInt(HelperClass.formatDate(aa, HelperClass.dayText)));
    }
}



 /*binding.tvRateThisDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    if (reservationOptionDataResponseModel != null)
                        setSpinnerForDateCopy(reservationOptionDataResponseModel.getData().getVenue().getMax_booking_duration(), reservationOptionDataResponseModel.getReservationcloseday());
                    isPopup = true;
                }
            }
        });

        binding.tvDob.setText(HelperClass.formatddMMMyyyy_EEE_dd_MMM(searchDate));
        binding.rlSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    if (reservationOptionDataResponseModel != null)
                        setSpinnerForDateCopy(reservationOptionDataResponseModel.getData().getVenue().getMax_booking_duration(), reservationOptionDataResponseModel.getReservationcloseday());
                    isPopup = true;
                }
            }
        });*/