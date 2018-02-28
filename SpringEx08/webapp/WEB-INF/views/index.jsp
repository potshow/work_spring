<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head><title>도서관리 페이지</title></head>
<body>
	<h1>JK 도서관리</h1>
	<a href="<c:url value='/book/book-list.do'/>">관리 페이지로 이동 >></a><br>
	<a href="<c:url value='/admin/users-list.do'/>">도서 등록자 목록 >></a><br>
	<sec:authorize access="!hasRole('ADMIN') and !hasRole('USER')">
		<a href="<c:url value='/join.do'/>">회원 가입하기</a><br>
		<a href="<c:url value='/login.do'/>">로그인하기</a><br>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
		<a href="<c:url value='/users-modify.do'/>">회원정보변경</a><br>
		<a href="<c:url value='/logout.do'/>">로그아웃</a><br>
	</sec:authorize>
</body>
</html>