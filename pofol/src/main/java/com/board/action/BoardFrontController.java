package com.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.Action;
import com.board.action.ActionForward;
import com.board.db.BoardDTO;

public class BoardFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		System.out.println("Pro~");
		
		String requestURI = request.getRequestURI();
		System.out.println("URI : "+requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath : "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("Command : "+command);
		System.out.println("-------주소계산 완료-----------------");
		
		Action action = null;
		ActionForward forward = null;
		
			if(command.equals("/BoardWrite.bo")){
				System.out.println("/BoardWrite.bo~");
				
				forward = new ActionForward();
				
				forward.setPath("./board/writeForm.jsp");
				forward.setRedirect(false);
			}else if(command.equals("/BoardWriteAction.bo")){
				System.out.println("/BoardWriteAction~");
				
				action = new BoardWriteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(command.equals("/BoardList.bo")){
				System.out.println("/BoardList.bo~");
				
				action = new BoardListAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/BoardContent.bo")){
				System.out.println("/BoardContent.bo~");
				
				action = new BoardContentAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/BoardRewrite.bo")){
				System.out.println("/BoardRewrite.bo~");
				
				forward = new ActionForward();
				
				forward.setPath("./board/rewriteForm.jsp");
				forward.setRedirect(false);
			}else if(command.equals("/BoardRewriteAction.bo")){
				System.out.println("/BoardRewriteAction~");
				
				action = new BoardRewriteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/BoardUpdate.bo")){
				System.out.println("BoardUpdate~");
				
				action = new BoardUpdate();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/BoardUpdateAction.bo")){
				System.out.println("/BoardUpdateAction.bo~");
				
				action = new BoardUpdateAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/BoardDeleteAction.bo")){
				System.out.println("BDA~");
				
				action = new BoardDeleteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		
			if(forward != null){//페이지 이동 정보가 있을 때
			
			if(forward.isRedirect()){//true
				response.sendRedirect(forward.getPath());
			}else{//false
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				
				dis.forward(request, response);
			}
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest reqeust, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get~");
		doProcess(reqeust, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post~");
		doProcess(request, response);
	}
	
	

}
