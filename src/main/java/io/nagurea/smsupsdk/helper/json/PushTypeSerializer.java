package io.nagurea.smsupsdk.helper.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.nagurea.smsupsdk.sendsms.arguments.PushType;

import java.lang.reflect.Type;

public class PushTypeSerializer implements JsonSerializer<PushType> {

    @Override
    public JsonElement serialize(PushType pushType, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(pushType.getLabel());
    }
}
