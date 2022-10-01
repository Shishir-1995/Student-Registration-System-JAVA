package stud.reg.UseCase;

import java.util.Scanner;

import stud.reg.bean.Admin;
import stud.reg.dao.AdminDao;
import stud.reg.dao.AdminDaoImpl;
import stud.reg.exception.AdminException;

public class AdminLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Your Admin Username : ");
		String user = sc.next();
		
		System.out.println("Enter Your Password : ");
		String pass = sc.next();
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			
			Admin admin = ad.login(user, pass);
			System.out.println("Welcome ! Your Credentials : ");
			System.out.println("-----------------------------");
			System.out.println(admin);
			
		} catch (AdminException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}

	}

}
