package stud.reg.dao;

import java.util.List;

import stud.reg.bean.Course;
import stud.reg.bean.Student;
import stud.reg.exception.StudentException;

public interface StudentDao {

	
	String studentRegistration(Student student) throws StudentException;
	
	String updateDetails(int roll, String field, String newData) throws StudentException;
	
	List<Course> showCourseDetails() throws StudentException;
	
	
}
