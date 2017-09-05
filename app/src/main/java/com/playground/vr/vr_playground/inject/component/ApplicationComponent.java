package com.playground.vr.vr_playground.inject.component;

import android.content.Context;

import com.playground.vr.vr_playground.ProjectApplication;
import com.playground.vr.vr_playground.dataset.VrImageDao;
import com.playground.vr.vr_playground.inject.ApplicationContext;
import com.playground.vr.vr_playground.inject.module.ApplicationModule;
import com.playground.vr.vr_playground.inject.module.ContributeAndroidInjectorModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by petnagy on 2017. 09. 01..
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, ContributeAndroidInjectorModule.class})
public interface ApplicationComponent extends AndroidInjector<ProjectApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<ProjectApplication> {
    }

    @ApplicationContext
    Context getContext();

    VrImageDao getVrImageDao();
}
