<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 목록 조회</title>
</head>
<body>
	<h1>도서 목록 조회 화면입니다.</h1>
	<a href="book-add.do">책 추가하기</a>
	<table>
		<thead>
			<tr>
				<th>ISBN</th>
				<th>제목</th>
				<th>저자</th>
				<th>출판사</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ list }" var="book">
				<tr>
					<td>${ book.isbn }</td>
					<td><a href="book-detail.do?isbn=${ book.isbn }">${ book.title }</a></td>
					<td>${ book.author }</td>
					<td>${ book.publisher }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>