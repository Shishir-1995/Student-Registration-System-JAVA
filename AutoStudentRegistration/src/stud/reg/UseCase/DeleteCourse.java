package stud.reg.UseCase;

import java.util.Scanner;

import stud.reg.dao.AdminDao;
import stud.reg.dao.AdminDaoImpl;
import stud.reg.exception.AdminException;

public class DeleteCourse {

	public static void main(String[] args) {
		
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

}
