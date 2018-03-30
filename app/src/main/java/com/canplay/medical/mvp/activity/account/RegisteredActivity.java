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
import com.canplay.medical.mvp.activity.mine.MineInfoActivity;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.ClearEditText;
import com.canplay.medical.view.TimeSelectorDialog;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class RegisteredActivity extends BaseActivity {


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
    private TimeCount timeCount;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);

        //计时器
        timeCount = new TimeCount(60000, 1000);
    }

    @Override
    public void bindEvents() {
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
                if(TextUtil.isNotEmpty(s.toString())){
                    if(a==0){
                        ++count;
                        a=1;
                    }
                }else {
                    --count;
                    a=0;
                }
                if(count==4){
                    isSelect(true);
                }else {
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
                if(TextUtil.isNotEmpty(s.toString())){
                    if(b==0){
                        ++count;
                        b=1;
                    }
                }else {
                    --count;
                    b=0;
                }
                if(count==4){
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
                if(TextUtil.isNotEmpty(s.toString())){
                    if(c==0){
                        ++count;
                        c=1;
                    }
                }else {
                    --count;
                    c=0;
                }
                if(count==4){
                    isSelect(true);
                }else {
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
                if(TextUtil.isNotEmpty(s.toString())){
                    if(d==0){
                        ++count;
                        d=1;
                    }
                }else {
                    --count;
                    d=0;
                }
                if(count==4){
                    isSelect(true);
                }else {
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


    public void isSelect(boolean choose){
        if (choose){
            if(TextUtil.isNotEmpty(tvDate.getText().toString())){
                tvSave.setBackground(getResources().getDrawable(R.drawable.login_selector));
            }else {
                tvSave.setBackground(getResources().getDrawable(R.drawable.hui_blue_rectangle));
            }
        }else {
            tvSave.setBackground(getResources().getDrawable(R.drawable.hui_blue_rectangle));
        }

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
            tvGetcode.setEnabled(true);
        }
    }
}
