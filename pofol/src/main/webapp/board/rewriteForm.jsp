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
<%-- <c:set var="re_ref" value='${bdto.re_ref }'/>
<c:set var="re_lev" value='${bdto.re_lev }'/>
<c:set var="re_seq" value='${bdto.re_seq }'/> --%>
	<h1>답글 작성</h1>
		<fieldset>
			<legend>글쓰기</legend>
				<form action="./BoardRewriteAction.bo?pageNum=${pageNum }" method="post">
				<input type="hidden" name="num" value="${bdto.num }">
				<input type="hidden" name="re_ref" value="${param.re_ref }">
				<input type="hidden" name="re_lev" value="${param.re_lev }">
				<input type="hidden" name="re_seq" value="${param.re_seq }">
				
					글쓴이 : <input type="text" name="nick" value="${nick }" readonly><br>
					제목 : <input type="text" name="subject" value="[답글]"><br>
					내용 : <br><textarea rows="10" cols="20" name="content"></textarea><br>
						<input type="submit" value="작성">
				</form>
		</fieldset>
</body>
</html>