package stud.reg.UseCase;

import java.util.List;
import java.util.Scanner;

import stud.reg.bean.Admin;
import stud.reg.bean.Course;
import stud.reg.bean.CourseDTO;
import stud.reg.dao.AdminDao;
import stud.reg.dao.AdminDaoImpl;
import stud.reg.exception.AdminException;

public class AdminMenu {
	
	public void register()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Admin Details");
		System.out.println("-------------------");
		
		System.out.println("Enter name : ");
		String name = sc.nextLine();
		
		System.out.println("Enter username : ");
		String user = sc.next();
		
		System.out.println("Enter Password : ");
		String pass = sc.next();
		
		Admin admin = new Admin();
		admin.setAname(name);
		admin.setUsername(user);
		admin.setPassword(pass);
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			System.out.println(ad.adminRegistration(admin));
		}catch (AdminException ae) {

			System.out.println(ae.getMessage());
			
		}
	}
	
	public void addCourse() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course Details ");
		System.out.println("---------------------");
		
		System.out.println("Course Name : ");
		String name = sc.nextLine();
		
		System.out.println("Course Fee : ");
		int fee = sc.nextInt();
		
		Course course = new Course();
		course.setCname(name);
		course.setFee(fee);
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			System.out.println(ad.addCourse(course));
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateFee() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course ID : ");
		int cid = sc.nextInt();
		
		System.out.println("Enter New Fees : ");
		int fee = sc.nextInt();
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			System.out.println(ad.updateFee(cid, fee));
		}catch(AdminException ae){
			System.out.println(ae.getMessage());
		}
				
		
	}

	public void deleteCourse() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course ID : ");
		int cid = sc.nextInt();
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			
			System.out.println(ad.deleteCourse(cid));
			
		}catch(AdminException ae) {
			System.out.println(ae.getMessage());
		}
	}

	public void searchCourse() {
		
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
