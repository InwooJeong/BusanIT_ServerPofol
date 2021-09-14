package com.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.BoardDAO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("BoardListAction~");
		
		BoardDAO bdao = new BoardDAO();
		
		int check = bdao.getBoardCount();
		
		int pageSize = 10;
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage -1)*pageSize + 1;

		int endRow = currentPage*pageSize;
		
		int pageCount = check / pageSize + (check % pageSize == 0? 0:1);
		
		int pageBlock = 10;
		
		int startPage = ((currentPage-1)/pageBlock) * pageBlock+1;
		
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		System.out.println(startRow);
		System.out.println(endRow);
		
		ArrayList boardList = null;
		if(check != 0){
			boardList = bdao.getBoardList(startRow, endRow);
		}
		
		request.setAttribute("boardList", boardList);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", check);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/boardList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
