package com.simplebot.model.facebookpostedmessage.quickreply;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Message {

	private String text;

	@JsonProperty(value = "quick_replies")
	private List<QuickReply> quickReplies;

	public Message() {
	}

	public Message(String text, List<QuickReply> quickReplies) {
		this.text = text;
		this.quickReplies = quickReplies;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<QuickReply> getQuickReplies() {
		return quickReplies;
	}

	public void setQuickReplies(List<QuickReply> quickReplies) {
		this.quickReplies = quickReplies;
	}

	@Override
	public String toString() {
		return "Message{" +
				"text='" + text + '\'' +
				", quickReplies=" + quickReplies +
				'}';
	}
}
