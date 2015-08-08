package com.decent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexPageServlet extends HttpServlet {
	private static final String USER_NAME = "user_name";
	private static final String PASSWORD = "password";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * get post获取参数的方式是一样的
		 */
		String user_name = req.getParameter(USER_NAME);
		String password = req.getParameter(PASSWORD);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<html><body>");
		/*
		 * 判断用户名和密码
		 */
		if("firebull".equals(user_name) && "123".equals(password)){
			sb.append("<h1>"+"登录成功"+"</h1>");
		}else{
			sb.append("<h1>"+"登录失败"+"</h1>");
		}
		sb.append("</body></html>");
		PrintWriter writer = resp.getWriter();
		writer.print(sb.toString());
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 本质上来说get和post是一致的，只是传递参数的方式不一样
		 */
		doGet(req, resp);
	}
}
