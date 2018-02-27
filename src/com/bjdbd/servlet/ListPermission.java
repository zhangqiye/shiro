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
 *这个类做了用户列表类
 */
@WebServlet("/userListPermission.do")
public class ListPermission extends HttpServlet{
	private static final long serialVersionUID = -8626984485236325436L;
	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if(u==null){
			
		}else{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
		String name = request.getParameter("name");
		System.out.println("111");
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
		List<User> userlist = new ArrayList<>();
		if(name == null || name == ""){
			String SQL = "select id,office_id,finance_id,login_name,no,name,email,"
					+ "phone,mobile,password,user_type,login_flag from sys_user where del_flag = 0 and login_flag='0' and office_id='"+u.getOfficeId()+"' limit ?,? ";
			String CSQL = "select count(id) from sys_user where del_flag=0 and login_flag='0' and office_id='"+u.getOfficeId()+"'";
			ResultSet rs = JdbcHelper.query(SQL,(pageCurrent-1)*pageSize,pageSize);
			ResultSet r = JdbcHelper.query(CSQL);
			int count = 0;
			try {
				while(r.next()){
					count=r.getInt(1);
				}
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
					user.setUserType("2");
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
			sb.append("<input type='hidden' id='count' value='"+count+"'/>");
			sb.append("<table id='mytable' class='table table-hover text-center'>"
					+ "<tr>"
					+ "<th>登录名</th>"
					+ "<th>工号</th>"
					+ "<th>姓名</th>"
					+ "<th>邮箱</th>"
					+ "<th>电话</th>"
					+ "<th>手机</th>"
/*					+ "<th>用户类型</th>"*/
					+ "<th>操作</th>"
					+ "</tr>");
			for(User pojo : userlist){
				String str = pojo.getUserId();
				sb.append("<tr>" +
						"<td>"+pojo.getLoginName()+"</td>" );
				if(pojo.getNo()==null){
					sb.append("<td>"+""+"</td>");
				}else{
					sb.append("<td>"+pojo.getNo()+"</td>" );
				}
				sb.append("<td>"+pojo.getName()+"</td>" +
						"<td>"+pojo.getEmail()+"</td>" );
				if(pojo.getPhone()==null){
					sb.append("<td>"+""+"</td>");
				}else{
					sb.append("<td>"+pojo.getPhone()+"</td>" );
				}
				sb.append("<td>"+pojo.getMobile()+"</td>" );
						
						/*if(pojo.getUserType() == "2"||pojo.getUserType().equals("2")){
							sb.append("<td>"+"财务"+"</td>" );
						}
						if(pojo.getUserType() == "3"||pojo.getUserType().equals("3")){
							sb.append("<td>"+"普通用户"+"</td>" );
						}
						if(pojo.getUserType() == "4"||pojo.getUserType().equals("4")){
							sb.append("<td>"+"管理员"+"</td>" );
						}*/
						
				sb.append("<td><a class='button border-red' href='javascript:;' onclick='goPermission(\"" + str + "\")'>允许登录</a>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp;"
				);
				/*if(pojo.getUserType() == "2"||pojo.getUserType().equals("2")){
					sb.append("<a class='button' style='color:#999'>已为财务</a>" 
							+"</tr>");
				}
				if(pojo.getUserType() == "3"||pojo.getUserType().equals("3")){
					sb.append("<a class='button border-main' href='javascript:;' onclick='goAdd(\"" + str + "\")'>设为财务</a>" 
							+"</tr>");
				}
				if(pojo.getUserType() == "4"||pojo.getUserType().equals("4")){
					sb.append("<a class='button' style='color:#999'>管理人员</a>" 
							+"</tr>");
				}*/
			}
			sb.append("</table>");
			out.print(sb.toString());
			out.close();
		}else{
			String SQL = "select id,office_id,finance_id,login_name,no,name,email,"
					+ "phone,mobile,password,user_type,login_flag from sys_user "
					+ "where del_flag = 0 and login_flag='0' and office_id='"+u.getOfficeId()+"' and name LIKE '%"+name+"%' limit ?,? ";
			String CSQL = "select count(*) from sys_user where del_flag=0 and login_flag='0' and name LIKE '%"+name+"%' and user_type='3' and company_id='"+u.getCompanyId()+"'";
			ResultSet rs = JdbcHelper.query(SQL,(pageCurrent-1)*pageSize,pageCurrent*pageSize);
			ResultSet r = JdbcHelper.query(CSQL);
			int count = 0;
			
			try {
				while(r.next()){
					count=r.getInt(1);
				}
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
					user.setUserType("2");
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
			sb.append("<input type='hidden' id='count' value='"+count+"'/>");
			sb.append("<table id='mytable' class='table table-hover text-center'>"
					+ "<tr>"
					+ "<th>登录名</th>"
					+ "<th>工号</th>"
					+ "<th>姓名</th>"
					+ "<th>邮箱</th>"
					+ "<th>电话</th>"
					+ "<th>手机</th>"
					+ "<th>操作</th>"
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
						"<td><a class='button border-red' href='javascript:;' onclick='goPermission(\"" + str + "\")'>允许登录</a>"
								+ "&nbsp;&nbsp;&nbsp;&nbsp;"
								);
				/*if(pojo.getUserType() == "2"||pojo.getUserType().equals("2")){
					sb.append("<a class='button' style='color:#999'>已为财务</a>" 
							+"</tr>");
				}
				if(pojo.getUserType() == "3"||pojo.getUserType().equals("3")){
					sb.append("<a class='button border-main' href='javascript:;' onclick='goAdd(\"" + str + "\")'>设为财务</a>" 
							+"</tr>");
				}
				if(pojo.getUserType() == "4"||pojo.getUserType().equals("4")){
					sb.append("<a class='button' style='color:#999'>管理人员</a>" 
							+"</tr>");
				}*/
			}
			sb.append("</table>");
			out.print(sb.toString());
			out.close();
		}
	}
	}
}
