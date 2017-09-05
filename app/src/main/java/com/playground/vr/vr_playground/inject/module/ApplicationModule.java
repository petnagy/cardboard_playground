package com.playground.vr.vr_playground.inject.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.playground.vr.vr_playground.ProjectApplication;
import com.playground.vr.vr_playground.dataset.VrImageDao;
import com.playground.vr.vr_playground.dataset.VrImagesDatabase;
import com.playground.vr.vr_playground.inject.ApplicationContext;
import com.playground.vr.vr_playground.service.PreferenceService;
import com.playground.vr.vr_playground.service.SharedPreferenceService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by petnagy on 2017. 09. 01..
 */
@Module
public abstract class ApplicationModule {

    @Binds
    @ApplicationContext
    abstract Context provideContext(ProjectApplication application);

    @Singleton
    @Provides
    static VrImagesDatabase provideVrImageDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), VrImagesDatabase.class, VrImagesDatabase.DATABASE_NAME)
                .build();
    }

    @Singleton
    @Provides
    static VrImageDao provideLogDao(VrImagesDatabase appDatabase) {
        return appDatabase.vrImageDao();
    }

    @Singleton
    @Provides
    static PreferenceService provideSharedPreference(@ApplicationContext Context context) {
        return new SharedPreferenceService(context);
    }
}
