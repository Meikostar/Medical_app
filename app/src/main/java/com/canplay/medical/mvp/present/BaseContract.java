package com.canplay.medical.mvp.present;

import com.canplay.medical.base.BasePresenter;
import com.canplay.medical.base.BaseView;
import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.Mesure;

public class BaseContract {
    public    interface View extends BaseView {

//        <T> void toList(List<T> list, int type, int... refreshType);
        <T> void toEntity(T entity,int type);

        void toNextStep(int type);

        void showTomast(String msg);
    }

    public  interface Presenter extends BasePresenter<View> {

        /**
         * 测量记录
         */
        void getMeasureRecord(String a, String b,String c, String d);

        /**
         * 血压测量记录
         */
        void getBloodPressList(int  type,String from, String take);

        /**
         * 添加提醒
         */
        void addMesure(Mesure base);



    }
}
