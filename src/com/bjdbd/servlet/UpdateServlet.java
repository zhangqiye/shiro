package com.bjdbd.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjdbd.commen.JdbcHelper;
import com.bjdbd.entity.User;

@WebServlet("/upd.do")
public class UpdateServlet extends HttpServlet{

	private static final long serialVersionUID = -6325706864017044221L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		String id = request.getParameter("uuid");
		if(id!= null){
			String SQL = "select id,office_id,finance_id,login_name,no,name,email,"
					+ "phone,mobile,password,user_type,login_flag from sys_user where id=? ";
			ResultSet rs = JdbcHelper.query(SQL,id);
			try {
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
					user.setMobile(rs.getString(9));
					user.setPassword(rs.getString(10));
					user.setUserType(rs.getString(11));
					user.setLoginFlag(rs.getString(12));
					ServletContext application = request.getServletContext();
					application.setAttribute( "upd_user" , user );
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				JdbcHelper.release( rs );
			}
			
			response.sendRedirect( request.getContextPath() + "/admin/upd.jsp");
		}else{
			response.sendRedirect( request.getContextPath() + "/admin/error.jsp");
		}
	}
}
