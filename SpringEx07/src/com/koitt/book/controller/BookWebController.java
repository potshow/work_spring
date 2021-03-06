package com.koitt.book.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.model.FileException;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;
import com.koitt.book.service.BookService;
import com.koitt.book.service.FileService;
import com.koitt.book.service.UsersService;

@Controller
@RequestMapping("/book")
public class BookWebController {

	@Autowired
	private BookService service;

	@Autowired
	private UsersService usersService;

	@Autowired
	private FileService fileService;

	// 책 추가
	@RequestMapping("/book-list.do")
	public String list(Model model) {
		List<Book> list = null;
		try {
			list = service.list();
		} catch (BookException e) {
			model.addAttribute("error", "server");
		}

		model.addAttribute("list", list);

		return "book-list";
	}

	// 책 조회
	@RequestMapping(value = "/book-detail.do", method = RequestMethod.GET)
	public String detail(Model model,
			HttpServletRequest request,
			@RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		String filename = null;
		String imgPath = null;
		String uploadPath = null;
		try {
			book = service.detail(isbn);

			filename = book.getAttachment();
			if (filename != null && !filename.trim().isEmpty()) {
				filename = URLDecoder.decode(filename, "UTF-8");
			}

			imgPath = fileService.getImgPath(request, filename);
			uploadPath = fileService.getUploadPath(request);

		} catch (BookException e) {
			model.addAttribute("error", "server");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "encoding");
		}

		model.addAttribute("book", book);
		model.addAttribute("filename", filename);

		if (imgPath != null && !imgPath.trim().isEmpty()) {
			model.addAttribute("imgPath", imgPath);
		}
		model.addAttribute("uploadPath", uploadPath);

		return "book-detail";
	}

	// 책추가
	@RequestMapping(value = "/book-add.do", method = RequestMethod.GET)
	public String add(Model model) {

		// 사용자 번호 book에 담음.
		String email = usersService.getPrincipal().getUsername();
		try {

			Users users = usersService.detailByEmail(email);

			users.setPassword(null);

			model.addAttribute("users", users);

		} catch (UsersException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		return "book-add";
	}

	// 책 추가 후 책목록 이동
	@RequestMapping(value = "/book-add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request,
			String title,
			String author,
			String publisher,
			Integer price,
			String description,
			Integer userNo,
			@RequestParam("attachment") MultipartFile attachment) {

		Book book = new Book();
		book.setTitle(title);
		book.setUserNo(userNo);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPrice(price);
		book.setDescription(description);

		try {
			String filename = fileService.add(request, attachment);
			book.setAttachment(filename);

			service.add(book);

		} catch (BookException e) {
			request.setAttribute("error", "server");
		} catch (FileException e) {
			request.setAttribute("error", "file");
		}
		return "redirect:book-list.do";
	}

	// 책 수정
	@RequestMapping(value = "/book-modify.do", method = RequestMethod.GET)
	public String modify(Model model,
			@RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		try {
			book = service.detail(isbn);
		} catch (BookException e) {
			model.addAttribute("error", "server");
		}
		model.addAttribute("book", book);

		return "book-modify";
	}

	// 수정 후 글목록 이동
	@RequestMapping(value = "/book-modify.do", method = RequestMethod.POST)
	public String modify(HttpServletRequest request,
			Integer isbn,
			String title,
			String author,
			String publisher,
			Integer price,
			String description,
			@RequestParam("attachment") MultipartFile attachment) {

		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPrice(price);
		book.setDescription(description);

		try {

			String filename = fileService.add(request, attachment);
			book.setAttachment(filename);

			String toDeleteFilename = service.modify(book);
			fileService.remove(request, toDeleteFilename);

		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}
		return "redirect:book-list.do";
	}

	// 책 삭제

	@RequestMapping(value = "/book-remove.do", method = RequestMethod.GET)
	public String removeConfirm(Model model,
			@RequestParam(value="isbn", required=true) String isbn) {

		model.addAttribute("isbn", isbn);

		return "book-remove-confirm";
	}

	@RequestMapping(value = "/book-remove.do", method = RequestMethod.POST)
	public String remove(Model model, String isbn, HttpServletRequest request) {
		try {
			String toDeleteFilename = service.remove(isbn);
			fileService.remove(request, toDeleteFilename);

		} catch (BookException e) {
			model.addAttribute("error", "server");
		} catch (FileException e) {
			model.addAttribute("error", "file");
			e.printStackTrace();
		}
		return "redirect:book-list.do";
	}

}
