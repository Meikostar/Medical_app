package com.canplay.medical.mvp.activity.account;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.mvp.activity.MainActivity;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.LoginContract;
import com.canplay.medical.mvp.present.LoginPresenter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionFail;
import com.canplay.medical.permission.PermissionGen;
import com.canplay.medical.permission.PermissionSuccess;

import com.canplay.medical.util.SpUtil;
import com.canplay.medical.util.StringUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.ClearEditText;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends BaseActivity implements LoginContract.View {
    @Inject
    LoginPresenter presenter;
    @BindView(R.id.tv_logo)
    ImageView tvLogo;
    @BindView(R.id.et_user)
    ClearEditText etUser;
    @BindView(R.id.et_pws)
    ClearEditText etPws;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_registered)
    TextView tvRegistered;


    @Override
    public void initViews() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);

        PermissionGen.with(this)
                .addRequestCode(PermissionConst.REQUECT_DATE)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
        String userId = SpUtil.getInstance().getUserId();
        if (TextUtil.isNotEmpty(userId)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @PermissionSuccess(requestCode = PermissionConst.REQUECT_DATE)
    public void requestSdcardSuccess() {
        BaseApplication.getInstance().locationUtil.startLocation();
    }

    @PermissionFail(requestCode = PermissionConst.REQUECT_DATE)
    public void requestSdcardFailed() {
        showToasts("获取权限失败");
    }

    private int REQUEST_CODE_SCAN =6;
    @Override
    public void bindEvents() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString();
                String password = etPws.getText().toString();

                if (TextUtil.isEmpty(user)) {
                    showToasts(getString(R.string.qingshurusjh));
                    return;
                }
                if (TextUtil.isEmpty(password)) {
                    showToasts(getString(R.string.mimanull));
                    return;
                }
                showProgress("登录中...");
                presenter.goLogin(user, password);
            }
        });


        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//忘记密码
                Intent intent=new Intent(LoginActivity.this,ForgetFirstActivity.class);
                startActivity(intent);
            }
        });

        tvRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//注册
                Intent intent=new Intent(LoginActivity.this,RegisteredActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public <T> void toEntity(T entity,int type) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        dimessProgress();
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {
        showToasts(msg);
        dimessProgress();
    }


}
