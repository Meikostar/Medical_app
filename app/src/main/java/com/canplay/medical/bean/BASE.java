package com.canplay.medical.bean;

import java.util.List;

/***
 * 功能描述:
 * 作者:chenwei
 * 时间:2016/12/26
 * 版本:
 ***/
public class BASE {


//    {
//        "userId":"ccda9cf7-d871-41a5-8548-77022c54f1bf",
//            "familyAndFriendsUserId":"5c920653-14ed-435a-a83a-54d9349f78f2",
//            "nickname":"严桂宁",
//            "status":"Pending",
//            "createdDateTime":"1490050004412.87",
//            "lastModified":"1490050004412.87"
//    },
//    {
//        "userId":"5c920653-14ed-435a-a83a-54d9349f78f2",
//            "familyAndFriendsUserId":"ccda9cf7-d871-41a5-8548-77022c54f1bf",
//            "nickname":"张俊",
//            "status":"Waiting",
//            "createdDateTime":"1490050004412.87",
//            "lastModified":"1490050004412.87"
//    }
////]
//"object":{
//        "patientDeviceId":"e6192688-0b05-e711-b9e4-2c44fd9333fc",
//                "userId":"ccda9cf7-d871-41a5-8548-77022c54f1bf",
//                "userName":null,
//                "serialNo":"9999031842",
//                "deviceType":"Blood pressure monitor",
//                "deviceTypeDisplayName":"慧医-爱奥乐血压计",
//                "deviceUserId":"0",
//                "status":"Active",
//                "createdDateTime":"1489093277677.32",
//                "lastModified":"1489093277677.32"
//    },
//            "isSucceeded":true,
//            "message":"
// "reminderType":"Medicine",
//         "responseStatus":"0",
//         "responseDateTime":"xxxx"

    public Boolean isSucceeded;
    public Boolean isVerfied;
    public Boolean isCompleted;
    public String userName;
    public String serialNo;
    public String reminderType;
    public String deviceType;
    public String deviceTypeDisplayName;
    public String deviceUserId;

    public String name;
    public BASE object;
    public List<String> when;
    public String userId;
    public String Filename;
    public String patientDeviceId;
    public String familyAndFriendsUserId;
    public String reminderId;
    public String nickname;

    public String remindingFor;
    public Boolean IsSucceeded;
    public String message;
    public String version;
    public String type;
    public int total;
    public int responseStatus;
    public int numberOfUnreadMessages;

    public long createdDateTime;
    public long lastModified;
    public long responseDateTime;
    public String nextTaskDueIn;
    public String jobId;
    public String status;
    public String userFullName;


}
