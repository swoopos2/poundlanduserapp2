package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellStampInnerCardAdapterBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.responseModel.GetCustomerLoyaltyResponseModel;

import java.util.List;

public class StampInnerCardAdapter extends RecyclerView.Adapter<StampInnerCardAdapter.ViewResource> {

   private Context context;
    DrawerListner drawerListner;
    private List<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> rollAccessList;
    public StampInnerCardAdapter(Context context, List<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> rollAccessList, DrawerListner drawerListner) {
        this.context = context;
        this.rollAccessList = rollAccessList;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public StampInnerCardAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_stamp_inner_card_adapter, parent, false);

        return new StampInnerCardAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StampInnerCardAdapter.ViewResource holder, int position) {

       /* holder.binding.textViewName.setText(data[position].name);
        holder.binding.imageViewIcon.setImageResource(data[position].icon);*/

        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogUtils.stampTextInfoDialog(context,"","","",true);


                DialogUtils.stampImageInfoDialog(context, "", "", "", new OnDialogClickListener() {
                    @Override
                    public void onPositiveClick() {
                        DialogUtils.stampTextInfoDialog(context,"","","",false);
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return rollAccessList == null ? 0 : rollAccessList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellStampInnerCardAdapterBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
