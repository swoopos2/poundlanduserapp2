package com.poundland.retail.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellShippingMethodsBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.ShippingMethodResponseModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.poundland.retail.interfaces.Constants.SHIPPING_METHOD;


/* for setting item for the recycler view for */
public class ShippingMethodAdapter extends RecyclerView.Adapter<ShippingMethodAdapter.ViewResource> {

    Context mContext;
    DrawerListner drawerListner;
    private List<ShippingMethodResponseModel.ShippingDetailsBean> shippingList;
    private LayoutInflater mInflater;
    private PrefManager prefManager;
    //  private String format = "HH:mm:ss";
    private String format = "HH:mm";
    private SimpleDateFormat sdf = new SimpleDateFormat(format);
    private String currentTime;
    CountDownTimer timer;
    private String SAME_DAY = "Same Day Delivery";
    String PATTERN_START_WITH_DATE_NO_YEAR = "dd MMM";

    private LayoutCellShippingMethodsBinding lastSelectedBindingView;

    public ShippingMethodAdapter(Context mContext, List<ShippingMethodResponseModel.ShippingDetailsBean> shippingList, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.shippingList = shippingList;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(mContext);
        currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
    }

    @NonNull
    @Override
    public ShippingMethodAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_shipping_methods, parent, false);

        return new ShippingMethodAdapter.ViewResource(view);
    }

    public void clearCheck() {
        if (lastSelectedBindingView != null)
            lastSelectedBindingView.cbSameDayDelivery.setChecked(false);
        lastSelectedBindingView = null;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull final ShippingMethodAdapter.ViewResource holder, int position) {

        if (shippingList.size() > 0 && position == 0) {
            holder.binding.cbSameDayDelivery.setChecked(true);
            lastSelectedBindingView = holder.binding;
            drawerListner.onDrawerSelect(position, SHIPPING_METHOD);
        }


        CountDownTimer timer;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_START_WITH_DATE_NO_YEAR, Locale.getDefault());
            Calendar calendar = Calendar.getInstance();

            Date dateObj1 = sdf.parse(currentTime);
            Date dateObj2 = sdf.parse(shippingList.get(position).getTime());
            long diff = dateObj2.getTime() - dateObj1.getTime();

            if (diff <= 0) {  /// Time laps >> get in if condition
                diff = sdf.parse("23:59").getTime() - sdf.parse(currentTime).getTime();  // Date dateObjEnd - Date currentTime

                if (shippingList.get(position).getTo_day() > 1) {    ////  Get delivery on 21 feb
                    calendar.add(Calendar.DAY_OF_YEAR, shippingList.get(position).getTo_day() + 1); // Here adding To_day in current date to get delivery date
                    holder.binding.tvDeliveryDay.setText(dateFormat.format(calendar.getTime()));
                    if (shippingList.get(position).getType() == 1) {
                        holder.binding.tvSameDayDeliveryCost.setText(shippingList.get(position).getName().contains("Same Day Delivery")?mContext.getString(R.string.next_day_delivery):shippingList.get(position).getName());
                        holder.binding.tvSameDayDeliveryCostShow.setText(" +" + mContext.getString(R.string.pound) + shippingList.get(position).getAmount());
                    } else {
                        holder.binding.tvSameDayDeliveryCost.setText(mContext.getString(R.string.next_day_delivery));
                        holder.binding.tvSameDayDeliveryCostShow.setText(" +" + shippingList.get(position).getAmount() + mContext.getString(R.string.percent));
                    }

                } else {
                    if (shippingList.get(position).getHourly_delivery() == 0) {
                        holder.binding.tvDeliveryDay.setText(mContext.getString(R.string.tomorrow));
                        if (shippingList.get(position).getType() == 1) {// type denote percent or amnt
                            holder.binding.tvSameDayDeliveryCost.setText(mContext.getString(R.string.next_day_delivery));
                            holder.binding.tvSameDayDeliveryCostShow.setText(" +" + mContext.getString(R.string.pound) + shippingList.get(position).getAmount());
                        } else {
                            holder.binding.tvSameDayDeliveryCost.setText(mContext.getString(R.string.next_day_delivery));
                            holder.binding.tvSameDayDeliveryCostShow.setText(" +" + shippingList.get(position).getAmount() + mContext.getString(R.string.percent));
                        }
                    } else if (shippingList.get(position).getHourly_delivery() == 1) {  /// if its hourly and times up
                        holder.binding.cbSameDayDelivery.setChecked(false);
                        holder.binding.cbSameDayDelivery.setClickable(false);
                        holder.binding.cbSameDayDelivery.setEnabled(false);
                        //holder.binding.tvExpiry.setVisibility(View.INVISIBLE);
                        holder.binding.cbSameDayDelivery.setAlpha(.5f);
                        holder.binding.tvDeliveryDay.setAlpha(.5f);
                        holder.binding.tvSameDayDeliveryCost.setAlpha(.5f);
                        holder.binding.tvDeliveryWithin.setAlpha(.5f);
                        holder.binding.tvOrderWithin.setAlpha(.5f);
                        shippingList.get(position).setActive(true);
                        if (shippingList.get(position).getType() == 1) {
                            holder.binding.tvSameDayDeliveryCost.setText(shippingList.get(position).getName());
                            holder.binding.tvSameDayDeliveryCostShow.setText(" +" + mContext.getString(R.string.pound) + shippingList.get(position).getAmount());

                        } else {
                            holder.binding.tvSameDayDeliveryCost.setText(shippingList.get(position).getName());
                            holder.binding.tvSameDayDeliveryCostShow.setText(" +" + shippingList.get(position).getAmount() + mContext.getString(R.string.percent));
                        }

                    } else if (shippingList.get(position).getHourly_delivery() == 2) {  /// if its hourly and times up
                        holder.binding.cbSameDayDelivery.setChecked(false);
                        holder.binding.cbSameDayDelivery.setClickable(false);
                        holder.binding.cbSameDayDelivery.setEnabled(false);
                        //holder.binding.tvExpiry.setVisibility(View.INVISIBLE);
                        holder.binding.cbSameDayDelivery.setAlpha(.5f);
                        holder.binding.tvDeliveryDay.setAlpha(.5f);
                        holder.binding.tvSameDayDeliveryCost.setAlpha(.5f);
                        holder.binding.tvDeliveryWithin.setAlpha(.5f);
                        holder.binding.tvOrderWithin.setAlpha(.5f);
                        shippingList.get(position).setActive(true);
                        if (shippingList.get(position).getType() == 1) {
                            holder.binding.tvSameDayDeliveryCost.setText(shippingList.get(position).getName());
                            holder.binding.tvSameDayDeliveryCostShow.setText(" +" + mContext.getString(R.string.pound) + shippingList.get(position).getAmount());

                        } else {
                            holder.binding.tvSameDayDeliveryCost.setText(shippingList.get(position).getName());
                            holder.binding.tvSameDayDeliveryCostShow.setText(" +" + shippingList.get(position).getAmount() + mContext.getString(R.string.percent));
                        }

                    }

                }
            }
            else {  /// Time yet to come not lapps
                if (shippingList.get(position).getTo_day() > 1) {
                    calendar.add(Calendar.DAY_OF_YEAR, shippingList.get(position).getTo_day());
                    holder.binding.tvDeliveryDay.setText(dateFormat.format(calendar.getTime()));
                } else holder.binding.tvDeliveryDay.setText(mContext.getString(R.string.today));

                if (shippingList.get(position).getType() == 1) {
                    holder.binding.tvSameDayDeliveryCost.setText(shippingList.get(position).getName());
                    holder.binding.tvSameDayDeliveryCostShow.setText(" +" + mContext.getString(R.string.pound) + shippingList.get(position).getAmount());
                } else {
                    holder.binding.tvSameDayDeliveryCost.setText(shippingList.get(position).getName());
                    holder.binding.tvSameDayDeliveryCostShow.setText(" +" + shippingList.get(position).getAmount() + mContext.getString(R.string.percent));
                }
            }

            timer = new CountDownTimer(diff, 1000) {
                public void onTick(long millisUntilFinished) {
                    long millis = millisUntilFinished;
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    holder.binding.tvExpiry.setText(hms);
                }

                public void onFinish() {
                    holder.binding.tvExpiry.setText("Time up!");
                }
            };
            timer.start();
        } catch (Exception exc) {
            exc.printStackTrace();
            Log.e("TMM Exce", "" + exc);
        }
    }

    @Override
    public int getItemCount() {
        return shippingList == null ? 0 : shippingList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LayoutCellShippingMethodsBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

            binding.cvMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (shippingList.get(getAdapterPosition()).isActive()){
                return;
            }
            if (lastSelectedBindingView == null) {
                binding.cbSameDayDelivery.setChecked(true);
                lastSelectedBindingView = binding;
            } else {
                lastSelectedBindingView.cbSameDayDelivery.setChecked(false);
                binding.cbSameDayDelivery.setChecked(true);
                lastSelectedBindingView = binding;
            }
            if (drawerListner != null) {
                drawerListner.onDrawerSelect(getLayoutPosition(), SHIPPING_METHOD);
            }
        }
    }
}
