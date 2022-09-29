package stud.reg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stud.reg.bean.Course;
import stud.reg.bean.Student;
import stud.reg.exception.StudentException;
import stud.reg.util.DBUtil;

public class StudentDaoImpl implements StudentDao{

	@Override
	public String studentRegistration(Student student) throws StudentException {
		// TODO Auto-generated method stub
		
		String message = null;
		
		try(Connection con = DBUtil.establishConnection()){
			
			PreparedStatement ps =  con.prepareStatement("INSERT INTO Student(name,gender,email,password) VALUES ?,?,?,?");
			ps.setString(1, student.getName());
			ps.setString(2, student.getGender());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getPassword());
			
			int res = ps.executeUpdate();
			
			if(res > 0) {
				message = "Registration successfull";
			}else {
				throw new StudentException("Error in Registration");
			}

		}
		catch(SQLException e) {
			message = e.getMessage();
			throw new StudentException(message);
		}

		return message;
	}

	@Override
	public String updateDetails(int roll, String field, String newData) throws StudentException {
		// TODO Auto-generated method stub
		
		String message = null;
		
		try(Connection con = DBUtil.establishConnection()){
			
			PreparedStatement ps = con.prepareStatement("UPDATE Student set ?=? WHERE roll = ?");
			ps.setString(1, field);
			ps.setString(2, newData);
			ps.setInt(3, roll);
			
			int res = ps.executeUpdate();
			
			if(res > 0) {
				message = field+" changed to "+newData + " successfully";
			}else {
				throw new StudentException("Error Updating ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = e.getMessage();
			throw new StudentException(message);
		}
		
		return message;
	}

	@Override
	public List<Course> showCourseDetails() throws StudentException{
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<>();
		
		try(Connection con = DBUtil.establishConnection()){
			
			PreparedStatement ps =  con.prepareStatement("SELECT * FROM Course");
			
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				
				int cid = rs.getInt("c_id");
				String cname = rs.getString("c_name");
				int fee = rs.getInt("fee");
				int seats = rs.getInt("seats");
				flag = false;
				
				courses.add( new Course(cid, cname,fee,seats));
			}
			
			if(flag) throw new StudentException("No course Found");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return courses;
	}

	

}
