package com.canplay.medical.mvp.present;

import com.canplay.medical.base.BasePresenter;
import com.canplay.medical.base.BaseView;

public class LoginContract {
    public    interface View extends BaseView {

//        <T> void toList(List<T> list, int type, int... refreshType);
        <T> void toEntity(T entity);

        void toNextStep(int type);

        void showTomast(String msg);
    }

    public  interface Presenter extends BasePresenter<View> {

        /**
         * 获得联系人列表
         */
        void goLogin(String account, String pwd);

        /**
         * 获取token
         */
        void getToken();
        /**
         * 发送验证码
         */
        void getCode(String phone);
        /**
         * 校验验证码
         */
        void checkCode(String phone,String code);

        /**
         * 注册
         */
        void register(String name,String phone,String date,String pwd);


    }
}
