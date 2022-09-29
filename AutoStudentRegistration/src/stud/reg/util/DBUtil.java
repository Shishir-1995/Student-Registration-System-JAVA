package stud.reg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static Connection establishConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Connection conn = null;
		
		try { 
			conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/stud_reg","root","admin");
			
		} 
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
		
		return conn;
		
	}
	

}
