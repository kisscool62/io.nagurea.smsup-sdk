package io.nagurea.smsupsdk.campaigns.get.history;


import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class GetHistoryService extends GETSMSUpService {

    private static final String URL = "/campaigns";

    protected GetHistoryService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to retrieve your campaigns history. With any optional argument wrapped in historyArguments
     * @param token SMSUp token
     * @param historyArguments is a wrapper of arguments
     * @return GetHistoryResponse with detailed @GetHistoryResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetHistoryResponse getHistoryCampaigns(String token, @NonNull HistoryArguments historyArguments) throws IOException {
        return send(token,historyArguments);
    }

    /**
     * This method allows you to retrieve your campaigns history. With no optional argument
     * @param token SMSUp token
     * @return GetHistoryResponse with detailed @GetHistoryResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetHistoryResponse getHistoryCampaigns(String token) throws IOException {
        return send(token, null);
    }


    private GetHistoryResponse send(String token, HistoryArguments historyArguments) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildSendUrl(historyArguments), token);
        final String body = response.getRight();
        final GetHistoryResultResponse responseObject = GsonHelper.fromJson(body, GetHistoryResultResponse.class);
        return GetHistoryResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildSendUrl(HistoryArguments historyArguments) {
        String url = URL;

        if(historyArguments != null && historyArguments.hasAtLeastOneArgument()){
            url += historyArguments.toUrl();
        }

        return url;
    }

}
