package com.hy.model;

public class ConfigBus {
    private int speed;
    private int volume;
    private int timbre;
    private int taskNumber;
    private int aheadTime;

    public ConfigBus() {
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getTimbre() {
        return this.timbre;
    }

    public void setTimbre(int timbre) {
        this.timbre = timbre;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public int getAheadTime() {
        return this.aheadTime;
    }

    public void setAheadTime(int aheadTime) {
        this.aheadTime = aheadTime;
    }
}
