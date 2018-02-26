package com.koitt.book.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetails;

import com.koitt.book.model.Authority;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

/*
 * C : create
 * R : read
 * U : update
 * D : delete
 */
public interface UsersService {

	public List<Users> list() throws UsersException;

	public Users detail(Integer no) throws UsersException;

	public void add(Users users) throws UsersException;

	public String remove(Integer no, String password) throws UsersException;

	public String modify(Users users) throws UsersException;

	// 이메일로 사용자의 모든 정보 가져오기
	public Users detailByEmail(String email) throws UsersException;

	// 사용자 권한 가져오기
	public Authority getAuthority(Integer id) throws UsersException;

	// principal 객체 가져오기
	public UserDetails getPrincipal();

	// 로그아웃
	public void logout(HttpServletRequest req, HttpServletResponse resp);

	// 비밀번호 일치 여부 확인하는 메서드
	public boolean isPasswordMatched(String oldPassword) throws UsersException;


}
