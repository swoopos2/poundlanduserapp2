package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellHospIngredientHeadingItemBinding;
import com.poundland.retail.interfaces.HospIngredientSelectionListener;
import com.poundland.retail.interfaces.UpdateIngredientListener;
import com.poundland.retail.model.responseModel.BaseIngredientsModel;

import java.util.List;


public class HospitalityIngredientAdapter extends RecyclerView.Adapter<HospitalityIngredientAdapter.ViewResource> {

    private Context mContext;
    private List<BaseIngredientsModel> mItems;
    private LayoutInflater mInflater;
    private HospIngredientSelectionListener mCallback;
    private UpdateIngredientListener updateIngredientListener;


    public HospitalityIngredientAdapter(Context context, List<BaseIngredientsModel> mItems, HospIngredientSelectionListener mCallback) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mItems = mItems;
        this.mCallback = mCallback;

    }

    public List<BaseIngredientsModel> getItems() {
        return mItems;
    }

    @NonNull
    @Override
    public HospitalityIngredientAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_hosp_ingredient_heading_item, parent, false);

        return new HospitalityIngredientAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalityIngredientAdapter.ViewResource holder, int position) {
        holder.binding.tvModifierName.setText(mItems.get(position).getProductAddonsHeader().getAddon().getAddon_category().getCat_name());


        if (mItems.get(position).getProductAddonsHeader().getIs_mandatory() == 0) {
            if (mItems.get(position).getProductAddonsHeader().getFree_modifier_num() == 0) {
                holder.binding.tvSelectQty.setText("Max." + mItems.get(position).getProductAddonsHeader().getChoose_modifier_num() + " select");
            } else
                holder.binding.tvSelectQty.setText("Max." + mItems.get(position).getProductAddonsHeader().getChoose_modifier_num() + " select and " + mItems.get(position).getProductAddonsHeader().getFree_modifier_num() + " free");

        } else {
            if (mItems.get(position).getProductAddonsHeader().getFree_modifier_num() == 0) {
                holder.binding.tvSelectQty.setText("Select at least " + mItems.get(position).getProductAddonsHeader().getChoose_modifier_num());
            } else
                holder.binding.tvSelectQty.setText("Select at least " + mItems.get(position).getProductAddonsHeader().getChoose_modifier_num() + " select and " + mItems.get(position).getProductAddonsHeader().getFree_modifier_num() + " free");

        }
        HospitalityIngredientInnerAdapterNew innerAdapter = new HospitalityIngredientInnerAdapterNew(mContext, position, mItems.get(position).getAddonsList(), mCallback);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.binding.rvModifierList.setLayoutManager(layoutManager);
        holder.binding.rvModifierList.setAdapter(innerAdapter);
    }


    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellHospIngredientHeadingItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
