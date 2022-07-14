package io.nagurea.smsupsdk.helper.json.typeadapter.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.nagurea.smsupsdk.notifications.balance.update.body.ActivationStatus;

import java.lang.reflect.Type;

public class ActivationStatusSerializer implements JsonSerializer<ActivationStatus> {

    @Override
    public JsonElement serialize(ActivationStatus activationStatus, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(activationStatus.getLabel());
    }
}
