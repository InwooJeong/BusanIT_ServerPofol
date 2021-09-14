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
	
	<fieldset>
		<legend>기존 비밀번호와 변경 할 비밀번호를 입력하세요.</legend>
			<form action="./MemberUpdateAction.me" method="post">
				기존 비밀번호 : <input type="password" name="pass">
				변경 할 비밀번호 : <input type="password" name="npass">
				<input type="submit" value="확인">
			</form>
	</fieldset>
</body>
</html>