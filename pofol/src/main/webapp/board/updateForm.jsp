<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<fieldset>
			<legend>글 수정</legend>
				<form action="./BoardUpdateAction.bo?pageNum=${pageNum }" method="post">
					<input type="hidden" name="num" value="${bdto.num }">
					글쓴이 : <input type="text" name="nick" value="${bdto.nick }" readonly><br>
					제목 : <input type="text" name="subject" value="${bdto.subject }"><br>
					내용 : <br><textarea rows="10" cols="20" name="content"></textarea><br>
						<input type="submit" value="수정">
				</form>
		</fieldset>
</body>
</html>