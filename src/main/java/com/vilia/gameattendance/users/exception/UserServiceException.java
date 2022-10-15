package com.vilia.gameattendance.users.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserServiceException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2446198743789001072L;

	
	public UserServiceException(HttpStatusCode status, String reason, Throwable cause) {
		super(status, reason, cause);
	}

	public UserServiceException(HttpStatusCode status, String reason) {
		super(status, reason);
	}

	public UserServiceException(HttpStatusCode status) {
		super(status);
	}

	public UserServiceException(int rawStatusCode, String reason, Throwable cause) {
		super(rawStatusCode, reason, cause);
	}

	public UserServiceException(HttpStatusCode status, String string, Object obj) {
		super(status, string + " - " + obj.toString());
	}

}
