package com.websock.logger;

import org.junit.Test;

/**
 * Created by Robin on 2015-12-26.
 * <p>
 * Integration tests for the logger.
 */
public class LoggerServiceUnit {

    @Test
    public void sendLogMessage() throws Exception {
        HubConnector connector = new HubConnector();

        for (int i = 0; i < 100; i++) {
            connector.send("message=" + i);
        }
    }

}
