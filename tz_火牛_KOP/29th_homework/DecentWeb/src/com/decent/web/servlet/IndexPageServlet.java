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
		 * get post��ȡ�����ķ�ʽ��һ����
		 */
		String user_name = req.getParameter(USER_NAME);
		String password = req.getParameter(PASSWORD);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<html><body>");
		/*
		 * �ж��û���������
		 */
		if("firebull".equals(user_name) && "123".equals(password)){
			sb.append("<h1>"+"��¼�ɹ�"+"</h1>");
		}else{
			sb.append("<h1>"+"��¼ʧ��"+"</h1>");
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
		 * ��������˵get��post��һ�µģ�ֻ�Ǵ��ݲ����ķ�ʽ��һ��
		 */
		doGet(req, resp);
	}
}
