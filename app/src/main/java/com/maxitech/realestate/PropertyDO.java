package com.maxitech.realestate;

/**
 * Created by Anil.Jain on 10/16/2016.
 */

public class PropertyDO {

    String name,code,address,phone,consutantid,imageurl;

    public PropertyDO(String name, String code, String address, String phone, String consutantid, String imageurl) {
        this.name = name;
        this.code = code;
        this.address = address;
        this.phone = phone;
        this.consutantid = consutantid;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsutantid() {
        return consutantid;
    }

    public void setConsutantid(String consutantid) {
        this.consutantid = consutantid;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
