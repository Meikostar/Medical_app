package com.canplay.medical.mvp.present;


import android.support.annotation.NonNull;

import com.canplay.medical.base.manager.ApiManager;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.USER;
import com.canplay.medical.mvp.http.BaseApi;
import com.canplay.medical.net.MySubscriber;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import rx.Subscription;


public class HomePresenter implements HomeContract.Presenter {
    private Subscription subscription;

    private HomeContract.View mView;

    private BaseApi contactApi;

    @Inject
    HomePresenter(ApiManager apiManager){
        contactApi = apiManager.createApi(BaseApi.class);
    }
    @Override
    public void getUserData(final int type) {//1 用药提醒2 代表测量提醒
        String content;
        if(type==1){
            content="Medicine";
        }else {
            content="Measurement";
        }
        subscription = ApiManager.setSubscribe(contactApi.getUserdata(content), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                if(e.toString().contains("java.io.IOException:")){
                    mView.showTomast("账号或密码错误");
                }

            }

            @Override
            public void onNext(BASE entity){
                entity.type=type;
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
                mView.toNextStep(1);

            }
        });
    }
    @Override
    public void register(String name,String phone,String date,String pwd) {

    }
    @Override
    public void attachView(@NonNull HomeContract.View view){
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
