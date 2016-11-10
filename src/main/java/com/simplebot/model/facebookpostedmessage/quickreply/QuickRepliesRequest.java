package com.simplebot.model.facebookpostedmessage.quickreply;

import com.simplebot.model.facebookreceivedmessage.entryInner.messagingInner.UserId;

public class QuickRepliesRequest {
	private UserId recipient;
	private Message message;

	public QuickRepliesRequest() {
	}

	public UserId getRecipient() {
		return recipient;
	}

	public void setRecipient(UserId recipient) {
		this.recipient = recipient;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public static QuickRepliesRequest getRequest() {
		return new QuickRepliesRequest();
	}

	public QuickRepliesRequest(UserId recipient, Message message) {
		this.recipient = recipient;
		this.message = message;
	}



	@Override
	public String toString() {
		return "QuickRepliesRequest{" +
				"recipient=" + recipient +
				", message=" + message +
				'}';
	}
}
