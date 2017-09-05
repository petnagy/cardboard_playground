package com.playground.vr.vr_playground.dataset;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by petnagy on 2017. 09. 02..
 */
@Dao
public interface VrImageDao {

    @Query("SELECT * FROM images ORDER BY id")
    LiveData<List<VrImage>> getAllVrImage();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVrImage(VrImage vrImage);

    @Delete
    void deleteImageData(VrImage vrImage);
}
