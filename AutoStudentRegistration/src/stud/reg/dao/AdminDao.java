package stud.reg.dao;

import java.util.List;

import stud.reg.bean.Admin;
import stud.reg.bean.Batch;
import stud.reg.bean.Course;
import stud.reg.bean.CourseDTO;
import stud.reg.bean.Student;
import stud.reg.bean.StudentDTO;
import stud.reg.exception.AdminException;

public interface AdminDao {

	String adminRegistration(Admin admin) throws AdminException;
	
	String addCourse(Course course) throws AdminException;
	
	String updateFee(int cid, int newFee) throws AdminException;
	
	String deleteCourse(int cid) throws AdminException;
	
	String addBatchToCourse(Batch batch) throws AdminException;
	
	List<CourseDTO> searchCourse(int cid) throws AdminException;
	
	String addStudentToBatch(int roll, int bid, int cid) throws AdminException;
	
	String updateSeatsOfBatch(int bid, int newSeats) throws AdminException;
	
	List<StudentDTO> showAllStudent() throws AdminException;
	
	Admin login(String username, String password) throws AdminException;
	
	List<Student> studentList() throws AdminException;
	
	List<Course> courseList() throws AdminException;
	
}
