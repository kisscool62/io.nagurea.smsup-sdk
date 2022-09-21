package io.nagurea.smsupsdk.helper.json.typeadapter.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import io.nagurea.smsupsdk.accountmanaging.subaccount.lock.LockState;

import java.lang.reflect.Type;

public class LockStateDeserializer implements JsonDeserializer<LockState> {

    @Override
    public LockState deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext)  {
        return LockState.findByName(json.getAsJsonPrimitive().getAsString());
    }
}
