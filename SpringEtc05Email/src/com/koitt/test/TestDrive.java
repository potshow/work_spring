package com.koitt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.koitt.model.Users;
import com.koitt.service.MailService;

public class TestDrive {
	
	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("com/koitt/config/applicationContext.xml");
		
		Users user = new Users("jihyo_hello@naver.com", "강예은");
		
		MailService service = context.getBean(MailService.class);
		
		service.sendEmail(user);
		
	}
	
}
