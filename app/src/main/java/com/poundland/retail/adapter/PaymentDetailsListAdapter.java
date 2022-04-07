package com.poundland.retail.adapter;

import android.content.Context;
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
import com.poundland.retail.model.responseModel.GetStripeCardDBModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.DELETE_ITEM;
import static com.poundland.retail.interfaces.Constants.UNIVERSAL_CODE;


/*for setting item for the recycler view for   */
public class PaymentDetailsListAdapter extends RecyclerView.Adapter<PaymentDetailsListAdapter.ViewResource> {

    private Context mContext;
    private List<GetStripeCardDBModel.CardsBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;

    public PaymentDetailsListAdapter(Context mContext, List<GetStripeCardDBModel.CardsBean> data, DrawerListner drawerListner) {
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
    public PaymentDetailsListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_payment_details, parent, false);

        return new PaymentDetailsListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaymentDetailsListAdapter.ViewResource holder, int position) {

        holder.binding.tvExpiry.setText(mContext.getString(R.string.exp_date) + data.get(position).getExp_month() + "/" + data.get(position).getExp_year());
        holder.binding.tvCardName.setText("" + data.get(position).getCard_number());
        if (data.get(position).getCard_type()!=null)
        if (data.get(position).getCard_type().equalsIgnoreCase(mContext.getString(R.string.visa))) {
            holder.binding.ivItemImage.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ic_visa));
        } else if (data.get(position).getCard_type().equalsIgnoreCase(mContext.getString(R.string.mastercard))) {
            holder.binding.ivItemImage.setBackground(ContextCompat.getDrawable(mContext, R.drawable.cio_ic_mastercard));
        } else if (data.get(position).getCard_type().equalsIgnoreCase(mContext.getString(R.string.maestro_card))) {
            holder.binding.ivItemImage.setBackground(ContextCompat.getDrawable(mContext, R.drawable.ic_maestro));
        }

        holder.binding.tvDelete.setOnClickListener(view ->
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), DELETE_ITEM));

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
