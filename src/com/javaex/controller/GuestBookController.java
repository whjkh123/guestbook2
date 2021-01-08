package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;

@WebServlet("/gbc")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GuestBookController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("controller test");

		request.setCharacterEncoding("UTF-8");

		// 수행 할 기능
		String act = request.getParameter("action");
		System.out.println(act);

		if ("addlist".equals(act)) {
			System.out.println("리스트 출력");

			// 리스트 출력
			GuestBookDao gDao = new GuestBookDao();
			List<GuestBookVo> gList = gDao.dbList();

			// 리스트 데이터를 jsp에 전달
			request.setAttribute("GuestList", gList);

			// jsp forword
			WebUtil.forword(request, response, "./WEB-INF/addlist.jsp");
		} else if ("add".equals(act)) {
			System.out.println("방문록 등록");

			// GuestBookVo 데이터 로드
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			GuestBookVo gVo = new GuestBookVo(name, password, content);
			GuestBookDao gDao = new GuestBookDao();
			gDao.dbIsrt(gVo);

			WebUtil.redirect(request, response, "/guestbook2/gbc?action=addlist");
		} else if ("dform".equals(act)) {
			System.out.println("삭제 폼 출력");

			// jsp forword
			WebUtil.forword(request, response, "./WEB-INF/deleteForm.jsp");
		} else if ("delete".equals(act)) {
			System.out.println("방문록 삭제");

			// GuestBookVo 데이터 로드
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");

			GuestBookDao gDao = new GuestBookDao();
			GuestBookVo gVo = new GuestBookVo(no, password);

			int cnt = gDao.dbDle(gVo);

			if (cnt == 1) {
				WebUtil.redirect(request, response, "/guestbook2/gbc?action=addlist");
			} else {// 비밀번호를 틀렸을 경우 (cnt == 0)
				// jsp forword
				WebUtil.forword(request, response, "./WEB-INF/passworderror.jsp");
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
