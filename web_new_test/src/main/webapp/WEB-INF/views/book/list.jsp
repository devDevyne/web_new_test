<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>책 목록</title>
<link rel="stylesheet"
	href="https://uicdn.toast.com/grid/latest/tui-grid.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
</head>
<body>
	<h1>책 목록</h1>
	<p>${auth }${username }</p>

	<form action="/logout" method="get">
		<input value="로그아웃" type="submit">
	</form>

	<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
		<button type="button" onclick="location.href='/user/list'">유저
			리스트</button>
	</sec:authorize>
	<div>
		<button type="button" class="booklist_excel_download_btn"
			id="excel_download_btn" onclick="location.href='/bookList_excel'">엑셀
			다운로드</button>
	</div>
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
					<td><a href="/book/detail?bookId=${row.bookId}">
							${row.title} </a></td>
					<td>${row.category}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3"
							value="${row.price}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<button type="button" onclick="location.href='/book/insert'">생성</button>
	</p>
	<div id="grid" style="width: 700px;"></div>
	<script>
		const grid = new tui.Grid({
			el : document.getElementById('grid'),
			columns : [ {
				header : '제목',
				name : 'title'
			}, {
				header : '카테고리',
				name : 'category'
			}, {
				header : '가격',
				name : 'price'
			} ],
		});

		$.ajax({
			url : "/bookList",
			method : "GET",
			dataType : "JSON",
			success : function(result) {
				grid.resetData(result);
			}
		});
	</script>
</body>
</html>
