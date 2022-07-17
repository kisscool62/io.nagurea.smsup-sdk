package io.nagurea.smsupsdk.notifications.balance.update;

import io.nagurea.smsupsdk.common.http.put.PUTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.notifications.balance.update.body.request.Notification;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class UpdateBalanceService extends PUTSMSUpService {
    private static final String URL = "/notification/balance";

    public UpdateBalanceService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Update balance notification settings
     * @param token
     * @return the status and message
     * @throws IOException
     */
    public UpdateBalanceResponse updateBalance(@NonNull String token, @NonNull Notification notification) throws IOException {
        final ImmutablePair<Integer, String> response = put(URL, token, GsonHelper.toJson(notification));
        final UpdateBalanceResultResponse responseObject = GsonHelper.fromJson(response.getRight(), UpdateBalanceResultResponse.class);
        return UpdateBalanceResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
