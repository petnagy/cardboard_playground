package com.playground.vr.vr_playground.inject.module;

import com.playground.vr.vr_playground.inject.PerActivity;
import com.playground.vr.vr_playground.pages.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by petnagy on 2017. 09. 01..
 */
@Module
public interface ContributeAndroidInjectorModule {

    @PerActivity
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    MainActivity contributeMainActivityInjector();
}
