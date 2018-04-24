// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity.home;

import com.canplay.medical.mvp.present.BasesPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MeasureActivity_MembersInjector implements MembersInjector<MeasureActivity> {
  private final Provider<BasesPresenter> presenterProvider;

  public MeasureActivity_MembersInjector(Provider<BasesPresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<MeasureActivity> create(
      Provider<BasesPresenter> presenterProvider) {
    return new MeasureActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(MeasureActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      MeasureActivity instance, Provider<BasesPresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}