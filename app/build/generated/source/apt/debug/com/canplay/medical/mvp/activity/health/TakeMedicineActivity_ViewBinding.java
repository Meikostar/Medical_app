// Generated code from Butter Knife. Do not modify!
package com.canplay.medical.mvp.activity.health;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.canplay.medical.R;
import com.canplay.medical.view.NavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TakeMedicineActivity_ViewBinding implements Unbinder {
  private TakeMedicineActivity target;

  @UiThread
  public TakeMedicineActivity_ViewBinding(TakeMedicineActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TakeMedicineActivity_ViewBinding(TakeMedicineActivity target, View source) {
    this.target = target;

    target.line = Utils.findRequiredView(source, R.id.line, "field 'line'");
    target.navigationBar = Utils.findRequiredViewAsType(source, R.id.navigationBar, "field 'navigationBar'", NavigationBar.class);
    target.rlMenu = Utils.findRequiredViewAsType(source, R.id.rl_menu, "field 'rlMenu'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TakeMedicineActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.line = null;
    target.navigationBar = null;
    target.rlMenu = null;
  }
}
