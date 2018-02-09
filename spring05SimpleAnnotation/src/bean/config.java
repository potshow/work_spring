package bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *  config.xml 파일을 대신할 클래스 -> Config 클래스
 */

@Configuration	// 스프링 설정 클래스
public class config {

	@Bean		// config.xml 의 bean 앨리먼트
	public MyBean myBean() {		// 메서드명은 Bean 앨리먼트의 id 속성값
		// 리턴하는 객체의 클래스는 Bean 앨리먼트의 class 속성값
		return new MyBeanImpl();
	}
	
}
