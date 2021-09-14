package com.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	public Connection getConnection() throws Exception {
		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/pofol");

		con = ds.getConnection();

		System.out.println("DB 연결 con : " + con);

		return con;
	}

	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

			System.out.println("자원 해제");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int insertMember(MemberDTO mdto) {
		int num = 0;
		int chk = 0;

		try {
			con = getConnection();
			System.out.println("DB 연결 성공@@@@@@");

			sql = "select max(num) from member";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt("max(num)") + 1;
			}

			sql = "INSERT INTO member values(?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, mdto.getId());
			pstmt.setString(3, mdto.getPass());
			pstmt.setString(4, mdto.getNick());
			pstmt.setInt(5, mdto.getYob());
			pstmt.setString(6, mdto.getGender());
			pstmt.setString(7, mdto.getEmail());
			pstmt.setTimestamp(8, mdto.getReg_date());

			chk = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return chk;
	}
	///////////////////////////////////////////////////////////////////////////

	public int idCheck(String id, String pass) {
		int check = 0;

		try {
			con = getConnection();

			sql = "select pass from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (pass.equals(rs.getString("pass"))) {
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}

			System.out.println("완료~ : " + check);

			MemberDTO mdto = new MemberDTO();

			sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mdto = new MemberDTO();

				mdto.setNum(rs.getInt("num"));
				mdto.setId(rs.getString("id"));
				mdto.setPass(rs.getString("pass"));
				mdto.setNick(rs.getString("nick"));
				mdto.setGender(rs.getString("gender"));
				mdto.setYob(rs.getInt("yob"));
				mdto.setReg_date(rs.getTimestamp("reg_date"));
			}

			mdto.setNick(rs.getString("nick"));
			System.out.println(rs.getString("nick"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return check;
	}
	//////////////////////////////////////////////////////////////////////////////////

	public MemberDTO getMember(String id) {

		MemberDTO mdto = null;

		try {
			con = getConnection();

			sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mdto = new MemberDTO();

				mdto.setNum(rs.getInt("num"));
				mdto.setId(rs.getString("id"));
				mdto.setPass(rs.getString("pass"));
				mdto.setNick(rs.getString("nick"));
				mdto.setGender(rs.getString("gender"));
				mdto.setYob(rs.getInt("yob"));
				mdto.setReg_date(rs.getTimestamp("reg_date"));
			}
			System.out.println(id + "정보 : " + mdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mdto;
	}
	////////////////////////////////////////////////////////////////////////

	public int updateMember(String pass, String npass, String id) {
		int check = -1;

		try {
			con = getConnection();
			sql = "select pass from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (pass.equals(rs.getString("pass"))) {

					sql = "update member set pass=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, npass);
					pstmt.setString(2, id);

					pstmt.executeUpdate();

					check = 1;
				} else {
					check = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return check;
	}
	//////////////////////////////////////////////////////////////////////

	public int deleteMember(String id, String pass) {
		int check = -1;

		try {
			con = getConnection();
			sql = "select pass from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (pass.equals(rs.getString("pass"))) {

					sql = "delete from member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);

					pstmt.executeUpdate();

					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		System.out.println(check + " : check 탈퇴");

		return check;
	}

	//////////////////////////////////////////////////////////////////////////////

	public List<MemberDTO> getMemberList() {

		List<MemberDTO> memberList = new ArrayList<MemberDTO>();

		try {
			con = getConnection();

			sql = "select * from member order by num";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberDTO mdto = new MemberDTO();

				mdto.setNum(rs.getInt("num"));
				mdto.setId(rs.getString("id"));
				mdto.setPass(rs.getString("id"));
				mdto.setNick(rs.getString("nick"));
				mdto.setGender(rs.getString("gender"));
				mdto.setYob(rs.getInt("yob"));
				mdto.setReg_date(rs.getTimestamp("reg_date"));

				memberList.add(mdto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return memberList;
	}
	//////////////////////////////////////////////////////////////////////////////////

	public void purgeeMember(String pid) {

		try {
			con = getConnection();
			sql = "delete from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pid);

			pstmt.executeUpdate();

			System.out.println("추방 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//////////////////////////////////////////////////////////////////////

}
