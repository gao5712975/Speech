package com.hy.model;

import java.io.Serializable;

/**
 * Created by Yuan on 2015/9/2.
 */
public class Speech implements Serializable{
    private String time;
    private String carNumber;
    private String terminus;
    private String checkport;
    private String carType;
    private String carUnit;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getTerminus() {
        return terminus;
    }

    public void setTerminus(String terminus) {
        this.terminus = terminus;
    }

    public String getCheckport() {
        return checkport;
    }

    public void setCheckport(String checkport) {
        this.checkport = checkport;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarUnit() {
        return carUnit;
    }

    public void setCarUnit(String carUnit) {
        this.carUnit = carUnit;
    }
}
