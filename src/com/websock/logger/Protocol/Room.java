package com.websock.logger.Protocol;

/**
 * Created by Robin on 2015-12-18.
 *
 * Room data stored in the Registry.
 */
public class Room {
    private String room;
    private Integer hit = 1;

    public Room() {
    }

    public void hit() {
        if (hit == Integer.MAX_VALUE)
            hit = 0;

        hit += 1;
    }

    public Integer getHits() {
        return hit;
    }

    public String getName() {
        return room;
    }

    public void setName(String name) {
        this.room = name;
    }
}
