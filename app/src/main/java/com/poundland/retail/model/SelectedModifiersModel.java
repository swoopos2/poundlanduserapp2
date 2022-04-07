package com.poundland.retail.model;

public class SelectedModifiersModel {

    public String id;
    public String mName;
    public int quantity;
    public boolean isMChecked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isMChecked() {
        return isMChecked;
    }

    public void setMChecked(boolean MChecked) {
        isMChecked = MChecked;
    }
}
