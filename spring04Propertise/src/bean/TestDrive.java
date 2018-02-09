package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDrive {

	public static void main(String[] args) {
		
		// 1. 스프링 설정파일 불러오기
		ApplicationContext context = new FileSystemXmlApplicationContext("./src/config/config.xml");
		
		// 2. 객체 생성
		MyBean bean = context.getBean("mtbean", MyBean.class);
		
		// 3. 일반 메서드 호출
		bean.sayHello();
		
	}
	
}
