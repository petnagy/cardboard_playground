package com.playground.vr.vr_playground.dataset;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

/**
 * Created by petnagy on 2017. 09. 02..
 */
@Entity(tableName = "images")
public final class VrImage {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private Uri uri;

    private int inputType;

    private boolean isAsset;

    private String fileName;

    public VrImage(Uri uri, int inputType, String fileName, boolean isAsset) {
        this.uri = uri;
        this.inputType = inputType;
        this.fileName = fileName;
        this.isAsset = isAsset;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public boolean isAsset() {
        return isAsset;
    }

    public void setAsset(boolean asset) {
        isAsset = asset;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
