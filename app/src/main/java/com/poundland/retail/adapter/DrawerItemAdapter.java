package com.poundland.retail.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.DrawerItemRowBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.DataModel;

import static com.poundland.retail.interfaces.Constants.DRAWER;

/*for setting item for the recycler view for   */
public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemAdapter.ViewResource> {

    Context mContext;
    DataModel data[] = null;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;


    public DrawerItemAdapter(Context mContext, DataModel[] data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public DrawerItemAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item_row, parent, false);

        return new DrawerItemAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DrawerItemAdapter.ViewResource holder, int position) {
        if (position==10){
            holder.binding.imageViewIcon.setVisibility(View.GONE);

            Typeface typeface = ResourcesCompat.getFont(mContext, R.font.montserrat_bold);
            holder.binding.textViewName.setTypeface(typeface);

            holder.binding.textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP,22);
        }


        holder.binding.textViewName.setText(data[position].name);

        holder.binding.imageViewIcon.setImageResource(data[position].icon);

        holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getLayoutPosition(),DRAWER);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public DrawerItemRowBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
