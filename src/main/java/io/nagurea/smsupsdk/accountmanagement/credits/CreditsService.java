package io.nagurea.smsupsdk.accountmanagement.credits;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class CreditsService extends GETSMSUpService {
    private static final String URL = "/credits";

    public CreditsService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to get your credits amount
     * @param token
     * @return @{@link CreditsResponse} with credit amount as string
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CreditsResponse getCredits(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL, token);
        final String body = response.getRight();
        final CreditsResultResponse responseObject = GsonHelper.fromJson(body, CreditsResultResponse.class);
        return CreditsResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

}
