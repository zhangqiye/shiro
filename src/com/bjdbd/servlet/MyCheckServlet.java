package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjdbd.commen.Util;
import com.bjdbd.entity.User;

/**
 * Servlet implementation class MyCheckServlet
 */
@WebServlet("/mycheck.do")
public class MyCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		User user = (User)request.getSession().getAttribute("user");
		String oldpassword = request.getParameter("oldpassword");
		boolean flag = Util.validatePassword(oldpassword, user.getPassword());
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		if(flag) {
			sb.append(true);
		}else {
			sb.append(false);
		}
		out.print(sb.toString());
		out.close();
	}

}
