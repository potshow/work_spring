package bean;

public class PersonService {
	
	// 2-1. 필드 만들기
	private Person person;
	
	
	// 2-2. person 필드에  대한 setter		(autowire의 byName을 이용하기 위해)
	// byName은 setter 중에서 같은 이름을 가진 setter를 찾는 것이고,
	// byType은 setter 중에서 파라미터 타입이 같은 setter를 찾는 것이다.
	
	public void setPerson(Person person) {
		this.person = person;
	}

	
	public PersonService(Person person) {
		this.person = person;
	}
	
	
	// 2-2. person 객체의 getName() 호출하여 그 값을 출력하는 메서드 만들기
	public String getMsg() {
			System.out.println("안녕하세요.");
		return person.getName();
	}
	
}
