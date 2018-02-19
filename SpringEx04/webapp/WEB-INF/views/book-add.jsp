<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>도서 정보 추가</title>
</head>
<body>
	<h1>도서 정보 추가</h1>
	<form action="book-add.do" method="post">
		
		<div>
			<label>책 제목<input type="text" name="title"></label>
		</div>
		<div>
			<label>저자<input type="text" name="author"></label>
		</div>
		<div>
			<label>출판사<input type="text" name="publisher"></label>
		</div>
		<div>
			<label>가격<input type="text" name="price"></label>
		</div>
		<div>
			<label>책 설명<textarea name="description"></textarea></label>
		</div>
		
		<input type ="submit" value="책 등록">
		<input type="reset" value="입력 내용 초기화">
		<a href="book-list.do">도서 목록으로 이동</a>
		
	</form>
</body>
</html>