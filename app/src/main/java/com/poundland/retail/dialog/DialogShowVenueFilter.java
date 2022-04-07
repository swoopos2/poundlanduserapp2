package com.poundland.retail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.poundland.retail.R;
import com.poundland.retail.adapter.VenueFilterListAdapter;
import com.poundland.retail.adapter.VenueFilterListItemAdapter;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.DialogShowVenueFilterBinding;
import com.poundland.retail.interfaces.FilterListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.model.responseModel.VenueFilterDataResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogShowVenueFilter extends Dialog implements ModifierSelectionListner {
    private Context context;
    private LayoutInflater inflater;
    private DialogShowVenueFilterBinding binding;
    private List<VenueFilterDataResponseModel.FilterBean> filterListData;
    private PrefManager prefManager;
    private VenueFilterListAdapter filterListAdapter;
    private FilterListner filterListner;
    private HashMap<String, List<String>> hashMap = new HashMap();

    public DialogShowVenueFilter(Context context, List<VenueFilterDataResponseModel.FilterBean> filterListData, FilterListner filterListner) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.filterListData = filterListData;
        this.filterListner = filterListner;
        prefManager = PrefManager.getInstance(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_show_venue_filter, null, true);
        setFilterListDataAdaper();
        setContentView(binding.getRoot());
        setCancelable(true);
        Window window = getWindow();
        WindowManager.LayoutParams wmlp = getWindow().getAttributes();
        // wmlp.gravity = Gravity.CENTER;
        window.setAttributes(wmlp);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        binding.tvClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filterListAdapter != null && filterListData != null)
                    filterListAdapter.clearAllCheck();
            }
        });
        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.tvApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, VenueFilterListItemAdapter> hashMapList = filterListAdapter.getAdapterList();
                for (Map.Entry<String, VenueFilterListItemAdapter> item : hashMapList.entrySet()) {
                    VenueFilterListItemAdapter ss = item.getValue();
                    List<String> data = new ArrayList<>();
                    for (Map.Entry<Integer, String> _item : ss.getSelected().entrySet()) {
                        data.add(_item.getValue());
                    }
                    hashMap.put(item.getKey(), data);
                }
                filterListner.onApplyFilter(hashMap);
                Log.e("filterList ", "" + hashMap.toString());
                dismiss();
            }
        });
    }

    private void setFilterListDataAdaper() {
        filterListAdapter = new VenueFilterListAdapter(context, filterListData, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        binding.rvFilterList.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

       // binding.rvFilterList.setLayoutManager(layoutManager);
        binding.rvFilterList.setAdapter(filterListAdapter);
    }

    @Override
    public void onModifierSelect(int pos_1st, int pos_2nd) {

    }

    @Override
    public void onModifierDeselect(int pos_1st, int pos_2nd) {
    }
}
