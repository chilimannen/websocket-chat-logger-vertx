package com.websock.logger.Protocol;


/**
 * Created by Robin on 2015-12-28.
 * <p>
 * Measures in/out messages.
 */
public class IOLogger {
    public static final String ACTION = "logging.io";
    private Integer in = 0;
    private Integer out = 0;
    private String name;

    public IOLogger() {
    }

    public void in() {
        in += 1;
    }

    public void out() {
        out += 1;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }

    public Integer getOut() {
        return out;
    }

    public void setOut(Integer out) {
        this.out = out;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void reset() {
        this.in = 0;
        this.out = 0;
    }
}
