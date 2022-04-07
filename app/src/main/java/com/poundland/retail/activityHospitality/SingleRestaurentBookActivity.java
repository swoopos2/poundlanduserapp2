package com.poundland.retail.activityHospitality;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.poundland.retail.R;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.databinding.ActivitySingleRestaurentBookBinding;

public class SingleRestaurentBookActivity extends BaseActivity {
    private ActivitySingleRestaurentBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_restaurent_book);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_single_restaurent_book;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {

    }
}