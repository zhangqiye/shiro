package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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

@WebServlet("/office.do")
public class QueryOffice extends HttpServlet{

	private static final long serialVersionUID = -7463722228661060385L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		List<User> userlist = new ArrayList<>();
		if(u==null){
			
		}else{
			String companyId = u.getCompanyId();
			String SQL = "select o.id,o.name from sys_office o where o.parent_ids LIKE ? and del_flag = 0 group by name";
			ResultSet rs = JdbcHelper.query(SQL, "%"+companyId+"%");
			try {
				while(rs.next()){
					User user = new User();
					user.setOfficeId(rs.getString(1));
					user.setOfficeName(rs.getString(2));
					userlist.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				JdbcHelper.release( rs );
			}
			PrintWriter out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("<select name='officeId' class='input'>");
			for(User pojo:userlist){
				sb.append("<option value='"+pojo.getOfficeId()+"'>'"+pojo.getOfficeName()+"'</option>");
			}
			sb.append("</select>");
			out.print(sb.toString());
			out.close();
		}
	}
}

