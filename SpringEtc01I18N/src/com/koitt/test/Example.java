package com.koitt.test;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Example {
	
	private MessageSource messages;
	
	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}
	
	public void excute() {
		String message01 =
				 this.messages.getMessage("greeting", null, "hello", Locale.KOREAN);
		
		String message02 =
				this.messages.getMessage("argument.required", new Object[]{"Dog"}, 
						"Cat", Locale.KOREAN);
		
		System.out.println(message01);
		System.out.println(message02);
				
	}

}
