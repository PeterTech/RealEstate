package com.maxitech.realestate;

/**
 * Created by Avyukt on 10/15/2016.
 */
public class DistrictDO {
    String name,code;

    public DistrictDO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DistrictDO() {

    }
    public DistrictDO(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return  name;
    }
}
