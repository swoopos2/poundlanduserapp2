package com.poundland.retail.zzznewPoundland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.poundland.retail.R;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityFavouritesBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.AllCategoryResponseModel;
import com.poundland.retail.zzznewPoundland.adapter.FavoProductAdapter;
import com.poundland.retail.zzznewPoundland.adapter.FavoVenueAdapter;

import static com.poundland.retail.interfaces.Constants.UNIVERSAL_CODE;

public class FavouriteActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityFavouritesBinding binding;
    private PrefManager prefManager;
    private FavouriteActivity instance = null;
    private FavoVenueAdapter favoVenueAdapter;
    private FavoProductAdapter favoProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourites);
        init();
        setListeners();

        setFavVenueAdapter();
    }

    private void init() {
        instance = this;
        prefManager = PrefManager.getInstance(this);
        binding.title.setText(getString(R.string.favorite_ven_pro));
    }

    private void setFavVenueAdapter() {
        favoVenueAdapter = new FavoVenueAdapter(this, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvFavVenue.setLayoutManager(layoutManager);
        binding.rvFavVenue.setAdapter(favoVenueAdapter);
        //////////////////////////////////////////////////////////////////////////////////////////
        favoProductAdapter = new FavoProductAdapter(this, this);
        binding.rvFavProduct.setLayoutManager(new LinearLayoutManager(this));
        binding.rvFavProduct.setAdapter(favoProductAdapter);

        setVenueData();

    }

    private void setVenueData() {

        AllCategoryResponseModel fav_venue_content = new Gson().fromJson(HelperClass.venue_content, AllCategoryResponseModel.class);
        favoVenueAdapter.clear();
        favoVenueAdapter.addData(fav_venue_content.getCategories());

        AllCategoryResponseModel fav_prod_content = new Gson().fromJson(HelperClass.product_content, AllCategoryResponseModel.class);
        favoProductAdapter.clear();
        favoProductAdapter.addData(fav_prod_content.getCategories());
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {

        if (clickType == UNIVERSAL_CODE) {

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_my_cart;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {

    }
}
