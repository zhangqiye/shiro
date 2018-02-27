package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjdbd.commen.JdbcHelper;

@WebServlet("/del.do")
public class DeleteServlet extends HttpServlet{

	private static final long serialVersionUID = -6219846589779967356L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		String ddid = request.getParameter("did");
		String bid = request.getParameter("bid");
		if(ddid!= null){
			String SQL = "update sys_user set del_flag = 1 where id = ?";
			boolean flag = JdbcHelper.execute(SQL,ddid);
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append(flag);
			out.print(sb.toString());
			out.close();
		}
		if(bid!= null){
			String SQL = "update sys_user set user_type = 3,limit_money=0,finance_id = null where id = ?";
			boolean flag = JdbcHelper.execute(SQL,bid);
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append(flag);
			out.print(sb.toString());
			out.close();
		}
	}

}
