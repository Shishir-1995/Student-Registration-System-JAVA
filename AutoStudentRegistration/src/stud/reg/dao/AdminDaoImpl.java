package stud.reg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import stud.reg.bean.Admin;
import stud.reg.bean.Course;
import stud.reg.bean.Student;
import stud.reg.exception.AdminException;
import stud.reg.util.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public String adminRegistration(Admin admin) throws AdminException {
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO admin(a_name,a_user,a_pass) VALUES (?,?,?)");
			ps.setString(1, admin.getAname());
			ps.setString(2, admin.getUsername());
			ps.setString(3, admin.getPassword());
			
			int res = ps.executeUpdate();
			
			if(res > 0 ) {
				message = "Admin Registration Successfull";
			}else {
				throw new AdminException("Not Registered ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = e.getMessage();
			throw new AdminException(message);
		}
		
		return message;
	}

	@Override
	public String addCourse(Course course) throws AdminException {
		
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("INSERT INTO course(c_name,fee,seats) VALUES (?,?,?)");
			ps.setString(1, course.getCname());
			ps.setInt(2, course.getFee());
			ps.setInt(3, course.getSeats());
			
			int res = ps.executeUpdate();
			
			if(res > 0) {
				message = "Course Added Successfully";
			}else {
				throw new AdminException("Error Adding Course Details");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String deleteCourse(int cid) throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course searchCourse(int cid) throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addStudentToCourse(int roll, int cid) throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> showStudent() throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

}
