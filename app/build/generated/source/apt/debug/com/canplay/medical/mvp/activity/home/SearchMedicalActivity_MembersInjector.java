// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.canplay.medical.mvp.activity.home;

import com.canplay.medical.mvp.present.HomePresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SearchMedicalActivity_MembersInjector
    implements MembersInjector<SearchMedicalActivity> {
  private final Provider<HomePresenter> presenterProvider;

  public SearchMedicalActivity_MembersInjector(Provider<HomePresenter> presenterProvider) {
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<SearchMedicalActivity> create(
      Provider<HomePresenter> presenterProvider) {
    return new SearchMedicalActivity_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(SearchMedicalActivity instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.presenter = presenterProvider.get();
  }

  public static void injectPresenter(
      SearchMedicalActivity instance, Provider<HomePresenter> presenterProvider) {
    instance.presenter = presenterProvider.get();
  }
}
