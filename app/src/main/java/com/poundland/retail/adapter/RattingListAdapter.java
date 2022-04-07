package com.poundland.retail.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.databinding.LayoutCellRattingListBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.GetRattingResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.SHOW_ALL_CATEGORY;


/*for setting item for the recycler view for   */
public class RattingListAdapter extends RecyclerView.Adapter<RattingListAdapter.ViewResource> {

    private Context mContext;
    private List<GetRattingResponseModel.ReviewBean.ProductsBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;


    public RattingListAdapter(Context mContext, List<GetRattingResponseModel.ReviewBean.ProductsBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public RattingListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_ratting_list, parent, false);

        return new RattingListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RattingListAdapter.ViewResource holder, int position) {

        holder.binding.tvProductName.setText(data.get(position).getName());
        Glide.with(mContext).load(TextUtils.isEmpty(data.get(position).getModifier_images()) ? ApiRequestUrl.BASE_URL + data.get(position).getProduct_images() : ApiRequestUrl.BASE_URL + data.get(position).getModifier_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivProductImg);
        holder.binding.rbRateProduct.setRating(Float.parseFloat(data.get(position).getRatings()));
        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), SHOW_ALL_CATEGORY);
            }
        });

        holder.binding.rbRateProduct.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                data.get(position).setRatings(String.valueOf(holder.binding.rbRateProduct.getRating()));
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
        public LayoutCellRattingListBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
