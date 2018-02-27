package com.koitt.junit;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * 테스트 컨텍스트가 매번 주입해주는 애플리케이션 컨텍스트는
 * 항상 같은 객체인지 테스트하는 테스트 클래스 작성 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/koitt/junit/config.xml")
public class JUnitTest {
	
	/*
	 * 테스트 컨텍스트가 매번 주입해주는 애플리케이션 컨텍스트는
	 * 항상 같은 객체인지 테스트로 확인해보기.
	 */
	@Autowired
	private ApplicationContext context;
	
	static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
	static ApplicationContext contextObject = null;
	
	@Test public void test1() {
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
	
	@Test public void test2() {
		// assertTrue: 파라미터 값이 true이면 테스트 통과, false이면 테스트 실패
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@Test public void test3() {
		// either, is, or 메서드 이용: test1 메서드의 assertThat 메서드 부분과 동일한 뜻
		assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
		contextObject = this.context;
	}

}
