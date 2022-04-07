package com.poundland.retail;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.poundland.retail.databinding.ActivityProductDetailsBinding;
import com.poundland.retail.decentslider.DecentSliderModel;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
   // private ActivityViewPagerBinding binding;
   // private XxFragmentHomeBinding binding;
    private ActivityProductDetailsBinding binding;

    private ArrayList<String> imageList;
    private List<DecentSliderModel> sliderData;
    private List<View> sliderViews;
    private List<String> sliderTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// setContentView(R.layout.activity_main);
       // binding = DataBindingUtil.setContentView(this, R.layout.activity_view_pager);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);
        onNewIntent(getIntent());
       /* imageList = new ArrayList<>();
        sliderData = new ArrayList<>();
        sliderViews = new ArrayList<>();
        sliderTitles = new ArrayList<>();
        imageList.add("http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/uploaded/venues/1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg");
        imageList.add("http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/uploaded/venues/1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg");
        imageList.add("http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/uploaded/venues/1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg");
      */
       // setViewPager();
       // getFromSdcard();
    }

   /* private void setViewPager() {
        binding.viewPager.setAdapter(new SliderAdapterHome(this, imageList, false, -1,
                false, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        }));
        binding.viewPager.setPadding(0, 0, 0, 0);
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setCurrentItem(0, true);
        binding.viewPager.setPageMargin(0);
        binding.indicator.setupWithViewPager(binding.viewPager, false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 3000);
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (binding.viewPager.getCurrentItem() < imageList.size() - 1) {
                        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
                    } else {
                        binding.viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void getFromSdcard() {
        try {

            for (int i = 0; i < imageList.size(); i++) {
                DecentSliderModel decentSliderModel;
                View view1;
                decentSliderModel = new DecentSliderModel(getLayoutInflater().inflate(R.layout.slider_image_view, null),
                        imageList.get(i),
                        imageList.get(i),
                        null);
                sliderData.add(decentSliderModel);
                view1 = getLayoutInflater().inflate(R.layout.slider_image_view, null);
                sliderViews.add(view1);
            }
            binding.decentSliderView.start(sliderData, sliderViews, sliderTitles, 3, 400, null, this*//*R.drawable.logo_transparent*//*);

        } catch (Exception exc) {
            exc.printStackTrace();
            Log.e("exception", "" + exc.getMessage());
        }
    }*/

   /* @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        String data = intent.getDataString();

        }
    }*/
}
