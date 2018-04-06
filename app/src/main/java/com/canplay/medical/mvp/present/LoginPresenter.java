package com.canplay.medical.mvp.present;


import android.support.annotation.NonNull;


import com.canplay.medical.base.manager.ApiManager;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Righter;
import com.canplay.medical.bean.USER;
import com.canplay.medical.mvp.http.BaseApi;

import com.canplay.medical.net.MySubscriber;
import com.canplay.medical.util.SpUtil;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import rx.Subscription;


public class LoginPresenter implements LoginContract.Presenter {
    private Subscription subscription;

    private LoginContract.View mView;

    private BaseApi contactApi;

    @Inject
    LoginPresenter(ApiManager apiManager){
        contactApi = apiManager.createApi(BaseApi.class);
    }
    @Override
    public void goLogin(String account, String pwd) {
        Map<String, String> params = new TreeMap<>();
        params.put("grant_type", "password");
        params.put("username", account);
        params.put("password", pwd);
        String content = params.toString();
        subscription = ApiManager.setSubscribe(contactApi.Login(params), new MySubscriber<USER>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);

                    mView.showTomast("账号或密码错误");


            }

            @Override
            public void onNext(USER entity){
                SpUtil.getInstance().putUser(entity);
                mView.toEntity(entity);
            }
        });
    }
    @Override
    public void getToken() {

    }

    @Override
    public void getCode(String phone) {
        subscription = ApiManager.setSubscribe(contactApi.getCode(phone), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                if(e.toString().contains("java.io.IOException:")){
                    mView.showTomast("账号或密码错误");
                }

            }

            @Override
            public void onNext(BASE entity){
                mView.toEntity(entity);

            }
        });
    }


    @Override
    public void checkCode(String code,String phone) {
        subscription = ApiManager.setSubscribe(contactApi.checkCode(phone,code), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.toNextStep(0);
                mView.showTomast("验证码错误");

            }

            @Override
            public void onNext(BASE entity){
               if(entity.isVerfied){
                   mView.toNextStep(1);
               }


            }
        });
    }
    @Override
    public void register(String name,String firt,String last,String pwd,String phone) {

        Righter righter = new Righter();
        righter.lastname=last;
        righter.username=name;
        righter.confirmPassword=pwd;
        righter.firstname=firt;
        righter.password=pwd;
        righter.mobile=phone;

        subscription = ApiManager.setSubscribe(contactApi.righter(righter), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.toNextStep(0);
                mView.showTomast("稍后再试");

            }

            @Override
            public void onNext(BASE entity){
                mView.toNextStep(2);

            }
        });
    }
    @Override
    public void attachView(@NonNull LoginContract.View view){
        mView = view;
    }


    @Override
    public void detachView(){
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        mView = null;
    }
}
