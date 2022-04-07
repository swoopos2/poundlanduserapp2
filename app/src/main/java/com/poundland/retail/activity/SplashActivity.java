package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.PrefManager;

import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_skip;
    //  private SharedPreferences loginPreferences;
    //  private SharedPreferences.Editor loginPrefsEditor;
    private PrefManager prefManager;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkFirstOpen()) {
            setTheme(R.style.SplashTheme);
            setContentView(R.layout.activity_splash);
            tv_skip = findViewById(R.id.tv_skip);
            tv_skip.setOnClickListener(this);
            init();
        }
    }

    private void init() {
        //  loginPreferences = getSharedPreferences(SPLASH_PREF, MODE_PRIVATE);
        //  loginPrefsEditor = loginPreferences.edit();
        //  loginPrefsEditor.putBoolean(SAVE_SPLASH, true);
        //   loginPrefsEditor.apply();
        prefManager = PrefManager.getInstance(this);
        handler.postDelayed(runnable, 1000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            if (loginPreferences.getBoolean(SAVE_SPLASH, false)) {
            if (!prefManager.isPreferenceExists(AUTH_TOKEN)) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
//            }
        }
    };


    private boolean checkFirstOpen() {
        Boolean isFirstRun = true;//getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

        if (isFirstRun) {
            Log.e("ggg", "is 1st" + isFirstRun);

            Intent topStore = new Intent(SplashActivity.this, TutorialActivity.class);
            startActivity(topStore);
            finish();
        } else {
            isFirstRun = false;
            Log.e("ggg", "is 1st" + isFirstRun);
        }
        return isFirstRun;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /*public void getFromSdcard() {
        try {

            for (int i = 0; i < IMAGES.length; i++) {
                DecentSliderModel decentSliderModel;
                View view1;
                decentSliderModel = new DecentSliderModel(getLayoutInflater().inflate(R.layout.slider_image_view, null),
                        IMAGES[i],
                        IMAGES[i],
                        null);
                sliderData.add(decentSliderModel);
                view1 = getLayoutInflater().inflate(R.layout.slider_image_view, null);
                sliderViews.add(view1);
            }
            if (sliderData.size() > 0) {
                decentSlider.setVisibility(View.VISIBLE);
                decentSlider.start(sliderData, sliderViews, sliderTitles, 3, 400, null, this);
            } else {
                decentSlider.setVisibility(View.GONE);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            Log.e("exception", "" + exc.getMessage());
        }
    }*/
}
