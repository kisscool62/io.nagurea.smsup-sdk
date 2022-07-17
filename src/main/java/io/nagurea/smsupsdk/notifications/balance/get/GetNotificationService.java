package io.nagurea.smsupsdk.notifications.balance.get;


import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class GetNotificationService extends GETSMSUpService {

    private static final String URL = "/notification/balance";

    public GetNotificationService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Get balance notification settings
     * @param token SMSUp token
     * @return GetNotificationResponse with detailed @GetNotificationResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetNotificationResponse getNotificationBalance(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL, token);
        final String body = response.getRight();
        final GetNotificationResultResponse responseObject = GsonHelper.fromJson(body, GetNotificationResultResponse.class);
        return GetNotificationResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
