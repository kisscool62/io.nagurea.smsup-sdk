package io.nagurea.smsupsdk.contacts.npai;

import io.nagurea.smsupsdk.common.TestIntBase;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.contacts.npai.body.ContactListBody;
import io.nagurea.smsupsdk.contacts.npai.body.Gsm;
import io.nagurea.smsupsdk.contacts.npai.body.ListOfContacts;
import io.nagurea.smsupsdk.contacts.npai.body.NPAIList;
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
class AddContactToNPAIListServiceIntTest extends TestIntBase {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private AddContactToNPAIListService npaiListService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        public final Object npai = new Object() {
            public final Object contacts = new Object(){
                public final Object[] gsm = {
                        new Object(){
                            public final String value = "41781234567";
                            public final String info1 = "Hiroo";
                            public final String info2 = "Onoda";
                        },
                        new Object(){
                            public final String value = "41781234566";
                            public final String info1 = "Grace";
                            public final String info2 = "Hopper";
                        },
                        new Object(){
                            public final String value = "41781234565";
                            public final String info1 = "Hedy";
                            public final String info2 = "Lamarr";
                            public final String info3 = "Extase";
                            public final String info4 = "1933";
                        }
                };
           };

        };
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();

        mockServer.when(
                request()
                        .withPath("/npai")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"added_contacts\": 3\n" +
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
         final AddContactToNPAIListResultResponse expectedResponse = AddContactToNPAIListResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .addedContacts(3)
                 .build();
         final int expectedStatusCode = 200;

         final ContactListBody contactListBody = ContactListBody.builder()
                 .npai(
                         NPAIList.builder()
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
         final AddContactToNPAIListResponse result = npaiListService.addContactToNPAIList("token", contactListBody);
         final Integer effectiveStatusCode = result.getStatusCode();
         final AddContactToNPAIListResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }

}
