package com.example.demo.pojo;

import java.io.Serializable;

public class EmailRequest implements Serializable {

    private static final long serialVersionUID = 1505586152401995790L;

    private String toMail;
    private String date;

    public EmailRequest() {
    }


    public EmailRequest(String toMail, String amount, String date) {
        this.toMail = toMail;
        this.date = date;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
