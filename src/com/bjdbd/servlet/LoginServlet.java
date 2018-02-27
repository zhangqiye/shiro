package com.bjdbd.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjdbd.commen.JdbcHelper;
import com.bjdbd.commen.Util;
import com.bjdbd.entity.User;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -3446861046518572759L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String idCode = request.getParameter("idCode");
		String password = request.getParameter("password");
		String loginName = request.getParameter("loginName");
		HttpSession session = request.getSession();
		String SQL = "select u.id,u.office_id,u.finance_id,u.login_name,u.no,u.name,u.email,"
				+ "u.phone,u.login_flag,u.password from sys_user u,sys_office f where "
				+ "f.id_code = ? and u.login_name=? and u.del_flag = '0' and u.user_type = 4 and u.office_id = f.id " ;
		System.out.println("idcode:"+idCode+"name:"+loginName);
		ResultSet rs = JdbcHelper.query( SQL,  idCode , loginName );
		try{
			if(rs.next()){
				User user = new User();
				user.setUserId(rs.getString(1));
				user.setOfficeId(rs.getString(2));
				user.setFinanceId(rs.getString(3));
				user.setLoginName(rs.getString(4));
				user.setNo(rs.getString(5));
				user.setName(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setPhone(rs.getString(8));
				user.setUserType("4");
				user.setLoginFlag(rs.getString(9));
				user.setPassword(rs.getString(10));
				if(!Util.validatePassword(password, user.getPassword())){
				System.out.println("检查密码加密之后的样子："+Util.validatePassword(password, user.getPassword()));
				request.setAttribute("message", "请检查账号密码");
				session.setAttribute("idCode", idCode);
				session.setAttribute("loginName", loginName);
				request.getRequestDispatcher("/admin/login.jsp").forward(request,response); 
				}else{
					session.setAttribute( "user" , user );
					session.setAttribute("idCode", idCode);
					response.sendRedirect( request.getContextPath() + "/admin/index.jsp" );
				}
			}
			else{
				session.setAttribute("idCode", idCode);
				session.setAttribute("loginName", loginName);
				request.setAttribute("message", "请检查账号密码");
				request.getRequestDispatcher("/admin/login.jsp").forward(request,response); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "登录出现错误，请联系栋邦达");
			request.getRequestDispatcher("/admin/login.jsp").forward(request,response);
		}finally{
			JdbcHelper.release( rs );
		}
	}
}
