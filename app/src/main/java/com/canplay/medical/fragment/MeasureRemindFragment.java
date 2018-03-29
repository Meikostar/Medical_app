package com.canplay.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.mvp.activity.home.MeasureActivity;
import com.canplay.medical.mvp.adapter.RemindMeasureAdapter;
import com.canplay.medical.mvp.adapter.RemindMedicatAdapter;
import com.canplay.medical.view.RegularListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 用药提醒
 */
public class MeasureRemindFragment extends BaseFragment {



    @BindView(R.id.rl_menu)
    ListView rlMenu;
;
    Unbinder unbinder;
    private RemindMeasureAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remind_medical, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void initView() {
        adapter=new RemindMeasureAdapter(getActivity(),null,rlMenu);
        rlMenu.setAdapter(adapter);
        adapter.setListener(new RemindMeasureAdapter.selectItemListener() {
            @Override
            public void delete(int id, int type, int poistion) {
                startActivity(new Intent(getActivity(), MeasureActivity.class));
            }
        });

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
