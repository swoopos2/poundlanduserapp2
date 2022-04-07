package com.poundland.retail.model.responseModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class VenueFilterDataResponseModel implements Parcelable{


    /**
     * status : true
     * message : Venue Filter Data
     * filter : [{"filter_type":"Brand","isMultiple":1,"filter_list":[{"filter_value":"3","display_name":"Levis"},{"filter_value":"8","display_name":"Wow"},{"filter_value":"11","display_name":"Pacifia"}]},{"filter_type":"Price","isMultiple":1,"filter_list":[{"filter_key":"Price","display_name":"Under £ 0 - £ 50","filter_value":"0-50","isMultiple":1},{"filter_key":"Price","display_name":"£ 50 - £ 100","filter_value":"50-100","isMultiple":1},{"filter_key":"Price","display_name":"£ 100 - £ 200","filter_value":"100-200","isMultiple":1},{"filter_key":"Price","display_name":"Over £ 200","filter_value":"200-","isMultiple":1}]},{"filter_type":"Discount","isMultiple":0,"filter_list":[{"filter_key":"Discount","display_name":"Less than 10%","filter_value":"0-10","isMultiple":0},{"filter_key":"Discount","display_name":"10% - 35%","filter_value":"10-35","isMultiple":0},{"filter_key":"Discount","display_name":"35% - 50%","filter_value":"35-50","isMultiple":0},{"filter_key":"Discount","display_name":"50% or more","filter_value":"50-","isMultiple":0}]}]
     */

    private boolean status;
    private String message;
    private List<FilterBean> filter;

    protected VenueFilterDataResponseModel(Parcel in) {
        status = in.readByte() != 0;
        message = in.readString();
        filter = in.createTypedArrayList(FilterBean.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeString(message);
        dest.writeTypedList(filter);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VenueFilterDataResponseModel> CREATOR = new Creator<VenueFilterDataResponseModel>() {
        @Override
        public VenueFilterDataResponseModel createFromParcel(Parcel in) {
            return new VenueFilterDataResponseModel(in);
        }

        @Override
        public VenueFilterDataResponseModel[] newArray(int size) {
            return new VenueFilterDataResponseModel[size];
        }
    };

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

    public List<FilterBean> getFilter() {
        return filter;
    }

    public void setFilter(List<FilterBean> filter) {
        this.filter = filter;
    }

    public static class FilterBean implements Parcelable{


        /**
         * filter_type : Brand
         * isMultiple : 1
         * filter_list : [{"filter_value":"3","display_name":"Levis"},{"filter_value":"8","display_name":"Wow"},{"filter_value":"11","display_name":"Pacifia"}]
         */

        private String filter_type;
        private int isMultiple;
        private List<FilterListBean> filter_list;

        public FilterBean() {
        }

        public FilterBean(String filter_type, int isMultiple, List<FilterListBean> filter_list) {
            this.filter_type = filter_type;
            this.isMultiple = isMultiple;
            this.filter_list = filter_list;
        }

        protected FilterBean(Parcel in) {
            filter_type = in.readString();
            isMultiple = in.readInt();
            filter_list = in.createTypedArrayList(FilterListBean.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(filter_type);
            dest.writeInt(isMultiple);
            dest.writeTypedList(filter_list);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<FilterBean> CREATOR = new Creator<FilterBean>() {
            @Override
            public FilterBean createFromParcel(Parcel in) {
                return new FilterBean(in);
            }

            @Override
            public FilterBean[] newArray(int size) {
                return new FilterBean[size];
            }
        };

        public String getFilter_type() {
            return filter_type;
        }

        public void setFilter_type(String filter_type) {
            this.filter_type = filter_type;
        }

        public int getIsMultiple() {
            return isMultiple;
        }

        public void setIsMultiple(int isMultiple) {
            this.isMultiple = isMultiple;
        }

        public List<FilterListBean> getFilter_list() {
            return filter_list;
        }

        public void setFilter_list(List<FilterListBean> filter_list) {
            this.filter_list = filter_list;
        }

        public static class FilterListBean implements Parcelable{
            /**
             * filter_key : Category
             * display_name : Basketball
             * filter_value : 19
             * isMultiple : 1
             */

            private String filter_key;
            private String filter_value;
            private String display_name;
            private String isMultiple;
            ////////////////// Extra Added key from front end//////////////////////////////////
            private boolean isChecked;

            public FilterListBean( String filter_value, String display_name) {
                this.filter_value = filter_value;
                this.display_name = display_name;
            }

            protected FilterListBean(Parcel in) {
                filter_key = in.readString();
                filter_value = in.readString();
                display_name = in.readString();
                isMultiple = in.readString();
                isChecked = in.readByte() != 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(filter_key);
                dest.writeString(filter_value);
                dest.writeString(display_name);
                dest.writeString(isMultiple);
                dest.writeByte((byte) (isChecked ? 1 : 0));
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<FilterListBean> CREATOR = new Creator<FilterListBean>() {
                @Override
                public FilterListBean createFromParcel(Parcel in) {
                    return new FilterListBean(in);
                }

                @Override
                public FilterListBean[] newArray(int size) {
                    return new FilterListBean[size];
                }
            };

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public String getFilter_key() {
                return filter_key;
            }

            public void setFilter_key(String filter_key) {
                this.filter_key = filter_key;
            }

            public String getIsMultiple() {
                return isMultiple;
            }

            public void setIsMultiple(String isMultiple) {
                this.isMultiple = isMultiple;
            }

            public String getFilter_value() {
                return filter_value;
            }

            public void setFilter_value(String filter_value) {
                this.filter_value = filter_value;
            }

            public String getDisplay_name() {
                return display_name;
            }

            public void setDisplay_name(String display_name) {
                this.display_name = display_name;
            }
        }
    }
}
