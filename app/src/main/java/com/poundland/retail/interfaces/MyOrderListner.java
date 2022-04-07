package com.poundland.retail.interfaces;

import com.poundland.retail.model.responseModel.MyOrderResponseModel;

import java.util.List;

public interface MyOrderListner {

    void OnSelectOption(int type, String trackStatus, String qty, String imageLink, MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address,
                        String venue, String offer, String productId, String orderId, String order_date,
                        String Venue_address_1, String venue_images, String cancel_date, Float rating,
                        String review, String productName, String productDetailId, String venue_name, String venue_contact,
                        String cancelStatus, String deliveryType, String collectTime, List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData, int is_hospitality, String estDeliveryDays, String expected_shipping_date);


}
