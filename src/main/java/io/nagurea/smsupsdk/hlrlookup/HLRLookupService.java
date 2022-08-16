package io.nagurea.smsupsdk.hlrlookup;

import com.google.gson.Gson;
import io.nagurea.smsupsdk.common.exception.NotEmptyParameterException;
import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.hlrlookup.body.LookupBody;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class HLRLookupService extends POSTSMSUpService {
    private static final String URL = "/lookup";
    private static final String REQUIRED_TO = "to";

    public HLRLookupService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Get information about any number like its validity,
     * wether it is currently active in a mobile network, and if so, wich one.
     * You can also know if the number is in roaming mode, or if it is ported to another operator.
     * @param token
     * @param to the List of numbers to apply the HLR lookup. String array of numbers
     * @return The HLR info
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public HLRLookupResponse hlrLookup(@NonNull String token, List<String> to) throws IOException {
        if(to == null){
            throw NotEmptyParameterException.builder().requiredParam(REQUIRED_TO, null).build();
        }
        if(to.isEmpty()){
            throw NotEmptyParameterException.builder().requiredParam(REQUIRED_TO, to).build();
        }
        final ImmutablePair<Integer, String> response = post(URL, token, buildData(to));
        final HLRLookupResultResponse responseObject = new Gson().fromJson(response.getRight(), HLRLookupResultResponse.class);
        return HLRLookupResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildData(List<String> to) {
        final LookupBody.LookupBodyBuilder lookupBodyBuilder = LookupBody.builder();

        for (String phoneNumber : to) {
            lookupBodyBuilder.to(phoneNumber);
        }

        return GsonHelper.toJson(lookupBodyBuilder.build());
    }


}
