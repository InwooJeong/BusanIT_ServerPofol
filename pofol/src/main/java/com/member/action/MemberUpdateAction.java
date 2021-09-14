package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("MemberUpdateAction~");
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		String pass = request.getParameter("pass");
		String npass = request.getParameter("npass");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberDAO mdao = new MemberDAO();
		int check = mdao.updateMember(pass, npass, id);
		
		if(check == 0){
			response.sendRedirect("./peal.me");
			return null;
		}
		
		session.setAttribute("id", id);
		
		if(check == 1){
			response.sendRedirect("./pcal.me");
			return null;
		}
		
		return null;
		
	}

}