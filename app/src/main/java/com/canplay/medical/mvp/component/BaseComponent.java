package com.canplay.medical.mvp.component;


import com.canplay.medical.base.AppComponent;
import com.canplay.medical.fragment.BloodRecordFragment;
import com.canplay.medical.fragment.HealthDataFragment;
import com.canplay.medical.fragment.HomeDoctorFragment;
import com.canplay.medical.fragment.HomeFragment;
import com.canplay.medical.fragment.RemindMedicatFragment;
import com.canplay.medical.fragment.SetFragment;
import com.canplay.medical.mvp.ActivityScope;
import com.canplay.medical.mvp.activity.account.LoginActivity;
import com.canplay.medical.mvp.activity.MainActivity;
import com.canplay.medical.mvp.activity.account.RegisteredActivity;
import com.canplay.medical.mvp.activity.account.RegisteredSecondActivity;
import com.canplay.medical.mvp.activity.health.TakeMedicineActivity;
import com.canplay.medical.mvp.activity.home.MeasureActivity;
import com.canplay.medical.mvp.activity.home.MessageActivity;
import com.canplay.medical.mvp.activity.mine.AddFriendActivity;
import com.canplay.medical.mvp.activity.mine.MineEuipmentActivity;
import com.canplay.medical.mvp.activity.mine.MineHealthCenterActivity;

import dagger.Component;

/**
 * Created by leo on 2016/9/27.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface BaseComponent{

    void inject(LoginActivity binderActivity);
    void inject(MeasureActivity binderActivity);
    void inject(BloodRecordFragment binderActivity);
    void inject(TakeMedicineActivity binderActivity);
    void inject(MineEuipmentActivity binderActivity);
    void inject(AddFriendActivity binderActivity);
    void inject(MineHealthCenterActivity binderActivity);
    void inject(RemindMedicatFragment binderActivity);
    void inject(HomeFragment binderActivity);
    void inject(MessageActivity binderActivity);
    void inject(RegisteredActivity binderActivity);
    void inject(RegisteredSecondActivity binderActivity);
    void inject(MainActivity binderActivity);
    void inject(HealthDataFragment binderActivity);
    void inject(SetFragment binderActivity);

    void inject(HomeDoctorFragment binderActivity);


}
