package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.BoardDAO;
import com.board.db.BoardDTO;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("BoardWriteAction~");
		
		request.setCharacterEncoding("UTF-8");
		
		BoardDTO bdto = new BoardDTO();
		
		bdto.setNick(request.getParameter("nick"));
		bdto.setSubject(request.getParameter("subject"));
		bdto.setContent(request.getParameter("content"));
		
		BoardDAO bdao = new BoardDAO();
		
		bdao.insertBoard(bdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		return forward;
	}

}
