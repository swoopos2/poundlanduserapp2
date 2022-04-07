package com.poundland.retail.model.requestModel;

public class DeleteSavedCardRequestModel {
    private String stripe_card_id;

    public DeleteSavedCardRequestModel(String stripe_card_id) {
        this.stripe_card_id = stripe_card_id;
    }

    public String getStripe_card_id() {
        return stripe_card_id;
    }

    public void setStripe_card_id(String stripe_card_id) {
        this.stripe_card_id = stripe_card_id;
    }
}
