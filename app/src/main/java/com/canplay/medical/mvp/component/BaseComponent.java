package com.canplay.medical.mvp.component;


import com.canplay.medical.base.AppComponent;
import com.canplay.medical.fragment.BloodRecordFragment;
import com.canplay.medical.fragment.HealthDataFragment;
import com.canplay.medical.fragment.HomeDoctorFragment;
import com.canplay.medical.fragment.HomeFragment;
import com.canplay.medical.fragment.MeasureRemindFragment;
import com.canplay.medical.fragment.RemindMedicatFragment;
import com.canplay.medical.fragment.SetFragment;
import com.canplay.medical.mvp.ActivityScope;
import com.canplay.medical.mvp.activity.account.ForgetFirstActivity;
import com.canplay.medical.mvp.activity.account.ForgetPswActivity;
import com.canplay.medical.mvp.activity.account.LoginActivity;
import com.canplay.medical.mvp.activity.MainActivity;
import com.canplay.medical.mvp.activity.account.RegisteredActivity;
import com.canplay.medical.mvp.activity.account.RegisteredSecondActivity;
import com.canplay.medical.mvp.activity.health.TakeMedicineActivity;
import com.canplay.medical.mvp.activity.home.AddBloodDataActivity;
import com.canplay.medical.mvp.activity.home.ChooseMedicalActivity;
import com.canplay.medical.mvp.activity.home.MeasureActivity;
import com.canplay.medical.mvp.activity.home.MessageActivity;
import com.canplay.medical.mvp.activity.home.SearchMedicalActivity;
import com.canplay.medical.mvp.activity.home.SmartKitActivity;
import com.canplay.medical.mvp.activity.mine.AddFriendActivity;
import com.canplay.medical.mvp.activity.mine.MineEuipmentActivity;
import com.canplay.medical.mvp.activity.mine.MineHealthCenterActivity;
import com.canplay.medical.mvp.activity.mine.MineInfoActivity;
import com.canplay.medical.mvp.activity.mine.RemindSettingActivity;

import dagger.Component;

/**
 * Created by leo on 2016/9/27.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface BaseComponent{

    void inject(LoginActivity binderActivity);
    void inject(RemindSettingActivity binderActivity);
    void inject(ForgetPswActivity binderActivity);
    void inject(ForgetFirstActivity binderActivity);
    void inject(SmartKitActivity binderActivity);
    void inject(MeasureRemindFragment binderActivity);
    void inject(MineInfoActivity binderActivity);
    void inject(SearchMedicalActivity binderActivity);
    void inject(ChooseMedicalActivity binderActivity);
    void inject(AddBloodDataActivity binderActivity);
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
