package com.cy.pj.sys.service.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 7520052048206558208L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
