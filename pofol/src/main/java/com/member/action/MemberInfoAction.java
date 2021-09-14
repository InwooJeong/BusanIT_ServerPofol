package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;
import com.member.db.MemberDTO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("MemberInfo~");
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			
			return forward;
		}
		
		MemberDAO mdao = new MemberDAO();
		
		MemberDTO mdto = mdao.getMember(id);
		
		request.setAttribute("mdto", mdto);
		
		forward.setPath("./member/memberinfo.jsp");
		forward.setRedirect(false);
		return forward;
	}

}