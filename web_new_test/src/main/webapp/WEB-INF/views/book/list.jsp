<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>책 목록</title>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>책 목록</h1>
	<p>${auth }${username }</p>
	<form action="/logout" method="get">
		<input value="로그아웃" type="submit">
	</form>
	<table id="booklist" class="booklist">
		<thead>
			<tr>
				<td>제목</td>
				<td>카테고리</td>
				<td>가격</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${bookListVO}">
				<tr>
					<td><a href="/detail?bookId=${row.bookId}"> ${row.title} </a></td>
					<td>${row.category}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${row.price}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/insert">생성</a>
	</p>
</body>
<script>
	$(document).ready(function() {
		$('#booklist').DataTable();
	});
</script>
</html>
