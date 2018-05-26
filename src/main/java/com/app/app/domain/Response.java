package com.app.app.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Response {
	
	private String content;
	private String contentType;
	
	@Autowired
	public Response(String content, String contentType) {
		this.content = content;
		this.contentType = contentType;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	

}
