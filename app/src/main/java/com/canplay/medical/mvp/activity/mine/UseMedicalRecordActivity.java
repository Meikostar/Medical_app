package com.canplay.medical.mvp.activity.mine;


import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.mvp.adapter.recycle.PressRecordReCycleAdapter;
import com.canplay.medical.mvp.adapter.recycle.UseRecordRecycleAdapter;
import com.canplay.medical.util.LogUtils;
import com.canplay.medical.view.DivItemDecoration;
import com.canplay.medical.view.NavigationBar;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用药计划
 */
public class UseMedicalRecordActivity extends BaseActivity {


    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;

    private UseRecordRecycleAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;
    private int id;
    private int currpage=1;


    @Override
    public void initViews() {
        setContentView(R.layout.activity_blood_press_record);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        navigationBar.setNaviTitle("用药计划记录");

        navigationBar.setNavigationBarListener(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        adapter=new UseRecordRecycleAdapter(this);
        mSuperRecyclerView.setAdapter(adapter);


    }

    @Override
    public void bindEvents() {
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // mSuperRecyclerView.showMoreProgress();

//                model.getOrderHistory(id,1,TYPE_PULL_REFRESH,BloodPressRecordActivity.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSuperRecyclerView.hideMoreProgress();
                        LogUtils.e("result", "name2");
                    }
                }, 2000);
            }
        };
        mSuperRecyclerView.setRefreshListener(refreshListener);

    }
//    public void onDataLoaded( int loadType, final long ServerTotalSize, List<HISTORY> datas) {
//
//        if (loadType == TYPE_PULL_REFRESH) {
//            list.clear();
//            for (HISTORY info : datas) {
//                list.add(info);
//            }
//        } else {
//            for (HISTORY info : datas) {
//                list.add(info);
//            }
//        }
//        adapter.setDatas(list);
//        adapter.notifyDataSetChanged();
//        mSuperRecyclerView.hideMoreProgress();
//
//        if (adapter.getDatas().size() < ServerTotalSize) {
//            mSuperRecyclerView.setupMoreListener(new OnMoreListener() {
//                @Override
//                public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
//                    currpage++;
//                    mSuperRecyclerView.showMoreProgress();
//
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (adapter.getDatas().size() <= ServerTotalSize)
//                                mSuperRecyclerView.hideMoreProgress();
//                            LogUtils.e("result", "name3");
//                            model.getOrderHistory(id,currpage,TYPE_PULL_MORE,OrderHistoryRecordActivity.this);
//                        }
//                    }, 2000);
//                }
//            }, 1);
//        } else {
//            mSuperRecyclerView.hideMoreProgress();
//            mSuperRecyclerView.removeMoreListener();
//        }
//
//
//    }


    @Override
    public void initData() {

    }

}
