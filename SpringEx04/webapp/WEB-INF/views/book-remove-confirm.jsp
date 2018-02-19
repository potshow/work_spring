<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 정보 삭제 확인</title>
</head>
<body>
	<h1>삭제 확인</h1>
	<p>삭제하실 책의 isbn 이 ${ isbn } 번이 맞습니까?</p>
	
	<form action="book-remove.do" method="post">
		<input type="hidden" name="isbn" value="${ isbn }">
		<input type="submit" value="네. 맞습니다.">
	</form>
	<a href="book-list.do">도서 목록으로 이동</a>
</body>
</html>