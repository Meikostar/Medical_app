package com.canplay.medical.mvp.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.view.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 医生详情
 */
public class DoctorDetailActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.ll_bg)
    LinearLayout llBg;
    @BindView(R.id.tv_detail)
    TextView tvDetail;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);

    }

    @Override
    public void bindEvents() {

    }


    @Override
    public void initData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
