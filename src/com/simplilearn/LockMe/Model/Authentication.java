package com.simplilearn.LockMe.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.simplilearn.LockMe.Model.UserCredentials;
import com.simplilearn.LockMe.Model.Users;

public class Authentication {
	
	
	private static Scanner keyboard;
	private static Scanner input;
	private static Scanner lockerInput;
	private static PrintWriter output;
	private static PrintWriter lockerOutput;
	private static Users users;
	private static UserCredentials userCredentials;
	private static boolean navigateBack;
	private static String currentUser;
	private static Scanner relationInput;
	private static PrintWriter relationOutput;


	public static void main(String[] args) throws IOException {
		try {
			initApp();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		welcomeScreen();
		signInOptions();

	}
	public static void signInOptions() throws IOException {
		System.out.println("1 . Registration ");
		System.out.println("2 . Login ");
		System.out.println("3 . Exit ");
//		if(keyboard.hasNext()) {
//			System.out.println("Input");
//		}
		int option = keyboard.nextInt();
		//System.out.println(option);
		switch(option) {
			case 1 : 
				registerUser();
				break;
			case 2 :
				loginUser();
				break;
			case 3:
				System.out.println("Thanks for using LockMe");
				System.exit(0);
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
		keyboard.close();
		input.close();
	}

	public static void lockerOptions(String inpUsername) throws IOException {
		System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
		System.out.println("2 . STORE CREDENTIALS ");
		System.out.println("3 . ADD FILE ");
		System.out.println("4 . DELETE FILE ");
		System.out.println("5 . SEARCH FILE");
		System.out.println("6 . EXIT TO MAIN MENU ");
		int option = keyboard.nextInt();
		System.out.println(option);
		switch(option) {
			case 1 : 
				fetchCredentials(inpUsername);
				break;
			case 2 :
				storeCredentials(inpUsername);
				break;
			case 3 :
				addFile();
				break;
			case 6:
				welcomeScreen();
				signInOptions();
				initApp();
				break;
			default :
				System.out.println("Please select an Option");
				break;
		}
		lockerInput.close();
	}

	public static void addFile() {
			System.out.println("Enter file name");
			String fileName = keyboard.next();
			if(fileName.length() > 5){
				File newFileItem = new File(fileName);
				relationOutput.println(currentUser + "-" + fileName);
				relationOutput.close();
			}
		
	} 

	public static void registerUser() throws IOException {
		
		System.out.println("WELCOME TO REGISTRATION PAGE");
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		users.setUsername(username);

		System.out.println("Enter Password :");
		String password = keyboard.next();
		users.setPassword(password);

		output.println(users.getUsername());
		output.println(users.getPassword());

		System.out.println("User Registration Suscessful !");
		output.close();
		//welcomeScreen();
		signInOptions();

	}
	public static void loginUser() throws IOException {
		if(navigateBack)
			initApp();
		System.out.println("WELCOME TO LOGIN PAGE");
		System.out.println("Enter Username :");
		String inpUsername = keyboard.next();
		boolean found = false;
		while(input.hasNext() && !found) {
			if(input.next().equals(inpUsername)) {
				System.out.println("Enter Password :");
				String inpPassword = keyboard.next();
				if(input.next().equals(inpPassword)) {
					System.out.println("Login Successful ! 200OK");
					found = true;
					currentUser = inpUsername;
					lockerOptions(inpUsername);
					break;
				}
			}
		}
		if(!found) {
			System.out.println("User Not Found : Login Failure : 404");
			
			//welcomeScreen();
			//signInOptions();
			initApp();
			loginUser();
		}
		

	}

	public static void welcomeScreen() {
		
		System.out.println("Welcome To LockMe");
		System.out.println("Your Personal Digital Locker");
		

	}
	
	public static void storeCredentials(String loggedInUser) throws IOException {
		
		
		System.out.println("WELCOME TO DIGITAL LOCKER STORE YOUR CREDENTIALS HERE");
		
		
		userCredentials.setLoginUser(loggedInUser);

		System.out.println("Enter Site Name :");
		String siteName = keyboard.next();
		userCredentials.setSiteName(siteName);

		System.out.println("Enter Username :");
		String username = keyboard.next();
		userCredentials.setUsername(username);

		System.out.println("Enter Password :");
		String password = keyboard.next();
		userCredentials.setPassword(password);

		lockerOutput.println(userCredentials.getLoginUser());
		lockerOutput.println(userCredentials.getSiteName());
		lockerOutput.println(userCredentials.getUsername());
		lockerOutput.println(userCredentials.getPassword());

		System.out.println("YOUR CREDENTIALS ARE STORED AND ALSO SECURED");
		lockerOutput.close();	
		System.out.println("Press 1 to add more credentials or 2 to go back");
		int inp = keyboard.nextInt();
		if(inp == 1) {
			navigateBack = false;
			storeCredentials(currentUser);
		} else {

				navigateBack = true;
				lockerOptions(currentUser);
		}
	}
	
	public static void fetchCredentials(String inpUsername) throws IOException {
		if(navigateBack) {
			File  lockerFile = new File("locker-file.txt");
			try {
				lockerInput = new Scanner(lockerFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("WELCOME TO DIGITAL LOCKER");		
		System.out.println("YOUR CREDENTIALS ARE");	
//		System.out.println("3 . BACK");
		System.out.println(inpUsername);


//		System.out.println(lockerInput.next());
		while(lockerInput.hasNext()) {
//			System.out.println(lockerInput.hasNext());
			if(lockerInput.next().equals(inpUsername)) {
				System.out.println("Site Name: "+lockerInput.next());
				System.out.println("User Name: "+lockerInput.next());
				System.out.println("User Password: "+lockerInput.next());
			}
		}
		System.out.println("Press 1 to go back");
		int inp = keyboard.nextInt();
		if(inp == 1) {
			navigateBack = true;
			lockerOptions(currentUser);
		}

	}

	public static void initApp() throws IOException {
 		
		File  dbFile = new File("database.txt");
		File  lockerFile = new File("locker-file.txt");
		File  relationFile = new File("relations.txt");
		
		relationFile.createNewFile();

		try {
			
			input = new Scanner(dbFile);
			
			lockerInput = new Scanner(lockerFile);

			
			keyboard = new Scanner(System.in);

			relationInput = new Scanner(relationFile);

			relationOutput = new PrintWriter(new FileWriter(relationFile,true));
			output = new PrintWriter( new FileWriter(dbFile,true));
			lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));

			users = new Users();
			userCredentials  = new UserCredentials();
			navigateBack = false;


		} catch (IOException e) {
			System.out.println("404 : File Not Found " + e);
		}

	}

}