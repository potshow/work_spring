package annotation01;

public class TestDrive {

	public static void main(String [] agrs) {
		UserA userA = new UserA("s001", "KIM");
		UserB userB = new UserB("1", "Hong");
		
		IdExtractor idExtractor= new IdExtractor();
		String idA = idExtractor.extractId(userA);
		String idB = idExtractor.extractId(userB);
		
		System.out.println("UserA 의 id 값: " + idA);
		System.out.println("UserB 의 id 값: " + idB);
		
		
	}
	
}
