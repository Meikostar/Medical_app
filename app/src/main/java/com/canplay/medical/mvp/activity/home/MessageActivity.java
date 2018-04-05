package com.canplay.medical.mvp.activity.home;

import android.view.View;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.base.BaseApplication;
import com.canplay.medical.bean.Message;
import com.canplay.medical.mvp.adapter.MessageAdapter;
import com.canplay.medical.mvp.adapter.RemindMedicatAdapter;
import com.canplay.medical.mvp.component.DaggerBaseComponent;
import com.canplay.medical.mvp.present.HomeContract;
import com.canplay.medical.mvp.present.HomePresenter;
import com.canplay.medical.view.NavigationBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息
 */
public class MessageActivity extends BaseActivity implements HomeContract.View {
    @Inject
    HomePresenter presenter;
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
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        presenter.getMessageList();
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

   private List<Message> data;
    @Override
    public <T> void toEntity(T entity) {
        data= (List<Message>) entity;
        adapter.setData(data);
    }

    @Override
    public void toNextStep(int type) {

    }

    @Override
    public void showTomast(String msg) {

    }
}
