package io.nagurea.smsupsdk.common;

import org.mockserver.integration.ClientAndServer;

public class TestIntBase {


    protected static final String YOUR_TOKEN = "Your Token";
    protected static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    protected static ClientAndServer startMockServer(){
        return ClientAndServer.startClientAndServer("localhost", 4242, 4242);
    }
}
