package com.koitt.book.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.book.dao.AuthorityDao;
import com.koitt.book.dao.UsersDao;
import com.koitt.book.model.Authority;
import com.koitt.book.model.AuthorityId;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDao usersDao;

	private AuthorityDao authorityDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<Users> list() throws UsersException {
		return usersDao.selectAll();
	}
	@Override
	public Users detail(Integer no) throws UsersException {
		return usersDao.select(no);
	}

	@Override
	public void add(Users users) throws UsersException {
		String encode = passwordEncoder.encode(users.getPassword());
		users.setPassword(encode);

		Authority auth = new Authority(AuthorityId.USER.getAuthorityId(), AuthorityId.USER.name());

		Set<Authority> auths = new HashSet<>();
		auths.add(auth);
		users.setAuthorities(auths);

		usersDao.insert(users);

		Integer no = usersDao.selectLastInsertId();

		users.setNo(no);

		usersDao.insertAuthority(users);
	}

	@Override
	public String remove(Integer no, String password) throws UsersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify(Users users) throws UsersException {

		Users item = usersDao.select(users.getNo());
		String filename = item.getAttachment();
		usersDao.update(users);

		users.setPassword(passwordEncoder.encode(users.getPassword()));

		usersDao.update(users);

		return filename;
	}

	@Override
	public Users detailByEmail(String email) throws UsersException {
		return usersDao.selectByEmail(email);
	}

	@Override
	public Authority getAuthority(Integer id) throws UsersException {
		return authorityDao.select(id);
	}

	@Override
	public UserDetails getPrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Object principal = auth.getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserDetails) principal;
		}

		return null;
	}

	@Override
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, resp, auth);
		}

	}

	@Override
	public boolean isPasswordMatched(String oldPassword) throws UsersException {
		String email = this.getPrincipal().getUsername();
		Users users = usersDao.selectByEmail(email);

		return passwordEncoder.matches(oldPassword, users.getPassword());
	}

}