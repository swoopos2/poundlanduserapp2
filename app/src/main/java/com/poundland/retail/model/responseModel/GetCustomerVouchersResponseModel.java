package com.poundland.retail.model.responseModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GetCustomerVouchersResponseModel {

    /**
     * status : true
     * message : My Voucher List.
     * voucher : {"current_page":1,"data":[{"venue_name":"Lillywhites","address_1":"24-36 Regent St, St. James's, London, UK","post_code":"SW1Y 4QF","city_name":"Wolverhampton","venue_images":"1581525313.lillywhites.jpg,,,,,","voucher_number":"20XSJC4GC81VF0BA","voucher_qrcode_image":"uploaded/voucher/qrcodeImage/7444815858132620.png","amount":"10.00","title":"Thursday special","terms_conditions":"TNC","days_available":"Mon,Tue,Wed,Thu,Fri,Sat,Sun","start_date":"2020-04-02","start_time":"00:15:00","end_date":"2020-04-30","end_time":"23:55:00"}],"first_page_url":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerVoucher?page=1","from":1,"last_page":1,"last_page_url":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerVoucher?page=1","next_page_url":null,"path":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerVoucher","per_page":10,"prev_page_url":null,"to":1,"total":1}
     */

    private boolean status;
    private String message;
    private VoucherBean voucher;

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

    public VoucherBean getVoucher() {
        return voucher;
    }

    public void setVoucher(VoucherBean voucher) {
        this.voucher = voucher;
    }

    public static class VoucherBean {
        /**
         * current_page : 1
         * data : [{"venue_name":"Lillywhites","address_1":"24-36 Regent St, St. James's, London, UK","post_code":"SW1Y 4QF","city_name":"Wolverhampton","venue_images":"1581525313.lillywhites.jpg,,,,,","voucher_number":"20XSJC4GC81VF0BA","voucher_qrcode_image":"uploaded/voucher/qrcodeImage/7444815858132620.png","amount":"10.00","title":"Thursday special","terms_conditions":"TNC","days_available":"Mon,Tue,Wed,Thu,Fri,Sat,Sun","start_date":"2020-04-02","start_time":"00:15:00","end_date":"2020-04-30","end_time":"23:55:00"}]
         * first_page_url : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerVoucher?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerVoucher?page=1
         * next_page_url : null
         * path : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerVoucher
         * per_page : 10
         * prev_page_url : null
         * to : 1
         * total : 1
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

        public static class DataBean implements Parcelable {
            /**
             * venue_name : Lillywhites
             * address_1 : 24-36 Regent St, St. James's, London, UK
             * post_code : SW1Y 4QF
             * city_name : Wolverhampton
             * venue_images : 1581525313.lillywhites.jpg,,,,,
             * voucher_number : 20XSJC4GC81VF0BA
             * voucher_qrcode_image : uploaded/voucher/qrcodeImage/7444815858132620.png
             * amount : 10.00
             * title : Thursday special
             * terms_conditions : TNC
             * days_available : Mon,Tue,Wed,Thu,Fri,Sat,Sun
             * start_date : 2020-04-02
             * start_time : 00:15:00
             * end_date : 2020-04-30
             * end_time : 23:55:00
             */

            private String venue_name;
            private String address_1;
            private String post_code;
            private String city_name;
            private String venue_images;
            private String voucher_number;
            private String voucher_qrcode_image;
            private String amount;
            private String title;
            private String terms_conditions;
            private String days_available;
            private String start_date;
            private String start_time;
            private String end_date;
            private String end_time;

            protected DataBean(Parcel in) {
                venue_name = in.readString();
                address_1 = in.readString();
                post_code = in.readString();
                city_name = in.readString();
                venue_images = in.readString();
                voucher_number = in.readString();
                voucher_qrcode_image = in.readString();
                amount = in.readString();
                title = in.readString();
                terms_conditions = in.readString();
                days_available = in.readString();
                start_date = in.readString();
                start_time = in.readString();
                end_date = in.readString();
                end_time = in.readString();
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

            public String getVenue_name() {
                return venue_name;
            }

            public void setVenue_name(String venue_name) {
                this.venue_name = venue_name;
            }

            public String getAddress_1() {
                return address_1;
            }

            public void setAddress_1(String address_1) {
                this.address_1 = address_1;
            }

            public String getPost_code() {
                return post_code;
            }

            public void setPost_code(String post_code) {
                this.post_code = post_code;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getVenue_images() {
                return venue_images;
            }

            public void setVenue_images(String venue_images) {
                this.venue_images = venue_images;
            }

            public String getVoucher_number() {
                return voucher_number;
            }

            public void setVoucher_number(String voucher_number) {
                this.voucher_number = voucher_number;
            }

            public String getVoucher_qrcode_image() {
                return voucher_qrcode_image;
            }

            public void setVoucher_qrcode_image(String voucher_qrcode_image) {
                this.voucher_qrcode_image = voucher_qrcode_image;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTerms_conditions() {
                return terms_conditions;
            }

            public void setTerms_conditions(String terms_conditions) {
                this.terms_conditions = terms_conditions;
            }

            public String getDays_available() {
                return days_available;
            }

            public void setDays_available(String days_available) {
                this.days_available = days_available;
            }

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_date() {
                return end_date;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(venue_name);
                dest.writeString(address_1);
                dest.writeString(post_code);
                dest.writeString(city_name);
                dest.writeString(venue_images);
                dest.writeString(voucher_number);
                dest.writeString(voucher_qrcode_image);
                dest.writeString(amount);
                dest.writeString(title);
                dest.writeString(terms_conditions);
                dest.writeString(days_available);
                dest.writeString(start_date);
                dest.writeString(start_time);
                dest.writeString(end_date);
                dest.writeString(end_time);
            }
        }
    }
}
