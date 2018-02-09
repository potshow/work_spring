package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDrive {
	public static void main(String[] args) {
		// 1. 스프링 설정 불러오기
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(config.class);
		
		// 2. Bean 객체 생성
		MyBean bean = context.getBean("myBean", MyBean.class);
		bean.sayHello();
		
	}
}
