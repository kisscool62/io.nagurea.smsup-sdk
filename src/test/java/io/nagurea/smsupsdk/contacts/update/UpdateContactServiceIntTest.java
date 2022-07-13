package io.nagurea.smsupsdk.contacts.update;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.contacts.insert.InsertContactInListResponse;
import io.nagurea.smsupsdk.contacts.insert.InsertContactInListResultResponse;
import io.nagurea.smsupsdk.contacts.insert.InsertContactInListService;
import io.nagurea.smsupsdk.contacts.insert.body.ContactList;
import io.nagurea.smsupsdk.contacts.insert.body.ContactListBody;
import io.nagurea.smsupsdk.contacts.insert.body.Gsm;
import io.nagurea.smsupsdk.contacts.insert.body.ListOfContacts;
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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class UpdateContactServiceIntTest {
    private static final String CONTACT_ID = "123456";

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private UpdateContactService updateContactService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        final String destination = "41781234567";
        final String info1 = "Martin";
        final String info2 = "T";
        final String info3 = "T Corp";
        final String info4 = "";
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);

        mockServer.when(
                request()
                        .withPath("/list/contact/" + CONTACT_ID)
                        .withMethod("PUT")
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
     void insertContactInList() throws IOException {
         //given
         final UpdateContactResultResponse expectedResponse = UpdateContactResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .build();
         final int expectedStatusCode = 200;

         final ContactBody contactBody = ContactBody.builder()
                     .destination("41781234567")
                     .info1("Martin")
                     .info2("T")
                     .info3("T Corp")
                     .info4("")
                     .build();

         //when
         final UpdateContactResponse result = updateContactService.updateContact("token", CONTACT_ID, contactBody);
         final Integer effectiveStatusCode = result.getStatusCode();
         final UpdateContactResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }

}
