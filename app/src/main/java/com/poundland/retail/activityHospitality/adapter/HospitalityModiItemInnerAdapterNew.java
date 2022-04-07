package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.XxLayoutCellModifierInnerItemBinding;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;

import java.util.ArrayList;
import java.util.List;


/*for setting item for the recycler view for   */
public class HospitalityModiItemInnerAdapterNew extends RecyclerView.Adapter<HospitalityModiItemInnerAdapterNew.ViewResource> {

    Context mContext;
    private LayoutInflater mInflater;
    private ModifierSelectionListner mCallBack;
    private List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean> modList;
    private int preSelected = 0;
    private int parentPosition;
    private XxLayoutCellModifierInnerItemBinding lastSelectedBinding;
    private double discPrice;

    public HospitalityModiItemInnerAdapterNew(Context mContext, int parentPosition, List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean> modList, double discPrice, ModifierSelectionListner mCallBack) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.parentPosition = parentPosition;
        this.mCallBack = mCallBack;
        this.modList = new ArrayList<>(modList);
        this.discPrice = discPrice;
        for (int i = 0; i < this.modList.size(); i++) {
            this.modList.get(i).setSelected(false);
        }
    }

    public List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ModifierListBean> getItems() {
        return modList;
    }

    @NonNull
    @Override
    public HospitalityModiItemInnerAdapterNew.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xx_layout_cell_modifier_inner_item, parent, false);

        return new HospitalityModiItemInnerAdapterNew.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalityModiItemInnerAdapterNew.ViewResource holder, int position) {

        holder.binding.tvModifierName.setText(modList.get(position).getModifier_name());
        double amtSell = Double.parseDouble(modList.get(position).getSell_price()) - discPrice;
        holder.binding.tvModifierPrice.setText(mContext.getString(R.string.pound) + String.format("%.2f", amtSell));
        if (position == preSelected) {
            holder.binding.tvModifierName.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
            holder.binding.tvModifierPrice.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
            holder.binding.rbSelect.setChecked(true);
            modList.get(position).setSelected(true);
            lastSelectedBinding = holder.binding;
            if (mCallBack != null) {
                mCallBack.onModifierSelect(parentPosition, position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return modList == null ? 0 : modList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public XxLayoutCellModifierInnerItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.rlMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_main:
                    if (lastSelectedBinding == null) {
                        binding.rbSelect.setChecked(true);
                        binding.tvModifierName.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
                        binding.tvModifierPrice.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
                        modList.get(getLayoutPosition()).setSelected(true);
                        preSelected = getLayoutPosition();
                        lastSelectedBinding = binding;

                    } else {
                        lastSelectedBinding.rbSelect.setChecked(false);
                        lastSelectedBinding.tvModifierName.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                        lastSelectedBinding.tvModifierPrice.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                        modList.get(preSelected).setSelected(false);

                        binding.rbSelect.setChecked(true);
                        binding.tvModifierName.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
                        binding.tvModifierPrice.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
                        modList.get(getLayoutPosition()).setSelected(true);
                        preSelected = getLayoutPosition();
                        lastSelectedBinding = binding;
                    }
                    if (mCallBack != null) {
                        mCallBack.onModifierSelect(parentPosition, getLayoutPosition());
                    }
                    break;
            }
        }
    }


}
