package com.poundland.retail.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
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
import com.poundland.retail.databinding.LayoutCellReviewBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.SingleProductDetailResponseModel;

import java.util.List;


/*for setting item for the recycler view for   */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewResource> {

    Context mContext;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;
    private List<SingleProductDetailResponseModel.ProductRatingBean.ReviewsBean> reviewsBeanList;

    public ReviewAdapter(Context mContext, List<SingleProductDetailResponseModel.ProductRatingBean.ReviewsBean> reviewsBeanList, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.reviewsBeanList = reviewsBeanList;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_review, parent, false);

        return new ReviewAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewAdapter.ViewResource holder, int position) {


        holder.binding.tvUserName.setText(reviewsBeanList.get(position).getCust_name());
        if (reviewsBeanList.get(position).getReview() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.tvReview.setText(Html.fromHtml(reviewsBeanList.get(position).getReview(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                holder.binding.tvReview.setText(Html.fromHtml(reviewsBeanList.get(position).getReview()));
            }
        }


        holder.binding.rbRating.setRating(reviewsBeanList.get(position).getRattings());

        if (!TextUtils.isEmpty(reviewsBeanList.get(position).getCust_image()) && reviewsBeanList.get(position).getCust_image().contains("https:")) {
            Glide.with(mContext).load(reviewsBeanList.get(position).getCust_image()).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_user1))
                    .into(holder.binding.ivUserImage);
        }else {
            Glide.with(mContext).load(ApiRequestUrl.BASE_URL + reviewsBeanList.get(position).getCust_image()).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_user1))
                    .into(holder.binding.ivUserImage);
        }


                /* holder.binding.rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition());
            }
        });*/

    }


    @Override
    public int getItemCount() {
        return reviewsBeanList == null ? 0 : reviewsBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellReviewBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
