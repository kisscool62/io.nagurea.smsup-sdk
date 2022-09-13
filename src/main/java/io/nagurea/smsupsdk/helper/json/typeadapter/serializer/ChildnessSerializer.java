package io.nagurea.smsupsdk.helper.json.typeadapter.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.Childness;

import java.lang.reflect.Type;

public class ChildnessSerializer implements JsonSerializer<Childness> {

    @Override
    public JsonElement serialize(Childness childness, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(childness.getValue());
    }
}
