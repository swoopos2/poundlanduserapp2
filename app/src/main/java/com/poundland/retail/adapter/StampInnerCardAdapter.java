package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellStampInnerCardAdapterBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.zzznewPoundland.model.StampDataModel;

import java.util.List;

public class StampInnerCardAdapter extends RecyclerView.Adapter<StampInnerCardAdapter.ViewResource> {

    private Context context;
    DrawerListner drawerListner;
    private List<StampDataModel.ListDataBean> listData;

    public StampInnerCardAdapter(Context context, List<StampDataModel.ListDataBean> listData, DrawerListner drawerListner) {
        this.context = context;
        this.listData = listData;
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

        if (listData.get(position).getStampType() == 4) {
            Glide.with(context).load("")
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_red_circle_stamp_bg)).
                    into(holder.binding.ivStampBg);


        } else if (listData.get(position).getStampType() == 3) {
            Glide.with(context).load("")
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_green_circle_stamp_bg)).
                    into(holder.binding.ivStampBg);
        } else if (listData.get(position).getStampType() == 2) {
            Glide.with(context).load("")
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_scratch_circle_stamp_bg)).
                    into(holder.binding.ivStampBg);
        } else {
            holder.binding.tvStampNo.setVisibility(View.VISIBLE);
            holder.binding.tvStampNo.setText(listData.get(position).getStampNo());
            Glide.with(context).load("")
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_blue_circle_stamp_bg)).
                    into(holder.binding.ivStampBg);
        }

        // holder.binding.imageViewIcon.setImageResource(data[position].icon);

        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DialogUtils.stampTextInfoDialog(context,"","","",true);

                if (listData.get(position).getStampType() == 1)
                    return;

                if (listData.get(position).getStampType() == 4 || listData.get(position).getStampType() == 3) {

                    DialogUtils.stampTextInfoDialog(context, "" + listData.get(position).getStampType(), "", "", false);

                } else if (listData.get(position).getStampType() == 2) {

                    DialogUtils.scratchStampForInfo(context, "", "", "", new OnDialogClickListener() {
                        @Override
                        public void onPositiveClick() {
                            DialogUtils.dialogScratchedSuccessfullyMessage(context, "", "", "", new DrawerListner() {
                                @Override
                                public void onDrawerSelect(int position, int clickId) {

                                }
                            });
                        }

                        @Override
                        public void onNegativeClick() {

                        }
                    });

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
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
