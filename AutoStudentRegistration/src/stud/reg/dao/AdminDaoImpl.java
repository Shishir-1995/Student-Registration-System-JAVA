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
import stud.reg.bean.StudentDTO;
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
															+ "ON b.cid = c.c_id AND c.c_id= (?)");
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
			
			if(res > 0) message = "Course ID : "+cid+" New Fees : "+newFee;
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
	public String addStudentToBatch(int roll, int bid, int cid) throws AdminException {
		// TODO Auto-generated method stub
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE roll = ?");
			ps.setInt(1, roll);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String studentName = rs.getString("name");
				PreparedStatement ps2 =  conn.prepareStatement("SELECT * FROM course WHERE c_id = ?");
				ps2.setInt(1, cid);
				
				ResultSet rs2 = ps2.executeQuery();
				
				if(rs2.next()) {
					
					String courseName = rs2.getString("c_name");
					PreparedStatement ps3 = conn.prepareStatement("SELECT bname,seats FROM batch WHERE bid = ? AND cid = ?");
					ps3.setInt(1, bid);
					ps3.setInt(2, cid);
					
					ResultSet rs3 = ps3.executeQuery();
					
					if(rs3.next()) {
						
						String batchName = rs3.getString("bname");
						int batchSeats = rs3.getInt("seats");
						
						if(batchSeats > 0) {
							
							batchSeats--;
							PreparedStatement up = conn.prepareStatement("UPDATE batch SET seats = ? WHERE bid = ?");
							up.setInt(1, batchSeats);
							up.setInt(2, bid);
							
							int r = up.executeUpdate();
							
							PreparedStatement p = conn.prepareStatement("INSERT INTO student_batch VALUES (?,?,?)");
							p.setInt(1, roll);
							p.setInt(2, cid);
							p.setInt(3, bid);
							
							int res = p.executeUpdate();
							
							if(res > 0) {
								
								message = "Student "+studentName+" Added to Batch "+ batchName+" of Course "+courseName+" Successfully.";
							
							}
							else {
								throw new AdminException("Batch and Course Not Matching.");
							}
							
							
						}
						else {
							throw new AdminException("No Seats Available ! Add More Seats to Add more Student.");
						}
					}else {
						throw new AdminException("Batch with Batch ID "+bid+" not Found !");
					}

				}else {
					throw new AdminException("Course with course ID "+ cid + " not Found !");
				}
				
			}else {
				throw new AdminException("Student with Roll number "+ roll+ " not Found !");
			}
			
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}
		
		return message;
	}

	@Override
	public String updateSeatsOfBatch(int bid, int newSeats) throws AdminException {
		// TODO Auto-generated method stub
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("UPDATE batch SET seats = ? WHERE bid = ?");
			ps.setInt(1, newSeats);
			ps.setInt(2, bid);
			
			int res = ps.executeUpdate();
			
			if(res>0) message = "Batch ID : "+bid+" is Updated with Seats : "+ newSeats+" Successfully.";
			else throw new AdminException("Batch ID Error.");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		return message;
	}

	@Override
	public List<StudentDTO> showAllStudent() throws AdminException {
		// TODO Auto-generated method stub
		
		List<StudentDTO> students = new ArrayList<>();
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("SELECT s.roll,s.name,c.c_id,c.c_name,b.bid,b.bname "
					+ "FROM student s INNER JOIN batch b INNER JOIN course c INNER JOIN "
					+ "student_batch sb ON c.c_id = sb.cid AND b.bid = sb.bid");
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				
				int roll = rs.getInt("roll");
				String sName = rs.getString("name");
				int cid = rs.getInt("c_id");
				String cName = rs.getString("c_name");
				int bid = rs.getInt("bid");
				String bName = rs.getString("bname");
				flag = false;
				
				
				StudentDTO student = new StudentDTO(roll, sName, cid, cName, bid, bName);
				students.add(student);
				
			}
			
			if(flag) throw new AdminException("No student added to Batch");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
			
		}
		
		return students;
	}

	@Override
	public Admin login(String username, String password) throws AdminException {
		// TODO Auto-generated method stub
		Admin admin = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("SELECT * FROM admin WHERE a_user = ? AND a_pass = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String user = rs.getString(3);
				String pass = rs.getString(4);
				
				admin = new Admin(id, name, user, pass);

			}
			else {
				throw new AdminException("Authentication Error ! ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		
		return admin;
	}

	@Override
	public List<Student> studentList() throws AdminException {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<>();
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("SELECT * FROM student");
		
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				
				flag = false;
				int roll = rs.getInt("roll");
				String name = rs.getString("name");
				String gender= rs.getString("gender");
				String email= rs.getString("email");
				String pass= rs.getString("password");
				
				Student student = new Student(roll,name,gender,email,pass);
				
				students.add(student);
			}
			
			if(flag) throw new AdminException("No Student Data Found !");
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		return students;
	}

	@Override
	public List<Course> courseList() throws AdminException {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<>();
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM course");
			
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				int cid = rs.getInt("c_id");
				String cname = rs.getString("c_name");
				int fee = rs.getInt("fee");
				flag = false;
				Course course = new Course(cid,cname,fee);
				
				courses.add(course);
			}
			
			if(flag) throw new AdminException("No Course Found !");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		
		
		
		
		return courses;
	
	
	}

}
