package com.bridgelabz.fundoonotes.exception;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String message;
	
	public UserException(String message) {
		super(message);
	}
}
