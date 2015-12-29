package com.websock.logger.Protocol;

/**
 * Created by Robin on 2015-12-28.
 *
 * Sent to the logging service.
 */
public class LogUserCount {
    public final static String ACTION = "logging.users";
    private String name;
    private String type;
    private Integer count;

    public LogUserCount() {
    }

    public LogUserCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
