package com.poundland.retail.adapter;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellPaymentDetailsBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.ActivePayResponseModel;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.poundland.retail.interfaces.Constants.UNIVERSAL_CODE;


/*for setting item for the recycler view for   */
public class ActivePayListAdapter extends RecyclerView.Adapter<ActivePayListAdapter.ViewResource> {

    private Context mContext;
    private List<ActivePayResponseModel.DataBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;

    public ActivePayListAdapter(Context mContext, List<ActivePayResponseModel.DataBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    public void removeCard(int pos) {
        data.remove(pos);
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(pos);
    }

    public void refreshItem() {

        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ActivePayListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_payment_details, parent, false);

        return new ActivePayListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ActivePayListAdapter.ViewResource holder, int position) {
        try {
            /*
            byte[] encData = Base64.decode(data.get(position).getCard_type(), Base64.DEFAULT);
            String text = new String(encData, StandardCharsets.UTF_8);
  */

            holder.binding.tvExpiry.setVisibility(View.GONE);
            holder.binding.tvCardName.setText(new String(Base64.decode(data.get(position).getCard_number(), Base64.DEFAULT), StandardCharsets.UTF_8));

            if (new String(Base64.decode(data.get(position).getCard_type(), Base64.DEFAULT), StandardCharsets.UTF_8).equalsIgnoreCase(mContext.getString(R.string.visa_credit))) {
                holder.binding.ivItemImage.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ic_visa));
            } else if (new String(Base64.decode(data.get(position).getCard_number(), Base64.DEFAULT), StandardCharsets.UTF_8).equalsIgnoreCase(mContext.getString(R.string.mastercard))) {
                holder.binding.ivItemImage.setBackground(ContextCompat.getDrawable(mContext, R.drawable.cio_ic_mastercard));
            } else if (new String(Base64.decode(data.get(position).getCard_number(), Base64.DEFAULT), StandardCharsets.UTF_8).equalsIgnoreCase(mContext.getString(R.string.maestro_card))) {
                holder.binding.ivItemImage.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ic_maestro));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  holder.binding.tvDelete.setOnClickListener(view ->
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), DELETE_ITEM));*/

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), UNIVERSAL_CODE);
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
        public LayoutCellPaymentDetailsBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
