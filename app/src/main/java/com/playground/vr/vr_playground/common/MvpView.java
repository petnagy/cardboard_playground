package com.playground.vr.vr_playground.common;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * Created by petnagy on 2017. 09. 01..
 */

public interface MvpView extends LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(LifecycleOwner source);
}
