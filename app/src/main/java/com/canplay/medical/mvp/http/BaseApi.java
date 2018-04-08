package com.canplay.medical.mvp.http;



import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Euipt;
import com.canplay.medical.bean.Friend;
import com.canplay.medical.bean.Medic;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.bean.Message;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.Press;
import com.canplay.medical.bean.Record;
import com.canplay.medical.bean.Righter;
import com.canplay.medical.bean.Sug;
import com.canplay.medical.bean.Sugar;
import com.canplay.medical.bean.USER;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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



    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Medicine")
    Observable<Medicines> addMedical(@Body Medic body);
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

    @GET("Flow/v2/User/Search/{search}")
    Observable<List<Friend>> searchFriend(@Path("search") String search);

    /**
     * 添加好友
     */

    @GET("Flow/v2/User/{userId}")
    Observable<Friend> getFriendInfo(@Path("userId") String userId);


    /**
     * 医生列表
     */

    @GET("Flow/v2/FamilyDoctor")
    Observable<List<Friend>> getDoctorList();

    /**
     * 添加好友
     */
    @GET("Flow/api/Participant/Name/{search}")
    Observable<List<Friend> > searchDoctor(@Path("search") String search);

    /**
     * 手搜索药品
     */
    @GET("Flow/v2/MedicineCatalog/Search/{search}")
    Observable<List<Medicines> > searchMedicine(@Path("search") String search);

    /**
     * 添加好友
     */

    @GET("Flow/v2/Practitioner/{userId}")
    Observable<List<Friend> > getDoctorInfo(@Path("userId") String userId);


    /**
     * 添加好友
     */
    @DELETE("Flow/api/FamilyDoctor/{userId}")
    Observable<Friend> AddDoctor(@Path("userId") String userId);



    /**
     * 智能设备列表
     */
    @GET("Flow/v2/Device/{userId}")
    Observable<List<Euipt>> getSmartList(@Path("userId") String userId);

    /**
     * 测量记录
     */
    @GET("Flow/v2/Timeline/{userId}/{category}/{from}/{take}")
    Observable<List<Record>> getMeasureRecord(@Path("userId") String userId, @Path("category") String category,
                                              @Path("from") String from, @Path("take") String take);


    /**
     * 血压测量记录
     */
    @GET("Flow/v2/BloodPressure/{userId}/{from}/{take}")
    Observable<List<Record>> getBloodPressList(@Path("userId") String userId,
                                              @Path("from") String from, @Path("take") String take);


    /**
     * 添加测量
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Reminder")
    Observable<BASE> addMesure(@Body Mesure body);

    /**
     *  添加好友
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/Circle")
    Observable<List<BASE>> addFriend(@Body Add body);



    /**
     * 同意友
     */
    @POST("Flow/v2/Circle/Approve/{familyAndFriendsId}")
    Observable<BASE > agree(@Path("familyAndFriendsId") String familyAndFriendsId);
    /**
     * bu不同意友/移除好友关系
     */
    @DELETE("Flow/v2/Circle/Approve/{familyAndFriendsId}")
    Observable<BASE> dissAgree(@Path("familyAndFriendsId") String familyAndFriendsId);


    /**
     *  添加血糖数据
     */

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/BloodGlucose")
    Observable<Sugar> addBloodSugar(@Body Sug body);



    /**
     *  添加血压数据
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("Flow/v2/BloodPressure")
    Observable<Sugar> addBloodPress(@Body Press body);


    /**
     * 血糖数据记录
     */
    @GET("Flow/v2/BloodGlucose/{userId}/{from}/{take}")
    Observable<List<Sugar>> getBloodList(@Path("userId") String userId,
                                   @Path("from") String from, @Path("take") String take);

    /**
     *指定天数血糖数据记录
     */

    @GET("Flow/v2/BloodGlucose/{userId}/{days}")
    Observable<List<Sugar>> getDayBloodRecord(@Path("userId") String userId,
                                         @Path("days") String days);

    /**
     *指定天数血糖数据记录
     */

    @GET("Flow/v2/BloodPressure/{userId}/{days}")
    Observable<List<Sugar>> getDayBloodPressRecord(@Path("userId") String userId,
                                              @Path("days") String days);

    /**
     *药物列表
     */

    @GET("Flow/v2/Medicine/{userId}")
    Observable<List<Medicine>> getMedicalList(@Path("userId") String userId);

    /**
     *扫描添加
     */

    @GET("Flow/v2/Medicine/Verify/{meidcineCode}")
    Observable<Medicines> getMedicineInfo(@Path("meidcineCode") String meidcineCode);

}
