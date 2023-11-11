package Data;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

public class ConnectDb {	// 데이터베이스 연결
	public static Connection getConnection() throws Exception{
		Context initContext = new InitialContext();
		
		Context envContext = (Context)initContext.lookup("java:comp/env");
		
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		
		Connection conn = ds.getConnection();
		
		return conn;
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(conn != null)conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null)stmt.close();
			if(conn != null)conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
