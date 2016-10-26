package com.byndyusoft.androidinfrastructure.utils.enumUtils;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public class EnumUtils {

    @Nullable
    static public <E extends Enum<E>> String GetSerializedNameValue(E e) {
        String value = null;
        try {
            value = e.getClass().getField(e.name()).getAnnotation(SerializedName.class).value();
        } catch (NoSuchFieldException exception) {
            exception.printStackTrace();
        }
        return value;
    }

}
