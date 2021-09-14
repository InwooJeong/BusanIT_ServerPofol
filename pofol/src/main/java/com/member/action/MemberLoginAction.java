package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberDAO;
import com.member.db.MemberDTO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("MemberLoginAction~");
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.idCheck(id,pass);
		System.out.println(id);
		if(check == 0){
			response.sendRedirect("./pwal.me");
			return null;
		}else if(check == -1){
			response.sendRedirect("./idal.me");
			return null;
		}
		
		HttpSession session = request.getSession();
		
		MemberDTO mdto = mdao.getMember(id);
		
		session.setAttribute("id", id);
		session.setAttribute("nick", mdto.getNick());
		
		System.out.println("@"+mdto.getNick());
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		return forward;
	}
}
