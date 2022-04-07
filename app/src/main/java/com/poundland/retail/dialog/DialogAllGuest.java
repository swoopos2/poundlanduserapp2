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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.adapter.GuestNameAdapter;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.LayoutDialogAllGuestBinding;
import com.poundland.retail.model.responseModel.MyOrderReservationResponseModel;

import java.util.List;


public class DialogAllGuest extends BottomSheetDialogFragment implements View.OnClickListener {
    private Context context;
    private LayoutInflater inflater;
    private LayoutDialogAllGuestBinding binding;
    private PrefManager prefManager;
    private List<MyOrderReservationResponseModel.ReservationsBean.DataBean.ReservationGuestBean> reservation_guest;
    public static DialogAllGuest newInstance(Context context, List<MyOrderReservationResponseModel.ReservationsBean.DataBean.ReservationGuestBean> reservation_guest) {
        DialogAllGuest fragment = new DialogAllGuest();
        fragment.inflater = LayoutInflater.from(context);
        fragment.prefManager = PrefManager.getInstance(context);
        fragment.context = context;
        fragment.reservation_guest=reservation_guest;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupDialog(@NonNull Dialog dialog, int style) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_dialog_all_guest, null, false);
        dialog.setContentView(binding.getRoot());
        ((View) binding.getRoot().getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        // BottomSheetDialog d = (BottomSheetDialog) dialog;
        FrameLayout bottomSheet = (FrameLayout) dialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);

        binding.rlClose.setOnClickListener(this);
        binding.tvReadLater.setOnClickListener(this);

        setOrderListAdapter();
    }

    private void setOrderListAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        GuestNameAdapter guestNameAdapter = new GuestNameAdapter(context, reservation_guest);
        binding.rvCustomerList.setLayoutManager(layoutManager);
        binding.rvCustomerList.setAdapter(guestNameAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_read_later:
                dismiss();
                break;
            case R.id.rl_close:
                dismiss();
                break;
        }
    }
}
