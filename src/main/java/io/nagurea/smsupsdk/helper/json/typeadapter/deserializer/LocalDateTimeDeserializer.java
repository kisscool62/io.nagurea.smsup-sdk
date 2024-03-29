package io.nagurea.smsupsdk.helper.json.typeadapter.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.nagurea.smsupsdk.helper.json.typeadapter.SMSUP_FORMATS.SMSUP_DATE_FORMAT;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(SMSUP_DATE_FORMAT);

    @Override
    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), localDateTimeFormatter);
    }
}

