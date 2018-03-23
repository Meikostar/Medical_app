package com.canplay.medical.mvp.activity.health;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.mvp.activity.home.BloodMeasureRecordActivity;
import com.canplay.medical.mvp.adapter.UsePlanAdapter;
import com.canplay.medical.mvp.adapter.UserRecordAdapter;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.PopView_NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 服药记录
 */
public class TakeMedicineActivity extends BaseActivity {



    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.rl_menu)
    ListView rlMenu;

    private UsePlanAdapter adapter;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_take_medicine);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        adapter = new UsePlanAdapter(this);
        adapter.setType(1);
        rlMenu.setAdapter(adapter);

    }


    @Override
    public void bindEvents() {




    }



    @Override
    public void initData() {

    }



}
