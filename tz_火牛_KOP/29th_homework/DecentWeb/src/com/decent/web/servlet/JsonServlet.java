package com.decent.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.decent.datastore.datahandler.SportRecordHandler;

public class JsonServlet extends HttpServlet {

	private static final String RECORD_ID = "record_id";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        String record_id = req.getParameter(RECORD_ID);
        String resultJson = null;
        if(record_id==null){
        	resultJson = JSON.toJSONString(SportRecordHandler.getAllSportRecordList());
        }else{
        	resultJson = JSON.toJSONString(SportRecordHandler.getSportRecordById(Integer.valueOf(record_id)));
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(resultJson);
        printWriter.flush();
        printWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
