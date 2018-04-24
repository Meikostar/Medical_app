package com.canplay.medical.mvp.activity.account;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.bean.BaseResult;
import com.canplay.medical.bean.Recovery;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.LoginContract;
import com.canplay.medical.mvp.present.LoginPresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class ForgetFirstActivity extends BaseActivity implements LoginContract.View {
    @Inject
    LoginPresenter presenter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.tv_save)
    TextView tvSave;

    private Subscription mSubscription;

    private LinearLayoutManager mLinearLayoutManager;
    private boolean is_time;
    private boolean is_right;
    private TimeCount timeCount;
    private String jobId;
    private int type=0;
    private Recovery recovery;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_forget_first);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        //计时器
        timeCount = new TimeCount(60000, 1000);
        recovery=new Recovery();
        tvSave.setEnabled(false);
    }
   private String username;
    @Override
    public void bindEvents() {

        tvGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etPhone.getText().toString();
                if (TextUtil.isNotEmpty(username) ) {
                    presenter.getRecoveryCode(username);

                }
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtil.isNotEmpty(username)){
                    if(!username.equals(etPhone.getText().toString())){
                        showToasts("用户名已更改重新获取验证码");
                        return;
                    }
                }
                recovery.jobId=jobId;
                recovery.code=etCode.getText().toString();
                recovery.username=username;
                presenter.checkCodeRecovery(recovery);

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etCode.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if(TextUtil.isNotEmpty(s.toString())&&s.toString().length()==6){
                    isSelect(true);
                }else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        etPhone.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if(s.toString().length()<11){
                    etCode.setText("");
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
    }
    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void isSelect(boolean choose) {
        if (choose) {
                tvSave.setEnabled(true);
                tvSave.setBackground(getResources().getDrawable(R.drawable.login_selector));

        } else {
            tvSave.setBackground(getResources().getDrawable(R.drawable.hui_blue_rectangle));
            tvSave.setEnabled(false);
        }

    }

    @Override
    public <T> void toEntity(T entity,int type) {
        if(type==0){
            BaseResult base = (BaseResult) entity;
            jobId = base.object;
            timeCount.start();
            tvGetcode.setBackground(getResources().getDrawable(R.drawable.send_hui_rectangle));
        }else {
            BaseResult base = (BaseResult) entity;
            Intent intent = new Intent(ForgetFirstActivity.this, ForgetPswActivity.class);
            intent.putExtra("passwordResetToken",base.object);
            intent.putExtra("username",username);
            startActivity(intent);
            finish();
        }







    }

    @Override
    public void toNextStep(int type) {
        if (type == 0) {
            etCode.setText("");
        } else if (type == 1) {


        }
    }

    @Override
    public void showTomast(String msg) {
        showToasts(msg);
    }



    //计时器
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvGetcode.setEnabled(false);
            tvGetcode.setText(millisUntilFinished / 1000 + getString(R.string.schongxinhuoqu));
        }

        @Override
        public void onFinish() {
            is_time = false;
            tvGetcode.setText(R.string.chongxinhuoqu);
            tvGetcode.setBackground(getResources().getDrawable(R.drawable.login_selector));
            tvGetcode.setEnabled(true);
        }
    }
}
