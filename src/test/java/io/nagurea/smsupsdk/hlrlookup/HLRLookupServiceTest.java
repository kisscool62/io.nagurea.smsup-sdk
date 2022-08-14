package io.nagurea.smsupsdk.hlrlookup;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.hlrlookup.response.HLRLookup;
import io.nagurea.smsupsdk.hlrlookup.response.Lookup;
import io.nagurea.smsupsdk.hlrlookup.response.LookupError;
import io.nagurea.smsupsdk.hlrlookup.response.LookupStatus;
import io.nagurea.smsupsdk.hlrlookup.response.OriginalNetwork;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class HLRLookupServiceTest {
    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    /**
     * Useless. Only here to show how services could be used with Spring
     */
    @Autowired
    private HLRLookupService hlrLookupService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        final Object lookup = new Object() {
            final Object[] to = {"41781234567","41781234566"};
        };
    };

    @BeforeAll
    public static void startMockSMSUpServer() {
        ConfigurationProperties.logLevel("DEBUG");
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/lookup")
                        .withMethod("POST")
                        .withHeader("Authorization", EXPECTED_TOKEN)
                .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,      \n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"cost\": 2,\n" +
                                        "  \"credits\": 642,\n" +
                                        "  \"response\": {\n" +
                                        "    \"lookup\": [\n" +
                                        "      {\n" +
                                        "        \"to\": \"41781234567\",\n" +
                                        "        \"mccMnc\": \"20801\",\n" +
                                        "        \"imsi\": \"208019900000000\",\n" +
                                        "        \"originalNetwork\": {\n" +
                                        "          \"networkName\": \"Orange\",\n" +
                                        "          \"networkPrefix\": \"671\",\n" +
                                        "          \"countryName\": \"France\",\n" +
                                        "          \"countryPrefix\": \"33\"\n" +
                                        "        },\n" +
                                        "        \"ported\": false,\n" +
                                        "        \"roaming\": false,\n" +
                                        "        \"status\": {\n" +
                                        "          \"name\": \"DELIVERED\",\n" +
                                        "          \"description\": \"Message delivered to handset\",\n" +
                                        "          \"detailed\": \"DELIVERED_TO_HANDSET\"\n" +
                                        "        },\n" +
                                        "        \"error\": {\n" +
                                        "          \"id\": 0,\n" +
                                        "          \"description\": \"No Error\",\n" +
                                        "          \"permanent\": false\n" +
                                        "        }\n" +
                                        "      },\n" +
                                        "      {\n" +
                                        "        \"to\": \"41781234566\",\n" +
                                        "        \"mccMnc\": \"20801\",\n" +
                                        "        \"originalNetwork\": {\n" +
                                        "          \"networkName\": \"Orange\",\n" +
                                        "          \"networkPrefix\": \"671\",\n" +
                                        "          \"countryName\": \"France\",\n" +
                                        "          \"countryPrefix\": \"33\"\n" +
                                        "        },\n" +
                                        "        \"ported\": false,\n" +
                                        "        \"roaming\": false,\n" +
                                        "        \"status\": {\n" +
                                        "          \"name\": \"UNDELIVERABLE\",\n" +
                                        "          \"description\": \"Message sent not delivered\",\n" +
                                        "          \"detailed\": \"UNDELIVERABLE_NOT_DELIVERED\"\n" +
                                        "        },\n" +
                                        "        \"error\": {\n" +
                                        "          \"id\": 27,\n" +
                                        "          \"description\": \"Absent Subscriber\",\n" +
                                        "          \"permanent\": false\n" +
                                        "        }\n" +
                                        "      }\n" +
                                        "    ]\n" +
                                        "  }\n" +
                                        "}"
                        )
        );
    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void hlrLookup() throws IOException {
        //given
        //expected results
        final HLRLookupResultResponse expectedResponse = HLRLookupResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .cost(2)
                .credits(642)
                .response(
                        HLRLookup.builder()
                                .lookup(
                                    Lookup.builder()
                                            .to("41781234567")
                                            .mccMnc("20801")
                                            .imsi("208019900000000")
                                            .originalNetwork(
                                                    OriginalNetwork.builder()
                                                            .networkName("Orange")
                                                            .networkPrefix("671")
                                                            .countryName("France")
                                                            .countryPrefix("33")
                                                    .build()
                                            )
                                            .ported(false)
                                            .roaming(false)
                                            .status(
                                                    LookupStatus.builder()
                                                            .name("DELIVERED")
                                                            .description("Message delivered to handset")
                                                            .detailed("DELIVERED_TO_HANDSET")
                                                    .build()
                                            )
                                            .error(
                                                    LookupError.builder()
                                                            .id(0)
                                                            .description("No Error")
                                                            .permanent(false)
                                                    .build()
                                            )
                                    .build()
                                )
                                .lookup(
                                        Lookup.builder()
                                                .to("41781234566")
                                                .mccMnc("20801")
                                                .originalNetwork(
                                                        OriginalNetwork.builder()
                                                                .networkName("Orange")
                                                                .networkPrefix("671")
                                                                .countryName("France")
                                                                .countryPrefix("33")
                                                                .build()
                                                )
                                                .ported(false)
                                                .roaming(false)
                                                .status(
                                                        LookupStatus.builder()
                                                                .name("UNDELIVERABLE")
                                                                .description("Message sent not delivered")
                                                                .detailed("UNDELIVERABLE_NOT_DELIVERED")
                                                                .build()
                                                )
                                                .error(
                                                        LookupError.builder()
                                                                .id(27)
                                                                .description("Absent Subscriber")
                                                                .permanent(false)
                                                                .build()
                                                )
                                                .build()
                                )
                        .build()
                )
                .build();
        final int expectedStatusCode = 200;

        //given arguments
        final List<String> to = Arrays.asList("41781234567", "41781234566");


        //when
        final HLRLookupResponse result = hlrLookupService.hlrLookup(YOUR_TOKEN, to);
        final Integer effectiveStatusCode = result.getStatusCode();
        final HLRLookupResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(expectedResponse, effectiveResponse);
    }


}