package com.simplebot.model.facebookpostedmessage;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/4/2016.
 */
public class Message {
    @JsonProperty("text")
    private String text;

    public Message(String text) {
        this.text = text;
    }

    public Message() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
