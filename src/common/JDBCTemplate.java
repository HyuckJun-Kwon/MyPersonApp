package common;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JDBCTemplate {
	/*명시하여 DB연결
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/my_emp?";
			String user = "hjkwon";
			String password = "Kwongurwns97!";
			
			conn = DriverManager.getConnection(url, user, password);
			
			conn.setAutoCommit(false);
			if (!conn.isClosed()) {
		    	System.out.println("연결 중이야");
		    }
		}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	*/
	//properties 이용하여 DB연결
	public static Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		
		try (InputStream input = JDBCTemplate.class.getClassLoader().getResourceAsStream("db.properties")){
			if (input == null) {
				throw new RuntimeException("db.properties 파일 존재하지 않음");
			}
			
			prop.load(input);
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			Class.forName(driver);//jdk 버젼 상관없이 리소스 로드 시 반드시 명시
			conn = DriverManager.getConnection(url, user, password);
			
			conn.setAutoCommit(false);
			
			if (!conn.isClosed()) {
		    	System.out.println("처리 완료");
		    }
		}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	//DB Close 하는 곳
	public static void Close(Connection con) {
		if (con != null) {
			try {
				con.close();
			}catch (SQLException e) {
				System.out.println("데이터베이스 연결 닫기 오류 발생" + e.getSQLState() + "\t" + e.getMessage());
			}
		}
	}
	
	//Statement Close 하는 곳
	public static void Close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			}catch (SQLException e) {
				System.out.println("명령 오류 발생" + e.getSQLState() + "\t" + e.getMessage());
			}
		}
	}
	
	//ResultSet Close 하는 곳
	public static void Close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			}catch (SQLException e) {
				System.out.println("쿼리 리턴 오류 발생" + e.getSQLState() + "\t" + e.getMessage());
			}
		}
	}
	
	//트랜잭션 처리 (commit)
	public static void commit(Connection con) {
		if (con != null) {
			try {
				con.commit();
			}catch (SQLException e) {
				System.out.println("데이터베이스 연결 닫기 오류 발생" + e.getSQLState() + "\t" + e.getMessage());
			}
		}
	}
	//트랜잭션 처리 (rollback)
	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			}catch (SQLException e) {
				System.out.println("데이터베이스 연결 닫기 오류 발생" + e.getSQLState() + "\t" + e.getMessage());
			}
		}
	}	
}
