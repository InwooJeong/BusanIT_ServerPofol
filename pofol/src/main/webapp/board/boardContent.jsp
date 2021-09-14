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
<c:set var="pageNum" value='${param.pageNum }'/>
<c:set var="num" value='${bdto.num }' />
<c:set var="re_ref" value='${bdto.re_ref }'/>
<c:set var="re_lev" value='${bdto.re_lev }'/>
<c:set var="re_seq" value='${bdto.re_seq }'/>
	<table align="center" border="1">
		<tr>
			<td>글 번호</td><td>${bdto.num }</td>
			<td>조회 수</td><td>${bdto.readcount }</td>
		</tr>	
		<tr>
			<td>작성자</td><td>${bdto.nick }</td>
			<td>작성일</td><td>${bdto.written }</td>
		</tr>	
		<tr>
			<td>제목</td><td colspan="3">${bdto.subject }</td>
		</tr>	
		<tr>
			<td>내용</td><td colspan="3">${bdto.content }</td>
		</tr>	
		<tr>
			<td colspan="4">
			
			<c:if test = "${nick eq 'admin' or nick eq bdto.nick }">
			<input type="button" value="글 수정"
				onclick="location.href='./BoardUpdate.bo?num=${bdto.num }&pageNum=${pageNum }'">
			</c:if>
			
			<c:if test = "${nick eq 'admin' or nick eq bdto.nick }">
			<input type="button" value="글 삭제"
				onclick="location.href='./BoardDeleteAction.bo?num=${bdto.num }&pageNum=${pageNum }'">
			</c:if>
			
			<input type="button" value="답글 달기"
				onclick="location.href='./BoardRewrite.bo?num=${bdto.num }&pageNum=${pageNum }&re_ref=${bdto.re_ref }&re_lev=${bdto.re_lev }&re_seq=${bdto.re_seq }'">
			
			<input type="button" value="글 목록"
				onclick="location.href='./BoardList.bo?pageNum=${pageNum}'">	
			</td>
		</tr>	
	
	
	</table>
	
</body>
</html>