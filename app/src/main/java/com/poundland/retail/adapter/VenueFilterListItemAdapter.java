package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellModifierItemBinding;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.model.responseModel.VenueFilterDataResponseModel;

import java.util.HashMap;
import java.util.List;

/*for setting item for the recycler view for   */
public class VenueFilterListItemAdapter extends RecyclerView.Adapter<VenueFilterListItemAdapter.ViewResource> {
    private HashMap<Integer, String> selected = new HashMap<>();
    Context mContext;
    private LayoutInflater mInflater;
    private ModifierSelectionListner selectionListner;
    private List<VenueFilterDataResponseModel.FilterBean.FilterListBean> modifierListitemName;
    private int checkedPosition = -1;
    private int isMultiple;

    public HashMap<Integer, String> getSelected() {
        return selected;
    }

    public void clearCheck() {
        for (int j = 0; j < modifierListitemName.size(); j++) {
            modifierListitemName.get(j).setChecked(false);
        }
        selected.clear();
        notifyDataSetChanged();
    }

    public VenueFilterListItemAdapter(Context mContext, List<VenueFilterDataResponseModel.FilterBean.FilterListBean> modifierListitemName,
                                      int checkedPosition, int isMultiple, ModifierSelectionListner selectionListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.modifierListitemName = modifierListitemName;
        this.checkedPosition = checkedPosition;
        this.isMultiple = isMultiple;
        this.selectionListner = selectionListner;
        //this.selected= new HashMap<>();
    }

    @NonNull
    @Override
    public VenueFilterListItemAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_modifier_item, parent, false);

        return new VenueFilterListItemAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueFilterListItemAdapter.ViewResource holder, int position) {
        if (modifierListitemName.get(position).isChecked()) {
            selected.put(position, modifierListitemName.get(position).getFilter_value());
        }
        CheckBox radioButton = holder.binding.cbModifierName;
        //setSelection(position, radioButton);

        radioButton.setText(modifierListitemName.get(position).getDisplay_name());

        radioButton.setChecked(modifierListitemName.get(position).isChecked());

        holder.binding.cbModifierName.setOnClickListener(v -> {
            checkedPosition = position;
            if (checkedPosition >= 0) {
                if (isMultiple == 0) {
                    notifyDataSetChanged();
                }

                if (holder.binding.cbModifierName.isChecked()) {

                    if (selected != null)
                        modifierListitemName.get(position).setChecked(true);
                    selected.put(holder.getAdapterPosition(), modifierListitemName.get(position).getFilter_value());
                    selectionListner.onModifierSelect(-1, position);


                } else {

                    if (selected != null)
                        modifierListitemName.get(position).setChecked(false);
                    selected.remove(holder.getAdapterPosition());
                    selectionListner.onModifierDeselect(-1, position);

                }
            }
        });
    }

    public void setSelection(int pos, CheckBox view) {
        if (pos == checkedPosition || modifierListitemName.get(pos).isChecked()) {
            view.setChecked(true);
        } else view.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return modifierListitemName == null ? 0 : modifierListitemName.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellModifierItemBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}


 /*holder.binding.rvModifierList.addOnItemTouchListener(new MyRecyclerItemClickListener(mContext,
                        holder.binding.rvModifierList, new MyRecyclerItemClickListener.OnItemClickListener() {


                    @Override
                    public void onItemClick(View view, int position) {
                        HelperClass.toast(mContext,"1"+modifierListBeans.get(position).getType());
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );*/
        /*StringBuilder sb = new StringBuilder(14);
        sb.append(firstname).append(" ").append(lastname);
        System.out.println(sb.toString());*/