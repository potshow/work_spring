package bean;

public class MyBean {

	private String greeting;
	
	//필드
	public MyBean(String greeting) {
		this.greeting = greeting;
	}
	
	//생성자

	public void sayHello() {
		System.out.println(this.greeting + "~~~~~~~~~~~~~~~~~~~~~~~");
	}

	//getter
	public String getGreeting() {
		return greeting;
	}

	//setter
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	
	
}
