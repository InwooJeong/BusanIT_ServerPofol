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
		
		<h3>${nick } 환영합니다~!</h3>
		
		<input type="button" value="logout"
				onclick="location.href='./MemberLogout.me'"/>
				
		<hr><hr>
		
		<div style="background: red">
		<a href="./MemberInfo.me" style="color: white">회원 정보 조회</a>
		</div>
		<hr><hr>
		<div style="background: orange">
		<a href="./MemberUpdate.me" style="color: white">비밀 번호 수정</a>
		</div>
		<hr><hr>
		<div style="background: yellow">
		<a href="./MemberDelete.me" style="color: black">회원 탈퇴</a>
		</div>
		<hr><hr>
		<div style="background: green">
		<a href="./BoardList.bo" style="color: white">게시판 가기</a>
		</div>
		<hr><hr>
		<div style="background: blue">
		<a href="./dataroom/fileDownload.jsp" style="color: white">자료실 가기</a>
		</div>
		<hr><hr>
		
		<c:if test = "${id eq 'admin' }">
			<div style="background: darkblue">
			<a href="./MemberList.me" style="color: white">모든 회원 보기</a>
			</div>
			<hr><hr>
			<div style="background: purple">
			<a href="./dataroom/index.jsp" style="color: white">자료 올리기</a>
			</div>
		</c:if>
		
</body>
</html>