package com.canplay.medical.mvp.activity.account;


import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * 忘记密码
 */
public class ForgetPswActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView navigationbar;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.tv_psw)
    TextView tvPsw;
    @BindView(R.id.et_pws)
    ClearEditText etPws;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.et_npws)
    ClearEditText etNpws;

    private Subscription mSubscription;

    private LinearLayoutManager mLinearLayoutManager;
    private int type;
    private boolean is_time;
    private TimeCount timeCount;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);

        type = getIntent().getIntExtra("type", 0);


        //计时器
        timeCount = new TimeCount(60000, 1000);
    }
    private int count;
    private int a;
    private int b;
    private int c;
    private int d;
    @Override
    public void bindEvents() {
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
        etNpws.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
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
        navigationbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

   public void isSelect(boolean choose){
       if (choose){
           tvLogin.setBackground(getResources().getDrawable(R.drawable.login_selector));
       }else {
           tvLogin.setBackground(getResources().getDrawable(R.drawable.hui_blue_rectangle));
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
