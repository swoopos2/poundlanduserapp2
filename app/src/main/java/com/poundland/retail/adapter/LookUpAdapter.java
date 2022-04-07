package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellLookupBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.GetUserLocationResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.UNIVERSAL_CODE;


/*for setting item for the recycler view for   */
public class LookUpAdapter extends RecyclerView.Adapter<LookUpAdapter.ViewResource> {

    private Context mContext;
    private List<GetUserLocationResponseModel.DataBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;


    public LookUpAdapter(Context mContext, List<GetUserLocationResponseModel.DataBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public LookUpAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_lookup, parent, false);

        return new LookUpAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LookUpAdapter.ViewResource holder, int position) {

        holder.binding.tvAddress.setText(data  .get(position).getLine_1());

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(),UNIVERSAL_CODE);
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
        public LayoutCellLookupBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
