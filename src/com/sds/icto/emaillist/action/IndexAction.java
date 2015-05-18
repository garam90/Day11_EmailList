package com.sds.icto.emaillist.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.emaillist.dao.EmailDao;
import com.sds.icto.emaillist.vo.EmailVO;
import com.sds.icto.web.Action;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, ServletException, IOException {
		ArrayList<EmailVO> list = new ArrayList<EmailVO>();
		try{
			EmailDao dao = new EmailDao();
			list = dao.selectList();
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/view/show_emaillist.jsp").forward(request, response);
	}

}
