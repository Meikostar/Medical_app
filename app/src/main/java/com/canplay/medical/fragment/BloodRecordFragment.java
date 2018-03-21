package com.canplay.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.mvp.activity.home.DoctorDetailActivity;
import com.canplay.medical.mvp.adapter.RemindMeasureAdapter;
import com.canplay.medical.mvp.adapter.recycle.BloodRecordRecycleAdapter;
import com.canplay.medical.mvp.adapter.recycle.HomeDoctorRecycleAdapter;
import com.canplay.medical.view.DivItemDecoration;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 血压测量记录 （列表）
 */
public class BloodRecordFragment extends BaseFragment {


    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    private BloodRecordRecycleAdapter adapter;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private LinearLayoutManager mLinearLayoutManager;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;
    Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_record, null);
        unbinder = ButterKnife.bind(this, view);

        initView();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    private void initView() {

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        adapter = new BloodRecordRecycleAdapter(getActivity());
        mSuperRecyclerView.setAdapter(adapter);
//        reflash();
        // mSuperRecyclerView.setRefreshing(false);

        mSuperRecyclerView.setRefreshListener(refreshListener);



    }

    private void reflash() {
        if (mSuperRecyclerView != null) {
            //实现自动下拉刷新功能
            mSuperRecyclerView.getSwipeToRefresh().post(new Runnable() {
                @Override
                public void run() {
                    mSuperRecyclerView.setRefreshing(true);//执行下拉刷新的动画
                    refreshListener.onRefresh();//执行数据加载操作
                }
            });
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
