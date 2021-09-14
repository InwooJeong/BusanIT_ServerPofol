package com.dataroom.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataroomDAO {
		
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	public Connection getConnection() throws Exception{
		Context init = new InitialContext();
		
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/project");
		
		con = ds.getConnection();
		
		System.out.println("DB 연결 완료! : " + con);
		return con;
	}
	
	public void closeDB(){
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			
			System.out.println("자원 해제 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int upload(String fileName, String fileRealName){
		
		try {
			con = getConnection();
			sql = "insert into dataroom values (?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
