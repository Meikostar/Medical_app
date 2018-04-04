package com.canplay.medical.mvp.http;



import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Righter;
import com.canplay.medical.bean.USER;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
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
    @POST("VerifyMobileNumber/")
    Observable<USER> Login(@QueryMap Map<String, String> options);

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

    @Headers({"Content-Type: application/json"})
    @POST("Flow/Register")
    Observable<BASE> righter(@Body String body);
    /**
     * 首页
     */
    @POST("Flow/v2/ReminderStatus/{name}")
    Observable<BASE> getUserdata(@Path("name") String name);

}
