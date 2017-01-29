package com.epam.jobmatch.service.exception;


/**
 * Thrown when some kind of exceptional situation
 * in service layer has occurred
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException(){
		super();
	}
	
	public ServiceException(String message){
		super(message);
	}	
	
	public ServiceException(Exception e){
		super(e);
	}
	
	public ServiceException(String message, Exception e){
		super(message, e);
	}
}
