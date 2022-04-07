package com.poundland.retail.model;

public class QR_Model {

    public String id;
    public String type;

    public QR_Model(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }
}
