package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.LayoutCellMyOrderReservationBinding;
import com.poundland.retail.databinding.LayoutCellMyOrderReservationNewBinding;
import com.poundland.retail.dialog.DialogAllGuest;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.MyOrderReservationResponseModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.poundland.retail.interfaces.Constants.CANCEL_ORDER;
import static com.poundland.retail.interfaces.Constants.NEW_BOOKING;
import static com.poundland.retail.interfaces.Constants.ORDER_FOOD;
import static com.poundland.retail.interfaces.Constants.SELF_CHECKIN;
import static com.poundland.retail.interfaces.Constants.YYYY_MM_dd_HH_mm_ss;

public class MyOrderReservationAdapter extends RecyclerView.Adapter<MyOrderReservationAdapter.ViewResource> {

    private Context mContext;
    private List<MyOrderReservationResponseModel.ReservationsBean.DataBean> mItem;
    private LayoutInflater mInflater;
    private SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss);
    private DrawerListner drawerListner;
    public LayoutCellMyOrderReservationBinding binding;

    public MyOrderReservationAdapter(Context mContext, List<MyOrderReservationResponseModel.ReservationsBean.DataBean> orderList,
                                     DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mItem = orderList;
        this.drawerListner = drawerListner;
    }


   /* public void addItems(List<MyOrderReservationResponseModel.ReservationsBean.DataBean> mItem) {
        this.mItem = mItem;
        notifyItemInserted(this.mItem.size());
    }*/


    @NonNull
    @Override
    public MyOrderReservationAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_my_order_reservation_new, parent, false);
        return new MyOrderReservationAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderReservationAdapter.ViewResource holder, int position) {

        MyOrderReservInnerAdapter myOrderNewAdapter = new MyOrderReservInnerAdapter(mContext, mItem.get(position).getOrders_masters());
        holder.binding.rvOrderMaster.setLayoutManager(new WrapContentLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.binding.rvOrderMaster.setAdapter(myOrderNewAdapter);

        holder.binding.llOrderFood.setVisibility(View.VISIBLE);
        holder.binding.llCancelOrder.setVisibility(View.VISIBLE);
        holder.binding.llCheckinDetails.setVisibility(View.VISIBLE);
        holder.binding.cvQrImage.setVisibility(View.VISIBLE);

        holder.binding.tvCancelOrder.setVisibility(View.GONE);
        holder.binding.tvSelfCheckIn.setVisibility(View.GONE);

        holder.binding.tvVenueName.setText(mItem.get(position).getVenue().getVenue_name());
        holder.binding.tvVenueAddress.setText(mItem.get(position).getVenue().getAddress_1());
        holder.binding.tvDate.setText(HelperClass.formatddMMMMyyyyDayName(mItem.get(position).getCreated_at()) + ", " + HelperClass.formatDateTimeTO_Time(mItem.get(position).getCreated_at()));
        holder.binding.tvBookingNo.setText(mItem.get(position).getReservation_id());
        holder.binding.tvAmount.setText(mContext.getResources().getString(R.string.pound) + mItem.get(position).getAmount());
        holder.binding.tvGuestCount.setText(mItem.get(position).getTotal_guest() + " Guest");

        holder.binding.tvCheckinDay.setText(HelperClass.getDaysName(mItem.get(position).getCheck_in()));
        holder.binding.tvCheckinDate.setText(HelperClass.getDateOnly(mItem.get(position).getCheck_in()));
        holder.binding.tvCheckinMonth.setText(HelperClass.getMonthOnly(mItem.get(position).getCheck_in()));
        holder.binding.tvCheckinYear.setText(HelperClass.getYearOnly(mItem.get(position).getCheck_in()));
        holder.binding.tvCheckinTime.setText(HelperClass.formatDateTimeTO_Time(mItem.get(position).getCheck_in()));

        holder.binding.tvCheckoutDay.setText(HelperClass.getDaysName(mItem.get(position).getCheck_out()));
        holder.binding.tvCheckoutDate.setText(HelperClass.getDateOnly(mItem.get(position).getCheck_out()));
        holder.binding.tvCheckoutMonth.setText(HelperClass.getMonthOnly(mItem.get(position).getCheck_out()));
        holder.binding.tvCheckoutYear.setText(HelperClass.getYearOnly(mItem.get(position).getCheck_out()));
        holder.binding.tvCheckoutTime.setText(HelperClass.formatDateTimeTO_Time(mItem.get(position).getCheck_out()));


        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + mItem.get(position).getVenue().getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivVenueImage);
        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + mItem.get(position).getQr_code()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivQrImage);
        holder.binding.tvCustName.setText(mItem.get(position).getCustomer_details().getCust_name());

        if (mItem.get(position).getReservation_guest() != null)
            if (mItem.get(position).getReservation_guest().size() == 2) {
                holder.binding.rlCustName2.setVisibility(View.VISIBLE);
                holder.binding.tvCustName2.setText(mItem.get(position).getReservation_guest().get(1).getFirst_name());
            } else if (mItem.get(position).getReservation_guest().size() == 3) {
                holder.binding.rlCustName2.setVisibility(View.VISIBLE);
                holder.binding.rlCustName3.setVisibility(View.VISIBLE);
                holder.binding.tvCustName2.setText(mItem.get(position).getReservation_guest().get(1).getFirst_name());
                holder.binding.tvCustName3.setText(mItem.get(position).getReservation_guest().get(2).getFirst_name());
            } else if (mItem.get(position).getReservation_guest().size() > 3) {
                holder.binding.rlCustName2.setVisibility(View.VISIBLE);
                holder.binding.rlCustName3.setVisibility(View.VISIBLE);
                holder.binding.tvCustName2.setText(mItem.get(position).getReservation_guest().get(1).getFirst_name());
                holder.binding.tvCustName3.setText(mItem.get(position).getReservation_guest().get(2).getFirst_name());
                holder.binding.tvViewMore.setVisibility(View.VISIBLE);
            }


        if (HelperClass.getComparedDate_time(mItem.get(position).getCheck_out()).equalsIgnoreCase(mContext.getResources().getString(R.string.yet_to_come))
                && HelperClass.getComparedDate_time(mItem.get(position).getCheck_in()).equalsIgnoreCase(mContext.getResources().getString(R.string.passed))) {
            holder.binding.tvSelfCheckIn.setVisibility(View.VISIBLE);
            CountDownTimer timer;
            try {
                String endTime = mItem.get(position).getCheck_out();
                Date dateObj1 = sdf.parse(new SimpleDateFormat(YYYY_MM_dd_HH_mm_ss, Locale.getDefault()).format(new Date())); // current
                Date dateObj2 = sdf.parse(endTime);
                long diff = dateObj2.getTime() - dateObj1.getTime();

                mItem.get(position).setmOrderStatus(true);
                timer = new CountDownTimer(diff, 1000) {
                    public void onTick(long millisUntilFinished) {
                        long millis = millisUntilFinished;
                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                        holder.binding.tvOrderStatus.setText(hms);
                    }

                    public void onFinish() {
                        holder.binding.tvOrderStatus.setText(mContext.getResources().getString(R.string.time_out));
                        mItem.get(position).setmOrderStatus(false);
                    }
                };
                timer.start();
            } catch (Exception exc) {
                exc.printStackTrace();
                holder.binding.tvSelfCheckIn.setVisibility(View.GONE);
                Log.e("TMM", "" + exc);
            }
            ////////////////////////////////////////////////////////////////////////////////
        } else if (HelperClass.getComparedDate_time(mItem.get(position).getCheck_in()).equalsIgnoreCase(mContext.getResources().getString(R.string.yet_to_come))) {
            if (mItem.get(position).getVenue().getIs_booking_cancel_allow() == 1)
                holder.binding.tvCancelOrder.setVisibility(View.VISIBLE);
            holder.binding.tvOrderStatus.setText(mContext.getResources().getString(R.string.yet_to_come));
            mItem.get(position).setmOrderStatus(false);
            //  holder.binding.llMain.setBackgroundColor(mContext.getResources().getColor(R.color.light_green));

        } else if (HelperClass.getComparedDate_time(mItem.get(position).getCheck_out()).equalsIgnoreCase(mContext.getResources().getString(R.string.passed))) {
            mItem.get(position).setmOrderStatus(false);
            holder.binding.tvOrderStatus.setText(mContext.getResources().getString(R.string.time_out));
        }

        if (mItem.get(position).getStatus() == 3) {
            mItem.get(position).setmOrderStatus(false);
            holder.binding.tvOrderStatus.setText(mContext.getResources().getString(R.string.cancelled));
            holder.binding.tvCancelOrder.setVisibility(View.GONE);
        } else if (mItem.get(position).getStatus() == 4) {
            holder.binding.tvSelfCheckIn.setAlpha(.5f);
            holder.binding.tvSelfCheckIn.setEnabled(false);
            holder.binding.tvSelfCheckIn.setClickable(false);
        }

        holder.binding.tvSelfCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getLayoutPosition(), SELF_CHECKIN);
                holder.binding.tvSelfCheckIn.setAlpha(.5f);
                holder.binding.tvSelfCheckIn.setEnabled(false);
                holder.binding.tvSelfCheckIn.setClickable(false);
            }
        });
        holder.binding.tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getLayoutPosition(), CANCEL_ORDER);
            }
        });

        holder.binding.tvOrderFoodDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getLayoutPosition(), ORDER_FOOD);
            }
        });
        holder.binding.ivVenueImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getLayoutPosition(), ORDER_FOOD);
            }
        });

        holder.binding.tvMakeNewBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getLayoutPosition(), NEW_BOOKING);
            }
        });

        holder.binding.tvViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAllGuest bottomSheetDialog = DialogAllGuest.newInstance(mContext, mItem.get(position).getReservation_guest());
                bottomSheetDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
            }
        });

        holder.binding.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.binding.llCheckinDetails.getVisibility() == View.GONE) {

                    holder.binding.llOrderFood.setVisibility(View.VISIBLE);
                    holder.binding.llCancelOrder.setVisibility(View.VISIBLE);

                    holder.binding.llCheckinDetails.setVisibility(View.VISIBLE);
                    holder.binding.cvQrImage.setVisibility(View.VISIBLE);

                } else {

                    holder.binding.llOrderFood.setVisibility(View.GONE);
                    holder.binding.llCancelOrder.setVisibility(View.GONE);
                    holder.binding.llCheckinDetails.setVisibility(View.GONE);
                    holder.binding.cvQrImage.setVisibility(View.GONE);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mItem.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        //public LayoutCellMyOrderReservationBinding binding;
        public LayoutCellMyOrderReservationNewBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Toast.makeText(mContext, "" + mItem.get(getAdapterPosition()).getAmount(), Toast.LENGTH_SHORT).show();
        }
    }


}
