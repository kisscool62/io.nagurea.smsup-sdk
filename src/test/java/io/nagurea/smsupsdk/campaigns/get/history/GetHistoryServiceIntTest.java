package io.nagurea.smsupsdk.campaigns.get.history;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.NonNull;
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
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class GetHistoryServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetHistoryService getHistoryService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/campaigns")
                        .withQueryStringParameter("start", "0")
                        .withQueryStringParameter("length", "10")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"campaigns\": [\n" +
                                        "      {\n" +
                                        "          \"id\": \"18969398\",\n" +
                                        "          \"sender\": \"BESTSHOES\",\n" +
                                        "          \"text\": \"Special offer : Buy one shoe and get the second one for free\",\n" +
                                        "          \"date\": \"2018-07-04 11:37:16\",\n" +
                                        "          \"cost\": \"2128\",\n" +
                                        "          \"delivery_rate\": \"92\"\n" +
                                        "        },\n" +
                                        "      {\n" +
                                        "          \"id\": \"18852069\",\n" +
                                        "          \"sender\": \"\",\n" +
                                        "          \"text\": \"Hey this is my first campaign !\",\n" +
                                        "          \"date\": \"2018-05-30 14:03:19\",\n" +
                                        "          \"cost\": \"1\",\n" +
                                        "          \"delivery_rate\": \"100\"\n" +
                                        "        }\n" +
                                        "  ],\n" +
                                        "  \"totalRecords\": 2,\n" +
                                        "  \"totalDisplayRecords\": 2\n" +
                                        "}"
                        )
        );
    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

     @Test
     void getCampaignNPAIs() throws IOException {
         //given
         final GetHistoryResultResponse expectedResponse = GetHistoryResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .campaigns(
                         Arrays.asList(
                                 Campaign.builder()
                                         .id("18969398")
                                         .sender("BESTSHOES")
                                         .text("Special offer : Buy one shoe and get the second one for free")
                                         .date(LocalDateTime.of(2018, 7, 4, 11, 37, 16))
                                         .cost("2128")
                                         .deliveryRate("92")
                                         .build(),
                                 Campaign.builder()
                                         .id("18852069")
                                         .sender("")
                                         .text("Hey this is my first campaign !")
                                         .date(LocalDateTime.of(2018, 5, 30, 14, 3, 19))
                                         .cost("1")
                                         .deliveryRate("100")
                                         .build()
                         )
                 )
                 .build();
         final int expectedStatusCode = 200;
         @NonNull HistoryArguments historyArguments = HistoryArguments.builder()
                 .start(0)
                 .length(10)
                 .build();

         //when
         final GetHistoryResponse result = getHistoryService.getHistoryCampaigns(YOUR_TOKEN, historyArguments);
         final Integer effectiveStatusCode = result.getStatusCode();
         final GetHistoryResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
         assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }


}
