package com.sms.exception;

import java.util.Date;

public class ErrorDetails {

	private Date date;
	private String message;
	private String request;
	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorDetails(Date date, String message, String request) {
		super();
		this.date = date;
		this.message = message;
		this.request = request;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	
	
}
