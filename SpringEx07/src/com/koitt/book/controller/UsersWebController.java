package com.koitt.book.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.book.model.FileException;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;
import com.koitt.book.service.FileService;
import com.koitt.book.service.UsersService;

@Controller
public class UsersWebController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FileService fileService;
	
	// 사용자 목록
	@RequestMapping(value="/users-list.do", method=RequestMethod.GET)
	public String list(Model model, HttpServletRequest req) {
		List<Users> list = null;
		
		try {
			list = usersService.list();
			
		} catch (UsersException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("list", list);
		model.addAttribute("uploadPath", fileService.getUploadPath(req));
		
		return "admin/users-list";
	}
	// 사용자 추가 (가입하기 화면)
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	// 사용자 추가 (가입하기 화면에서 전달받은 값으로 사용자 생성)
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(HttpServletRequest req,
			String email,
			String password,
			String name,
			@RequestParam("attachment") MultipartFile attachment) {
		
		// 클라이언트로부터 전달받은 값으로 객체 생성
		Users users = new Users(null, email, password, name, null);
		
		try {
			String filename = fileService.add(req, attachment);
			
			users.setAttachment(filename);
			
			usersService.add(users);
			String encodedName = URLEncoder.encode(users.getName(), "UTF-8");
			
			return "redirect:join-confirm.do?name=" + encodedName;
			
		} catch (FileException e) {
			System.out.println(e.getMessage());
			req.setAttribute("error", "file");
		} catch (UsersException e) {
			System.out.println(e.getMessage());
			req.setAttribute("error", "server");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			req.setAttribute("error", "encoding");
		}
		
		return "redirect:index.html";
	}
	
	// 가입 확인
	@RequestMapping(value="/join-confirm.do", method=RequestMethod.GET)
	public String joinConfirm(Model model, String name) {
		model.addAttribute("name", name);
		return "join-confirm";
	}
	

	// 로그인
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "login";
	}

	// 접근 제한
	@RequestMapping(value="/access-denied.do", method=RequestMethod.GET)
	public String accessDenied(Model model) {

		model.addAttribute("email", usersService.getPrincipal().getUsername());

		return "access-denied";
	}

	// 로그아웃
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		usersService.logout(req, resp);

		return "redirect:/login.do?logout=true";
	}

	// 도서 정보 수정 화면
	@RequestMapping(value="/users-modify.do", method=RequestMethod.GET)
	public String modify(HttpServletRequest request) {
		Users users = null;
		String uploadPath = null;
		try {
			String email = usersService.getPrincipal().getUsername();
			users = usersService.detailByEmail(email);
			uploadPath = fileService.getUploadPath(request);

		} catch (UsersException e) {
			request.setAttribute("error", "server");
		}

		request.setAttribute("users", users);
		request.setAttribute("uploadPath", uploadPath);

		return "users-modify";
	}

	@RequestMapping(value="/users-modify.do", method=RequestMethod.POST)
	public String modify(HttpServletRequest request,
			String oldPassword,
			String newPassword,
			String name,
			@RequestParam("attachment") MultipartFile attachment) {

		try {
			
			// 비번확인
			boolean isMatched = usersService.isPasswordMatched(oldPassword);
			
			// 비밀번호가 일치하면 사용자 정보를 변경한다.
			if (isMatched) {
				String email = usersService.getPrincipal().getUsername();

				Users oldUsers = usersService.detailByEmail(email);

				String filename = fileService.add(request, attachment);
				
				Users users = new Users(oldUsers.getNo(), email, newPassword, name, filename);

				String toDeleteFile = usersService.modify(users);

				// 기존 프사 삭제
				fileService.remove(request, toDeleteFile);


			} else {
				
				return "redirect:users-modify.do?error=password";
				
			}
		} catch (UsersException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}

		return "redirect:users-modify-confirm.do";

	}
	
	@RequestMapping(value="users-modify-confirm.do", method=RequestMethod.GET)
	public String modifyConfirm() {
		return "users-modify-confirm";
	}

}
