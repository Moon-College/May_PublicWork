package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.MJdbcTemplate;

public class JdbcTest01 {

	public static void main(String[] args){
		commonOrder();
		String sql = "insert into t_studentssss values(?,?,?,?)";
		int count = MJdbcTemplate.executeUpdate(sql , 99, "张三", "男", 18);
		//下一步集成--注解，反射，泛型
	}
	
	/**
	 * 正常的流程
	 */
	private static void commonOrder(){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		//1.加载驱动
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//2.建立连接
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			//用户名
			String user = "scott";
			//密码
			String password = "scott";
			conn = DriverManager.getConnection(url, user, password);
			 //3.创建sql语句操作对象
			String sql="select *  from t_student";
			statement = conn.prepareStatement(sql);
			//4.通过sql语句操作对象执行操作
			//executeQuery:用来执行数据的查询操作
			//循环获取查询结果集中的数据
			resultSet=statement.executeQuery();
			while(resultSet.next()){
				//rs.getXXX来获取指定列的值
				int id = resultSet.getInt("f_id");
				String name = resultSet.getString("f_name");
				String sex = resultSet.getString("f_sex");
				int age = resultSet.getInt("f_age");
				System.out.println("id:" + id + ",name:" + name + ",sex:" + sex + ",age:" + age);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//5.释放资源
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
