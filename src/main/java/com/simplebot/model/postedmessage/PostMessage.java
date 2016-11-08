package com.simplebot.model.postedmessage;

/**
 * Created by Vladislav on 11/4/2016.
 */


public class PostMessage {

    private Recipient recipient;

    private Message message;

    public Recipient getRecipient() {
        return recipient;
    }

    public PostMessage(Recipient recipient, Message message) {
        this.recipient = recipient;
        this.message = message;
    }

    public PostMessage() {
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
