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
		<h2><a href="./BoardWrite.bo">글 쓰기</a></h2>
		<hr><hr>
		<h2><a href="./Main.me">메인으로</a></h2>
		${nick } 이/가 접속 중
	<table align="center" border = "1">
			<tr>
				<td>글 번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>조회수</td>
				<td>작성일</td>
			</tr>
			<c:forEach var="boardList" items="${boardList }" >
			<c:set var="bnick" value="${boardList.nick }" scope="request"/>
			<tr>
				<td>${boardList.num }</td>
				<td>${boardList.nick }</td>
				<td>
				<c:if test="${boardList.re_lev gt 1 }">
				<c:forEach begin="1" end="${boardList.re_lev }" step="1">
				&nbsp;
				</c:forEach>
				</c:if>
				<c:if test="${boardList.re_lev gt 0 }">
				▶
				</c:if>
				<a href="./BoardContent.bo?num=${boardList.num }&pageNum=${pageNum }">
					${boardList.subject }<img src="./board/level.gif"></a></td>
				<td>${boardList.readcount }</td>
				<td>${boardList.written }</td>
			</tr>
			</c:forEach>
	</table>
	<c:if test = "${count ne 0}">
		<c:if test = "${startPage gt pageBlock }">
			<a href="./BoardList.bo?pageNum=${startPage-pageBlock }">[prev]</a>
		</c:if>
		<c:forEach begin="${startPage }" end="${endPage }" step="1" var="i">
			<a href="./BoardList.bo?pageNum=${i }">[${i }]</a>
		</c:forEach>
		<c:if test = "${endPage lt pageCount }">
			<a href="./BoardList.bo?pageNum=${startPage+pageBlock }">[next]</a>
		</c:if>
	</c:if>
</body>
</html>