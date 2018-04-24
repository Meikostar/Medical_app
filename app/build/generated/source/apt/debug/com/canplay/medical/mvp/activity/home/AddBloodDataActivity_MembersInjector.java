// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity.home;

import com.canplay.medical.mvp.present.BasesPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AddBloodDataActivity_MembersInjector
    implements MembersInjector<AddBloodDataActivity> {
  private final Provider<BasesPresenter> presenterProvider;

  public AddBloodDataActivity_MembersInjector(Provider<BasesPresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<AddBloodDataActivity> create(
      Provider<BasesPresenter> presenterProvider) {
    return new AddBloodDataActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(AddBloodDataActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      AddBloodDataActivity instance, Provider<BasesPresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}
