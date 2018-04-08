package com.canplay.medical.mvp.present;


import android.support.annotation.NonNull;

import com.canplay.medical.base.manager.ApiManager;
import com.canplay.medical.bean.Add;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.Medic;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.bean.Medicines;
import com.canplay.medical.bean.Mesure;
import com.canplay.medical.bean.Press;
import com.canplay.medical.bean.Record;
import com.canplay.medical.bean.Righter;
import com.canplay.medical.bean.Sug;
import com.canplay.medical.bean.Sugar;
import com.canplay.medical.bean.USER;
import com.canplay.medical.mvp.http.BaseApi;
import com.canplay.medical.net.MySubscriber;
import com.canplay.medical.util.SpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import rx.Subscription;


public class BasesPresenter implements BaseContract.Presenter {
    private Subscription subscription;

    private BaseContract.View mView;

    private BaseApi contactApi;

    @Inject
    BasesPresenter(ApiManager apiManager){
        contactApi = apiManager.createApi(BaseApi.class);
    }
    @Override
    public void getMeasureRecord(String userId, String category,String from, String take) {

        subscription = ApiManager.setSubscribe(contactApi.getMeasureRecord(userId,category,from,take), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);




            }

            @Override
            public void onNext(List<Record> entity){

                mView.toEntity(entity,0);
            }
        });
    }
    @Override
    public void getBloodPressList(final int  type, String from, String take) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getBloodPressList(userId,from,take), new MySubscriber<List<Record>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Record> entity){

                mView.toEntity(entity,type);
            }
        });
    }
    @Override
    public void addMedical(Medic med) {
        subscription = ApiManager.setSubscribe(contactApi.addMedical(med), new MySubscriber<Medicines>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Medicines entity){

                mView.toEntity(entity,0);

            }
        });
    }
    @Override
    public void getMedicineList() {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getMedicalList(userId), new MySubscriber<List<Medicine>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Medicine> entity){

                mView.toEntity(entity,0);
            }
        });
    }
    private List<Medicines> list=new ArrayList<>();

    @Override
    public void getMedicineInfo(String medicindCode) {

        subscription = ApiManager.setSubscribe(contactApi.getMedicineInfo(medicindCode), new MySubscriber<Medicines>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Medicines entity){
                list.clear();
                list.add(entity);
                mView.toEntity(list,0);
            }
        });
    }

    @Override
    public void getBloodList(final int  type, String from, String take) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getBloodList(userId,from,take), new MySubscriber<List<Sugar>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Sugar> entity){

                mView.toEntity(entity,type);
            }
        });
    }
    @Override
    public void getDayBloodRecord( String day) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getDayBloodRecord(userId,day), new MySubscriber<List<Sugar>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Sugar> entity){

                mView.toEntity(entity,0);
            }
        });
    }

    @Override
    public void getDayBloodPressRecord( String day) {
        String userId = SpUtil.getInstance().getUserId();
        subscription = ApiManager.setSubscribe(contactApi.getDayBloodPressRecord(userId,day), new MySubscriber<List<Sugar>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(List<Sugar> entity){

                mView.toEntity(entity,0);
            }
        });
    }

    @Override
    public void addMesure(Mesure base) {

        subscription = ApiManager.setSubscribe(contactApi.addMesure(base), new MySubscriber<BASE>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(BASE entity){

                mView.toEntity(entity,0);
            }
        });
    }



    @Override
    public void addBloodPress(Press base) {

        subscription = ApiManager.setSubscribe(contactApi.addBloodPress(base), new MySubscriber<Sugar>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Sugar entity){

                mView.toEntity(entity,2);
            }
        });
    }
    @Override
    public void addBloodSugar(Sug base) {

        subscription = ApiManager.setSubscribe(contactApi.addBloodSugar(base), new MySubscriber<Sugar>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);


            }

            @Override
            public void onNext(Sugar entity){

                mView.toEntity(entity,1);
            }
        });
    }

    @Override
    public void attachView(@NonNull BaseContract.View view){
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
