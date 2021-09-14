<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="uploadAction.jsp" method="post" enctype="multipart/form-data">
		파일 : <input type="file" name="file"><br>
		<input type="submit" value="Upload"><br>
	</form>
	<br>
	<a href="fileDownload.jsp">파일 다운로드 페이지</a>
	<hr><hr>
	<a href="../Main.me">메인으로</a>
</body>
</html>