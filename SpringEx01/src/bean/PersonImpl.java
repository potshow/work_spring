package bean;

public class PersonImpl implements Person {

	// 1-1. 필드 만들기
	private String name;
	
	// 1-2. 기본 생성자 만들기
	public PersonImpl() { }
	
	// 1-3. 필드 초기화하는 생성자 만들기
	public PersonImpl(String name) {
		this.name = name;
	}
	
	// 1-4. name 필드 getter, setter 만들기
	
	
	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
