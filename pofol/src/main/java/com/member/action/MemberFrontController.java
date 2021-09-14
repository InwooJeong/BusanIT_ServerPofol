package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doProcess!");

		String requestURI = request.getRequestURI();
		System.out.println("URI : " + requestURI);

		String contextPath = request.getContextPath();
		System.out.println("contextPath : " + contextPath);

		String command = requestURI.substring(contextPath.length());
		System.out.println("Command : " + command);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@여기까지 주소");

		Action action = null;
		ActionForward forward = null;

		if (command.equals("/MemberJoin.me")) {
			System.out.println("/MemberJoin.me 주소 요청@@@@@@@@@");

			forward = new ActionForward();
			forward.setPath("./member/joinForm.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberLogin.me")) {
			forward = new ActionForward();
			forward.setPath("./index.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberJoinAction.me")) {
			System.out.println("/MemberJoinAction.me 주소 요청@@@@@@@@@");

			action = new MemberJoinAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberLogin2.me")) {
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/jial.me")) {
			System.out.println("가입 : ID 오류");
			forward = new ActionForward();
			forward.setPath("./member/jial.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberLoginAction.me")) {
			System.out.println("/MemberLoginAction.me 주소 요청!");

			action = new MemberLoginAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}

		} else if (command.equals("/pwal.me")) {
			System.out.println("로그인 비밀번호 오류!");
			forward = new ActionForward();
			forward.setPath("./member/pwal.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/idal.me")) {
			System.out.println("로그인 아이디 오류!");
			forward = new ActionForward();
			forward.setPath("./member/idal.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/Main.me")) {
			System.out.println("~메인까지 성공~");
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/MemberLogout.me")) {
			System.out.println("MemberLogout.me~");

			action = new MemberLogoutAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/loal.me")) {
			System.out.println("로그아웃!");
			forward = new ActionForward();
			forward.setPath("./member/loal.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/MemberInfo.me")) {
			System.out.println("MemberInfo.me~");

			action = new MemberInfoAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/MemberUpdate.me")) {
			System.out.println("업데이트@@@@@");
			forward = new ActionForward();
			// System.out.println("어디서@@@@@");
			forward.setPath("./member/updateCk.jsp");
			// System.out.println("막히나@@@@@");
			forward.setRedirect(false);

		} else if (command.equals("/MemberUpdateAction.me")) {

			action = new MemberUpdateAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/peal.me")) {
			System.out.println("비밀번호 오류!!");
			forward = new ActionForward();
			forward.setPath("./member/peal.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/pcal.me")) {
			System.out.println("수정 완료!");
			forward = new ActionForward();
			forward.setPath("./member/pcal.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/MemberDelete.me")) {
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/MemberDeleteAction.me")) {
			action = new MemberDeleteAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/dial.me")) {
			System.out.println("아이디 오류!");
			forward = new ActionForward();
			forward.setPath("./member/dial.jsp");
			forward.setRedirect(false);

		}else if (command.equals("/dpal.me")) {
			System.out.println("비밀번호 오류!");
			forward = new ActionForward();
			forward.setPath("./member/dpal.jsp");
			forward.setRedirect(false);

		}else if (command.equals("/dsal.me")) {
			System.out.println("탈퇴 성공!");
			forward = new ActionForward();
			forward.setPath("./member/dsal.jsp");
			forward.setRedirect(false);

		}else if(command.equals("/MemberList.me")){
			action = new MemberListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberPurgee.me")){
			action = new MemberPurgeeAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/purg.me")){
			System.out.println("추방 성공!");
			forward = new ActionForward();
			forward.setPath("./member/purg.jsp");
			forward.setRedirect(false);
		}
		
		

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());

				dis.forward(request, response);
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet->doProcess");

		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost->doProcess");

		doProcess(request, response);
	}

}
