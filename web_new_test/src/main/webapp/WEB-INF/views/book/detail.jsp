<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>책 상세</title>
</head>
<body>
	<h1>책 상세</h1>
	<p>제목 : ${ detailBookVO.title }</p>
	<p>카테고리 : ${ detailBookVO.category }</p>
	<p>
		가격 :
		<fmt:formatNumber type="number" maxFractionDigits="3"
			value="${ detailBookVO.price}" />
	</p>

	<p>
		<a href="/book/update?bookId=${bookId }">수정</a>
	</p>
	<form method="POST" action="/book/delete">
		<input type="hidden" name="bookId" value="${bookId }" /> <input
			type="submit" value="삭제" />
	</form>
	<p>
		<a href="/book/list">목록으로</a>
	</p>
</body>
</html>
