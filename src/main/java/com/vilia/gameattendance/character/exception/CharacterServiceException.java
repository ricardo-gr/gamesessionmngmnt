package com.vilia.gameattendance.character.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CharacterServiceException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2446198743789001072L;

	
	public CharacterServiceException(HttpStatusCode status, String reason, Throwable cause) {
		super(status, reason, cause);
	}

	public CharacterServiceException(HttpStatusCode status, String reason) {
		super(status, reason);
	}

	public CharacterServiceException(HttpStatusCode status) {
		super(status);
	}

	public CharacterServiceException(int rawStatusCode, String reason, Throwable cause) {
		super(rawStatusCode, reason, cause);
	}

	public CharacterServiceException(HttpStatusCode status, String string, Object obj) {
		super(status, string + " - " + obj.toString());
	}

}
