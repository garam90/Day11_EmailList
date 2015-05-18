package com.sds.icto.emaillist.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.emaillist.dao.EmailDao;
import com.sds.icto.emaillist.vo.EmailVO;
import com.sds.icto.web.Action;

public class insertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, ServletException,
			IOException {
		String fn = request.getParameter("fn");	
		String ln = request.getParameter("ln");	
		String email = request.getParameter("email");
		
		EmailVO vo = new EmailVO();
		vo.setFirstName(fn);
		vo.setLastName(ln);
		vo.setEmail(email);
		
		EmailDao dao = new EmailDao();
		dao.insert(vo);
		response.sendRedirect("/Day04_EmailList2/email");
	}

}
