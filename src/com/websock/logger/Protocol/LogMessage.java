package com.websock.logger.Protocol;

/**
 * Created by Robin on 2015-12-29.
 *
 * Generic logmessage for unpacking.
 */
public class LogMessage {
    private String type;
    private String name;

    public LogMessage() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
