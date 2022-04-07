package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.XxLayoutCellTipItemBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.TipModel;

import java.util.List;

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewResource> {

    private List<TipModel> data;
    Context mContext;
    private LayoutInflater mInflater;
    private DrawerListner drawerListner;
    private int preSelected = -1;
    private XxLayoutCellTipItemBinding lastSelectedBinding;
    int pos;
    boolean isClicked;

    public TipAdapter(Context mContext, List<TipModel> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public TipAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xx_layout_cell_tip_item, parent, false);

        return new TipAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TipAdapter.ViewResource holder, int position) {

        holder.binding.cvMain.setBackgroundResource(R.drawable.red_filled_rounded_rect);
        holder.binding.tvTipAmount.setText(data.get(position).percent + mContext.getString(R.string.percent));

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
        public XxLayoutCellTipItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.rlMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int selectedTip = data.get(getLayoutPosition()).percent;
            if (preSelected == -1) {
                binding.cvMain.setBackgroundResource(R.drawable.red_extr_filled_4dp_rect);
                preSelected = getLayoutPosition();
            } else {
                if (preSelected == getLayoutPosition()) {
                    lastSelectedBinding.cvMain.setBackgroundResource(R.drawable.red_filled_rounded_rect);
                    preSelected = -1;
                    selectedTip = 0;
                } else {
                    lastSelectedBinding.cvMain.setBackgroundResource(R.drawable.red_filled_rounded_rect);
                    binding.cvMain.setBackgroundResource(R.drawable.red_extr_filled_4dp_rect);
                    preSelected = getLayoutPosition();
                }
            }

            lastSelectedBinding = binding;

            drawerListner.onDrawerSelect(getLayoutPosition(), selectedTip);
        }

    }
}
