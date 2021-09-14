<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>글 작성</h1>
		<fieldset>
			<legend>글쓰기</legend>
				<form action="./BoardWriteAction.bo" method="post">
					글쓴이 : <input type="text" name="nick" value="${nick }" readonly><br>
					제목 : <input type="text" name="subject"><br>
					내용 : <br><textarea rows="10" cols="20" name="content"></textarea><br>
						<input type="submit" value="작성">
				</form>
		</fieldset>
</body>
</html>