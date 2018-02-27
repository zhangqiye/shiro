package com.bjdbd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bjdbd.commen.JdbcHelper;
import com.bjdbd.commen.SmsUtil;

@WebServlet("/Yzm.do")
public class YzmServlet extends HttpServlet{

	private static final long serialVersionUID = 5822718181908549781L;
	protected void service( HttpServletRequest request , HttpServletResponse response ) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mobile = request.getParameter("mobile");
		String idCode=(String) session.getAttribute("idCode");
		System.out.println("手机号是"+mobile);
		String SQL = "select a.id from sys_user a,sys_office b where a.mobile = ? and a.del_flag='0' and a.office_id=b.id and b.id_code=? and b.del_flag='0'";
		System.out.println(idCode);
		ResultSet rs = JdbcHelper.query(SQL, mobile,idCode);
		System.out.println(rs);
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		InetAddress address = InetAddress.getLocalHost();
		String ip = address.getHostAddress();
		try {
			boolean bool = false;
			if(rs.next()) {
				sb.append(bool);
				out.print(false);
				out.close();
				System.out.println(bool);
			}else {
				bool = true;
				sb.append(bool);
				out.close();
				System.out.println(bool);
				String yzm = YzmServlet.getFourRandom();
				String id = com.bjdbd.commen.IdGen.uuid();
				String content = "您的短信验证码为:"+yzm;
				String yzmSQL = "insert into dzff_sms_record (id,mobile,code,message,type,ip,create_by,create_date,update_by,update_date,del_flag) value (?,?,?,?,?,?,?,?,?,?,?)";
				int count = JdbcHelper.insert(yzmSQL, false, id,mobile,yzm,content,0,ip,1,new Date(),1,new Date(),0);
				if(count==1){
					SmsUtil.sendSMS(mobile, content);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
     */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
          for(int i=1; i<=4-randLength; i++)
              fourRandom = "0" + fourRandom  ;
      }
        return fourRandom;
    } 
}
