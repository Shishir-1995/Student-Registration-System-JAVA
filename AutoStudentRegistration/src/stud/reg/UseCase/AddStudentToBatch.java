package stud.reg.UseCase;

import java.util.Scanner;

import stud.reg.dao.AdminDao;
import stud.reg.dao.AdminDaoImpl;
import stud.reg.exception.AdminException;

public class AddStudentToBatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Add Student To Batch ");
		System.out.println("----------------------");
		
		System.out.println("Enter the Student Roll Number : ");
		int roll = sc.nextInt();
		
		System.out.println("Enter Course ID : ");
		int cid = sc.nextInt();
		
		System.out.println("Enter Batch ID : ");
		int bid = sc.nextInt();
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			
			System.out.println(ad.addStudentToBatch(roll, bid, cid));
			
		} catch (AdminException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} 
				
		
	}

}
