package com.hy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Yuan on 2015/9/1.
 */
public class SpeechVO implements Serializable{
    private String id;
    private String speech;
    private Date time;
    private boolean toSow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isToSow() {
        return toSow;
    }

    public void setToSow(boolean toSow) {
        this.toSow = toSow;
    }
}
