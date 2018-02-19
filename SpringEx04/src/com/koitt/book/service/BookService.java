package com.koitt.book.service;

import java.util.List;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

public interface BookService {
	
	// 책 추가
	public void add(Book book) throws BookException;
	
	// isbn으로 책 불러오기
	public Book detail(String isbn) throws BookException;
	
	// 전체 책 조회
	public List<Book> list() throws BookException;
	
	// 책 수정
	public void modify(Book book) throws BookException;
	
	// 책 삭제
	public void remove(String isbn) throws BookException;

}
