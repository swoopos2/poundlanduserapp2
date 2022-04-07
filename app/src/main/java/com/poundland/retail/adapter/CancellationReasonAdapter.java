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
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.REPLACE_REFUND;


/*for setting item for the recycler view for   */
public class CancellationReasonAdapter extends RecyclerView.Adapter<CancellationReasonAdapter.ViewResource>  {

    Context mContext;
    private LayoutInflater mInflater;
    private DrawerListner selectionListner;
    List<MyOrderResponseModel.ReturnReasonsBean> returnReasonsBeanList ;
    private int checkedPosition = -1;
    public CancellationReasonAdapter(Context mContext, List<MyOrderResponseModel.ReturnReasonsBean> returnReasonsBeanList, DrawerListner selectionListner, int checkedPosition) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.returnReasonsBeanList = returnReasonsBeanList;
        this.selectionListner = selectionListner;
        this.checkedPosition=checkedPosition;
    }

    @NonNull
    @Override
    public CancellationReasonAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_modifier_item, parent, false);

        return new CancellationReasonAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CancellationReasonAdapter.ViewResource holder, final int position) {


        CheckBox radioButton = holder.binding.cbModifierName;
        setSelection(position, radioButton);

        radioButton.setText(returnReasonsBeanList.get(position).getReason());

        holder.binding.cbModifierName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPosition = position;
                if (checkedPosition >= 0) {
                    notifyDataSetChanged();
                }
                selectionListner.onDrawerSelect(position,REPLACE_REFUND);
            }
        });

    }

    public void setSelection(int pos, CheckBox view) {
        if (pos == checkedPosition) {
            view.setChecked(true);
        } else view.setChecked(false);
    }


    @Override
    public int getItemCount() {
        return returnReasonsBeanList == null ? 0 : returnReasonsBeanList.size();
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
