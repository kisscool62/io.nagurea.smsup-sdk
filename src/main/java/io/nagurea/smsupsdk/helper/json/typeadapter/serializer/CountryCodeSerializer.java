package io.nagurea.smsupsdk.helper.json.typeadapter.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.neovisionaries.i18n.CountryCode;

import java.lang.reflect.Type;

public class CountryCodeSerializer implements JsonSerializer<CountryCode> {

    @Override
    public JsonElement serialize(CountryCode countryCode, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(countryCode.getAlpha2().toLowerCase());
    }
}
