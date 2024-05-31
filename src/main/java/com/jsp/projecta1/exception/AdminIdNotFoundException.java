package com.jsp.projecta1.exception;

import lombok.Getter;

@Getter
public class AdminIdNotFoundException extends RuntimeException {

	private String message;

	public AdminIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
