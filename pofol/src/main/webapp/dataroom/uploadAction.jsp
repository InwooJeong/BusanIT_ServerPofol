<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.dataroom.db.*" %>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	
		String directory = application.getRealPath("/upload/");
		//실제 물리적 경로
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest = 
				new MultipartRequest(request, directory, maxSize, encoding,
						new DefaultFileRenamePolicy());
		
		String fileName = multipartRequest.getOriginalFileName("file");
		String fileRealName = multipartRequest.getFilesystemName("file");
		
		new DataroomDAO().upload(fileName, fileRealName);
		
		out.write("파일명 : " + fileName + "<br>");
		out.write("실제 파일명 : " + fileRealName + "<br>");
	
	%>
	<hr><hr>
	<a href="../Main.me">메인으로</a>
</body>
</html>