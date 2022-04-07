package com.poundland.retail.model.responseModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReservationVenuesResponseModel {


    /**
     * status : true
     * message : List Of Venue
     * category : ["beers","burger","champagne","chicken","curries","drinks","fastfood","food","healthy","helathy","pizza","pork","rum","scotch","spicy","takeaway","tasty","thai food","veg food","vodka","whisky","wine","wines"]
     * dietary : {"veg":"Veg","non-veg":"Non Veg"}
     * venues : {"current_page":1,"data":[{"id":67,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":100,"delivery_rating":0,"latitude":null,"longitude":null,"timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"healthy,spicy","product_type":"both","merchant_id":16,"venue_id":"2020102009594316","venue_name":"Food Street","collection_time":30,"is_booking_allow":1,"venue_images":"20201020100338-201807170926215b4db63d8ff97.jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH12 7XD","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":null,"ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":32616,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32616,"freeSheet":1,"totB":1},{"id":32617,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32617,"freeSheet":1,"totB":1},{"id":32618,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32618,"freeSheet":1,"totB":1}],"timing":{"total_offers":0,"opening_time":"2020-12-30 10:00","closing_time":"2020-12-30 20:00","delivery_opening_time":"11:00","delivery_closing_time":"19:00","delivery_closing_datetime":"2020-12-30 19:00","collection_opening_time":"11:00","collection_closing_time":"19:00","is_venue_open":1,"isClose":"1"}},{"id":45,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":100,"delivery_rating":5,"latitude":"51.5095249","longitude":"-0.134223","timing_slot_gap":"30","max_order_slot":"20.00","venue_category":"Veg Food,Drinks,Burger","product_type":"veg","merchant_id":5,"venue_id":"202005271311185","venue_name":"Ankush Pandey","collection_time":30,"is_booking_allow":1,"venue_images":"1590581478-photo-1533481405265-e9ce0c044abb.jpeg","address_1":"24-36 Regent St, St. James's","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"SW1Y 4QF","home_delivery_mov":"1.00","delivery":1,"collection":1,"delivery_distance":120,"distance":"3,559.31","ratingcount":1,"is_like":0,"rate":"2.250000000","reservationSlot":[{"id":12821,"venue_id":"202005271311185","merchant_id":5,"day":3,"time":"10:00:00","priceing_tier":1,"price":"10.00","created_at":"2020-07-13 14:03:21","updated_at":"2020-07-13 14:03:21","time_id":12821,"freeSheet":1,"totB":0},{"id":12822,"venue_id":"202005271311185","merchant_id":5,"day":3,"time":"10:15:00","priceing_tier":1,"price":"10.00","created_at":"2020-07-13 14:03:21","updated_at":"2020-07-13 14:03:21","time_id":12822,"freeSheet":1,"totB":0},{"id":12823,"venue_id":"202005271311185","merchant_id":5,"day":3,"time":"10:30:00","priceing_tier":1,"price":"10.00","created_at":"2020-07-13 14:03:21","updated_at":"2020-07-13 14:03:21","time_id":12823,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 09:00","closing_time":"2020-12-30 22:00","delivery_opening_time":"10:00","delivery_closing_time":"21:00","delivery_closing_datetime":"2020-12-30 21:00","collection_opening_time":"10:00","collection_closing_time":"21:00","is_venue_open":1,"isClose":"1"}},{"id":33,"order_status":1,"venue_type":2,"booking_stay":120,"restaurant_capacity":5,"delivery_rating":5,"latitude":"52.4572148","longitude":"-2.1474445","timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"Wine,Whisky,Rum,Scotch,Vodka,champagne","product_type":"veg","merchant_id":15,"venue_id":"2020042710540215","venue_name":"Majestic Stourbridge","collection_time":45,"is_booking_allow":1,"venue_images":"20200918122353-20200520154611-majestic.jpg","address_1":"Stourbridge Town Hall, Crown Lane, Stourbridge, UK","address_2":null,"city_name":"Stourbridge","country":"United Kingdom","post_code":"DY8 1YE","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":10000,"distance":"3,625.92","ratingcount":4,"is_like":0,"rate":"4.562500000","reservationSlot":[{"id":27148,"venue_id":"2020042710540215","merchant_id":15,"day":3,"time":"10:05:00","priceing_tier":1,"price":"8.00","created_at":"2020-09-18 12:23:53","updated_at":"2020-09-18 12:23:53","time_id":27148,"freeSheet":1,"totB":0},{"id":27149,"venue_id":"2020042710540215","merchant_id":15,"day":3,"time":"10:35:00","priceing_tier":1,"price":"8.00","created_at":"2020-09-18 12:23:53","updated_at":"2020-09-18 12:23:53","time_id":27149,"freeSheet":1,"totB":0},{"id":27150,"venue_id":"2020042710540215","merchant_id":15,"day":3,"time":"11:05:00","priceing_tier":1,"price":"8.00","created_at":"2020-09-18 12:23:53","updated_at":"2020-09-18 12:23:53","time_id":27150,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 05:05","closing_time":"2020-12-31 00:00","delivery_opening_time":"05:05","delivery_closing_time":"22:40","delivery_closing_datetime":"2020-12-30 22:40","collection_opening_time":"05:05","collection_closing_time":"20:00","is_venue_open":1,"isClose":"0"}},{"id":43,"order_status":1,"venue_type":2,"booking_stay":120,"restaurant_capacity":100,"delivery_rating":5,"latitude":"52.578609","longitude":"-2.1256158","timing_slot_gap":"30","max_order_slot":"15.00","venue_category":"Drinks,Wines,Beers,Whisky","product_type":"veg","merchant_id":15,"venue_id":"2020052208361515","venue_name":"Hedonism Wines","collection_time":30,"is_booking_allow":1,"venue_images":"1590132975-Header-Hedonism-Wines-e1516542447373.jpg","address_1":"Shell, Birmingham Road, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV2 3LH","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":"3,634.28","ratingcount":1,"is_like":0,"rate":"5.000000000","reservationSlot":[{"id":21413,"venue_id":"2020052208361515","merchant_id":15,"day":3,"time":"11:00:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-18 09:42:27","updated_at":"2020-07-18 09:42:27","time_id":21413,"freeSheet":1,"totB":0},{"id":21414,"venue_id":"2020052208361515","merchant_id":15,"day":3,"time":"11:30:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-18 09:42:27","updated_at":"2020-07-18 09:42:27","time_id":21414,"freeSheet":1,"totB":0},{"id":21415,"venue_id":"2020052208361515","merchant_id":15,"day":3,"time":"12:00:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-18 09:42:27","updated_at":"2020-07-18 09:42:27","time_id":21415,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 10:00","closing_time":"2020-12-31 00:00","delivery_opening_time":"11:00","delivery_closing_time":"22:50","delivery_closing_datetime":"2020-12-30 22:50","collection_opening_time":"11:00","collection_closing_time":"21:10","is_venue_open":1,"isClose":"1"}},{"id":35,"order_status":1,"venue_type":2,"booking_stay":180,"restaurant_capacity":1000,"delivery_rating":3,"latitude":"52.5853501","longitude":"-2.1314475","timing_slot_gap":"30","max_order_slot":"15.00","venue_category":"thai food,pork,curries","product_type":"both","merchant_id":5,"venue_id":"202004281056405","venue_name":"Made in Thai","collection_time":70,"is_booking_allow":1,"venue_images":"20200506084120-F64BJAT2VZESHJAXYH3JKFH4IQ.jpeg","address_1":"24 Darlington St, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 4HW","home_delivery_mov":"5.0","delivery":0,"collection":1,"delivery_distance":14,"distance":"3,634.75","ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":10073,"venue_id":"202004281056405","merchant_id":5,"day":3,"time":"11:00:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-03 14:39:17","updated_at":"2020-07-03 14:39:17","time_id":10073,"freeSheet":1,"totB":0},{"id":10074,"venue_id":"202004281056405","merchant_id":5,"day":3,"time":"11:15:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-03 14:39:17","updated_at":"2020-07-03 14:39:17","time_id":10074,"freeSheet":1,"totB":0},{"id":10075,"venue_id":"202004281056405","merchant_id":5,"day":3,"time":"11:30:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-03 14:39:17","updated_at":"2020-07-03 14:39:17","time_id":10075,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 09:00","closing_time":"2020-12-31 00:55","delivery_opening_time":"11:00","delivery_closing_time":"21:00","delivery_closing_datetime":"2020-12-30 21:00","collection_opening_time":"11:00","collection_closing_time":"21:00","is_venue_open":1,"isClose":"1"}},{"id":62,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":50,"delivery_rating":0,"latitude":"52.5875483","longitude":"-2.120122100000001","timing_slot_gap":"30","max_order_slot":"25.00","venue_category":"Drinks,Food,Takeaway","product_type":"both","merchant_id":15,"venue_id":"2020100508573315","venue_name":"Bella Cosa","collection_time":45,"is_booking_allow":1,"venue_images":"20201005090417-Bella-Cosa.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":1000,"distance":"3,634.89","ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":36990,"venue_id":"2020100508573315","merchant_id":15,"day":3,"time":"10:10:00","priceing_tier":1,"price":"5.00","created_at":"2020-12-07 07:20:06","updated_at":"2020-12-07 07:20:06","time_id":36990,"freeSheet":1,"totB":0},{"id":36991,"venue_id":"2020100508573315","merchant_id":15,"day":3,"time":"10:25:00","priceing_tier":1,"price":"5.00","created_at":"2020-12-07 07:20:06","updated_at":"2020-12-07 07:20:06","time_id":36991,"freeSheet":1,"totB":0},{"id":36992,"venue_id":"2020100508573315","merchant_id":15,"day":3,"time":"10:40:00","priceing_tier":1,"price":"5.00","created_at":"2020-12-07 07:20:06","updated_at":"2020-12-07 07:20:06","time_id":36992,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 ","closing_time":"2020-12-30 ","delivery_opening_time":"08:05","delivery_closing_time":"21:05","delivery_closing_datetime":"2020-12-30 21:05","collection_opening_time":"08:10","collection_closing_time":"21:10","is_venue_open":1,"isClose":"1"}},{"id":42,"order_status":0,"venue_type":2,"booking_stay":300,"restaurant_capacity":100,"delivery_rating":5,"latitude":"52.5923018","longitude":"-2.1383264","timing_slot_gap":"30","max_order_slot":"15.00","venue_category":"FastFood,Drinks","product_type":"both","merchant_id":15,"venue_id":"2020052207223015","venue_name":"Leon Naturally Fast Food","collection_time":40,"is_booking_allow":1,"venue_images":"1590128550-Vision-at-Leon-Stansted-Airport.jpg","address_1":"UKIM Madinah Masjid, New Hampton Road East, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 4BB","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":"3,635.24","ratingcount":1,"is_like":0,"rate":"5.000000000","reservationSlot":[{"id":1547,"venue_id":"2020052207223015","merchant_id":15,"day":3,"time":"10:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-07-01 13:43:39","updated_at":"2020-07-01 13:43:39","time_id":1547,"freeSheet":1,"totB":0},{"id":1548,"venue_id":"2020052207223015","merchant_id":15,"day":3,"time":"10:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-07-01 13:43:39","updated_at":"2020-07-01 13:43:39","time_id":1548,"freeSheet":1,"totB":0},{"id":1549,"venue_id":"2020052207223015","merchant_id":15,"day":3,"time":"10:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-07-01 13:43:39","updated_at":"2020-07-01 13:43:39","time_id":1549,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 08:00","closing_time":"2020-12-30 22:00","delivery_opening_time":"08:30","delivery_closing_time":"22:30","delivery_closing_datetime":"2020-12-30 22:30","collection_opening_time":"08:30","collection_closing_time":"22:30","is_venue_open":1,"isClose":"0"}},{"id":34,"order_status":1,"venue_type":2,"booking_stay":240,"restaurant_capacity":100,"delivery_rating":3,"latitude":"53.4538557","longitude":"-2.9756757","timing_slot_gap":"15","max_order_slot":"40.00","venue_category":"Chicken,Pizza,Burger","product_type":"both","merchant_id":5,"venue_id":"202004271734165","venue_name":"Dixi Chicken Pizza","collection_time":70,"is_booking_allow":1,"venue_images":"20200622115653-resize.jpg","address_1":"Southport Road, Liverpool, Bootle, UK","address_2":null,"city_name":"Liverpool","country":"United Kingdom","post_code":"L31QR","home_delivery_mov":"0.30","delivery":1,"collection":1,"delivery_distance":1000,"distance":"3,695.69","ratingcount":3,"is_like":0,"rate":"4.500000000","reservationSlot":[{"id":38404,"venue_id":"202004271734165","merchant_id":5,"day":3,"time":"12:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-12-29 09:10:10","updated_at":"2020-12-29 09:10:10","time_id":38404,"freeSheet":1,"totB":0},{"id":38405,"venue_id":"202004271734165","merchant_id":5,"day":3,"time":"12:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-12-29 09:10:10","updated_at":"2020-12-29 09:10:10","time_id":38405,"freeSheet":1,"totB":0},{"id":38406,"venue_id":"202004271734165","merchant_id":5,"day":3,"time":"12:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-12-29 09:10:10","updated_at":"2020-12-29 09:10:10","time_id":38406,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 07:30","closing_time":"2020-12-31 00:00","delivery_opening_time":"10:05","delivery_closing_time":"22:00","delivery_closing_datetime":"2020-12-30 22:00","collection_opening_time":"05:00","collection_closing_time":"22:30","is_venue_open":1,"isClose":"0"}},{"id":31,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":25,"delivery_rating":5,"latitude":"55.9428422","longitude":"-3.2831273","timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"healthy, spicy","product_type":"non-veg","merchant_id":16,"venue_id":"2020042308172516","venue_name":"Raman Restaurant","collection_time":70,"is_booking_allow":1,"venue_images":"20200520154700-what-to-eat-in-morocco.jpg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"th floor 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","city_name":"castophine","country":"United Kingdom","post_code":"eh127xd","home_delivery_mov":"20.00","delivery":1,"collection":1,"delivery_distance":0,"distance":"3,867.74","ratingcount":3,"is_like":0,"rate":"3.250000000","reservationSlot":[{"id":34286,"venue_id":"2020042308172516","merchant_id":16,"day":3,"time":"10:00:00","priceing_tier":1,"price":"3.00","created_at":"2020-11-10 09:08:22","updated_at":"2020-11-10 09:08:22","time_id":34286,"freeSheet":1,"totB":0},{"id":34287,"venue_id":"2020042308172516","merchant_id":16,"day":3,"time":"10:30:00","priceing_tier":1,"price":"3.00","created_at":"2020-11-10 09:08:22","updated_at":"2020-11-10 09:08:22","time_id":34287,"freeSheet":1,"totB":0},{"id":34288,"venue_id":"2020042308172516","merchant_id":16,"day":3,"time":"11:00:00","priceing_tier":1,"price":"3.00","created_at":"2020-11-10 09:08:22","updated_at":"2020-11-10 09:08:22","time_id":34288,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 08:00","closing_time":"2020-12-30 22:00","delivery_opening_time":"09:00","delivery_closing_time":"22:00","delivery_closing_datetime":"2020-12-30 22:00","collection_opening_time":"09:00","collection_closing_time":"22:00","is_venue_open":1,"isClose":"0"}},{"id":69,"order_status":1,"venue_type":2,"booking_stay":90,"restaurant_capacity":50,"delivery_rating":0,"latitude":"55.9431887","longitude":"-3.2894863","timing_slot_gap":"15","max_order_slot":"5.00","venue_category":"helathy","product_type":"both","merchant_id":16,"venue_id":"2020102010034016","venue_name":"Food World","collection_time":30,"is_booking_allow":1,"venue_images":"20201020100752-BL09FOODWORLD1.jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Corstorphine","country":"United Kingdom","post_code":"EH12 7XD","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":"3,867.77","ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":32392,"venue_id":"2020102010034016","merchant_id":16,"day":3,"time":"12:00:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:13:01","updated_at":"2020-10-20 10:13:01","time_id":32392,"freeSheet":1,"totB":0},{"id":32393,"venue_id":"2020102010034016","merchant_id":16,"day":3,"time":"12:15:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:13:01","updated_at":"2020-10-20 10:13:01","time_id":32393,"freeSheet":1,"totB":0},{"id":32394,"venue_id":"2020102010034016","merchant_id":16,"day":3,"time":"12:30:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:13:01","updated_at":"2020-10-20 10:13:01","time_id":32394,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 11:00","closing_time":"2020-12-30 21:00","delivery_opening_time":"12:00","delivery_closing_time":"20:00","delivery_closing_datetime":"2020-12-30 20:00","collection_opening_time":"12:00","collection_closing_time":"20:00","is_venue_open":1,"isClose":"1"}},{"id":70,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":50,"delivery_rating":0,"latitude":"55.953252","longitude":"-3.188267","timing_slot_gap":"15","max_order_slot":"5.00","venue_category":"healthy, tasty","product_type":"both","merchant_id":16,"venue_id":"2020102010121316","venue_name":"Food Park","collection_time":30,"is_booking_allow":1,"venue_images":"20201020101823-hotel-food-park-raigad-maharashtra-1xdgxa4vc2.jpg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH12 7XD","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":"3,868.34","ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":32840,"venue_id":"2020102010121316","merchant_id":16,"day":3,"time":"11:00:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:18:23","updated_at":"2020-10-20 10:18:23","time_id":32840,"freeSheet":1,"totB":0},{"id":32841,"venue_id":"2020102010121316","merchant_id":16,"day":3,"time":"11:15:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:18:23","updated_at":"2020-10-20 10:18:23","time_id":32841,"freeSheet":1,"totB":0},{"id":32842,"venue_id":"2020102010121316","merchant_id":16,"day":3,"time":"11:30:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:18:23","updated_at":"2020-10-20 10:18:23","time_id":32842,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 20:00","closing_time":"2020-12-30 22:00","delivery_opening_time":"11:00","delivery_closing_time":"19:00","delivery_closing_datetime":"2020-12-30 19:00","collection_opening_time":"11:00","collection_closing_time":"19:00","is_venue_open":1,"isClose":"1"}}],"first_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getReservationVenue?page=1","from":1,"last_page":1,"last_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getReservationVenue?page=1","next_page_url":null,"path":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getReservationVenue","per_page":100,"prev_page_url":null,"to":11,"total":11}
     * times : [{"time":"10:00:00"},{"time":"10:05:00"},{"time":"10:10:00"},{"time":"10:15:00"},{"time":"10:20:00"},{"time":"10:25:00"},{"time":"10:30:00"},{"time":"10:35:00"},{"time":"10:40:00"},{"time":"10:45:00"},{"time":"10:50:00"},{"time":"10:55:00"},{"time":"11:00:00"},{"time":"11:05:00"},{"time":"11:10:00"},{"time":"11:15:00"},{"time":"11:20:00"},{"time":"11:25:00"},{"time":"11:30:00"},{"time":"11:35:00"},{"time":"11:40:00"},{"time":"11:45:00"},{"time":"11:50:00"},{"time":"11:55:00"},{"time":"12:00:00"},{"time":"12:05:00"},{"time":"12:10:00"},{"time":"12:15:00"},{"time":"12:20:00"},{"time":"12:25:00"},{"time":"12:30:00"},{"time":"12:35:00"},{"time":"12:40:00"},{"time":"12:45:00"},{"time":"12:50:00"},{"time":"12:55:00"},{"time":"13:00:00"},{"time":"13:05:00"},{"time":"13:10:00"},{"time":"13:15:00"},{"time":"13:20:00"},{"time":"13:25:00"},{"time":"13:30:00"},{"time":"13:35:00"},{"time":"13:40:00"},{"time":"13:45:00"},{"time":"13:50:00"},{"time":"13:55:00"},{"time":"14:00:00"},{"time":"14:05:00"},{"time":"14:10:00"},{"time":"14:15:00"},{"time":"14:20:00"},{"time":"14:25:00"},{"time":"14:30:00"},{"time":"14:35:00"},{"time":"14:40:00"},{"time":"14:45:00"},{"time":"14:50:00"},{"time":"14:55:00"},{"time":"15:00:00"},{"time":"15:05:00"},{"time":"15:10:00"},{"time":"15:15:00"},{"time":"15:20:00"},{"time":"15:25:00"},{"time":"15:30:00"},{"time":"15:35:00"},{"time":"15:40:00"},{"time":"15:45:00"},{"time":"15:50:00"},{"time":"15:55:00"},{"time":"16:00:00"},{"time":"16:05:00"},{"time":"16:10:00"},{"time":"16:15:00"},{"time":"16:20:00"},{"time":"16:25:00"},{"time":"16:30:00"},{"time":"16:35:00"},{"time":"16:40:00"},{"time":"16:45:00"},{"time":"16:50:00"},{"time":"16:55:00"},{"time":"17:00:00"},{"time":"17:05:00"},{"time":"17:10:00"},{"time":"17:15:00"},{"time":"17:20:00"},{"time":"17:25:00"},{"time":"17:30:00"},{"time":"17:35:00"},{"time":"17:40:00"},{"time":"17:45:00"},{"time":"17:50:00"},{"time":"17:55:00"},{"time":"18:00:00"},{"time":"18:05:00"},{"time":"18:10:00"},{"time":"18:15:00"},{"time":"18:20:00"},{"time":"18:25:00"},{"time":"18:30:00"},{"time":"18:35:00"},{"time":"18:40:00"},{"time":"18:45:00"},{"time":"18:50:00"},{"time":"18:55:00"},{"time":"19:00:00"},{"time":"19:05:00"},{"time":"19:10:00"},{"time":"19:15:00"},{"time":"19:20:00"},{"time":"19:25:00"},{"time":"19:30:00"},{"time":"19:35:00"},{"time":"19:40:00"},{"time":"19:45:00"},{"time":"19:50:00"},{"time":"19:55:00"},{"time":"20:00:00"},{"time":"20:05:00"},{"time":"20:10:00"},{"time":"20:15:00"},{"time":"20:20:00"},{"time":"20:25:00"},{"time":"20:30:00"},{"time":"20:35:00"},{"time":"20:40:00"},{"time":"20:45:00"},{"time":"20:50:00"},{"time":"20:55:00"},{"time":"21:00:00"},{"time":"21:05:00"},{"time":"21:10:00"},{"time":"21:15:00"},{"time":"21:20:00"},{"time":"21:25:00"},{"time":"21:30:00"},{"time":"21:35:00"},{"time":"21:40:00"},{"time":"21:45:00"},{"time":"21:50:00"},{"time":"21:55:00"},{"time":"22:00:00"},{"time":"22:05:00"},{"time":"22:10:00"},{"time":"22:15:00"},{"time":"22:20:00"},{"time":"22:25:00"},{"time":"22:30:00"},{"time":"22:35:00"},{"time":"22:40:00"},{"time":"22:45:00"},{"time":"22:50:00"},{"time":"22:55:00"},{"time":"23:00:00"},{"time":"23:05:00"},{"time":"23:10:00"},{"time":"23:15:00"},{"time":"23:20:00"},{"time":"23:25:00"},{"time":"23:30:00"},{"time":"23:35:00"},{"time":"23:40:00"},{"time":"23:45:00"},{"time":"23:50:00"},{"time":"23:55:00"}]
     * guests : [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]
     */

    private boolean status;
    private String message;
    private DietaryBean dietary;
    private VenuesBean venues;
    private List<String> category;
    private List<TimesBean> times;
    private List<Integer> guests;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DietaryBean getDietary() {
        return dietary;
    }

    public void setDietary(DietaryBean dietary) {
        this.dietary = dietary;
    }

    public VenuesBean getVenues() {
        return venues;
    }

    public void setVenues(VenuesBean venues) {
        this.venues = venues;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<TimesBean> getTimes() {
        return times;
    }

    public void setTimes(List<TimesBean> times) {
        this.times = times;
    }

    public List<Integer> getGuests() {
        return guests;
    }

    public void setGuests(List<Integer> guests) {
        this.guests = guests;
    }

    public static class DietaryBean {
        /**
         * veg : Veg
         * non-veg : Non Veg
         */

        private String veg;
        @SerializedName("non-veg")
        private String nonveg;

        public String getVeg() {
            return veg;
        }

        public void setVeg(String veg) {
            this.veg = veg;
        }

        public String getNonveg() {
            return nonveg;
        }

        public void setNonveg(String nonveg) {
            this.nonveg = nonveg;
        }
    }

    public static class VenuesBean {
        /**
         * current_page : 1
         * data : [{"id":67,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":100,"delivery_rating":0,"latitude":null,"longitude":null,"timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"healthy,spicy","product_type":"both","merchant_id":16,"venue_id":"2020102009594316","venue_name":"Food Street","collection_time":30,"is_booking_allow":1,"venue_images":"20201020100338-201807170926215b4db63d8ff97.jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH12 7XD","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":null,"ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":32616,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32616,"freeSheet":1,"totB":1},{"id":32617,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32617,"freeSheet":1,"totB":1},{"id":32618,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32618,"freeSheet":1,"totB":1}],"timing":{"total_offers":0,"opening_time":"2020-12-30 10:00","closing_time":"2020-12-30 20:00","delivery_opening_time":"11:00","delivery_closing_time":"19:00","delivery_closing_datetime":"2020-12-30 19:00","collection_opening_time":"11:00","collection_closing_time":"19:00","is_venue_open":1,"isClose":"1"}},{"id":45,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":100,"delivery_rating":5,"latitude":"51.5095249","longitude":"-0.134223","timing_slot_gap":"30","max_order_slot":"20.00","venue_category":"Veg Food,Drinks,Burger","product_type":"veg","merchant_id":5,"venue_id":"202005271311185","venue_name":"Ankush Pandey","collection_time":30,"is_booking_allow":1,"venue_images":"1590581478-photo-1533481405265-e9ce0c044abb.jpeg","address_1":"24-36 Regent St, St. James's","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"SW1Y 4QF","home_delivery_mov":"1.00","delivery":1,"collection":1,"delivery_distance":120,"distance":"3,559.31","ratingcount":1,"is_like":0,"rate":"2.250000000","reservationSlot":[{"id":12821,"venue_id":"202005271311185","merchant_id":5,"day":3,"time":"10:00:00","priceing_tier":1,"price":"10.00","created_at":"2020-07-13 14:03:21","updated_at":"2020-07-13 14:03:21","time_id":12821,"freeSheet":1,"totB":0},{"id":12822,"venue_id":"202005271311185","merchant_id":5,"day":3,"time":"10:15:00","priceing_tier":1,"price":"10.00","created_at":"2020-07-13 14:03:21","updated_at":"2020-07-13 14:03:21","time_id":12822,"freeSheet":1,"totB":0},{"id":12823,"venue_id":"202005271311185","merchant_id":5,"day":3,"time":"10:30:00","priceing_tier":1,"price":"10.00","created_at":"2020-07-13 14:03:21","updated_at":"2020-07-13 14:03:21","time_id":12823,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 09:00","closing_time":"2020-12-30 22:00","delivery_opening_time":"10:00","delivery_closing_time":"21:00","delivery_closing_datetime":"2020-12-30 21:00","collection_opening_time":"10:00","collection_closing_time":"21:00","is_venue_open":1,"isClose":"1"}},{"id":33,"order_status":1,"venue_type":2,"booking_stay":120,"restaurant_capacity":5,"delivery_rating":5,"latitude":"52.4572148","longitude":"-2.1474445","timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"Wine,Whisky,Rum,Scotch,Vodka,champagne","product_type":"veg","merchant_id":15,"venue_id":"2020042710540215","venue_name":"Majestic Stourbridge","collection_time":45,"is_booking_allow":1,"venue_images":"20200918122353-20200520154611-majestic.jpg","address_1":"Stourbridge Town Hall, Crown Lane, Stourbridge, UK","address_2":null,"city_name":"Stourbridge","country":"United Kingdom","post_code":"DY8 1YE","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":10000,"distance":"3,625.92","ratingcount":4,"is_like":0,"rate":"4.562500000","reservationSlot":[{"id":27148,"venue_id":"2020042710540215","merchant_id":15,"day":3,"time":"10:05:00","priceing_tier":1,"price":"8.00","created_at":"2020-09-18 12:23:53","updated_at":"2020-09-18 12:23:53","time_id":27148,"freeSheet":1,"totB":0},{"id":27149,"venue_id":"2020042710540215","merchant_id":15,"day":3,"time":"10:35:00","priceing_tier":1,"price":"8.00","created_at":"2020-09-18 12:23:53","updated_at":"2020-09-18 12:23:53","time_id":27149,"freeSheet":1,"totB":0},{"id":27150,"venue_id":"2020042710540215","merchant_id":15,"day":3,"time":"11:05:00","priceing_tier":1,"price":"8.00","created_at":"2020-09-18 12:23:53","updated_at":"2020-09-18 12:23:53","time_id":27150,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 05:05","closing_time":"2020-12-31 00:00","delivery_opening_time":"05:05","delivery_closing_time":"22:40","delivery_closing_datetime":"2020-12-30 22:40","collection_opening_time":"05:05","collection_closing_time":"20:00","is_venue_open":1,"isClose":"0"}},{"id":43,"order_status":1,"venue_type":2,"booking_stay":120,"restaurant_capacity":100,"delivery_rating":5,"latitude":"52.578609","longitude":"-2.1256158","timing_slot_gap":"30","max_order_slot":"15.00","venue_category":"Drinks,Wines,Beers,Whisky","product_type":"veg","merchant_id":15,"venue_id":"2020052208361515","venue_name":"Hedonism Wines","collection_time":30,"is_booking_allow":1,"venue_images":"1590132975-Header-Hedonism-Wines-e1516542447373.jpg","address_1":"Shell, Birmingham Road, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV2 3LH","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":"3,634.28","ratingcount":1,"is_like":0,"rate":"5.000000000","reservationSlot":[{"id":21413,"venue_id":"2020052208361515","merchant_id":15,"day":3,"time":"11:00:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-18 09:42:27","updated_at":"2020-07-18 09:42:27","time_id":21413,"freeSheet":1,"totB":0},{"id":21414,"venue_id":"2020052208361515","merchant_id":15,"day":3,"time":"11:30:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-18 09:42:27","updated_at":"2020-07-18 09:42:27","time_id":21414,"freeSheet":1,"totB":0},{"id":21415,"venue_id":"2020052208361515","merchant_id":15,"day":3,"time":"12:00:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-18 09:42:27","updated_at":"2020-07-18 09:42:27","time_id":21415,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 10:00","closing_time":"2020-12-31 00:00","delivery_opening_time":"11:00","delivery_closing_time":"22:50","delivery_closing_datetime":"2020-12-30 22:50","collection_opening_time":"11:00","collection_closing_time":"21:10","is_venue_open":1,"isClose":"1"}},{"id":35,"order_status":1,"venue_type":2,"booking_stay":180,"restaurant_capacity":1000,"delivery_rating":3,"latitude":"52.5853501","longitude":"-2.1314475","timing_slot_gap":"30","max_order_slot":"15.00","venue_category":"thai food,pork,curries","product_type":"both","merchant_id":5,"venue_id":"202004281056405","venue_name":"Made in Thai","collection_time":70,"is_booking_allow":1,"venue_images":"20200506084120-F64BJAT2VZESHJAXYH3JKFH4IQ.jpeg","address_1":"24 Darlington St, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 4HW","home_delivery_mov":"5.0","delivery":0,"collection":1,"delivery_distance":14,"distance":"3,634.75","ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":10073,"venue_id":"202004281056405","merchant_id":5,"day":3,"time":"11:00:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-03 14:39:17","updated_at":"2020-07-03 14:39:17","time_id":10073,"freeSheet":1,"totB":0},{"id":10074,"venue_id":"202004281056405","merchant_id":5,"day":3,"time":"11:15:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-03 14:39:17","updated_at":"2020-07-03 14:39:17","time_id":10074,"freeSheet":1,"totB":0},{"id":10075,"venue_id":"202004281056405","merchant_id":5,"day":3,"time":"11:30:00","priceing_tier":1,"price":"0.00","created_at":"2020-07-03 14:39:17","updated_at":"2020-07-03 14:39:17","time_id":10075,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 09:00","closing_time":"2020-12-31 00:55","delivery_opening_time":"11:00","delivery_closing_time":"21:00","delivery_closing_datetime":"2020-12-30 21:00","collection_opening_time":"11:00","collection_closing_time":"21:00","is_venue_open":1,"isClose":"1"}},{"id":62,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":50,"delivery_rating":0,"latitude":"52.5875483","longitude":"-2.120122100000001","timing_slot_gap":"30","max_order_slot":"25.00","venue_category":"Drinks,Food,Takeaway","product_type":"both","merchant_id":15,"venue_id":"2020100508573315","venue_name":"Bella Cosa","collection_time":45,"is_booking_allow":1,"venue_images":"20201005090417-Bella-Cosa.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":1000,"distance":"3,634.89","ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":36990,"venue_id":"2020100508573315","merchant_id":15,"day":3,"time":"10:10:00","priceing_tier":1,"price":"5.00","created_at":"2020-12-07 07:20:06","updated_at":"2020-12-07 07:20:06","time_id":36990,"freeSheet":1,"totB":0},{"id":36991,"venue_id":"2020100508573315","merchant_id":15,"day":3,"time":"10:25:00","priceing_tier":1,"price":"5.00","created_at":"2020-12-07 07:20:06","updated_at":"2020-12-07 07:20:06","time_id":36991,"freeSheet":1,"totB":0},{"id":36992,"venue_id":"2020100508573315","merchant_id":15,"day":3,"time":"10:40:00","priceing_tier":1,"price":"5.00","created_at":"2020-12-07 07:20:06","updated_at":"2020-12-07 07:20:06","time_id":36992,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 ","closing_time":"2020-12-30 ","delivery_opening_time":"08:05","delivery_closing_time":"21:05","delivery_closing_datetime":"2020-12-30 21:05","collection_opening_time":"08:10","collection_closing_time":"21:10","is_venue_open":1,"isClose":"1"}},{"id":42,"order_status":0,"venue_type":2,"booking_stay":300,"restaurant_capacity":100,"delivery_rating":5,"latitude":"52.5923018","longitude":"-2.1383264","timing_slot_gap":"30","max_order_slot":"15.00","venue_category":"FastFood,Drinks","product_type":"both","merchant_id":15,"venue_id":"2020052207223015","venue_name":"Leon Naturally Fast Food","collection_time":40,"is_booking_allow":1,"venue_images":"1590128550-Vision-at-Leon-Stansted-Airport.jpg","address_1":"UKIM Madinah Masjid, New Hampton Road East, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 4BB","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":"3,635.24","ratingcount":1,"is_like":0,"rate":"5.000000000","reservationSlot":[{"id":1547,"venue_id":"2020052207223015","merchant_id":15,"day":3,"time":"10:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-07-01 13:43:39","updated_at":"2020-07-01 13:43:39","time_id":1547,"freeSheet":1,"totB":0},{"id":1548,"venue_id":"2020052207223015","merchant_id":15,"day":3,"time":"10:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-07-01 13:43:39","updated_at":"2020-07-01 13:43:39","time_id":1548,"freeSheet":1,"totB":0},{"id":1549,"venue_id":"2020052207223015","merchant_id":15,"day":3,"time":"10:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-07-01 13:43:39","updated_at":"2020-07-01 13:43:39","time_id":1549,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 08:00","closing_time":"2020-12-30 22:00","delivery_opening_time":"08:30","delivery_closing_time":"22:30","delivery_closing_datetime":"2020-12-30 22:30","collection_opening_time":"08:30","collection_closing_time":"22:30","is_venue_open":1,"isClose":"0"}},{"id":34,"order_status":1,"venue_type":2,"booking_stay":240,"restaurant_capacity":100,"delivery_rating":3,"latitude":"53.4538557","longitude":"-2.9756757","timing_slot_gap":"15","max_order_slot":"40.00","venue_category":"Chicken,Pizza,Burger","product_type":"both","merchant_id":5,"venue_id":"202004271734165","venue_name":"Dixi Chicken Pizza","collection_time":70,"is_booking_allow":1,"venue_images":"20200622115653-resize.jpg","address_1":"Southport Road, Liverpool, Bootle, UK","address_2":null,"city_name":"Liverpool","country":"United Kingdom","post_code":"L31QR","home_delivery_mov":"0.30","delivery":1,"collection":1,"delivery_distance":1000,"distance":"3,695.69","ratingcount":3,"is_like":0,"rate":"4.500000000","reservationSlot":[{"id":38404,"venue_id":"202004271734165","merchant_id":5,"day":3,"time":"12:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-12-29 09:10:10","updated_at":"2020-12-29 09:10:10","time_id":38404,"freeSheet":1,"totB":0},{"id":38405,"venue_id":"202004271734165","merchant_id":5,"day":3,"time":"12:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-12-29 09:10:10","updated_at":"2020-12-29 09:10:10","time_id":38405,"freeSheet":1,"totB":0},{"id":38406,"venue_id":"202004271734165","merchant_id":5,"day":3,"time":"12:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-12-29 09:10:10","updated_at":"2020-12-29 09:10:10","time_id":38406,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 07:30","closing_time":"2020-12-31 00:00","delivery_opening_time":"10:05","delivery_closing_time":"22:00","delivery_closing_datetime":"2020-12-30 22:00","collection_opening_time":"05:00","collection_closing_time":"22:30","is_venue_open":1,"isClose":"0"}},{"id":31,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":25,"delivery_rating":5,"latitude":"55.9428422","longitude":"-3.2831273","timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"healthy, spicy","product_type":"non-veg","merchant_id":16,"venue_id":"2020042308172516","venue_name":"Raman Restaurant","collection_time":70,"is_booking_allow":1,"venue_images":"20200520154700-what-to-eat-in-morocco.jpg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"th floor 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","city_name":"castophine","country":"United Kingdom","post_code":"eh127xd","home_delivery_mov":"20.00","delivery":1,"collection":1,"delivery_distance":0,"distance":"3,867.74","ratingcount":3,"is_like":0,"rate":"3.250000000","reservationSlot":[{"id":34286,"venue_id":"2020042308172516","merchant_id":16,"day":3,"time":"10:00:00","priceing_tier":1,"price":"3.00","created_at":"2020-11-10 09:08:22","updated_at":"2020-11-10 09:08:22","time_id":34286,"freeSheet":1,"totB":0},{"id":34287,"venue_id":"2020042308172516","merchant_id":16,"day":3,"time":"10:30:00","priceing_tier":1,"price":"3.00","created_at":"2020-11-10 09:08:22","updated_at":"2020-11-10 09:08:22","time_id":34287,"freeSheet":1,"totB":0},{"id":34288,"venue_id":"2020042308172516","merchant_id":16,"day":3,"time":"11:00:00","priceing_tier":1,"price":"3.00","created_at":"2020-11-10 09:08:22","updated_at":"2020-11-10 09:08:22","time_id":34288,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 08:00","closing_time":"2020-12-30 22:00","delivery_opening_time":"09:00","delivery_closing_time":"22:00","delivery_closing_datetime":"2020-12-30 22:00","collection_opening_time":"09:00","collection_closing_time":"22:00","is_venue_open":1,"isClose":"0"}},{"id":69,"order_status":1,"venue_type":2,"booking_stay":90,"restaurant_capacity":50,"delivery_rating":0,"latitude":"55.9431887","longitude":"-3.2894863","timing_slot_gap":"15","max_order_slot":"5.00","venue_category":"helathy","product_type":"both","merchant_id":16,"venue_id":"2020102010034016","venue_name":"Food World","collection_time":30,"is_booking_allow":1,"venue_images":"20201020100752-BL09FOODWORLD1.jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Corstorphine","country":"United Kingdom","post_code":"EH12 7XD","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":"3,867.77","ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":32392,"venue_id":"2020102010034016","merchant_id":16,"day":3,"time":"12:00:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:13:01","updated_at":"2020-10-20 10:13:01","time_id":32392,"freeSheet":1,"totB":0},{"id":32393,"venue_id":"2020102010034016","merchant_id":16,"day":3,"time":"12:15:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:13:01","updated_at":"2020-10-20 10:13:01","time_id":32393,"freeSheet":1,"totB":0},{"id":32394,"venue_id":"2020102010034016","merchant_id":16,"day":3,"time":"12:30:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:13:01","updated_at":"2020-10-20 10:13:01","time_id":32394,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 11:00","closing_time":"2020-12-30 21:00","delivery_opening_time":"12:00","delivery_closing_time":"20:00","delivery_closing_datetime":"2020-12-30 20:00","collection_opening_time":"12:00","collection_closing_time":"20:00","is_venue_open":1,"isClose":"1"}},{"id":70,"order_status":1,"venue_type":2,"booking_stay":60,"restaurant_capacity":50,"delivery_rating":0,"latitude":"55.953252","longitude":"-3.188267","timing_slot_gap":"15","max_order_slot":"5.00","venue_category":"healthy, tasty","product_type":"both","merchant_id":16,"venue_id":"2020102010121316","venue_name":"Food Park","collection_time":30,"is_booking_allow":1,"venue_images":"20201020101823-hotel-food-park-raigad-maharashtra-1xdgxa4vc2.jpg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH12 7XD","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"distance":"3,868.34","ratingcount":0,"is_like":0,"rate":null,"reservationSlot":[{"id":32840,"venue_id":"2020102010121316","merchant_id":16,"day":3,"time":"11:00:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:18:23","updated_at":"2020-10-20 10:18:23","time_id":32840,"freeSheet":1,"totB":0},{"id":32841,"venue_id":"2020102010121316","merchant_id":16,"day":3,"time":"11:15:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:18:23","updated_at":"2020-10-20 10:18:23","time_id":32841,"freeSheet":1,"totB":0},{"id":32842,"venue_id":"2020102010121316","merchant_id":16,"day":3,"time":"11:30:00","priceing_tier":1,"price":"1.00","created_at":"2020-10-20 10:18:23","updated_at":"2020-10-20 10:18:23","time_id":32842,"freeSheet":1,"totB":0}],"timing":{"total_offers":0,"opening_time":"2020-12-30 20:00","closing_time":"2020-12-30 22:00","delivery_opening_time":"11:00","delivery_closing_time":"19:00","delivery_closing_datetime":"2020-12-30 19:00","collection_opening_time":"11:00","collection_closing_time":"19:00","is_venue_open":1,"isClose":"1"}}]
         * first_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getReservationVenue?page=1
         * from : 1
         * last_page : 1
         * last_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getReservationVenue?page=1
         * next_page_url : null
         * path : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getReservationVenue
         * per_page : 100
         * prev_page_url : null
         * to : 11
         * total : 11
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private Object next_page_url;
        private String path;
        private int per_page;
        private Object prev_page_url;
        private int to;
        private int total;
        private List<DataBean> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public Object getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(Object next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public Object getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(Object prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 67
             * order_status : 1
             * venue_type : 2
             * booking_stay : 60
             * restaurant_capacity : 100
             * delivery_rating : 0
             * latitude : null
             * longitude : null
             * timing_slot_gap : 15
             * max_order_slot : 10.00
             * venue_category : healthy,spicy
             * product_type : both
             * merchant_id : 16
             * venue_id : 2020102009594316
             * venue_name : Food Street
             * collection_time : 30
             * is_booking_allow : 1
             * venue_images : 20201020100338-201807170926215b4db63d8ff97.jpeg
             * address_1 : 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom
             * address_2 : 7th flor
             * city_name : Edinburgh
             * country : United Kingdom
             * post_code : EH12 7XD
             * home_delivery_mov : 10.00
             * delivery : 1
             * collection : 1
             * delivery_distance : 100
             * distance : null
             * ratingcount : 0
             * is_like : 0
             * rate : null
             * reservationSlot : [{"id":32616,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32616,"freeSheet":1,"totB":1},{"id":32617,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32617,"freeSheet":1,"totB":1},{"id":32618,"venue_id":"2020102009594316","merchant_id":16,"day":3,"time":"11:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38","time_id":32618,"freeSheet":1,"totB":1}]
             * timing : {"total_offers":0,"opening_time":"2020-12-30 10:00","closing_time":"2020-12-30 20:00","delivery_opening_time":"11:00","delivery_closing_time":"19:00","delivery_closing_datetime":"2020-12-30 19:00","collection_opening_time":"11:00","collection_closing_time":"19:00","is_venue_open":1,"isClose":"1"}
             */

            private int id;
            private int order_status;
            private int venue_type;
            private int booking_stay;
            private int restaurant_capacity;
            private int delivery_rating;
            private Object latitude;
            private Object longitude;
            private String timing_slot_gap;
            private String max_order_slot;
            private String venue_category;
            private String product_type;
            private int merchant_id;
            private String venue_id;
            private String venue_name;
            private int collection_time;
            private int is_booking_allow;
            private String venue_images;
            private String address_1;
            private String address_2;
            private String city_name;
            private String country;
            private String post_code;
            private String home_delivery_mov;
            private int delivery;
            private int default_reservation_length;
            private int collection;
            private int delivery_distance;
            private String distance;
            private int ratingcount;
            private int is_like;
            private String rate;
            private TimingBean timing;
            private List<ReservationSlotBean> reservationSlot;

            public int getDefault_reservation_length() {
                return default_reservation_length;
            }

            public void setDefault_reservation_length(int default_reservation_length) {
                this.default_reservation_length = default_reservation_length;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
            }

            public int getVenue_type() {
                return venue_type;
            }

            public void setVenue_type(int venue_type) {
                this.venue_type = venue_type;
            }

            public int getBooking_stay() {
                return booking_stay;
            }

            public void setBooking_stay(int booking_stay) {
                this.booking_stay = booking_stay;
            }

            public int getRestaurant_capacity() {
                return restaurant_capacity;
            }

            public void setRestaurant_capacity(int restaurant_capacity) {
                this.restaurant_capacity = restaurant_capacity;
            }

            public int getDelivery_rating() {
                return delivery_rating;
            }

            public void setDelivery_rating(int delivery_rating) {
                this.delivery_rating = delivery_rating;
            }

            public Object getLatitude() {
                return latitude;
            }

            public void setLatitude(Object latitude) {
                this.latitude = latitude;
            }

            public Object getLongitude() {
                return longitude;
            }

            public void setLongitude(Object longitude) {
                this.longitude = longitude;
            }

            public String getTiming_slot_gap() {
                return timing_slot_gap;
            }

            public void setTiming_slot_gap(String timing_slot_gap) {
                this.timing_slot_gap = timing_slot_gap;
            }

            public String getMax_order_slot() {
                return max_order_slot;
            }

            public void setMax_order_slot(String max_order_slot) {
                this.max_order_slot = max_order_slot;
            }

            public String getVenue_category() {
                return venue_category;
            }

            public void setVenue_category(String venue_category) {
                this.venue_category = venue_category;
            }

            public String getProduct_type() {
                return product_type;
            }

            public void setProduct_type(String product_type) {
                this.product_type = product_type;
            }

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public String getVenue_id() {
                return venue_id;
            }

            public void setVenue_id(String venue_id) {
                this.venue_id = venue_id;
            }

            public String getVenue_name() {
                return venue_name;
            }

            public void setVenue_name(String venue_name) {
                this.venue_name = venue_name;
            }

            public int getCollection_time() {
                return collection_time;
            }

            public void setCollection_time(int collection_time) {
                this.collection_time = collection_time;
            }

            public int getIs_booking_allow() {
                return is_booking_allow;
            }

            public void setIs_booking_allow(int is_booking_allow) {
                this.is_booking_allow = is_booking_allow;
            }

            public String getVenue_images() {
                return venue_images;
            }

            public void setVenue_images(String venue_images) {
                this.venue_images = venue_images;
            }

            public String getAddress_1() {
                return address_1;
            }

            public void setAddress_1(String address_1) {
                this.address_1 = address_1;
            }

            public String getAddress_2() {
                return address_2;
            }

            public void setAddress_2(String address_2) {
                this.address_2 = address_2;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getPost_code() {
                return post_code;
            }

            public void setPost_code(String post_code) {
                this.post_code = post_code;
            }

            public String getHome_delivery_mov() {
                return home_delivery_mov;
            }

            public void setHome_delivery_mov(String home_delivery_mov) {
                this.home_delivery_mov = home_delivery_mov;
            }

            public int getDelivery() {
                return delivery;
            }

            public void setDelivery(int delivery) {
                this.delivery = delivery;
            }

            public int getCollection() {
                return collection;
            }

            public void setCollection(int collection) {
                this.collection = collection;
            }

            public int getDelivery_distance() {
                return delivery_distance;
            }

            public void setDelivery_distance(int delivery_distance) {
                this.delivery_distance = delivery_distance;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public int getRatingcount() {
                return ratingcount;
            }

            public void setRatingcount(int ratingcount) {
                this.ratingcount = ratingcount;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public TimingBean getTiming() {
                return timing;
            }

            public void setTiming(TimingBean timing) {
                this.timing = timing;
            }

            public List<ReservationSlotBean> getReservationSlot() {
                return reservationSlot;
            }

            public void setReservationSlot(List<ReservationSlotBean> reservationSlot) {
                this.reservationSlot = reservationSlot;
            }

            public static class TimingBean {
                /**
                 * total_offers : 0
                 * opening_time : 2020-12-30 10:00
                 * closing_time : 2020-12-30 20:00
                 * delivery_opening_time : 11:00
                 * delivery_closing_time : 19:00
                 * delivery_closing_datetime : 2020-12-30 19:00
                 * collection_opening_time : 11:00
                 * collection_closing_time : 19:00
                 * is_venue_open : 1
                 * isClose : 1
                 */

                private int total_offers;
                private String opening_time;
                private String closing_time;
                private String delivery_opening_time;
                private String delivery_closing_time;
                private String delivery_closing_datetime;
                private String collection_opening_time;
                private String collection_closing_time;
                private int is_venue_open;
                private String isClose;

                public int getTotal_offers() {
                    return total_offers;
                }

                public void setTotal_offers(int total_offers) {
                    this.total_offers = total_offers;
                }

                public String getOpening_time() {
                    return opening_time;
                }

                public void setOpening_time(String opening_time) {
                    this.opening_time = opening_time;
                }

                public String getClosing_time() {
                    return closing_time;
                }

                public void setClosing_time(String closing_time) {
                    this.closing_time = closing_time;
                }

                public String getDelivery_opening_time() {
                    return delivery_opening_time;
                }

                public void setDelivery_opening_time(String delivery_opening_time) {
                    this.delivery_opening_time = delivery_opening_time;
                }

                public String getDelivery_closing_time() {
                    return delivery_closing_time;
                }

                public void setDelivery_closing_time(String delivery_closing_time) {
                    this.delivery_closing_time = delivery_closing_time;
                }

                public String getDelivery_closing_datetime() {
                    return delivery_closing_datetime;
                }

                public void setDelivery_closing_datetime(String delivery_closing_datetime) {
                    this.delivery_closing_datetime = delivery_closing_datetime;
                }

                public String getCollection_opening_time() {
                    return collection_opening_time;
                }

                public void setCollection_opening_time(String collection_opening_time) {
                    this.collection_opening_time = collection_opening_time;
                }

                public String getCollection_closing_time() {
                    return collection_closing_time;
                }

                public void setCollection_closing_time(String collection_closing_time) {
                    this.collection_closing_time = collection_closing_time;
                }

                public int getIs_venue_open() {
                    return is_venue_open;
                }

                public void setIs_venue_open(int is_venue_open) {
                    this.is_venue_open = is_venue_open;
                }

                public String getIsClose() {
                    return isClose;
                }

                public void setIsClose(String isClose) {
                    this.isClose = isClose;
                }
            }

            public static class ReservationSlotBean {
                /**
                 * id : 32616
                 * venue_id : 2020102009594316
                 * merchant_id : 16
                 * day : 3
                 * time : 11:00:00
                 * priceing_tier : 1
                 * price : 2.00
                 * created_at : 2020-10-20 10:13:38
                 * updated_at : 2020-10-20 10:13:38
                 * time_id : 32616
                 * freeSheet : 1
                 * totB : 1
                 */

                private int id;
                private String venue_id;
                private int merchant_id;
                private int day;
                private String time;
                private int priceing_tier;
                private String price;
                private String created_at;
                private String updated_at;
                private int time_id;
                private int freeSheet;
                private int totB;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getVenue_id() {
                    return venue_id;
                }

                public void setVenue_id(String venue_id) {
                    this.venue_id = venue_id;
                }

                public int getMerchant_id() {
                    return merchant_id;
                }

                public void setMerchant_id(int merchant_id) {
                    this.merchant_id = merchant_id;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getPriceing_tier() {
                    return priceing_tier;
                }

                public void setPriceing_tier(int priceing_tier) {
                    this.priceing_tier = priceing_tier;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public int getTime_id() {
                    return time_id;
                }

                public void setTime_id(int time_id) {
                    this.time_id = time_id;
                }

                public int getFreeSheet() {
                    return freeSheet;
                }

                public void setFreeSheet(int freeSheet) {
                    this.freeSheet = freeSheet;
                }

                public int getTotB() {
                    return totB;
                }

                public void setTotB(int totB) {
                    this.totB = totB;
                }
            }
        }
    }

    public static class TimesBean {
        /**
         * time : 10:00:00
         */

        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
