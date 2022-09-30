package stud.reg.UseCase;

import java.util.Scanner;

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
			
			System.out.println(ad.searchCourse(cid));
			
		}catch(AdminException e) {
			
			System.out.println(e.getMessage());
			
		}

	}

}
