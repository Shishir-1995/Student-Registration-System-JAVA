package stud.reg.UseCase;

import java.util.Scanner;

import stud.reg.bean.Student;
import stud.reg.dao.StudentDao;
import stud.reg.dao.StudentDaoImpl;
import stud.reg.exception.StudentException;

public class RegisterStudent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		int cid = sc.nextInt();
		
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

}
