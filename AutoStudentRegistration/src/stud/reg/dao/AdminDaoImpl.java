package stud.reg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stud.reg.bean.Admin;
import stud.reg.bean.Batch;
import stud.reg.bean.Course;
import stud.reg.bean.CourseDTO;
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
			
			PreparedStatement ps =  conn.prepareStatement("INSERT INTO course(c_name,fee) VALUES (?,?)");
			ps.setString(1, course.getCname());
			ps.setInt(2, course.getFee());
	
			
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
		
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM course WHERE c_id = ?");
			ps.setInt(1, cid);
			
			int res = ps.executeUpdate();
			
			if(res>0) message = "Course Removed Successfully";
			else throw new AdminException("Course ID Error! Not Found");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public List<CourseDTO> searchCourse(int cid) throws AdminException {

		List<CourseDTO> courses = new ArrayList<>();
		
		try(Connection con = DBUtil.establishConnection()){
			
			
			PreparedStatement p = con.prepareStatement("SELECT * FROM course WHERE c_id = ?");
			p.setInt(1, cid);
			
			ResultSet r = p.executeQuery();
			
			if(r.next()){
			
				PreparedStatement ps = con.prepareStatement("SELECT b.seats,b.bname,c.c_name,c.fee,c.c_id "
															+ "FROM batch b INNER JOIN course c "
															+ "ON b.cid = c.c_id AND c.c_id= (?);");
				ps.setInt(1, cid);
				
				ResultSet rs = ps.executeQuery();
				
				boolean flag = true;
				
				while(rs.next()) {
					
					flag = false;
					int seats = rs.getInt("b.seats");
					String bname = rs.getString("b.bname");
					String cname = rs.getString("c.c_name");
					int fee = rs.getInt("c.fee");
					int c_id = rs.getInt("c.c_id");
					
					CourseDTO course = new CourseDTO(c_id,cname,bname,fee,seats);
					
					courses.add(course);
				}
				
				if(flag) throw new AdminException("No Data Found! ");
			
			}
			else {
			
				throw new AdminException("Course ID Error !");
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}

		return courses;
	}

	@Override
	public String updateFee(int cid, int newFee) throws AdminException {
		// TODO Auto-generated method stub
		
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("UPDATE course SET fee = ? WHERE cid = ?");
			ps.setInt(1, newFee);
			ps.setInt(2, cid);
			
			int res = ps.executeUpdate();
			
			if(res > 0) message = "Course ID : "+cid+" Updated Fees : "+newFee;
			else throw new AdminException("Error Updating Fee ! Check Course ID. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			throw new AdminException(e.getMessage());
			
		}
		
		return message;
	}

	@Override
	public String addBatchToCourse(Batch batch) throws AdminException {
		// TODO Auto-generated method stub
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("INSERT INTO batch VALUES (?,?,?,?,?)");
			ps.setInt(1, batch.getBid() );
			ps.setString(2, batch.getName());
			ps.setInt(3, batch.getDuration());
			ps.setInt(4, batch.getCid());
			ps.setInt(5, batch.getSeats());
			
			int res = ps.executeUpdate();
			
			if(res > 0) message = "Batch "+batch.getName() + " Added to Course ID : "+ batch.getCid();
			else throw new AdminException("Batch Error ! Check Credentials Again.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		
		
		
		return message;
	}

	@Override
	public String addStudentToBatch(int roll, int bid) throws AdminException {
		// TODO Auto-generated method stub
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE roll = ?");
			ps.setInt(1, roll);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				PreparedStatement ps2 =  conn.prepareStatement("SELECT * FROM batch WHERE bid = ?");
				ps2.setInt(1, bid);
				
				ResultSet rs2 = ps2.executeQuery();
				
				if(rs2.next()) {
					
					conn.prepareStatement("SELECT ");
					
				}else {
					throw new AdminException("Batch Error !");
				}
				
			}else {
				throw new AdminException("Student Error !");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}

	@Override
	public String updateSeatsOfBatch(int bid, int newSeats) throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> showAllStudent() throws AdminException {
		// TODO Auto-generated method stub
		return null;
	}

}
