package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestDrive {

	public static void main(String[] args) {
		
		// MyBean과 TestDrive 간의 의존성이 생김
		MyBean bean = new MyBean();
		bean.sayHello();
		
		// 의존성이 없도록 하기 위해 Spring의 DI를 사용
		// 1. 스프링 설정파일을 불러온다.
		ApplicationContext context = new GenericXmlApplicationContext("/config/applicationContext.xml");
		
		/*
		 * 2. 애플리케이션 컨텍스트를 통해서 my
		 * mybean이라는 이름으로 MyBean 객체를 요청 후
		 * 생성한 객체를 bean02 변수에 저장
		 */
		MyBean bean02 = (MyBean) context.getBean("mybean");
		
		// 3. bean02 객체를 이용하여 메서드 호출
		bean02.sayHello();
		
	}
	
}
