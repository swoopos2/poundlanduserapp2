package com.poundland.retail.model.requestModel;

import java.util.List;

public class ProductDetailByModifierRequestModel {

    /*{
        "venue_id":"201911011148462",
        "product_id":"30",
        "offer_id":"7",
        "modifier_name":"64,Gold",
        "curr_mod":"L"
    }*/
    private String venue_id;
    private String product_id;
    private String modifier_name;
    private String curr_mod;
    private String offer_id;

    public ProductDetailByModifierRequestModel(String product_id) {
        this.product_id = product_id;
    }

    private List<String> category_id; //// home brand filter request

    public ProductDetailByModifierRequestModel(String venue_id, String product_id, String modifier_name,
                                               String curr_mod,String offer_id) {
        this.venue_id = venue_id;
        this.product_id = product_id;
        this.modifier_name = modifier_name;
        this.curr_mod = curr_mod;
        this.offer_id = offer_id;
    }


    public ProductDetailByModifierRequestModel(List<String> category_id) {
        this.category_id = category_id;
    }

    public List<String> getCategory_id() {
        return category_id;
    }

    public void setCategory_id(List<String> category_id) {
        this.category_id = category_id;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getModifier_name() {
        return modifier_name;
    }

    public void setModifier_name(String modifier_name) {
        this.modifier_name = modifier_name;
    }

    public String getCurr_mod() {
        return curr_mod;
    }

    public void setCurr_mod(String curr_mod) {
        this.curr_mod = curr_mod;
    }
}
