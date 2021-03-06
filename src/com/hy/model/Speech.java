package com.hy.model;

import java.io.Serializable;

public class Speech implements Serializable {
    private String id;
    private String time;
    private String carNumber;
    private String terminus;
    private String checkport;
    private String carType;
    private String carUnit;

    public Speech() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getTerminus() {
        return this.terminus;
    }

    public void setTerminus(String terminus) {
        this.terminus = terminus;
    }

    public String getCheckport() {
        return this.checkport;
    }

    public void setCheckport(String checkport) {
        this.checkport = checkport;
    }

    public String getCarType() {
        return this.carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarUnit() {
        return this.carUnit;
    }

    public void setCarUnit(String carUnit) {
        this.carUnit = carUnit;
    }
}
