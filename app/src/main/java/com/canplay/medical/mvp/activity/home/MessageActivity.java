package com.canplay.medical.mvp.activity.home;

import android.view.View;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.Message;
import com.canplay.medical.mvp.adapter.MessageAdapter;
import com.canplay.medical.mvp.adapter.RemindMedicatAdapter;
import com.canplay.medical.view.NavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息
 */
public class MessageActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    @BindView(R.id.rl_menu)
    ListView rlMenu;


    private MessageAdapter adapter;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        adapter=new MessageAdapter(this);
        rlMenu.setAdapter(adapter);

    }


    @Override
    public void bindEvents() {
        adapter.setClickListener(new MessageAdapter.ItemCliks() {
            @Override
            public void getItem(Message menu, int type) {

            }
        });

    }


    @Override
    public void initData() {

    }


}
