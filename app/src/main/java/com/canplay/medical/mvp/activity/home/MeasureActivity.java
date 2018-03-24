package com.canplay.medical.mvp.activity.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.canplay.medical.BuildConfig;
import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseDailogManager;
import com.canplay.medical.bean.DATA;
import com.canplay.medical.mvp.adapter.TimeAddAdapter;
import com.canplay.medical.util.StringUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.HourSelector;
import com.canplay.medical.view.ListPopupWindow;
import com.canplay.medical.view.MarkaBaseDialog;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.ProgressDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 测量提醒
 */
public class MeasureActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_again)
    LinearLayout llAgain;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_ring)
    LinearLayout llRing;
    @BindView(R.id.tv_ring)
    TextView tvRing;
    @BindView(R.id.ll_project)
    LinearLayout ll_project;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.listview_all_city)
    ListView listview;

    private TimeAddAdapter adapter;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_measure_remind);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        adapter=new TimeAddAdapter(this);
        listview.setAdapter(adapter);
        initPopWind();

    }
    private int type=1;
    private int CHOOSE=6;
    @Override
    public void bindEvents() {
     tvAdd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(mPopupWindow.isShowing()){
                 mPopupWindow.dismiss();
             }else {
                 mPopupWindow.showAtLocation(line , Gravity.BOTTOM, 0, 0);
             }

         }
     });
        ll_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeasureActivity.this, ChooseProjectActivity.class);
                intent.putExtra("status",type);
                startActivityForResult(intent,CHOOSE);
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

   private Map<String ,String > map =new HashMap<>();
    private List<String> data =new ArrayList<>();
    private List<DATA> list =new ArrayList<>();
    private List<DATA> lists =new ArrayList<>();

    private ListPopupWindow popupWindow;
    private ListPopupWindow popupWindow1;
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
        DATA data4 = new DATA();
        data4.content="每隔1周";
        DATA data5 = new DATA();
        data5.content="每隔2周";
        list.add(datas);
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        popupWindow=new ListPopupWindow(this,list);
        popupWindow.setSureListener(new ListPopupWindow.ClickListener() {
            @Override
            public void clickListener(DATA menu, int poistion) {
               tvTime.setText(menu.content);
                popupWindow.dismiss();
            }
        });
        initRingPop();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==CHOOSE){
                 type = data.getIntExtra("type",1);
                if(type==1){
                    tvType.setText("血压");
                }else {
                    tvType.setText("血糖1");
                }
            }
        }
    }
    public void initRingPop(){
        DATA datas = new DATA();
        datas.content="铃声01";
        DATA data1 = new DATA();
        data1.content="默认";
        DATA data2 = new DATA();
        data2.content="铃声02";
        lists.add(datas);
        lists.add(data1);
        lists.add(data2);

        popupWindow1=new ListPopupWindow(this,lists);
        popupWindow1.remove();
        popupWindow1.setSureListener(new ListPopupWindow.ClickListener() {
            @Override
            public void clickListener(DATA menu, int poistion) {
                tvRing.setText(menu.content);
                popupWindow1.dismiss();
            }
        });

    }
    private View mView;
   private PopupWindow mPopupWindow;
   private TextView but_confirm;
   private TextView but_cancel;
   private TextView but_title;
   private HourSelector selector;

    public void initPopWind(){
        mView=View.inflate(this, R.layout.time_select, null);
        but_cancel=(TextView)mView.findViewById(R.id.but_cancel);
        but_confirm=(TextView)mView.findViewById(R.id.but_confirm);
        but_title=(TextView)mView.findViewById(R.id.but_title);
        selector=(HourSelector)mView.findViewById(R.id.selector);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        but_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selector = MeasureActivity.this.selector.getSelector();
                String time= map.get(selector);
                if(TextUtil.isEmpty(time)){
                    map.put(selector,selector);
                    data.add(selector);
                    adapter.setData(data);
                }
                mPopupWindow.dismiss();
            }
        });
        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });


    }

}
