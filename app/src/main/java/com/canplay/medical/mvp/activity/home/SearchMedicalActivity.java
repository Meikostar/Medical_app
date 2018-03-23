package com.canplay.medical.mvp.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseActivity;
import com.canplay.medical.bean.ORDER;
import com.canplay.medical.mvp.adapter.Medicaldapter;
import com.canplay.medical.mvp.adapter.SearchMedicalAdapter;
import com.canplay.medical.view.ClearEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择药物search
 */
public class SearchMedicalActivity extends BaseActivity {


    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.top_view_back)
    ImageView topViewBack;
    @BindView(R.id.topview_left_layout)
    LinearLayout topviewLeftLayout;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.et_search)
    ClearEditText etSearch;
    @BindView(R.id.listview_all_city)
    ListView listview;
    private SearchMedicalAdapter adapter;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_search_medical);
        ButterKnife.bind(this);
        adapter = new SearchMedicalAdapter(this);
        listview.setAdapter(adapter);


    }

    @Override
    public void bindEvents() {
     etSearch.setOnClearEditTextListener(new ClearEditText.ClearEditTextListener() {
         @Override
         public void afterTextChanged4ClearEdit(Editable s) {

         }

         @Override
         public void changeText(CharSequence s) {

         }
     });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<ORDER> data = adapter.getData();
//                for(ORDER order:data){
//
//                }
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
