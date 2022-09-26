package io.nagurea.smsupsdk.helper.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.neovisionaries.i18n.CountryCode;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.AccountType;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.Childness;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.AccountInfo;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.RetrieveAccountResultResponse;
import io.nagurea.smsupsdk.accountmanaging.common.AbstractAccountInfo;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.RetentionTime;
import io.nagurea.smsupsdk.accountmanaging.subaccount.lock.LockState;
import io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response.RetrieveSubaccountResultResponse;
import io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response.SubaccountInfo;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.helper.json.typeadapter.RuntimeTypeAdapterFactory;
import io.nagurea.smsupsdk.helper.json.typeadapter.deserializer.ActivationStatusDeserializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.deserializer.LocalDateTimeDeserializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.deserializer.LockStateDeserializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.deserializer.ResponseStatusDeserializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.AccountTypeSerializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.ActivationStatusSerializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.ChildnessSerializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.CountryCodeSerializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.ListRetentionSerializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.PushTypeSerializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.RetentionTimeSerializer;
import io.nagurea.smsupsdk.helper.json.typeadapter.serializer.SenderSerializer;
import io.nagurea.smsupsdk.lists.create.CreateListResultResponse;
import io.nagurea.smsupsdk.lists.get.list.GetListResultResponse;
import io.nagurea.smsupsdk.lists.get.lists.GetListsResultResponse;
import io.nagurea.smsupsdk.notifications.balance.update.body.ActivationStatus;
import io.nagurea.smsupsdk.sendsms.arguments.PushType;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
import io.nagurea.smsupsdk.sendsms.singlemessage.SingleMessageResultResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
                .registerSubtype(GetListResultResponse.class)
                .registerSubtype(RetrieveSubaccountResultResponse.class)
                .registerSubtype(RetrieveAccountResultResponse.class);

        final TypeAdapterFactory accountInfoFactory = RuntimeTypeAdapterFactory.of(AbstractAccountInfo.class)
                .registerSubtype(SubaccountInfo.class)
                .registerSubtype(AccountInfo.class);

        return new GsonBuilder()
                .registerTypeAdapterFactory(resultResponseFactory)
                .registerTypeAdapterFactory(accountInfoFactory)
                .registerTypeAdapter(ResponseStatus.class, new ResponseStatusDeserializer())
                .registerTypeAdapter(ActivationStatus.class, new ActivationStatusDeserializer())
                .registerTypeAdapter(LockState.class, new LockStateDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
    }

    private static GsonBuilder newGsonSerializerBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(PushType.class, new PushTypeSerializer());
        gsonBuilder.registerTypeAdapter(ActivationStatus.class, new ActivationStatusSerializer());
        gsonBuilder.registerTypeAdapter(Sender.class, new SenderSerializer());
        gsonBuilder.registerTypeAdapter(AccountType.class, new AccountTypeSerializer());
        gsonBuilder.registerTypeAdapter(Childness.class, new ChildnessSerializer());
        gsonBuilder.registerTypeAdapter(CountryCode.class, new CountryCodeSerializer());
        gsonBuilder.registerTypeAdapter(ListRetention.class, new ListRetentionSerializer());
        gsonBuilder.registerTypeHierarchyAdapter(RetentionTime.class, new RetentionTimeSerializer());
        return gsonBuilder;
    }

    public static String toJson(Object object){
        return gsonSerializer.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> expectedClass){
        return gsonDeserializer.fromJson(json, expectedClass);
    }


}
