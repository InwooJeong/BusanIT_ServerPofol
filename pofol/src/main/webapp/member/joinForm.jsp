<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WELCOME!</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<fieldset>
		<legend>회원가입</legend>
			<form action = "./MemberJoinAction.me" name="fr" method="post">
				ID : <input type="text" name="id" required><br>
				비밀번호 : <input type="password" name="pass" required><br>
				닉네임 : <input type="text" name="nick" required><br>

				출생년도 : <select id="yob" name="yob"></select><br><br>
				
				<input type="radio" name="gender" value="m">남성
				<input type="radio" name="gender" value="f">여성<br><br>
				
				E-MAIL : <input type="email" name="email"><br>
				
				<hr><hr>
				<input type="submit" value="회원가입">
				<input type="reset" value="다시작성">
				<input type="button" value="취소" 
					onclick="location.href='./'">
			</form>
	</fieldset>
	
	
	<script>
		$(document).ready(function(){
			//alert("JQUERY~");
			setYOBBox();
		});
		
		function setYOBBox(){
			let dt = new Date();
			let year = "";
			let com_year = dt.getFullYear();
			
			$("#yob").append("<option value='0000'>출생연도를 선택하세요!</option>");
			
			for(let y=(com_year);y>=(com_year-140);y--){
				$("#yob").append("<option value='"+y+"'>"+y+"년"+"</option>");
			}
		}
	</script>
</body>
</html>