package annotation01;

import java.lang.reflect.Field;

public class IdExtractor {

	public String extractId(Object obj) {
		// 최종 리턴할 값을 저장할 변수
		String id = null;

		// 1. 파라미터로 전달받은 obj 객체의 클래스 정보를 가져와 clazz 변수에 담는다
		Class clazz = obj.getClass();

		// 2. clazz 변수에서 해당 클래스에 정의된 필드들의 목록을 배열에 담는다.
		Field[] fields = clazz.getDeclaredFields();

		// 3. for문을 이용하여 클래스의 필드들을 순차적으로 탐색한다.
		for (Field field : fields) {
			// 4. 만약에 OnjectId라는 애노테이션을 가진 필드라면
			if (field.getAnnotation(ObjectId.class) != null) {
				// 5. 해당 필드에 접근가능하도록 설정 (private 이지만 접근이 가능할 수 있게 한다.)
				field.setAccessible(true);
				try {
					// 6. 필드의 값을 가져온다.
					// 파라미터로 전달받은 obj의 해당 필드를 가져온다.
					Object value = field.get(obj);
					
					/*
					 *  7. 만약 필드값이 null이 아니라면 해당 값을 
					 *  리턴할 id 변수에 저장한다.
					 */
					if (value != null) {
						// 이미 String 타입이라는 것을 알기 때문에 이렇게 처리
						id = value.toString();
					} 

				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		// 8. 필드 값을 리턴 
		return id;
	}

}
