package com.poundland.retail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.poundland.retail.R;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.LayoutDialogBookingTermConditionBinding;
import com.poundland.retail.interfaces.OnDialogClickListener;

import static com.poundland.retail.interfaces.Constants.DD_MMM_YYYY_dash_in_middle;


public class DialogBookingTermsCondition extends BottomSheetDialogFragment implements View.OnClickListener {
    private Context context;
    private LayoutInflater inflater;
    private LayoutDialogBookingTermConditionBinding binding;
    private PrefManager prefManager;
    private OnDialogClickListener listener;

    public static DialogBookingTermsCondition newInstance(Context context, OnDialogClickListener listener) {
        DialogBookingTermsCondition fragment = new DialogBookingTermsCondition();
        fragment.inflater = LayoutInflater.from(context);
        fragment.prefManager = PrefManager.getInstance(context);
        fragment.context = context;
        fragment.listener = listener;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_dialog_booking_term_condition, null, false);
        dialog.setContentView(binding.getRoot());
        ((View) binding.getRoot().getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        // BottomSheetDialog d = (BottomSheetDialog) dialog;
        FrameLayout bottomSheet = (FrameLayout) dialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);

        binding.tvLastUpdate.setText("Last Update :" + HelperClass.getCurrentDate(DD_MMM_YYYY_dash_in_middle));
        //  binding.tvContent.setText(addressVenue);

        binding.rlClose.setOnClickListener(this);
        binding.tvReadLater.setOnClickListener(this);
        binding.tvAcceptTerm.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_read_later:
                dismiss();
                break;
            case R.id.tv_accept_term:
                dismiss();
                listener.onPositiveClick();
                break;
            case R.id.rl_close:
                dismiss();
                break;
        }
    }
}
