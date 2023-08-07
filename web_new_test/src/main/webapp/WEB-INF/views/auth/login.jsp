<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="login-wrapper">
		<h2>Login</h2>
		<form method="post" action="/login" name="loginform" id="loginform">
			<input type="text" name="userId" placeholder="Email"> <input
				type="password" name="userPw" placeholder="Password">
			<label for="remember-check"> <input type="checkbox"
				id="remember-check">아이디 저장하기
			</label> <input type="submit" value="Login">
		</form>
	</div>
</body>
</html>