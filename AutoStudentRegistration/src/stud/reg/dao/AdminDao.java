package stud.reg.dao;

import java.util.List;

import stud.reg.bean.Admin;
import stud.reg.bean.Course;
import stud.reg.bean.Student;
import stud.reg.exception.AdminException;

public interface AdminDao {

	String adminRegistration(Admin admin) throws AdminException;
	
	String addCourse(Course course) throws AdminException;
	
	String deleteCourse(int cid) throws AdminException;
	
	Course searchCourse(int cid) throws AdminException;
	
	String addStudentToCourse(int roll, int cid) throws AdminException;
	
	List<Student> showStudent() throws AdminException;
}
