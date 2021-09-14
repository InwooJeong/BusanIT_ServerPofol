package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.BoardDAO;
import com.board.db.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("BDA 도착!");
		
		request.setCharacterEncoding("UTF-8");
		
		String pageNum = request.getParameter("pageNum");
		BoardDTO bdto = new BoardDTO();
		bdto.setNum(Integer.parseInt(request.getParameter("num")));
		
		BoardDAO bdao = new BoardDAO();
		
		bdao.deleteBoard(bdto);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo?pageNum="+pageNum+"");
		forward.setRedirect(true);
		return forward;
	}

}
