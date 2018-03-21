package com.canplay.medical.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */


public class KPI {



    public String month;
    public String y_axis;

    public List<KPI> list;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getY_axis() {
        return y_axis;
    }

    public void setY_axis(String y_axis) {
        this.y_axis = y_axis;
    }

    public List<KPI> getList() {
        return list;
    }

    public void setList(List<KPI> list) {
        this.list = list;
    }
}
