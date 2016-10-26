package com.byndyusoft.androidinfrastructure.utils.json;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dmitrii Komiakov
 * komiakov.dmitrii@gmail.com
 * komyakovds@byndyusoft.com
 * on 26.10.2016.
 */

public class DateDeserializer implements JsonDeserializer<Date> {

    private static final String[] DATE_FORMATS = new String[]{
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            "yyyy-MM-dd HH:mm:ss.SSS ZZZZZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZZZZZ",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss"
    };

    private final static List<SimpleDateFormat> formatList = new ArrayList<>();

    static {
        for (String f : DATE_FORMATS) {
            formatList.add(new SimpleDateFormat(f, Locale.US));
        }
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type typeOF,
                                         JsonDeserializationContext context) throws JsonParseException {
        Date parsed = null;
        String formatter = "";
        for (SimpleDateFormat format : formatList) {
//            for (String format : DATE_FORMATS) {
            try {
                parsed = format.parse(jsonElement.getAsString());
//                    parsed = new SimpleDateFormat(format, Locale.US).parse(jsonElement.getAsString());
//                    formatter = format;
//                    Log.i("parser", "date " + jsonElement +" parsed to: " + parsed);
                return parsed;
            } catch (ParseException e) {
//                    e.printStackTrace();
            }
        }
        Log.e("parser", jsonElement.getAsString());
        throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                + "\". Supported formats: " + Arrays.toString(DATE_FORMATS));
    }
}
