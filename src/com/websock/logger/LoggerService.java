package com.websock.logger;

import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

/**
 * Created by Robin on 2015-12-26.
 * <p>
 * Transmits logging events to the logging-processor.
 */
class LoggerService implements Verticle {
    private Vertx vertx;

    @Override
    public Vertx getVertx() {
        return vertx;
    }

    @Override
    public void init(Vertx vertx, Context context) {
        this.vertx = vertx;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        startLogListener();
    }

    private void startLogListener() {
        vertx.createHttpServer().websocketHandler(event -> {

            // some form of processing could be implemented here..
            event.handler(data -> writeLog(data.toString()));

        }).listen(Configuration.LOGGER_PORT);
    }


    private void writeLog(String message) {
        vertx.eventBus().send(Configuration.UPSTREAM, message);
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        vertx.close();
    }
}
