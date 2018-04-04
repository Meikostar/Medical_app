package com.canplay.medical.mvp.activity.account;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.BASE;
import com.canplay.medical.mvp.activity.mine.MineInfoActivity;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.LoginContract;
import com.canplay.medical.mvp.present.LoginPresenter;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.util.gsonUtils;
import com.canplay.medical.view.ClearEditText;
import com.canplay.medical.view.TimeSelectorDialog;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class RegisteredActivity extends BaseActivity implements LoginContract.View {
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
    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.tv_psw)
    TextView tvPsw;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.et_pws)
    ClearEditText etPws;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.ll_hint)
    LinearLayout llHint;
    private Subscription mSubscription;

    private LinearLayoutManager mLinearLayoutManager;
    private int type;
    private boolean is_time;
    private boolean is_right;
    private TimeCount timeCount;
    private String jobId;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        //计时器
        timeCount = new TimeCount(60000, 1000);
        tvSave.setEnabled(false);
    }

    @Override
    public void bindEvents() {

        tvGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etPhone.getText().toString();
                if (TextUtil.isNotEmpty(phone) && phone.length() == 11) {
                    presenter.getCode(phone);

                }
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.register("","","","");
                if (is_right) {
                    Map<String, Object> pubArgs = new HashMap<>();
                    pubArgs.put("mobile", etPhone.getText().toString());
                    pubArgs.put("confirmPassword", etPws.getText().toString());
                    pubArgs.put("password", etPws.getText().toString());
                    pubArgs.put("lastname", etName.getText().toString());
                    pubArgs.put("firstname", etName.getText().toString());
                    pubArgs.put("username", etName.getText().toString());
                    String gson = gsonUtils.getGson(pubArgs);

//                    presenter.register(etName.getText().toString(),etPhone.getText().toString(),"Lxm",etPws.getText().toString());

                }else {
                    showToasts("验证码错误");
                }
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSelectorDialog selectorDialog = new TimeSelectorDialog(RegisteredActivity.this);
                selectorDialog.setDate(new Date(System.currentTimeMillis()))
                        .setBindClickListener(new TimeSelectorDialog.BindClickListener() {
                            @Override
                            public void time(String time) {
                                tvDate.setText(time);
                            }
                        });
                selectorDialog.show(findViewById(R.id.iv_back));
            }
        });
        etCode.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (s.toString().length() == 6) {
                          presenter.checkCode(jobId,s.toString());
                    }
                    if (a == 0) {
                        ++count;
                        a = 1;
                    }
                } else {
                    --count;
                    a = 0;
                }
                if (count == 4) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        etName.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (b == 0) {
                        ++count;
                        b = 1;
                    }
                } else {
                    --count;
                    b = 0;
                }
                if (count == 4) {
                    isSelect(true);
                } else {
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
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (c == 0) {
                        ++count;
                        c = 1;
                    }
                } else {
                    --count;
                    c = 0;
                }
                if (count == 4) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });
        etPws.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
            @Override
            public void afterTextChanged4ClearEdit(Editable s) {
                if (TextUtil.isNotEmpty(s.toString())) {
                    if (d == 0) {
                        ++count;
                        d = 1;
                    }
                } else {
                    --count;
                    d = 0;
                }
                if (count == 4) {
                    isSelect(true);
                } else {
                    isSelect(false);
                }
            }

            @Override
            public void changeText(CharSequence s) {

            }
        });

    }

    private int count;
    private int a;
    private int b;
    private int c;
    private int d;

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void isSelect(boolean choose) {
        if (choose) {
            if (TextUtil.isNotEmpty(tvDate.getText().toString())) {
                tvSave.setEnabled(true);

                tvSave.setBackground(getResources().getDrawable(R.drawable.login_selector));
            } else {
                tvSave.setBackground(getResources().getDrawable(R.drawable.hui_blue_rectangle));
                tvSave.setEnabled(false);
            }
        } else {
            tvSave.setBackground(getResources().getDrawable(R.drawable.hui_blue_rectangle));
            tvSave.setEnabled(false);
        }

    }

    @Override
    public <T> void toEntity(T entity) {
        timeCount.start();
        tvGetcode.setBackground(getResources().getDrawable(R.drawable.send_hui_rectangle));
        BASE base= (BASE) entity;
        jobId=base.jobId;
    }

    @Override
    public void toNextStep(int type) {
        if (type == 0) {
            etCode.setText("");
        } else if (type == 1) {
            is_right = true;
        } else if (type == 2) {
            showToasts("注册成功");
            finish();
        }
    }

    @Override
    public void showTomast(String msg) {

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
