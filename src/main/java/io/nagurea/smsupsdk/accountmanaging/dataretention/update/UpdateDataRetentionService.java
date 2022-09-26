package io.nagurea.smsupsdk.accountmanaging.dataretention.update;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.DataRetentionBody;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator.DataRetentionBodyValidator;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.DataRetentionInfo;
import io.nagurea.smsupsdk.common.exception.ValidationParameterException;
import io.nagurea.smsupsdk.common.http.put.PUTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import jakarta.validation.ConstraintViolation;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class UpdateDataRetentionService extends PUTSMSUpService {
    private static final String URL = "/retention";

    public UpdateDataRetentionService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * You can set the retention time of your data, meaning the time during which it is stored on our servers.
     * Past that time, it will be deleted.
     * @param token
     * @param dataRetentionInfo @{@link DataRetentionInfo} represents info about message, survey, list, campaign retentions
     * @return the status and message
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public UpdateDataRetentionResponse updateDataRetention(@NonNull String token, @NonNull DataRetentionInfo dataRetentionInfo) throws IOException {
        final DataRetentionBody dataRetentionBody = DataRetentionBody.builder().retention(dataRetentionInfo).build();
        validateDataRetention(dataRetentionBody);
        final ImmutablePair<Integer, String> response = put(URL, token, GsonHelper.toJson(
                dataRetentionBody
        ));
        final UpdateDataRetentionResultResponse responseObject = GsonHelper.fromJson(response.getRight(), UpdateDataRetentionResultResponse.class);
        return UpdateDataRetentionResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


    private void validateDataRetention(@NonNull DataRetentionBody dataRetentionBody) {
        final Set<ConstraintViolation<DataRetentionBody>> validateDataRetention = DataRetentionBodyValidator.validates(dataRetentionBody);

        final ValidationParameterException.ValidationParameterExceptionBuilder validationParameterExceptionBuilder = ValidationParameterException.builder();
        if(! validateDataRetention.isEmpty()) {
            for (ConstraintViolation<DataRetentionBody> validate : validateDataRetention) {
                validationParameterExceptionBuilder.validatedParam(validate.getPropertyPath().toString(), validate.getMessage());
            }
            throw validationParameterExceptionBuilder.build();
        }

        if(!dataRetentionBody.isValid()){
            validationParameterExceptionBuilder.validatedParam("Duration number/unit not allowed", dataRetentionBody);
        }
    }

}
