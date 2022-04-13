package io.nagurea.smsupsdk.helper.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.nagurea.smsupsdk.sendmessages.arguments.PushType;
import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class GsonHelper {
    private static final GsonBuilder builder = newGsonBuilder();
    private static final Gson gson = builder.create();

    private static GsonBuilder newGsonBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PushType.class, new PushTypeSerializer());
        gsonBuilder.registerTypeAdapter(Sender.class, new SenderSerializer());
        return gsonBuilder;
    }

    public static String toJson(Object object){
        return gson.toJson(object);
    }


}
