package io.nagurea.smsupsdk.helper.json.typeadapter.serializer;


import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetention;

import java.lang.reflect.Type;

public class ListRetentionSerializer implements JsonSerializer<ListRetention> {

    @Override
    public JsonElement serialize(ListRetention retention, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(retention.toString());
    }
}
