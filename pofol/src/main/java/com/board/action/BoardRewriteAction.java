package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.BoardDAO;
import com.board.db.BoardDTO;

public class BoardRewriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("Ankunft");
		request.setCharacterEncoding("UTF-8");
		String pageNum = request.getParameter("pageNum");
		System.out.println(request.getParameter("re_ref"));
		int re_ref = Integer.parseInt(request.getParameter("re_ref"));
		int re_lev = Integer.parseInt(request.getParameter("re_lev"));
		int re_seq = Integer.parseInt(request.getParameter("re_seq"));
		BoardDTO bdto = new BoardDTO();
		
		bdto.setNick(request.getParameter("nick"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setContent(request.getParameter("content"));
		bdto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		bdto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		bdto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		
		BoardDAO bdao = new BoardDAO();
		
		System.out.println(bdto);
		
		bdao.reInsertBoard(bdto);
		
		System.out.println(pageNum);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo?pageNum="+pageNum+"");
		forward.setRedirect(true);
		return forward;
	}

}
