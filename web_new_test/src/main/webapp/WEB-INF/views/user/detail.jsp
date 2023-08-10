<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 상세정보</title>
</head>
<body>
	<h1>유저 상세정보</h1>
	<div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<button type="button">수정</button>
			<button type="button">삭제</button>
		</sec:authorize>
	</div>
	<p>이름 : ${userDetails.name }</p>
	<p>이메일 : ${userDetails.email }</p>
	<p>
		권한 :
		<c:choose>
			<c:when test="${userDetails.auth eq 'admin' }">관리자</c:when>
			<c:when test="${userDetails.auth eq 'user' }">멤버</c:when>
			<c:when test="${userDetails.auth eq 'guest' }">게스트</c:when>
		</c:choose>
	</p>
	<p>
		<button type="button" onclick="location.href='/user/list'">목록으로</button>
	</p>
</body>
</html>