package com.poundland.retail.model;

import com.poundland.retail.R;

/**
 * Created by anupamchugh on 26/12/15.
 */
public enum ModelObject {

    RED(R.string.login, R.layout.view_blue),
    BLUE(R.string.logout, R.layout.view_green),
    GREEN(R.string.logout_message, R.layout.view_red);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
