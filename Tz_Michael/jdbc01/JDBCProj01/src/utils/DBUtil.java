package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库的工具类
 * @author michael
 *
 */
public class DBUtil {

	//连接字符串
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	//用户名
	private static final String USER = "scott";
	//密码
	private static final String password = "szm";
	
	//将驱动加载放在静态代码块中，保证类调用的同时，加载驱动
	static{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取数据库的连接
	 * @return
	 */
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(URL, USER, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 释放资源
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void close(ResultSet resultSet,PreparedStatement statement,Connection connection){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
