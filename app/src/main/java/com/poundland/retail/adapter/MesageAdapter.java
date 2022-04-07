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
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.model.responseModel.GetAllMessageEcomResponseModel;
import com.poundland.retail.databinding.LayoutCellMessageBinding;
import com.poundland.retail.interfaces.DrawerListner;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.MESSAGE;

public class MesageAdapter extends RecyclerView.Adapter<MesageAdapter.ViewResource> {

    private Context mContext;
    private List<GetAllMessageEcomResponseModel.MessagesBean.DataBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;


    public MesageAdapter(Context mContext, List<GetAllMessageEcomResponseModel.MessagesBean.DataBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }
    public void removeItem(int position) {
        data.remove(position);
        // NOTE: don't call notifyDataSetChanged()
        notifyDataSetChanged();
        // notifyItemRemoved(position);
    }
    public void refreshItem() {

        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public MesageAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_message, parent, false);

        return new MesageAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MesageAdapter.ViewResource holder, int position) {

        if (data.get(position).getUnread_msg() > 0) {
            holder.binding.rlMessageCounter.setVisibility(View.VISIBLE);
        } else holder.binding.rlMessageCounter.setVisibility(View.GONE);
        if (data.get(position).getUnique_code() != null){
            holder.binding.tvOrderNumber.setVisibility(View.VISIBLE);
            holder.binding.tvOrderPrice.setVisibility(View.VISIBLE);
            holder.binding.tvOrderNumber.setText(String.format("%s%s", mContext.getString(R.string.order_no), data.get(position).getUnique_code()));
            holder.binding.tvOrderPrice.setText(String.format("%s%s", mContext.getString(R.string.pound), data.get(position).getNet_amount()));
        }

        holder.binding.tvVenueName.setText(data.get(position).getVenue_name());
        holder.binding.tvMessageDescription.setText(data.get(position).getMessage());
        holder.binding.tvMessageCount.setText(String.valueOf(data.get(position).getUnread_msg()));
        holder.binding.tvDate.setText(HelperClass.formatDateMonthHr(data.get(position).getCreated_at()));

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_VENUE + data.get(position).getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivItemImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), MESSAGE);
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
        public LayoutCellMessageBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
