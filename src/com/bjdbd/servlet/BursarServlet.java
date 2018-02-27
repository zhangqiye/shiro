package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjdbd.commen.JdbcHelper;
import com.bjdbd.entity.User;

/**
 * @author bj-dbd
 */
@WebServlet("/bursar.do")
public class BursarServlet extends HttpServlet{
	private static final long serialVersionUID = -536840554256460196L;
	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if(u==null){
			
		}else{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			List<User> userlist = new ArrayList<>();
			String SQL = "select id,office_id,finance_id,login_name,no,name,email,"
					+ "phone,mobile,password,user_type,login_flag from sys_user where del_flag = 0 and office_id='"+u.getOfficeId()+"' and user_type = 3";
			ResultSet rs = JdbcHelper.query(SQL);
			try {
				while(rs.next()){
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
					userlist.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				JdbcHelper.release( rs );
			}
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<table id='mytable' class='table table-hover text-center'>"
					+ "<tr>"
					+ "<th>登录名</th>"
					+ "<th>工号</th>"
					+ "<th>姓名</th>"
					+ "<th>邮箱</th>"
					+ "<th>电话</th>"
					+ "<th>手机</th><"
					+ "th>操作</th>"
					+ "</tr>");
			for(User pojo : userlist){
				String str = pojo.getUserId();
				sb.append("<tr>" +
						"<td>"+pojo.getLoginName()+"</td>" +
						"<td>"+pojo.getNo()+"</td>" +
						"<td>"+pojo.getName()+"</td>" +
						"<td>"+pojo.getEmail()+"</td>" +
						"<td>"+pojo.getPhone()+"</td>" +
						"<td>"+pojo.getMobile()+"</td>" +
						"<td><a class='button border-red' href='javascript:;' onclick='goDel(\"" + str + "\")'>删除用户</a>"
								+"</tr>");
			}
			sb.append("</table>");
			out.print(sb.toString());
			out.close();
		}
	}
}
