// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity;

import com.canplay.medical.mvp.present.HomePresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<HomePresenter> presenterProvider;

  public MainActivity_MembersInjector(Provider<HomePresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<MainActivity> create(Provider<HomePresenter> presenterProvider) {
    return new MainActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      MainActivity instance, Provider<HomePresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}
