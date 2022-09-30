package stud.reg.UseCase;

import java.util.Scanner;

import stud.reg.bean.Batch;
import stud.reg.dao.AdminDao;
import stud.reg.dao.AdminDaoImpl;
import stud.reg.exception.AdminException;

public class AddBatchToCourse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter New Batch Details ");
		System.out.println("--------------------------");
		
		System.out.println("Enter the Course ID of the batch : ");
		int cid = sc.nextInt();
		
		System.out.println("Enter the Batch ID (Must be Unique) : ");
		int bid = sc.nextInt();
		
		System.out.println("Enter the batch name : ");
		String bname = sc.next();
		
		System.out.println("Enter the batch duration ( in months ) : ");
		int duration = sc.nextInt();
		
		System.out.println("Enter the number of seats in this batch : ");
		int seats = sc.nextInt();
		
		Batch batch = new Batch(bid, bname, duration, seats, cid);
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			
			System.out.println(ad.addBatchToCourse(batch));
		}catch(AdminException ae) {
			System.out.println(ae.getMessage());
		}
		
		
		
		
	}

}
