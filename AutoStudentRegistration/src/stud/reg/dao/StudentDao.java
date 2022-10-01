package stud.reg.dao;

import java.util.List;

import stud.reg.bean.Course;
import stud.reg.bean.CoursesDTO;
import stud.reg.bean.Student;
import stud.reg.exception.StudentException;

public interface StudentDao {

	
	String studentRegistration(Student student, int cid) throws StudentException;
	
	String updateDetails(int roll, String field, String newData) throws StudentException;
	
	List<CoursesDTO> showAllCourseDetails() throws StudentException;
	
	Student login(String username, String password) throws StudentException;
	
	
}
