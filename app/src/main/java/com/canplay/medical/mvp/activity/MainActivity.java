package com.canplay.medical.mvp.activity;


import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.baidu.platform.comapi.map.A;
import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.bean.Province;
import com.canplay.medical.fragment.HomeDoctorFragment;
import com.canplay.medical.fragment.HomeFragment;
import com.canplay.medical.fragment.HealthDataFragment;
import com.canplay.medical.fragment.SetFragment;
import com.canplay.medical.mvp.activity.mine.MineInfoActivity;
import com.canplay.medical.mvp.adapter.FragmentViewPagerAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.component.OnChangeListener;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionGen;
import com.canplay.medical.permission.PermissionSuccess;
import com.canplay.medical.receiver.AlarmReceiver;
import com.canplay.medical.receiver.Service1;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.BottonNevgBar;
import com.canplay.medical.view.ChangeNoticeDialog;
import com.canplay.medical.view.NoScrollViewPager;
import com.google.gson.Gson;
import com.google.zxing.client.android.activity.CaptureActivity;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements HomeFragment.ScanListener , HomeContract.View {
    @Inject
    HomePresenter presenter;
    NoScrollViewPager viewpagerMain;
    BottonNevgBar bnbHome;
    private Subscription mSubscription;
    private FragmentViewPagerAdapter mainViewPagerAdapter;
    private List<Fragment> mFragments;
    private int current = 0;
    private long firstTime = 0l;
    private HomeDoctorFragment homeDoctorFragment;
    private HomeFragment homeFragment;

    private HealthDataFragment menutFragment;
    private SetFragment setFragment;
    private View line;
    private ChangeNoticeDialog dialog;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        bnbHome = (BottonNevgBar) findViewById(R.id.bnb_home);
        line =  findViewById(R.id.line);

        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        viewpagerMain = (NoScrollViewPager) findViewById(R.id.viewpager_main);
        viewpagerMain.setScanScroll(false);
        dialog=new ChangeNoticeDialog(this,line);

    }
    private void alarm() {
        startService(new Intent(MainActivity.this, Service1.class));

    }
    @Override
    public void bindEvents() {

        setViewPagerListener();
        setNevgBarChangeListener();


        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;
                if(SubscriptionBean.MENU_SCAN==bean.type){
                  dialog.show();
                }else if(SubscriptionBean.FINISH==bean.type){
                    finish();
                }


            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);

        dialog.setBindClickListener(new ChangeNoticeDialog.BindClickListener() {
            @Override
            public void teaMoney(String money) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public void initData() {
        addFragment();
        mainViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragments);
        viewpagerMain.setAdapter(mainViewPagerAdapter);
        viewpagerMain.setOffscreenPageLimit(3);//设置缓存view 的个数
        viewpagerMain.setCurrentItem(current);
        bnbHome.setSelect(current);
        if(BaseApplication.province==null){
            Observable.create(new Observable.OnSubscribe<JSONObject>() {

                @Override
                public void call(Subscriber<? super JSONObject> subscriber) {
                    JSONObject json = TextUtil.getJson("city.json",MainActivity.this);
                    subscriber.onNext(json);
                    subscriber.onCompleted();//结束异步任务

                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<JSONObject>() {
                        @Override
                        public void call(JSONObject json) {
                            JSONObject dataJson = json.optJSONObject("data");
                            BaseApplication.province = new Gson().fromJson(dataJson.toString(), Province.class);

                        }
                    });
        }
    }

    private void setViewPagerListener() {
        viewpagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bnbHome.setSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setNevgBarChangeListener() {
        bnbHome.setOnChangeListener(new OnChangeListener() {
            @Override
            public void onChagne(int currentIndex) {
                current = currentIndex;
                bnbHome.setSelect(currentIndex);alarm();
                viewpagerMain.setCurrentItem(currentIndex);
            }
        });
    }

    private void addFragment() {
        mFragments = new ArrayList<>();
        homeDoctorFragment=new HomeDoctorFragment();

        menutFragment=new HealthDataFragment();
        homeFragment=new HomeFragment();
        setFragment=new SetFragment();
        mFragments.add(homeFragment);
        mFragments.add(homeDoctorFragment);
        mFragments.add(menutFragment);
        mFragments.add(setFragment);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            RxBus.getInstance().unSub(mSubscription);
        }
    }

    @PermissionSuccess(requestCode = PermissionConst.REQUECT_CODE_CAMERA)
    public void requestSdcardSuccess() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
         /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
         * 也可以不传这个参数
         * 不传的话  默认都为默认不震动  其他都为true
         * */

        //ZxingConfig config = new ZxingConfig();
        //config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
        //config.setPlayBeep(true);//是否播放提示音
        //config.setShake(true);//是否震动
        //config.setShowAlbum(true);//是否显示相册
        //config.setShowFlashLight(true);//是否显示闪光灯
        //intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    private int REQUEST_CODE_SCAN=6;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra("scan_result");
                showToasts("扫描结果为：" +content);
//                result.setText("扫描结果为：" + content);
            }
        }
    }

    @Override
    public void scanListener() {
        PermissionGen.with(MainActivity.this)//动态权限
                .addRequestCode(PermissionConst.REQUECT_CODE_CAMERA)
                .permissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
    }

    @Override
    public <T> void toEntity(T entity) {

    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
//
//    //屏蔽返回键的代码:
//    public boolean onKeyDown(int keyCode,KeyEvent event)
//    {
//        switch(keyCode)
//        {
//            case KeyEvent.KEYCODE_BACK:return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}
