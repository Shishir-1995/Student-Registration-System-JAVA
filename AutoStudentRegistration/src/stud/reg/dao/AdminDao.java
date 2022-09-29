package stud.reg.dao;

import java.util.List;

import stud.reg.bean.Admin;
import stud.reg.bean.Course;
import stud.reg.bean.Student;

public interface AdminDao {

	String adminRegistration(Admin admin);
	
	String addCourse(Course course);
	
	String deleteCourse(int cid);
	
	Course searchCourse(int cid);
	
	String addStudentToCourse(int roll, int cid);
	
	List<Student> showStudent();
}
