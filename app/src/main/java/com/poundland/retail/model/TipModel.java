package com.poundland.retail.model;

public class TipModel {
    public int percent;
    public boolean isEnable;

    public TipModel(int percent, boolean isEnable) {
        this.percent = percent;
        this.isEnable = isEnable;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}