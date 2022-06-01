package io.nagurea.smsupsdk.helper.json.typeadapter.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.nagurea.smsupsdk.common.status.ResponseStatus;

import java.lang.reflect.Type;

public class ResponseStatusDeserializer implements JsonDeserializer<ResponseStatus> {

    @Override
    public ResponseStatus deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ResponseStatus.findByCode(json.getAsJsonPrimitive().getAsInt());
    }

}

