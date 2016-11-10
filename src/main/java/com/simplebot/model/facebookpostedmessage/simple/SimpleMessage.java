package com.simplebot.model.facebookpostedmessage.simple;

public class SimpleMessage {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SimpleMessage(String text) {
		this.text = text;
	}

	public SimpleMessage() {
	}

	@Override
	public String toString() {
		return "SimpleMessage{" +
				"text='" + text + '\'' +
				'}';
	}
}
