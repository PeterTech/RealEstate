package com.maxitech.realestate;

/**
 * Created by Avyukt on 10/15/2016.
 */
public class CityDO {

    String districtcode;
    String name;
    String citycode;

    public CityDO() {
    }

    public CityDO(String citycode, String districtcode, String name) {
        this.districtcode = districtcode;
        this.name = name;
        this.citycode = citycode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
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

    public String toString() {
        return name;
    }
}
