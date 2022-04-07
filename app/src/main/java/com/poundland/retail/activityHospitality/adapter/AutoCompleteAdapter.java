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

import static com.poundland.retail.interfaces.Constants.AUTO_COMPLETE_PRODUCT;

public class AutoCompleteAdapter extends ArrayAdapter<HeaderSearchResponseModel.SuggestionBean> {
    private List<HeaderSearchResponseModel.SuggestionBean> allPlacesList;
    private List<HeaderSearchResponseModel.SuggestionBean> filteredPlacesList;
    private List<HeaderSearchResponseModel.CategoriesNameBean> categoriesName;
    private List<HeaderSearchResponseModel.VenuesSearchDataBean> venuesSearchData;
    private DrawerListner drawerListner;
    private boolean isCalled;

    public AutoCompleteAdapter(@NonNull Context context, @NonNull List<HeaderSearchResponseModel.SuggestionBean> placesList
            , List<HeaderSearchResponseModel.CategoriesNameBean> categoriesName, List<HeaderSearchResponseModel.VenuesSearchDataBean> venuesSearchData, DrawerListner drawerListner) {
        super(context, 0, placesList);
        this.categoriesName = categoriesName;
        this.venuesSearchData = venuesSearchData;
        this.drawerListner = drawerListner;
        if (allPlacesList != null)
            allPlacesList.clear();
        allPlacesList = new ArrayList<>(placesList);
    }

    public HeaderSearchResponseModel.SuggestionBean getObject(int position) {
        return allPlacesList.get(position);
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_cell_suggestion, parent, false
            );
        }

        ImageView placeImage = convertView.findViewById(R.id.iv_cat_image);
        TextView placeLabel = convertView.findViewById(R.id.tv_cat_name);
        RelativeLayout rl_main = convertView.findViewById(R.id.rl_main);
        RecyclerView recyclerView = convertView.findViewById(R.id.rv_category_list);
        RecyclerView rv_venue_list = convertView.findViewById(R.id.rv_venue_list);

        HeaderSearchResponseModel.SuggestionBean place = getItem(position);
        if (place != null) {
            placeLabel.setText(place.getProduct_name());
            Glide.with(getContext()).load(TextUtils.isEmpty(place.getModifier_images()) ? ApiRequestUrl.BASE_URL + place.getImages() : ApiRequestUrl.BASE_URL + place.getModifier_images()).apply(new RequestOptions()
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

            AutoCompleteVenueAdapter adapter1 = new AutoCompleteVenueAdapter(getContext(), venuesSearchData, new DrawerListner() {
                @Override
                public void onDrawerSelect(int position, int searchVenue) {
                    drawerListner.onDrawerSelect(position, searchVenue);
                }
            });
            rv_venue_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rv_venue_list.setAdapter(adapter1);
        }

        rl_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerListner.onDrawerSelect(position, AUTO_COMPLETE_PRODUCT);
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
                filteredPlacesList.addAll(allPlacesList);
            } else {
                /*String filterPattern = constraint.toString().toLowerCase().trim();
                for (HeaderSearchResponseModel.SuggestionBean place : allPlacesList) {
                     if (place.getProduct_name().toLowerCase().contains(filterPattern)) {
                    filteredPlacesList.add(place);
                     }
                }*/
                filteredPlacesList.addAll(allPlacesList);
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
            return ((HeaderSearchResponseModel.SuggestionBean) resultValue).getProduct_name();
        }
    };
}
