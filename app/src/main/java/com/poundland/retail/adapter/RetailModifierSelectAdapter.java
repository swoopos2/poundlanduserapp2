package com.poundland.retail.adapter;

import android.content.Context;
import android.graphics.Paint;
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
import com.poundland.retail.databinding.LayoutCellDialogModifierSelectionBinding;
import com.poundland.retail.interfaces.ModifierAddListner;
import com.poundland.retail.model.responseModel.ModifierListByProductIdModel;

import java.util.List;


public class RetailModifierSelectAdapter extends RecyclerView.Adapter<RetailModifierSelectAdapter.ViewResource> {

    private Context mContext;
    private List<ModifierListByProductIdModel.ModifiersBean> data;
    private LayoutInflater mInflater;
    private ModifierAddListner listner;

    public RetailModifierSelectAdapter(Context mContext, List<ModifierListByProductIdModel.ModifiersBean> data, ModifierAddListner listner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.listner = listner;
    }

    @NonNull
    @Override
    public RetailModifierSelectAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_dialog_modifier_selection, parent, false);

        return new RetailModifierSelectAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RetailModifierSelectAdapter.ViewResource holder, int position) {

        holder.binding.tvModifierName.setText(data.get(position).getModifier_name());
        if (data.get(position).getOffer_title() == null || data.get(position).getOffer_type() == null) {
            holder.binding.tvDiscountPrice.setVisibility(View.GONE);
        } else holder.binding.tvDiscountPrice.setVisibility(View.VISIBLE);
        holder.binding.tvDiscountPrice.setPaintFlags(holder.binding.tvDiscountPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.binding.tvDiscountPrice.setText(String.format("%s%s", mContext.getString(R.string.pound), data.get(position).getSelling_price()));

        holder.binding.tvPrice.setText(String.format("%s%s", mContext.getString(R.string.pound), data.get(position).getNew_price()));

        if (data.get(position).getAvl_quantity() > 0) {
            holder.binding.checkBox.setVisibility(View.VISIBLE);
            holder.binding.tvStatus.setText(String.format("%s %s", mContext.getString(R.string.status), mContext.getString(R.string.in_stock)));
        } else {
            holder.binding.checkBox.setVisibility(View.INVISIBLE);
            holder.binding.tvStatus.setText(String.format("%s %s", mContext.getString(R.string.status), mContext.getString(R.string.out_of_stock)));
        }


        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + data.get(position).getModifier_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImage);

        holder.binding.checkBox.setChecked(data.get(position).isMChecked());
        holder.binding.checkBox.setTag(position);
        holder.binding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.binding.checkBox.getTag();

                if (data.get(pos).isMChecked()) {
                    data.get(pos).setMChecked(false);
                    holder.binding.tvQty.setVisibility(View.GONE);
                    holder.binding.rlUpdateLayout.setVisibility(View.GONE);
                } else {
                    data.get(pos).setMChecked(true);
                    holder.binding.tvQty.setVisibility(View.VISIBLE);
                    holder.binding.rlUpdateLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.binding.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.binding.checkBox.getTag();

                if (holder.binding.checkBox.isChecked()){
                    holder.binding.checkBox.setChecked(false);
                }else holder.binding.checkBox.setChecked(true);

                if (data.get(pos).isMChecked()) {
                    data.get(pos).setMChecked(false);
                    holder.binding.tvQty.setVisibility(View.GONE);
                    holder.binding.rlUpdateLayout.setVisibility(View.GONE);
                } else {
                    data.get(pos).setMChecked(true);
                    holder.binding.tvQty.setVisibility(View.VISIBLE);
                    holder.binding.rlUpdateLayout.setVisibility(View.VISIBLE);
                }
            }
        });


        holder.binding.tvSubtractItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = Integer.parseInt(String.valueOf(holder.binding.tvItemCount.getText()));
                if (count == 1) {
                    holder.binding.tvItemCount.setText("1");
                    data.get(position).setQuantity(count);
                } else {
                    count -= 1;
                    holder.binding.tvItemCount.setText("" + count);
                    data.get(position).setQuantity(count);
                }
            }
        });
        holder.binding.tvAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = Integer.parseInt(String.valueOf(holder.binding.tvItemCount.getText()));
                count++;
                holder.binding.tvItemCount.setText(String.valueOf(count));
                data.get(position).setQuantity(count);
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
        public LayoutCellDialogModifierSelectionBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
