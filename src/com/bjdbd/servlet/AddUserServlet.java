package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjdbd.commen.JdbcHelper;
import com.bjdbd.commen.Util;
import com.bjdbd.entity.User;

@WebServlet("/addUser.do")
public class AddUserServlet  extends HttpServlet{

	private static final long serialVersionUID = 4933297428959880577L;

	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String idCode=(String) session.getAttribute("idCode");
		if(u==null){
			
		}else{
			
			String officeId = u.getOfficeId();
//			String officeId = request.getParameter("officeId");
			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");
			String no = request.getParameter("no");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String mobile = request.getParameter("mobile");
			String yzm = request.getParameter("yzm");
			String id = com.bjdbd.commen.IdGen.uuid();
			String querySQL = "select a.id from sys_user a,sys_office b where a.mobile = ? and a.del_flag='0' and a.office_id=b.id and b.id_code=? and b.del_flag='0'";
			String yzmSQL = "select create_date from dzff_sms_record where code = ? and mobile = ? order by create_date desc limit 1";
			ResultSet yz = JdbcHelper.query(yzmSQL,yzm,mobile);
			ResultSet rs = JdbcHelper.query(querySQL, mobile,idCode);
			try {
				if(yz.next()) {
				try {
					if(rs.next()){
						PrintWriter out = response.getWriter();
						out.print(false);
						out.close();
					}else{
						String SQL = "insert into sys_user (id,login_name,password,no,name,email,phone,mobile,user_type,login_flag,office_id,finance_id,company_id,create_by,create_date,update_by,update_date,del_flag,limit_money) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						int count = JdbcHelper.insert(SQL, false, id,loginName,Util.entryptPassword(password),no,name,email,phone,mobile,2,1,officeId,id,officeId,1,new Date(),1,new Date(),0,0);
						boolean flag = false;
						if(count==1){
							flag = true;
						}
						PrintWriter out = response.getWriter();
						StringBuffer sb = new StringBuffer();
						sb.append(flag);
						out.print(sb.toString());
						out.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
				else {
					String mes = "error";
					PrintWriter out = response.getWriter();
					StringBuffer sb = new StringBuffer();
					sb.append(mes);
					out.print(sb.toString());
					out.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
