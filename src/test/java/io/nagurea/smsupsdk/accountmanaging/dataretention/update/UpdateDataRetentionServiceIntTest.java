package io.nagurea.smsupsdk.accountmanaging.dataretention.update;

import io.nagurea.smsupsdk.accountmanaging.dataretention.DataRetentionResult;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.DataRetentionInfo;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.CampaignRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetentionWithExpiration;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.MessageRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.SurveyRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.TimeUnit;
import io.nagurea.smsupsdk.common.TestIntBase;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.TimeUnit.MONTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class UpdateDataRetentionServiceIntTest extends TestIntBase {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private UpdateDataRetentionService updateDataRetentionService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
            public final Object retention = new Object(){
                public final String message = "2d";
                public final String survey = "5m";
                public final String list = "2m";
                public final String campaign = "5m";
            };
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/retention")
                        .withMethod("PUT")
                        .withHeader("Authorization", EXPECTED_TOKEN)
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "    \"status\": 1,\n" +
                                    "    \"message\": \"OK\",\n" +
                                    "    \"retention\": {\n" +
                                    "        \"message\": \"2d\",\n" +
                                    "        \"survey\": \"5m\",\n" +
                                    "        \"list\": \"2m\",\n" +
                                    "        \"campaign\": \"5m\"\n" +
                                    "    }\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void updateDataRetention() throws IOException {
        // given
        UpdateDataRetentionResultResponse expectedResponse = UpdateDataRetentionResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .retention(
                        DataRetentionResult.builder()
                                .message("2d")
                                .survey("5m")
                                .list("2m")
                                .campaign("5m")
                                .build()
                )
                .build();

        final DataRetentionInfo dataRetentionInfo = DataRetentionInfo.builder()
                        .message(MessageRetention.builder()
                                .number(2)
                                .timeUnit(TimeUnit.DAY).build())
                        .survey(SurveyRetention.builder()
                                .number(5)
                                .timeUnit(MONTH)
                                .build())
                        .list(ListRetention.buildListRetention(
                                ListRetentionWithExpiration.builder()
                                        .number(2)
                                        .timeUnit(MONTH).build()
                        ))
                        .campaign(CampaignRetention.builder()
                                .number(5)
                                .timeUnit(MONTH)
                                .build())
                        .build();

        // when
        final UpdateDataRetentionResultResponse actualResponse = updateDataRetentionService.updateDataRetention(
                YOUR_TOKEN,
                dataRetentionInfo
                ).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualResponse.getStatus());
        assertEquals("OK", actualResponse.getMessage());
        assertEquals(expectedResponse, actualResponse);

    }
}