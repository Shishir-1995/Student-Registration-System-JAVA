package student.registration;

import java.util.Scanner;

import stud.reg.UseCase.StudentMenu;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void studentAuth() {
		
		System.out.println("PLEASE SELECT FROM THE FOLLOWING OPTIONS ");
		System.out.println("1. Student Login");
		System.out.println("2. New Student Registration");
		System.out.println("3. Show All Courses and Seats Available");
		System.out.println("\n\n");
		System.out.println("0. Go Back");
		System.out.println("99. Exit The Application");
		
		int option = sc.nextInt();
		StudentMenu student = new StudentMenu();
		
		switch(option){
			case 0 : menu();
				break;
			case 2 : student.register();
					studentAuth();
				break;
			case 1 : student.login();
				studentActivity();
				break;
			case 3 : student.showAllCourse();
				break;
			case 99 :
				System.out.println("Thank you for using Application");
				break;
			default : 
				System.out.println("Invalid Selection ");
				studentAuth();
		}
		
	}
	
	public static void studentActivity() {
		
		System.out.println("PLEASE SELECT FROM THE FOLLOWING OPTIONS ");
		System.out.println("1. Update Details ");
		System.out.println("\n\n");
		System.out.println("0. Go Back");
		System.out.println("99. Exit the Application");
		
		int option = sc.nextInt();
		StudentMenu student = new StudentMenu();
		
		switch(option) {
			case 0 : studentAuth();
				break;
			case 1 : student.updateDetail();
				break;
			case 99 :
				System.out.println("Thank you for using Application");
				break;
			default : 
				System.out.println("Invalid Selection ");
				studentActivity();
		}
	}
	
	public static void adminAuth() {
		
		System.out.println("PLEASE SELECT FROM THE FOLLOWING OPTIONS ");
		System.out.println("1. New Admin Registration");
		System.out.println("2. Admin Login");
		System.out.println("\n\n");
		System.out.println("0. Go Back");
		System.out.println("99. Exit The Application");
	}
	
	public static void menu() {
		
		System.out.println("PLEASE SELECT FROM THE OPTIONS ");
		System.out.println("1. Student ");
		System.out.println("2. Admin ");
		System.out.println("\n\n");
		System.out.println("99. Exit the Application");
		
		int option = sc.nextInt();
		
		if(option == 1) {
			studentAuth();
		}else if(option == 2) {
			adminAuth();
		}else if(option == 99) {
			System.out.println("Thank You for using Application.");
		}else {
			System.out.println("Invalid Selection : Please try again");
			menu();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("WELCOME TO STUDENT REGISTRATION PORTAL ");
		System.out.println("--------------------------------------");
		
		menu();
	}

}
