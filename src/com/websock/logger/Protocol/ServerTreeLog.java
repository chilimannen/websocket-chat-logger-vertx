package com.websock.logger.Protocol;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Robin on 2015-12-28.
 * <p>
 * Contains a tree of all the registered servers.
 */
public class ServerTreeLog {
    public static final String ACTION = "logging.servers";
    private Collection<Server> servers = new ArrayList<>();
    private String name;

    public ServerTreeLog() {
    }

    public Collection<Server> getServers() {
        return servers;
    }

    public void setServers(ArrayList<Server> servers) {
        this.servers = servers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
