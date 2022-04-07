package com.poundland.retail.model.responseModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class HospitalityVenueProductResponseModel {


    /**
     * status : true
     * message : List Of Venue
     * category : [{"id":49,"cat_name":"Burger","parent_cat_id":0,"swoope_cat_id":null,"color":"64b9e6","menu_level":1,"venue_id":"2020040808042515","short_num":4,"product":0,"sub_category":[]},{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"color":"7164e6","menu_level":1,"venue_id":"2020040808042515","short_num":5,"product":10,"sub_category":[{"id":64,"cat_name":"Old Beer","parent_cat_id":63,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"63","taggs":"OldBeer","product_feature":null,"status":1,"is_integrated":0,"image":"Old Beer09042020075106.png","color":"7e1718","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:51:06","updated_at":"2020-04-27 12:33:45","short_num":0,"is_misc":0,"product_count":3},{"id":98,"cat_name":"Vodka","parent_cat_id":63,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"63","taggs":"Vodka","product_feature":null,"status":1,"is_integrated":0,"image":"","color":"646ee6","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-04-27 09:58:12","updated_at":"2020-04-27 12:33:45","short_num":0,"is_misc":0,"product_count":3},{"id":100,"cat_name":"Rim","parent_cat_id":63,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"63","taggs":"rum","product_feature":null,"status":1,"is_integrated":0,"image":"","color":"648ae6","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-04-27 10:32:31","updated_at":"2020-04-27 12:33:45","short_num":0,"is_misc":0,"product_count":0},{"id":150,"cat_name":"Vodka","parent_cat_id":63,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Vodka","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-15 14:28:20","updated_at":"2020-05-15 14:28:20","short_num":null,"is_misc":0,"product_count":0},{"id":154,"cat_name":"Vodka","parent_cat_id":63,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Vodka","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","short_num":null,"is_misc":0,"product_count":1},{"id":155,"cat_name":"Vodka","parent_cat_id":63,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Vodka","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","short_num":null,"is_misc":0,"product_count":1},{"id":156,"cat_name":"Vodka","parent_cat_id":63,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Vodka","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","short_num":null,"is_misc":0,"product_count":1},{"id":157,"cat_name":"Vodka","parent_cat_id":63,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Vodka","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","short_num":null,"is_misc":0,"product_count":1}]},{"id":61,"cat_name":"Champagne","parent_cat_id":0,"swoope_cat_id":null,"color":"e66465","menu_level":1,"venue_id":"2020040808042515","short_num":6,"product":2,"sub_category":[{"id":62,"cat_name":"Champagne 12 years","parent_cat_id":61,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"61","taggs":"12","product_feature":null,"status":1,"is_integrated":0,"image":"Champagne 12 years09042020074245.png","color":"6472e6","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:40:14","updated_at":"2020-04-09 07:42:45","short_num":0,"is_misc":0,"product_count":2}]},{"id":69,"cat_name":"Whisky","parent_cat_id":0,"swoope_cat_id":null,"color":"64c9e6","menu_level":1,"venue_id":"2020040808042515","short_num":6,"product":1,"sub_category":[{"id":70,"cat_name":"Old Whisky","parent_cat_id":69,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"69","taggs":"OldW","product_feature":null,"status":1,"is_integrated":0,"image":"Old Whisky09042020134224.png","color":"9c64e6","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-04-09 13:42:24","updated_at":"2020-04-09 13:42:24","short_num":0,"is_misc":0,"product_count":1}]},{"id":99,"cat_name":"Rum","parent_cat_id":0,"swoope_cat_id":null,"color":"648ae6","menu_level":1,"venue_id":"2020040808042515","short_num":6,"product":0,"sub_category":[]},{"id":71,"cat_name":"Scotch","parent_cat_id":0,"swoope_cat_id":null,"color":"9464e6","menu_level":1,"venue_id":"2020040808042515","short_num":7,"product":0,"sub_category":[{"id":72,"cat_name":"Scotch Old","parent_cat_id":71,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"71","taggs":"Oldy","product_feature":null,"status":1,"is_integrated":0,"image":"Scotch Old09042020134555.png","color":"e664e1","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-04-09 13:45:55","updated_at":"2020-04-09 13:45:55","short_num":0,"is_misc":0,"product_count":0}]},{"id":53,"cat_name":"Wine","parent_cat_id":0,"swoope_cat_id":null,"color":"64e0e6","menu_level":1,"venue_id":"2020040808042515","short_num":8,"product":11,"sub_category":[{"id":54,"cat_name":"Red wine","parent_cat_id":53,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"53","taggs":"redy","product_feature":null,"status":1,"is_integrated":0,"image":"Red wine08042020094432.png","color":"e6648f","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-04-08 09:44:32","updated_at":"2020-04-10 09:55:57","short_num":0,"is_misc":0,"product_count":7},{"id":184,"cat_name":"Red wine","parent_cat_id":53,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Red wine","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-20 13:21:07","updated_at":"2020-05-20 13:21:07","short_num":null,"is_misc":0,"product_count":1},{"id":185,"cat_name":"Red wine","parent_cat_id":53,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Red wine","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-20 13:21:07","updated_at":"2020-05-20 13:21:07","short_num":null,"is_misc":0,"product_count":1},{"id":186,"cat_name":"White Wine","parent_cat_id":53,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"White Wine","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-20 13:21:07","updated_at":"2020-05-20 13:21:07","short_num":null,"is_misc":0,"product_count":1},{"id":187,"cat_name":"Red wine","parent_cat_id":53,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Red wine","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-05-20 13:21:07","updated_at":"2020-05-20 13:21:07","short_num":null,"is_misc":0,"product_count":1}]},{"id":247,"cat_name":"Food","parent_cat_id":0,"swoope_cat_id":null,"color":"b864e6","menu_level":1,"venue_id":"2020040808042515","short_num":9,"product":3,"sub_category":[{"id":248,"cat_name":"Breakfast","parent_cat_id":247,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"247","taggs":"BF","product_feature":null,"status":1,"is_integrated":0,"image":"","color":"64e6d4","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-06-12 10:27:11","updated_at":"2020-06-12 10:27:11","short_num":0,"is_misc":0,"product_count":2},{"id":249,"cat_name":"Lunch","parent_cat_id":247,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"247","taggs":"LUN","product_feature":null,"status":1,"is_integrated":0,"image":"","color":"6476e6","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-06-12 10:27:30","updated_at":"2020-06-12 10:27:30","short_num":0,"is_misc":0,"product_count":1},{"id":250,"cat_name":"Dinner","parent_cat_id":247,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"247","taggs":"Dine","product_feature":null,"status":1,"is_integrated":0,"image":"","color":"6476e6","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-06-12 10:27:46","updated_at":"2020-06-12 10:27:46","short_num":0,"is_misc":0,"product_count":0}]},{"id":530,"cat_name":"Mains","parent_cat_id":0,"swoope_cat_id":null,"color":"1d01cf","menu_level":1,"venue_id":"2020040808042515","short_num":10,"product":3,"sub_category":[{"id":531,"cat_name":"Breakfasts","parent_cat_id":530,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":null,"taggs":"Breakfasts","product_feature":null,"status":1,"is_integrated":0,"image":null,"color":"1d01cf","menu_level":2,"order_by":1,"taxable":1,"created_at":"2020-11-27 18:10:13","updated_at":"2020-11-27 18:10:13","short_num":null,"is_misc":0,"product_count":3}]}]
     * products : {"current_page":1,"data":[{"id":807,"unique_code":"20200408112425152020040808042515","prod_guid":"80472887-93e8-4a25-96c1-c5dc28d8d5ac","merchant_id":15,"venue_id":"2020040808042515","product_name":"Rioja Reserva 'Selección Especial' Viña Ardanza 2010 La Rioja Alta","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">The Viña Ardanza 'Selección Especial' Reserva is only made in the very best vintages. This is only the fourth in history and the first in 19 years. A heady blend of Tempranillo and Garnacha, from 30-year-old vines, and aged for three years in American oak barrels, it's one of the finest expressions of Rioja Reserva you're likely to taste. No wonder its scooped 96 points from James Suckling and 95 points from Robert Parker's Wine Advocate.<\/span><br><\/p>","total_quantity":0,"measurement_unit_id":null,"category_id":"64","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"wine","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":10,"final_buy_price":5,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/9354415887454770.png,,,","modifier_name":"Rioja Reserva 'Selección Especial' Viña Ardanza 2010 La Rioja Alta","modifier_value":null,"modifier_list":[{"id":918,"product_id":807,"venueId":null,"modifier_name":"750ML","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"10.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:31:29","updated_at":"2020-06-18 08:21:41","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":926,"unique_code":"20200515145811152020040808042515","prod_guid":"20200515145811152020040808042515","merchant_id":15,"venue_id":"2020040808042515","product_name":"Absolut vanilla flavoured vodka 70cl","product_description":"Absolut Vanillia is rich, robust and complex with the distinct taste of vanilla, notes of butterscotch and hints of dark chocolate. Made exclusively from natural ingredients, it doesn't contain any added sugar.","total_quantity":1000,"measurement_unit_id":0,"category_id":"156","group_id":0,"modifier_id":"0","related_product_ids":"","brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"","tax_type":null,"delivery_type":null,"service_charges":"0.00","product_tags":"Absolut vanilla flavoured vodka 70cl","product_availability":null,"shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":21,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":"","meta_name":"","meta_keyword":null,"images":"uploaded/products/2957215895512690.png,,,","modifier_name":null,"modifier_value":null,"modifier_list":[{"id":1100,"product_id":926,"venueId":null,"modifier_name":"S","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"21.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":1107,"product_id":926,"venueId":null,"modifier_name":"750ml","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"21.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 15:22:31","updated_at":"2020-05-15 15:22:31","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":0,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":927,"unique_code":"20200515145811152020040808042515","prod_guid":"20200515145811152020040808042515","merchant_id":15,"venue_id":"2020040808042515","product_name":"Chase rhubarb vodka 70cl","product_description":"At Chase they make everything from scratch. They grow the potatoes or the vodka on their working farm (they also make Tyrrells crisps) and even handcraft the beautiful bottles. Fabulously creamy and smooth, serve straight from the fridge for best effects.","total_quantity":1000,"measurement_unit_id":0,"category_id":"157","group_id":0,"modifier_id":"0","related_product_ids":"","brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"","tax_type":null,"delivery_type":null,"service_charges":"0.00","product_tags":"Chase rhubarb vodka 70cl","product_availability":null,"shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":19,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":"","meta_name":"","meta_keyword":null,"images":"uploaded/products/8665115895512780.png,,,","modifier_name":null,"modifier_value":null,"modifier_list":[{"id":1101,"product_id":927,"venueId":null,"modifier_name":"S","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"19.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":1108,"product_id":927,"venueId":null,"modifier_name":"750ml","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"19.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 15:22:31","updated_at":"2020-05-15 15:22:31","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":0,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":858,"unique_code":"20200427095933152020040808042515","prod_guid":"900d7f5e-7614-44c6-be54-21018fbd625e","merchant_id":15,"venue_id":"2020040808042515","product_name":"Absolut Vodka 70cl","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Absolut Vodka is one of the most famous vodkas in the world. The main ingredients are water and winter wheat grown in the fields of Åhus, Sweden. The water comes from a deep well in Åhus where it's protected from impurities. With its refined nature and no added sugar, Absolut vodka blends perfectly with other aromas, enhancing the taste of your drink. Pour over ice, add soda and garnish with lime.<\/span><br><\/p>","total_quantity":100,"measurement_unit_id":null,"category_id":"98","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"Absolute,Vodka","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":20,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/2232615887457440.png,uploaded/products/2546415877062631.jpeg,,","modifier_name":"Absolut Vodka 70cl","modifier_value":null,"modifier_list":[{"id":1006,"product_id":858,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"20.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-27 09:59:33","updated_at":"2020-04-27 09:59:33","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":10,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":1066,"unique_code":"20200612111007152020040808042515","prod_guid":"dd2a6cf7-8e6d-4d48-bc3a-f39e27499013","merchant_id":15,"venue_id":"2020040808042515","product_name":"Asahi Superdry Beer","product_description":"<p><br data-mce-bogus=\"1\"><\/p>","total_quantity":100,"measurement_unit_id":null,"category_id":"64","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":null,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"11","service_charges":"0.00","product_tags":"Asahi,Superdry,Beer","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":2.99,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/8664815919566070.jpeg","modifier_name":"Asahi Superdry Beer","modifier_value":null,"modifier_list":[{"id":1410,"product_id":1066,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"2.99","rrp":"0.00","barcode":null,"quantity":1,"quantity_per_case":1,"moq":1,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-06-12 11:10:07","updated_at":"2020-06-12 11:10:07","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":0,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":1,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":863,"unique_code":"20200427100506152020040808042515","prod_guid":"ce59b572-9a5e-4250-8f74-a1956b923bd3","merchant_id":15,"venue_id":"2020040808042515","product_name":"Absolut Raspberry Flavoured Vodka 70cl","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Absolut Raspberri Vodka is rich and intense with the fresh and fruity taste of ripened raspberries harvested in the Swedish hillsides. Absolut Raspberri is made exclusively from natural ingredients, and unlike some other flavoured vodkas, it doesn't contain any added sugar making it perfect for mixing. Serving suggestion: Use Absolut Raspberri to make the ultimate Absolut Raspberri Collins.<\/span><br><\/p>","total_quantity":100,"measurement_unit_id":null,"category_id":"98","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"Spirits,Fortified","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":22,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/6371815887457650.png,uploaded/products/2546415877062631.jpeg,,","modifier_name":"Absolut Raspberry Flavoured Vodka 70cl","modifier_value":null,"modifier_list":[{"id":1011,"product_id":863,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"22.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-27 10:05:06","updated_at":"2020-04-27 10:05:06","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":10,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":913,"unique_code":"20200515075848152020040808042515","prod_guid":"cf2d2c8b-23d0-433b-845c-d1d096f2cd2d","merchant_id":15,"venue_id":"2020040808042515","product_name":"Stowford Press Cider 0.5% 12x 330ml Bottles","product_description":"<p>Westons Cider has been producing traditional English cider in the Herefordshire village of Much Marcle since 1880. It's run by the fourth generation of the Westons family and is one of the largest independent cider makers in the UK today. It continues to use traditional methods in the cider making process, passed down through the generations, such as maturing the cider for long periods in oak vats for a great depth of flavour. However, this traditon doesn't mean it's a stick-in-the-mud. This guilt-free cider has all the flavour of your favourite ciders just at 0.5% ABV.<\/p>","total_quantity":50000,"measurement_unit_id":null,"category_id":"64","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"4","tax_type":1,"delivery_type":"00","service_charges":"0.00","product_tags":"Stowford,Press,Cider,0.5%,12x,330ml,Bottles","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":10,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/5560215895259280.png","modifier_name":"Stowford Press Cider 0.5% 12x 330ml Bottles","modifier_value":null,"modifier_list":[{"id":1081,"product_id":913,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"10.00","rrp":"0.00","barcode":null,"quantity":1,"quantity_per_case":1,"moq":1,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 07:58:48","updated_at":"2020-05-15 07:58:48","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":1,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":914,"unique_code":"20200515080100152020040808042515","prod_guid":"dc0e5a6e-eeb8-46f8-b81c-9abf386d3510","merchant_id":15,"venue_id":"2020040808042515","product_name":"Ghost Vodka Gold Vodka 70cl","product_description":"<p>Ghost is an ultra-premium vodka like no other. It is distilled at a haunted salt depot in Cambridge using sugar beet. the result is a smooth vodka that works beautifully when mixed with citrus or sweeter flavours. Alternatively, give it 20 minutes in the freezer and enjoy how 'scarily' smooth it really is.<\/p>","total_quantity":50000,"measurement_unit_id":null,"category_id":"98","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":null,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"3","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"Ghost,Vodka,Gold,70cl","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":30,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/8906315895260600.png","modifier_name":"Ghost Vodka Gold Vodka 70cl","modifier_value":null,"modifier_list":[{"id":1082,"product_id":914,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"30.00","rrp":"0.00","barcode":null,"quantity":1,"quantity_per_case":1,"moq":1,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 08:01:00","updated_at":"2020-05-15 08:01:00","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":1,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":924,"unique_code":"20200515145811152020040808042515","prod_guid":"20200515145811152020040808042515","merchant_id":15,"venue_id":"2020040808042515","product_name":"Crystal head vodka","product_description":"Yes, Dan Aykroyd does make a vodka. It's made from the highest quality peaches and cream corn, distilled four times into a neutral grain spirit and blended with pristine water from Newfoundland. The liquid is then filtered seven times, of which three are through layers of semi precious crystals known as Herkimer diamonds. The result? One of the purest vodkas going.","total_quantity":1000,"measurement_unit_id":0,"category_id":"154","group_id":0,"modifier_id":"0","related_product_ids":"","brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"","tax_type":null,"delivery_type":null,"service_charges":"0.00","product_tags":"Crystal head vodka","product_availability":null,"shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":20,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":"","meta_name":"","meta_keyword":null,"images":"uploaded/products/8524515895512490.jpeg,,,","modifier_name":null,"modifier_value":null,"modifier_list":[{"id":1098,"product_id":924,"venueId":null,"modifier_name":"S","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"20.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":1105,"product_id":924,"venueId":null,"modifier_name":"750ml","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"20.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 15:22:31","updated_at":"2020-05-15 15:22:31","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":0,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":925,"unique_code":"20200515145811152020040808042515","prod_guid":"20200515145811152020040808042515","merchant_id":15,"venue_id":"2020040808042515","product_name":"Black cow strawberry flavoured vodka 70cl","product_description":"By using strawberries to flavour the vodka naturally, with no added sugar or sweeteners the fresh fruit maceration creates a beautiful strawberry blush vodka the taste of which is unparalleled amongst competitor products with similar flavour expressions. The resulting vodka has an unmistakable strawberry taste with delicate, honeyed floral notes. Delicious served neat over ice or with a soda splash","total_quantity":1000,"measurement_unit_id":0,"category_id":"155","group_id":0,"modifier_id":"0","related_product_ids":"","brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"","tax_type":null,"delivery_type":null,"service_charges":"0.00","product_tags":"Black cow strawberry flavoured vodka 70cl","product_availability":null,"shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":25,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":"","meta_name":"","meta_keyword":null,"images":"uploaded/products/1564815895512610.png,,,","modifier_name":null,"modifier_value":null,"modifier_list":[{"id":1099,"product_id":925,"venueId":null,"modifier_name":"S","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"25.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":1106,"product_id":925,"venueId":null,"modifier_name":"750ml","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"25.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 15:22:31","updated_at":"2020-05-15 15:22:31","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":0,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":805,"unique_code":"20200408100515152020040808042515","prod_guid":"55d5b80f-3ac7-4e93-9701-889476abc1b5","merchant_id":15,"venue_id":"2020040808042515","product_name":"The Guv'nor","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">The Guv'nor is made by our favourite winemakers. We told them to craft the ideal wine they'd drink at home and to forget any rules to achieve it. The result is a deep, multi-regional Tempranillo that's chock-full of bramble, blackcurrant and vanilla.<\/span><br><\/p>","total_quantity":0,"measurement_unit_id":null,"category_id":"62","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"porta","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":7,"final_buy_price":4,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/7420415887454250.jpeg,,,","modifier_name":"The Guv'nor","modifier_value":null,"modifier_list":[{"id":917,"product_id":805,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"7.00","rrp":"0.00","barcode":null,"quantity":99,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:30:44","updated_at":"2020-06-18 09:34:21","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:39:31","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":1,"product_type":2,"product_moq":0,"short_num":6,"parent_cate_id":61,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Champagne","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"champ","product_feature":null,"image":"Champagne09042020074223.png","color":"e66465","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":61,"cat_name":"Champagne","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"champ","product_feature":null,"status":1,"is_integrated":0,"image":"Champagne09042020074223.png","color":"e66465","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:39:31","updated_at":"2020-04-28 06:55:54","short_num":6,"is_misc":0},"product_addon":[]},{"id":811,"unique_code":"20200409081426152020040808042515","prod_guid":"1522d195-eadb-4287-b2ca-e1180f3051e4","merchant_id":15,"venue_id":"2020040808042515","product_name":"Bouvet Ladubay Saumur, France","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Bouvet is made with the same 'traditional method' as Champagne. But it's crafted from Chenin Blanc grapes. This pumps up the honeysuckle-and-citrus-filled body of the juice. If you're looking for a pocket-friendly Champagne double, it's here. The Telegraph's Victoria Moore called it \"excellent value, proper wine\".<\/span><br><\/p>","total_quantity":0,"measurement_unit_id":null,"category_id":"62","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"cham,Old","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":12,"final_buy_price":7,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/3506015887455110.png,,,","modifier_name":"Bouvet Ladubay Saumur, France","modifier_value":null,"modifier_list":[{"id":919,"product_id":811,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"12.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:31:47","updated_at":"2020-04-25 14:22:39","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:39:31","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":0,"short_num":6,"parent_cate_id":61,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Champagne","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"champ","product_feature":null,"image":"Champagne09042020074223.png","color":"e66465","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":61,"cat_name":"Champagne","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"champ","product_feature":null,"status":1,"is_integrated":0,"image":"Champagne09042020074223.png","color":"e66465","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:39:31","updated_at":"2020-04-28 06:55:54","short_num":6,"is_misc":0},"product_addon":[]},{"id":820,"unique_code":"20200414110932152020040808042515","prod_guid":"7acc3e0f-22c1-42f4-a204-5ac207e664e8","merchant_id":15,"venue_id":"2020040808042515","product_name":"75% OF 28 WOULD BUY IT AGAIN Moët & Chandon Grand Vintage 2012 Champagne","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Vintage Champagne is only made in the very best years and the 2012 crop was exceptional. Moët remains one of the finest exponents of vintage Champagne and has made the most of this blockbuster year. Awarded 92-points by Wine Advocate, its 'Grand Vintage' is a sophisticated medley of expressive yellow orchard fruit, toasted nuts, fresh baked bread and honeycomb. A truly decadent flute filler.<\/span><br><\/p>","total_quantity":100,"measurement_unit_id":null,"category_id":"70","group_id":null,"modifier_id":null,"related_product_ids":"804,805,807","brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"Vintagewine","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":11,"final_buy_price":6,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/7651815887455480.jpeg,,,","modifier_name":"75% OF 28 WOULD BUY IT AGAIN Moët & Chandon Grand Vintage 2012 Champagne","modifier_value":null,"modifier_list":[{"id":920,"product_id":820,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"11.00","rrp":"0.00","barcode":null,"quantity":2,"quantity_per_case":1,"moq":0,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:32:21","updated_at":"2020-04-25 15:02:15","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":923,"product_id":820,"venueId":null,"modifier_name":null,"variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"0.00","rrp":"0.00","barcode":null,"quantity":1110,"quantity_per_case":1,"moq":0,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-24 06:21:50","updated_at":"2020-04-24 06:21:50","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 13:41:01","updated_at":"2020-04-28 06:55:54","choose_addon_num":2,"free_addon_num":1,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":10,"short_num":6,"parent_cate_id":69,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Whisky","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Whisky","product_feature":null,"image":"Whisky09042020134101.png","color":"64c9e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[{"product_id":805,"final_sell_price":7,"product_name":"The Guv'nor","modifier_id":917,"modifier_name":"S","sell_price":"7.00","offer_id":null,"discount_type":null,"discount_amount":null},{"product_id":807,"final_sell_price":10,"product_name":"Rioja Reserva 'Selección Especial' Viña Ardanza 2010 La Rioja Alta","modifier_id":918,"modifier_name":"750ML","sell_price":"10.00","offer_id":null,"discount_type":null,"discount_amount":null},{"product_id":804,"final_sell_price":8,"product_name":"Porta 6 2018, Lisboa","modifier_id":1685,"modifier_name":"S","sell_price":"8.00","offer_id":null,"discount_type":null,"discount_amount":null},{"product_id":804,"final_sell_price":8,"product_name":"Porta 6 2018, Lisboa","modifier_id":1686,"modifier_name":"Medium","sell_price":"0.00","offer_id":null,"discount_type":null,"discount_amount":null}],"parent_category_name":{"id":69,"cat_name":"Whisky","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Whisky","product_feature":null,"status":1,"is_integrated":0,"image":"Whisky09042020134101.png","color":"64c9e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 13:41:01","updated_at":"2020-04-28 06:55:54","short_num":6,"is_misc":0},"product_addon":[{"id":8,"product_id":820,"addon_id":3,"sell_price":"1.00","qty":50,"created_at":"2020-04-23 15:32:21","updated_at":"2020-07-30 06:32:31","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":1,"choose_modifier_num":10,"free_modifier_num":1,"is_mandatory":0,"group_id":null,"group_modifier_id":"0","status":1,"addon":{"id":3,"category_id":3,"sub_category_id":3,"name":"Sugarr","sku":"518498877","buy_price":"1.00","sell_price":"2.00","quantity":1001,"venue_id":"2020040808042515","image":"1586856951.jpg","created_at":"2020-04-14 10:32:48","updated_at":"2020-04-14 10:35:51","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}},{"id":9,"product_id":820,"addon_id":4,"sell_price":"2.00","qty":20,"created_at":"2020-04-23 15:32:21","updated_at":"2020-07-30 06:32:31","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":2,"choose_modifier_num":10,"free_modifier_num":1,"is_mandatory":0,"group_id":null,"group_modifier_id":"0","status":1,"addon":{"id":4,"category_id":3,"sub_category_id":3,"name":"Grapes","sku":"5448798","buy_price":"1.50","sell_price":"2.50","quantity":1200,"venue_id":"2020040808042515","image":"1586857065.jpg","created_at":"2020-04-14 10:37:45","updated_at":"2020-04-14 10:37:45","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}}]},{"id":804,"unique_code":"20200408095135152020040808042515","prod_guid":"e40bae4e-7718-4d99-9f6e-dcb0ca768361","merchant_id":15,"venue_id":"2020040808042515","product_name":"Porta 6 2018, Lisboa","product_description":null,"total_quantity":50000,"measurement_unit_id":null,"category_id":"54","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"3","tax_type":1,"delivery_type":"00","service_charges":"0.00","product_tags":"portawine","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":8,"final_buy_price":5,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/3916515948101280.png","modifier_name":"Porta 6 2018, Lisboa","modifier_value":null,"modifier_list":[{"id":1685,"product_id":804,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"8.00","rrp":"0.00","barcode":null,"quantity":106,"quantity_per_case":1,"moq":0,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-07-15 11:48:48","updated_at":"2020-07-15 11:48:48","addon_max_qty":2,"order_num":0,"group_qty":[]},{"id":1686,"product_id":804,"venueId":null,"modifier_name":"Medium","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"0.00","rrp":"0.00","barcode":null,"quantity":10,"quantity_per_case":1,"moq":0,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-07-15 11:48:48","updated_at":"2020-07-15 11:48:48","addon_max_qty":3,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-08 09:43:37","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":0,"is_veg":1,"inventory_tag_id":1,"product_type":2,"product_moq":0,"short_num":8,"parent_cate_id":53,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Wine","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Wine","product_feature":null,"image":"Wine08042020094337.png","color":"64e0e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":53,"cat_name":"Wine","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Wine","product_feature":null,"status":1,"is_integrated":0,"image":"Wine08042020094337.png","color":"64e0e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-08 09:43:37","updated_at":"2020-04-28 06:55:54","short_num":8,"is_misc":0},"product_addon":[{"id":185,"product_id":804,"addon_id":3,"sell_price":"1.00","qty":1,"created_at":"2020-07-15 11:48:48","updated_at":"2020-07-15 11:48:48","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":1,"choose_modifier_num":2,"free_modifier_num":0,"is_mandatory":1,"group_id":"G_804WMAVmit","group_modifier_id":"0","status":1,"addon":{"id":3,"category_id":3,"sub_category_id":3,"name":"Sugarr","sku":"518498877","buy_price":"1.00","sell_price":"2.00","quantity":1001,"venue_id":"2020040808042515","image":"1586856951.jpg","created_at":"2020-04-14 10:32:48","updated_at":"2020-04-14 10:35:51","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}},{"id":186,"product_id":804,"addon_id":4,"sell_price":"1.00","qty":1,"created_at":"2020-07-15 11:48:48","updated_at":"2020-07-15 11:48:48","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":2,"choose_modifier_num":2,"free_modifier_num":0,"is_mandatory":1,"group_id":"G_804WMAVmit","group_modifier_id":"0","status":1,"addon":{"id":4,"category_id":3,"sub_category_id":3,"name":"Grapes","sku":"5448798","buy_price":"1.50","sell_price":"2.50","quantity":1200,"venue_id":"2020040808042515","image":"1586857065.jpg","created_at":"2020-04-14 10:37:45","updated_at":"2020-04-14 10:37:45","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}}]},{"id":848,"unique_code":"20200424063103152020040808042515","prod_guid":"bbb4d906-c7f9-4859-a812-2609321e5522","merchant_id":15,"venue_id":"2020040808042515","product_name":"Jim Barry 'Cover Drive' Cabernet Sauvignon 2017, Australia","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Coonawarra is the Bordeaux of Australia. Jim Barry is one of the region's top wineries, awarded a perfect 'Five Red Stars' by leading Aussie wine critic James Halliday. You'll taste it in ripe notes of black fruits and cedar. This would cost at least twice the price if it was French.<\/span><br><\/p>","total_quantity":1000,"measurement_unit_id":null,"category_id":"54","group_id":null,"modifier_id":null,"related_product_ids":"804","brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"wine,Auswine","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":10,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/7155215887455820.png,uploaded/products/2546415877062631.jpeg,,","modifier_name":"Jim Barry 'Cover Drive' Cabernet Sauvignon 2017, Australia","modifier_value":null,"modifier_list":[{"id":926,"product_id":848,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"10.00","rrp":"0.00","barcode":null,"quantity":996,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-24 06:31:03","updated_at":"2020-04-25 11:45:41","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-08 09:43:37","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":1,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":10,"short_num":8,"parent_cate_id":53,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Wine","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Wine","product_feature":null,"image":"Wine08042020094337.png","color":"64e0e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[{"product_id":804,"final_sell_price":8,"product_name":"Porta 6 2018, Lisboa","modifier_id":1685,"modifier_name":"S","sell_price":"8.00","offer_id":null,"discount_type":null,"discount_amount":null},{"product_id":804,"final_sell_price":8,"product_name":"Porta 6 2018, Lisboa","modifier_id":1686,"modifier_name":"Medium","sell_price":"0.00","offer_id":null,"discount_type":null,"discount_amount":null}],"parent_category_name":{"id":53,"cat_name":"Wine","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Wine","product_feature":null,"status":1,"is_integrated":0,"image":"Wine08042020094337.png","color":"64e0e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-08 09:43:37","updated_at":"2020-04-28 06:55:54","short_num":8,"is_misc":0},"product_addon":[{"id":10,"product_id":848,"addon_id":4,"sell_price":"1.00","qty":100,"created_at":"2020-04-24 06:31:03","updated_at":"2020-07-30 06:32:31","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":1,"choose_modifier_num":10,"free_modifier_num":1,"is_mandatory":0,"group_id":null,"group_modifier_id":"0","status":1,"addon":{"id":4,"category_id":3,"sub_category_id":3,"name":"Grapes","sku":"5448798","buy_price":"1.50","sell_price":"2.50","quantity":1200,"venue_id":"2020040808042515","image":"1586857065.jpg","created_at":"2020-04-14 10:37:45","updated_at":"2020-04-14 10:37:45","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}},{"id":11,"product_id":848,"addon_id":3,"sell_price":"1.00","qty":100,"created_at":"2020-04-24 06:31:03","updated_at":"2020-07-30 06:32:31","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":2,"choose_modifier_num":10,"free_modifier_num":1,"is_mandatory":0,"group_id":null,"group_modifier_id":"0","status":1,"addon":{"id":3,"category_id":3,"sub_category_id":3,"name":"Sugarr","sku":"518498877","buy_price":"1.00","sell_price":"2.00","quantity":1001,"venue_id":"2020040808042515","image":"1586856951.jpg","created_at":"2020-04-14 10:32:48","updated_at":"2020-04-14 10:35:51","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}}]}],"first_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/hospitalityVenueDetail?page=1","from":1,"last_page":2,"last_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/hospitalityVenueDetail?page=2","next_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/hospitalityVenueDetail?page=2","path":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/hospitalityVenueDetail","per_page":15,"prev_page_url":null,"to":15,"total":28}
     * venues : {"id":27,"max_order_slot":"8.00","venue_category":"Wine,Redwine,Scotch,Whisky","product_type":"both","merchant_id":15,"venue_id":"2020040808042515","latitude":"52.586973","longitude":"-2.12882","venue_name":"MAJESTIC WINE","venue_images":"20200506070720-Majestic-Wine.jpg,,,,,","address_1":"Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV30DS","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":100,"is_booking_allow":0,"booking_slot_gap":60,"cleaning_buffer":5,"restaurant_capacity":90,"booking_fee_type":1,"max_booking_duration":7,"max_guest":15,"is_refundable":1,"service_charge":0,"is_service_charge":1,"default_reservation_length":55,"end_days":45,"collection_time":30,"delivery_rating":5,"order_status":1,"venue_description":null,"distance":"3,634.86","rate":"5.000000000","is_like":0,"total_rating_count":1,"timing":{"total_offers":0,"opening_time":"2020-12-23 07:00","closing_time":"2020-12-23 23:00","delivery_opening_time":"07:00","delivery_closing_time":"22:40","delivery_closing_datetime":"2020-12-23 22:40","collection_opening_time":"07:00","collection_closing_time":"22:40","is_venue_open":1,"isClose":"0"},"reservation_slot":[]}
     * reservationBooking : null
     * isCatFilter : 0
     * isClose : 0
     * cateId : 0
     * isShow : 0
     * reservationcloseday : []
     * favProductCount : 0
     * theme : null
     */

    private boolean status;
    private String message;
    private ProductsBean products;


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

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }



    public static class ProductsBean implements Parcelable {
        /**
         * current_page : 1
         * data : [{"id":807,"unique_code":"20200408112425152020040808042515","prod_guid":"80472887-93e8-4a25-96c1-c5dc28d8d5ac","merchant_id":15,"venue_id":"2020040808042515","product_name":"Rioja Reserva 'Selección Especial' Viña Ardanza 2010 La Rioja Alta","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">The Viña Ardanza 'Selección Especial' Reserva is only made in the very best vintages. This is only the fourth in history and the first in 19 years. A heady blend of Tempranillo and Garnacha, from 30-year-old vines, and aged for three years in American oak barrels, it's one of the finest expressions of Rioja Reserva you're likely to taste. No wonder its scooped 96 points from James Suckling and 95 points from Robert Parker's Wine Advocate.<\/span><br><\/p>","total_quantity":0,"measurement_unit_id":null,"category_id":"64","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"wine","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":10,"final_buy_price":5,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/9354415887454770.png,,,","modifier_name":"Rioja Reserva 'Selección Especial' Viña Ardanza 2010 La Rioja Alta","modifier_value":null,"modifier_list":[{"id":918,"product_id":807,"venueId":null,"modifier_name":"750ML","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"10.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:31:29","updated_at":"2020-06-18 08:21:41","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":926,"unique_code":"20200515145811152020040808042515","prod_guid":"20200515145811152020040808042515","merchant_id":15,"venue_id":"2020040808042515","product_name":"Absolut vanilla flavoured vodka 70cl","product_description":"Absolut Vanillia is rich, robust and complex with the distinct taste of vanilla, notes of butterscotch and hints of dark chocolate. Made exclusively from natural ingredients, it doesn't contain any added sugar.","total_quantity":1000,"measurement_unit_id":0,"category_id":"156","group_id":0,"modifier_id":"0","related_product_ids":"","brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"","tax_type":null,"delivery_type":null,"service_charges":"0.00","product_tags":"Absolut vanilla flavoured vodka 70cl","product_availability":null,"shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":21,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":"","meta_name":"","meta_keyword":null,"images":"uploaded/products/2957215895512690.png,,,","modifier_name":null,"modifier_value":null,"modifier_list":[{"id":1100,"product_id":926,"venueId":null,"modifier_name":"S","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"21.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":1107,"product_id":926,"venueId":null,"modifier_name":"750ml","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"21.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 15:22:31","updated_at":"2020-05-15 15:22:31","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":0,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":927,"unique_code":"20200515145811152020040808042515","prod_guid":"20200515145811152020040808042515","merchant_id":15,"venue_id":"2020040808042515","product_name":"Chase rhubarb vodka 70cl","product_description":"At Chase they make everything from scratch. They grow the potatoes or the vodka on their working farm (they also make Tyrrells crisps) and even handcraft the beautiful bottles. Fabulously creamy and smooth, serve straight from the fridge for best effects.","total_quantity":1000,"measurement_unit_id":0,"category_id":"157","group_id":0,"modifier_id":"0","related_product_ids":"","brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"","tax_type":null,"delivery_type":null,"service_charges":"0.00","product_tags":"Chase rhubarb vodka 70cl","product_availability":null,"shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":19,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":"","meta_name":"","meta_keyword":null,"images":"uploaded/products/8665115895512780.png,,,","modifier_name":null,"modifier_value":null,"modifier_list":[{"id":1101,"product_id":927,"venueId":null,"modifier_name":"S","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"19.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":1108,"product_id":927,"venueId":null,"modifier_name":"750ml","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"19.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 15:22:31","updated_at":"2020-05-15 15:22:31","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":0,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":858,"unique_code":"20200427095933152020040808042515","prod_guid":"900d7f5e-7614-44c6-be54-21018fbd625e","merchant_id":15,"venue_id":"2020040808042515","product_name":"Absolut Vodka 70cl","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Absolut Vodka is one of the most famous vodkas in the world. The main ingredients are water and winter wheat grown in the fields of Åhus, Sweden. The water comes from a deep well in Åhus where it's protected from impurities. With its refined nature and no added sugar, Absolut vodka blends perfectly with other aromas, enhancing the taste of your drink. Pour over ice, add soda and garnish with lime.<\/span><br><\/p>","total_quantity":100,"measurement_unit_id":null,"category_id":"98","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"Absolute,Vodka","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":20,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/2232615887457440.png,uploaded/products/2546415877062631.jpeg,,","modifier_name":"Absolut Vodka 70cl","modifier_value":null,"modifier_list":[{"id":1006,"product_id":858,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"20.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-27 09:59:33","updated_at":"2020-04-27 09:59:33","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":10,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":1066,"unique_code":"20200612111007152020040808042515","prod_guid":"dd2a6cf7-8e6d-4d48-bc3a-f39e27499013","merchant_id":15,"venue_id":"2020040808042515","product_name":"Asahi Superdry Beer","product_description":"<p><br data-mce-bogus=\"1\"><\/p>","total_quantity":100,"measurement_unit_id":null,"category_id":"64","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":null,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"11","service_charges":"0.00","product_tags":"Asahi,Superdry,Beer","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":2.99,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/8664815919566070.jpeg","modifier_name":"Asahi Superdry Beer","modifier_value":null,"modifier_list":[{"id":1410,"product_id":1066,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"2.99","rrp":"0.00","barcode":null,"quantity":1,"quantity_per_case":1,"moq":1,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-06-12 11:10:07","updated_at":"2020-06-12 11:10:07","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":0,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":1,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":863,"unique_code":"20200427100506152020040808042515","prod_guid":"ce59b572-9a5e-4250-8f74-a1956b923bd3","merchant_id":15,"venue_id":"2020040808042515","product_name":"Absolut Raspberry Flavoured Vodka 70cl","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Absolut Raspberri Vodka is rich and intense with the fresh and fruity taste of ripened raspberries harvested in the Swedish hillsides. Absolut Raspberri is made exclusively from natural ingredients, and unlike some other flavoured vodkas, it doesn't contain any added sugar making it perfect for mixing. Serving suggestion: Use Absolut Raspberri to make the ultimate Absolut Raspberri Collins.<\/span><br><\/p>","total_quantity":100,"measurement_unit_id":null,"category_id":"98","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"Spirits,Fortified","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":22,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/6371815887457650.png,uploaded/products/2546415877062631.jpeg,,","modifier_name":"Absolut Raspberry Flavoured Vodka 70cl","modifier_value":null,"modifier_list":[{"id":1011,"product_id":863,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"22.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-27 10:05:06","updated_at":"2020-04-27 10:05:06","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":10,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":913,"unique_code":"20200515075848152020040808042515","prod_guid":"cf2d2c8b-23d0-433b-845c-d1d096f2cd2d","merchant_id":15,"venue_id":"2020040808042515","product_name":"Stowford Press Cider 0.5% 12x 330ml Bottles","product_description":"<p>Westons Cider has been producing traditional English cider in the Herefordshire village of Much Marcle since 1880. It's run by the fourth generation of the Westons family and is one of the largest independent cider makers in the UK today. It continues to use traditional methods in the cider making process, passed down through the generations, such as maturing the cider for long periods in oak vats for a great depth of flavour. However, this traditon doesn't mean it's a stick-in-the-mud. This guilt-free cider has all the flavour of your favourite ciders just at 0.5% ABV.<\/p>","total_quantity":50000,"measurement_unit_id":null,"category_id":"64","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"4","tax_type":1,"delivery_type":"00","service_charges":"0.00","product_tags":"Stowford,Press,Cider,0.5%,12x,330ml,Bottles","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":10,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/5560215895259280.png","modifier_name":"Stowford Press Cider 0.5% 12x 330ml Bottles","modifier_value":null,"modifier_list":[{"id":1081,"product_id":913,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"10.00","rrp":"0.00","barcode":null,"quantity":1,"quantity_per_case":1,"moq":1,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 07:58:48","updated_at":"2020-05-15 07:58:48","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":1,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":914,"unique_code":"20200515080100152020040808042515","prod_guid":"dc0e5a6e-eeb8-46f8-b81c-9abf386d3510","merchant_id":15,"venue_id":"2020040808042515","product_name":"Ghost Vodka Gold Vodka 70cl","product_description":"<p>Ghost is an ultra-premium vodka like no other. It is distilled at a haunted salt depot in Cambridge using sugar beet. the result is a smooth vodka that works beautifully when mixed with citrus or sweeter flavours. Alternatively, give it 20 minutes in the freezer and enjoy how 'scarily' smooth it really is.<\/p>","total_quantity":50000,"measurement_unit_id":null,"category_id":"98","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":null,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"3","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"Ghost,Vodka,Gold,70cl","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":30,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/8906315895260600.png","modifier_name":"Ghost Vodka Gold Vodka 70cl","modifier_value":null,"modifier_list":[{"id":1082,"product_id":914,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"30.00","rrp":"0.00","barcode":null,"quantity":1,"quantity_per_case":1,"moq":1,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 08:01:00","updated_at":"2020-05-15 08:01:00","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":1,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":924,"unique_code":"20200515145811152020040808042515","prod_guid":"20200515145811152020040808042515","merchant_id":15,"venue_id":"2020040808042515","product_name":"Crystal head vodka","product_description":"Yes, Dan Aykroyd does make a vodka. It's made from the highest quality peaches and cream corn, distilled four times into a neutral grain spirit and blended with pristine water from Newfoundland. The liquid is then filtered seven times, of which three are through layers of semi precious crystals known as Herkimer diamonds. The result? One of the purest vodkas going.","total_quantity":1000,"measurement_unit_id":0,"category_id":"154","group_id":0,"modifier_id":"0","related_product_ids":"","brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"","tax_type":null,"delivery_type":null,"service_charges":"0.00","product_tags":"Crystal head vodka","product_availability":null,"shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":20,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":"","meta_name":"","meta_keyword":null,"images":"uploaded/products/8524515895512490.jpeg,,,","modifier_name":null,"modifier_value":null,"modifier_list":[{"id":1098,"product_id":924,"venueId":null,"modifier_name":"S","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"20.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":1105,"product_id":924,"venueId":null,"modifier_name":"750ml","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"20.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 15:22:31","updated_at":"2020-05-15 15:22:31","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":0,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":925,"unique_code":"20200515145811152020040808042515","prod_guid":"20200515145811152020040808042515","merchant_id":15,"venue_id":"2020040808042515","product_name":"Black cow strawberry flavoured vodka 70cl","product_description":"By using strawberries to flavour the vodka naturally, with no added sugar or sweeteners the fresh fruit maceration creates a beautiful strawberry blush vodka the taste of which is unparalleled amongst competitor products with similar flavour expressions. The resulting vodka has an unmistakable strawberry taste with delicate, honeyed floral notes. Delicious served neat over ice or with a soda splash","total_quantity":1000,"measurement_unit_id":0,"category_id":"155","group_id":0,"modifier_id":"0","related_product_ids":"","brand_id":36,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"","tax_type":null,"delivery_type":null,"service_charges":"0.00","product_tags":"Black cow strawberry flavoured vodka 70cl","product_availability":null,"shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":25,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":"","meta_name":"","meta_keyword":null,"images":"uploaded/products/1564815895512610.png,,,","modifier_name":null,"modifier_value":null,"modifier_list":[{"id":1099,"product_id":925,"venueId":null,"modifier_name":"S","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"25.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 14:58:11","updated_at":"2020-05-15 14:58:11","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":1106,"product_id":925,"venueId":null,"modifier_name":"750ml","variant_type":null,"sku":"1","weight":"0","buy_price":"0.00","sell_price":"25.00","rrp":"0.00","barcode":null,"quantity":1000,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-05-15 15:22:31","updated_at":"2020-05-15 15:22:31","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","choose_addon_num":0,"free_addon_num":0,"is_veg":1,"inventory_tag_id":null,"product_type":2,"product_moq":0,"short_num":5,"parent_cate_id":63,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Beer & Spirits","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0},"product_addon":[]},{"id":805,"unique_code":"20200408100515152020040808042515","prod_guid":"55d5b80f-3ac7-4e93-9701-889476abc1b5","merchant_id":15,"venue_id":"2020040808042515","product_name":"The Guv'nor","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">The Guv'nor is made by our favourite winemakers. We told them to craft the ideal wine they'd drink at home and to forget any rules to achieve it. The result is a deep, multi-regional Tempranillo that's chock-full of bramble, blackcurrant and vanilla.<\/span><br><\/p>","total_quantity":0,"measurement_unit_id":null,"category_id":"62","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"porta","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":7,"final_buy_price":4,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/7420415887454250.jpeg,,,","modifier_name":"The Guv'nor","modifier_value":null,"modifier_list":[{"id":917,"product_id":805,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"7.00","rrp":"0.00","barcode":null,"quantity":99,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:30:44","updated_at":"2020-06-18 09:34:21","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:39:31","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":1,"product_type":2,"product_moq":0,"short_num":6,"parent_cate_id":61,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Champagne","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"champ","product_feature":null,"image":"Champagne09042020074223.png","color":"e66465","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":61,"cat_name":"Champagne","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"champ","product_feature":null,"status":1,"is_integrated":0,"image":"Champagne09042020074223.png","color":"e66465","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:39:31","updated_at":"2020-04-28 06:55:54","short_num":6,"is_misc":0},"product_addon":[]},{"id":811,"unique_code":"20200409081426152020040808042515","prod_guid":"1522d195-eadb-4287-b2ca-e1180f3051e4","merchant_id":15,"venue_id":"2020040808042515","product_name":"Bouvet Ladubay Saumur, France","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Bouvet is made with the same 'traditional method' as Champagne. But it's crafted from Chenin Blanc grapes. This pumps up the honeysuckle-and-citrus-filled body of the juice. If you're looking for a pocket-friendly Champagne double, it's here. The Telegraph's Victoria Moore called it \"excellent value, proper wine\".<\/span><br><\/p>","total_quantity":0,"measurement_unit_id":null,"category_id":"62","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"cham,Old","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":12,"final_buy_price":7,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/3506015887455110.png,,,","modifier_name":"Bouvet Ladubay Saumur, France","modifier_value":null,"modifier_list":[{"id":919,"product_id":811,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"12.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:31:47","updated_at":"2020-04-25 14:22:39","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 07:39:31","updated_at":"2020-04-28 06:55:54","choose_addon_num":null,"free_addon_num":null,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":0,"short_num":6,"parent_cate_id":61,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Champagne","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"champ","product_feature":null,"image":"Champagne09042020074223.png","color":"e66465","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":61,"cat_name":"Champagne","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"champ","product_feature":null,"status":1,"is_integrated":0,"image":"Champagne09042020074223.png","color":"e66465","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:39:31","updated_at":"2020-04-28 06:55:54","short_num":6,"is_misc":0},"product_addon":[]},{"id":820,"unique_code":"20200414110932152020040808042515","prod_guid":"7acc3e0f-22c1-42f4-a204-5ac207e664e8","merchant_id":15,"venue_id":"2020040808042515","product_name":"75% OF 28 WOULD BUY IT AGAIN Moët & Chandon Grand Vintage 2012 Champagne","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Vintage Champagne is only made in the very best years and the 2012 crop was exceptional. Moët remains one of the finest exponents of vintage Champagne and has made the most of this blockbuster year. Awarded 92-points by Wine Advocate, its 'Grand Vintage' is a sophisticated medley of expressive yellow orchard fruit, toasted nuts, fresh baked bread and honeycomb. A truly decadent flute filler.<\/span><br><\/p>","total_quantity":100,"measurement_unit_id":null,"category_id":"70","group_id":null,"modifier_id":null,"related_product_ids":"804,805,807","brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"Vintagewine","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":11,"final_buy_price":6,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/7651815887455480.jpeg,,,","modifier_name":"75% OF 28 WOULD BUY IT AGAIN Moët & Chandon Grand Vintage 2012 Champagne","modifier_value":null,"modifier_list":[{"id":920,"product_id":820,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"11.00","rrp":"0.00","barcode":null,"quantity":2,"quantity_per_case":1,"moq":0,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:32:21","updated_at":"2020-04-25 15:02:15","addon_max_qty":0,"order_num":0,"group_qty":[]},{"id":923,"product_id":820,"venueId":null,"modifier_name":null,"variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"0.00","rrp":"0.00","barcode":null,"quantity":1110,"quantity_per_case":1,"moq":0,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-24 06:21:50","updated_at":"2020-04-24 06:21:50","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-09 13:41:01","updated_at":"2020-04-28 06:55:54","choose_addon_num":2,"free_addon_num":1,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":10,"short_num":6,"parent_cate_id":69,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Whisky","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Whisky","product_feature":null,"image":"Whisky09042020134101.png","color":"64c9e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[{"product_id":805,"final_sell_price":7,"product_name":"The Guv'nor","modifier_id":917,"modifier_name":"S","sell_price":"7.00","offer_id":null,"discount_type":null,"discount_amount":null},{"product_id":807,"final_sell_price":10,"product_name":"Rioja Reserva 'Selección Especial' Viña Ardanza 2010 La Rioja Alta","modifier_id":918,"modifier_name":"750ML","sell_price":"10.00","offer_id":null,"discount_type":null,"discount_amount":null},{"product_id":804,"final_sell_price":8,"product_name":"Porta 6 2018, Lisboa","modifier_id":1685,"modifier_name":"S","sell_price":"8.00","offer_id":null,"discount_type":null,"discount_amount":null},{"product_id":804,"final_sell_price":8,"product_name":"Porta 6 2018, Lisboa","modifier_id":1686,"modifier_name":"Medium","sell_price":"0.00","offer_id":null,"discount_type":null,"discount_amount":null}],"parent_category_name":{"id":69,"cat_name":"Whisky","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Whisky","product_feature":null,"status":1,"is_integrated":0,"image":"Whisky09042020134101.png","color":"64c9e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 13:41:01","updated_at":"2020-04-28 06:55:54","short_num":6,"is_misc":0},"product_addon":[{"id":8,"product_id":820,"addon_id":3,"sell_price":"1.00","qty":50,"created_at":"2020-04-23 15:32:21","updated_at":"2020-07-30 06:32:31","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":1,"choose_modifier_num":10,"free_modifier_num":1,"is_mandatory":0,"group_id":null,"group_modifier_id":"0","status":1,"addon":{"id":3,"category_id":3,"sub_category_id":3,"name":"Sugarr","sku":"518498877","buy_price":"1.00","sell_price":"2.00","quantity":1001,"venue_id":"2020040808042515","image":"1586856951.jpg","created_at":"2020-04-14 10:32:48","updated_at":"2020-04-14 10:35:51","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}},{"id":9,"product_id":820,"addon_id":4,"sell_price":"2.00","qty":20,"created_at":"2020-04-23 15:32:21","updated_at":"2020-07-30 06:32:31","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":2,"choose_modifier_num":10,"free_modifier_num":1,"is_mandatory":0,"group_id":null,"group_modifier_id":"0","status":1,"addon":{"id":4,"category_id":3,"sub_category_id":3,"name":"Grapes","sku":"5448798","buy_price":"1.50","sell_price":"2.50","quantity":1200,"venue_id":"2020040808042515","image":"1586857065.jpg","created_at":"2020-04-14 10:37:45","updated_at":"2020-04-14 10:37:45","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}}]},{"id":804,"unique_code":"20200408095135152020040808042515","prod_guid":"e40bae4e-7718-4d99-9f6e-dcb0ca768361","merchant_id":15,"venue_id":"2020040808042515","product_name":"Porta 6 2018, Lisboa","product_description":null,"total_quantity":50000,"measurement_unit_id":null,"category_id":"54","group_id":null,"modifier_id":null,"related_product_ids":null,"brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"3","tax_type":1,"delivery_type":"00","service_charges":"0.00","product_tags":"portawine","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":8,"final_buy_price":5,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/3916515948101280.png","modifier_name":"Porta 6 2018, Lisboa","modifier_value":null,"modifier_list":[{"id":1685,"product_id":804,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"8.00","rrp":"0.00","barcode":null,"quantity":106,"quantity_per_case":1,"moq":0,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-07-15 11:48:48","updated_at":"2020-07-15 11:48:48","addon_max_qty":2,"order_num":0,"group_qty":[]},{"id":1686,"product_id":804,"venueId":null,"modifier_name":"Medium","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"0.00","rrp":"0.00","barcode":null,"quantity":10,"quantity_per_case":1,"moq":0,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-07-15 11:48:48","updated_at":"2020-07-15 11:48:48","addon_max_qty":3,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-08 09:43:37","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":0,"is_veg":1,"inventory_tag_id":1,"product_type":2,"product_moq":0,"short_num":8,"parent_cate_id":53,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Wine","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Wine","product_feature":null,"image":"Wine08042020094337.png","color":"64e0e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[],"parent_category_name":{"id":53,"cat_name":"Wine","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Wine","product_feature":null,"status":1,"is_integrated":0,"image":"Wine08042020094337.png","color":"64e0e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-08 09:43:37","updated_at":"2020-04-28 06:55:54","short_num":8,"is_misc":0},"product_addon":[{"id":185,"product_id":804,"addon_id":3,"sell_price":"1.00","qty":1,"created_at":"2020-07-15 11:48:48","updated_at":"2020-07-15 11:48:48","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":1,"choose_modifier_num":2,"free_modifier_num":0,"is_mandatory":1,"group_id":"G_804WMAVmit","group_modifier_id":"0","status":1,"addon":{"id":3,"category_id":3,"sub_category_id":3,"name":"Sugarr","sku":"518498877","buy_price":"1.00","sell_price":"2.00","quantity":1001,"venue_id":"2020040808042515","image":"1586856951.jpg","created_at":"2020-04-14 10:32:48","updated_at":"2020-04-14 10:35:51","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}},{"id":186,"product_id":804,"addon_id":4,"sell_price":"1.00","qty":1,"created_at":"2020-07-15 11:48:48","updated_at":"2020-07-15 11:48:48","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":2,"choose_modifier_num":2,"free_modifier_num":0,"is_mandatory":1,"group_id":"G_804WMAVmit","group_modifier_id":"0","status":1,"addon":{"id":4,"category_id":3,"sub_category_id":3,"name":"Grapes","sku":"5448798","buy_price":"1.50","sell_price":"2.50","quantity":1200,"venue_id":"2020040808042515","image":"1586857065.jpg","created_at":"2020-04-14 10:37:45","updated_at":"2020-04-14 10:37:45","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}}]},{"id":848,"unique_code":"20200424063103152020040808042515","prod_guid":"bbb4d906-c7f9-4859-a812-2609321e5522","merchant_id":15,"venue_id":"2020040808042515","product_name":"Jim Barry 'Cover Drive' Cabernet Sauvignon 2017, Australia","product_description":"<p><span style=\"color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;\">Coonawarra is the Bordeaux of Australia. Jim Barry is one of the region's top wineries, awarded a perfect 'Five Red Stars' by leading Aussie wine critic James Halliday. You'll taste it in ripe notes of black fruits and cedar. This would cost at least twice the price if it was French.<\/span><br><\/p>","total_quantity":1000,"measurement_unit_id":null,"category_id":"54","group_id":null,"modifier_id":null,"related_product_ids":"804","brand_id":22,"supplier_id":null,"takeaway_with_tax":0,"tax_id":"0","tax_type":2,"delivery_type":"00","service_charges":"0.00","product_tags":"wine,Auswine","product_availability":"111","shipping_status":0,"shipping_prod_weight":0,"shipping_prod_unit":null,"final_sell_price":10,"final_buy_price":0,"bulk_sell_price":"0.00","bulk_sell_qty":null,"final_wastage":0,"final_discount_present_cat_price":0,"gross_profit":0,"domain_name":null,"meta_description":null,"meta_name":null,"meta_keyword":null,"images":"uploaded/products/7155215887455820.png,uploaded/products/2546415877062631.jpeg,,","modifier_name":"Jim Barry 'Cover Drive' Cabernet Sauvignon 2017, Australia","modifier_value":null,"modifier_list":[{"id":926,"product_id":848,"venueId":null,"modifier_name":"S","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"10.00","rrp":"0.00","barcode":null,"quantity":996,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-24 06:31:03","updated_at":"2020-04-25 11:45:41","addon_max_qty":0,"order_num":0,"group_qty":[]}],"return_day":0,"status":1,"action_type":"ACTION_ADDED","barcode":null,"barcode_type":null,"in_stock":1,"country":1,"rrp":"0.00","product_option":null,"product_link":null,"is_integrated":0,"is_sync":1,"deleted_at":null,"created_at":"2020-04-08 09:43:37","updated_at":"2020-04-28 06:55:54","choose_addon_num":1,"free_addon_num":1,"is_veg":1,"inventory_tag_id":5,"product_type":2,"product_moq":10,"short_num":8,"parent_cate_id":53,"product_place":null,"kitchen_name":null,"product_length":null,"product_width":null,"product_height":null,"cat_name":"Wine","parent_cat_id":0,"swoope_cat_id":null,"breadcrumb":"0","taggs":"Wine","product_feature":null,"image":"Wine08042020094337.png","color":"64e0e6","menu_level":1,"order_by":1,"taxable":1,"is_misc":0,"is_like":0,"offer_id":null,"discount_type":null,"discount_amount":null,"related_products":[{"product_id":804,"final_sell_price":8,"product_name":"Porta 6 2018, Lisboa","modifier_id":1685,"modifier_name":"S","sell_price":"8.00","offer_id":null,"discount_type":null,"discount_amount":null},{"product_id":804,"final_sell_price":8,"product_name":"Porta 6 2018, Lisboa","modifier_id":1686,"modifier_name":"Medium","sell_price":"0.00","offer_id":null,"discount_type":null,"discount_amount":null}],"parent_category_name":{"id":53,"cat_name":"Wine","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Wine","product_feature":null,"status":1,"is_integrated":0,"image":"Wine08042020094337.png","color":"64e0e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-08 09:43:37","updated_at":"2020-04-28 06:55:54","short_num":8,"is_misc":0},"product_addon":[{"id":10,"product_id":848,"addon_id":4,"sell_price":"1.00","qty":100,"created_at":"2020-04-24 06:31:03","updated_at":"2020-07-30 06:32:31","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":1,"choose_modifier_num":10,"free_modifier_num":1,"is_mandatory":0,"group_id":null,"group_modifier_id":"0","status":1,"addon":{"id":4,"category_id":3,"sub_category_id":3,"name":"Grapes","sku":"5448798","buy_price":"1.50","sell_price":"2.50","quantity":1200,"venue_id":"2020040808042515","image":"1586857065.jpg","created_at":"2020-04-14 10:37:45","updated_at":"2020-04-14 10:37:45","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}},{"id":11,"product_id":848,"addon_id":3,"sell_price":"1.00","qty":100,"created_at":"2020-04-24 06:31:03","updated_at":"2020-07-30 06:32:31","addon_cate_id":3,"addon_sub_cate_id":3,"short_num":2,"choose_modifier_num":10,"free_modifier_num":1,"is_mandatory":0,"group_id":null,"group_modifier_id":"0","status":1,"addon":{"id":3,"category_id":3,"sub_category_id":3,"name":"Sugarr","sku":"518498877","buy_price":"1.00","sell_price":"2.00","quantity":1001,"venue_id":"2020040808042515","image":"1586856951.jpg","created_at":"2020-04-14 10:32:48","updated_at":"2020-04-14 10:35:51","status":1,"addon_category":{"id":3,"cat_name":"Wine"}}}]}]
         * first_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/hospitalityVenueDetail?page=1
         * from : 1
         * last_page : 2
         * last_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/hospitalityVenueDetail?page=2
         * next_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/hospitalityVenueDetail?page=2
         * path : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/hospitalityVenueDetail
         * per_page : 15
         * prev_page_url : null
         * to : 15
         * total : 28
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

        protected ProductsBean(Parcel in) {
            current_page = in.readInt();
            first_page_url = in.readString();
            from = in.readInt();
            last_page = in.readInt();
            last_page_url = in.readString();
            next_page_url = in.readString();
            path = in.readString();
            per_page = in.readInt();
            to = in.readInt();
            total = in.readInt();
            data = in.createTypedArrayList(DataBean.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(current_page);
            dest.writeString(first_page_url);
            dest.writeInt(from);
            dest.writeInt(last_page);
            dest.writeString(last_page_url);
            dest.writeString(next_page_url);
            dest.writeString(path);
            dest.writeInt(per_page);
            dest.writeInt(to);
            dest.writeInt(total);
            dest.writeTypedList(data);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ProductsBean> CREATOR = new Creator<ProductsBean>() {
            @Override
            public ProductsBean createFromParcel(Parcel in) {
                return new ProductsBean(in);
            }

            @Override
            public ProductsBean[] newArray(int size) {
                return new ProductsBean[size];
            }
        };

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

        public static class DataBean implements Parcelable {
            /**
             * id : 807
             * unique_code : 20200408112425152020040808042515
             * prod_guid : 80472887-93e8-4a25-96c1-c5dc28d8d5ac
             * merchant_id : 15
             * venue_id : 2020040808042515
             * product_name : Rioja Reserva 'Selección Especial' Viña Ardanza 2010 La Rioja Alta
             * product_description : <p><span style="color: rgb(41, 53, 51); font-family: Avenir, &quot;Avenir Roman&quot;, sans-serif; font-size: 18px;">The Viña Ardanza 'Selección Especial' Reserva is only made in the very best vintages. This is only the fourth in history and the first in 19 years. A heady blend of Tempranillo and Garnacha, from 30-year-old vines, and aged for three years in American oak barrels, it's one of the finest expressions of Rioja Reserva you're likely to taste. No wonder its scooped 96 points from James Suckling and 95 points from Robert Parker's Wine Advocate.</span><br></p>
             * total_quantity : 0
             * measurement_unit_id : null
             * category_id : 64
             * group_id : null
             * modifier_id : null
             * related_product_ids : null
             * brand_id : 22
             * supplier_id : null
             * takeaway_with_tax : 0
             * tax_id : 0
             * tax_type : 2
             * delivery_type : 00
             * service_charges : 0.00
             * product_tags : wine
             * product_availability : 111
             * shipping_status : 0
             * shipping_prod_weight : 0
             * shipping_prod_unit : null
             * final_sell_price : 10
             * final_buy_price : 5
             * bulk_sell_price : 0.00
             * bulk_sell_qty : null
             * final_wastage : 0
             * final_discount_present_cat_price : 0
             * gross_profit : 0
             * domain_name : null
             * meta_description : null
             * meta_name : null
             * meta_keyword : null
             * images : uploaded/products/9354415887454770.png,,,
             * modifier_name : Rioja Reserva 'Selección Especial' Viña Ardanza 2010 La Rioja Alta
             * modifier_value : null
             * modifier_list : [{"id":918,"product_id":807,"venueId":null,"modifier_name":"750ML","variant_type":null,"sku":null,"weight":"0","buy_price":"0.00","sell_price":"10.00","rrp":"0.00","barcode":null,"quantity":100,"quantity_per_case":1,"moq":10,"bulk_selling_price":"0.00","bulk_selling_qty":null,"modifier_length":null,"modifier_width":null,"modifier_height":null,"modifier_images":null,"product_barcode_image":null,"product_qrcode_image":null,"modifier_status":1,"created_at":"2020-04-23 15:31:29","updated_at":"2020-06-18 08:21:41","addon_max_qty":0,"order_num":0,"group_qty":[]}]
             * return_day : 0
             * status : 1
             * action_type : ACTION_ADDED
             * barcode : null
             * barcode_type : null
             * in_stock : 1
             * country : 1
             * rrp : 0.00
             * product_option : null
             * product_link : null
             * is_integrated : 0
             * is_sync : 1
             * deleted_at : null
             * created_at : 2020-04-09 07:41:35
             * updated_at : 2020-04-28 06:55:54
             * choose_addon_num : null
             * free_addon_num : null
             * is_veg : 1
             * inventory_tag_id : 5
             * product_type : 2
             * product_moq : 0
             * short_num : 5
             * parent_cate_id : 63
             * product_place : null
             * kitchen_name : null
             * product_length : null
             * product_width : null
             * product_height : null
             * cat_name : Beer & Spirits
             * parent_cat_id : 0
             * swoope_cat_id : null
             * breadcrumb : 0
             * taggs : Beer
             * product_feature : null
             * image : Beer & Spirits09042020074135.png
             * color : 7164e6
             * menu_level : 1
             * order_by : 1
             * taxable : 1
             * is_misc : 0
             * is_like : 0
             * offer_id : null
             * discount_type : null
             * discount_amount : null
             * related_products : []
             * parent_category_name : {"id":63,"cat_name":"Beer & Spirits","parent_cat_id":0,"merchant_id":15,"venue_id":"2020040808042515","swoope_cat_id":null,"breadcrumb":"0","taggs":"Beer","product_feature":null,"status":1,"is_integrated":0,"image":"Beer & Spirits09042020074135.png","color":"7164e6","menu_level":1,"order_by":1,"taxable":1,"created_at":"2020-04-09 07:41:35","updated_at":"2020-04-28 06:55:54","short_num":5,"is_misc":0}
             * product_addon : []
             */

            private int id;
            private String unique_code;
            private String prod_guid;
            private int merchant_id;
            private String venue_id;
            private String product_name;
            private String product_description;
            private int total_quantity;
            private Object measurement_unit_id;
            private String category_id;
            private Object group_id;
            private int modifier_id;
            private Object related_product_ids;
            private int brand_id;
            private Object supplier_id;
            private int takeaway_with_tax;
            private String tax_id;
            private int tax_type;
            private String delivery_type;
            private String service_charges;
            private String product_tags;
            private String product_availability;
            private int shipping_status;
            private int shipping_prod_weight;
            private Object shipping_prod_unit;
            private double final_sell_price;
            private double final_buy_price;
            private String bulk_sell_price;
            private Object bulk_sell_qty;
            private int final_wastage;
            private int final_discount_present_cat_price;
            private int gross_profit;
            private Object domain_name;
            private Object meta_description;
            private Object meta_name;
            private Object meta_keyword;
            private String images;
            private String modifier_name;
            private Object modifier_value;
            private int return_day;
            private int status;
            private String action_type;
            private Object barcode;
            private Object barcode_type;
            private int in_stock;
            private int country;
            private String rrp;
            private Object product_option;
            private Object product_link;
            private int is_integrated;
            private int is_sync;
            private Object deleted_at;
            private String created_at;
            private String updated_at;
            private Object choose_addon_num;
            private Object free_addon_num;
            private int is_veg;
            private int inventory_tag_id;
            private int product_type;
            private int product_moq;
            private int short_num;
            private int parent_cate_id;
            private Object product_place;
            private Object kitchen_name;
            private Object product_length;
            private Object product_width;
            private Object product_height;
            private String cat_name;
            private int parent_cat_id;
            private Object swoope_cat_id;
            private String breadcrumb;
            private String taggs;
            private Object product_feature;
            private String image;
            private String color;
            private int menu_level;
            private int order_by;
            private int taxable;
            private int is_misc;
            private int is_like;
            private int offer_id;
            private float stars;
            private int discount_type;
            private String discount_amount;
            private ParentCategoryNameBean parent_category_name;
            private List<ModifierListBean> modifier_list;
            private List<RelatedProductsBean> related_products;
            private List<ProductAddonBean> product_addon;
            private List<AllergiesListBean> allergies_list;
            private int isCart;
            private String mSelectedAllergy;

            protected DataBean(Parcel in) {
                id = in.readInt();
                unique_code = in.readString();
                prod_guid = in.readString();
                merchant_id = in.readInt();
                venue_id = in.readString();
                product_name = in.readString();
                product_description = in.readString();
                total_quantity = in.readInt();
                category_id = in.readString();
                modifier_id = in.readInt();
                brand_id = in.readInt();
                takeaway_with_tax = in.readInt();
                tax_id = in.readString();
                tax_type = in.readInt();
                delivery_type = in.readString();
                service_charges = in.readString();
                product_tags = in.readString();
                product_availability = in.readString();
                shipping_status = in.readInt();
                shipping_prod_weight = in.readInt();
                final_sell_price = in.readDouble();
                final_buy_price = in.readDouble();
                bulk_sell_price = in.readString();
                final_wastage = in.readInt();
                final_discount_present_cat_price = in.readInt();
                gross_profit = in.readInt();
                images = in.readString();
                modifier_name = in.readString();
                return_day = in.readInt();
                status = in.readInt();
                action_type = in.readString();
                in_stock = in.readInt();
                country = in.readInt();
                rrp = in.readString();
                is_integrated = in.readInt();
                is_sync = in.readInt();
                created_at = in.readString();
                updated_at = in.readString();
                is_veg = in.readInt();
                inventory_tag_id = in.readInt();
                product_type = in.readInt();
                product_moq = in.readInt();
                short_num = in.readInt();
                parent_cate_id = in.readInt();
                cat_name = in.readString();
                parent_cat_id = in.readInt();
                breadcrumb = in.readString();
                taggs = in.readString();
                image = in.readString();
                color = in.readString();
                mSelectedAllergy = in.readString();
                menu_level = in.readInt();
                order_by = in.readInt();
                taxable = in.readInt();
                is_misc = in.readInt();
                is_like = in.readInt();
                isCart = in.readInt();
                offer_id = in.readInt();
                discount_type = in.readInt();
                discount_amount = in.readString();
                stars = in.readFloat();
                modifier_list = in.createTypedArrayList(ModifierListBean.CREATOR);
                product_addon = in.createTypedArrayList(ProductAddonBean.CREATOR);
                related_products = in.createTypedArrayList(RelatedProductsBean.CREATOR);
                allergies_list = in.createTypedArrayList(AllergiesListBean.CREATOR);
            }
            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(unique_code);
                dest.writeString(prod_guid);
                dest.writeInt(merchant_id);
                dest.writeString(venue_id);
                dest.writeString(product_name);
                dest.writeString(product_description);
                dest.writeInt(total_quantity);
                dest.writeString(category_id);
                dest.writeInt(modifier_id);
                dest.writeInt(brand_id);
                dest.writeInt(takeaway_with_tax);
                dest.writeString(tax_id);
                dest.writeInt(tax_type);
                dest.writeString(delivery_type);
                dest.writeString(service_charges);
                dest.writeString(product_tags);
                dest.writeString(product_availability);
                dest.writeInt(shipping_status);
                dest.writeInt(shipping_prod_weight);
                dest.writeDouble(final_sell_price);
                dest.writeDouble(final_buy_price);
                dest.writeString(bulk_sell_price);
                dest.writeInt(final_wastage);
                dest.writeInt(final_discount_present_cat_price);
                dest.writeInt(gross_profit);
                dest.writeString(images);
                dest.writeString(modifier_name);
                dest.writeInt(return_day);
                dest.writeInt(status);
                dest.writeString(action_type);
                dest.writeInt(in_stock);
                dest.writeInt(country);
                dest.writeString(rrp);
                dest.writeInt(is_integrated);
                dest.writeInt(is_sync);
                dest.writeString(created_at);
                dest.writeString(updated_at);
                dest.writeInt(is_veg);
                dest.writeInt(inventory_tag_id);
                dest.writeInt(product_type);
                dest.writeInt(product_moq);
                dest.writeInt(short_num);
                dest.writeInt(parent_cate_id);
                dest.writeString(cat_name);
                dest.writeInt(parent_cat_id);
                dest.writeString(breadcrumb);
                dest.writeString(taggs);
                dest.writeString(image);
                dest.writeString(color);
                dest.writeString(mSelectedAllergy);
                dest.writeInt(menu_level);
                dest.writeInt(order_by);
                dest.writeInt(taxable);
                dest.writeInt(is_misc);
                dest.writeInt(is_like);
                dest.writeInt(isCart);
                dest.writeInt(offer_id);
                dest.writeInt(discount_type);
                dest.writeString(discount_amount);
                dest.writeFloat(stars);
                dest.writeTypedList(modifier_list);
                dest.writeTypedList(product_addon);
                dest.writeTypedList(related_products);
                dest.writeTypedList(allergies_list);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel in) {
                    return new DataBean(in);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };

            public List<ProductAddonBean> getProduct_addon() {
                return product_addon;
            }

            public void setProduct_addon(List<ProductAddonBean> product_addon) {
                this.product_addon = product_addon;
            }

            public List<AllergiesListBean> getAllergies_list() {
                return allergies_list;
            }

            public void setAllergies_list(List<AllergiesListBean> allergies_list) {
                this.allergies_list = allergies_list;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUnique_code() {
                return unique_code;
            }

            public void setUnique_code(String unique_code) {
                this.unique_code = unique_code;
            }

            public String getProd_guid() {
                return prod_guid;
            }

            public void setProd_guid(String prod_guid) {
                this.prod_guid = prod_guid;
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

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getProduct_description() {
                return product_description;
            }

            public void setProduct_description(String product_description) {
                this.product_description = product_description;
            }

            public int getTotal_quantity() {
                return total_quantity;
            }

            public void setTotal_quantity(int total_quantity) {
                this.total_quantity = total_quantity;
            }

            public Object getMeasurement_unit_id() {
                return measurement_unit_id;
            }

            public void setMeasurement_unit_id(Object measurement_unit_id) {
                this.measurement_unit_id = measurement_unit_id;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public Object getGroup_id() {
                return group_id;
            }

            public void setGroup_id(Object group_id) {
                this.group_id = group_id;
            }

            public int getModifier_id() {
                return modifier_id;
            }

            public void setModifier_id(int modifier_id) {
                this.modifier_id = modifier_id;
            }

            public Object getRelated_product_ids() {
                return related_product_ids;
            }

            public void setRelated_product_ids(Object related_product_ids) {
                this.related_product_ids = related_product_ids;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public Object getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(Object supplier_id) {
                this.supplier_id = supplier_id;
            }

            public int getTakeaway_with_tax() {
                return takeaway_with_tax;
            }

            public void setTakeaway_with_tax(int takeaway_with_tax) {
                this.takeaway_with_tax = takeaway_with_tax;
            }

            public String getTax_id() {
                return tax_id;
            }

            public void setTax_id(String tax_id) {
                this.tax_id = tax_id;
            }

            public int getTax_type() {
                return tax_type;
            }

            public void setTax_type(int tax_type) {
                this.tax_type = tax_type;
            }

            public String getDelivery_type() {
                return delivery_type;
            }

            public void setDelivery_type(String delivery_type) {
                this.delivery_type = delivery_type;
            }

            public String getService_charges() {
                return service_charges;
            }

            public void setService_charges(String service_charges) {
                this.service_charges = service_charges;
            }

            public String getProduct_tags() {
                return product_tags;
            }

            public void setProduct_tags(String product_tags) {
                this.product_tags = product_tags;
            }

            public String getProduct_availability() {
                return product_availability;
            }

            public void setProduct_availability(String product_availability) {
                this.product_availability = product_availability;
            }

            public int getShipping_status() {
                return shipping_status;
            }

            public void setShipping_status(int shipping_status) {
                this.shipping_status = shipping_status;
            }

            public int getShipping_prod_weight() {
                return shipping_prod_weight;
            }

            public void setShipping_prod_weight(int shipping_prod_weight) {
                this.shipping_prod_weight = shipping_prod_weight;
            }

            public Object getShipping_prod_unit() {
                return shipping_prod_unit;
            }

            public void setShipping_prod_unit(Object shipping_prod_unit) {
                this.shipping_prod_unit = shipping_prod_unit;
            }

            public double getFinal_sell_price() {
                return final_sell_price;
            }

            public void setFinal_sell_price(double final_sell_price) {
                this.final_sell_price = final_sell_price;
            }

            public double getFinal_buy_price() {
                return final_buy_price;
            }

            public void setFinal_buy_price(double final_buy_price) {
                this.final_buy_price = final_buy_price;
            }

            public String getBulk_sell_price() {
                return bulk_sell_price;
            }

            public void setBulk_sell_price(String bulk_sell_price) {
                this.bulk_sell_price = bulk_sell_price;
            }

            public Object getBulk_sell_qty() {
                return bulk_sell_qty;
            }

            public void setBulk_sell_qty(Object bulk_sell_qty) {
                this.bulk_sell_qty = bulk_sell_qty;
            }

            public int getFinal_wastage() {
                return final_wastage;
            }

            public void setFinal_wastage(int final_wastage) {
                this.final_wastage = final_wastage;
            }

            public int getFinal_discount_present_cat_price() {
                return final_discount_present_cat_price;
            }

            public void setFinal_discount_present_cat_price(int final_discount_present_cat_price) {
                this.final_discount_present_cat_price = final_discount_present_cat_price;
            }

            public int getGross_profit() {
                return gross_profit;
            }

            public void setGross_profit(int gross_profit) {
                this.gross_profit = gross_profit;
            }

            public Object getDomain_name() {
                return domain_name;
            }

            public void setDomain_name(Object domain_name) {
                this.domain_name = domain_name;
            }

            public Object getMeta_description() {
                return meta_description;
            }

            public void setMeta_description(Object meta_description) {
                this.meta_description = meta_description;
            }

            public Object getMeta_name() {
                return meta_name;
            }

            public void setMeta_name(Object meta_name) {
                this.meta_name = meta_name;
            }

            public Object getMeta_keyword() {
                return meta_keyword;
            }

            public void setMeta_keyword(Object meta_keyword) {
                this.meta_keyword = meta_keyword;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getModifier_name() {
                return modifier_name;
            }

            public void setModifier_name(String modifier_name) {
                this.modifier_name = modifier_name;
            }

            public Object getModifier_value() {
                return modifier_value;
            }

            public void setModifier_value(Object modifier_value) {
                this.modifier_value = modifier_value;
            }

            public int getReturn_day() {
                return return_day;
            }

            public void setReturn_day(int return_day) {
                this.return_day = return_day;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAction_type() {
                return action_type;
            }

            public void setAction_type(String action_type) {
                this.action_type = action_type;
            }

            public Object getBarcode() {
                return barcode;
            }

            public void setBarcode(Object barcode) {
                this.barcode = barcode;
            }

            public Object getBarcode_type() {
                return barcode_type;
            }

            public void setBarcode_type(Object barcode_type) {
                this.barcode_type = barcode_type;
            }

            public int getIn_stock() {
                return in_stock;
            }

            public void setIn_stock(int in_stock) {
                this.in_stock = in_stock;
            }

            public int getCountry() {
                return country;
            }

            public void setCountry(int country) {
                this.country = country;
            }

            public String getRrp() {
                return rrp;
            }

            public void setRrp(String rrp) {
                this.rrp = rrp;
            }

            public Object getProduct_option() {
                return product_option;
            }

            public void setProduct_option(Object product_option) {
                this.product_option = product_option;
            }

            public Object getProduct_link() {
                return product_link;
            }

            public void setProduct_link(Object product_link) {
                this.product_link = product_link;
            }

            public int getIs_integrated() {
                return is_integrated;
            }

            public void setIs_integrated(int is_integrated) {
                this.is_integrated = is_integrated;
            }

            public int getIs_sync() {
                return is_sync;
            }

            public void setIs_sync(int is_sync) {
                this.is_sync = is_sync;
            }

            public Object getDeleted_at() {
                return deleted_at;
            }

            public void setDeleted_at(Object deleted_at) {
                this.deleted_at = deleted_at;
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

            public Object getChoose_addon_num() {
                return choose_addon_num;
            }

            public void setChoose_addon_num(Object choose_addon_num) {
                this.choose_addon_num = choose_addon_num;
            }

            public Object getFree_addon_num() {
                return free_addon_num;
            }

            public void setFree_addon_num(Object free_addon_num) {
                this.free_addon_num = free_addon_num;
            }

            public int getIs_veg() {
                return is_veg;
            }

            public void setIs_veg(int is_veg) {
                this.is_veg = is_veg;
            }

            public int getInventory_tag_id() {
                return inventory_tag_id;
            }

            public void setInventory_tag_id(int inventory_tag_id) {
                this.inventory_tag_id = inventory_tag_id;
            }

            public int getProduct_type() {
                return product_type;
            }

            public void setProduct_type(int product_type) {
                this.product_type = product_type;
            }

            public int getProduct_moq() {
                return product_moq;
            }

            public void setProduct_moq(int product_moq) {
                this.product_moq = product_moq;
            }

            public int getShort_num() {
                return short_num;
            }

            public void setShort_num(int short_num) {
                this.short_num = short_num;
            }

            public int getParent_cate_id() {
                return parent_cate_id;
            }

            public void setParent_cate_id(int parent_cate_id) {
                this.parent_cate_id = parent_cate_id;
            }

            public Object getProduct_place() {
                return product_place;
            }

            public void setProduct_place(Object product_place) {
                this.product_place = product_place;
            }

            public Object getKitchen_name() {
                return kitchen_name;
            }

            public void setKitchen_name(Object kitchen_name) {
                this.kitchen_name = kitchen_name;
            }

            public Object getProduct_length() {
                return product_length;
            }

            public void setProduct_length(Object product_length) {
                this.product_length = product_length;
            }

            public Object getProduct_width() {
                return product_width;
            }

            public void setProduct_width(Object product_width) {
                this.product_width = product_width;
            }

            public Object getProduct_height() {
                return product_height;
            }

            public void setProduct_height(Object product_height) {
                this.product_height = product_height;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public int getParent_cat_id() {
                return parent_cat_id;
            }

            public void setParent_cat_id(int parent_cat_id) {
                this.parent_cat_id = parent_cat_id;
            }

            public Object getSwoope_cat_id() {
                return swoope_cat_id;
            }

            public void setSwoope_cat_id(Object swoope_cat_id) {
                this.swoope_cat_id = swoope_cat_id;
            }

            public String getBreadcrumb() {
                return breadcrumb;
            }

            public void setBreadcrumb(String breadcrumb) {
                this.breadcrumb = breadcrumb;
            }

            public String getTaggs() {
                return taggs;
            }

            public void setTaggs(String taggs) {
                this.taggs = taggs;
            }

            public Object getProduct_feature() {
                return product_feature;
            }

            public void setProduct_feature(Object product_feature) {
                this.product_feature = product_feature;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getmSelectedAllergy() {
                return mSelectedAllergy;
            }

            public void setmSelectedAllergy(String mSelectedAllergy) {
                this.mSelectedAllergy = mSelectedAllergy;
            }

            public int getMenu_level() {
                return menu_level;
            }

            public void setMenu_level(int menu_level) {
                this.menu_level = menu_level;
            }

            public int getOrder_by() {
                return order_by;
            }

            public void setOrder_by(int order_by) {
                this.order_by = order_by;
            }

            public int getTaxable() {
                return taxable;
            }

            public void setTaxable(int taxable) {
                this.taxable = taxable;
            }

            public int getIs_misc() {
                return is_misc;
            }

            public void setIs_misc(int is_misc) {
                this.is_misc = is_misc;
            }

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }

            public int getIsCart() {
                return isCart;
            }

            public void setIsCart(int isCart) {
                this.isCart = isCart;
            }

            public int getOffer_id() {
                return offer_id;
            }

            public void setOffer_id(int offer_id) {
                this.offer_id = offer_id;
            }

            public int getDiscount_type() {
                return discount_type;
            }

            public void setDiscount_type(int discount_type) {
                this.discount_type = discount_type;
            }

            public String getDiscount_amount() {
                return discount_amount;
            }

            public void setDiscount_amount(String discount_amount) {
                this.discount_amount = discount_amount;
            }

            public ParentCategoryNameBean getParent_category_name() {
                return parent_category_name;
            }

            public void setParent_category_name(ParentCategoryNameBean parent_category_name) {
                this.parent_category_name = parent_category_name;
            }

            public List<ModifierListBean> getModifier_list() {
                return modifier_list;
            }

            public void setModifier_list(List<ModifierListBean> modifier_list) {
                this.modifier_list = modifier_list;
            }

            public List<RelatedProductsBean> getRelated_products() {
                return related_products;
            }

            public void setRelated_products(List<RelatedProductsBean> related_products) {
                this.related_products = related_products;
            }

            public float getStars() {
                return stars;
            }

            public void setStars(float stars) {
                this.stars = stars;
            }

            public static class ParentCategoryNameBean {
                /**
                 * id : 63
                 * cat_name : Beer & Spirits
                 * parent_cat_id : 0
                 * merchant_id : 15
                 * venue_id : 2020040808042515
                 * swoope_cat_id : null
                 * breadcrumb : 0
                 * taggs : Beer
                 * product_feature : null
                 * status : 1
                 * is_integrated : 0
                 * image : Beer & Spirits09042020074135.png
                 * color : 7164e6
                 * menu_level : 1
                 * order_by : 1
                 * taxable : 1
                 * created_at : 2020-04-09 07:41:35
                 * updated_at : 2020-04-28 06:55:54
                 * short_num : 5
                 * is_misc : 0
                 */

                private int id;
                private String cat_name;
                private int parent_cat_id;
                private int merchant_id;
                private String venue_id;
                private Object swoope_cat_id;
                private String breadcrumb;
                private String taggs;
                private Object product_feature;
                private int status;
                private int is_integrated;
                private String image;
                private String color;
                private int menu_level;
                private int order_by;
                private int taxable;
                private String created_at;
                private String updated_at;
                private int short_num;
                private int is_misc;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCat_name() {
                    return cat_name;
                }

                public void setCat_name(String cat_name) {
                    this.cat_name = cat_name;
                }

                public int getParent_cat_id() {
                    return parent_cat_id;
                }

                public void setParent_cat_id(int parent_cat_id) {
                    this.parent_cat_id = parent_cat_id;
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

                public Object getSwoope_cat_id() {
                    return swoope_cat_id;
                }

                public void setSwoope_cat_id(Object swoope_cat_id) {
                    this.swoope_cat_id = swoope_cat_id;
                }

                public String getBreadcrumb() {
                    return breadcrumb;
                }

                public void setBreadcrumb(String breadcrumb) {
                    this.breadcrumb = breadcrumb;
                }

                public String getTaggs() {
                    return taggs;
                }

                public void setTaggs(String taggs) {
                    this.taggs = taggs;
                }

                public Object getProduct_feature() {
                    return product_feature;
                }

                public void setProduct_feature(Object product_feature) {
                    this.product_feature = product_feature;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getIs_integrated() {
                    return is_integrated;
                }

                public void setIs_integrated(int is_integrated) {
                    this.is_integrated = is_integrated;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public int getMenu_level() {
                    return menu_level;
                }

                public void setMenu_level(int menu_level) {
                    this.menu_level = menu_level;
                }

                public int getOrder_by() {
                    return order_by;
                }

                public void setOrder_by(int order_by) {
                    this.order_by = order_by;
                }

                public int getTaxable() {
                    return taxable;
                }

                public void setTaxable(int taxable) {
                    this.taxable = taxable;
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

                public int getShort_num() {
                    return short_num;
                }

                public void setShort_num(int short_num) {
                    this.short_num = short_num;
                }

                public int getIs_misc() {
                    return is_misc;
                }

                public void setIs_misc(int is_misc) {
                    this.is_misc = is_misc;
                }
            }

            public static class ModifierListBean implements Parcelable {
                /**
                 * id : 918
                 * product_id : 807
                 * venueId : null
                 * modifier_name : 750ML
                 * variant_type : null
                 * sku : null
                 * weight : 0
                 * buy_price : 0.00
                 * sell_price : 10.00
                 * rrp : 0.00
                 * barcode : null
                 * quantity : 100
                 * quantity_per_case : 1
                 * moq : 10
                 * bulk_selling_price : 0.00
                 * bulk_selling_qty : null
                 * modifier_length : null
                 * modifier_width : null
                 * modifier_height : null
                 * modifier_images : null
                 * product_barcode_image : null
                 * product_qrcode_image : null
                 * modifier_status : 1
                 * created_at : 2020-04-23 15:31:29
                 * updated_at : 2020-06-18 08:21:41
                 * addon_max_qty : 0
                 * order_num : 0
                 * group_qty : []
                 */

                private int id;
                private int product_id;
                private Object venueId;
                private String modifier_name;
                private Object variant_type;
                private Object sku;
                private String weight;
                private String buy_price;
                private String sell_price;
                private String rrp;
                private Object barcode;
                private int quantity;
                private int quantity_per_case;
                private int moq;
                private String bulk_selling_price;
                private Object bulk_selling_qty;
                private Object modifier_length;
                private Object modifier_width;
                private Object modifier_height;
                private Object modifier_images;
                private Object product_barcode_image;
                private Object product_qrcode_image;
                private int modifier_status;
                private String created_at;
                private String updated_at;
                private int addon_max_qty;
                private int order_num;
                private List<?> group_qty;
                private boolean isSelected = false;
                private CartBean cart;

                protected ModifierListBean(Parcel in) {
                    id = in.readInt();
                    product_id = in.readInt();
                    modifier_name = in.readString();
                    weight = in.readString();
                    buy_price = in.readString();
                    sell_price = in.readString();
                    rrp = in.readString();
                    quantity = in.readInt();
                    quantity_per_case = in.readInt();
                    moq = in.readInt();
                    bulk_selling_price = in.readString();
                    modifier_status = in.readInt();
                    created_at = in.readString();
                    updated_at = in.readString();
                    addon_max_qty = in.readInt();
                    order_num = in.readInt();
                    isSelected = in.readInt() == 1;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeInt(product_id);
                    dest.writeString(modifier_name);
                    dest.writeString(weight);
                    dest.writeString(buy_price);
                    dest.writeString(sell_price);
                    dest.writeString(rrp);
                    dest.writeInt(quantity);
                    dest.writeInt(quantity_per_case);
                    dest.writeInt(moq);
                    dest.writeString(bulk_selling_price);
                    dest.writeInt(modifier_status);
                    dest.writeString(created_at);
                    dest.writeString(updated_at);
                    dest.writeInt(addon_max_qty);
                    dest.writeInt(order_num);
                    dest.writeInt(isSelected ? 1 : 0);
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                public static final Creator<ModifierListBean> CREATOR = new Creator<ModifierListBean>() {
                    @Override
                    public ModifierListBean createFromParcel(Parcel in) {
                        return new ModifierListBean(in);
                    }

                    @Override
                    public ModifierListBean[] newArray(int size) {
                        return new ModifierListBean[size];
                    }
                };

                public CartBean getCart() {
                    return cart;
                }

                public void setCart(CartBean cart) {
                    this.cart = cart;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public Object getVenueId() {
                    return venueId;
                }

                public void setVenueId(Object venueId) {
                    this.venueId = venueId;
                }

                public String getModifier_name() {
                    return modifier_name;
                }

                public void setModifier_name(String modifier_name) {
                    this.modifier_name = modifier_name;
                }

                public Object getVariant_type() {
                    return variant_type;
                }

                public void setVariant_type(Object variant_type) {
                    this.variant_type = variant_type;
                }

                public Object getSku() {
                    return sku;
                }

                public void setSku(Object sku) {
                    this.sku = sku;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public String getBuy_price() {
                    return buy_price;
                }

                public void setBuy_price(String buy_price) {
                    this.buy_price = buy_price;
                }

                public String getSell_price() {
                    return sell_price;
                }

                public void setSell_price(String sell_price) {
                    this.sell_price = sell_price;
                }

                public String getRrp() {
                    return rrp;
                }

                public void setRrp(String rrp) {
                    this.rrp = rrp;
                }

                public Object getBarcode() {
                    return barcode;
                }

                public void setBarcode(Object barcode) {
                    this.barcode = barcode;
                }

                public int getQuantity() {
                    return quantity;
                }

                public void setQuantity(int quantity) {
                    this.quantity = quantity;
                }

                public int getQuantity_per_case() {
                    return quantity_per_case;
                }

                public void setQuantity_per_case(int quantity_per_case) {
                    this.quantity_per_case = quantity_per_case;
                }

                public int getMoq() {
                    return moq;
                }

                public void setMoq(int moq) {
                    this.moq = moq;
                }

                public String getBulk_selling_price() {
                    return bulk_selling_price;
                }

                public void setBulk_selling_price(String bulk_selling_price) {
                    this.bulk_selling_price = bulk_selling_price;
                }

                public Object getBulk_selling_qty() {
                    return bulk_selling_qty;
                }

                public void setBulk_selling_qty(Object bulk_selling_qty) {
                    this.bulk_selling_qty = bulk_selling_qty;
                }

                public Object getModifier_length() {
                    return modifier_length;
                }

                public void setModifier_length(Object modifier_length) {
                    this.modifier_length = modifier_length;
                }

                public Object getModifier_width() {
                    return modifier_width;
                }

                public void setModifier_width(Object modifier_width) {
                    this.modifier_width = modifier_width;
                }

                public Object getModifier_height() {
                    return modifier_height;
                }

                public void setModifier_height(Object modifier_height) {
                    this.modifier_height = modifier_height;
                }

                public Object getModifier_images() {
                    return modifier_images;
                }

                public void setModifier_images(Object modifier_images) {
                    this.modifier_images = modifier_images;
                }

                public Object getProduct_barcode_image() {
                    return product_barcode_image;
                }

                public void setProduct_barcode_image(Object product_barcode_image) {
                    this.product_barcode_image = product_barcode_image;
                }

                public Object getProduct_qrcode_image() {
                    return product_qrcode_image;
                }

                public void setProduct_qrcode_image(Object product_qrcode_image) {
                    this.product_qrcode_image = product_qrcode_image;
                }

                public int getModifier_status() {
                    return modifier_status;
                }

                public void setModifier_status(int modifier_status) {
                    this.modifier_status = modifier_status;
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

                public int getAddon_max_qty() {
                    return addon_max_qty;
                }

                public void setAddon_max_qty(int addon_max_qty) {
                    this.addon_max_qty = addon_max_qty;
                }

                public int getOrder_num() {
                    return order_num;
                }

                public void setOrder_num(int order_num) {
                    this.order_num = order_num;
                }

                public boolean isSelected() {
                    return isSelected;
                }

                public void setSelected(boolean selected) {
                    isSelected = selected;
                }

                public List<?> getGroup_qty() {
                    return group_qty;
                }

                public void setGroup_qty(List<?> group_qty) {
                    this.group_qty = group_qty;
                }

                public static class CartBean implements Serializable {
                    /**
                     * quantities : 2
                     * bulk_quantities : 0
                     * total_carts : 2
                     * isCart : 1
                     */

                    private int quantities;
                    private int bulk_quantities;
                    private int total_carts;
                    private int isCart;

                    public int getQuantities() {
                        return quantities;
                    }

                    public void setQuantities(int quantities) {
                        this.quantities = quantities;
                    }

                    public int getBulk_quantities() {
                        return bulk_quantities;
                    }

                    public void setBulk_quantities(int bulk_quantities) {
                        this.bulk_quantities = bulk_quantities;
                    }

                    public int getTotal_carts() {
                        return total_carts;
                    }

                    public void setTotal_carts(int total_carts) {
                        this.total_carts = total_carts;
                    }

                    public int getIsCart() {
                        return isCart;
                    }

                    public void setIsCart(int isCart) {
                        this.isCart = isCart;
                    }
                }

            }

            public static class ProductAddonBean implements Parcelable {
                /**
                 * id : 147
                 * product_id : 1034
                 * addon_id : 12
                 * sell_price : 0.30
                 * qty : 1
                 * created_at : 2020-06-22 11:32:36
                 * updated_at : 2020-12-29 10:39:23
                 * addon_cate_id : 19
                 * addon_sub_cate_id : 19
                 * short_num : 1
                 * choose_modifier_num : 10
                 * free_modifier_num : 1
                 * is_mandatory : 0
                 * group_id : null
                 * group_modifier_id : 0
                 * status : 1
                 * addon : {"id":12,"category_id":19,"sub_category_id":19,"name":"Cheese","sku":"Chw1","buy_price":"1.00","sell_price":"1.00","quantity":100,"venue_id":"202004271734165","image":null,"created_at":"2020-04-27 17:39:34","updated_at":"2020-04-27 17:39:34","status":1,"addon_category":{"id":19,"cat_name":"Milk Products"}}
                 */

                private int id;
                private int product_id;
                private int addon_id;
                private String sell_price;
                private int qty;
                private String created_at;
                private String updated_at;
                private int addon_cate_id;
                private int addon_sub_cate_id;
                private int short_num;
                private int choose_modifier_num;
                private int free_modifier_num;
                private int is_mandatory;
                private String group_id;
                private String group_modifier_id;
                private int status;
                private AddonBean addon;
                private int selectedQty = 0;

                protected ProductAddonBean(Parcel in) {
                    id = in.readInt();
                    product_id = in.readInt();
                    addon_id = in.readInt();
                    sell_price = in.readString();
                    qty = in.readInt();
                    created_at = in.readString();
                    updated_at = in.readString();
                    addon_cate_id = in.readInt();
                    addon_sub_cate_id = in.readInt();
                    short_num = in.readInt();
                    choose_modifier_num = in.readInt();
                    free_modifier_num = in.readInt();
                    is_mandatory = in.readInt();
                    group_id = in.readString();
                    group_modifier_id = in.readString();
                    status = in.readInt();
                    addon = in.readParcelable(AddonBean.class.getClassLoader());
                    selectedQty = in.readInt();
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeInt(product_id);
                    dest.writeInt(addon_id);
                    dest.writeString(sell_price);
                    dest.writeInt(qty);
                    dest.writeString(created_at);
                    dest.writeString(updated_at);
                    dest.writeInt(addon_cate_id);
                    dest.writeInt(addon_sub_cate_id);
                    dest.writeInt(short_num);
                    dest.writeInt(choose_modifier_num);
                    dest.writeInt(free_modifier_num);
                    dest.writeInt(is_mandatory);
                    dest.writeString(group_id);
                    dest.writeString(group_modifier_id);
                    dest.writeInt(status);
                    dest.writeParcelable(addon, flags);
                    dest.writeInt(selectedQty);
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                public static final Creator<ProductAddonBean> CREATOR = new Creator<ProductAddonBean>() {
                    @Override
                    public ProductAddonBean createFromParcel(Parcel in) {
                        return new ProductAddonBean(in);
                    }

                    @Override
                    public ProductAddonBean[] newArray(int size) {
                        return new ProductAddonBean[size];
                    }
                };

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public int getAddon_id() {
                    return addon_id;
                }

                public void setAddon_id(int addon_id) {
                    this.addon_id = addon_id;
                }

                public String getSell_price() {
                    return sell_price;
                }

                public void setSell_price(String sell_price) {
                    this.sell_price = sell_price;
                }

                public int getQty() {
                    return qty;
                }

                public void setQty(int qty) {
                    this.qty = qty;
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

                public int getAddon_cate_id() {
                    return addon_cate_id;
                }

                public void setAddon_cate_id(int addon_cate_id) {
                    this.addon_cate_id = addon_cate_id;
                }

                public int getAddon_sub_cate_id() {
                    return addon_sub_cate_id;
                }

                public void setAddon_sub_cate_id(int addon_sub_cate_id) {
                    this.addon_sub_cate_id = addon_sub_cate_id;
                }

                public int getShort_num() {
                    return short_num;
                }

                public void setShort_num(int short_num) {
                    this.short_num = short_num;
                }

                public int getChoose_modifier_num() {
                    return choose_modifier_num;
                }

                public void setChoose_modifier_num(int choose_modifier_num) {
                    this.choose_modifier_num = choose_modifier_num;
                }

                public int getFree_modifier_num() {
                    return free_modifier_num;
                }

                public void setFree_modifier_num(int free_modifier_num) {
                    this.free_modifier_num = free_modifier_num;
                }

                public int getIs_mandatory() {
                    return is_mandatory;
                }

                public void setIs_mandatory(int is_mandatory) {
                    this.is_mandatory = is_mandatory;
                }

                public String getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(String group_id) {
                    this.group_id = group_id;
                }

                public String getGroup_modifier_id() {
                    return group_modifier_id;
                }

                public void setGroup_modifier_id(String group_modifier_id) {
                    this.group_modifier_id = group_modifier_id;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public AddonBean getAddon() {
                    return addon;
                }

                public void setAddon(AddonBean addon) {
                    this.addon = addon;
                }

                public int getSelectedQty() {
                    return selectedQty;
                }

                public void setSelectedQty(int selectedQty) {
                    this.selectedQty = selectedQty;
                }

                public static class AddonBean implements Parcelable {
                    /**
                     * id : 12
                     * category_id : 19
                     * sub_category_id : 19
                     * name : Cheese
                     * sku : Chw1
                     * buy_price : 1.00
                     * sell_price : 1.00
                     * quantity : 100
                     * venue_id : 202004271734165
                     * image : null
                     * created_at : 2020-04-27 17:39:34
                     * updated_at : 2020-04-27 17:39:34
                     * status : 1
                     * addon_category : {"id":19,"cat_name":"Milk Products"}
                     */
                    private int mQtyAdded;  //// THIS IS FOR FRONT END ONLY TO DETECT ADDED QTY BYB USER
                    private int id;
                    private int category_id;
                    private int sub_category_id;
                    private String name;
                    private String sku;
                    private String buy_price;
                    private String sell_price;
                    private int quantity;
                    private String venue_id;
                    private Object image;
                    private String created_at;
                    private String updated_at;
                    private int status;
                    private AddonCategoryBean addon_category;

                    protected AddonBean(Parcel in) {
                        id = in.readInt();
                        category_id = in.readInt();
                        sub_category_id = in.readInt();
                        name = in.readString();
                        sku = in.readString();
                        buy_price = in.readString();
                        sell_price = in.readString();
                        quantity = in.readInt();
                        mQtyAdded = in.readInt();
                        venue_id = in.readString();
                        created_at = in.readString();
                        updated_at = in.readString();
                        status = in.readInt();
                        addon_category = in.readParcelable(AddonCategoryBean.class.getClassLoader());
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeInt(id);
                        dest.writeInt(category_id);
                        dest.writeInt(sub_category_id);
                        dest.writeString(name);
                        dest.writeString(sku);
                        dest.writeString(buy_price);
                        dest.writeString(sell_price);
                        dest.writeInt(quantity);
                        dest.writeInt(mQtyAdded);
                        dest.writeString(venue_id);
                        dest.writeString(created_at);
                        dest.writeString(updated_at);
                        dest.writeInt(status);
                        dest.writeParcelable(addon_category, flags);
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    public static final Creator<AddonBean> CREATOR = new Creator<AddonBean>() {
                        @Override
                        public AddonBean createFromParcel(Parcel in) {
                            return new AddonBean(in);
                        }

                        @Override
                        public AddonBean[] newArray(int size) {
                            return new AddonBean[size];
                        }
                    };

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getCategory_id() {
                        return category_id;
                    }

                    public void setCategory_id(int category_id) {
                        this.category_id = category_id;
                    }

                    public int getSub_category_id() {
                        return sub_category_id;
                    }

                    public void setSub_category_id(int sub_category_id) {
                        this.sub_category_id = sub_category_id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getSku() {
                        return sku;
                    }

                    public void setSku(String sku) {
                        this.sku = sku;
                    }

                    public String getBuy_price() {
                        return buy_price;
                    }

                    public void setBuy_price(String buy_price) {
                        this.buy_price = buy_price;
                    }

                    public String getSell_price() {
                        return sell_price;
                    }

                    public void setSell_price(String sell_price) {
                        this.sell_price = sell_price;
                    }

                    public int getQuantity() {
                        return quantity;
                    }

                    public void setQuantity(int quantity) {
                        this.quantity = quantity;
                    }

                    public int getmQtyAdded() {
                        return mQtyAdded;
                    }

                    public void setmQtyAdded(int mQtyAdded) {
                        this.mQtyAdded = mQtyAdded;
                    }

                    public String getVenue_id() {
                        return venue_id;
                    }

                    public void setVenue_id(String venue_id) {
                        this.venue_id = venue_id;
                    }

                    public Object getImage() {
                        return image;
                    }

                    public void setImage(Object image) {
                        this.image = image;
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

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public AddonCategoryBean getAddon_category() {
                        return addon_category;
                    }

                    public void setAddon_category(AddonCategoryBean addon_category) {
                        this.addon_category = addon_category;
                    }

                    public static class AddonCategoryBean implements Parcelable {
                        /**
                         * id : 19
                         * cat_name : Milk Products
                         */

                        private int id;
                        private String cat_name;

                        protected AddonCategoryBean(Parcel in) {
                            id = in.readInt();
                            cat_name = in.readString();
                        }

                        @Override
                        public void writeToParcel(Parcel dest, int flags) {
                            dest.writeInt(id);
                            dest.writeString(cat_name);
                        }

                        @Override
                        public int describeContents() {
                            return 0;
                        }

                        public static final Creator<AddonCategoryBean> CREATOR = new Creator<AddonCategoryBean>() {
                            @Override
                            public AddonCategoryBean createFromParcel(Parcel in) {
                                return new AddonCategoryBean(in);
                            }

                            @Override
                            public AddonCategoryBean[] newArray(int size) {
                                return new AddonCategoryBean[size];
                            }
                        };

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getCat_name() {
                            return cat_name;
                        }

                        public void setCat_name(String cat_name) {
                            this.cat_name = cat_name;
                        }
                    }
                }
            }

            public static class RelatedProductsBean implements Parcelable {
                /**
                 * product_id : 871
                 * final_sell_price : 2.99
                 * product_name : Hawaiian Pizza
                 * modifier_id : 1566
                 * modifier_name : 9 Inch
                 * sell_price : 2.99
                 */

                private int product_id;
                private double final_sell_price;
                private String product_name;
                private int modifier_id;
                private String modifier_name;
                private String images;
                private String sell_price;

                protected RelatedProductsBean(Parcel in) {
                    product_id = in.readInt();
                    final_sell_price = in.readDouble();
                    product_name = in.readString();
                    modifier_id = in.readInt();
                    modifier_name = in.readString();
                    sell_price = in.readString();
                    images = in.readString();
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(product_id);
                    dest.writeDouble(final_sell_price);
                    dest.writeString(product_name);
                    dest.writeInt(modifier_id);
                    dest.writeString(modifier_name);
                    dest.writeString(sell_price);
                    dest.writeString(images);
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                public static final Creator<RelatedProductsBean> CREATOR = new Creator<RelatedProductsBean>() {
                    @Override
                    public RelatedProductsBean createFromParcel(Parcel in) {
                        return new RelatedProductsBean(in);
                    }

                    @Override
                    public RelatedProductsBean[] newArray(int size) {
                        return new RelatedProductsBean[size];
                    }
                };

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public double getFinal_sell_price() {
                    return final_sell_price;
                }

                public void setFinal_sell_price(double final_sell_price) {
                    this.final_sell_price = final_sell_price;
                }

                public String getProduct_name() {
                    return product_name;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }

                public int getModifier_id() {
                    return modifier_id;
                }

                public void setModifier_id(int modifier_id) {
                    this.modifier_id = modifier_id;
                }

                public String getModifier_name() {
                    return modifier_name;
                }

                public void setModifier_name(String modifier_name) {
                    this.modifier_name = modifier_name;
                }

                public String getImages() {
                    return images;
                }

                public void setImages(String images) {
                    this.images = images;
                }

                public String getSell_price() {
                    return sell_price;
                }

                public void setSell_price(String sell_price) {
                    this.sell_price = sell_price;
                }
            }

            public static class AllergiesListBean implements Parcelable{
                /**
                 * id : 32
                 * allerges_id : 4
                 * product_id : 871
                 * allergie : {"id":4,"name":"Eggs","image":"/allergens_images/20210316064913-eggs.png","description":"Eggs are often found in cakes, some meat products, mayonnaise, mousses,\r\npasta, quiche, sauces and pastries or foods brushed or glazed with egg."}
                 */

                private int id;
                private String allerges_id;
                private int product_id;
                private AllergieBean allergie;

                protected AllergiesListBean(Parcel in) {
                    id = in.readInt();
                    allerges_id = in.readString();
                    product_id = in.readInt();
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeString(allerges_id);
                    dest.writeInt(product_id);
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                public static final Creator<AllergiesListBean> CREATOR = new Creator<AllergiesListBean>() {
                    @Override
                    public AllergiesListBean createFromParcel(Parcel in) {
                        return new AllergiesListBean(in);
                    }

                    @Override
                    public AllergiesListBean[] newArray(int size) {
                        return new AllergiesListBean[size];
                    }
                };

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAllerges_id() {
                    return allerges_id;
                }

                public void setAllerges_id(String allerges_id) {
                    this.allerges_id = allerges_id;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public AllergieBean getAllergie() {
                    return allergie;
                }

                public void setAllergie(AllergieBean allergie) {
                    this.allergie = allergie;
                }

                public static class AllergieBean implements Parcelable{
                    /**
                     * id : 4
                     * name : Eggs
                     * image : /allergens_images/20210316064913-eggs.png
                     * description : Eggs are often found in cakes, some meat products, mayonnaise, mousses,
                     pasta, quiche, sauces and pastries or foods brushed or glazed with egg.
                     */

                    private int id;
                    private String name;
                    private String image;
                    private String description;

                    protected AllergieBean(Parcel in) {
                        id = in.readInt();
                        name = in.readString();
                        image = in.readString();
                        description = in.readString();
                    }

                    public static final Creator<AllergieBean> CREATOR = new Creator<AllergieBean>() {
                        @Override
                        public AllergieBean createFromParcel(Parcel in) {
                            return new AllergieBean(in);
                        }

                        @Override
                        public AllergieBean[] newArray(int size) {
                            return new AllergieBean[size];
                        }
                    };

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        parcel.writeInt(id);
                        parcel.writeString(name);
                        parcel.writeString(image);
                        parcel.writeString(description);
                    }
                }
            }
        }
    }

}