package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;

public class MemberPurgeeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("MemberPurgeeAction~");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String pid = request.getParameter("pur");
		
		System.out.println("id : "+id);
		System.out.println("pid : "+pid);
		
		ActionForward forward = new ActionForward();
		
		if(id == null || !id.equals("admin")){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			
			return forward;
		}
		
		MemberDAO mdao = new MemberDAO();
		
		mdao.purgeeMember(pid);
		
		response.sendRedirect("./purg.me");
		
		return null;
	}

}
