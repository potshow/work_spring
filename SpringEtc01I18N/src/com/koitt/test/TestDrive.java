package com.koitt.test;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDrive {
	public static void main(String[] args) {
		
		// Spring 설정파일 가져오기
		ApplicationContext context =
				new ClassPathXmlApplicationContext("com/koitt/config/config.xml");
	
		// Example 타입으로 빈 객체 찾아서 가져오기
		Example example = context.getBean(Example.class);
		
		// example 객체의 excute 메서드 호출해서 번역
		example.excute();
		
		// 애플리케이션 컨텍스트 객체에서 국제화된 문자열을 가져오는 방법
		String msg01 = context.getMessage("greeting", null, "hello", Locale.ENGLISH);
		System.out.println(msg01);
	}

}
