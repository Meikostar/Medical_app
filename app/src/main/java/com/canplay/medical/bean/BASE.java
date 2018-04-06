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
//]

    public Boolean isSucceeded;
    public Boolean isVerfied;
    public Boolean isCompleted;
    public String name;
    public List<String> when;
    public String userId;
    public String familyAndFriendsUserId;
    public String reminderId;
    public String nickname;

    public String remindingFor;
    public String message;
    public String version;
    public int type;
    public int total;
    public int numberOfUnreadMessages;
    public long createdDateTime;
    public long lastModified;
    public String nextTaskDueIn;
    public String jobId;
    public String status;
    public String userFullName;


}
