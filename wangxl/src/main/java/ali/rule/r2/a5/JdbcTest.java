package ali.rule.r2.a5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/mysql";
	static final String USER = "root";
	static final String PASS = "111111";

	public static void main(String[] args) {
		
		JdbcTest j = new JdbcTest();
		j.tranTest();
	}
	/**
	 * 事务例子
	 * */
	public void tranTest(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//conn.setAutoCommit(false);  //将自动提交设置为false
			stmt = conn.createStatement(); //得到运行环境
			
			String sql = "INSERT INTO student(id,name,sex) values(1,'Tom',1)"; 
			int result = stmt.executeUpdate(sql);
			System.out.println( result);
			
			result = stmt.executeUpdate(sql);
			System.out.println( result);
			
			//conn.commit();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
	
	/**
	 * 事务
	 * */
	public void queryTest(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT Host,User,Password FROM user";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String Host = rs.getString("Host");
				String User = rs.getString("User");
				String Password = rs.getString("Password");

				// Display values
				System.out.println( Host+" "+User+" "+Password);
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null) conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
