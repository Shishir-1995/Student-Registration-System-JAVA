package stud.reg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stud.reg.bean.CoursesDTO;
import stud.reg.bean.Student;
import stud.reg.exception.StudentException;
import stud.reg.util.DBUtil;

public class StudentDaoImpl implements StudentDao{

	@Override
	public String studentRegistration(Student student, int cid) throws StudentException {
		// TODO Auto-generated method stub
		
		String message = null;
		
		try(Connection con = DBUtil.establishConnection()){
			
			PreparedStatement ps =  con.prepareStatement("INSERT INTO student(name,gender,email,password) VALUES (?,?,?,?)");
			ps.setString(1, student.getName());
			ps.setString(2, student.getGender());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getPassword());
			
			ps.executeUpdate();
			
			PreparedStatement ps2 = con.prepareStatement("SELECT * FROM course WHERE c_id = ?");
			ps2.setInt(1, cid);
			
			ResultSet rs = ps2.executeQuery();
			
			if(rs.next()) {
				
				String email = student.getEmail();
				System.out.println(email);
				
				PreparedStatement ps3 = con.prepareStatement("SELECT roll FROM student WHERE email = ?");
				ps3.setString(1, email);
				
				ResultSet rs2 = ps3.executeQuery();
				rs2.next();
				int roll = rs2.getInt("roll");
				
				PreparedStatement ps4 = con.prepareStatement("INSERT INTO student_course VALUES (?,?)");
				ps4.setInt(1, roll);
				ps4.setInt(2, cid);
				
				int res = ps4.executeUpdate();
				if(res > 0) {
					message = "Registration successfull";
				}else {
					throw new StudentException("Error in Registration");
				}
				
			}else {
				throw new StudentException("Course ID Error.");
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
			
			PreparedStatement ps = con.prepareStatement("UPDATE student set "+field+"=? WHERE roll = ?");
			ps.setString(1, newData); 
//			ps.setString(2, newData);
			ps.setInt(2, roll);
			
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
	public List<CoursesDTO> showAllCourseDetails() throws StudentException {
		// TODO Auto-generated method stub
		List<CoursesDTO> courses = new ArrayList<>();
		
		try(Connection con = DBUtil.establishConnection()){
			
			PreparedStatement ps =  con.prepareStatement("SELECT c.c_id,c.c_name,sum(b.seats) FROM batch b "
					+ "INNER JOIN course c ON c.c_id = b.cid GROUP BY c.c_id");
			
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				
				int cid = rs.getInt(1);
				String name = rs.getString(2);
				int totalFees = rs.getInt(3);
				
				flag = false;
				
				CoursesDTO course = new CoursesDTO(cid, name, totalFees);
				
				courses.add(course);
			}
			
			if(flag) throw new StudentException("No course Found");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public Student login(String username, String password) throws StudentException {
		// TODO Auto-generated method stub
		Student student = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("SELECT * FROM student WHERE email = ? AND password = ?");
			ps.setString(1,username);
			ps.setString(2,password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int roll = rs.getInt("roll");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String pass = rs.getString("password");
				
				student = new Student(roll,name,gender,email,pass);
			}else {
				throw new StudentException("Student Not Found");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new StudentException(e.getMessage());
		}
		
		
		return student;
	}

	

}
