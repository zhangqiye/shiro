package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjdbd.commen.JdbcHelper;
import com.bjdbd.commen.Util;
import com.bjdbd.entity.User;

@WebServlet("/my.do")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = -8531965442528166552L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		User user = (User)request.getSession().getAttribute("user");
		String password = request.getParameter("password");
		String SQL = "update sys_user set password = ? where id = ? ";
		boolean flag = JdbcHelper.execute(SQL, Util.entryptPassword(password),user.getUserId());
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append(flag);
		out.print(sb.toString());
		out.close();

	}
}
