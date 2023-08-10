<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<html>
<head>
<title>책 수정</title>
</head>
<body>
	<h1>책 수정</h1>
	<form method="POST" action="/book/udtBook">
		<p>
			제목 : <input type="text" name="title" value="${ detailBookVO.title }" />
		</p>
		<p>
			카테고리 : <input type="text" name="category" value="${ detailBookVO.category }" />
		</p>
		<p>
			가격 : <input type="text" name="price" value="${ detailBookVO.price }" />
		</p>
		<p>
			<input type="hidden" name="bookId" value="${ detailBookVO.bookId }" />
			<input type="submit" value="저장" />
	</form>
</body>
</html>
