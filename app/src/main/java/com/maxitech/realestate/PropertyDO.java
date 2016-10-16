package com.maxitech.realestate;

import java.io.Serializable;

/**
 * Created by Anil.Jain on 10/16/2016.
 */

public class PropertyDO implements Serializable{

    String address,consultantid,imageurl,name,phoneNumber,propertyType,propertyid;

    public PropertyDO() {
    }

    public PropertyDO(String address, String consultantid, String imageurl, String name, String phoneNumber, String propertyType, String propertyid) {
        this.address = address;
        this.consultantid = consultantid;
        this.imageurl = imageurl;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.propertyType = propertyType;
        this.propertyid = propertyid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsultantid() {
        return consultantid;
    }

    public void setConsultantid(String consultantid) {
        this.consultantid = consultantid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid;
    }
}
