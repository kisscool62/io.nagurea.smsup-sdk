package io.nagurea.smsupsdk.helper.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.nagurea.smsupsdk.sendsms.sender.Sender;

import java.lang.reflect.Type;

public class SenderSerializer implements JsonSerializer<Sender> {

    @Override
    public JsonElement serialize(Sender sender, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(sender.whoSent().orElse(""));
    }
}
