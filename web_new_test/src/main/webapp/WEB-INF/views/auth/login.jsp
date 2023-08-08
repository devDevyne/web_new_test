<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${path}/resources/css/login.css" rel="stylesheet" />
<title>Login Page</title>
</head>
<body>
	<div class="login-wrapper">
		<h2>Login</h2>
		<form method="post" action="/login" name="login-form" id="login-form">
			<input type="email" name="userId" placeholder="Email"> <input
				type="password" name="userPw" placeholder="Password">
			<p style="color: red;">${requestScope.errormsg }</p>
			<!-- requestScope : 서블릿 기준으로 클라이언트가 서버에 요청한 HttpServletRequest 객체 -->
			<input type="submit" value="Login">
		</form>
	</div>
</body>
</html>