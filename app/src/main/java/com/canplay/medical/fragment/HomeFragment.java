package com.canplay.medical.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canplay.medical.R;
import com.canplay.medical.base.BaseFragment;
import com.canplay.medical.base.RxBus;
import com.canplay.medical.base.SubscriptionBean;
import com.canplay.medical.mvp.activity.LocationBdActivity;
import com.canplay.medical.mvp.activity.home.MeasurePlanActivity;
import com.canplay.medical.mvp.activity.home.MessageActivity;
import com.canplay.medical.mvp.activity.home.UsePlanActivity;
import com.canplay.medical.mvp.activity.mine.AddFriendActivity;
import com.canplay.medical.mvp.activity.mine.MineEuipmentActivity;
import com.canplay.medical.mvp.activity.mine.MineHealthCenterActivity;
import com.canplay.medical.mvp.adapter.HomeAdapter;
import com.canplay.medical.permission.PermissionConst;
import com.canplay.medical.permission.PermissionSuccess;
import com.canplay.medical.view.NavigationBar;
import com.canplay.medical.view.RegularListView;
import com.canplay.medical.view.banner.BannerView;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;


/**
 * Created by mykar on 17/4/10.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.iv_alarm)
    ImageView ivAlarm;
    @BindView(R.id.navigationbar_title)
    TextView navigationbarTitle;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.bannerView)
    BannerView bannerView;
    @BindView(R.id.ll_health)
    LinearLayout llHealth;
    @BindView(R.id.ll_care)
    LinearLayout llCare;
    @BindView(R.id.ll_equipment)
    LinearLayout llEquipment;
    @BindView(R.id.ll_shop)
    LinearLayout llShop;
    @BindView(R.id.lv_info)
    RegularListView lvInfo;

   public interface ScanListener{
       void scanListener();
   }
   public ScanListener listener;
    private HomeAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);

        initView();
        initListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ScanListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    private Subscription mSubscription;

    private void initListener() {
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if (bean.type == SubscriptionBean.MENU_REFASHS) {
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);

      adapter.setClickListener(new HomeAdapter.ClickListener() {
          @Override
          public void clickListener(int poistion, String id) {
              if(poistion%2==0){
                  Intent intent = new Intent(getActivity(), UsePlanActivity.class);
                  startActivity(intent);
              }else {
                  Intent intent = new Intent(getActivity(), MeasurePlanActivity.class);
                  startActivity(intent);
              }

          }
      });
        llShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LocationBdActivity.class));
            }
        });
        llEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MineEuipmentActivity.class);
                intent.putExtra("name","智能设备");
                startActivity(intent);
            }
        });
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.scanListener();
            }
        });
        ivAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
            }
        });
        llHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MineHealthCenterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        adapter=new HomeAdapter(getActivity());
        lvInfo.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }


}
