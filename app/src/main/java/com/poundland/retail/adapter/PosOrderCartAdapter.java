package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellPosOrderCartItemBinding;
import com.poundland.retail.model.responseModel.FetchPOSorderResponseModel;

import java.util.ArrayList;
import java.util.List;

public class PosOrderCartAdapter extends RecyclerView.Adapter<PosOrderCartAdapter.ViewResource> {

    private List<FetchPOSorderResponseModel.OrdersBean.OrderDetailsBean> data;
    private Context mContext;
    private LayoutInflater mInflater;

    public PosOrderCartAdapter(Context mContext) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        data = new ArrayList<>();
    }

    public void clearData() {
        this.data.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<FetchPOSorderResponseModel.OrdersBean.OrderDetailsBean> data) {
        this.data.addAll(data);
        notifyItemChanged(this.data.size());
    }

    public void addItem(FetchPOSorderResponseModel.OrdersBean.OrderDetailsBean data) {
        this.data.add(data);
        notifyItemChanged(this.data.size());
    }

    @NonNull
    @Override
    public PosOrderCartAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_pos_order_cart_item, parent, false);

        return new PosOrderCartAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PosOrderCartAdapter.ViewResource holder, int position) {

        holder.binding.tvProductQty.setText(mContext.getResources().getString(R.string.quantity) + " : " + data.get(position).getProduct_qty());
        holder.binding.tvProductName.setText(data.get(position).getProduct_details().getProduct_name());
        holder.binding.tvProductPrice.setText(mContext.getResources().getString(R.string.pound) +String.format("%.2f",Double.parseDouble(data.get(position).getNet_amount()) ));

        PosOrderAdonsAdapter tipAdapter = new PosOrderAdonsAdapter(mContext, data.get(position).getAdd_on());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder.binding.rvProductAdonsList.setLayoutManager(layoutManager);
        holder.binding.rvProductAdonsList.setAdapter(tipAdapter);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LayoutCellPosOrderCartItemBinding binding;

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
