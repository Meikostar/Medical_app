// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.fragment;

import com.canplay.medical.mvp.present.BasesPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BloodRecordFragment_MembersInjector
    implements MembersInjector<BloodRecordFragment> {
  private final Provider<BasesPresenter> presenterProvider;

  public BloodRecordFragment_MembersInjector(Provider<BasesPresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<BloodRecordFragment> create(
      Provider<BasesPresenter> presenterProvider) {
    return new BloodRecordFragment_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(BloodRecordFragment instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      BloodRecordFragment instance, Provider<BasesPresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}
