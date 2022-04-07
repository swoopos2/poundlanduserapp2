package com.poundland.retail.interfaces;

/**
 * Created by shakti on 21/01/2021.
 */
public interface UpdateIngredientListener {
    void onIncrease(int pos_1, int pos_2, int count);
    void onDecrease(int pos_1,int pos_2, int count);
}
