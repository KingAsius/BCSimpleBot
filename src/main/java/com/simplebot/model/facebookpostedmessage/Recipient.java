package com.simplebot.model.facebookpostedmessage;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/4/2016.
 */
public class Recipient {
    @JsonProperty("id")
    private long id;

    public Recipient(long id) {
        this.id = id;
    }

    public Recipient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
