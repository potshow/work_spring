package com.koitt.book.dao;

import java.util.List;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

public interface BookDao {

	// 책 추가
	public void insert(Book book) throws BookException;
	
	// 책 isbn으로 책 불러오기
	public Book select(String isbn) throws BookException;
	
	// 전체 책 조회
	public List<Book> selectAll() throws BookException;
	
	// 책 삭제
	public void update(Book book) throws BookException;
	
	// 책 수정
	public void delete(String isbn) throws BookException;
	
	// 책 전체 삭제
	public void deleteAll() throws BookException;
	
	// 등록된 책 개수 가져오기
	public Integer getCount() throws BookException;
	
	// 자동증가한 ISBN 번호 가져오기
	public Integer selectLastInsertId() throws BookException;
	
}
