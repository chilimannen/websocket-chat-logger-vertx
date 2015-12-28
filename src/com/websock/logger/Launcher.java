package com.websock.logger;

import io.vertx.core.*;

/**
 * Created by Robin on 2015-12-26.
 * <p>
 * Bootstraps the application.
 */
public class Launcher extends AbstractVerticle {
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
        vertx.deployVerticle(new LoggerService());
        vertx.deployVerticle(new LogWriter(), new DeploymentOptions().setWorker(true));
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
    }

}
