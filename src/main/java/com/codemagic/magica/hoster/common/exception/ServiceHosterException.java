package com.codemagic.magica.hoster.common.exception;

public class ServiceHosterException extends Exception {
	private static final long serialVersionUID = 2816232044938018425L;

	public ServiceHosterException(Throwable cause) {
		super(cause);
	}

	public ServiceHosterException(String message) {
		super(message);
	}
}
