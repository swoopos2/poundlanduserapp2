package com.poundland.retail.activityHospitality.adapter;

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
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.databinding.LayoutCellSingleTextNImageBinding;
import com.poundland.retail.interfaces.AllergyListener;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;

import java.util.HashMap;
import java.util.List;

public class AllergyAdapter extends RecyclerView.Adapter<AllergyAdapter.ViewResource> {

    private Context mContext;
    private List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.AllergiesListBean> allergiesList;
    private LayoutInflater mInflater;
    private AllergyListener allergyListener;
    private int preSelected = -1;
    private LayoutCellSingleTextNImageBinding lastSelectedBinding;
    private HashMap<Integer, String> list;

    public AllergyAdapter(Context mContext, List<HospitalityVenueProductResponseModel.ProductsBean.DataBean.AllergiesListBean> allergiesList, AllergyListener allergyListener) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.allergiesList = allergiesList;
        this.allergyListener = allergyListener;
        list = new HashMap<>();

    }

   /* public void addItems(List<ReservationBookingDate> mItem) {
        this.allergies_list = allergies_list;
        notifyItemInserted(this.allergies_list.size());
    }*/


    @NonNull
    @Override
    public AllergyAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_single_text_n_image, parent, false);

        return new AllergyAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllergyAdapter.ViewResource holder, int position) {

        holder.binding.cvMain.setBackgroundResource(R.drawable.white_filled_grey_border_rect);
        holder.binding.tvAllergyName.setText(allergiesList.get(position).getAllergie().getName());
        Glide.with(mContext).load(ApiRequestUrl.BASE_URL + allergiesList.get(position).getAllergie().getImage()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(holder.binding.ivAlleryImg);


    }


    @Override
    public int getItemCount() {
        return allergiesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewResource extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LayoutCellSingleTextNImageBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.cvMain.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           /* if (!list.containsKey(allergiesList.get(getLayoutPosition()).getAllergie().getId())) {
                binding.cvMain.setBackgroundResource(R.drawable.white_filled_red_border_rect);
                list.put(allergiesList.get(getLayoutPosition()).getAllergie().getId(), allergiesList.get(getLayoutPosition()).getAllergie().getName());
            } else {
                binding.cvMain.setBackgroundResource(R.drawable.white_filled_grey_border_rect);
                list.remove(allergiesList.get(getLayoutPosition()).getAllergie().getId());
            }
            allergyListener.onAllergySelect(list);*/
        }
    }

}
