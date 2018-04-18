package com.canplay.medical.mvp.activity.home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.Medicine;
import com.canplay.medical.mvp.activity.MainActivity;
import com.canplay.medical.mvp.adapter.Medicaldapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.BaseContract;
import com.canplay.medical.mvp.present.BasesPresenter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionGen;
import com.canplay.medical.permission.PermissionSuccess;
import com.canplay.medical.view.SideLetterBars;
import com.google.zxing.client.android.activity.CaptureActivity;
import com.malinskiy.superrecyclerview.SuperRecyclerView;


import org.eclipse.jdt.internal.compiler.impl.Constant;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择药物
 */
public class ChooseMedicalActivity extends BaseActivity implements BaseContract.View{

    @Inject
    BasesPresenter presenter;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.top_view_back)
    ImageView topViewBack;
    @BindView(R.id.topview_left_layout)
    LinearLayout topviewLeftLayout;
    @BindView(R.id.tv_scan)
    ImageView tvScan;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView superRecycleView;
    @BindView(R.id.listview_all_city)
    ListView listview;
    @BindView(R.id.side_letter_bars)
    SideLetterBars mLetterBar;
    @BindView(R.id.tv_letter_overlay)
    TextView overlay;
   private Medicaldapter adapter;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_choose_medical);
        ButterKnife.bind(this);

        DaggerBaseComponent.builder().appComponent(((BaseApplication)getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);

        presenter.getMedicineList();


        llBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseMedicalActivity.this, SearchMedicalActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void bindEvents() {

        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBars.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = adapter.getLetterPosition(letter);
                listview.setSelection(position);
            }
        });
        tvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionGen.with(ChooseMedicalActivity.this)//动态权限
                        .addRequestCode(PermissionConst.REQUECT_CODE_CAMERA)
                        .permissions(Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                        .request();
            }
        });
        topViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @PermissionSuccess(requestCode = PermissionConst.REQUECT_CODE_CAMERA)
    public void requestSdcardSuccess() {
        Intent intent = new Intent(ChooseMedicalActivity.this, CaptureActivity.class);
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
    public void initData() {

    }


    private List<Medicine> data;
    @Override
    public <T> void toEntity(T entity, int type) {
        data= (List<Medicine>) entity;
        adapter=new Medicaldapter(this,data,0);
        listview.setAdapter(adapter);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
