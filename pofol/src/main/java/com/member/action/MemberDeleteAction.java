package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("MemberDeleteAction");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
		}
		
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.deleteMember(id, pass);
		
		if(check == 0){
			response.sendRedirect("./dpal.me");
			return null;
		}else if(check == -1){
			response.sendRedirect("./dial.me");
			return null;
		}

		session.invalidate();
		
		response.sendRedirect("./dsal.me");
		
		return null;
	}

}
