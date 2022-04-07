package com.poundland.retail.zzznewPoundland.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

import com.poundland.retail.R;
import com.poundland.retail.zzznewPoundland.dialog.MultiSelectionSpinnerDialog;

import java.util.List;

public class MultiSpinner extends AppCompatTextView {

    public MultiSpinner(final Context context) {
        super(context);

    }

    public MultiSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapterWithImage(Context context, List<String> imageURLList, List<String> spinnerContentList, MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener onMultiSpinnerSelectionListener) {

        MultiSelectionSpinnerDialog multiSelectionSpinnerDialog = new MultiSelectionSpinnerDialog(context);
        multiSelectionSpinnerDialog.setMultiSelectionAdapterWithImage(context, imageURLList, spinnerContentList, onMultiSpinnerSelectionListener);

    }

    public void setAdapterWithOutImage(Context context, List<String> spinnerContentList, MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener onMultiSpinnerSelectionListener) {

        MultiSelectionSpinnerDialog multiSelectionSpinnerDialog = new MultiSelectionSpinnerDialog(context);
        multiSelectionSpinnerDialog.setMultiSelectionAdapterWithOutImage(context, spinnerContentList, onMultiSpinnerSelectionListener);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void initMultiSpinner(final Context context, MultiSpinner multiSpinner) {

        if (context != null && multiSpinner != null) {

            MultiSelectionSpinnerDialog.multiSpinner = multiSpinner;
            multiSpinner.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0);
            multiSpinner.setBackground(getResources().getDrawable(R.drawable.app_color_blue_filled_ract));
            multiSpinner.setGravity(Gravity.CENTER_VERTICAL);
            multiSpinner.setPadding(20, 5, 20, 5);
            multiSpinner.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {

                    MultiSelectionSpinnerDialog.multiSelectionSpinnerDialog.show();
                }
            });
        }
    }


}