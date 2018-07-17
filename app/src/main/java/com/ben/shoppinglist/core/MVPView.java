package com.ben.shoppinglist.core;

import android.app.Activity;

public interface MVPView {

    void launchActivity(Class<? extends Activity> cls);
    void launchActivity(Class<? extends Activity> cls, int flag);
}
