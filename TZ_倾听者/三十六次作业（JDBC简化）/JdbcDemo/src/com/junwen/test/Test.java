package com.junwen.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.junwen.util.BaseJdbcUtil;
import com.junwen.util.JdbcUtil;

public class Test {

	public static void main(String[] args) {
//		int insertResult = JdbcUtil.excuteUpdate("insert into student values(?,?,?)", 2,"俊文",30);
//		System.out.println(insertResult);
//		int updateResult = JdbcUtil.excuteUpdate("update student set sage=? where sid=?",30,1 );
////		System.out.println(updateResult);
//		int deleteResult = JdbcUtil.excuteUpdate("delete from student where sid=?",1);
//		System.out.println(deleteResult);
		ResultSet result = JdbcUtil.excuteQuery("select sname from student where sage=?",30);
		if(result != null){
			try {
				while(result.next()){
					try {
						System.out.println(result.getString(1));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				BaseJdbcUtil.close(result, null, null);
			}
		}
	}
}
