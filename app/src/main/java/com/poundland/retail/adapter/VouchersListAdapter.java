package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutCellVouchersBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.GetCustomerVouchersResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.VOUCHER;


public class VouchersListAdapter extends RecyclerView.Adapter<VouchersListAdapter.ViewResource> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<GetCustomerVouchersResponseModel.VoucherBean.DataBean> voucherList;
    private PrefManager prefManager;
    private DrawerListner drawerListner;

    public VouchersListAdapter(Context mContext, List<GetCustomerVouchersResponseModel.VoucherBean.DataBean> voucherList, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.voucherList = voucherList;
        this.drawerListner = drawerListner;
        prefManager = PrefManager.getInstance(mContext);
    }


    @NonNull
    @Override
    public VouchersListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_vouchers, parent, false);

        return new VouchersListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VouchersListAdapter.ViewResource holder, int position) {

        holder.binding.tvVoucherName.setText(voucherList.get(position).getVoucher_number());
        holder.binding.tvVenueName.setText(voucherList.get(position).getVenue_name());
        holder.binding.tvValidOnDays.setText(mContext.getString(R.string.valid_on)+":["+voucherList.get(position).getDays_available()+"]");
        holder.binding.tvExpire.setText(String.format("%s:%s", mContext.getString(R.string.expire), voucherList.get(position).getEnd_date()));

        holder.binding.tvPriceCount.setText(String.format("%s%s", mContext.getString(R.string.pound), voucherList.get(position).getAmount()));   ////  getActualPrice();

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + voucherList.get(position).getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivVenueImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), VOUCHER);
            }
        });

    }

    @Override
    public int getItemCount() {
        return voucherList == null ? 0 : voucherList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellVouchersBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
