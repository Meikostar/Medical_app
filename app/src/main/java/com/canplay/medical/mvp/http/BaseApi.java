package com.canplay.medical.mvp.http;



import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Message;
import com.canplay.medical.bean.Righter;
import com.canplay.medical.bean.USER;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;


public interface BaseApi {


    /**
     * Login
     * @param options
     * @return
     */


    @FormUrlEncoded
    @POST("Flow/Token")
    Observable<USER> Login(@FieldMap Map<String, String> options);

    /**
     * 获取验证码
     */
    @POST("Flow/v2/VerifyMobileNumber/{name}")
    Observable<BASE> getCode(@Path("name") String name);
    /**
     * 校验验证码
     */
    @POST("Flow/v2/VerifyMobileNumber/Verify/{code}/{phone}")
    Observable<BASE> checkCode(@Path("phone") String phone,@Path("code") String code);
    /**
     * 注册
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/Register")
    Observable<BASE> righter(@Body Righter body);
    /**
     * 首页
     */
    @GET("Flow/v2/ReminderStatus/{name}")
    Observable<BASE> getUserdata(@Path("name") String name);
    /**
     * messagecout
     */
    @GET("Flow/v2/Message/Count")
    Observable<BASE> getMessageCout();
    /**
     * messageList
     */
    @GET("Flow/v2/Message")
    Observable<List<Message>> getMessageList();
    /**
     * 用药提醒
     */
    @GET("Flow/v2/Reminder/{userId}/Medicine")
    Observable<List<Medicine>> MedicineRemindList(@Path("userId") String userId);
    /**
     * 测量提醒
     */
    @GET("Flow/v2/Reminder/{userId}/Measurement")
    Observable<List<Medicine>> MeasureRemindList(@Path("userId") String userId);

    /**
     * 好友列表
     */
    @GET("Flow/v2/Circle")
    Observable<List<Friend>> getFriendList();

    /**
     * 添加好友
     */
    @FormUrlEncoded
    @GET("Flow/v2/User/Search/{search}")
    Observable<Friend> searchFriend(@Path("search") String search);

    /**
     * 添加好友
     */
    @FormUrlEncoded
    @GET("Flow/v2/User/{userId}")
    Observable<Friend> getFriendInfo(@Path("userId") String userId);
}
