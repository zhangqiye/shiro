package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjdbd.commen.JdbcHelper;
import com.bjdbd.entity.User;

@WebServlet("/updateStatus.do")
public class UpdateStatusServlet extends HttpServlet{

	private static final long serialVersionUID = -6219846589779967356L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String idCode = (String) session.getAttribute("idCode");
		String status =  request.getParameter("status");
		System.out.println(status);
		String result=null;
		if(status!= null){
			String SQL = "update sys_office set user_login_permission = ? where id_code = ?";
			boolean b = JdbcHelper.execute(SQL,status,idCode);
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append(b);
			out.print(sb.toString());
			out.close();
		}
	}

}
