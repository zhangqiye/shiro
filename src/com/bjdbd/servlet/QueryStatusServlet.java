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

@WebServlet("/queryStatus.do")
public class QueryStatusServlet extends HttpServlet{

	private static final long serialVersionUID = -6219846589779967356L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String idCode = (String) session.getAttribute("idCode");
		System.out.println(idCode);
		String result=null;
		if(idCode!= null){
			String SQL = "select user_login_permission from sys_office where del_flag = '0' and id_code = ?";
			ResultSet r = JdbcHelper.query(SQL,idCode);
			try {
				while(r.next()){
					result=r.getString(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				JdbcHelper.release( r );
			}
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			if(result.equals("1")) {
				sb.append("<label><font color='red' style='margin-left: 1130px'>自动登录授权</font><input type='checkbox' id='status' value='自动登录授权' onclick='update()'></label>");
			}else if(result.equals("0")) {
				sb.append("<label><font color='red' style='margin-left: 1130px'>自动登录授权</font><input type='checkbox' id='status' value='自动登录授权' checked onclick='update()'></label>");
			}
			
			
			out.print(sb.toString());
			out.close();
		}
	}

}
