package com.poundland.retail.decentslider;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.poundland.retail.R;
import com.poundland.retail.enumeratio.FileType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class DecentSlider extends RelativeLayout implements DecentSliderAdapter.OnSliderListener {
    private final String TAG = "DecentSlider";
    Context context;
    View cursor;
    ImageView logo;
    AutoScrollViewPager viewPager;
    View gradientCover;
    private DecentSliderAdapter decentSliderAdapter;

    private Handler mHandler = new Handler();

    private static final int MESSAGE_SCROLL = 123;
    private int homeColumnScrollInterval = 4;
    private int tabNum = 0;
    private int viewNum = 0;
    private static final int ITEM_MAX_NUM = 200000;
    private static final int CURSOR_HEIGHT_DP = 2;
    private int animationDuration = 300;
    private boolean enableGradient = true;

    private List<TextView> titles;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_SCROLL) {
                if (viewPager != null) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    startAutoPlay();
                }
            }
        }
    };

    public void startAutoPlay() {
        stopAutoPlay();
//        handler.sendEmptyMessageDelayed(MESSAGE_SCROLL, homeColumnScrollInterval * 1000);
        if (viewPager != null) {
            viewPager.startAutoScroll(homeColumnScrollInterval * 1000);
        }
    }

    public void stopAutoPlay() {
//        handler.removeMessages(MESSAGE_SCROLL);
        if (viewPager != null) {
            viewPager.stopAutoScroll();
        }
    }

    public DecentSlider(Context context) {
        super(context);
    }

    public DecentSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DecentSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void start(List<DecentSliderModel> sliderData, List<View> views, List<String> titles, int interval, int animationDuration) {
        init(sliderData, views, titles, interval, animationDuration, 0);
    }

    public void start(List<DecentSliderModel> sliderData, List<View> views, List<String> titles, int interval, int animationDuration, int logoResourceId) {
        init(sliderData, views, titles, interval, animationDuration, logoResourceId);
    }

    public void start(List<DecentSliderModel> sliderData, List<View> views, List<String> titles, int interval, int animationDuration, Bitmap logo, Context context) {
        this.context = context;
        init(sliderData, views, titles, interval, animationDuration, logo);
    }

    public void setGradientEnabled(boolean enabled) {
        this.enableGradient = enabled;
        if (gradientCover != null) {
            if (enabled) {
                gradientCover.setVisibility(VISIBLE);
            } else {
                gradientCover.setVisibility(GONE);
            }
        }
    }

    public boolean isGradientEnabled() {
        return enableGradient;
    }

    private void init(List<DecentSliderModel> sliderData, List<View> views, final List<String> titleStrings, int interval, int animationDuration, int logoResourceId) {
        Bitmap logoBitmap;
        if (logoResourceId > 0) {
            logoBitmap = BitmapFactory.decodeResource(getResources(), logoResourceId);
        } else {
            logoBitmap = null;
        }
        init(sliderData, views, titleStrings, interval, animationDuration, logoBitmap);
    }

    private void init(final List<DecentSliderModel> sliderData, List<View> views, final List<String> titleStrings, int interval, int animationDuration, Bitmap logoBitmap) {
        inflate(getContext(), R.layout.decent_slider_layout, this);
        cursor = findViewById(R.id.cursor_view);
        logo = (ImageView) findViewById(R.id.logo_image);
        viewPager = (AutoScrollViewPager) findViewById(R.id.viewpager);

        gradientCover = findViewById(R.id.gradient_cover);

        if (titleStrings.size() == 0) {
            enableGradient = false;
        }

        if (enableGradient) {
            gradientCover.setVisibility(VISIBLE);
            cursor.setVisibility(VISIBLE);
        } else {
            gradientCover.setVisibility(GONE);
            cursor.setVisibility(GONE);
        }

        if (logoBitmap != null) {
            logo.setVisibility(VISIBLE);
            logo.setImageBitmap(logoBitmap);
        } else {
            logo.setVisibility(GONE);
        }

        this.homeColumnScrollInterval = interval;
        this.animationDuration = animationDuration;

        viewNum = views.size();
        tabNum = titleStrings.size();

        titles = new ArrayList<>(tabNum);
        for (String titleString : titleStrings) {
            TextView title = new TextView(getContext());
            title.setText(titleString);
            title.setTextColor(Color.WHITE);
            addView(title);
            titles.add(title);
        }

        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                DecentSlider.this.getViewTreeObserver().removeOnPreDrawListener(this);
                if (titles.size() != 0) {
                    initTabsAndCursorLayout();
                }
                return true;
            }
        });

        decentSliderAdapter = new DecentSliderAdapter(ITEM_MAX_NUM, sliderData, views, this, context);
        viewPager.setAdapter(decentSliderAdapter);
        int index = ITEM_MAX_NUM / 2 - ITEM_MAX_NUM / 2 % viewNum;
        viewPager.setCurrentItem(index);

        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(), new AccelerateDecelerateInterpolator(), animationDuration);
            mScroller.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        startAutoPlay();

        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        stopAutoPlay();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        startAutoPlay();
                        break;

                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset != 0) { // not to waste system resource for refreshes.
                    animateTitlesAndCursor(position, positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if ((position % sliderData.size()) == 4) {
                    if (sliderData.get(position % sliderData.size()).getFileType() == FileType.VIDEO) {
                        stopAutoPlay();
                        Log.e(TAG, " << onPageScrolled: >> " + sliderData.get(position % sliderData.size()).getFileType());
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void animateTitlesAndCursor(int position, float positionOffSet) {
        if (tabNum <= 1) {
            return;
        }
        int cursorLeft = 0;
        int cursorRight = 0;

        for (int i = 0; i < tabNum; i++) {
            if ((position % tabNum) != i) {
                continue;
            }
            if ((position % tabNum) == tabNum - 1) {
                TextView endTextView = titles.get(tabNum - 1);
                TextView startTextView = titles.get(0);
                if (positionOffSet < 0.5) {
                    cursorRight = endTextView.getRight();
                    cursorLeft = endTextView.getLeft() + (int) (endTextView.getWidth() * positionOffSet * 1.5);
                } else {
                    cursorLeft = startTextView.getLeft();
                    cursorRight = startTextView.getRight() - (int) (startTextView.getWidth() * (1 - positionOffSet) * 1.5);
                }
                startTextView.setTextColor(Color.argb((int) (255 * (positionOffSet * 0.5 + 0.5)), 255, 255, 255));
                endTextView.setTextColor(Color.argb((int) (255 * (1 - positionOffSet * 0.5)), 255, 255, 255));
            } else {
                TextView startTextView = titles.get(i);
                TextView endTextView = titles.get(i + 1);
                cursorLeft = startTextView.getLeft() + (int) (positionOffSet * (endTextView.getLeft() - startTextView.getLeft()));
                cursorRight = startTextView.getRight() + (int) (positionOffSet * (endTextView.getRight() - startTextView.getRight()));
                startTextView.setTextColor(Color.argb((int) (255 * (1 - positionOffSet * 0.5)), 255, 255, 255));
                endTextView.setTextColor(Color.argb((int) (255 * (positionOffSet * 0.5 + 0.5)), 255, 255, 255));
            }
        }

        LayoutParams layoutParams = new LayoutParams(cursorRight - cursorLeft, dip2px(getContext(), CURSOR_HEIGHT_DP));
        layoutParams.leftMargin = cursorLeft;
        layoutParams.topMargin = cursor.getTop();
        cursor.setLayoutParams(layoutParams);
    }

    private void initTabsAndCursorLayout() {
        int windowWidth = this.getMeasuredWidth();
        int windowHeight = this.getMeasuredHeight();
        int cursorLeft = 0;
        for (int i = 0; i < titles.size(); i++) {
            TextView textView = titles.get(i);
            LayoutParams layoutParams = new LayoutParams(textView.getMeasuredWidth(), textView.getMeasuredHeight());
            layoutParams.leftMargin = (windowWidth - 60) * (2 * i + 1) / (tabNum * 2) - textView.getMeasuredWidth() / 2 + 30;
            if (i == 0) {
                cursorLeft = (windowWidth - 60) * (2 * i + 1) / (tabNum * 2) - textView.getMeasuredWidth() / 2 + 30;
            }
            layoutParams.topMargin = windowHeight - dip2px(getContext(), 15) - textView.getMeasuredHeight();
            textView.setLayoutParams(layoutParams);
            if (i != 0) {
                textView.setTextColor(Color.GRAY);
            }
        }

        LayoutParams layoutParams = new LayoutParams(titles.get(0).getMeasuredWidth(), dip2px(getContext(), CURSOR_HEIGHT_DP));
        layoutParams.leftMargin = cursorLeft;
        layoutParams.topMargin = windowHeight - dip2px(getContext(), 13);
        cursor.setLayoutParams(layoutParams);
    }

    @Override
    public void startSlider() {
//        startAutoPlay();
    }

    @Override
    public void stopSlider() {
//        stopAutoPlay();
    }

    class FixedSpeedScroller extends Scroller {
        int duration;

        public FixedSpeedScroller(Context context, int duration) {
            super(context);
            this.duration = duration;
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, int duration) {
            super(context, interpolator);
            this.duration = duration;
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel, int duration) {
            super(context, interpolator, flywheel);
            this.duration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, this.duration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, this.duration);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /*///////////////////////////////////////  For circular pager indicator   ///////////////////////////////////////////

       // TabLayout tabLayout = (TabLayout) findViewById(R.id.indicator);
        //viewPager.setAdapter(new SliderAdapterHome(getActivity(), topCviList));
        viewPager.setPadding(80, 0, 80, 0);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(30);
        tabLayout.setupWithViewPager(viewPager, true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 3000);
        ///////////////////////////////////////////////////////////////////////////////////////////////////*/

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if ((Activity) context == null)
                return;
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < tabNum - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}