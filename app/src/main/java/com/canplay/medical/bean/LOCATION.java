package com.canplay.medical.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/19.
 */

public class LOCATION implements Serializable {


    public String name;
    public String  address ;
    public String  uid ;
    public String  time ;
    public double latui;
    public double weidu;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLatui() {
        return latui;
    }

    public void setLatui(double latui) {
        this.latui = latui;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

