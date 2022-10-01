package stud.reg.UseCase;

import java.util.Scanner;

import stud.reg.bean.Student;
import stud.reg.dao.StudentDao;
import stud.reg.dao.StudentDaoImpl;
import stud.reg.exception.StudentException;

public class StudentLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
			
		} catch (StudentException e) {
			// TODO: handle exception
			
			System.out.println(e.getMessage());
		}

	}

}
