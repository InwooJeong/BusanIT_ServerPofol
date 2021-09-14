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
	
	<h1>회원 정보</h1>
	ID : ${id } <hr>
	Nickname : ${mdto.nick } <hr>
	성별 : ${mdto.gender } <hr>
	출생년도 : ${mdto.yob } <hr>
	가입일 : ${mdto.reg_date } <hr>
	
	<h3><a href="./Main.me">Main Page~</a></h3>
</body>
</html>