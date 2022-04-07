package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poundland.retail.R;
import com.poundland.retail.databinding.LayoutCellModifierFilterBinding;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.model.responseModel.VenueFilterDataResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*for setting item for the recycler view for   */
public class ProductStoreCategoryFilterAdapter extends RecyclerView.Adapter<ProductStoreCategoryFilterAdapter.ViewResource> implements Filterable {
    private HashMap<String, VenueFilterListItemAdapter> adapterList;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<VenueFilterDataResponseModel.FilterBean> filterListBeans;
    private List<VenueFilterDataResponseModel.FilterBean> mListFilterd;
    private ModifierSelectionListner modifierSelectionListner;
    private VenueFilterListItemAdapter venueFilterListItemAdapter;

    public HashMap<String, VenueFilterListItemAdapter> getAdapterList() {
        return adapterList;
    }

    public ProductStoreCategoryFilterAdapter(Context mContext, List<VenueFilterDataResponseModel.FilterBean> filterListBeans, ModifierSelectionListner modifierSelectionListner) {

        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.filterListBeans = filterListBeans;
        this.modifierSelectionListner = modifierSelectionListner;
        this.adapterList = new HashMap<>();
        this.mListFilterd = new ArrayList<>();
    }

    public void clearAllCheck() {

        for (Map.Entry<String, VenueFilterListItemAdapter> adapter : adapterList.entrySet()) {
            adapter.getValue().clearCheck();
        }
    }

    public void addAll(int pos, VenueFilterDataResponseModel.FilterBean filterListData) {
        if (this.filterListBeans != null) {
            if (pos != -1) {
                this.filterListBeans.get(pos).setFilter_list(filterListData.getFilter_list());
                notifyItemChanged(pos);
            } else {
                this.filterListBeans.add(filterListData);
                notifyDataSetChanged();
            }
        }
    }

    public void remove(int pos) {
        if (this.filterListBeans != null) {
            if (pos != -1) {
                this.filterListBeans.get(pos).setFilter_list(null);
                //this.modifierListBeans.get(pos).getFilter_list().remove(0);
                notifyDataSetChanged();
            } else {
                this.filterListBeans.remove(pos);
                notifyDataSetChanged();
            }

        }
    }

    @NonNull
    @Override
    public ProductStoreCategoryFilterAdapter.ViewResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cell_modifier_filter, parent, false);

        return new ProductStoreCategoryFilterAdapter.ViewResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductStoreCategoryFilterAdapter.ViewResource holder, int position) {

        if (mListFilterd.get(position).getFilter_type().equals("Category")) {
            holder.binding.tvModifierName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_category, 0, 0, 0);
        } else if (mListFilterd.get(position).getFilter_type().equals("Brand")) {
            holder.binding.tvModifierName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_brand, 0, 0, 0);
        } else if (mListFilterd.get(position).getFilter_type().equals("Modifier")) {
            holder.binding.tvModifierName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_modifier, 0, 0, 0);
        } else if (mListFilterd.get(position).getFilter_type().equals("Price")) {
            holder.binding.tvModifierName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic__payment, 0, 0, 0);
        } else if (mListFilterd.get(position).getFilter_type().equals("Rating")) {
            holder.binding.tvModifierName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_rating, 0, 0, 0);
        }



        holder.binding.tvModifierName.setText(mListFilterd.get(position).getFilter_type());

        if (position == 0) {
            holder.binding.ivExpand.setImageResource(R.drawable.ic_up_arrow);
            holder.binding.rvModifierList.setVisibility(View.VISIBLE);
        } else {
            holder.binding.ivExpand.setImageResource(R.drawable.ic_down_arrow);
        }

        venueFilterListItemAdapter = new VenueFilterListItemAdapter(mContext, mListFilterd.get(position).getFilter_list(),
                -1, mListFilterd.get(position).getIsMultiple(), new ModifierSelectionListner() {

            @Override
            public void onModifierSelect(int pos_1st, int pos_2nd) {
                modifierSelectionListner.onModifierSelect(position, pos_2nd);
            }

            @Override
            public void onModifierDeselect(int pos_1st, int pos_2nd) {
                modifierSelectionListner.onModifierDeselect(position, pos_2nd);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        holder.binding.rvModifierList.setLayoutManager(layoutManager);
        holder.binding.rvModifierList.setAdapter(venueFilterListItemAdapter);

        adapterList.put(mListFilterd.get(position).getFilter_type(), venueFilterListItemAdapter);

        holder.binding.tvModifierName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.binding.rvModifierList.getVisibility() == View.GONE) {
                    holder.binding.rvModifierList.setVisibility(View.VISIBLE);
                    holder.binding.ivExpand.setImageResource(R.drawable.ic_up_arrow);
                } else {
                    holder.binding.rvModifierList.setVisibility(View.GONE);
                    holder.binding.ivExpand.setImageResource(R.drawable.ic_down_arrow);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListFilterd == null ? 0 : mListFilterd.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ViewResource extends RecyclerView.ViewHolder {
        public LayoutCellModifierFilterBinding binding;

        ViewResource(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                try {
                    List<VenueFilterDataResponseModel.FilterBean> filteredList = new ArrayList<>();
                    //  match condition. depending on your requirement
                    for (int i = 0; i < filterListBeans.size(); i++) {
                        if (filterListBeans.get(i).getFilter_list() != null && filterListBeans.get(i).getFilter_list().size() > 0) {
                            filteredList.add(filterListBeans.get(i));
                        }
                    }

                    mListFilterd = filteredList;
                } catch (Exception e) {
                    mListFilterd = filterListBeans;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListFilterd;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListFilterd = (List<VenueFilterDataResponseModel.FilterBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
