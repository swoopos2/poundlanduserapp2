package com.poundland.retail.model.responseModel;

import java.io.Serializable;
import java.util.List;

public class LandingPageTopResponseModel {

    /**
     * status : true
     * banners : [{"id":9,"merchant_id":5,"venue_id":"202002121635135","app_banner_for":1,"banner_type":0,"user_app":1,"app_banner":"uploaded/banner/appbanner/1410115887483120.png","banner_placement_main":1,"category":0,"start_time":"00:00:00","end_time":"23:00:00","product_categories_id":"3","appProductUrl":"","gender_for_show":"Both","appCatUrl":1,"venue_name":"Lillywhites"},{"id":8,"merchant_id":5,"venue_id":"202002121635135","app_banner_for":1,"banner_type":0,"user_app":1,"app_banner":"uploaded/banner/appbanner/9078615887484700.png","banner_placement_main":1,"category":3,"start_time":"00:00:00","end_time":"23:00:00","product_categories_id":"3","appProductUrl":"","gender_for_show":"Both","appCatUrl":1,"venue_name":"Lillywhites"},{"id":7,"merchant_id":5,"venue_id":"202002121635135","app_banner_for":1,"banner_type":0,"user_app":1,"app_banner":"uploaded/banner/appbanner/4750515887487400.png","banner_placement_main":1,"category":3,"start_time":"00:00:00","end_time":"23:00:00","product_categories_id":"","appProductUrl":"171","gender_for_show":"Both","appCatUrl":null,"venue_name":"Lillywhites"}]
     * total_liked : 0
     * total_quantity : 0
     * total_bulk_quantity : 0
     * total_carts : 0
     * retailVanues : [{"id":47,"merchant_id":16,"venue_id":"2020061707363216","venue_name":"World of Cloth","venue_images":"20200918135831-20200617083735-New-folder-1024x576.jpg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH127XD","phone_number":"919717802669","venue_email":"willusssmith2019@gmail.com","venue_website":null,"home_delivery_mov":"10.00","start_days":1,"end_days":90,"collection_time":60,"preparation_time":null,"free_delivery":"20.00","delivery_charge":"2.00","delivery":1,"collection":0,"latitude":"55.9428422","longitude":"-3.2831273","delivery_rating":0,"distance":"3,648.32","product_count":303,"opening_time":"2021-03-02 08:00","closing_time":"2021-03-02 22:00","isClose":"0","stars":"4.3","total_offers":0,"isWishlisted":false},{"id":5,"merchant_id":5,"venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","phone_number":"03443325602","venue_email":"lillywhites@swoopos.com","venue_website":null,"home_delivery_mov":"10.00","start_days":3,"end_days":5,"collection_time":60,"preparation_time":65,"free_delivery":"22.00","delivery_charge":"1.00","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_rating":0,"distance":"3,690.06","product_count":107,"opening_time":"2021-03-02 06:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"3.5","total_offers":0,"isWishlisted":true},{"id":40,"merchant_id":15,"venue_id":"2020051914514215","venue_name":"New Look","venue_images":"20200918135628-1589896302-new-look-cornwallPNG.png","address_1":"UKIM Madinah Masjid, New Hampton Road East","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 4BB","phone_number":"044254877984","venue_email":"newlook@gilfun.com","venue_website":null,"home_delivery_mov":"10.00","start_days":1,"end_days":3,"collection_time":30,"preparation_time":null,"free_delivery":"20.00","delivery_charge":"2.00","delivery":1,"collection":1,"latitude":"52.5923018","longitude":"-2.1383264","delivery_rating":0,"distance":"3,726.63","product_count":63,"opening_time":"2021-03-02 08:00","closing_time":"2021-03-02 22:00","isClose":"0","stars":"3.4","total_offers":0,"isWishlisted":false},{"id":54,"merchant_id":21,"venue_id":"2020071407361121","venue_name":"GREGGS","venue_images":"20200918135302-1594708571-PRI_97768867.jpg","address_1":"London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"WV30DS","phone_number":"915467987987897","venue_email":"greggs@votavk.com 2021-03-02 18:59:21.429 D/OkHttp: ","venue_website":"https://www.greggs.com","home_delivery_mov":"10.00","start_days":1,"end_days":4,"collection_time":40,"preparation_time":null,"free_delivery":"22.00","delivery_charge":"2.00","delivery":0,"collection":0,"latitude":"51.5073509","longitude":"-0.1277583","delivery_rating":0,"distance":"3,689.89","product_count":61,"opening_time":"2021-03-02 07:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"3.7","total_offers":0,"isWishlisted":false},{"id":71,"merchant_id":15,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","phone_number":"919140832767","venue_email":"ankushpandey007@gmail.com","venue_website":null,"home_delivery_mov":"12.00","start_days":1,"end_days":3,"collection_time":40,"preparation_time":null,"free_delivery":"20.00","delivery_charge":"2.00","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_rating":0,"distance":"3,726.12","product_count":55,"opening_time":"2021-03-02 06:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"4.2","total_offers":0,"isWishlisted":false},{"id":25,"merchant_id":16,"venue_id":"2020040712023516","venue_name":"Raman Venue","venue_images":"1586257355.images (4).jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH127XD","phone_number":"789456799","venue_email":"ramanvenue@mailinator.com","venue_website":null,"home_delivery_mov":"15.00","start_days":10,"end_days":20,"collection_time":90,"preparation_time":15,"free_delivery":"10.00","delivery_charge":"2.00","delivery":1,"collection":1,"latitude":"55.9428422","longitude":"-3.2831273","delivery_rating":0,"distance":"3,648.32","product_count":45,"opening_time":"2021-03-02 10:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"0","total_offers":0,"isWishlisted":false},{"id":39,"merchant_id":16,"venue_id":"2020051914423516","venue_name":"New Retail raman","venue_images":"20200918135658-20200527064217-images (9).jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH127XD","phone_number":"9638528956","venue_email":"newretail@mailinator.com","venue_website":null,"home_delivery_mov":"15.00","start_days":1,"end_days":2,"collection_time":90,"preparation_time":null,"free_delivery":"30.00","delivery_charge":"5.00","delivery":1,"collection":1,"latitude":"55.9428422","longitude":"-3.2831273","delivery_rating":0,"distance":"3,648.32","product_count":34,"opening_time":"2021-03-02 06:00","closing_time":"2021-03-02 20:00","isClose":"0","stars":"0","total_offers":0,"isWishlisted":false},{"id":60,"merchant_id":23,"venue_id":"2020092807055523","venue_name":"The Cornershop","venue_images":"1601273156-Corner-shop.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","phone_number":"01215845258","venue_email":"thecorner@qatw.net","venue_website":"https://www.thecornershop.com","home_delivery_mov":"8.00","start_days":2,"end_days":4,"collection_time":45,"preparation_time":null,"free_delivery":"15.00","delivery_charge":"1.00","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_rating":0,"distance":"3,726.12","product_count":33,"opening_time":"2021-03-02 08:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"4.3","total_offers":0,"isWishlisted":true},{"id":21,"merchant_id":5,"venue_id":"202003250514135","venue_name":"LW London","venue_images":"20200918135540-20200506075301-unnamed (6).jpg","address_1":"The Oval, Kennington Oval, London, UK","address_2":null,"city_na 2021-03-02 18:59:21.429 D/OkHttp: me":"London","country":"United Kingdom","post_code":"SE11 5SS","phone_number":"4324324234","venue_email":"fedro@alrmail.com","venue_website":null,"home_delivery_mov":"20.00","start_days":1,"end_days":2,"collection_time":100,"preparation_time":70,"free_delivery":"0.00","delivery_charge":"0.00","delivery":1,"collection":1,"latitude":"51.4837565","longitude":"-0.1149737","delivery_rating":0,"distance":"3,690.25","product_count":29,"opening_time":"2021-03-02 09:00","closing_time":"2021-03-02 10:30","isClose":"1","stars":"4.3","total_offers":0,"isWishlisted":false},{"id":56,"merchant_id":16,"venue_id":"2020071409041916","venue_name":"Fashion World","venue_images":"20210215111635-Fashion-World-01.jpg","address_1":"Saint John's Road","address_2":null,"city_name":"Edgi","country":"United Kingdom","post_code":"EH12 7XD","phone_number":"918076938481","venue_email":"akeshramany2k@gmail.com","venue_website":null,"home_delivery_mov":"10.00","start_days":1,"end_days":2,"collection_time":70,"preparation_time":null,"free_delivery":"20.00","delivery_charge":"5.00","delivery":1,"collection":1,"latitude":"55.9429476","longitude":"-3.2895802","delivery_rating":0,"distance":"3,648.53","product_count":29,"opening_time":"2021-03-02 10:00","closing_time":"2021-03-02 20:00","isClose":"0","stars":"0","total_offers":0,"isWishlisted":false},{"id":28,"merchant_id":15,"venue_id":"2020040808100815","venue_name":"Go Amazon Retail","venue_images":"20200918135343-20200506074957-9oa83k5s.jpeg","address_1":"Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV30DS","phone_number":"454645479878","venue_email":"goamazon@svpmail.com","venue_website":null,"home_delivery_mov":"15.00","start_days":1,"end_days":3,"collection_time":30,"preparation_time":45,"free_delivery":"20.00","delivery_charge":"2.00","delivery":1,"collection":1,"latitude":"52.586973","longitude":"-2.12882","delivery_rating":0,"distance":"3,726.47","product_count":21,"opening_time":"2021-03-02 06:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"3.2","total_offers":0,"isWishlisted":false},{"id":29,"merchant_id":16,"venue_id":"2020040908350416","venue_name":"Indian Cloth","venue_images":"1586417705.download (11).jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH12 7XD","phone_number":"9511599515","venue_email":"ramancloth@mailinator.com","venue_website":null,"home_delivery_mov":"50.00","start_days":1,"end_days":2,"collection_time":30,"preparation_time":30,"free_delivery":"10.00","delivery_charge":"5.00","delivery":1,"collection":1,"latitude":"55.9428422","longitude":"-3.2831273","delivery_rating":0,"distance":"3,648.32","product_count":13,"opening_time":"2021-03-02 04:00","closing_time":"2021-03-02 23:00","isClose":"0","stars":"0","total_offers":0,"isWishlisted":false}]
     * total_retailVenues : 22
     * swoopeEatVanues : [{"id":31,"merchant_id":16,"venue_id":"2020042308172516","venue_name":"Raman Restaurant","venue_images":"20200520154700-what-to-eat-in-morocco.jpg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"th floor 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","city_name":"castophine","country":"United Kingdom","post_code":"eh127xd","phone_number":"1478523697","venue_email":"ramanhospitality@mailinator.com","venue_website":null,"home_delivery_mov":"20.00","start_days":60,"end_days":90,"collection_time":70,"preparation_time":30,"free_delivery":"30.00","delivery_charge":"2.00","delivery":1,"collection":1,"is_booking_allow":1,"latitude":"55.9428422","longitude":"-3.2831273","venue_category":["healthy"," spicy"],"delivery_rating":5,"distance":"3,648.32","opening_time":"2021-03-02 08:00","closing_time":"2021-03-02 22:00","isClose":"0","stars":"3.1","total_offers":0,"isWishlisted":true},{"id":33,"merchant_id":15,"venue_id":"2020042710540215","ve 2021-03-02 18:59:21.429 D/OkHttp: nue_name":"Majestic Stourbridge","venue_images":"20200918122353-20200520154611-majestic.jpg","address_1":"Stourbridge Town Hall, Crown Lane, Stourbridge, UK","address_2":null,"city_name":"Stourbridge","country":"United Kingdom","post_code":"DY8 1YE","phone_number":"01902747525","venue_email":"majestick@whmailtop.com","venue_website":null,"home_delivery_mov":"10.00","start_days":30,"end_days":45,"collection_time":45,"preparation_time":35,"free_delivery":"12.00","delivery_charge":"2.00","delivery":1,"collection":1,"is_booking_allow":1,"latitude":"52.4572148","longitude":"-2.1474445","venue_category":["Wine","Whisky","Rum"],"delivery_rating":5,"distance":"3,732.05","opening_time":"2021-03-02 06:05","closing_time":"2021-03-02 23:00","isClose":"0","stars":"4.6","total_offers":0,"isWishlisted":false},{"id":41,"merchant_id":16,"venue_id":"2020052110402516","venue_name":"Your Resturant","venue_images":"20200522093601-images (8).jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH12 7XD","phone_number":"8578591535","venue_email":"willussmith2019@gmail.com","venue_website":null,"home_delivery_mov":"20.00","start_days":50,"end_days":60,"collection_time":90,"preparation_time":null,"free_delivery":"30.00","delivery_charge":"2.00","delivery":1,"collection":1,"is_booking_allow":0,"latitude":null,"longitude":null,"venue_category":["healthy","Tasty","spicy"],"delivery_rating":3,"distance":null,"opening_time":"2021-03-02 07:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"4.3","total_offers":0,"isWishlisted":true},{"id":72,"merchant_id":5,"venue_id":"202010281859135","venue_name":"Vik Grocery","venue_images":"1603911554-download.jpeg","address_1":"Oaklands Road, Wolverhampton WV3 0DS, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","phone_number":"91918236375","venue_email":"vikalpweb@gmail.com","venue_website":null,"home_delivery_mov":"0.00","start_days":2,"end_days":2,"collection_time":70,"preparation_time":null,"free_delivery":"0.00","delivery_charge":"0.00","delivery":1,"collection":1,"is_booking_allow":1,"latitude":"52.5756465","longitude":"-2.138598","venue_category":["Grocery","Essential Items","Foods"],"delivery_rating":0,"distance":"3,727.26","opening_time":"2021-03-02 09:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"1.4","total_offers":0,"isWishlisted":false},{"id":92,"merchant_id":36,"venue_id":"2020112705294136","venue_name":"Lasan Restaurant","venue_images":"2508316064549120.jpeg","address_1":"Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":null,"post_code":"WV30DS","phone_number":"558785458","venue_email":"aryanm22897@gmail.com","venue_website":null,"home_delivery_mov":"0.00","start_days":null,"end_days":null,"collection_time":8,"preparation_time":null,"free_delivery":"0.00","delivery_charge":"0.00","delivery":1,"collection":0,"is_booking_allow":0,"latitude":null,"longitude":null,"venue_category":["Eat","Food","Drink"],"delivery_rating":0,"distance":null,"opening_time":"2021-03-02 08:00","closing_time":"2021-03-02 20:00","isClose":"0","stars":"0","total_offers":0,"isWishlisted":false},{"id":94,"merchant_id":38,"venue_id":"2020112707261538","venue_name":"Harborne Kitchen","venue_images":"3275516064618900.jpeg","address_1":"175-179 High St, Harborne, Birmingham, UK","address_2":null,"city_name":"Birmingham","country":"United Kingdom","post_code":"B17 9QE","phone_number":"121 439 9150","venue_email":"jerard73@lftjaguar.com","venue_website":null,"home_delivery_mov":"0.00","start_days":null,"end_days":null,"collection_time":0,"preparation_time":null,"free_delivery":"0.00","delivery_charge":"0.00","delivery":1,"collection":1,"is_booking_allow":0,"latitude":"52.45864599999999","longitude":"-1.9498564","venue_category":["Eat-In","Food","Drink"],"delivery_rating":0,"distance":"3,724.53","opening_time":"2021-03-02 2021-03-02 18:59:21.429 D/OkHttp:  10:00","closing_time":"2021-03-02 22:00","isClose":"0","stars":"0","total_offers":0,"isWishlisted":false},{"id":96,"merchant_id":40,"venue_id":"2020112708020440","venue_name":"Gaucho Birmingham","venue_images":"4738716064640960.jpeg","address_1":"55 Colmore Row, Colmore Row, Birmingham, UK","address_2":null,"city_name":"Birmingham","country":"United Kingdom","post_code":"B3 2AA","phone_number":"121 439 9236","venue_email":"jadeyn2@fxsuppose.com","venue_website":null,"home_delivery_mov":"0.00","start_days":null,"end_days":null,"collection_time":null,"preparation_time":null,"free_delivery":"0.00","delivery_charge":"0.00","delivery":0,"collection":0,"is_booking_allow":0,"latitude":"52.4815938","longitude":"-1.9000993","venue_category":["Food","Drink"],"delivery_rating":0,"distance":"3,721.80","opening_time":"2021-03-02 11:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"0","total_offers":0,"isWishlisted":false},{"id":101,"merchant_id":43,"venue_id":"2021011113403943","venue_name":"Purecraft Bar & Kitchen","venue_images":"9488816103722550.png","address_1":"17 Oaklands Road","address_2":null,"city_name":"Wolverhampton","country":"England","post_code":"WV3 0DS","phone_number":"919457033407","venue_email":"itzabella4@vsalmonusq.com","venue_website":null,"home_delivery_mov":"0.00","start_days":null,"end_days":null,"collection_time":null,"preparation_time":null,"free_delivery":"0.00","delivery_charge":"0.00","delivery":0,"collection":1,"is_booking_allow":0,"latitude":"52.57592","longitude":"-2.138813","venue_category":["Eat-In","Takeaway","Food"],"delivery_rating":0,"distance":"3,727.26","opening_time":"2021-03-02 09:00","closing_time":"2021-03-02 21:00","isClose":"0","stars":"0","total_offers":0,"isWishlisted":false}]
     * total_swoopeEatVenues : 8
     * time_gap : 300
     * max_person : 20
     * showTabs : [{"id":1,"tab_name":"top_banner","sort":1,"status":1},{"id":2,"tab_name":"resrvation_filter","sort":2,"status":1},{"id":3,"tab_name":"retail_venue","sort":3,"status":1},{"id":4,"tab_name":"eat_venue","sort":4,"status":1},{"id":5,"tab_name":"beauty_venue","sort":5,"status":1},{"id":6,"tab_name":"upcoming_venue","sort":6,"status":1},{"id":7,"tab_name":"deals_of_day","sort":7,"status":1},{"id":8,"tab_name":"top_category","sort":8,"status":1},{"id":9,"tab_name":"top_category_products","sort":9,"status":1},{"id":10,"tab_name":"subscription","sort":10,"status":1},{"id":11,"tab_name":"new_arrival","sort":11,"status":1}]
     * times : [{"time":"10:00:00"},{"time":"10:05:00"},{"time":"10:10:00"},{"time":"10:15:00"},{"time":"10:20:00"},{"time":"10:25:00"},{"time":"10:30:00"},{"time":"10:35:00"},{"time":"10:40:00"},{"time":"10:45:00"},{"time":"10:50:00"},{"time":"10:55:00"},{"time":"11:00:00"},{"time":"11:05:00"},{"time":"11:10:00"},{"time":"11:15:00"},{"time":"11:20:00"},{"time":"11:25:00"},{"time":"11:30:00"},{"time":"11:35:00"},{"time":"11:40:00"},{"time":"11:45:00"},{"time":"11:50:00"},{"time":"11:55:00"},{"time":"12:00:00"},{"time":"12:05:00"},{"time":"12:10:00"},{"time":"12:15:00"},{"time":"12:20:00"},{"time":"12:25:00"},{"time":"12:30:00"},{"time":"12:35:00"},{"time":"12:40:00"},{"time":"12:45:00"},{"time":"12:50:00"},{"time":"12:55:00"},{"time":"13:00:00"},{"time":"13:05:00"},{"time":"13:10:00"},{"time":"13:15:00"},{"time":"13:20:00"},{"time":"13:25:00"},{"time":"13:30:00"},{"time":"13:35:00"},{"time":"13:40:00"},{"time":"13:45:00"},{"time":"13:50:00"},{"time":"13:55:00"},{"time":"14:00:00"},{"time":"14:05:00"},{"time":"14:10:00"},{"time":"14:15:00"},{"time":"14:20:00"},{"time":"14:25:00"},{"time":"14:30:00"},{"time":"14:35:00"},{"time":"14:40:00"},{"time":"14:45:00"},{"time":"14:50:00"},{"time":"14:55:00"},{"time":"15:00:00"},{"time":"15:05:00"},{"time":"15:10:00"},{"time":"15:15:00"},{"time":"15:20:00"},{"time":"15:25:00"},{"time":"15:30:00"},{"time":"15:35:00"},{"time":"15:40:00"},{"time":"15:45:00"},{"time":"15:50:00"},{"time":"15:55:00"},{"time":"16:00:00"},{"time":"16:05:00"},{"time":"16:10:00"},{"time":"16:15:00"},{"time":"16:20:00"},{"time":"16:25:00"},{"time":"16:30:00"},{"time":"16:35:00"},{"time":"16:40:00"},{"time":"16:45:00"},{"time":"16:50:00"},{"time":"16:55:00"},{"time":"17:00:00"},{"time":"17:05:00"},{"time":"17:10:00"},{"time":"17:15:00"},{"time":"17:20:00"},{"time":"17:25:00"},{"time":"17:30:00"},{"time":"17:35:00"},{"time":"17:40:00"},{"time":"17:45:00"},{"time":"17:50:00"},{"time":"17:55:00"},{"time":"18:00:00"},{"time":"18:05:00"},{"time":"18:10:00"},{"time":"18:15:00"},{"time":"18:20:00"},{"time":"18:25:00"},{"time":"18:30:00"},{"time":"18:35:00"},{"time":"18:40:00"},{"time":"18:45:00"},{"time":"18:50:00"},{"time":"18:55:00"},{"time":"19:00:00"},{"time":"19:05:00"},{"time":"19:10:00"},{"time":"19:15:00"},{"time":"19:20:00"},{"time":"19:25:00"},{"time":"19:30:00"},{"time":"19:35:00"},{"time":"19:40:00"},{"time":"19:45:00"},{"time":"19:50:00"},{"time":"19:55:00"},{"time":"20:00:00"},{"time":"20:05:00"},{"time":"20:10:00"},{"time":"20:15:00"},{"time":"20:20:00"},{"time":"20:25:00"},{"time":"20:30:00"},{"time":"20:35:00"},{"time":"20:40:00"},{"time":"20:45:00"},{"time":"20:50:00"},{"time":"20:55:00"},{"time":"21:00:00"},{"time":"21:05:00"},{"time":"21:10:00"},{"time":"21:15:00"},{"time":"21:20:00"},{"time":"21:25:00"},{"time":"21:30:00"},{"time":"21:35:00"},{"time":"21:40:00"},{"time":"21:45:00"},{"time":"21:50:00"},{"time":"21:55:00"},{"time":"22:00:00"},{"time":"22:05:00"},{"time":"22:10:00"},{"time":"22:15:00"},{"time":"22:20:00"},{"time":"22:25:00"},{"time":"22:30:00"},{"time":"22:35:00"},{"time":"22:40:00"},{"time":"22:45:00"},{"time":"22:50:00"},{"time":"22:55:00"},{"time":"23:00:00"},{"time":"23:05:00"},{"time":"23:10:00"},{"time":"23:15:00"},{"time":"23:20:00"},{"time":"23:25:00"},{"time":"23:30:00"},{"time":"23:35:00"},{"time":"23:40:00"},{"time":"23:45:00"},{"time":"23:50:00"},{"time":"23:55:00"},{"time":"10:00:00"},{"time":"10:05:00"},{"time":"10:10:00"},{"time":"10:15:00"},{"time":"10:20:00"},{"time":"10:25:00"},{"time":"10:30:00"},{"time":"10:35:00"},{"time":"10:40:00"},{"time":"10:45:00"},{"time":"10:50:00"},{"time":"10:55:00"},{"time":"11:00:00"},{"time":"11:05:00"},{"time":"11:10:00"},{"time":"11:15:00"},{"time":"11:20:00"},{"time":"11:25:00"},{"time":"11:30:00"},{"time":"11:35:00"},{"time":"11:40:00"},{"time":"11:45:00"},{"time":"11:50:00"},{"time":"11:55:00"},{"time":"12:00:00"},{"time":"12:05:00"},{"time":"12:10:00"},{"time":"12:15:00"},{"time":"12:20:00"},{"time":"12:25:00"},{"time":"12:30:00"},{"time":"12:35:00"},{"time":"12:40:00"},{"time":"12:45:00"},{"time":"12:50:00"},{"time":"12:55:00"},{"time":"13:00:00"},{"time":"13:05:00"},{"time":"13:10:00"},{"time":"13:15:00"},{"time":"13:20:00"},{"time":"13:25:00"},{"time":"13:30:00"},{"time":"13:35:00"},{"time":"13:40:00"},{"time":"13:45:00"},{"time":"13:50:00"},{"time":"13:55:00"},{"time":"14:00:00"},{"time":"14:05:00"},{"time":"14:10:00"},{"time":"14:15:00"},{"time":"14:20:00"},{"time":"14:25:00"},{"time":"14:30:00"},{"time":"14:35:00"},{"time":"14:40:00"},{"time":"14:45:00"},{"time":"14:50:00"},{"time":"14:55:00"},{"time":"15:00:00"},{"time":"15:05:00"},{"time":"15:10:00"},{"time":"15:15:00"},{"time":"15:20:00"},{"time":"15:25:00"},{"time":"15:30:00"},{"time":"15:35:00"},{"time":"15:40:00"},{"time":"15:45:00"},{"time":"15:50:00"},{"time":"15:55:00"},{"time":"16:00:00"},{"time":"16:05:00"},{"time":"16:10:00"},{"time":"16:15:00"},{"time":"16:20:00"},{"time":"16:25:00"},{"time":"16:30:00"},{"time":"16:35:00"},{"time":"16:40:00"},{"time":"16:45:00"},{"time":"16:50:00"},{"time":"16:55:00"},{"time":"17:00:00"},{"time":"17:05:00"},{"time":"17:10:00"},{"time":"17:15:00"},{"time":"17:20:00"},{"time":"17:25:00"},{"time":"17:30:00"},{"time":"17:35:00"},{"time":"17:40:00"},{"time":"17:45:00"},{"time":"17:50:00"},{"time":"17:55:00"},{"time":"18:00:00"},{"time":"18:05:00"},{"time":"18:10:00"},{"time":"18:15:00"},{"time":"18:20:00"},{"time":"18:25:00"},{"time":"18:30:00"},{"time":"18:35:00"},{"time":"18:40:00"},{"time":"18:45:00"},{"time":"18:50:00"},{"time":"18:55:00"},{"time":"19:00:00"},{"time":"19:05:00"},{"time":"19:10:00"},{"time":"19:15:00"},{"time":"19:20:00"},{"time":"19:25:00"},{"time":"19:30:00"},{"time":"19:35:00"},{"time":"19:40:00"},{"time":"19:45:00"},{"time":"19:50:00"},{"time":"19:55:00"},{"time":"20:00:00"},{"time":"20:05:00"},{"time":"20:10:00"},{"time":"20:15:00"},{"time":"20:20:00"},{"time":"20:25:00"},{"time":"20:30:00"},{"time":"20:35:00"},{"time":"20:40:00"},{"time":"20:45:00"},{"time":"20:50:00"},{"time":"20:55:00"},{"time":"21:00:00"},{"time":"21:05:00"},{"time":"21:10:00"},{"time":"21:15:00"},{"time":"21:20:00"},{"time":"21:25:00"},{"time":"21:30:00"},{"time":"21:35:00"},{"time":"21:40:00"},{"time":"21:45:00"},{"time":"21:50:00"},{"time":"21:55:00"},{"time":"22:00:00"},{"time":"22:05:00"},{"time":"22:10:00"},{"time":"22:15:00"},{"time":"22:20:00"},{"time":"22:25:00"},{"time":"22:30:00"},{"time":"22:35:00"},{"time":"22:40:00"},{"time":"22:45:00"},{"time":"22:50:00"},{"time":"22:55:00"},{"time":"23:00:00"},{"time":"23:05:00"},{"time":"23:10:00"},{"time":"23:15:00"},{"time":"23:20:00"},{"time":"23:25:00"},{"time":"23:30:00"},{"time":"23:35:00"},{"time":"23:40:00"},{"time":"23:45:00"},{"time":"23:50:00"},{"time":"23:55:00"},{"time":"10:00:00"},{"time":"10:05:00"},{"time":"10:10:00"},{"time":"10:15:00"},{"time":"10:20:00"},{"time":"10:25:00"},{"time":"10:30:00"},{"time":"10:35:00"},{"time":"10:40:00"},{"time":"10:45:00"},{"time":"10:50:00"},{"time":"10:55:00"},{"time":"11:00:00"},{"time":"11:05:00"},{"time":"11:10:00"},{"time":"11:15:00"},{"time":"11:20:00"},{"time":"11:25:00"},{"time":"11:30:00"},{"time":"11:35:00"},{"time":"11:40:00"},{"time":"11:45:00"},{"time":"11:50:00"},{"time":"11:55:00"},{"time":"12:00:00"},{"time":"12:05:00"},{"time":"12:10:00"},{"time":"12:15:00"},{"time":"12:20:00"},{"time":"12:25:00"},{"time":"12:30:00"},{"time":"12:35:00"},{"time":"12:40:00"},{"time":"12:45:00"},{"time":"12:50:00"},{"time":"12:55:00"},{"time":"13:00:00"},{"time":"13:05:00"},{"time":"13:10:00"},{"time":"13:15:00"},{"time":"13:20:00"},{"time":"13:25:00"},{"time":"13:30:00"},{"time":"13:35:00"},{"time":"13:40:00"},{"time":"13:45:00"},{"time":"13:50:00"},{"time":"13:55:00"},{"time":"14:00:00"},{"time":"14:05:00"},{"time":"14:10:00"},{"time":"14:15:00"},{"time":"14:20:00"},{"time":"14:25:00"},{"time":"14:30:00"},{"time":"14:35:00"},{"time":"14:40:00"},{"time":"14:45:00"},{"time":"14:50:00"},{"time":"14:55:00"},{"time":"15:00:00"},{"time":"15:05:00"},{"time":"15:10:00"},{"time":"15:15:00"},{"time":"15:20:00"},{"time":"15:25:00"},{"time":"15:30:00"},{"time":"15:35:00"},{"time":"15:40:00"},{"time":"15:45:00"},{"time":"15:50:00"},{"time":"15:55:00"},{"time":"16:00:00"},{"time":"16:05:00"},{"time":"16:10:00"},{"time":"16:15:00"},{"time":"16:20:00"},{"time":"16:25:00"},{"time":"16:30:00"},{"time":"16:35:00"},{"time":"16:40:00"},{"time":"16:45:00"},{"time":"16:50:00"},{"time":"16:55:00"},{"time":"17:00:00"},{"time":"17:05:00"},{"time":"17:10:00"},{"time":"17:15:00"},{"time":"17:20:00"},{"time":"17:25:00"},{"time":"17:30:00"},{"time":"17:35:00"},{"time":"17:40:00"},{"time":"17:45:00"},{"time":"17:50:00"},{"time":"17:55:00"},{"time":"18:00:00"},{"time":"18:05:00"},{"time":"18:10:00"},{"time":"18:15:00"},{"time":"18:20:00"},{"time":"18:25:00"},{"time":"18:30:00"},{"time":"18:35:00"},{"time":"18:40:00"},{"time":"18:45:00"},{"time":"18:50:00"},{"time":"18:55:00"},{"time":"19:00:00"},{"time":"19:05:00"},{"time":"19:10:00"},{"time":"19:15:00"},{"time":"19:20:00"},{"time":"19:25:00"},{"time":"19:30:00"},{"time":"19:35:00"},{"time":"19:40:00"},{"time":"19:45:00"},{"time":"19:50:00"},{"time":"19:55:00"},{"time":"20:00:00"},{"time":"20:05:00"},{"time":"20:10:00"},{"time":"20:15:00"},{"time":"20:20:00"},{"time":"20:25:00"},{"time":"20:30:00"},{"time":"20:35:00"},{"time":"20:40:00"},{"time":"20:45:00"},{"time":"20:50:00"},{"time":"20:55:00"},{"time":"21:00:00"},{"time":"21:05:00"},{"time":"21:10:00"},{"time":"21:15:00"},{"time":"21:20:00"},{"time":"21:25:00"},{"time":"21:30:00"},{"time":"21:35:00"},{"time":"21:40:00"},{"time":"21:45:00"},{"time":"21:50:00"},{"time":"21:55:00"},{"time":"22:00:00"},{"time":"22:05:00"},{"time":"22:10:00"},{"time":"22:15:00"},{"time":"22:20:00"},{"time":"22:25:00"},{"time":"22:30:00"},{"time":"22:35:00"},{"time":"22:40:00"},{"time":"22:45:00"},{"time":"22:50:00"},{"time":"22:55:00"},{"time":"23:00:00"},{"time":"23:05:00"},{"time":"23:10:00"},{"time":"23:15:00"},{"time":"23:20:00"},{"time":"23:25:00"},{"time":"23:30:00"},{"time":"23:35:00"},{"time":"23:40:00"},{"time":"23:45:00"},{"time":"23:50:00"},{"time":"23:55:00"},{"time":"10:00:00"},{"time":"10:05:00"},{"time":"10:10:00"},{"time":"10:15:00"},{"time":"10:20:00"},{"time":"10:25:00"},{"time":"10:30:00"},{"time":"10:35:00"},{"time":"10:40:00"},{"time":"10:45:00"},{"time":"10:50:00"},{"time":"10:55:00"},{"time":"11:00:00"},{"time":"11:05:00"},{"time":"11:10:00"},{"time":"11:15:00"},{"time":"11:20:00"},{"time":"11:25:00"},{"time":"11:30:00"},{"time":"11:35:00"},{"time":"11:40:00"},{"time":"11:45:00"},{"time":"11:50:00"},{"time":"11:55:00"},{"time":"12:00:00"},{"time":"12:05:00"},{"time":"12:10:00"},{"time":"12:15:00"},{"time":"12:20:00"},{"time":"12:25:00"},{"time":"12:30:00"},{"time":"12:35:00"},{"time":"12:40:00"},{"time":"12:45:00"},{"time":"12:50:00"},{"time":"12:55:00"},{"time":"13:00:00"},{"time":"13:05:00"},{"time":"13:10:00"},{"time":"13:15:00"},{"time":"13:20:00"},{"time":"13:25:00"},{"time":"13:30:00"},{"time":"13:35:00"},{"time":"13:40:00"},{"time":"13:45:00"},{"time":"13:50:00"},{"time":"13:55:00"},{"time":"14:00:00"},{"time":"14:05:00"},{"time":"14:10:00"},{"time":"14:15:00"},{"time":"14:20:00"},{"time":"14:25:00"},{"time":"14:30:00"},{"time":"14:35:00"},{"time":"14:40:00"},{"time":"14:45:00"},{"time":"14:50:00"},{"time":"14:55:00"},{"time":"15:00:00"},{"time":"15:05:00"},{"time":"15:10:00"},{"time":"15:15:00"},{"time":"15:20:00"},{"time":"15:25:00"},{"time":"15:30:00"},{"time":"15:35:00"},{"time":"15:40:00"},{"time":"15:45:00"},{"time":"15:50:00"},{"time":"15:55:00"},{"time":"16:00:00"},{"time":"16:05:00"},{"time":"16:10:00"},{"time":"16:15:00"},{"time":"16:20:00"},{"time":"16:25:00"},{"time":"16:30:00"},{"time":"16:35:00"},{"time":"16:40:00"},{"time":"16:45:00"},{"time":"16:50:00"},{"time":"16:55:00"},{"time":"17:00:00"},{"time":"17:05:00"},{"time":"17:10:00"},{"time":"17:15:00"},{"time":"17:20:00"},{"time":"17:25:00"},{"time":"17:30:00"},{"time":"17:35:00"},{"time":"17:40:00"},{"time":"17:45:00"},{"time":"17:50:00"},{"time":"17:55:00"},{"time":"18:00:00"},{"time":"18:05:00"},{"time":"18:10:00"},{"time":"18:15:00"},{"time":"18:20:00"},{"time":"18:25:00"},{"time":"18:30:00"},{"time":"18:35:00"},{"time":"18:40:00"},{"time":"18:45:00"},{"time":"18:50:00"},{"time":"18:55:00"},{"time":"19:00:00"},{"time":"19:05:00"},{"time":"19:10:00"},{"time":"19:15:00"},{"time":"19:20:00"},{"time":"19:25:00"},{"time":"19:30:00"},{"time":"19:35:00"},{"time":"19:40:00"},{"time":"19:45:00"},{"time":"19:50:00"},{"time":"19:55:00"},{"time":"20:00:00"},{"time":"20:05:00"},{"time":"20:10:00"},{"time":"20:15:00"},{"time":"20:20:00"},{"time":"20:25:00"},{"time":"20:30:00"},{"time":"20:35:00"},{"time":"20:40:00"},{"time":"20:45:00"},{"time":"20:50:00"},{"time":"20:55:00"},{"time":"21:00:00"},{"time":"21:05:00"},{"time":"21:10:00"},{"time":"21:15:00"},{"time":"21:20:00"},{"time":"21:25:00"},{"time":"21:30:00"},{"time":"21:35:00"},{"time":"21:40:00"},{"time":"21:45:00"},{"time":"21:50:00"},{"time":"21:55:00"},{"time":"22:00:00"},{"time":"22:05:00"},{"time":"22:10:00"},{"time":"22:15:00"},{"time":"22:20:00"},{"time":"22:25:00"},{"time":"22:30:00"},{"time":"22:35:00"},{"time":"22:40:00"},{"time":"22:45:00"},{"time":"22:50:00"},{"time":"22:55:00"},{"time":"23:00:00"},{"time":"23:05:00"},{"time":"23:10:00"},{"time":"23:15:00"},{"time":"23:20:00"},{"time":"23:25:00"},{"time":"23:30:00"},{"time":"23:35:00"},{"time":"23:40:00"},{"time":"23:45:00"},{"time":"23:50:00"},{"time":"23:55:00"},{"time":"10:00:00"},{"time":"10:05:00"},{"time":"10:10:00"},{"time":"10:15:00"},{"time":"10:20:00"},{"time":"10:25:00"},{"time":"10:30:00"},{"time":"10:35:00"},{"time":"10:40:00"},{"time":"10:45:00"},{"time":"10:50:00"},{"time":"10:55:00"},{"time":"11:00:00"},{"time":"11:05:00"},{"time":"11:10:00"},{"time":"11:15:00"},{"time":"11:20:00"},{"time":"11:25:00"},{"time":"11:30:00"},{"time":"11:35:00"},{"time":"11:40:00"},{"time":"11:45:00"},{"time":"11:50:00"},{"time":"11:55:00"},{"time":"12:00:00"},{"time":"12:05:00"},{"time":"12:10:00"},{"time":"12:15:00"},{"time":"12:20:00"},{"time":"12:25:00"},{"time":"12:30:00"},{"time":"12:35:00"},{"time":"12:40:00"},{"time":"12:45:00"},{"time":"12:50:00"},{"time":"12:55:00"},{"time":"13:00:00"},{"time":"13:05:00"},{"time":"13:10:00"},{"time":"13:15:00"},{"time":"13:20:00"},{"time":"13:25:00"},{"time":"13:30:00"},{"time":"13:35:00"},{"time":"13:40:00"},{"time":"13:45:00"},{"time":"13:50:00"},{"time":"13:55:00"},{"time":"14:00:00"},{"time":"14:05:00"},{"time":"14:10:00"},{"time":"14:15:00"},{"time":"14:20:00"},{"time":"14:25:00"},{"time":"14:30:00"},{"time":"14:35:00"},{"time":"14:40:00"},{"time":"14:45:00"},{"time":"14:50:00"},{"time":"14:55:00"},{"time":"15:00:00"},{"time":"15:05:00"},{"time":"15:10:00"},{"time":"15:15:00"},{"time":"15:20:00"},{"time":"15:25:00"},{"time":"15:30:00"},{"time":"15:35:00"},{"time":"15:40:00"},{"time":"15:45:00"},{"time":"15:50:00"},{"time":"15:55:00"},{"time":"16:00:00"},{"time":"16:05:00"},{"time":"16:10:00"},{"time":"16:15:00"},{"time":"16:20:00"},{"time":"16:25:00"},{"time":"16:30:00"},{"time":"16:35:00"},{"time":"16:40:00"},{"time":"16:45:00"},{"time":"16:50:00"},{"time":"16:55:00"},{"time":"17:00:00"},{"time":"17:05:00"},{"time":"17:10:00"},{"time":"17:15:00"},{"time":"17:20:00"},{"time":"17:25:00"},{"time":"17:30:00"},{"time":"17:35:00"},{"time":"17:40:00"},{"time":"17:45:00"},{"time":"17:50:00"},{"time":"17:55:00"},{"time":"18:00:00"},{"time":"18:05:00"},{"time":"18:10:00"},{"time":"18:15:00"},{"time":"18:20:00"},{"time":"18:25:00"},{"time":"18:30:00"},{"time":"18:35:00"},{"time":"18:40:00"},{"time":"18:45:00"},{"time":"18:50:00"},{"time":"18:55:00"},{"time":"19:00:00"},{"time":"19:05:00"},{"time":"19:10:00"},{"time":"19:15:00"},{"time":"19:20:00"},{"time":"19:25:00"},{"time":"19:30:00"},{"time":"19:35:00"},{"time":"19:40:00"},{"time":"19:45:00"},{"time":"19:50:00"},{"time":"19:55:00"},{"time":"20:00:00"},{"time":"20:05:00"},{"time":"20:10:00"},{"time":"20:15:00"},{"time":"20:20:00"},{"time":"20:25:00"},{"time":"20:30:00"},{"time":"20:35:00"},{"time":"20:40:00"},{"time":"20:45:00"},{"time":"20:50:00"},{"time":"20:55:00"},{"time":"21:00:00"},{"time":"21:05:00"},{"time":"21:10:00"},{"time":"21:15:00"},{"time":"21:20:00"},{"time":"21:25:00"},{"time":"21:30:00"},{"time":"21:35:00"},{"time":"21:40:00"},{"time":"21:45:00"},{"time":"21:50:00"},{"time":"21:55:00"},{"time":"22:00:00"},{"time":"22:05:00"},{"time":"22:10:00"},{"time":"22:15:00"},{"time":"22:20:00"},{"time":"22:25:00"},{"time":"22:30:00"},{"time":"22:35:00"},{"time":"22:40:00"},{"time":"22:45:00"},{"time":"22:50:00"},{"time":"22:55:00"},{"time":"23:00:00"},{"time":"23:05:00"},{"time":"23:10:00"},{"time":"23:15:00"},{"time":"23:20:00"},{"time":"23:25:00"},{"time":"23:30:00"},{"time":"23:35:00"},{"time":"23:40:00"},{"time":"23:45:00"},{"time":"23:50:00"},{"time":"23:55:00"}]
     */

    private boolean status;
    private int total_liked;
    private int total_quantity;
    private int total_bulk_quantity;
    private int total_carts;
    private int total_retailVenues;
    private int total_swoopeEatVenues;
    private int time_gap;
    private String max_person;
    private List<BannersBean> banners;
    private List<RetailVanuesBean> retailVanues;
    private List<SwoopeEatVanuesBean> swoopeEatVanues;
    private List<ShowTabsBean> showTabs;
    private List<TimesBean> times;
    private List<LocalshopBean> Localshop;

    public List<LocalshopBean> getLocalshop() {
        return Localshop;
    }

    public void setLocalshop(List<LocalshopBean> localshop) {
        Localshop = localshop;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal_liked() {
        return total_liked;
    }

    public void setTotal_liked(int total_liked) {
        this.total_liked = total_liked;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getTotal_bulk_quantity() {
        return total_bulk_quantity;
    }

    public void setTotal_bulk_quantity(int total_bulk_quantity) {
        this.total_bulk_quantity = total_bulk_quantity;
    }

    public int getTotal_carts() {
        return total_carts;
    }

    public void setTotal_carts(int total_carts) {
        this.total_carts = total_carts;
    }

    public int getTotal_retailVenues() {
        return total_retailVenues;
    }

    public void setTotal_retailVenues(int total_retailVenues) {
        this.total_retailVenues = total_retailVenues;
    }

    public int getTotal_swoopeEatVenues() {
        return total_swoopeEatVenues;
    }

    public void setTotal_swoopeEatVenues(int total_swoopeEatVenues) {
        this.total_swoopeEatVenues = total_swoopeEatVenues;
    }

    public int getTime_gap() {
        return time_gap;
    }

    public void setTime_gap(int time_gap) {
        this.time_gap = time_gap;
    }

    public String getMax_person() {
        return max_person;
    }

    public void setMax_person(String max_person) {
        this.max_person = max_person;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<RetailVanuesBean> getRetailVanues() {
        return retailVanues;
    }

    public void setRetailVanues(List<RetailVanuesBean> retailVanues) {
        this.retailVanues = retailVanues;
    }

    public List<SwoopeEatVanuesBean> getSwoopeEatVanues() {
        return swoopeEatVanues;
    }

    public void setSwoopeEatVanues(List<SwoopeEatVanuesBean> swoopeEatVanues) {
        this.swoopeEatVanues = swoopeEatVanues;
    }

    public List<ShowTabsBean> getShowTabs() {
        return showTabs;
    }

    public void setShowTabs(List<ShowTabsBean> showTabs) {
        this.showTabs = showTabs;
    }

    public List<TimesBean> getTimes() {
        return times;
    }

    public void setTimes(List<TimesBean> times) {
        this.times = times;
    }

    public static class BannersBean implements Serializable {
        /**
         * id : 9
         * merchant_id : 5
         * venue_id : 202002121635135
         * app_banner_for : 1
         * banner_type : 0
         * user_app : 1
         * app_banner : uploaded/banner/appbanner/1410115887483120.png
         * banner_placement_main : 1
         * category : 0
         * start_time : 00:00:00
         * end_time : 23:00:00
         * product_categories_id : 3
         * appProductUrl :
         * gender_for_show : Both
         * appCatUrl : 1
         * venue_name : Lillywhites
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private int app_banner_for;
        private int banner_type;
        private int user_app;
        private String app_banner;
        private int banner_placement_main;
        private int category;
        private String start_time;
        private String end_time;
        private String product_categories_id;
        private String appProductUrl;
        private String gender_for_show;
        private int appCatUrl;
        private String venue_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getApp_banner_for() {
            return app_banner_for;
        }

        public void setApp_banner_for(int app_banner_for) {
            this.app_banner_for = app_banner_for;
        }

        public int getBanner_type() {
            return banner_type;
        }

        public void setBanner_type(int banner_type) {
            this.banner_type = banner_type;
        }

        public int getUser_app() {
            return user_app;
        }

        public void setUser_app(int user_app) {
            this.user_app = user_app;
        }

        public String getApp_banner() {
            return app_banner;
        }

        public void setApp_banner(String app_banner) {
            this.app_banner = app_banner;
        }

        public int getBanner_placement_main() {
            return banner_placement_main;
        }

        public void setBanner_placement_main(int banner_placement_main) {
            this.banner_placement_main = banner_placement_main;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getProduct_categories_id() {
            return product_categories_id;
        }

        public void setProduct_categories_id(String product_categories_id) {
            this.product_categories_id = product_categories_id;
        }

        public String getAppProductUrl() {
            return appProductUrl;
        }

        public void setAppProductUrl(String appProductUrl) {
            this.appProductUrl = appProductUrl;
        }

        public String getGender_for_show() {
            return gender_for_show;
        }

        public void setGender_for_show(String gender_for_show) {
            this.gender_for_show = gender_for_show;
        }

        public int getAppCatUrl() {
            return appCatUrl;
        }

        public void setAppCatUrl(int appCatUrl) {
            this.appCatUrl = appCatUrl;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }
    }

    public static class RetailVanuesBean implements Serializable {
        /**
         * id : 47
         * merchant_id : 16
         * venue_id : 2020061707363216
         * venue_name : World of Cloth
         * venue_images : 20200918135831-20200617083735-New-folder-1024x576.jpg
         * address_1 : 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom
         * address_2 : 7th flor
         * city_name : Edinburgh
         * country : United Kingdom
         * post_code : EH127XD
         * phone_number : 919717802669
         * venue_email : willusssmith2019@gmail.com
         * venue_website : null
         * home_delivery_mov : 10.00
         * start_days : 1
         * end_days : 90
         * collection_time : 60
         * preparation_time : null
         * free_delivery : 20.00
         * delivery_charge : 2.00
         * delivery : 1
         * collection : 0
         * latitude : 55.9428422
         * longitude : -3.2831273
         * delivery_rating : 0
         * distance : 3,648.32
         * product_count : 303
         * opening_time : 2021-03-02 08:00
         * closing_time : 2021-03-02 22:00
         * isClose : 0
         * stars : 4.3
         * total_offers : 0
         * isWishlisted : false
         * city_na 2021-03-02 18:59:21.429 D/OkHttp: me : London
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private String venue_name;
        private String venue_images;
        private String address_1;
        private String address_2;
        private String city_name;
        private String country;
        private String post_code;
        private String phone_number;
        private String venue_email;
        private Object venue_website;
        private String home_delivery_mov;
        private int start_days;
        private int end_days;
        private int collection_time;
        private Object preparation_time;
        private String free_delivery;
        private String delivery_charge;
        private int delivery;
        private int collection;
        private String latitude;
        private String longitude;
        private int delivery_rating;
        private String distance;
        private int product_count;
        private String opening_time;
        private String closing_time;
        private String isClose;
        private String stars;
        private int total_offers;
        private boolean isWishlisted;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getVenue_email() {
            return venue_email;
        }

        public void setVenue_email(String venue_email) {
            this.venue_email = venue_email;
        }

        public Object getVenue_website() {
            return venue_website;
        }

        public void setVenue_website(Object venue_website) {
            this.venue_website = venue_website;
        }

        public String getHome_delivery_mov() {
            return home_delivery_mov;
        }

        public void setHome_delivery_mov(String home_delivery_mov) {
            this.home_delivery_mov = home_delivery_mov;
        }

        public int getStart_days() {
            return start_days;
        }

        public void setStart_days(int start_days) {
            this.start_days = start_days;
        }

        public int getEnd_days() {
            return end_days;
        }

        public void setEnd_days(int end_days) {
            this.end_days = end_days;
        }

        public int getCollection_time() {
            return collection_time;
        }

        public void setCollection_time(int collection_time) {
            this.collection_time = collection_time;
        }

        public Object getPreparation_time() {
            return preparation_time;
        }

        public void setPreparation_time(Object preparation_time) {
            this.preparation_time = preparation_time;
        }

        public String getFree_delivery() {
            return free_delivery;
        }

        public void setFree_delivery(String free_delivery) {
            this.free_delivery = free_delivery;
        }

        public String getDelivery_charge() {
            return delivery_charge;
        }

        public void setDelivery_charge(String delivery_charge) {
            this.delivery_charge = delivery_charge;
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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getDelivery_rating() {
            return delivery_rating;
        }

        public void setDelivery_rating(int delivery_rating) {
            this.delivery_rating = delivery_rating;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getProduct_count() {
            return product_count;
        }

        public void setProduct_count(int product_count) {
            this.product_count = product_count;
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

        public String getIsClose() {
            return isClose;
        }

        public void setIsClose(String isClose) {
            this.isClose = isClose;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getTotal_offers() {
            return total_offers;
        }

        public void setTotal_offers(int total_offers) {
            this.total_offers = total_offers;
        }

        public boolean isIsWishlisted() {
            return isWishlisted;
        }

        public void setIsWishlisted(boolean isWishlisted) {
            this.isWishlisted = isWishlisted;
        }

        public boolean isWishlisted() {
            return isWishlisted;
        }

        public void setWishlisted(boolean wishlisted) {
            isWishlisted = wishlisted;
        }
    }

    public static class SwoopeEatVanuesBean implements Serializable {
        /**
         * id : 31
         * merchant_id : 16
         * venue_id : 2020042308172516
         * venue_name : Raman Restaurant
         * venue_images : 20200520154700-what-to-eat-in-morocco.jpg
         * address_1 : 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom
         * address_2 : th floor 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom
         * city_name : castophine
         * country : United Kingdom
         * post_code : eh127xd
         * phone_number : 1478523697
         * venue_email : ramanhospitality@mailinator.com
         * venue_website : null
         * home_delivery_mov : 20.00
         * start_days : 60
         * end_days : 90
         * collection_time : 70
         * preparation_time : 30
         * free_delivery : 30.00
         * delivery_charge : 2.00
         * delivery : 1
         * collection : 1
         * is_booking_allow : 1
         * latitude : 55.9428422
         * longitude : -3.2831273
         * venue_category : ["healthy"," spicy"]
         * delivery_rating : 5
         * distance : 3,648.32
         * opening_time : 2021-03-02 08:00
         * closing_time : 2021-03-02 22:00
         * isClose : 0
         * stars : 3.1
         * total_offers : 0
         * isWishlisted : true
         * ve 2021-03-02 18:59:21.429 D/OkHttp: nue_name : Majestic Stourbridge
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private String venue_name;
        private String venue_images;
        private String address_1;
        private String address_2;
        private String city_name;
        private String country;
        private String post_code;
        private String phone_number;
        private String venue_email;
        private Object venue_website;
        private String home_delivery_mov;
        private int start_days;
        private int end_days;
        private int collection_time;
        private int preparation_time;
        private String free_delivery;
        private String delivery_charge;
        private int delivery;
        private int collection;
        private int is_booking_allow;
        private String latitude;
        private String longitude;
        private int delivery_rating;
        private String distance;
        private String opening_time;
        private String closing_time;
        private String isClose;
        private String stars;
        private int total_offers;
        private boolean isWishlisted;
        private List<String> venue_category;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getVenue_email() {
            return venue_email;
        }

        public void setVenue_email(String venue_email) {
            this.venue_email = venue_email;
        }

        public Object getVenue_website() {
            return venue_website;
        }

        public void setVenue_website(Object venue_website) {
            this.venue_website = venue_website;
        }

        public String getHome_delivery_mov() {
            return home_delivery_mov;
        }

        public void setHome_delivery_mov(String home_delivery_mov) {
            this.home_delivery_mov = home_delivery_mov;
        }

        public int getStart_days() {
            return start_days;
        }

        public void setStart_days(int start_days) {
            this.start_days = start_days;
        }

        public int getEnd_days() {
            return end_days;
        }

        public void setEnd_days(int end_days) {
            this.end_days = end_days;
        }

        public int getCollection_time() {
            return collection_time;
        }

        public void setCollection_time(int collection_time) {
            this.collection_time = collection_time;
        }

        public int getPreparation_time() {
            return preparation_time;
        }

        public void setPreparation_time(int preparation_time) {
            this.preparation_time = preparation_time;
        }

        public String getFree_delivery() {
            return free_delivery;
        }

        public void setFree_delivery(String free_delivery) {
            this.free_delivery = free_delivery;
        }

        public String getDelivery_charge() {
            return delivery_charge;
        }

        public void setDelivery_charge(String delivery_charge) {
            this.delivery_charge = delivery_charge;
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

        public int getIs_booking_allow() {
            return is_booking_allow;
        }

        public void setIs_booking_allow(int is_booking_allow) {
            this.is_booking_allow = is_booking_allow;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getDelivery_rating() {
            return delivery_rating;
        }

        public void setDelivery_rating(int delivery_rating) {
            this.delivery_rating = delivery_rating;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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

        public String getIsClose() {
            return isClose;
        }

        public void setIsClose(String isClose) {
            this.isClose = isClose;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getTotal_offers() {
            return total_offers;
        }

        public void setTotal_offers(int total_offers) {
            this.total_offers = total_offers;
        }

        public boolean isIsWishlisted() {
            return isWishlisted;
        }

        public void setIsWishlisted(boolean isWishlisted) {
            this.isWishlisted = isWishlisted;
        }

        public List<String> getVenue_category() {
            return venue_category;
        }

        public void setVenue_category(List<String> venue_category) {
            this.venue_category = venue_category;
        }
    }

    public static class ShowTabsBean implements Serializable {
        /**
         * id : 1
         * tab_name : top_banner
         * sort : 1
         * status : 1
         */

        private int id;
        private String tab_name;
        private int sort;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTab_name() {
            return tab_name;
        }

        public void setTab_name(String tab_name) {
            this.tab_name = tab_name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class TimesBean implements Serializable {
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

    public static class LocalshopBean implements Serializable {
        /**
         * image : /uploaded/local_shop/20210216133838-clothing.jpeg
         * name : Clothing
         * slug : clothing
         * shop_type : 1
         */

        private String image;
        private String name;
        private String slug;
        private String shop_type;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }
    }

}
