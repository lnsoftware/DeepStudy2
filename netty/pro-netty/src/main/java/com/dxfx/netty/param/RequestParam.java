package com.dxfx.netty.param;



public class RequestParam {
	
	private String command;
	
	private Object content;
	
	private  long id;
	

	
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	
	
	
	
	
	
	
}
