package com.hy.model;

public class SpeechBus {
    private String time;
    private String id;
    private String carNumber;
    private String terminus;
    private String checkport;
    private String carType;
    private String carUnit;
    private String speed;
    private String volume;
    private String timbre;
    private String taskNumber;
    private String aheadTime;
    private String rulePlay;

    public String getRulePlay() {
        return rulePlay;
    }

    public void setRulePlay(String rulePlay) {
        this.rulePlay = rulePlay;
    }

    public SpeechBus() {
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTimbre() {
        return timbre;
    }

    public void setTimbre(String timbre) {
        this.timbre = timbre;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getAheadTime() {
        return aheadTime;
    }

    public void setAheadTime(String aheadTime) {
        this.aheadTime = aheadTime;
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
