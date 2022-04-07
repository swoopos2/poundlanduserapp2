package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellPosOrderAdonsItemBinding;
import com.poundland.retail.model.responseModel.FetchPOSorderResponseModel;

import java.util.List;

public class PosOrderAdonsAdapter extends RecyclerView.Adapter<PosOrderAdonsAdapter.ViewResource> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<FetchPOSorderResponseModel.OrdersBean.OrderDetailsBean.AddOnBean> add_on;

    public PosOrderAdonsAdapter(Context mContext, List<FetchPOSorderResponseModel.OrdersBean.OrderDetailsBean.AddOnBean> add_on) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.add_on = add_on;
    }

    public void clearData() {
        this.add_on.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<FetchPOSorderResponseModel.OrdersBean.OrderDetailsBean.AddOnBean> data) {
        this.add_on.addAll(data);
        notifyItemChanged(this.add_on.size());
    }

    @NonNull
    @Override
    public PosOrderAdonsAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_pos_order_adons_item, parent, false);

        return new PosOrderAdonsAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PosOrderAdonsAdapter.ViewResource holder, int position) {

        holder.binding.tvProductQty.setText(mContext.getResources().getString(R.string.quantity)+" : " + add_on.get(position).getQty());
        holder.binding.tvProductName.setText(add_on.get(position).getName());
        holder.binding.tvProductPrice.setText( mContext.getResources().getString(R.string.pound)+ String.format("%.2f",add_on.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return add_on == null ? 0 : add_on.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LayoutCellPosOrderAdonsItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            // binding.rlMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_main:
                    /*if (binding.llInclude.getVisibility() == View.VISIBLE) {
                        binding.llInclude.setVisibility(View.GONE);
                    }*/
                    break;
            }
        }
    }
}
