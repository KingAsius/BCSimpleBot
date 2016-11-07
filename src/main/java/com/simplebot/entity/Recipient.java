package com.simplebot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/4/2016.
 */
public class Recipient {
    @JsonProperty("id")
    private String id;

    public Recipient(String id) {
        this.id = id;
    }

    public Recipient() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
