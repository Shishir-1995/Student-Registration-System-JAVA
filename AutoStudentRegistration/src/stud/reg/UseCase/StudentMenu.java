package stud.reg.UseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import stud.reg.bean.CoursesDTO;
import stud.reg.bean.Student;
import stud.reg.dao.StudentDao;
import stud.reg.dao.StudentDaoImpl;
import stud.reg.exception.StudentException;

public class StudentMenu {

	public void register() {
		Scanner sc = new Scanner(System.in);
		
		Student student = new Student();
		
		System.out.println("Enter Student Details ");
		System.out.println("---------------------");
		
		System.out.println("Enter Name : ");
		String name = sc.nextLine();
		
		System.out.println("Select Gender (M/F) : ");
		String gender = sc.next();
		
		System.out.println("Enter Email : ");
		String email = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		System.out.println("Enter Course ID :");
		String c;
		int cid = 0;
		
		try {
			c = sc.next();
			cid = Integer.parseInt(c);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Try Again !");
			register();
		}
		
		student.setName(name);
		student.setGender(gender);
		student.setEmail(email);
		student.setPassword(password);
		
		StudentDao sd = new StudentDaoImpl();
		try {
			
			System.out.println(sd.studentRegistration(student, cid));
			
		} 
		catch (StudentException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	public int login() {
		
		int check = 0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Your email (case sensitive)");
		String username = sc.next();
		
		System.out.println("Enter Your Password (case sensitive)");
		String password = sc.next();
		
		StudentDao sd = new StudentDaoImpl();
		
		try {
			
			Student student = sd.login(username, password);
			
			System.out.println("Welcome ! Your Credentials are : ");
			System.out.println("----------------------------");
			System.out.println(student);
			
			check = student.getRoll();
			
		} catch (StudentException e) {
			// TODO: handle exception
			
			System.out.println(e.getMessage());
		}
		
		return check;
		
	}
	
	public void showAllCourse() {
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
	
	public void updateDetail(int rollNumber) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Update Detail ");
		
//		System.out.println("Enter your roll number : ");
//		int roll = sc.nextInt();
		int roll = rollNumber;
		
		System.out.println("Select field to update (name/gender/email/password): ");
		String field = sc.next();
		
		sc.nextLine();
		System.out.println("Enter the new Data : ");
		String data = sc.nextLine();
		
		field = field.toLowerCase();
		
		if(field.equals("roll")) {
			System.out.println("No Access to change Roll number");
		}else {
			
			StudentDao sd = new StudentDaoImpl();
			try {
				System.out.println( sd.updateDetails(roll, field, data));
			} catch (StudentException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
	}
	
}
