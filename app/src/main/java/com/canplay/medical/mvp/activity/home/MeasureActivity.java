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
import com.canplay.medical.mvp.adapter.TimeAddAdapter;
import com.canplay.medical.util.StringUtil;
import com.canplay.medical.util.TextUtil;
import com.canplay.medical.view.HourSelector;
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
    }

   private Map<String ,String > map =new HashMap<>();
    private List<String > data =new ArrayList<>();
    @Override
    public void initData() {

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
                    data.add(time);
                    adapter.setData(data);
                }
            }
        });
    }

}
