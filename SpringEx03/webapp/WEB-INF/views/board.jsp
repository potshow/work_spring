<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 내용 조회</title>
</head>
<body>
	<h2>게시글 내용 조회</h2>
	<ul>
		<li>${ board.no }</li>
		<li>${ board.title }</li>
		<li>${ board.content }</li>
		<li>${ board.userNo }</li>
		<li>${ board.regdate }</li>
	</ul>
</body>
</html>