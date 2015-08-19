package cn.ysh.server;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyAndroidServlet extends HttpServlet{

	//初始化回调
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String passWord = req.getParameter("password");
		System.out.println("userName:"+userName+"---"+"passWord:"+passWord);
		//req 是请求 resp 是响应
		resp.addHeader("content-type", "text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");//设置响应编码
		PrintWriter writer = resp.getWriter();
		StringBuffer sb = new StringBuffer();
		sb.append("<html>"+"\n");
		sb.append("<body>"+"\n");
		sb.append("<h1>"+"为学"+"</h1>"+"\n");
		sb.append("</body>"+"\n");
		sb.append("</html>"+"\n");
		System.out.println(sb.toString());
		writer.write(sb.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
