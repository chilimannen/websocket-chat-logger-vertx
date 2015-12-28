package com.websock.logger;

import com.microsoft.eventhubs.client.EventHubException;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

/**
 * Created by Robin on 2015-12-26.
 * <p>
 * Uploads events to a logging hub using REST.
 */
class LogWriter implements Verticle {
    private Vertx vertx;
    private HubConnector hub;

    @Override
    public Vertx getVertx() {
        return vertx;
    }

    @Override
    public void init(Vertx vertx, Context context) {
        this.vertx = vertx;

        try {
            this.hub = new HubConnector();
        } catch (EventHubException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.eventBus().consumer(Configuration.UPSTREAM, event -> {

            try {
                hub.send(event.body().toString());
                System.out.println(event.body().toString());
            } catch (EventHubException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        vertx.close();
    }
}
