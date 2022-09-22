package io.nagurea.smsupsdk.accountmanaging.transfercredits;


import io.nagurea.smsupsdk.accountmanaging.transfercredits.body.TransferBody;
import io.nagurea.smsupsdk.accountmanaging.transfercredits.body.TransferInfo;
import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class TransferCreditsService extends POSTSMSUpService {

    private static final String URL_TOKEN = "/account/transfer";

    public TransferCreditsService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to transfer credits to a sub-account.
     * @param token SMSUp token
     * @param toAccount The account id of your sub-account
     * @param credits Amount of credits
     * @return TransferCreditsResponse with detailed @{@link TransferCreditsResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public TransferCreditsResponse transferCredits(String token, @NonNull Integer toAccount, @NonNull Integer credits) throws IOException {
        final ImmutablePair<Integer, String> response = post(
                URL_TOKEN, token,
                GsonHelper.toJson(
                        TransferBody.builder()
                                .transfer(TransferInfo.builder()
                                    .toAccount(toAccount)
                                    .credits(credits)
                                    .build())
                        .build())
                );
        final String body = response.getRight();
        final TransferCreditsResultResponse responseObject = GsonHelper.fromJson(body, TransferCreditsResultResponse.class);
        return TransferCreditsResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
