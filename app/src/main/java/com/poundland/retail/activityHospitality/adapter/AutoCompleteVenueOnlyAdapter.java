package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.HeaderSearchResponseModel;

import java.util.ArrayList;
import java.util.List;

import static com.poundland.retail.interfaces.Constants.AUTO_COMPLETE_VENUE;

public class AutoCompleteVenueOnlyAdapter extends ArrayAdapter<HeaderSearchResponseModel.VenuesSearchDataBean> {
    private List<HeaderSearchResponseModel.VenuesSearchDataBean> filteredPlacesList;
    private List<HeaderSearchResponseModel.CategoriesNameBean> categoriesName;
    private List<HeaderSearchResponseModel.VenuesSearchDataBean> venuesSearchData;
    private DrawerListner drawerListner;
    private boolean isCalled;

    public AutoCompleteVenueOnlyAdapter(@NonNull Context context, @NonNull  List<HeaderSearchResponseModel.CategoriesNameBean> categoriesName, List<HeaderSearchResponseModel.VenuesSearchDataBean> venuesSearchData, DrawerListner drawerListner) {
        super(context, 0, venuesSearchData);
        this.categoriesName = categoriesName;
        this.drawerListner = drawerListner;
        if (this.venuesSearchData != null)
            this.venuesSearchData.clear();
       this. venuesSearchData = new ArrayList<>(venuesSearchData);
    }

    public HeaderSearchResponseModel.VenuesSearchDataBean getObject(int position) {
        return venuesSearchData.get(position);
    }
   /* public void clearData() {
        if (allPlacesList != null)
            allPlacesList.clear();
        notifyDataSetChanged();
    }*/

    @NonNull
    @Override
    public Filter getFilter() {
        return placeFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_cell_auto_complete_venue_only_adapter, parent, false
            );
        }

        ImageView placeImage = convertView.findViewById(R.id.iv_venue);
        TextView placeLabel = convertView.findViewById(R.id.textViewName);
        RelativeLayout rl_main = convertView.findViewById(R.id.rl_main);
        RecyclerView recyclerView = convertView.findViewById(R.id.rv_category_list);

        HeaderSearchResponseModel.VenuesSearchDataBean place = getItem(position);
        if (place != null) {
            placeLabel.setText(place.getVenue_name());
            Glide.with(getContext()).load(TextUtils.isEmpty(place.getVenue_images()) ? ApiRequestUrl.BASE_URL_VENUE + place.getVenue_images() : ApiRequestUrl.BASE_URL_VENUE + place.getVenue_images()).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(placeImage);
        }

        if (position == 0) {
            AutoCompleteCategoryAdapter adapter = new AutoCompleteCategoryAdapter(getContext(), categoriesName, new DrawerListner() {
                @Override
                public void onDrawerSelect(int position, int searchCat) {
                    drawerListner.onDrawerSelect(position, searchCat);
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }

        rl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(position, AUTO_COMPLETE_VENUE);
            }
        });

        return convertView;
    }

    private Filter placeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            filteredPlacesList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredPlacesList.addAll(venuesSearchData);
            } else {
                filteredPlacesList.addAll(venuesSearchData);
            }

            results.values = filteredPlacesList;
            results.count = filteredPlacesList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            notifyDataSetChanged();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((HeaderSearchResponseModel.VenuesSearchDataBean) resultValue).getVenue_name();
        }
    };
}
