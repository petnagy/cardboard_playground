package com.playground.vr.vr_playground.dataset;

import android.arch.persistence.room.TypeConverter;
import android.net.Uri;

/**
 * Created by petnagy on 2017. 09. 02..
 */

public class Converters {

    @TypeConverter
    public static String fromUri(Uri uri) {
        return uri.toString();
    }

    @TypeConverter
    public static Uri fromString(String uriStr) {
        return Uri.parse(uriStr);
    }

}
