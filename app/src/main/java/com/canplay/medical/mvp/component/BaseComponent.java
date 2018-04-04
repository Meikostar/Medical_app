package com.canplay.medical.mvp.component;


import com.canplay.medical.base.AppComponent;
import com.canplay.medical.fragment.HealthDataFragment;
import com.canplay.medical.fragment.HomeDoctorFragment;
import com.canplay.medical.fragment.SetFragment;
import com.canplay.medical.mvp.ActivityScope;
import com.canplay.medical.mvp.activity.account.LoginActivity;
import com.canplay.medical.mvp.activity.MainActivity;
import com.canplay.medical.mvp.activity.account.RegisteredActivity;

import dagger.Component;

/**
 * Created by leo on 2016/9/27.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface BaseComponent{

    void inject(LoginActivity binderActivity);
    void inject(RegisteredActivity binderActivity);
    void inject(MainActivity binderActivity);
    void inject(HealthDataFragment binderActivity);
    void inject(SetFragment binderActivity);

    void inject(HomeDoctorFragment binderActivity);


}
