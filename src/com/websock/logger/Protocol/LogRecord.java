package com.websock.logger.Protocol;

import com.websock.logger.Configuration;

import java.util.ArrayList;

/**
 * Created by Robin on 2015-12-29.
 * <p>
 * Contains logging data to be sent as a single message.
 */
public class LogRecord {
    private ArrayList<IOLogger> io = new ArrayList<>();
    private ArrayList<LogUserCount> users = new ArrayList<>();
    private ServerTreeLog tree = new ServerTreeLog();
    private String namespace = Configuration.NAMESPACE;

    public void addIO(IOLogger data) {
        boolean found = false;

        for (IOLogger log : new ArrayList<>(io)) {
            if (data.getName().equals(log.getName())) {
                io.remove(log);
                io.add(data);
                found = true;
            }
        }

        if (!found)
            io.add(data);
    }

    public void addUserCount(LogUserCount data) {
        boolean found = false;

        for (LogUserCount log : new ArrayList<>(users)) {
            if (data.getName().equals(log.getName())) {
                users.remove(log);
                users.add(data);
                found = true;
            }
        }

        if (!found)
            users.add(data);
    }

    public void reset() {
        users.clear();
        io.clear();
        tree = new ServerTreeLog();
    }

    public LogRecord compile() {
        for (Server server : tree.getServers()) {
            for (LogUserCount count : users) {
                if (server.getName().equals(count.getName()))
                    server.setUsers(count.getCount());
            }
        }
        return this;
    }

    public ArrayList<IOLogger> getIo() {
        return io;
    }

    public void setIo(ArrayList<IOLogger> io) {
        this.io = io;
    }

    public ServerTreeLog getTree() {
        return tree;
    }

    public void setTree(ServerTreeLog tree) {
        this.tree = tree;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
