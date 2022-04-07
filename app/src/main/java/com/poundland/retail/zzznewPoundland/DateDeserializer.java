package com.poundland.retail.zzznewPoundland;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date> {
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    @Override
    public Date deserialize(JsonElement jsonElement, Type typeOF,
                            JsonDeserializationContext context) throws JsonParseException {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(jsonElement.getAsString());
        } catch (ParseException e) {
        }

        throw new JsonParseException("Unparseable date: \"" + jsonElement.getAsString()
                + "\". Supported formats: " + DATE_FORMAT);
    }
}