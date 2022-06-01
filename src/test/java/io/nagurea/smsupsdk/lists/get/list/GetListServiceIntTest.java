package io.nagurea.smsupsdk.lists.get.list;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.lists.get.lists.ContactList;
import io.nagurea.smsupsdk.lists.get.lists.GetListsResultResponse;
import io.nagurea.smsupsdk.lists.get.lists.GetListsService;
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
class GetListServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetListService getListService;

    private static ClientAndServer mockServer;


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/list/id-list")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{ \n" +
                                    "    \"status\": 1,\n" +
                                    "    \"message\": \"OK\",\n" +
                                    "    \"list\": [\n" +
                                    "        {\n" +
                                    "            \"id\": \"5a0331bffc5886074551ce97\",\n" +
                                    "            \"destination\": \"41781234567\",\n" +
                                    "            \"info1\": \"Louis\",\n" +
                                    "            \"info2\": \"de Broglie\",\n" +
                                    "            \"info3\": \"1892\",\n" +
                                    "            \"info4\": \"Dieppe\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": \"5a0331bffc5886074551ce98\",\n" +
                                    "            \"destination\": \"41781234566\",\n" +
                                    "            \"info1\": \"Richard\",\n" +
                                    "            \"info2\": \"Feynman\",\n" +
                                    "            \"info3\": \"1918\",\n" +
                                    "            \"info4\": \"New-York\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": \"5a0331bffc5886074551ce99\",\n" +
                                    "            \"destination\": \"41781234565\",\n" +
                                    "            \"info1\": \"Hiroo\",\n" +
                                    "            \"info2\": \"Onoda\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": \"5a0331bffc5886074551cf00\",\n" +
                                    "            \"destination\": \"41781234564\",\n" +
                                    "            \"info1\": \"Grace\",\n" +
                                    "            \"info2\": \"Hopper\"\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"id\": \"5a0331bffc5886074551cf01\",\n" +
                                    "            \"destination\": \"41781234563\",\n" +
                                    "            \"info1\": \"Hedy\",\n" +
                                    "            \"info2\": \"Lamarr\",\n" +
                                    "            \"info3\": \"Extase\",\n" +
                                    "            \"info4\": \"1933\"\n" +
                                    "        }\n" +
                                    "    ],\n" +
                                    "    \"name\": \"Liste API\",\n" +
                                    "    \"contacts\": \"5\",\n" +
                                    "    \"totalRecords\": 5,\n" +
                                    "    \"totalDisplayRecords\": 5\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void getListById() throws IOException {
        // given
        GetListResultResponse expectedGetListResponse = GetListResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .name("Liste API")
                .contacts("5")
                .totalRecords(5)
                .totalDisplayRecords(5)
                .list(Arrays.asList(
                        Contact.builder()
                                .id("5a0331bffc5886074551ce97")
                                .destination("41781234567")
                                .info1("Louis")
                                .info2("de Broglie")
                                .info3("1892")
                                .info4("Dieppe")
                                .build(),
                        Contact.builder()
                                .id("5a0331bffc5886074551ce98")
                                .destination("41781234566")
                                .info1("Richard")
                                .info2("Feynman")
                                .info3("1918")
                                .info4("New-York")
                                .build(),
                        Contact.builder()
                                .id("5a0331bffc5886074551ce99")
                                .destination("41781234565")
                                .info1("Hiroo")
                                .info2("Onoda")
                                .build(),
                        Contact.builder()
                                .id("5a0331bffc5886074551cf00")
                                .destination("41781234564")
                                .info1("Grace")
                                .info2("Hopper")
                                .build(),
                        Contact.builder()
                                .id("5a0331bffc5886074551cf01")
                                .destination("41781234563")
                                .info1("Hedy")
                                .info2("Lamarr")
                                .info3("Extase")
                                .info4("1933")
                                .build()
                ))
                .build();


        // when
        final GetListResultResponse actualGetListResponse = getListService.getListById(YOUR_TOKEN, "id-list").getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualGetListResponse.getStatus());
        assertEquals("OK", actualGetListResponse.getMessage());
        assertEquals(expectedGetListResponse, actualGetListResponse);

    }
}