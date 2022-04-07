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
import com.poundland.retail.databinding.LayoutCellNotificationBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.NotificationResponseModel;

import java.util.List;


/*for setting item for the recycler view for   */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewResource> {

    private Context mContext;
    private List<NotificationResponseModel.NotificationsBean.DataBeanX> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;


    public NotificationAdapter(Context mContext, List<NotificationResponseModel.NotificationsBean.DataBeanX> data, DrawerListner drawerListner) {
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

    public void restoreItem(NotificationResponseModel.NotificationsBean.DataBeanX item, int position) { /// send list type instead of string with real data
        data.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_notification, parent, false);

        return new NotificationAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.ViewResource holder, int position) {

        holder.binding.tvTitle.setText(data  .get(position).getTitle());
        holder.binding.tvMessage.setText(data.get(position).getMessage());
        holder.binding.tvTime.setText( HelperClass.formatDateMonthHr(data.get(position).getCreated_at()));

        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + data.get(position).getImage()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivItemImage);
        /*holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition());
            }
        });*/
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
        public LayoutCellNotificationBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
