package com.byndyusoft.androidinfrastructure.utils.enumUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public class EnumRetrofitConverterFactory extends Converter.Factory {

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Converter<?, String> converter = null;
        if (type instanceof Class && ((Class<?>)type).isEnum()) {
            converter = value -> EnumUtils.GetSerializedNameValue((Enum) value);
        }
        return converter;
    }

}
