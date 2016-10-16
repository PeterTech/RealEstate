package com.maxitech.realestate;

/**
 * Created by Avyukt on 10/16/2016.
 */
public class AreaDO {
    String areacode;
    String areaname;
    String citycode;
    String districtcode;

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

    public String getDistrictcode() {
        return districtcode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    @Override
    public String toString() {
        return areaname;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public AreaDO(String areacode, String areaname, String citycode, String districtcode) {
        this.areacode = areacode;
        this.areaname = areaname;
        this.citycode = citycode;
        this.districtcode = districtcode;
    }

    public AreaDO() {
    }
}
