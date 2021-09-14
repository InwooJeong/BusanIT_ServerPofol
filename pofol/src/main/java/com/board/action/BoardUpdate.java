package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.BoardDAO;
import com.board.db.BoardDTO;

public class BoardUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("BoardUpdate execute!");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		ActionForward forward = new ActionForward();
		
		BoardDAO bdao = new BoardDAO();
		
		BoardDTO bdto = bdao.getBoard(num);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("bdto", bdto);
		
		forward.setPath("./board/updateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
