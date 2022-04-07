package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellAddressBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.AddressResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.ADDRESS;
import static com.poundland.retail.interfaces.Constants.DELETE_ITEM;
import static com.poundland.retail.interfaces.Constants.EDIT_ITEM;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewResource> {

    private Context mContext;
    private List<AddressResponseModel.AddressessBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;
    private String fromWhere;

    public AddressAdapter(Context mContext, List<AddressResponseModel.AddressessBean> data, String fromWhere, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.fromWhere = fromWhere;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_address, parent, false);

        return new AddressAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressAdapter.ViewResource holder, int position) {

        if (fromWhere.equals(mContext.getString(R.string.cart))) {
            holder.binding.ivDelete.setVisibility(View.GONE);
        }
        else holder.binding.ivDelete.setVisibility(View.VISIBLE);

        String address = data.get(position).getArea() +", "+ data.get(position).getLandmark()+", "+data.get(position).getCity() + ", "+
                data.get(position).getCountry()+" "+ data.get(position).getPincode();
        address = address.replace("null", "");

        holder.binding.tvAddress.setText(address);

        holder.binding.tvAddressType.setText(data.get(position).getType());

        if (data.get(position).getType() != null) {
            if (data.get(position).getType().equalsIgnoreCase(mContext.getString(R.string.work))) {
                holder.binding.ivItemImage.setImageResource(R.drawable.ic_work);
            } else if (data.get(position).getType().equalsIgnoreCase(mContext.getString(R.string.home))) {
                holder.binding.ivItemImage.setImageResource(R.drawable.ic_home);
            }
        }

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), ADDRESS);
            }
        });

        holder.binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), EDIT_ITEM);
            }
        });

        holder.binding.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), DELETE_ITEM);
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
        public LayoutCellAddressBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
