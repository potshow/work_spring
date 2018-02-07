package annotation03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Invoker {
	public void invoke(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 1. 파라미터로 전달받은 obj 객체의 클래스 정보를 가져온다.
		Class<?> clazz = obj.getClass();
		
		// 2. 해당 클래스에 존재하는 메서드들을 가져온다.
		Method[] methods = clazz.getDeclaredMethods();
		
		// 3. seq 애노테이션으로 지정한 순서에 따라 메서드를 저장할 배열을 생성한다.
		Method[] methods2 = new Method[methods.length];
		
		// 4. 클래스에 존재하는 메서드들을 순차적으로 순회한다.
		for (Method m : methods) {
			// 해당 메서드에 Call 애노테이션이 존재하는 경우
			// 애노테이션 존재확인...
			if (m.isAnnotationPresent(Call.class)) {
				// 5. 애노테이션에 적은 seq 값을 가져오기 위해 annotation
				Call anno = m.getAnnotation(Call.class);
				
				// 6. 순서에 따라 우리가 만든 메서드 배열에 메서드를 집어넣는다.
				methods2[anno.seq() - 1] = m;
			}
		}
		// 7. 순서에 따라 넣은 메서드들을 순차적으로 호출한다.
		for (Method m : methods2) {
			m.invoke(obj, null);
		}
	}
	
}
