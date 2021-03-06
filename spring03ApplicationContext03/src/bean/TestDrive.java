package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDrive {
	public static void main(String[] args) {
		
		// 1. Spring 설정파일 불러오기
		ApplicationContext context = 
				new FileSystemXmlApplicationContext("./src/config/config.xml");
		
		// 2. bean 객체 생성 방법
		
		// 2-1. 인터페이스를 사용하지 않고 bean 이름만으로 생성하는 방법
		PersonImpl bean01 = (PersonImpl) context.getBean("person");
		// 2-2. 인터페이스를 사용하지 않고 bean 이름과 클래스 타입으로 생성하는 방법
		PersonImpl bean02 = context.getBean("person", PersonImpl.class);
		// 2-3. 인터페이스 타입으로 빈 객체를 생성하는 방법
		Person bean03 = context.getBean(Person.class);
		// 2-4. 인터페이스의 클래스 정보
		Person bean04 = context.getBean(PersonImpl.class);
		
		// 주소값 비교
		System.out.println(bean01 == bean02);
		
		bean01.sayHello();
		bean02.sayHello();
		bean03.sayHello();
		bean04.sayHello();
		
		System.out.println(bean01);
		System.out.println(bean02);
		System.out.println(bean03);
		System.out.println(bean04);
		
	}
}
