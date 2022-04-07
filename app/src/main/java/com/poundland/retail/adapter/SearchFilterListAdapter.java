package com.poundland.retail.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.adapter.AutoCompleteCategoryAdapter;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.HeaderSearchResponseModel;

import java.util.List;


/*for setting item for the recycler view for   */
public class SearchFilterListAdapter extends ArrayAdapter<HeaderSearchResponseModel.SuggestionBean> /*implements Filterable*/ {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<HeaderSearchResponseModel.SuggestionBean> allPlacesList;
    //  private List<HeaderSearchResponseModel.SuggestionBean> filteredPlacesList;
    private List<HeaderSearchResponseModel.CategoriesNameBean> categoriesName;
    private DrawerListner drawerListner;

    public SearchFilterListAdapter(Context mContext, @NonNull List<HeaderSearchResponseModel.SuggestionBean> placesList,
                                   List<HeaderSearchResponseModel.CategoriesNameBean> categoriesName, DrawerListner drawerListner) {
        super(mContext, 0, placesList);
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        // this.filteredPlacesList = new ArrayList<>();
        this.allPlacesList = placesList;
        this.categoriesName = categoriesName;
        this.drawerListner = drawerListner;
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
        RecyclerView recyclerView = convertView.findViewById(R.id.rv_category_list);

        HeaderSearchResponseModel.SuggestionBean place = allPlacesList.get(position);
        if (place != null) {
            placeLabel.setText(place.getProduct_name());
            Glide.with(getContext()).load(TextUtils.isEmpty(place.getModifier_images()) ? ApiRequestUrl.BASE_URL + place.getImages() : ApiRequestUrl.BASE_URL + place.getModifier_images()).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(placeImage);
        }

        if (position == 0) {
            AutoCompleteCategoryAdapter adapter = new AutoCompleteCategoryAdapter(getContext(), categoriesName, new DrawerListner() {
                @Override
                public void onDrawerSelect(int position, int clickId) {
                    drawerListner.onDrawerSelect(position, clickId);
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return allPlacesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
