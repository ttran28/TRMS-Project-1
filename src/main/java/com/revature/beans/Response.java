package com.revature.beans;

import java.sql.Date;

public class Response {
	// Values stored in a Response object
	private int id;
	private int sender;
	private int receiver;
	private Date responseDate;
	private String response;
	
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
	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	// Getter and setter methods for the response field
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
