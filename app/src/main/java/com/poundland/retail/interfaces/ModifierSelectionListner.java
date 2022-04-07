package com.poundland.retail.interfaces;

public interface ModifierSelectionListner {

    void onModifierSelect(int parentPosition, int childPosition);

    void onModifierDeselect(int pos_1st, int pos_2nd);

}
