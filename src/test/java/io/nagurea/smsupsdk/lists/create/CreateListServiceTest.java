package io.nagurea.smsupsdk.lists.create;

import io.nagurea.smsupsdk.common.TestIntBase;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.lists.create.arguments.CreateListArguments;
import io.nagurea.smsupsdk.lists.create.body.Contacts;
import io.nagurea.smsupsdk.lists.create.body.Gsm;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class CreateListServiceTest extends TestIntBase {


    /**
     * Useless. Only here to show how services could be used with Spring
     */
    @Autowired
    private CreateListService createListService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        public final Object list = new Object() {
            public final String name = "My list";
            public final Object contacts = new Object() {
                public final Object[] gsm = {
                  new Object(){
                        public final String value = "41781234567";
                        public final String info1 = "Louis";
                        public final String info2 = "de Broglie";
                        public final String info3 = "1892";
                        public final String info4 = "Dieppe";
                  },
                    new Object(){
                        public final String value = "41781234566";
                        public final String info1 = "Richard";
                        public final String info2 = "Feynman";
                        public final String info3 = "1918";
                        public final String info4 = "New-York";
                  }
                };
            };
        };
    };

    @BeforeAll
    public static void startMockSMSUpServer() {
        ConfigurationProperties.logLevel("DEBUG");
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/list")
                        .withMethod("POST")
                        .withHeader("Authorization", EXPECTED_TOKEN)
                .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"contacts\": 2,\n" +
                                        "  \"id\": \"50433\"\n" +
                                        "}"
                        )
        );
    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void create() throws IOException {
        //given
        //expected results
        final CreateListResultResponse expectedResponse = CreateListResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .contacts(2)
                .id("50433")
                .build();
        final int expectedStatusCode = 200;

        //given arguments
        final CreateListArguments createListArguments = CreateListArguments.builder()
                .name("My list")
                .contacts(
                        Contacts.builder()
                                .gsm(Gsm.builder()
                                        .value("41781234567")
                                        .info1("Louis")
                                        .info2("de Broglie")
                                        .info3("1892")
                                        .info4("Dieppe")
                                        .build())
                                .gsm(Gsm.builder()
                                        .value("41781234566")
                                        .info1("Richard")
                                        .info2("Feynman")
                                        .info3("1918")
                                        .info4("New-York")
                                        .build())
                                .build()
                )
                .build();


        //when
        final CreateListResponse result = createListService.create(YOUR_TOKEN, createListArguments);
        final Integer effectiveStatusCode = result.getStatusCode();
        final CreateListResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(expectedResponse, effectiveResponse);
    }


}