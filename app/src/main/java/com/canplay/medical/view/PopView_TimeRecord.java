package com.canplay.medical.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.canplay.medical.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by syj on 2016/11/23.
 */
public class PopView_TimeRecord extends BasePopView {


    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_press)
    TextView tvPress;
    @BindView(R.id.tv_eat)
    TextView tvEat;
    @BindView(R.id.tv_sugar)
    TextView tvSugar;


    public PopView_TimeRecord(Activity activity, int type) {
        super(activity);
        this.type = type;
    }

    public ItemCliskListeners listeners;

    public interface ItemCliskListeners {
        void clickListener(int poistion);
    }

    public void setClickListener(ItemCliskListeners listener) {
        listeners = listener;
    }

    @Override
    protected View initPopView(LayoutInflater infalter) {
        View popView = infalter.inflate(R.layout.popview_navigationbars, null);
        ButterKnife.bind(this, popView);
        tvAll.setOnClickListener(this);
        tvPress.setOnClickListener(this);
        tvEat.setOnClickListener(this);
        tvSugar.setOnClickListener(this);


        popView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return popView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                listeners.clickListener(0);
                break;
            case R.id.tv_press:
                listeners.clickListener(1);
                break;
            case R.id.tv_eat:
                listeners.clickListener(2);
                break;
            case R.id.tv_sugar:
                listeners.clickListener(3);
                break;

        }

    }

    public void setView(View view) {
        line = view;
    }


}
