package com.koitt.practice;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class JUnitTestUsingSet {
	
	@Autowired
	private ApplicationContext context;

	static ApplicationContext contextObject = null;
	
	Set<ApplicationContext> testObjects = new HashSet<ApplicationContext>();
	
	
	@Test public void test1() {
		/*
		 * 컬렉션의 사이즈가 0 초과인 것은 적어도
		 * 애플리케이션 컨텍스트 객체가 하나 이상 존재하는 것이므로
		 * 그때부터 객체를 비교해서 같은 것이 있는지 조사를 하는 것이다.
		 */
		if (testObjects.size() > 0) {
			assertThat(testObjects, hasItem(this.context));
		}
		
	}

}
