package com.junwen.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {

	/**
	 * 根据传入的sql语句，和参数，进行sql查询
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static int excuteUpdate(String sql, Object... objects) {
		Connection connection = null;
		connection = BaseJdbcUtil.getConnection();
		PreparedStatement prepareStatement  = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			if(objects != null){
				for (int i = 0; i < objects.length; i++) {
					prepareStatement.setObject(i+1, objects[i]);
				}
			}
			 int update = prepareStatement.executeUpdate();
			 return update;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			BaseJdbcUtil.close(null, prepareStatement, connection);
			
		}
		return 0;
	}
	
	/**
	 * 查询方法
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static ResultSet excuteQuery(String sql,Object... objects){
		Connection connection = null;
		connection = BaseJdbcUtil.getConnection();
		PreparedStatement prepareStatement  = null;
		ResultSet resultSet = null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			if(objects != null){
				for (int i = 0; i < objects.length; i++) {
					prepareStatement.setObject(i+1, objects[i]);
				}
			}
			return prepareStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
