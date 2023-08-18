<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
	$(document).ready(function() {
		$('#userlist').DataTable({});
	});
</script>
<title>유저 리스트</title>
</head>
<body>
	<h1>유저 목록</h1>
	<div>
		<button type="button" onclick="location.href='/userList_excel'">엑셀 다운로드</button>
	</div>
	<table id="userlist" class="userlist">
		<thead>
			<tr>
				<td>이름</td>
				<td>이메일</td>
				<td>권한</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${userList }">
				<tr>
					<td><a href="/user/detail?userId=${row.userId }">${row.name }</a></td>
					<td>${row.email }</td>
					<td><c:choose>
							<c:when test="${row.auth eq 'admin' }">관리자</c:when>
							<c:when test="${row.auth eq 'user' }">멤버</c:when>
							<c:when test="${row.auth eq 'guest' }">게스트</c:when>
						</c:choose></td>
					<%-- <sec:authorize access="hasRole('ROLE_ADMIN')">
						<td><button type="button">수정</button></td>
						<td><button type="button">삭제</button></td>
					</sec:authorize> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<button type="button" onclick="location.href='/user/create'">유저
			등록</button>
	</sec:authorize>
	<button type="button" onclick="location.href='/book/list'">북
		리스트</button>
</body>
</html>