package com.poundland.retail.model.responseModel;

import com.google.gson.annotations.SerializedName;

public class VenueThemeResponse {
    @SerializedName("status")
    Boolean status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public  static class Data{
        @SerializedName("header_color")
        String headerColor;
        @SerializedName("header_font_color")
        String headerFontColor;
        @SerializedName("add_to_cart_color")
        String addToCartColor;
        @SerializedName("add_to_cart_font_color")
        String addToCartFontColor;

        public String getHeaderColor() {
            return headerColor;
        }

        public void setHeaderColor(String headerColor) {
            this.headerColor = headerColor;
        }

        public String getHeaderFontColor() {
            return headerFontColor;
        }

        public void setHeaderFontColor(String headerFontColor) {
            this.headerFontColor = headerFontColor;
        }

        public String getAddToCartColor() {
            return addToCartColor;
        }

        public void setAddToCartColor(String addToCartColor) {
            this.addToCartColor = addToCartColor;
        }

        public String getAddToCartFontColor() {
            return addToCartFontColor;
        }

        public void setAddToCartFontColor(String addToCartFontColor) {
            this.addToCartFontColor = addToCartFontColor;
        }
    }
}
