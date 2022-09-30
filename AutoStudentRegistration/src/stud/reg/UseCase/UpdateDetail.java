package stud.reg.UseCase;

import java.util.Scanner;

import stud.reg.dao.StudentDao;
import stud.reg.dao.StudentDaoImpl;
import stud.reg.exception.StudentException;

public class UpdateDetail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Update Detail ");
		
		System.out.println("Enter your roll number : ");
		int roll = sc.nextInt();
		
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
