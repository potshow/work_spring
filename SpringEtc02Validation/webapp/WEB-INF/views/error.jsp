<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>!! ERROR !! 유효성 검사 오류 페이지</title>
</head>
<body>
	<h1>유효성 검사 오류 페이지</h1>
	<p>
		<Spring:bind path="customer.name">
			${ status.expression }: ${ status.errorMessages[0] }
		</Spring:bind>
	</p>
	<p>
		<Spring:bind path="customer.age">
			${ status.expression }: ${ status.errorMessages[0] }
		</Spring:bind>
	</p>
</body>
</html>