package com.poundland.retail.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellComboOfferBinding;
import com.poundland.retail.interfaces.ComboOfferListner;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.SingleProductDetailResponseModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/*for setting item for the recycler view for   */
public class ComboOfferAdapter extends RecyclerView.Adapter<ComboOfferAdapter.ViewResource> implements DrawerListner {

    Context mContext;
    private LayoutInflater mInflater;
    private ComboOfferListner comboOfferListner;
    private List<List<SingleProductDetailResponseModel.ComboOfferBean>> comboOfferList;
    private String currentTime;
    private String format = "HH:mm:ss";
    private SimpleDateFormat sdf = new SimpleDateFormat(format);

    public ComboOfferAdapter(Context mContext, List<List<SingleProductDetailResponseModel.ComboOfferBean>> comboOfferList, String currentTime, ComboOfferListner comboOfferListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.comboOfferList = comboOfferList;
        this.comboOfferListner = comboOfferListner;
        this.currentTime = currentTime;

    }

    @NonNull
    @Override
    public ComboOfferAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_combo_offer, parent, false);

        return new ComboOfferAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ComboOfferAdapter.ViewResource holder, int position) {

        // holder.binding.rbRating.setRating(reviewsBeanList.get(position).getRattings());
        InnerComboOfferAdapter reviewAdapter = new InnerComboOfferAdapter(mContext, comboOfferList.get(position), new ComboOfferListner() {
            @Override
            public void onComboOfferListner(int pos) {
                comboOfferListner.onComboOfferListner(position);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.binding.rvComboOffer.setLayoutManager(layoutManager);
        holder.binding.rvComboOffer.setAdapter(reviewAdapter);

        try {
            String endTime = comboOfferList.get(position).get(0).getOffer_time_end();
            Date dateObj1 = sdf.parse(currentTime);
            Date dateObj2 = sdf.parse(endTime);

            new CountDownTimer(dateObj2.getTime() - dateObj1.getTime(), 1000) {
                public void onTick(long millisUntilFinished) {
                    long millis = millisUntilFinished;
                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                    holder.binding.tvExpiry.setText(hms);
                }

                public void onFinish() {
                }
            }.start();

        } catch (Exception exc) {
            exc.printStackTrace();
        }


    }

    @Override
    public void onDrawerSelect(int position, int clickId) {

    }

    @Override
    public int getItemCount() {
        return comboOfferList == null ? 0 : comboOfferList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellComboOfferBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
