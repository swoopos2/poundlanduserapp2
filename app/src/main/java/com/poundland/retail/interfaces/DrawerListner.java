package com.poundland.retail.interfaces;

public interface DrawerListner {
    void onDrawerSelect(int position, int clickId);

    interface onMOdifierSelectCallBack {
        void parentAdapterClicked(int parentPosition);

        void childAdapterClicked(int childPosition);
    }
}
