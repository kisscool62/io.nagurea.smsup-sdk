package io.nagurea.smsupsdk.campaigns.blacklist.npai;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.put.PUTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class BlacklistNPAIService extends PUTSMSUpService {

    private static final String URL = "/campaign/%s/npai";
    private static final String ID = "campaign id";

    public BlacklistNPAIService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to add the NPAI of your campaign into your NPAI list.
     * @param token SMSUp token
     * @param id id of the campaign to blacklist the npai
     * @return BlacklistNPAIResponse with detailed @BlacklistNPAIResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public BlacklistNPAIResponse blacklistNPAI(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = put(buildSendUrl(id), token);
        final String body = response.getRight();
        final BlacklistNPAIResultResponse responseObject = GsonHelper.fromJson(body, BlacklistNPAIResultResponse.class);
        return BlacklistNPAIResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }



    private String buildSendUrl(@NonNull String id) {
        final RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(StringUtils.isBlank(id)){
            exception.requiredParam(ID, id);
            throw exception.build();
        }

        return String.format(BlacklistNPAIService.URL, id);
    }

}
