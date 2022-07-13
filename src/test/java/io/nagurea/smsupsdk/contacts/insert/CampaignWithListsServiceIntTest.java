package io.nagurea.smsupsdk.contacts.insert;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.contacts.insert.body.ContactList;
import io.nagurea.smsupsdk.contacts.insert.body.ContactListBody;
import io.nagurea.smsupsdk.contacts.insert.body.Gsm;
import io.nagurea.smsupsdk.contacts.insert.body.ListOfContacts;
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
class CampaignWithListsServiceIntTest {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private InsertContactInListService insertContactInListService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        final Object list = new Object() {
            final String listId = "50433";
            final Object contacts = new Object(){
                final Object[] gsm = {
                        new Object(){
                            final String value = "41781234567";
                            final String info1 = "Hiroo";
                            final String info2 = "Onoda";
                        },
                        new Object(){
                            final String value = "41781234566";
                            final String info1 = "Grace";
                            final String info2 = "Hopper";
                        },
                        new Object(){
                            final String value = "41781234565";
                            final String info1 = "Hedy";
                            final String info2 = "Lamarr";
                            final String info3 = "Extase";
                            final String info4 = "1933";
                        }
                };
           };

        };
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);

        mockServer.when(
                request()
                        .withPath("/list")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"contacts\": 3,\n" +
                                        "  \"id\": \"50433\"" +
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
         final InsertContactInListResultResponse expectedResponse = InsertContactInListResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .contacts(3)
                 .id("50433")
                 .build();
         final int expectedStatusCode = 200;

         final ContactListBody contactListBody = ContactListBody.builder()
                 .list(
                         ContactList.builder()
                                 .listId(50433)
                                 .contacts(
                                         ListOfContacts.builder()
                                                 .gsm(
                                                     Arrays.asList(
                                                             Gsm.builder()
                                                                     .value("41781234567")
                                                                     .info1("Hiroo")
                                                                     .info2("Onoda")
                                                             .build(),
                                                             Gsm.builder()
                                                                     .value("41781234566")
                                                                     .info1("Grace")
                                                                     .info2("Hopper")
                                                                     .build(),
                                                             Gsm.builder()
                                                                     .value("41781234565")
                                                                     .info1("Hedy")
                                                                     .info2("Lamarr")
                                                                     .info3("Extase")
                                                                     .info4("1933")
                                                                     .build()
                                                     )
                                                 )
                                                 .build()
                                 )
                                 .build()
                 )
                 .build();

         //when
         final InsertContactInListResponse result = insertContactInListService.insertContactInList("token", contactListBody);
         final Integer effectiveStatusCode = result.getStatusCode();
         final InsertContactInListResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }

}
