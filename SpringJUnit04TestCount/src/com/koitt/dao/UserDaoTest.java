package com.koitt.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.koitt.vo.User;

public class UserDaoTest {
	
	// 픽스처(fixture): 테스트를 수행하는데 필요한 정보나 객체
	// setUp() 메서드에서 만드는 객체를 테스트 메서드에서 사용할 수 있도록 필드로 선언
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	/*
	 * Before 애노테이션: JUnit이 제공하는 애노테이션. 
	 * @Test 메서드가 실행되기 전에 먼저 실행되어야하는 메서드를 정의함
	 */
	@Before 
	public void setUp() {
		
		ApplicationContext context = 
				new GenericXmlApplicationContext("/com/koitt/config/applicationContext.xml");

		this.dao = context.getBean("userDao", UserDao.class);
		
		User user1 = new User("curling", "김영미", "1234");
		User user2 = new User("speed", "이승훈", "1234");
		User user3 = new User("skeleton", "윤성빈", "1234");
		
	}
	
	

	@Test	// JUnit에게 테스트용 메소드임을 알려준다.
	public void addAndGet() throws SQLException { // JUnit 테스트 메소드는 반드시 public으로 선언
		

		// 전체 삭제
		dao.deleteAll();

		// 정말 전체삭제 됐는지 확인하기 위해 getCount로 확인
		assertThat(dao.getCount(), is(0));


		// 생성한 사용자를 데이터베이스에 저장
		dao.add(user1);
		dao.add(user2);

		//생성한 데이터가 1개인지 확인
		assertThat(dao.getCount(), is(2));

		// 첫 번째 user의 id로 get()을 실행하면 첫번째 User의 값을 가진 객체를 돌려주는지 확인한다.
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));

		// 두 번째 User도 같은 방법으로 검증
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));


		/*
		 *  assertThat 
		 *  첫번째 파라미터: 비교할 원본
		 *  두번째 파라미터: 비교할 대상
		 *  원본과 대상이 같다면 넘어가고 같지 않다면 테스트 실패로 간주
		 *  (아래 assertThat 모두 테스트 성공해야 addAndGet 테스트는 성공한 것으로 JUnit에서 출력)
		 *  
		 *  */


	}

	//UserDao의 getCount 메서드에 대한 검증
	@Test
	public void count() throws SQLException {
		
		// 동일한 테스트 결과를 얻기 위해 테이블 내용을 비워준다.
		dao.deleteAll();
		// 실제 비워졌는지 getCount 메서드를 이용하여 확인
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	public static void main(String[] args) {
		/*
		 *  파라미터: JUnit Test 메소드가 정의된 클래스 위치를 알려준다.
		 *  실행을 하면 콘솔로 테스트 결과를 알려준다.
		 */
		JUnitCore.main("com.koitt.dao.UserDaoTest");
	}
	
	/*
	 * 주의할 점: 두 개의 테스트가 어떤 순서로 실행될지는 알 수 없다.
	 * 모든 테스트는 실행 순서에 상관없이 독립적으로 항상 동일한 결과를
	 * 낼 수 있도록 한다.
	 */
	
	// 테스트 중에 발생할 것으로 기대하는 예외 클래스를 지정해준다.
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		ApplicationContext context = 
				new GenericXmlApplicationContext("/com/koitt/config/applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		/*
		 * 이 테스트 메서드 실행 중에 예외가 발생해야 한다.
		 * 예외가 발생하지 않는다면 테스트는 실패
		 */
		
		dao.get("unknown_id");
		
		/*
		 * getUserFailure() 테스트 코드에 나타난 기능
		 * 			단계					내용					코드
		 * 조건		어떤 조건을 가지고		가져올 사용자 정보가	dao.deleteAll();
		 * 									존재하지 않는 경우에	assertThat(dao.getCount(), is(0);
		 * 
		 * 행위		무엇을 할 때			존재하지 않는 id로		dao.get("unknown_id");
		 * 결과		어떤 결과가 나온다		get()을 실행하면 특별	@Test(expected=
		 * 									한 예외가 던져진다.		EmptyResultDataAccessException.class)
		 */
		
		//위와 같이 작성하면 기능 설계, 코드 구현, 테스트 세 가지 작업을 동시에 할 수 있다.
		
		/*
		 * 테스트 주도 개발 (TDD: Test Driven Development)
		 * TDD의 기본 원칙: 실패한 테스트를 성공시키기 위한 목적이 아닌 코드는 만들지 않는다.
		 * TDD는 테스트를 먼저 만들고 그 테스트가 성공하도록 하는 코드만 만드는 식으로
		 * 진행하기 때문에 테스트를 빼먹지 않고 꼼꼼하게 만들어낼 수 있다.
		 */
	}
}
