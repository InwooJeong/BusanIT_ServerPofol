<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>회원 로그인</legend>
		<form action="./MemberLoginAction.me" name="fr" method="post">
			ID : <input type="text" name="id"><br> PW : <input
				type="password" name="pass"><br>
			<hr>
			<input type="submit" value="login"> <input type="button"
				name="joinBtn" value="회원가입"
				onclick="location.href='./MemberJoin.me'">
		</form>
	</fieldset>
</body>
</html>