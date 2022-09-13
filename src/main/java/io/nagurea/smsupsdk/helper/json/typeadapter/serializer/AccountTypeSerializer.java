package io.nagurea.smsupsdk.helper.json.typeadapter.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.AccountType;

import java.lang.reflect.Type;

public class AccountTypeSerializer implements JsonSerializer<AccountType> {

    @Override
    public JsonElement serialize(AccountType accountType, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(accountType.getLabel());
    }
}
