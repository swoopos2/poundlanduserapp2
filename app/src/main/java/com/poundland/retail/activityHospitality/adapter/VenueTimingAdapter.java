package com.poundland.retail.activityHospitality.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.poundland.retail.R;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.model.responseModel.VenueThemeResponse;
import com.poundland.retail.model.responseModel.WeekTimeModel;

import java.util.ArrayList;
import java.util.List;

public class VenueTimingAdapter extends ArrayAdapter<WeekTimeModel.VenueWeekTimesBean> {
    private List<WeekTimeModel.VenueWeekTimesBean> weekTimesList;
    private VenueThemeResponse.Data themeColor;

    public VenueTimingAdapter(@NonNull Context context, int resource, @NonNull List<WeekTimeModel.VenueWeekTimesBean> weekTimesList, VenueThemeResponse.Data themeColor) {
        super(context, resource, weekTimesList);
        this.weekTimesList = new ArrayList<>(weekTimesList);
        this.themeColor = themeColor;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_cell_venue_timing, parent, false
            );
        }

        TextView tv_day = convertView.findViewById(R.id.tv_day);
        TextView tv_open = convertView.findViewById(R.id.tv_open);
        TextView tv_close = convertView.findViewById(R.id.tv_close);
        RelativeLayout rl_main = convertView.findViewById(R.id.rl_main);

        if (themeColor != null && themeColor.getHeaderColor()!=null) {
            String op = themeColor.getHeaderColor().replace("#", "#E6");
            rl_main.setBackgroundColor(Color.parseColor(op));
        }

        WeekTimeModel.VenueWeekTimesBean timesBean = getItem(position);
        if (timesBean != null) {
            tv_day.setText(timesBean.getDay_name());
            tv_open.setText(HelperClass.getTime(timesBean.getOpening_time()));
            tv_close.setText(HelperClass.getTime(timesBean.getClosing_time()));
        }

        return convertView;
    }

}
