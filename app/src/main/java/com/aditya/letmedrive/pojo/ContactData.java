package com.aditya.letmedrive.pojo;

/**
 * Created by devad_000 on 09-07-2015.
 */
public class ContactData {
    private String contactName;
    private String contactNumber;

    public ContactData(String contactName, String contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public String getContactName() {
        return contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

}
