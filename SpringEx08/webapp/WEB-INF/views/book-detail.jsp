<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>도서 정보 상세 보기</title>
</head>
<body>
	<h1>도서 정보 상세 보기</h1>
		<dl>
			<dt>ISBN</dt><dd>${ book.isbn }</dd>
			
			<dt>작성자</dt><dd>${ book.users.name } (${ book.users.email })</dd>
			<dt>작성자 사진</dt><dd><img src="${ uploadPath }/${book.users.attachment}"></dd>
			
			<dt>책 제목</dt><dd>${ book.title }</dd>
			<dt>저자</dt><dd>${ book.author }</dd>
			<dt>출판사</dt><dd>${ book.publisher }</dd>
			<dt>가격</dt><dd>${ book.price }</dd>
			<dt>설명</dt><dd>${ book.description }</dd>
			
			<c:if test="${ !empty filename }">
				<dt>첨부파일</dt>
				<dd><a href = "download.do?filename=${ book.attachment }">${ filename }</a></dd>
			</c:if>
			<c:if test = "${ !empty imgPath }">
				<img src="${ imgPath }" alt="이미지 파일 출력위치">
			</c:if>
		</dl>
		<a href="book-list.do">책 목록으로 이동</a>
		<a href="book-modify.do?isbn=${ book.isbn }">수정하기</a>
		<a href="book-remove.do?isbn=${ book.isbn }">삭제하기</a>
</body>
</html>