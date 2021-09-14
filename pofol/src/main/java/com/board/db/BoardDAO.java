package com.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	public Connection getConnection() throws Exception{
		Context init = new InitialContext();
		
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/pofol");
		
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
	
	public void insertBoard(BoardDTO bdto){
		System.out.println("insertBoard(dto) 글쓰기 메서드");
		int num = 0;
		
		try {
			con = getConnection();
			
			sql = "select max(num) from board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt("max(num)") + 1;
			}
			
			System.out.println("글 번호 : " + num);
			
			sql = "insert into board "
					+ "values(?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bdto.getNick());
			pstmt.setString(3, bdto.getSubject());
			pstmt.setString(4, bdto.getContent());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			
			pstmt.executeUpdate();
			
			System.out.println("글 작성 완료~");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//////////////////////////////////////////////////////////////////
	
	public int getBoardCount(){
		int check = 0;
		
		try {
			con = getConnection();
			sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				check = rs.getInt(1);
			}
			System.out.println("게시판 글 개수는 : " +check);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return check;
		}
	//////////////////////////////////////////////////////////////////

	public ArrayList<BoardDTO> getBoardList(int startRow, int endRow){
		ArrayList<BoardDTO> BoardList = new ArrayList<BoardDTO>();
		
		try {
			con = getConnection();
			
			sql = "select * from (select row_number() over(order by "
					+ "re_ref desc, re_seq asc) as rn, num, nick, subject, content, "
					+ "readcount, re_ref, re_lev, re_seq, written from board "
					+ "order by re_ref desc, re_seq asc) "
					+ "where rn <= ? and rn >= ?";
			
			/*sql = "select * from board";*/
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, endRow);
			pstmt.setInt(2, startRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				BoardDTO bdto = new BoardDTO();
				
				bdto.setNum(rs.getInt(2));
				bdto.setNick(rs.getString(3));
				bdto.setSubject(rs.getString(4));
				bdto.setContent(rs.getString(5));
				bdto.setReadcount(rs.getInt(6));
				bdto.setRe_ref(rs.getInt(7));
				bdto.setRe_lev(rs.getInt(8));
				bdto.setRe_seq(rs.getInt(9));
				bdto.setWritten(rs.getDate(10));
				
				BoardList.add(bdto);
			}
			System.out.println("됨?");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return BoardList;
	}
	//////////////////////////////////////////////////////////////////	
	
	public void updateReadCount(int num){
		
		try {
			con = getConnection();
			
			sql = "update board set readcount = readcount + 1 where num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
			System.out.println(num+" 조회수 1 증가!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//////////////////////////////////////////////////////////////////
	
	public BoardDTO getBoard(int num){
		BoardDTO bdto = null;
		
		try {
			con = getConnection();
			
			sql = "select * from board where num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bdto = new BoardDTO();
				
				bdto.setNum(rs.getInt("num"));
				bdto.setNick(rs.getString("nick"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setContent(rs.getString("content"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setWritten(rs.getDate("written"));
			}
			System.out.println("여기까지 됨");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return bdto;
	}
	//////////////////////////////////////////////////////////////////	
	
	public void reInsertBoard(BoardDTO bdto){
		int num = 0;
				System.out.println(bdto.getRe_ref());
		try {
			con = getConnection();
			
			sql = "select max(num) from board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1) + 1;
			}
			
			sql = "update board set re_seq=re_seq+1 "
					+ "where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bdto.getRe_ref());
			pstmt.setInt(2, bdto.getRe_seq());
			
			pstmt.executeUpdate();
			
			System.out.println("됨?");

				sql = "insert into "
						+ "board(num, nick, subject, content, readcount, re_ref, re_lev, "
						+ "re_seq, written) "
						+ "values(?,?,?,?,?,?,?,?,sysdate)";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				pstmt.setString(2, bdto.getNick());
				pstmt.setString(3, bdto.getSubject());
				pstmt.setString(4, bdto.getContent());
				pstmt.setInt(5, bdto.getReadcount());
				pstmt.setInt(6, bdto.getRe_ref());
				pstmt.setInt(7, bdto.getRe_lev()+1);
				pstmt.setInt(8, bdto.getRe_seq()+1);
				
			
			System.out.println("passt?");
	
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//////////////////////////////////////////////////////////////////
	
	public void updateBoard(BoardDTO bdto){
		
		try {
			con = getConnection();
			
			sql = "update board set subject=?, content=? where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bdto.getSubject());
			pstmt.setString(2, bdto.getContent());
			pstmt.setInt(3, bdto.getNum());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//////////////////////////////////////////////////////////////////
	
	public void deleteBoard(BoardDTO bdto){
		try {
			con = getConnection();
			
			sql = "delete from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getNum());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
	}
	//////////////////////////////////////////////////////////////////
	
		
	}
	
