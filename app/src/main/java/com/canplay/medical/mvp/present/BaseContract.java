package com.canplay.medical.mvp.present;

import com.canplay.medical.base.BasePresenter;
import com.canplay.medical.base.BaseView;
import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.Medic;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.Press;
import com.canplay.medical.bean.Sug;

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
        /**
         * 添加血糖记录
         */
        void addBloodSugar(Sug base);

        /**
         * 血糖测量记录
         */
        void getBloodList(final int  type, String from, String take);

        /**
         * 指定天数血糖测量记录
         */
        void getDayBloodRecord( String day);

        /**
         * 指定天数血糖测量记录
         */
        void addBloodPress(Press base);

        /**
         * 指定天数血压测量记录
         */
        void getDayBloodPressRecord( String day);

        /**
         * 药物列表
         */
        void getMedicineList();

        /**
         * 扫描添加药物
         */
        void getMedicineInfo(String medicindCode);

        void addMedical(Medic med);
    }
}
