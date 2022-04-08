package com.poundland.retail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.interfaces.DrawerListner;

import java.util.ArrayList;
import java.util.List;

import static com.poundland.retail.interfaces.Constants.STORE;

public class SliderAdapterHome extends PagerAdapter {

    private Context context;
    private List<String> imageList;
    private boolean openLargeDialog;
    private boolean isFitXy;
    private String imageUrl;
    private int store;
    private DrawerListner listner;

    public SliderAdapterHome(Context context, List<String> imageList, boolean openLargeDialog, int store, boolean isFitXy, DrawerListner listner) {
        this.context = context;
        this.imageList = imageList;
        this.store = store;
        this.isFitXy = isFitXy;
        this.openLargeDialog = openLargeDialog;
        this.listner = listner;
    }

    public SliderAdapterHome(Context context, boolean openLargeDialog, int store, boolean isFitXy) {
        this.context = context;
        this.imageList = new ArrayList<>();
        this.store = store;
        this.isFitXy = isFitXy;
        this.openLargeDialog = openLargeDialog;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_slider_row, null);
        ImageView imageView = view.findViewById(R.id.image_view_slider);
        final ViewPager viewPager = (ViewPager) container;


///////////////////////////
        if (isFitXy)
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (store == STORE) {
            Glide.with(context).load(ApiRequestUrl.BASE_URL_VENUE + imageList.get(position)).apply(new RequestOptions()
                    .placeholder(R.drawable.app_logo_poundland))
                    .error(R.drawable.app_logo_poundland)
                    .into(imageView);
            store = STORE;
        } else {
            store = -1;
            if (position == 0) {
                Glide.with(context).load(/*ApiRequestUrl.BASE_URL_VENUE + imageList.get(position)*/"").apply(new RequestOptions()
                        .placeholder(R.drawable.poundland_banner))
                        .error(R.drawable.poundland_banner)
                        .into(imageView);
            } else if (position == 1) {
                Glide.with(context).load(""/*ApiRequestUrl.BASE_URL + imageList.get(position)*/).apply(new RequestOptions()
                        .placeholder(R.drawable.poundland_banner2))
                        .error(R.drawable.poundland_banner2)
                        .into(imageView);

            } else {
                Glide.with(context).load(""/*ApiRequestUrl.BASE_URL + imageList.get(position)*/).apply(new RequestOptions()
                        .placeholder(R.drawable.poundland_banner_3))
                        .error(R.drawable.poundland_banner_3)
                        .into(imageView);
            }
        }




       /* view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (openLargeDialog) {
                    new DialogShowLargeImage(context, imageList, position, store).show();
                } else {
                    listner.onDrawerSelect(position, BANNER_CLICK);
                }
            }
        });*/

        ((ViewPager) container).addView(view, 0);
        //  viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

}
