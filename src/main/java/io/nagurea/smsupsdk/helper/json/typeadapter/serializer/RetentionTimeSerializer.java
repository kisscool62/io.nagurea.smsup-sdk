package io.nagurea.smsupsdk.helper.json.typeadapter.serializer;


import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.RetentionTime;

import java.lang.reflect.Type;

public class RetentionTimeSerializer implements JsonSerializer<RetentionTime> {

    @Override
    public JsonElement serialize(RetentionTime retention, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(retention.toString());
    }
}
