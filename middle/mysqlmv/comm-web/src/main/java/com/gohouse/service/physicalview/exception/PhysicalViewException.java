package com.gohouse.service.physicalview.exception;

public class PhysicalViewException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errcode;
	private String message;
	
	public PhysicalViewException(int errcode, String message,  Throwable e){
		
		//super(message, e);
		
		this.errcode = errcode;
		this.message = message;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
