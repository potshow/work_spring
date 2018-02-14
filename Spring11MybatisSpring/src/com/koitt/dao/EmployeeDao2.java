package com.koitt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.model.Employee;

@Repository
public class EmployeeDao2 {

	@Autowired
	private SqlSession session;
	
	public Employee getEmployee(int empno) {
		Employee emp = session.selectOne("com.koitt.model.Employee.select", empno);
		/*
		 * 싱글턴으로 객체를 생성했기 때문에 단 하나만 존재하는 객체이므로
		 * 객체를 닫을 경우 다음 번 호출 때 selectOne이 동작하지 않기 때문에
		 * SqlSession 객체를 닫으면 안된다.
		 * ↓닫으면 문제발생
		 */
		//session.close(); 
		return emp;
	}
}
