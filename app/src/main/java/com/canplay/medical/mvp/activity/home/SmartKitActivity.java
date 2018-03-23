package com.canplay.medical.mvp.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.Euip;
import com.canplay.medical.mvp.adapter.EuipmentAdapter;
import com.canplay.medical.mvp.adapter.ItemAdapter;
import com.canplay.medical.mvp.adapter.SmartCycAdapter;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.NoScrollGridView;
import com.canplay.medical.view.RegularListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 智能药盒
 */
public class SmartKitActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.rl_menu)
    RegularListView rlMenu;
    @BindView(R.id.grid_view)
    NoScrollGridView gridView;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    private ItemAdapter adapter;
    private SmartCycAdapter adapters;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_smart_kit);
        ButterKnife.bind(this);

        navigationBar.setNavigationBarListener(this);
        adapter= new ItemAdapter(this);
        adapters= new SmartCycAdapter(this);
        rlMenu.setAdapter(adapter);
        gridView.setAdapter(adapters);

    }

    @Override
    public void bindEvents() {
        adapters.setClickListener(new SmartCycAdapter.ItemCliks() {
            @Override
            public void getItem(Euip menu, int type) {

            }
        });
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
        tvSure.setOnClickListener(new View.OnClickListener() {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
