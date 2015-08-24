package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 数据操作封装类
 * @author michael
 *
 */
public class MJdbcTemplate {

	/**
	 * 数据库的更新操作
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int executeUpdate(String sql, Object ... params){
		Connection conn=DBUtil.getConnection();
		PreparedStatement statement = null;
		try {
			statement=conn.prepareStatement(sql);
			//设置参数(pstm.setXXX(位置：从1开始, 值))
			if(params!=null){
				for(int i=0;i<params.length;i++){
					statement.setObject(i+1, params[i]);
				}
			}
			//通过sql语句操作对象执行操作
			//executeUpdate:用来执行数据的更新操作(insert|update|delete)
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DBUtil.close(null, statement, conn);
		}
	}
	
}
