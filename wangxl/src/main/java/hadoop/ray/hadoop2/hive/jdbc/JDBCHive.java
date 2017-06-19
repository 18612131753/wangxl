package hadoop.ray.hadoop2.hive.jdbc;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class JDBCHive {

	private static String Driver = "org.apache.hive.jdbc.HiveDriver";
	private static String url = "jdbc:hive2://192.168.192.146:10000/default";
	
	private static String name = "user1";
	private static String password = "password1";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, name, password);
			Statement stat = conn.createStatement();
			//1. 查询所有db
			// String sql = "show databases";
			String sql = "show tables";
			System.out.println("Running: " + sql);  
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1));// hive的索引从1开始
			}
			
			//2. 查询sql
			sql = "select * from person";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				// hive的索引从1开始
				System.out.println(rs.getString(1) +"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				// 关闭Hive连接
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
