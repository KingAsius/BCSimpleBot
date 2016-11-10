package com.simplebot.model.facebookpostedmessage.simple;


import com.simplebot.model.facebookreceivedmessage.entryInner.messagingInner.UserId;

public class SimpleMessageRequest {
	private UserId recipient;
	private SimpleMessage message;

	public UserId getRecipient() {
		return recipient;
	}

	public void setRecipient(UserId recipient) {
		this.recipient = recipient;
	}

	public SimpleMessage getMessage() {
		return message;
	}

	public void setMessage(SimpleMessage message) {
		this.message = message;
	}

	public SimpleMessageRequest(UserId recipient, SimpleMessage message) {
		this.recipient = recipient;
		this.message = message;
	}

	public SimpleMessageRequest() {
	}

	@Override
	public String toString() {
		return "SimpleMessageRequest{" +
				"recipient=" + recipient +
				", message=" + message +
				'}';
	}
}
