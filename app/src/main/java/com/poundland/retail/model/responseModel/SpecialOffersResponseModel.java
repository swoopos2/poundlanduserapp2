package com.poundland.retail.model.responseModel;

import java.util.List;

public class SpecialOffersResponseModel {

    /**
     * status : true
     * productOffers : {"current_page":1,"data":[{"is_on_sale":0,"avl_quantity":1000,"buy_price":"10.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-04","end_date":"2020-02-12","offer_time_start":"00:00:00","offer_time_end":"23:50:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":207,"modifier_images":"special_offer/20191101114846220200204110354.png","product_id":135,"product_images":"special_offer/20191101114846220200204110354.png","product_name":"Notification Offer","new_price":"10.00","selling_price":"20.00","offer_id":null,"special_offer_id":1,"offer_title":"Notification Offer","offer_type":"","product_description":"OFFER DETAILS","stars":"0","review_count":0,"image":"special_offer/20191101114846220200204110354.png","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":1000,"buy_price":"20.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-04","end_date":"2020-02-11","offer_time_start":"00:00:00","offer_time_end":"23:55:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":303,"modifier_images":"special_offer/20191101114846220200204111632.png","product_id":202,"product_images":"special_offer/20191101114846220200204111632.png","product_name":"LILLY WHITE OFFER","new_price":"10.00","selling_price":"40.00","offer_id":null,"special_offer_id":2,"offer_title":"LILLY WHITE OFFER","offer_type":"","product_description":"OFFER","stars":"0","review_count":0,"image":"special_offer/20191101114846220200204111632.png","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":1000,"buy_price":"20.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-04","end_date":"2020-02-18","offer_time_start":"00:00:00","offer_time_end":"23:50:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":303,"modifier_images":"app-assets/images/logo-1.png","product_id":202,"product_images":"app-assets/images/logo-1.png","product_name":"NEW OFFER","new_price":"10.00","selling_price":"40.00","offer_id":null,"special_offer_id":3,"offer_title":"NEW OFFER","offer_type":"","product_description":"101","stars":"0","review_count":0,"image":"app-assets/images/logo-1.png","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":1,"avl_quantity":10,"buy_price":"10.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-07","end_date":"2020-02-14","offer_time_start":"09:00:00","offer_time_end":"14:00:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":221,"modifier_images":"special_offer/20191101114846220200207064557.jpg","product_id":139,"product_images":"special_offer/20191101114846220200207064557.jpg","product_name":"Offer07","new_price":"10.00","selling_price":"15.00","offer_id":null,"special_offer_id":8,"offer_title":"Offer07","offer_type":"","product_description":"Best Offer of the week","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207064557.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":10,"buy_price":"4.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-07","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"20:00:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":360,"modifier_images":"special_offer/20191101114846220200207073652.jpg","product_id":229,"product_images":"special_offer/20191101114846220200207073652.jpg","product_name":"OfferWeek","new_price":"7.00","selling_price":"8.00","offer_id":null,"special_offer_id":9,"offer_title":"OfferWeek","offer_type":"","product_description":"NewOfferOfTheWeek","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207073652.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":10,"buy_price":"4.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-08","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"17:15:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":360,"modifier_images":"special_offer/20191101114846220200207100027.jpg","product_id":229,"product_images":"special_offer/20191101114846220200207100027.jpg","product_name":"StartsFrom08","new_price":"5.00","selling_price":"8.00","offer_id":null,"special_offer_id":10,"offer_title":"StartsFrom08","offer_type":"","product_description":"Weekend Offer starts from tomorrow","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207100027.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":15,"buy_price":"10.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-08","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"21:00:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":219,"modifier_images":"special_offer/20191101114846220200207103314.jpg","product_id":139,"product_images":"special_offer/20191101114846220200207103314.jpg","product_name":"WeekOffer","new_price":"8.00","selling_price":"15.00","offer_id":null,"special_offer_id":11,"offer_title":"WeekOffer","offer_type":"","product_description":"Weekend best offer starts from tomorrow","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207103314.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":10,"buy_price":"1.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-08","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"23:55:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":370,"modifier_images":"special_offer/20191101114846220200207110217.jpg","product_id":235,"product_images":"special_offer/20191101114846220200207110217.jpg","product_name":"NewWeekendOffer","new_price":"1.00","selling_price":"2.00","offer_id":null,"special_offer_id":12,"offer_title":"NewWeekendOffer","offer_type":"","product_description":"Fun weekend offer starts from tomorrow","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207110217.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":100,"buy_price":"4.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-09","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"23:55:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":359,"modifier_images":"special_offer/20191101114846220200207124204.png","product_id":228,"product_images":"special_offer/20191101114846220200207124204.png","product_name":"V-day Offer","new_price":"5.00","selling_price":"7.00","offer_id":null,"special_offer_id":13,"offer_title":"V-day Offer","offer_type":"","product_description":"Many people celebrate their love for their partner by sending cards or letters, giving gifts or flowers and arranging meals in restaurants or romantic nights in hotels. People who would like to have a romantic relationship with somebody may use the occasion to make this known, often anonymously. Valentine's cards are often decorated with images of hearts, red roses or Cupid. Common Valentine's Day gifts are flowers chocolates, candy, lingerie and champagne or sparkling wine. However, some people use the occasion to present lavish gifts, such as jewelry. Many restaurants and hotels have special offers at this time. These can include romantic meals or weekend breaks.","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207124204.png","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"id":15,"offer_title":"NewOffer","days_available":"Mon,Tue,Wed,Thu,Fri,Sat,Sun","offer_type":"SpecialOffer","user_type":1,"merchant_id":2,"venue_id":"201911011148462","notification_range":2,"beacon_id":0,"offer_detail":"Best Offer of the day","product_id":157,"category_id":1,"modifier_id":249,"terms_condition":"TNC","start_date":"2020-02-09","end_date":"2020-02-10","start_time":"12:00:00","end_time":"17:15:00","regular_price":"6.00","offer_price":"3.00","gender":"female","age_brackets":"0-17","total_qty":10,"quantity_offer":10,"image":"20191101114846220200209075804.jpg","status":2,"created_at":"2020-02-09 07:58:04","updated_at":"2020-02-09 07:58:30","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"28.607851","longitude":"-2.1385980000000018","delivery_distance":0,"distance":"4,820.45","buy_price":"2.00"}],"first_page_url":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getSpecialOffer?page=1","from":1,"last_page":2,"last_page_url":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getSpecialOffer?page=2","next_page_url":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getSpecialOffer?page=2","path":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getSpecialOffer","per_page":10,"prev_page_url":null,"to":10,"total":16}
     */

    private boolean status;
    private ProductOffersBean productOffers;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ProductOffersBean getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(ProductOffersBean productOffers) {
        this.productOffers = productOffers;
    }

    public static class ProductOffersBean {
        /**
         * current_page : 1
         * data : [{"is_on_sale":0,"avl_quantity":1000,"buy_price":"10.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-04","end_date":"2020-02-12","offer_time_start":"00:00:00","offer_time_end":"23:50:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":207,"modifier_images":"special_offer/20191101114846220200204110354.png","product_id":135,"product_images":"special_offer/20191101114846220200204110354.png","product_name":"Notification Offer","new_price":"10.00","selling_price":"20.00","offer_id":null,"special_offer_id":1,"offer_title":"Notification Offer","offer_type":"","product_description":"OFFER DETAILS","stars":"0","review_count":0,"image":"special_offer/20191101114846220200204110354.png","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":1000,"buy_price":"20.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-04","end_date":"2020-02-11","offer_time_start":"00:00:00","offer_time_end":"23:55:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":303,"modifier_images":"special_offer/20191101114846220200204111632.png","product_id":202,"product_images":"special_offer/20191101114846220200204111632.png","product_name":"LILLY WHITE OFFER","new_price":"10.00","selling_price":"40.00","offer_id":null,"special_offer_id":2,"offer_title":"LILLY WHITE OFFER","offer_type":"","product_description":"OFFER","stars":"0","review_count":0,"image":"special_offer/20191101114846220200204111632.png","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":1000,"buy_price":"20.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-04","end_date":"2020-02-18","offer_time_start":"00:00:00","offer_time_end":"23:50:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":303,"modifier_images":"app-assets/images/logo-1.png","product_id":202,"product_images":"app-assets/images/logo-1.png","product_name":"NEW OFFER","new_price":"10.00","selling_price":"40.00","offer_id":null,"special_offer_id":3,"offer_title":"NEW OFFER","offer_type":"","product_description":"101","stars":"0","review_count":0,"image":"app-assets/images/logo-1.png","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":1,"avl_quantity":10,"buy_price":"10.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-07","end_date":"2020-02-14","offer_time_start":"09:00:00","offer_time_end":"14:00:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":221,"modifier_images":"special_offer/20191101114846220200207064557.jpg","product_id":139,"product_images":"special_offer/20191101114846220200207064557.jpg","product_name":"Offer07","new_price":"10.00","selling_price":"15.00","offer_id":null,"special_offer_id":8,"offer_title":"Offer07","offer_type":"","product_description":"Best Offer of the week","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207064557.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":10,"buy_price":"4.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-07","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"20:00:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":360,"modifier_images":"special_offer/20191101114846220200207073652.jpg","product_id":229,"product_images":"special_offer/20191101114846220200207073652.jpg","product_name":"OfferWeek","new_price":"7.00","selling_price":"8.00","offer_id":null,"special_offer_id":9,"offer_title":"OfferWeek","offer_type":"","product_description":"NewOfferOfTheWeek","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207073652.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":10,"buy_price":"4.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-08","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"17:15:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":360,"modifier_images":"special_offer/20191101114846220200207100027.jpg","product_id":229,"product_images":"special_offer/20191101114846220200207100027.jpg","product_name":"StartsFrom08","new_price":"5.00","selling_price":"8.00","offer_id":null,"special_offer_id":10,"offer_title":"StartsFrom08","offer_type":"","product_description":"Weekend Offer starts from tomorrow","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207100027.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":15,"buy_price":"10.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-08","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"21:00:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":219,"modifier_images":"special_offer/20191101114846220200207103314.jpg","product_id":139,"product_images":"special_offer/20191101114846220200207103314.jpg","product_name":"WeekOffer","new_price":"8.00","selling_price":"15.00","offer_id":null,"special_offer_id":11,"offer_title":"WeekOffer","offer_type":"","product_description":"Weekend best offer starts from tomorrow","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207103314.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":10,"buy_price":"1.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-08","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"23:55:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":370,"modifier_images":"special_offer/20191101114846220200207110217.jpg","product_id":235,"product_images":"special_offer/20191101114846220200207110217.jpg","product_name":"NewWeekendOffer","new_price":"1.00","selling_price":"2.00","offer_id":null,"special_offer_id":12,"offer_title":"NewWeekendOffer","offer_type":"","product_description":"Fun weekend offer starts from tomorrow","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207110217.jpg","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"is_on_sale":0,"avl_quantity":100,"buy_price":"4.00","delivery_distance":0,"disc_amt":"0.00","disc_per":"0.00","discount_amount":"0.00","discount_type":"","distance":"4,820.45","start_date":"2020-02-09","end_date":"2020-02-14","offer_time_start":"00:00:00","offer_time_end":"23:55:00","isWishlisted":false,"venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":359,"modifier_images":"special_offer/20191101114846220200207124204.png","product_id":228,"product_images":"special_offer/20191101114846220200207124204.png","product_name":"V-day Offer","new_price":"5.00","selling_price":"7.00","offer_id":null,"special_offer_id":13,"offer_title":"V-day Offer","offer_type":"","product_description":"Many people celebrate their love for their partner by sending cards or letters, giving gifts or flowers and arranging meals in restaurants or romantic nights in hotels. People who would like to have a romantic relationship with somebody may use the occasion to make this known, often anonymously. Valentine's cards are often decorated with images of hearts, red roses or Cupid. Common Valentine's Day gifts are flowers chocolates, candy, lingerie and champagne or sparkling wine. However, some people use the occasion to present lavish gifts, such as jewelry. Many restaurants and hotels have special offers at this time. These can include romantic meals or weekend breaks.","stars":"0","review_count":0,"image":"special_offer/20191101114846220200207124204.png","token":"eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz","login_device_type":"ANDROID"},{"id":15,"offer_title":"NewOffer","days_available":"Mon,Tue,Wed,Thu,Fri,Sat,Sun","offer_type":"SpecialOffer","user_type":1,"merchant_id":2,"venue_id":"201911011148462","notification_range":2,"beacon_id":0,"offer_detail":"Best Offer of the day","product_id":157,"category_id":1,"modifier_id":249,"terms_condition":"TNC","start_date":"2020-02-09","end_date":"2020-02-10","start_time":"12:00:00","end_time":"17:15:00","regular_price":"6.00","offer_price":"3.00","gender":"female","age_brackets":"0-17","total_qty":10,"quantity_offer":10,"image":"20191101114846220200209075804.jpg","status":2,"created_at":"2020-02-09 07:58:04","updated_at":"2020-02-09 07:58:30","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"28.607851","longitude":"-2.1385980000000018","delivery_distance":0,"distance":"4,820.45","buy_price":"2.00"}]
         * first_page_url : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getSpecialOffer?page=1
         * from : 1
         * last_page : 2
         * last_page_url : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getSpecialOffer?page=2
         * next_page_url : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getSpecialOffer?page=2
         * path : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getSpecialOffer
         * per_page : 10
         * prev_page_url : null
         * to : 10
         * total : 16
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
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

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
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
             * is_on_sale : 0
             * avl_quantity : 1000
             * buy_price : 10.00
             * delivery_distance : 0
             * disc_amt : 0.00
             * disc_per : 0.00
             * discount_amount : 0.00
             * discount_type :
             * distance : 4,820.45
             * start_date : 2020-02-04
             * end_date : 2020-02-12
             * offer_time_start : 00:00:00
             * offer_time_end : 23:50:00
             * isWishlisted : false
             * venue_id : 201911011148462
             * merchant_id : 2
             * venue_name : LillyWhites
             * modifier_id : 207
             * modifier_images : special_offer/20191101114846220200204110354.png
             * product_id : 135
             * product_images : special_offer/20191101114846220200204110354.png
             * product_name : Notification Offer
             * new_price : 10.00
             * selling_price : 20.00
             * offer_id : null
             * special_offer_id : 1
             * offer_title : Notification Offer
             * offer_type :
             * product_description : OFFER DETAILS
             * stars : 0
             * review_count : 0
             * image : special_offer/20191101114846220200204110354.png
             * token : eULS62ZrulU:APA91bHTG1f-6MycH5X3PGkf-EmH6OdwuiMBcTvWny9ELt7Bb8YCy24-azUYvUI0qWb279CEUPSYK5vXGfCeR6QKbCNG_Y9AZj10tQkGjcErLv1T4wJR4w8atiqd5FBMpW-Vthfh-0sz
             * login_device_type : ANDROID
             * id : 15
             * days_available : Mon,Tue,Wed,Thu,Fri,Sat,Sun
             * user_type : 1
             * notification_range : 2
             * beacon_id : 0
             * offer_detail : Best Offer of the day
             * category_id : 1
             * terms_condition : TNC
             * start_time : 12:00:00
             * end_time : 17:15:00
             * regular_price : 6.00
             * offer_price : 3.00
             * gender : female
             * age_brackets : 0-17
             * total_qty : 10
             * quantity_offer : 10
             * status : 2
             * created_at : 2020-02-09 07:58:04
             * updated_at : 2020-02-09 07:58:30
             * venue_images : 1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,
             * address_1 : Oaklands Road, Wolverhampton WV3 0DS, England, UK
             * address_2 : null
             * city_name : Wolverhampton
             * country : United Kingdom
             * post_code : WV3 0DS
             * delivery : 1
             * collection : 1
             * latitude : 28.607851
             * longitude : -2.1385980000000018
             */

            private int is_on_sale;
            private int avl_quantity;
            private String buy_price;
            private long delivery_distance;
            private String disc_amt;
            private String disc_per;
            private String discount_amount;
            private String discount_type;
            private String distance;
            private String start_date;
            private String end_date;
            private String offer_time_start;
            private String offer_time_end;
            private boolean isWishlisted;
            private String venue_id;
            private int merchant_id;
            private String venue_name;
            private int modifier_id;
            private String modifier_images;
            private int product_id;
            private String product_images;
            private String product_name;
            private String new_price;
            private String selling_price;
            private int offer_id;
            private int special_offer_id;
            private String offer_title;
            private String offer_type;
            private String product_description;
            private String stars;
            private int review_count;
            private String image;
            private String token;
            private String login_device_type;
            private int id;
            private String days_available;
            private int user_type;
            private int notification_range;
            private int beacon_id;
            private String offer_detail;
            private int category_id;
            private String terms_condition;
            private String start_time;
            private String end_time;
            private String regular_price;
            private String offer_price;
            private String gender;
            private String age_brackets;
            private int total_qty;
            private int quantity_offer;
            private int status;
            private String created_at;
            private String updated_at;
            private String venue_images;
            private String address_1;
            private Object address_2;
            private String city_name;
            private String country;
            private String post_code;
            private int delivery;
            private int collection;
            private String latitude;
            private String longitude;
            private int sameDayShipping;
            private int quantity_per_case;
            private String bulk_selling_price;

            public int getQuantity_per_case() {
                return quantity_per_case;
            }

            public void setQuantity_per_case(int quantity_per_case) {
                this.quantity_per_case = quantity_per_case;
            }

            public String getBulk_selling_price() {
                return bulk_selling_price;
            }

            public void setBulk_selling_price(String bulk_selling_price) {
                this.bulk_selling_price = bulk_selling_price;
            }

            public int getSameDayShipping() {
                return sameDayShipping;
            }

            public void setSameDayShipping(int sameDayShipping) {
                this.sameDayShipping = sameDayShipping;
            }

            public int getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(int is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public int getAvl_quantity() {
                return avl_quantity;
            }

            public void setAvl_quantity(int avl_quantity) {
                this.avl_quantity = avl_quantity;
            }

            public String getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(String buy_price) {
                this.buy_price = buy_price;
            }

            public long getDelivery_distance() {
                return delivery_distance;
            }

            public void setDelivery_distance(long delivery_distance) {
                this.delivery_distance = delivery_distance;
            }

            public String getDisc_amt() {
                return disc_amt;
            }

            public void setDisc_amt(String disc_amt) {
                this.disc_amt = disc_amt;
            }

            public String getDisc_per() {
                return disc_per;
            }

            public void setDisc_per(String disc_per) {
                this.disc_per = disc_per;
            }

            public String getDiscount_amount() {
                return discount_amount;
            }

            public void setDiscount_amount(String discount_amount) {
                this.discount_amount = discount_amount;
            }

            public String getDiscount_type() {
                return discount_type;
            }

            public void setDiscount_type(String discount_type) {
                this.discount_type = discount_type;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getOffer_time_start() {
                return offer_time_start;
            }

            public void setOffer_time_start(String offer_time_start) {
                this.offer_time_start = offer_time_start;
            }

            public String getOffer_time_end() {
                return offer_time_end;
            }

            public void setOffer_time_end(String offer_time_end) {
                this.offer_time_end = offer_time_end;
            }

            public boolean isIsWishlisted() {
                return isWishlisted;
            }

            public void setIsWishlisted(boolean isWishlisted) {
                this.isWishlisted = isWishlisted;
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

            public String getVenue_name() {
                return venue_name;
            }

            public void setVenue_name(String venue_name) {
                this.venue_name = venue_name;
            }

            public int getModifier_id() {
                return modifier_id;
            }

            public void setModifier_id(int modifier_id) {
                this.modifier_id = modifier_id;
            }

            public String getModifier_images() {
                return modifier_images;
            }

            public void setModifier_images(String modifier_images) {
                this.modifier_images = modifier_images;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getProduct_images() {
                return product_images;
            }

            public void setProduct_images(String product_images) {
                this.product_images = product_images;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getNew_price() {
                return new_price;
            }

            public void setNew_price(String new_price) {
                this.new_price = new_price;
            }

            public String getSelling_price() {
                return selling_price;
            }

            public void setSelling_price(String selling_price) {
                this.selling_price = selling_price;
            }

            public int getOffer_id() {
                return offer_id;
            }

            public void setOffer_id(int offer_id) {
                this.offer_id = offer_id;
            }

            public int getSpecial_offer_id() {
                return special_offer_id;
            }

            public void setSpecial_offer_id(int special_offer_id) {
                this.special_offer_id = special_offer_id;
            }

            public String getOffer_title() {
                return offer_title;
            }

            public void setOffer_title(String offer_title) {
                this.offer_title = offer_title;
            }

            public String getOffer_type() {
                return offer_type;
            }

            public void setOffer_type(String offer_type) {
                this.offer_type = offer_type;
            }

            public String getProduct_description() {
                return product_description;
            }

            public void setProduct_description(String product_description) {
                this.product_description = product_description;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getReview_count() {
                return review_count;
            }

            public void setReview_count(int review_count) {
                this.review_count = review_count;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getLogin_device_type() {
                return login_device_type;
            }

            public void setLogin_device_type(String login_device_type) {
                this.login_device_type = login_device_type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDays_available() {
                return days_available;
            }

            public void setDays_available(String days_available) {
                this.days_available = days_available;
            }

            public int getUser_type() {
                return user_type;
            }

            public void setUser_type(int user_type) {
                this.user_type = user_type;
            }

            public int getNotification_range() {
                return notification_range;
            }

            public void setNotification_range(int notification_range) {
                this.notification_range = notification_range;
            }

            public int getBeacon_id() {
                return beacon_id;
            }

            public void setBeacon_id(int beacon_id) {
                this.beacon_id = beacon_id;
            }

            public String getOffer_detail() {
                return offer_detail;
            }

            public void setOffer_detail(String offer_detail) {
                this.offer_detail = offer_detail;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getTerms_condition() {
                return terms_condition;
            }

            public void setTerms_condition(String terms_condition) {
                this.terms_condition = terms_condition;
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

            public String getRegular_price() {
                return regular_price;
            }

            public void setRegular_price(String regular_price) {
                this.regular_price = regular_price;
            }

            public String getOffer_price() {
                return offer_price;
            }

            public void setOffer_price(String offer_price) {
                this.offer_price = offer_price;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getAge_brackets() {
                return age_brackets;
            }

            public void setAge_brackets(String age_brackets) {
                this.age_brackets = age_brackets;
            }

            public int getTotal_qty() {
                return total_qty;
            }

            public void setTotal_qty(int total_qty) {
                this.total_qty = total_qty;
            }

            public int getQuantity_offer() {
                return quantity_offer;
            }

            public void setQuantity_offer(int quantity_offer) {
                this.quantity_offer = quantity_offer;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public Object getAddress_2() {
                return address_2;
            }

            public void setAddress_2(Object address_2) {
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
        }
    }
}
