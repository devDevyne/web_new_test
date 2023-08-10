<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 유저 등록</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function check_email() {
		var email = $('#email').val(); //입력한 이메일 
		$.ajax({
			url : '/check_email', //Controller에서 요청 받을 주소
			type : 'post', //POST 방식으로 전달
			data : {
				"email" : email
			// "변수" : 변수 value
			},
			success : function(data) {
				// 데이터 전송이 성공적으로 끝났을 때 
				if (email != "") {
					if (data == "Y") {
						alert("사용 가능한 이메일입니다.");
						$('#register').removeAttr('disabled');
					} else {
						alert("이미 사용중인 이메일입니다.");
					}
				} else {
					alert("이메일을 입력해주세요.");
				}
			},
			error : function(request, error) {
				alert("code : " + request.status + "\n" + "message : "
						+ request.responseText + "\n" + "error : " + error);
			}
		});
	};
</script>
</head>
<body>
	<h1>유저 등록</h1>
	<form method="post" action="/createUser">
		<p>
			이름 : <input type="text" name="name" required />
		</p>
		<p>
			이메일 : <input type="email" name="email" id="email" required /> 
			<input type="button" onclick="check_email()" value="중복검사" />
		</p>
		<p>
			패스워드 : <input type="password" name="passwd" required />
		</p>
		<p>
			권한 : <select name="auth" id="auth">
				<option value="admin">관리자</option>
				<option value="user" selected>멤버</option>
				<option value="guest">게스트</option>
			</select>
		</p>
		<p>
			<input type="submit" id="register" disabled value="등록" />
			<input type="reset" value="리셋" />
			<button type="button" onclick="location.href='/user/list'">취소</button>
	</form>
</body>
</html>