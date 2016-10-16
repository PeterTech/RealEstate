package com.maxitech.realestate;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class ConsultantDO {
    String areacode;
    String citycode;
    String code;
    String districtcode;
    String name;

    public ConsultantDO() {
    }

    @Override
    public String toString() {
        return name;
    }

    public ConsultantDO(String areacode, String citycode, String code, String districtcode, String name) {
        this.areacode = areacode;
        this.citycode = citycode;
        this.code = code;
        this.districtcode = districtcode;
        this.name = name;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDistrictcode() {
        return districtcode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
