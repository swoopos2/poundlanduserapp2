package com.poundland.retail.model.responseModel;

import java.util.List;

public class GetUserLocationResponseModel {

    /**
     * status : true
     * data : [{"postcode":"B13 9PG","post_town":"BIRMINGHAM","dependant_locality":"","double_dependant_locality":"","thoroughfare":"Wake Green Road","dependant_thoroughfare":"","building_number":"56","building_name":"","sub_building_name":"","po_box":"","department_name":"","organisation_name":"","udprn":413415,"umprn":"","postcode_type":"S","su_organisation_indicator":"","delivery_point_suffix":"1A","postcode_inward":"9PG","postcode_outward":"B13","line_1":"56 Wake Green Road","line_2":"","line_3":"","premise":"56","longitude":-1.875068,"latitude":52.442894,"eastings":408589,"northings":282777,"country":"England","traditional_county":"Worcestershire","administrative_county":"","postal_county":"West Midlands","county":"West Midlands","district":"Birmingham","ward":"Moseley"},{"postcode":"B13 9PG","post_town":"BIRMINGHAM","dependant_locality":"","double_dependant_locality":"","thoroughfare":"Wake Green Road","dependant_thoroughfare":"","building_number":"58","building_name":"","sub_building_name":"","po_box":"","department_name":"","organisation_name":"","udprn":413416,"umprn":"","postcode_type":"S","su_organisation_indicator":"","delivery_point_suffix":"1B","postcode_inward":"9PG","postcode_outward":"B13","line_1":"58 Wake Green Road","line_2":"","line_3":"","premise":"58","longitude":-1.875068,"latitude":52.442894,"eastings":408589,"northings":282777,"country":"England","traditional_county":"Worcestershire","administrative_county":"","postal_county":"West Midlands","county":"West Midlands","district":"Birmingham","ward":"Moseley"},{"postcode":"B13 9PG","post_town":"BIRMINGHAM","dependant_locality":"","double_dependant_locality":"","thoroughfare":"Wake Green Road","dependant_thoroughfare":"","building_number":"60","building_name":"","sub_building_name":"","po_box":"","department_name":"","organisation_name":"","udprn":413417,"umprn":"","postcode_type":"S","su_organisation_indicator":"","delivery_point_suffix":"1D","postcode_inward":"9PG","postcode_outward":"B13","line_1":"60 Wake Green Road","line_2":"","line_3":"","premise":"60","longitude":-1.875068,"latitude":52.442894,"eastings":408589,"northings":282777,"country":"England","traditional_county":"Worcestershire","administrative_county":"","postal_county":"West Midlands","county":"West Midlands","district":"Birmingham","ward":"Moseley"},{"postcode":"B13 9PG","post_town":"BIRMINGHAM","dependant_locality":"","double_dependant_locality":"","thoroughfare":"Wake Green Road","dependant_thoroughfare":"","building_number":"62","building_name":"","sub_building_name":"","po_box":"","department_name":"","organisation_name":"","udprn":413414,"umprn":"","postcode_type":"S","su_organisation_indicator":"","delivery_point_suffix":"1E","postcode_inward":"9PG","postcode_outward":"B13","line_1":"62 Wake Green Road","line_2":"","line_3":"","premise":"62","longitude":-1.875068,"latitude":52.442894,"eastings":408589,"northings":282777,"country":"England","traditional_county":"Worcestershire","administrative_county":"","postal_county":"West Midlands","county":"West Midlands","district":"Birmingham","ward":"Moseley"},{"postcode":"B13 9PG","post_town":"BIRMINGHAM","dependant_locality":"","double_dependant_locality":"","thoroughfare":"Wake Green Road","dependant_thoroughfare":"","building_number":"64","building_name":"","sub_building_name":"","po_box":"","department_name":"","organisation_name":"","udprn":413418,"umprn":"","postcode_type":"S","su_organisation_indicator":"","delivery_point_suffix":"1F","postcode_inward":"9PG","postcode_outward":"B13","line_1":"64 Wake Green Road","line_2":"","line_3":"","premise":"64","longitude":-1.875068,"latitude":52.442894,"eastings":408589,"northings":282777,"country":"England","traditional_county":"Worcestershire","administrative_county":"","postal_county":"West Midlands","county":"West Midlands","district":"Birmingham","ward":"Moseley"}]
     */

    private boolean status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * postcode : B13 9PG
         * post_town : BIRMINGHAM
         * dependant_locality :
         * double_dependant_locality :
         * thoroughfare : Wake Green Road
         * dependant_thoroughfare :
         * building_number : 56
         * building_name :
         * sub_building_name :
         * po_box :
         * department_name :
         * organisation_name :
         * udprn : 413415
         * umprn :
         * postcode_type : S
         * su_organisation_indicator :
         * delivery_point_suffix : 1A
         * postcode_inward : 9PG
         * postcode_outward : B13
         * line_1 : 56 Wake Green Road
         * line_2 :
         * line_3 :
         * premise : 56
         * longitude : -1.875068
         * latitude : 52.442894
         * eastings : 408589
         * northings : 282777
         * country : England
         * traditional_county : Worcestershire
         * administrative_county :
         * postal_county : West Midlands
         * county : West Midlands
         * district : Birmingham
         * ward : Moseley
         */

        private String postcode;
        private String post_town;
        private String dependant_locality;
        private String double_dependant_locality;
        private String thoroughfare;
        private String dependant_thoroughfare;
        private String building_number;
        private String building_name;
        private String sub_building_name;
        private String po_box;
        private String department_name;
        private String organisation_name;
        private int udprn;
        private String umprn;
        private String postcode_type;
        private String su_organisation_indicator;
        private String delivery_point_suffix;
        private String postcode_inward;
        private String postcode_outward;
        private String line_1;
        private String line_2;
        private String line_3;
        private String premise;
        private double longitude;
        private double latitude;
        private int eastings;
        private int northings;
        private String country;
        private String traditional_county;
        private String administrative_county;
        private String postal_county;
        private String county;
        private String district;
        private String ward;

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getPost_town() {
            return post_town;
        }

        public void setPost_town(String post_town) {
            this.post_town = post_town;
        }

        public String getDependant_locality() {
            return dependant_locality;
        }

        public void setDependant_locality(String dependant_locality) {
            this.dependant_locality = dependant_locality;
        }

        public String getDouble_dependant_locality() {
            return double_dependant_locality;
        }

        public void setDouble_dependant_locality(String double_dependant_locality) {
            this.double_dependant_locality = double_dependant_locality;
        }

        public String getThoroughfare() {
            return thoroughfare;
        }

        public void setThoroughfare(String thoroughfare) {
            this.thoroughfare = thoroughfare;
        }

        public String getDependant_thoroughfare() {
            return dependant_thoroughfare;
        }

        public void setDependant_thoroughfare(String dependant_thoroughfare) {
            this.dependant_thoroughfare = dependant_thoroughfare;
        }

        public String getBuilding_number() {
            return building_number;
        }

        public void setBuilding_number(String building_number) {
            this.building_number = building_number;
        }

        public String getBuilding_name() {
            return building_name;
        }

        public void setBuilding_name(String building_name) {
            this.building_name = building_name;
        }

        public String getSub_building_name() {
            return sub_building_name;
        }

        public void setSub_building_name(String sub_building_name) {
            this.sub_building_name = sub_building_name;
        }

        public String getPo_box() {
            return po_box;
        }

        public void setPo_box(String po_box) {
            this.po_box = po_box;
        }

        public String getDepartment_name() {
            return department_name;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public String getOrganisation_name() {
            return organisation_name;
        }

        public void setOrganisation_name(String organisation_name) {
            this.organisation_name = organisation_name;
        }

        public int getUdprn() {
            return udprn;
        }

        public void setUdprn(int udprn) {
            this.udprn = udprn;
        }

        public String getUmprn() {
            return umprn;
        }

        public void setUmprn(String umprn) {
            this.umprn = umprn;
        }

        public String getPostcode_type() {
            return postcode_type;
        }

        public void setPostcode_type(String postcode_type) {
            this.postcode_type = postcode_type;
        }

        public String getSu_organisation_indicator() {
            return su_organisation_indicator;
        }

        public void setSu_organisation_indicator(String su_organisation_indicator) {
            this.su_organisation_indicator = su_organisation_indicator;
        }

        public String getDelivery_point_suffix() {
            return delivery_point_suffix;
        }

        public void setDelivery_point_suffix(String delivery_point_suffix) {
            this.delivery_point_suffix = delivery_point_suffix;
        }

        public String getPostcode_inward() {
            return postcode_inward;
        }

        public void setPostcode_inward(String postcode_inward) {
            this.postcode_inward = postcode_inward;
        }

        public String getPostcode_outward() {
            return postcode_outward;
        }

        public void setPostcode_outward(String postcode_outward) {
            this.postcode_outward = postcode_outward;
        }

        public String getLine_1() {
            return line_1;
        }

        public void setLine_1(String line_1) {
            this.line_1 = line_1;
        }

        public String getLine_2() {
            return line_2;
        }

        public void setLine_2(String line_2) {
            this.line_2 = line_2;
        }

        public String getLine_3() {
            return line_3;
        }

        public void setLine_3(String line_3) {
            this.line_3 = line_3;
        }

        public String getPremise() {
            return premise;
        }

        public void setPremise(String premise) {
            this.premise = premise;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public int getEastings() {
            return eastings;
        }

        public void setEastings(int eastings) {
            this.eastings = eastings;
        }

        public int getNorthings() {
            return northings;
        }

        public void setNorthings(int northings) {
            this.northings = northings;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTraditional_county() {
            return traditional_county;
        }

        public void setTraditional_county(String traditional_county) {
            this.traditional_county = traditional_county;
        }

        public String getAdministrative_county() {
            return administrative_county;
        }

        public void setAdministrative_county(String administrative_county) {
            this.administrative_county = administrative_county;
        }

        public String getPostal_county() {
            return postal_county;
        }

        public void setPostal_county(String postal_county) {
            this.postal_county = postal_county;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getWard() {
            return ward;
        }

        public void setWard(String ward) {
            this.ward = ward;
        }
    }
}
