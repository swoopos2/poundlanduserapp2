package com.poundland.retail.zzznewPoundland.model;

import java.util.List;

public class StampDataModel {
    private List<ListDataBean> ListData;

    public List<ListDataBean> getListData() {
        return ListData;
    }

    public void setListData(List<ListDataBean> ListData) {
        this.ListData = ListData;
    }

    public static class ListDataBean {
        //  String stampType; // 4 == reddemed  , 3== not redeemed , 2==scratchable , 1== empty
        /**
         * stampType : 4
         * stampNo : 1
         */

        private int stampType;
        private String stampNo;

        public int getStampType() {
            return stampType;
        }

        public void setStampType(int stampType) {
            this.stampType = stampType;
        }

        public String getStampNo() {
            return stampNo;
        }

        public void setStampNo(String stampNo) {
            this.stampNo = stampNo;
        }
    }
}
