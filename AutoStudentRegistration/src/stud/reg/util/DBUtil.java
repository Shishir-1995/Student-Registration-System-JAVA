package stud.reg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	public static Connection establishConnection() {
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.Driver");			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Stud_Reg","root","admin");
			
		} 
		catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			
		}
		finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		return conn;
		
	}

}
