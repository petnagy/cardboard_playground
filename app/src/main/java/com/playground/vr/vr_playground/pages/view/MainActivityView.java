package com.playground.vr.vr_playground.pages.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.playground.vr.vr_playground.common.MvpView;

/**
 * Created by petnagy on 2017. 09. 01..
 */

public interface MainActivityView extends MvpView {

    void setLifecycleOwner(LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void loadImages();

}
