package com.poundland.retail.decentslider;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.poundland.retail.R;
import com.poundland.retail.enumeratio.FileType;

import java.util.ArrayList;
import java.util.List;

import static com.poundland.retail.R.id.img_sliderImage;

public class DecentSliderAdapter extends PagerAdapter {

    List<View> viewList;
    List<DecentSliderModel> sliderData;
    private int tabNum;
    private int itemNum;
    OnSliderListener onSliderListener;
    private Context context;

    public interface OnSliderListener {
        void startSlider();

        void stopSlider();
    }

    DecentSliderAdapter(int itemNum, List<DecentSliderModel> viewData, List<View> views,
                        OnSliderListener onSliderListener, Context context) {
        viewList = new ArrayList<>(tabNum);
        sliderData = new ArrayList<>(tabNum);
        viewList = views;
        sliderData = viewData;
        context = context;
        this.itemNum = itemNum;
        this.tabNum = views.size();
        this.onSliderListener = onSliderListener;
    }

    @Override
    public int getCount() {
        return itemNum;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int mPosition = (position % tabNum);
//        View view = viewList.get(mPosition);
        View view = sliderData.get(mPosition).getView();
        if (container.equals(view.getParent())) {
            container.removeView(view);
        }
        if (sliderData.get(mPosition).getFileType() == FileType.IMAGE) {
            ImageView imageView = view.findViewById(img_sliderImage);

            if (sliderData.get(mPosition).getLocalImage() != null) {

                // Bitmap myBitmap = BitmapFactory.decodeFile(sliderData.get(mPosition).getLocalImage());

                // imageView.setImageBitmap(myBitmap);
                String s = sliderData.get(mPosition).getLocalImage();

                int id = context.getResources().getIdentifier("com.retailuser.swoope:drawable/" + s, null, null);

                imageView.setImageResource(id);

            } else {
                imageView.setImageResource(R.drawable.ic_app_icon);
            }


        } else if (sliderData.get(mPosition).getFileType() == FileType.VIDEO) {
//            VideoView mVideoView = view.findViewById(R.id.player_view);
            if (onSliderListener != null) {
                onSliderListener.stopSlider();
            }
//            BaseApp.getInstance().handler().post(runnable);
        }
        container.addView(view);
        return view;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (onSliderListener != null) {
                onSliderListener.startSlider();
            }
        }
    };

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
    }
}