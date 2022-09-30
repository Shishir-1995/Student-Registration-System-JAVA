package stud.reg.UseCase;

import java.util.ArrayList;
import java.util.List;

import stud.reg.bean.CoursesDTO;
import stud.reg.dao.StudentDao;
import stud.reg.dao.StudentDaoImpl;
import stud.reg.exception.StudentException;

public class ShowCourse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StudentDao sd = new StudentDaoImpl();
		
		List<CoursesDTO> courses = new ArrayList<>();
		
		try {
			courses = sd.showAllCourseDetails();
			
			if(courses.size() == 0) {
				System.out.println("No course to Show.");
			}else {
				for(CoursesDTO c: courses) {
					System.out.println(c);
					System.out.println("------------------------");
				}
			}
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		

	}

}
