package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjdbd.commen.JdbcHelper;

@WebServlet("/UpdSubmit.do")
public class UpdSubmitServlet extends HttpServlet{

	private static final long serialVersionUID = 2426935370804621484L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		String uuid = request.getParameter("userId");
		String loginName = request.getParameter("loginName");
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String mobile = request.getParameter("mobile");
		String SQL = "update sys_user set login_name=?,no=?,name=?,email=?,phone=?,mobile=? where id=?";
		boolean flag = JdbcHelper.execute(SQL, loginName,no,name,email,phone,mobile,uuid);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();
	}
}
