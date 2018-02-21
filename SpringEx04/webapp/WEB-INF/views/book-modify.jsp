<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>책 정보 수정 페이지</title>
</head>
<body>
	<h1>책 정보 수정</h1>
	<form action="book-modify.do" method="post">
	<div>책 제목 <input type="text" name="title" value="${ book.title }"></label>
		</div>
		<div>
			<label>저자    <input type="text" name="author" value="${ book.author }"></label>
		</div>
		<div>
			<label>출판사  <input type="text" name="publisher" value="${ book.publisher }"></label>
		</div>
		<div>
			가격 : ${ book.price }
		</div>
		<div>
			<label>책 설명 <textarea name="description">${ book.description }</textarea></label>
		</div>
		
		<input type="hidden" name="isbn" value="${ book.isbn }">
		<input type="submit" value="책 정보 수정">
		<input type="reset" value="입력 내용 초기화">
		<a href="book-list.do">도서 목록으로 이동</a>
	
	
	</form>
</body>
</html>