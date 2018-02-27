package com.bjdbd.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/logout.do")
public class Logout extends HttpServlet {
	/**
	 * 用户注销
	 */
    private static final long serialVersionUID = 1L;  
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	String path = request.getContextPath();//获取根路径
        HttpSession session = request.getSession(false);//防止创建Session  
        if(session == null){  
            response.sendRedirect(path+"/admin/login.jsp");  
            return;  
        }else{ 
		    session.invalidate();//清除session属性
		    response.sendRedirect(path+"/admin/login.jsp");//重新登录 
        return;
        }
    }  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
    	this.doGet(request, response);
    }  
}
