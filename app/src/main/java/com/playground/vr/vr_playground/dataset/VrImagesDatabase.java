package com.playground.vr.vr_playground.dataset;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by petnagy on 2017. 09. 02..
 */
@Database(entities = {VrImage.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class VrImagesDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "vr_database";

    public abstract VrImageDao vrImageDao();

}
