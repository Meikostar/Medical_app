package com.canplay.medical.mvp.activity.mine;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.DATA;
import com.canplay.medical.bean.RingSelectItem;
import com.canplay.medical.mvp.activity.home.ChooseMedicalActivity;
import com.canplay.medical.mvp.activity.home.SmartKitActivity;
import com.canplay.medical.mvp.adapter.RingSelectAdapter;
import com.canplay.medical.view.ListPopupWindow;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;
import com.canplay.medical.view.RingPopupWindow;
import com.google.zxing.client.android.decode.WeacConstants;
import com.google.zxing.client.android.utils.AudioPlayer;
import com.google.zxing.client.android.utils.MyUtil;

import android.support.v4.app.LoaderManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 设置提醒
 */
public class RemindSettingActivity extends BaseActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {


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
    private RingPopupWindow popupWindow1;
    /**
     * loader Id
     */
    private static final int LOADER_ID = 1;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_remind_setting);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        LoaderManager loaderManager = getSupportLoaderManager();
        // 注册Loader
        loaderManager.initLoader(LOADER_ID, null, this);

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
                if(popupWindow1!=null){
                    popupWindow1.showAsDropDown(line);
                }

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

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // 查询内部存储音频文件
        return new CursorLoader(this,
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DATA}, null, null,
                MediaStore.Audio.Media.DISPLAY_NAME);
    }

    /**
     * 铃声选择位置
     */
    private int mPosition = 0;
    private RingSelectAdapter mSystemRingAdapter;

    /**
     * 铃声名
     */
    public  String sRingName;
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID:
                // 当为编辑闹钟状态时，铃声名为闹钟单例铃声名
                String ringName1;
                if (sRingName != null) {
                    ringName1 = sRingName;
                } else {
                    SharedPreferences share = getSharedPreferences(
                            WeacConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
                    // 当为新建闹钟状态时，铃声名为最近一次选择保存的铃声名,没有的话为默认铃声
                    ringName1 = share.getString(WeacConstants.RING_NAME,
                           "默认铃声");
                }

                // 过滤重复音频文件的Set
                HashSet<String> set = new HashSet<>();

                //  保存铃声信息的List
                List<Map<String, String>> list = new ArrayList<>();
                // 添加默认铃声
                Map<String, String> defaultRing = new HashMap<>();
                defaultRing.put(WeacConstants.RING_NAME, "默认铃声");
                defaultRing.put(WeacConstants.RING_URL, WeacConstants.DEFAULT_RING_URL);
                list.add(defaultRing);
                set.add("默认铃声");

                // 保存的铃声名为默认铃声，设置该列表的显示位置
                if ("默认铃声".equals(ringName1)) {
                    mPosition = 0;
                    RingSelectItem.getInstance().setRingPager(0);
                }

                // 添加无铃声
                Map<String, String> noRing = new HashMap<>();
                noRing.put(WeacConstants.RING_NAME, "无铃声");
                noRing.put(WeacConstants.RING_URL, WeacConstants.NO_RING_URL);
                list.add(noRing);
                set.add("无铃声");

                // 当列表中存在与保存的铃声名一致时，设置该列表的显示位置
                if ("无铃声".equals(ringName1)) {
                    mPosition = list.size() - 1;
                    RingSelectItem.getInstance().setRingPager(0);
                }

                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        // 音频文件名
                        String ringName = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        if (ringName != null) {
                            // 当过滤集合里不存在此音频文件
                            if (!set.contains(ringName)) {
                                // 添加音频文件到列表过滤同名文件
                                set.add(ringName);
                                // 去掉音频文件的扩展名
                                ringName = MyUtil.removeEx(ringName);
                                // 取得音频文件的地址
                                String ringUrl = cursor.getString(cursor
                                        .getColumnIndex(MediaStore.Audio.Media.DATA));
                                Map<String, String> map = new HashMap<>();
                                map.put(WeacConstants.RING_NAME, ringName);
                                map.put(WeacConstants.RING_URL, ringUrl);
                                list.add(map);
                                // 当列表中存在与保存的铃声名一致时，设置该列表的显示位置
                                if (ringName.equals(ringName1)) {
                                    mPosition = list.size() - 1;
                                    RingSelectItem.getInstance().setRingPager(0);
                                }
                            }
                        }
                    }
                }

                mSystemRingAdapter = new RingSelectAdapter(this, list, ringName1);
                if(popupWindow1==null){

                    popupWindow1=new RingPopupWindow(this,mSystemRingAdapter);
//                    popupWindow1.remove();
                    popupWindow1.setSureListener(new RingPopupWindow.ClickListener() {
                        @Override
                        public void clickListener(String menu, int poistion) {
                            tvRing.setText(menu);
                            popupWindow1.dismiss();
                            handler.sendMessageDelayed(handler.obtainMessage(),1200);
                        }
                    });
                }

                break;
        }
    }
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            AudioPlayer.getInstance(RemindSettingActivity.this).stop();
            return false;
        }
    });
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
