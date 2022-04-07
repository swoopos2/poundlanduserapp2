package com.poundland.retail.activityHospitality.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.XxLayoutCellReservationItemBinding;
import com.poundland.retail.dialog.DialogReservatioTimeDateEntry;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnSeasonExpireListener;
import com.poundland.retail.model.responseModel.ReservationVenuesResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.TOP_VENUE;


/* for setting item for the recycler view for */
public class ReservationVenueAdapter extends RecyclerView.Adapter<ReservationVenueAdapter.ViewResource> {

    private Activity mActivity;
    private Context mContext;
    private DrawerListner drawerListner;
    private List<ReservationVenuesResponseModel.VenuesBean.DataBean> data;
    private LayoutInflater mInflater;
    private PrefManager prefManager;
    private String searchDate;
    private int searchGuest;

    public ReservationVenueAdapter(Activity mActivity, Context mContext, List<ReservationVenuesResponseModel.VenuesBean.DataBean> data, String searchDate, int searchGuest, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.data = data;
        this.searchDate = searchDate;
        this.searchGuest = searchGuest;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public ReservationVenueAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xx_layout_cell_reservation_item, parent, false);

        return new ReservationVenueAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReservationVenueAdapter.ViewResource holder, int position) {

        holder.binding.tvVenueName.setText(data.get(position).getVenue_name());

        holder.binding.tvDistance.setText(data.get(position).getDistance() != null ? data.get(position).getDistance() + mContext.getString(R.string.miles) : "" + mContext.getString(R.string.miles));
        if (data.get(position).getDelivery() == 1) {
            holder.binding.tvHomeDelivery.setVisibility(View.VISIBLE);
        }
        if (data.get(position).getCollection() == 1) {
            holder.binding.tvClickCollect.setVisibility(View.VISIBLE);
        }
        if (data.get(position).getRate() != null && !data.get(position).getRate().isEmpty())
            holder.binding.rbRating.setRating(Float.parseFloat(data.get(position).getRate()));

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + data.get(position).getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivVenueImage);
        try {
            if (data.get(position).getReservationSlot() != null && data.get(position).getReservationSlot().size() == 3) {
                holder.binding.tvTimeSlot1.setText(HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(0).getTime()));
                holder.binding.tvTimeSlot2.setText(HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(1).getTime()));
                holder.binding.tvTimeSlot3.setText(HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(2).getTime()));
            } else if (data.get(position).getReservationSlot() != null && data.get(position).getReservationSlot().size() == 2) {
                holder.binding.tvTimeSlot1.setText(HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(0).getTime()));
                holder.binding.tvTimeSlot2.setText(HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(1).getTime()));
                holder.binding.tvTimeSlot3.setText("Not");
            } else if (data.get(position).getReservationSlot() != null && data.get(position).getReservationSlot().size() == 1) {
                holder.binding.tvTimeSlot1.setText(HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(0).getTime()));
                holder.binding.tvTimeSlot2.setText("Not");
                holder.binding.tvTimeSlot3.setText("Not");
            } else if (data.get(position).getReservationSlot() != null && data.get(position).getReservationSlot().size() == 0) {
                holder.binding.tvTimeSlot1.setText("Not");
                holder.binding.tvTimeSlot2.setText("Not");
                holder.binding.tvTimeSlot3.setText("Not");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.binding.tvBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : Pass Current Time when time slot is not available
                if (data.get(position).getReservationSlot().size() > 0) {


                    DialogReservatioTimeDateEntry bottomSheetDialog = DialogReservatioTimeDateEntry.newInstance(mActivity, mContext,
                            HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(0).getTime()) + "(" + mContext.getString(R.string.pound) + data.get(position).getReservationSlot().get(0).getPrice() + ")" + "(" + data.get(position).getDefault_reservation_length() + "Min" + ")",
                            data.get(position).getVenue_id(), searchDate, searchGuest,
                            data.get(position).getReservationSlot().get(0).getId()
                            , ApiRequestUrl.BASE_URL_VENUE + data.get(position).getVenue_images(),
                            data.get(position).getAddress_1(), data.get(position).getVenue_name(), new OnSeasonExpireListener() {
                                @Override
                                public void onSeasonExpire() {

                                }
                            });
                    bottomSheetDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
                } else {
                    HelperClass.showSnackBar(holder.binding.getRoot(), mContext.getResources().getString(R.string.time_not_available));
                }
            }
        });

        holder.binding.llT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.get(position).getReservationSlot().size() > 0) {
                    DialogReservatioTimeDateEntry bottomSheetDialog = DialogReservatioTimeDateEntry.newInstance(mActivity, mContext,
                            HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(0).getTime()) + "(" + mContext.getString(R.string.pound) + data.get(position).getReservationSlot().get(0).getPrice() + ")" + "(" + data.get(position).getDefault_reservation_length() + "Min" + ")",
                            data.get(position).getVenue_id(), searchDate, searchGuest, data.get(position).getReservationSlot().get(0).getId()
                            , ApiRequestUrl.BASE_URL_VENUE + data.get(position).getVenue_images(),
                            data.get(position).getAddress_1(), data.get(position).getVenue_name(), new OnSeasonExpireListener() {
                                @Override
                                public void onSeasonExpire() {

                                }
                            });
                    bottomSheetDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

                } else {
                    HelperClass.showSnackBar(holder.binding.getRoot(), mContext.getResources().getString(R.string.time_not_available));
                }

            }
        });

        holder.binding.llT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.get(position).getReservationSlot().size() > 1) {
                    DialogReservatioTimeDateEntry bottomSheetDialog = DialogReservatioTimeDateEntry.newInstance(mActivity, mContext,
                            HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(1).getTime()) + "(" + mContext.getString(R.string.pound) + data.get(position).getReservationSlot().get(1).getPrice() + ")" + "(" + data.get(position).getDefault_reservation_length() + "Min" + ")",
                            data.get(position).getVenue_id(), searchDate, searchGuest, data.get(position).getReservationSlot().get(1).getId()
                            , ApiRequestUrl.BASE_URL_VENUE + data.get(position).getVenue_images(),
                            data.get(position).getAddress_1(), data.get(position).getVenue_name(), new OnSeasonExpireListener() {
                                @Override
                                public void onSeasonExpire() {

                                }
                            });
                    bottomSheetDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

                } else {
                    HelperClass.showSnackBar(holder.binding.getRoot(), mContext.getResources().getString(R.string.time_not_available));
                }

            }
        });

        holder.binding.llT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.get(position).getReservationSlot().size() > 2) {
                    DialogReservatioTimeDateEntry bottomSheetDialog = DialogReservatioTimeDateEntry.newInstance(mActivity, mContext,
                            HelperClass.formatHrMinSec_TO_hrMin(data.get(position).getReservationSlot().get(2).getTime()) + "(" + mContext.getString(R.string.pound) + data.get(position).getReservationSlot().get(2).getPrice() + ")" + "(" + data.get(position).getDefault_reservation_length() + "Min" + ")",
                            data.get(position).getVenue_id(), searchDate, searchGuest, data.get(position).getReservationSlot().get(2).getId()
                            , ApiRequestUrl.BASE_URL_VENUE + data.get(position).getVenue_images(),
                            data.get(position).getAddress_1(), data.get(position).getVenue_name(), new OnSeasonExpireListener() {
                                @Override
                                public void onSeasonExpire() {

                                }
                            });
                    bottomSheetDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

                } else {
                    HelperClass.showSnackBar(holder.binding.getRoot(), mContext.getResources().getString(R.string.time_not_available));
                }
            }
        });

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), TOP_VENUE);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public XxLayoutCellReservationItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
