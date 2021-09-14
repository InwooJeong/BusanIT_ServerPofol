package com.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.db.MemberDAO;
import com.member.db.MemberDTO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("MemberJoinAction~!");
		
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO mdto = new MemberDTO();
		
		mdto.setId(request.getParameter("id"));
		mdto.setPass(request.getParameter("pass"));
		mdto.setNick(request.getParameter("nick"));
		mdto.setYob(Integer.parseInt(request.getParameter("yob")));
		mdto.setEmail(request.getParameter("email"));
		mdto.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(mdto.toString());
		
		MemberDAO mdao = new MemberDAO();
		
		int chk = mdao.insertMember(mdto);
		
		if(chk!=1) {
			response.sendRedirect("./jial.me");
			return null;
		}
		
		mdao.insertMember(mdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin2.me");
		forward.setRedirect(true);
		
		return forward;
	}

}