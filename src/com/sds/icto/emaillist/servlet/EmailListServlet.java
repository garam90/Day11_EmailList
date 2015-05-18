package com.sds.icto.emaillist.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.emaillist.action.ActionFactory;
import com.sds.icto.emaillist.action.IndexAction;
import com.sds.icto.emaillist.action.formAction;
import com.sds.icto.emaillist.action.insertAction;
import com.sds.icto.emaillist.dao.EmailDao;
import com.sds.icto.web.Action;

@WebServlet("/email")
public class EmailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmailListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		ActionFactory af = ActionFactory.getInstance();
		Action act = af.getAction(action);
		try {
			act.execute(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
