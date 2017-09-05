package com.playground.vr.vr_playground.inject.module;

import android.arch.lifecycle.LifecycleRegistry;

import com.playground.vr.vr_playground.dataset.VrImageDao;
import com.playground.vr.vr_playground.inject.PerActivity;
import com.playground.vr.vr_playground.pages.MainActivity;
import com.playground.vr.vr_playground.pages.adapter.VrListAdapter;
import com.playground.vr.vr_playground.pages.view.MainActivityView;
import com.playground.vr.vr_playground.pages.view.MainActivityViewImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 09. 01..
 */
@Module
public class MainActivityModule {

    @PerActivity
    @Provides
    LifecycleRegistry provideLifeCycleRegistry(MainActivity activity) {
        return new LifecycleRegistry(activity);
    }

    @PerActivity
    @Provides
    VrListAdapter provideVrListAdapter(MainActivity activity, LifecycleRegistry lifecycleRegistry) {
        return new VrListAdapter(activity, lifecycleRegistry);
    }

    @PerActivity
    @Provides
    MainActivityView provideMainActivityView(VrListAdapter adapter, VrImageDao vrImageDao) {
        return new MainActivityViewImpl(adapter, vrImageDao);
    }

}
