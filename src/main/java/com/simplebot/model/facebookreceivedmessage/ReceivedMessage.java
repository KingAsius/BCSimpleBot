package com.simplebot.model.facebookreceivedmessage;

import com.simplebot.model.facebookreceivedmessage.entryInner.messagingInner.UserId;

import java.util.List;

public class ReceivedMessage {

	private String object;
	private List<Entry> entry;
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public List<Entry> getEntry() {
		return entry;
	}
	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "ReceivedMessage{" +
				"object='" + object + '\'' +
				", entry=" + entry +
				'}';
	}

	public String getSenderId() {
		return entry.get(0).getMessaging().get(0).getSender().getId();
	}

	public UserId getSender() {
		return entry.get(0).getMessaging().get(0).getSender();
	}

	public String getPayloadInQuickReply() {
		return this.getEntry().get(0).getMessaging().get(0).getMessage().getQuickReply().getPayload();
	}

	public String getTextFromMessage() {
		return this.getEntry().get(0).getMessaging().get(0).getMessage().getText();
	}
}
