package com.canplay.medical.bean;

import java.util.List;

/***
 * 功能描述:
 * 作者:chenwei
 * 时间:2016/12/26
 * 版本:
 ***/
public class Box {
// "userId":"22ee3f4c-6393-41f6-9d92-86b23ad9a09c",
//         "error":"",
//         "batteryLevel":"100",
//         "lastModified":"1501173948713",
//         "lastSynced":"-5648688000000",
//         "planCreatedDateTime":"1498613720770",
//         "cups":[
//    {
//        "status":"1",
//            "dateTime":"1501205700000",
//            "medicines":[
//        {
//            "medicine":"辛伐他汀片",
//                "dose":"1"
//        }
//            ]
//    },


    public String userId;
    public String dose;
    public String medicine;
    public String error;
    public List<Box> cups;
    public List<Box> medicines;
    public String batteryLevel;
    public String lastModified;
    public String lastSynced;
    public long planCreatedDateTime;
    public long dateTime;
    public int  status;


}
