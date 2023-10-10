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
	<%-- <div class="login-wrapper">
		<h2>Login</h2>
		<form method="post" action="/login" name="login-form" id="login-form">
			<input type="email" name="userId" placeholder="Email"> <input
				type="password" name="userPw" placeholder="Password">
			<p style="color: red;">${requestScope.errormsg }</p>
			<!-- requestScope : 서블릿 기준으로 클라이언트가 서버에 요청한 HttpServletRequest 객체 -->
			<input type="submit" value="Login">
		</form>
	</div> --%>

	<div class="container" id="container">
		<div class="form-container sign-up-container">
			<form action="/signUp" method="post">
				<h1>Create Account</h1>
			<!--<div class="social-container">
       				<a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
         			<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
        			<a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      			</div> -->
				<span>or use your email for registration</span> 
				<input type="text" placeholder="Name" name="name" /> 
				<input type="email" placeholder="Email" name="email" />
				<input type="password" placeholder="Password" name="passwd" />
				<input type="hidden" name="auth" value="user" />
				<button>Sign Up</button>
			</form>
		</div>

		<div class="form-container sign-in-container">
			<form action="/login" method="post" name="login-form" id="login-form">
				<h1>Sign in</h1>
				<!-- <div class="social-container">
        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
      </div> -->
				<span>or use your account</span> <input type="email"
					placeholder="Email" name="userId" /> <input type="password"
					placeholder="Password" name="userPw" /> <a href="#">Forgot
					your password?</a>
				<p style="color: red;">${requestScope.errormsg }</p>
				<!-- requestScope : 서블릿 기준으로 클라이언트가 서버에 요청한 HttpServletRequest 객체 -->
				<input type="submit" value="Sign In">
			</form>
		</div>
		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1>Welcome Back!</h1>
					<p>To keep connected with us please login with your personal
						info</p>
					<button class="ghost" id="signIn">Sign In</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1>Hello, Friend!</h1>
					<p>Enter your personal details and start journey with us</p>
					<button class="ghost" id="signUp">Sign Up</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		const signUpButton = document.getElementById('signUp');
		const signInButton = document.getElementById('signIn');
		const container = document.getElementById('container');
	
		signUpButton.addEventListener('click', () => {
		  container.classList.add("right-panel-active");
		});
	
		signInButton.addEventListener('click', () => {
		  container.classList.remove("right-panel-active");
		});
	</script>

</body>
</html>