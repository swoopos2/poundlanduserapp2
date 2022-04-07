package com.poundland.retail.interfaces;

/**
 * Created by shakti on 01/07/2016.
 */
public interface UpdateCartListner {
    void onIncrease(int position, String count);
    void onDecrease(int position, String count);
    void onDelete(int position);
}
