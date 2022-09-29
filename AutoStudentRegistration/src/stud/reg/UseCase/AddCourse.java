package stud.reg.UseCase;

import java.util.Scanner;

import stud.reg.bean.Course;
import stud.reg.dao.AdminDao;
import stud.reg.dao.AdminDaoImpl;
import stud.reg.exception.AdminException;

public class AddCourse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course Details ");
		System.out.println("---------------------");
		
		System.out.println("Course Name : ");
		String name = sc.nextLine();
		
		System.out.println("Course Fee : ");
		int fee = sc.nextInt();
		
		System.out.println("Course Seats : ");
		int seats = sc.nextInt();
		
		Course course = new Course();
		course.setCname(name);
		course.setFee(fee);
		course.setSeats(seats);
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			System.out.println(ad.addCourse(course));
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

}
