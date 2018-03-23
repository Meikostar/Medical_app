package com.canplay.medical.mvp.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.DATA;
import com.canplay.medical.mvp.activity.home.ChooseMedicalActivity;
import com.canplay.medical.mvp.activity.home.SmartKitActivity;
import com.canplay.medical.view.ListPopupWindow;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.POST;

/**
 * 设置提醒
 */
public class RemindSettingActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_have)
    TextView tvHave;
    @BindView(R.id.ll_box)
    LinearLayout llBox;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.lv_info)
    RegularListView lvInfo;
    @BindView(R.id.tv_frequency)
    TextView tvFrequency;
    @BindView(R.id.ll_again)
    LinearLayout llAgain;
    @BindView(R.id.tv_ring)
    TextView tvRing;
    @BindView(R.id.ll_ring)
    LinearLayout llRing;
    private ListPopupWindow popupWindow;
    private ListPopupWindow popupWindow1;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_remind_setting);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);


    }


    @Override
    public void bindEvents() {


        navigationBar.setNavigationBarListener(new NavigationBar.NavigationBarListener() {
            @Override
            public void navigationLeft() {
                finish();
            }

            @Override
            public void navigationRight() {

            }

            @Override
            public void navigationimg() {

            }
        });
        llBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RemindSettingActivity.this,SmartKitActivity.class));
            }
        });
       tvAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               startActivity(new Intent(RemindSettingActivity.this,ChooseMedicalActivity.class));
           }
       });
        llAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(line);
            }
        });
        llRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.showAsDropDown(line);
            }
        });
    }

  private List<DATA> data=new ArrayList<>();
    @Override
    public void initData() {
        DATA datas = new DATA();
        datas.content="每天";
        DATA data1 = new DATA();
        data1.content="每隔一天";
        DATA data2 = new DATA();
        data2.content="每隔两天";
        DATA data3 = new DATA();
        data3.content="每隔三天";
        data.add(datas);
        data.add(data1);
        data.add(data2);
        data.add(data3);
        popupWindow=new ListPopupWindow(this,data);
        popupWindow.setSureListener(new ListPopupWindow.ClickListener() {
            @Override
            public void clickListener(DATA menu, int poistion) {
                   tvFrequency.setText(menu.content);
            }
        });
        initRingPop();
    }

   public void initRingPop(){
       DATA datas = new DATA();
       datas.content="铃声01";
       DATA data1 = new DATA();
       data1.content="默认";
       DATA data2 = new DATA();
       data2.content="铃声02";
       data.add(datas);
       data.add(data1);
       data.add(data2);

       popupWindow1=new ListPopupWindow(this,data);
       popupWindow1.remove();
       popupWindow1.setSureListener(new ListPopupWindow.ClickListener() {
           @Override
           public void clickListener(DATA menu, int poistion) {
               tvRing.setText(menu.content);
               popupWindow1.dismiss();
           }
       });

   }

}
