<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	Connection con = null;

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	
	Boolean connect=false;
	
	try{
		Class.forName(driver);
		con = DriverManager.getConnection(url, "inwoo", "inwoo");
		
		connect=true;
		
		con.close();
	}catch(Exception e){
		connect=false;
		e.printStackTrace();
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%if(connect) {%>
		연결 성공
	<%}else{ %>
		연결 실패
	<%} %>	
	
</body>
</html>