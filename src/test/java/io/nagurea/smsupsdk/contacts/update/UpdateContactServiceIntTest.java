package io.nagurea.smsupsdk.contacts.update;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.contacts.update.body.ContactBody;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class UpdateContactServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;
    private static final String CONTACT_ID = "66";

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private UpdateContactService updateContactService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
            public final String destination = "41781234567";
            public final String info1 = "Martin";
            public final String info2 = "T";
            public final String info3 = "T Corp";
            public final String info4 = "";
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/list/contact/" + CONTACT_ID)
                        .withMethod("PUT")
                        .withHeader("Authorization", EXPECTED_TOKEN)
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "  \"status\": 1,\n" +
                                    "  \"message\": \"OK\"\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void updateContact() throws IOException {
        // given
        UpdateContactResultResponse expectedResponse = UpdateContactResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .build();

        final ContactBody contactBody = ContactBody.builder()
                .destination("41781234567")
                .info1("Martin")
                .info2("T")
                .info3("T Corp")
                .info4("")
                .build();

        // when
        final UpdateContactResultResponse actualResponse = updateContactService.updateContact(YOUR_TOKEN, CONTACT_ID, contactBody).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualResponse.getStatus());
        assertEquals("OK", actualResponse.getMessage());
        assertEquals(expectedResponse, actualResponse);

    }
}