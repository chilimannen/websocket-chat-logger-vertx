package com.websock.logger;

import com.websock.logger.Protocol.*;
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
    private LogRecord record = new LogRecord();

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
        startLogSender();
    }

    private void startLogListener() {
        vertx.createHttpServer().websocketHandler(event -> {


            event.handler(data -> {
                LogMessage message = (LogMessage) Serializer.unpack(data.toString(), LogMessage.class);

                switch (message.getType()) {
                    case LogUserCount.ACTION:
                        record.addUserCount((LogUserCount) Serializer.unpack(data.toString(), LogUserCount.class));
                        break;
                    case IOLogger.ACTION:
                        record.addIO((IOLogger) Serializer.unpack(data.toString(), IOLogger.class));
                        break;
                    case ServerTreeLog.ACTION:
                        record.setTree((ServerTreeLog) Serializer.unpack(data.toString(), ServerTreeLog.class));
                        break;
                }

            });

        }).listen(Configuration.LOGGER_PORT);
    }

    private void startLogSender() {
        vertx.setPeriodic(Configuration.LOG_INTERVAL, event -> {
            vertx.eventBus().send(Configuration.UPSTREAM, Serializer.pack(record.compile()));
        });
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        vertx.close();
    }
}
