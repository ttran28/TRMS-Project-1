package com.revature.beans;

import java.io.File;

public class Response {
	// Values stored in a Response object
	private int id;
	private int sender;
	private int receiver;
	private String responseDate;
	private String response;
	private File attachment;
	
	//An empty constructor
	public Response() {
		
	}

	// Getter and setter methods for the id field
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// Getter and setter methods for the sender field
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}

	// Getter and setter methods for the receiver field
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	// Getter and setter methods for the responseDate field
	public String getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	// Getter and setter methods for the response field
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	// Getter and setter methods for the attachment field
	public File getAttachment() {
		return attachment;
	}
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
}
