package com.poundland.retail.model;

public class TestingModel {
    private boolean status;
    private String message;
    private double earn_loyalty_points;
    private CustomerData customer;

           /* "customer": {
        "id": 1,
                "first_name": "Rahul",
                "last_name": "Kr",
                "email": "rahul.kr@gmail.com",
                "contact": "1234567890",
                "gender": "M",
                "dob": "2020-02-26",
                "pos_id": "1",
                "email_verified_at": "2022-03-29T06:35:10.000000Z",
                "loyalty_points": 27,
                "user_type": 1,
                "fire_base_id": "",
                "status": 1,
                "created_at": "2022-03-29 06:35:10",
                "updated_at": "2022-04-06 10:07:44",
                "name": "Rahul Kr"
    }*/


    public static class CustomerData {
        private int id;
        private String first_name;
        private String last_name;
        private String email;
        private String contact;
        private String gender;
        private String dob;
        private String pos_id;
        private String email_verified_at;
        private String loyalty_points;
        private int user_type;
        private String fire_base_id;
        private int status;
        private String created_at;
        private String updated_at;
        private String name;
    }
}