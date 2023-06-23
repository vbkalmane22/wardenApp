package com.example.warden;

public class User {

    String date_of_apply,name,parent_contact,reason,status,usn;

    public User(){}
    public User(String date_of_apply, String name, String parent_contact, String reason, String status, String usn) {
        this.date_of_apply = date_of_apply;
        this.name = name;
        this.parent_contact = parent_contact;
        this.reason = reason;
        this.status = status;
        this.usn = usn;
    }

    public String getDate_of_apply() {
        return date_of_apply;
    }

    public void setDate_of_apply(String date_of_apply) {
        this.date_of_apply = date_of_apply;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_contact() {
        return parent_contact;
    }

    public void setParent_contact(String parent_contact) {
        this.parent_contact = parent_contact;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }
}