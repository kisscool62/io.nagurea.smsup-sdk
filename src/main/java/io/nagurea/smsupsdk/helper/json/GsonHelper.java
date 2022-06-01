package io.nagurea.smsupsdk.helper.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.helper.json.typeadapter.RuntimeTypeAdapterFactory;
import io.nagurea.smsupsdk.helper.json.typeadapter.deserializer.LocalDateTimeDeserializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.deserializer.ResponseStatusDeserializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.PushTypeSerializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.SenderSerializer;
import io.nagurea.smsupsdk.lists.create.CreateListResultResponse;
import io.nagurea.smsupsdk.lists.get.list.GetListResultResponse;
import io.nagurea.smsupsdk.lists.get.lists.GetListsResultResponse;
import io.nagurea.smsupsdk.sendsms.arguments.PushType;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
import io.nagurea.smsupsdk.sendsms.singlemessage.SingleMessageResultResponse;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class GsonHelper {
    private static final GsonBuilder serializerBuilder = newGsonSerializerBuilder();
    private static final Gson gsonSerializer = serializerBuilder.create();

    private static final GsonBuilder deserializerBuilder = newGsonResultResponseDeserializerBuilder();
    private static final Gson gsonDeserializer = deserializerBuilder.create();

    private static GsonBuilder newGsonResultResponseDeserializerBuilder() {
        final TypeAdapterFactory resultResponseFactory = RuntimeTypeAdapterFactory.of(ResultResponse.class)
                .registerSubtype(GetListsResultResponse.class)
                .registerSubtype(CreateListResultResponse.class)
                .registerSubtype(SingleMessageResultResponse.class)
                .registerSubtype(GetListResultResponse.class);

        return new GsonBuilder()
                .registerTypeAdapterFactory(resultResponseFactory)
                .registerTypeAdapter(ResponseStatus.class, new ResponseStatusDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
    }

    private static GsonBuilder newGsonSerializerBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(PushType.class, new PushTypeSerializer());
        gsonBuilder.registerTypeAdapter(Sender.class, new SenderSerializer());
        return gsonBuilder;
    }

    public static String toJson(Object object){
        return gsonSerializer.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> expectedClass){
        return gsonDeserializer.fromJson(json, expectedClass);
    }


}
