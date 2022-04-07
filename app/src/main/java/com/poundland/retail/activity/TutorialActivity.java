package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.adapter.ViewPagerAdapter;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityTutorialBinding;

import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;

public class TutorialActivity extends FragmentActivity {
    private ActivityTutorialBinding binding;
    private static final int NUM_PAGES = 4;
    private ViewPagerAdapter pagerAdapter;
    private PrefManager prefManager;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tutorial);
        //  if (!checkFirstOpen()) {
        prefManager = PrefManager.getInstance(this);
        handler.postDelayed(runnable, 1000);
       /* } else {
            binding.main.setBackgroundColor(Color.parseColor("#4590A5"));
            pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            pagerAdapter.addFragment(new TutorialOneFragment());
            pagerAdapter.addFragment(new TutorialTwoFragment());
            pagerAdapter.addFragment(new TutorialThreeFragment());
            pagerAdapter.addFragment(new TutorialFourFragment());
          //  binding.pager.setPageTransformer(true, new ZoomOutPageTransformer());
            binding.pager.setAdapter(pagerAdapter);
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
        }*/
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (!prefManager.isPreferenceExists(AUTH_TOKEN)) {
                Intent intent = new Intent(TutorialActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
//            }
        }
    };


    private boolean checkFirstOpen() {
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

        if (isFirstRun) {
            Log.e("ggg", "is 1st" + isFirstRun);
        } else {

            isFirstRun = false;
        }
        return isFirstRun;
    }

    @Override
    public void onBackPressed() {
    }

    public void getToNext() {
        binding.pager.setCurrentItem(binding.pager.getCurrentItem() + 1);
    }

}

