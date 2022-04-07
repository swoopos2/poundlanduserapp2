package com.poundland.retail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.XxLayoutCellModifierCircleShapeBinding;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.model.responseModel.SingleProductDetailResponseModel;

import java.util.List;


/*for setting item for the recycler view for   */
public class ModifierItemAdapter extends RecyclerView.Adapter<ModifierItemAdapter.ViewResource> {

    Context mContext;
    private LayoutInflater mInflater;
    private ModifierSelectionListner onModifierSelect;
    private List<SingleProductDetailResponseModel.ProductsBean.ModifierListBean.TypeListBean> modifierListitemName;
    private int checkedPosition = -1;
    private String type;

    public ModifierItemAdapter(Context mContext, List<SingleProductDetailResponseModel.ProductsBean.ModifierListBean.TypeListBean> modifierListitemName, String type, ModifierSelectionListner onModifierSelect, int checkedPosition) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.modifierListitemName = modifierListitemName;
        this.onModifierSelect = onModifierSelect;
        this.checkedPosition = checkedPosition;
        this.type = type;
    }

    @NonNull
    @Override
    public ModifierItemAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_modifier_item, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xx_layout_cell_modifier_circle_shape, parent, false);

        return new ModifierItemAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ModifierItemAdapter.ViewResource holder, final int position) {


       /* FOR ...*****layout_cell_modifier_item*****...
       CheckBox radioButton = holder.binding.cbModifierName;
        setSelection(position, radioButton);

        radioButton.setText(modifierListitemName.get(position));

        holder.binding.cbModifierName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPosition = position;
                if (checkedPosition >= 0) {
                    notifyDataSetChanged();
                }
                if (holder.binding.cbModifierName.isChecked()){
                    onModifierSelect.onModifierSelect(-1,position);
                }else
                onModifierSelect.onModifierDeselect(-1,position);
            }
        });*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*  FOR   ....********** xx_layout_cell_modifier_circle_shape*/
        /*  android:background="@drawable/ic_block" */

        if (type.equalsIgnoreCase(mContext.getString(R.string.color))) {
            holder.binding.ivModifier.setVisibility(View.VISIBLE);
            holder.binding.tvModifierName.setVisibility(View.GONE);
            holder.binding.ivModifier.setColorFilter(Color.parseColor(modifierListitemName.get(position).getCode()), android.graphics.PorterDuff.Mode.MULTIPLY);
            setSelectionColor(position, holder.binding.ivModifierCheck);
        } else if (type.equalsIgnoreCase(mContext.getString(R.string.size_of_modifier))) {
            holder.binding.ivModifier.setVisibility(View.GONE);
            holder.binding.tvModifierName.setVisibility(View.VISIBLE);
            holder.binding.tvModifierName.setText(modifierListitemName.get(position).getName());
            setSelectionSize(position, holder.binding.tvModifierName);
        } else {
            holder.binding.ivModifier.setVisibility(View.GONE);
            holder.binding.tvModifierName.setVisibility(View.VISIBLE);
            holder.binding.tvModifierName.setText(modifierListitemName.get(position).getName());
            setSelectionOther(position, holder.binding.tvModifierName);
        }

        holder.binding.rlArriving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPosition = position;
                if (checkedPosition >= 0) {
                    notifyDataSetChanged();
                }

                if (type.equalsIgnoreCase(mContext.getString(R.string.color))) {
                    setSelectionColor(position, holder.binding.ivModifierCheck);
                } else if (type.equalsIgnoreCase(mContext.getString(R.string.size_of_modifier))) {
                    setSelectionSize(position, holder.binding.tvModifierName);
                } else setSelectionOther(position, holder.binding.tvModifierName);


                onModifierSelect.onModifierSelect(-1, position);
            }
        });
    }

    public void setSelectionColor(int pos, ImageView ivModifier) {
        if (pos == checkedPosition) {
            ivModifier.setVisibility(View.VISIBLE);
        } else {
            ivModifier.setVisibility(View.GONE);
        }
    }

    public void setSelectionSize(int pos, TextView textView) {
        if (pos == checkedPosition) {
            textView.setTextColor(mContext.getResources().getColor(R.color.color_white));
            textView.setBackgroundResource(R.drawable.circle_app_color);
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.color_black));
            textView.setBackgroundResource(R.drawable.circle_white_strok_grey);
        }
    }

    public void setSelectionOther(int pos, TextView textView) {
        if (pos == checkedPosition) {
            textView.setTextColor(mContext.getResources().getColor(R.color.app_header_color));
            textView.setBackgroundResource(R.drawable.grey_rect_border_drawable);
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.color_black));
            textView.setBackgroundResource(R.drawable.grey_rect_border_drawable);
        }
    }

    public void setSelection(int pos, CheckBox view) {
        if (pos == checkedPosition) {
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
        // public LayoutCellModifierItemBinding binding;
        public XxLayoutCellModifierCircleShapeBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
