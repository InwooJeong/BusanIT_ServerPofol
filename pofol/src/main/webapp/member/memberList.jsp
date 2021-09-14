<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:if test = "${id eq null }">
			<c:redirect url="./MemberLogin.me"/>
		</c:if>
		
		<h1>회원 목록</h1>
		<form action="./MemberPurgee.me">
		<table align="center" border = "1">
			<tr>
				<td>select</td>
				<td>number</td>
				<td>id</td>
				<td>password</td>
				<td>nickname</td>
				<td>gender</td>
				<td>YoB</td>
				<td>sign up day</td>
			</tr>
			<c:forEach var="memberList" items="${memberList }" >
			<tr>
				<c:if test = "${memberList.id ne 'admin' }">
				<td><input type = "radio" name="pur" id="pur" value="${memberList.id }"></td>
				</c:if>
				
				<c:if test = "${memberList.id eq 'admin' }">
				<td>Admin</td>
				</c:if>
				<td>${memberList.num }</td>
				<td>${memberList.id }</td>
				<td>${memberList.pass }</td>
				<td>${memberList.nick }</td>
				<td>${memberList.gender }</td>
				<td>${memberList.yob }</td>
				<td>${memberList.reg_date }</td>
			</tr>
			</c:forEach>
		</table>
		<hr><hr>
		<input type="submit" value="purgee!">
		</form>
		<hr><hr>
		<h3><a href="./Main.me">Main</a></h3>
</body>
</html>