package com.poundland.retail.zzznewPoundland;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.poundland.retail.R;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityInviteBinding;


public class InviteActivity extends BaseActivity implements View.OnClickListener {
    //private InviteViewModel mViewModel;
    private ActivityInviteBinding binding;
    private PrefManager prefManager;
    private InviteActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_invite);
        setListeners();
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.tvShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_share:
                HelperClass.shareApp(this, "Share Via", "");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {

    }


}
