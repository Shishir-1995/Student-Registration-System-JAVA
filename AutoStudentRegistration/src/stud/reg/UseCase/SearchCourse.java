package stud.reg.UseCase;

import java.util.List;
import java.util.Scanner;

import stud.reg.bean.CourseDTO;
import stud.reg.dao.AdminDao;
import stud.reg.dao.AdminDaoImpl;
import stud.reg.exception.AdminException;

public class SearchCourse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the course ID : ");
		int cid = sc.nextInt();
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			
			List<CourseDTO> courses = ad.searchCourse(cid);
			
			for(CourseDTO c: courses) {
				
				System.out.println(c);
				
				System.out.println("----------------------------------------");
				
			}
			
			if(courses.size() == 0) System.out.println("No Data to Show. ");
			
		}catch(AdminException e) {
			
			System.out.println(e.getMessage());
			
		}

	}

}
