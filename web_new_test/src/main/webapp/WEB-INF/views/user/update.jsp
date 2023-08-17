<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 수정</title>
</head>
<body>
	<h1>유저정보 수정</h1>
	<form method="POST" action="/user/udtUser">
		<p>
			이름 : <input type="text" name="name" value="${ userDetails.name }" />
		</p>
		<p>
			이메일 : <input type="text" name="emali" disabled
				value="${ userDetails.email }" />
		</p>
		<p>
			권한 : <select name="auth" id="auth">
				<option value="admin" >관리자</option>
				<option value="user" >멤버</option>
				<option value="guest" >게스트</option>
			</select>
		</p>
		<p>
			<input type="hidden" name="userId" value="${ userDetails.userId }" />
			<input type="submit" value="저장" />
		</p>
	</form>
</body>
</html>