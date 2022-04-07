package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellModifierBinding;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.model.responseModel.SingleProductDetailResponseModel;

import java.util.List;


/*for setting item for the recycler view for   */
public class ModifierAdapter extends RecyclerView.Adapter<ModifierAdapter.ViewResource> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<SingleProductDetailResponseModel.ProductsBean.ModifierListBean> modifierListBeans;
    private ModifierSelectionListner modifierSelectionListner;

    public ModifierAdapter(Context mContext, List<SingleProductDetailResponseModel.ProductsBean.ModifierListBean> modifierListBeans, ModifierSelectionListner modifierSelectionListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.modifierListBeans = modifierListBeans;
        this.modifierSelectionListner = modifierSelectionListner;
    }

    @NonNull
    @Override
    public ModifierAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_modifier, parent, false);

        return new ModifierAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ModifierAdapter.ViewResource holder, int position) {

        if (modifierListBeans.get(position).getType_list().size() > 0) {
            holder.binding.tvModifierName.setText(modifierListBeans.get(position).getType());
        }else {
            holder.binding.tvModifierName.setVisibility(View.GONE);
        }
        ModifierItemAdapter reviewAdapter = new ModifierItemAdapter(mContext, modifierListBeans.get(position).getType_list(),
                modifierListBeans.get(position).getType(), new ModifierSelectionListner() {
            @Override
            public void onModifierSelect(int pos_1st, int pos_2nd) {
                modifierSelectionListner.onModifierSelect(holder.getAdapterPosition(), pos_2nd);
            }

            @Override
            public void onModifierDeselect(int pos_1st, int pos_2nd) {
                modifierSelectionListner.onModifierDeselect(holder.getAdapterPosition(), pos_2nd);
            }
        }, 0);
     //   final GridLayoutManager layoutManager = new GridLayoutManager(mContext, 8);
       // holder.binding.rvModifierList.setLayoutManager(layoutManager);
        holder.binding.rvModifierList.setAdapter(reviewAdapter);

    }

    @Override
    public int getItemCount() {
        return modifierListBeans == null ? 0 : modifierListBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellModifierBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
