package com.koitt.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koitt.book.model.Authority;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersService service;

	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		
		try {
			Users users = service.detailByEmail(email);
			
			if (users == null) {
				throw new UsernameNotFoundException("해당 사용자를 찾지 못했습니다.");
			}
			
			return new User(users.getEmail(),
					users.getPassword(),
					true,
					true,
					true,
					true,
					this.getGrantedAuthorities(users));
			
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}		
		
		return null;
	}
	
	// 사용자에게 권한을 부여하기위해 권한 목록을 리턴하는 메소드
	private List<GrantedAuthority> getGrantedAuthorities(Users users) {
		
		// 최종적으로 스프링에게 전달할 리스트 (리스트 타입은 GrantedAuthority)
		List<GrantedAuthority> auths = new ArrayList<>();
		
		for (Authority item : users.getAuthorities()) {
			auths.add(new SimpleGrantedAuthority("ROLE_" + item.getName()));
		}
		
		System.out.println(users.getEmail() + " 사용자의 권한: " + auths);
		
		return auths;
	}
}






