package com.poundland.retail.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellFaqBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.FaqResponseModel;

import java.util.List;

import static com.poundland.retail.interfaces.Constants.SHOW_ALL_CATEGORY;


/*for setting item for the recycler view for   */
public class FaqListAdapter extends RecyclerView.Adapter<FaqListAdapter.ViewResource> {

    private Context mContext;
    private List<FaqResponseModel.FaqBean> data;
    private LayoutInflater mInflater;
    DrawerListner drawerListner;


    public FaqListAdapter(Context mContext, List<FaqResponseModel.FaqBean> data, DrawerListner drawerListner) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.data = data;
        this.drawerListner = drawerListner;
    }

    @NonNull
    @Override
    public FaqListAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_faq, parent, false);

        return new FaqListAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FaqListAdapter.ViewResource holder, int position) {

        holder.binding.tvQuestion.setText(data.get(position).getQuestion());
        holder.binding.tvAns.setText(Html.fromHtml(data.get(position).getAnswer()));


//        Glide.with(mContext).load(ApiRequestUrl.BASE_URL_IMAGE_CATEGORY + data.get(position).getImage()).apply(new RequestOptions()
//                .placeholder(R.drawable.default_image))
//                .into(holder.binding.ivCatImage);

        holder.binding.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(holder.getAdapterPosition(), SHOW_ALL_CATEGORY);
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
        public LayoutCellFaqBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }


}
