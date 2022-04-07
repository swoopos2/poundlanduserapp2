package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellHospIngredientInnerBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.HospIngredientSelectionListener;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/*for setting item for the recycler view for   */
public class HospitalityIngredientInnerAdapterNew extends RecyclerView.Adapter<HospitalityIngredientInnerAdapterNew.ViewResource> {

    Context mContext;
    private LayoutInflater mInflater;
    private HospIngredientSelectionListener mCallback;
    private List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> productAddonBeans;
    private LinkedHashMap<Integer, Boolean> itemSelectedList;
    private int parentPosition;

    public HospitalityIngredientInnerAdapterNew(Context mContext, int parentPosition, List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> productAddonBeans, HospIngredientSelectionListener mCallback) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.parentPosition = parentPosition;
        this.productAddonBeans = productAddonBeans;
        this.mCallback = mCallback;
        itemSelectedList = new LinkedHashMap<>();

    }

    public LinkedList<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> getSelectedBaseIngredients() {
        LinkedList<HospitalityVenueProductResponseModel.ProductsBean.DataBean.ProductAddonBean> addons = new LinkedList<>();
        for (Map.Entry<Integer, Boolean> selected : itemSelectedList.entrySet()) {
            if (selected.getValue()) {
                // addons.add(mItems.get(selected.getKey()));
            }
        }
        return addons;
    }

    @NonNull
    @Override
    public HospitalityIngredientInnerAdapterNew.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_modifier_item, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_hosp_ingredient_inner, parent, false);

        return new HospitalityIngredientInnerAdapterNew.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HospitalityIngredientInnerAdapterNew.ViewResource holder, final int position) {
        holder.binding.rlUpdateLayout.setVisibility(View.GONE);
        holder.binding.tvIngredientName.setText(productAddonBeans.get(position).getAddon().getName());
        holder.binding.tvIngredientPrice.setText(mContext.getString(R.string.pound) + productAddonBeans.get(position).getSell_price());
      //  holder.binding.tvIngredientPrice.setText(mContext.getString(R.string.pound) + productAddonBeans.get(position).getAddon().getSell_price());
    }

    @Override
    public int getItemCount() {
        return productAddonBeans == null ? 0 : productAddonBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LayoutCellHospIngredientInnerBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.rlMain.setOnClickListener(this);
            binding.tvAddItem.setOnClickListener(this);
            binding.tvSubtractItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_main:
                    if (itemSelectedList.containsKey(getLayoutPosition())) {
                        binding.rbSelect.setChecked(false);
                        binding.tvIngredientName.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                        binding.tvIngredientPrice.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                        binding.rlUpdateLayout.setVisibility(View.GONE);
                        itemSelectedList.remove(getLayoutPosition());

                        productAddonBeans.get(getLayoutPosition()).setSelectedQty(0);
                        binding.tvItemCount.setText(String.valueOf(productAddonBeans.get(getLayoutPosition()).getSelectedQty()));
                    } else {
                        if (productAddonBeans.get(getLayoutPosition()).getChoose_modifier_num() > 0 && itemSelectedList.size() == productAddonBeans.get(getLayoutPosition()).getChoose_modifier_num()) {
                            String msg = "You can select only " + productAddonBeans.get(getLayoutPosition()).getChoose_modifier_num() + " modifiers for " + productAddonBeans.get(getLayoutPosition()).getAddon().getAddon_category().getCat_name() + ".";
                          //  Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                            showMessage(msg);
                            return;
                        }

                        binding.rbSelect.setChecked(true);
                        binding.tvIngredientName.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
                        binding.tvIngredientPrice.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
                        binding.rlUpdateLayout.setVisibility(View.VISIBLE);

                        itemSelectedList.put(getLayoutPosition(), true);
                        productAddonBeans.get(getLayoutPosition()).setSelectedQty(1);
                        binding.tvItemCount.setText(String.valueOf(productAddonBeans.get(getLayoutPosition()).getSelectedQty()));

                    }
                    break;
                case R.id.tv_addItem:
                    if (productAddonBeans.get(getLayoutPosition()).getChoose_modifier_num() > productAddonBeans.get(getLayoutPosition()).getSelectedQty()) {
                        productAddonBeans.get(getLayoutPosition()).setSelectedQty((productAddonBeans.get(getLayoutPosition()).getSelectedQty() + 1));
                        binding.tvItemCount.setText(String.valueOf(productAddonBeans.get(getLayoutPosition()).getSelectedQty()));
                    }
                    break;
                case R.id.tv_subtractItem:
                    if (productAddonBeans.get(getLayoutPosition()).getSelectedQty() > 1) {
                        productAddonBeans.get(getLayoutPosition()).setSelectedQty((productAddonBeans.get(getLayoutPosition()).getSelectedQty() - 1));
                        binding.tvItemCount.setText(String.valueOf(productAddonBeans.get(getLayoutPosition()).getSelectedQty()));
                    }else {
                        binding.rbSelect.setChecked(false);
                        binding.tvIngredientName.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                        binding.tvIngredientPrice.setTextColor(mContext.getResources().getColor(R.color.tab_unselected_color));
                        binding.rlUpdateLayout.setVisibility(View.GONE);
                        itemSelectedList.remove(getLayoutPosition());

                        productAddonBeans.get(getLayoutPosition()).setSelectedQty(0);
                        binding.tvItemCount.setText(String.valueOf(productAddonBeans.get(getLayoutPosition()).getSelectedQty()));
                    }
                    break;
            }
            if (mCallback != null) {
                mCallback.onIngredientSelect(parentPosition, getLayoutPosition());
            }
        }

    }

    public void showMessage(String msg) {
        DialogUtils.showAlertDialogEndSelf(mContext, msg,true, new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {
                // dismiss();
            }

            @Override
            public void onNegativeClick() {

            }
        });

    }
}
