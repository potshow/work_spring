package com.koitt.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.koitt.model.Employee;

public class TestDrive {
	public static void main(String[] args) {
		// 1. MyBatis 설정파일 경로 저장
		String resource = "com/koitt/config/mybatis.xml";
		
		try {
			// 2. 경로에 있는 mybatis.xml 파일 불러오기
			Reader reader = Resources.getResourceAsReader(resource);
			
			// 3. myBatis의 SqlSession 객체를 가져오기 위한 과정
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(reader, "development");
			SqlSession session = factory.openSession();
					
			/*
			 * 4. SqlSession 객체를 이용하여 SQL문 실행
			 * selectOne: select SQL문 사용할 때, select 결과가 하나의 행인 경우
			 * - 첫번째 파라미터 : mapper의 namespace + select 앨리먼트의 id 값
			 * - 두번째 파라미터 : select 앨리먼트로 전달할 값
			 */
			Employee emp = session.selectOne("com.koitt.model.Employee.select1", 7698);
			Employee emp2 = session.selectOne("com.koitt.model.Employee.select2", 7698);
			
			// 5. 사용 후 객체 정리
			session.close();
			
			// 6. SQL문 실행 후 호출
			System.out.println(emp);
			System.out.println(emp2);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}
	
}
