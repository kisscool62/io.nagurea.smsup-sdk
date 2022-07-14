package io.nagurea.smsupsdk.helper.json.typeadapter.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import io.nagurea.smsupsdk.notifications.balance.update.body.ActivationStatus;

import java.lang.reflect.Type;

public class ActivationStatusDeserializer implements JsonDeserializer<ActivationStatus> {

    @Override
    public ActivationStatus deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext)  {
        return ActivationStatus.findByLabel(json.getAsJsonPrimitive().getAsString());
    }
}
