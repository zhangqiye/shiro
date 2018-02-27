package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjdbd.commen.JdbcHelper;

@WebServlet("/add.do")
public class AddBursarServlet extends HttpServlet{
	private static final long serialVersionUID = -8126264134430647016L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		if(bid!= null){
			String SQL = "update sys_user set user_type = 2,finance_id = ? where id = ?";
			boolean flag = JdbcHelper.execute(SQL,bid,bid);
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append(flag);
			out.print(sb.toString());
			out.close();
		}
	}

}
