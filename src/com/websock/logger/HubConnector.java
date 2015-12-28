package com.websock.logger;


import com.microsoft.eventhubs.client.EventHubClient;
import com.microsoft.eventhubs.client.EventHubException;
import com.microsoft.eventhubs.client.EventHubSender;

/**
 * Created by Robin on 2015-12-26.
 * <p>
 * Connects to the Azure event hub.
 */
class HubConnector {
    private EventHubSender sender;

    public HubConnector() throws EventHubException {
        // todo regenerate the access key, is publicly available.
        EventHubClient client = EventHubClient.create("LogWriter", "85KmpG54Eev9W7RhlBKDxrGwxGFdAmPTkPX6Dou3Fxs=", "websock", "logging");
        sender = client.createPartitionSender(null);
    }

    public void send(String data) throws EventHubException {
        sender.send(data);
    }
}

