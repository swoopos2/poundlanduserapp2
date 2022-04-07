package com.poundland.retail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.poundland.retail.R;
import com.poundland.retail.adapter.SliderAdapterHome;
import com.poundland.retail.databinding.DialogShowLargeImageBinding;
import com.poundland.retail.interfaces.DrawerListner;

import java.util.List;

public class DialogShowLargeImage extends Dialog {
    private Context context;
    private LayoutInflater inflater;
    private DialogShowLargeImageBinding binding;
    private List<String> imageList;
    private int position;
    private int store;

    public DialogShowLargeImage(Context context, List<String> imageList, int position, int store) {
        super(context);
        this.context = context;
        this.imageList = imageList;
        this.position = position;
        this.store = store;
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_show_large_image, null, false);
        setContentView(binding.getRoot());
        setCancelable(false);
        //getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        Window window = getWindow();
        WindowManager.LayoutParams wmlp = getWindow().getAttributes();


        wmlp.dimAmount = 0.7f;
        wmlp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        wmlp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wmlp.gravity = Gravity.CENTER;
        window.setAttributes(wmlp);


        setViewPager();
        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setViewPager() {
        binding.viewPager.setAdapter(new SliderAdapterHome(context, imageList, false, store, false, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        }));
        binding.viewPager.setPadding(0, 0, 0, 0);
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setCurrentItem(position,true);
        binding.viewPager.setPageMargin(10);
        binding.indicator.setupWithViewPager(binding.viewPager, true);
        /*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 3000);*/
    }
}
