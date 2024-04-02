import java.io.*;
import java.sql.*;
import java.util.Scanner;

import oracle.jdbc.OraclePreparedStatement;

public class Main {

	static final String username = "T13";
	static final String password = "Summer2022T13";
	static final String DB_URL = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";
	static final int LeftPad = 10;
	private Connection Con;
     
	public static void main(String[] args) throws SQLException {
		try {
			Main pgm = new Main();
			pgm.Con = pgm.GetConnection(DB_URL, username, password);
			pgm.MainMenu();
			pgm.Con.close();
		}catch(SQLException se) {
			System.out.printf("Main method has an SQL issue: %s.\n", se.toString());
		}
	}
    
	Connection GetConnection(String url, String logIn, String passWd) throws SQLException{
		Connection Con = null;
		try{
			Con = DriverManager.getConnection(url, logIn, passWd);//, logIn, passWd);
		
		}//end try
		catch(SQLException e) {
			System.out.println("Failed to connect to DBMS in GetConnection() method.");
		}
		return Con;
	}
	 
    int MainMenu() throws SQLException {
 		String usrInput = "";
 		Scanner sc = new Scanner(System.in);
 		PrintItm("Welcome to the program.", LeftPad);

 		do {
 			PrintItm("Please enter your numeric selection.", LeftPad);
 			PrintItm(" ", LeftPad);
 			PrintItm("1. Patients.", LeftPad);
 			PrintItm("2. Doctors.", LeftPad);
 			PrintItm("3. Appointments.", LeftPad);
 			PrintItm("4. Bills.", LeftPad);
 			PrintItm("5. Reports.", LeftPad);
 			PrintItm("6. Exit Program.", LeftPad);
 			usrInput = sc.next();
 			if (usrInput.equals("1")){
 				Patients();
 			}
 			else if(usrInput.equals("2")){
 				Doctors();
 			}
 			else if(usrInput.equals("3")){
 				Appointments();
 			}
 			else if(usrInput.equals("4")){
 				Bills();
 			}
 			else if(usrInput.equals("5")){
 				Reports();
 			}
 			else;
 		}while(usrInput.equals("6") != true);
 		PrintItm("You have chosen to exit the program.", LeftPad);
 		
 		System.exit(0);
 		return 0;
 	}
	int Patients() throws SQLException{
		String usrInput = "";
 		Scanner sc = new Scanner(System.in);
 		PrintItm("Patients.", LeftPad);
 		
 		do {
 			PrintItm("Please enter your numeric selection.", LeftPad);
 			PrintItm(" ", LeftPad);
 			PrintItm("1. Preview Patient Information.", LeftPad);
 			PrintItm("2. Create Patient Information.", LeftPad);
 			PrintItm("3. Edit Patient Information.", LeftPad);
 			PrintItm("4. Delete Patient Information.", LeftPad);
 			PrintItm("5. Go Back To Main Menu.", LeftPad);
 			PrintItm("6. Exit Program.", LeftPad);
 			usrInput = sc.next();
 			
 			if (usrInput.equals("1")){
 				String ssn, fullName, insuranceCompanyy, insuranceNumber, activity;
 				String qry = "";
 				String choice = "";
 				String srch = "";
 				ResultSet rs;
 				PreparedStatement pStmt;
 				
 				PrintItm("I am in Preview patient Details", LeftPad);

 				PrintItm("Enter the Patients Ssn:", LeftPad);
 				srch = sc.next();
 				qry = "SELECT SSN, NAME, INSURANCE_COMPANY, INSURANCE_NUMBER, ACTIVITY FROM PATIENTS WHERE SSN like ?";
 				pStmt = this.Con.prepareStatement(qry);
 				pStmt.setString(1, srch);	
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-15s %-20s %-20s %-20s %-20s\n", "SSN", "NAME", "INSURANCE_COMPANY", "INSURANCE_NUMBER", "ACTIVITY");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						ssn = rs.getString(1);
 						fullName = rs.getString(2);
 						insuranceCompanyy= rs.getString(3);
 						insuranceNumber = rs.getString(4);
 						activity = rs.getString(5);
 						System.out.printf("          %-15s %-20s %-20s %-20s %-20s\n", ssn, fullName, insuranceCompanyy, insuranceNumber, activity);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 			}
 			
 			else if(usrInput.equals("2")){
			PreparedStatement myStmt = null;

			PrintItm("I am in Create Patient Details", LeftPad);
			Scanner psc = new Scanner(System.in);
			
			System.out.print("Enter your Ssn: ");
			String ssn = psc.nextLine();
			
			System.out.print("Enter your Full Name: ");
			String fullName = psc.nextLine();
			
			System.out.print("Enter your Insurance Company: ");
			String insuranceCompanyy = psc.nextLine();
			
			System.out.print("Enter your Insurance Number: ");
			String insuranceNumber = psc.nextLine();
			
			System.out.print("Enter your Activity: ");
			String activity = psc.nextLine();

			
			String sql = "insert into PATIENTS" + "(SSN, NAME, INSURANCE_COMPANY, INSURANCE_NUMBER, ACTIVITY)" + "values (?, ?, ?, ?, ?)";
			
			myStmt = Con.prepareStatement(sql);
			
			myStmt.setString(1, ssn);
			myStmt.setString(2, fullName);
			myStmt.setString(3, insuranceCompanyy);
			myStmt.setString(4, insuranceNumber);
			myStmt.setString(5, activity);
			myStmt.executeUpdate();
			
			System.out.printf("You have successfully created Patient: %s\n", ssn);
			
 			}
 			
 			else if(usrInput.equals("3")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Edit Patient Details", LeftPad);
 				Scanner pesc = new Scanner(System.in);
 				
 				System.out.print("Enter Patient SSN: ");
 				String ssn = pesc.nextLine();
 				
 				System.out.print("Enter your Full Name: ");
 				String fullName = pesc.nextLine();
 				
 				System.out.print("Enter your Insurance Company: ");
 				String insurance_company = pesc.nextLine();
 				
 				System.out.print("Enter your Insurance Number: ");
 				String insurance_number = pesc.nextLine();
 				
 				System.out.print("Enter your Activity: ");
 				String activity = pesc.nextLine();
 				
 				String sql = "update PATIENTS set NAME = ?, INSURANCE_COMPANY = ?, INSURANCE_NUMBER = ?, ACTIVITY = ? WHERE SSN = ?";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, fullName);
 				myStmt.setString(2, insurance_company);
 				myStmt.setString(3, insurance_number);
 				myStmt.setString(4, activity);
 				myStmt.setString(5, ssn);
 				myStmt.executeUpdate();
 				
 			}
 			else if(usrInput.equals("4")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Delete Patient Details", LeftPad);
 				Scanner pdsc = new Scanner(System.in);
 				
 				System.out.print("Enter Patient SSN: ");
 				String ssn = pdsc.nextLine();
 				
 				String sql = "delete FROM PATIENTS WHERE SSN = ?";
 				
 				myStmt = this.Con.prepareStatement(sql);
 				
 				myStmt.setString(1, ssn);
 				myStmt.executeUpdate();
 				
 			}
 			else if(usrInput.equals("5")){
 				MainMenu();
 			}
 			else;
 		}while(usrInput.equals("6") != true);
 		PrintItm("You have chosen to exit the program.", LeftPad);
 		
 		System.exit(0);
		return 0;
	}
	int Doctors() throws SQLException{
		String usrInput = "";
 		Scanner sc = new Scanner(System.in);
 		PrintItm("Doctors.", LeftPad);

 		do {
 			PrintItm("Please enter your numeric selection.", LeftPad);
 			PrintItm(" ", LeftPad);
 			PrintItm("1. Preview Doctor Information.", LeftPad);
 			PrintItm("2. Create Doctor Information.", LeftPad);
 			PrintItm("3. Edit Doctor Information.", LeftPad);
 			PrintItm("4. Delete Doctor Information.", LeftPad);
 			PrintItm("5. Go Back To Main Menu.", LeftPad);
 			PrintItm("6. Exit Program.", LeftPad);
 			usrInput = sc.next();
 			
 			if (usrInput.equals("1")){
 				String employee_id, name, activity;
 				String qry = "";
 				String choice = "";
 				String srch = "";
 				ResultSet rs;
 				PreparedStatement pStmt;
 				
 				PrintItm("I am in Preview Doctor Details", LeftPad);
 				
 				PrintItm("Enter the Doctor ID:", LeftPad);
 				srch = sc.next();
 				qry = "SELECT EMPLOYEE_ID, NAME, ACTIVITY FROM DOCTORS WHERE EMPLOYEE_ID like ?";
 				pStmt = this.Con.prepareStatement(qry);
 				pStmt.setString(1, srch);	
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-15s %-20s %-20s\n", "EMPLOYEE_ID", "NAME", "ACTIVITY");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						employee_id = rs.getString(1);
 						name = rs.getString(2);
 						activity = rs.getString(3);
 						System.out.printf("          %-15s %-20s %-20s\n", employee_id, name, activity);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 			}	
 			else if(usrInput.equals("2")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Create Doctor Details", LeftPad);
 		 		Scanner dsc = new Scanner(System.in);
 				
 		 		System.out.print("Enter your Full Name: ");
 				String name = dsc.nextLine();
 				
 				System.out.print("Enter your Activity: ");
 				String activity = dsc.nextLine();

 				String sql = "insert into DOCTORS" + "(NAME, ACTIVITY)" + "values (?, ?)";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, name);
 				myStmt.setString(2, activity);
 				myStmt.executeUpdate();
 				
 				
 				sql = "select doctors.employee_id from doctors where doctors.employee_id = (Select max(employee_id) from doctors)";
 				myStmt = Con.prepareStatement(sql);
 				ResultSet rs = myStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					String ID = rs.getString(1);
 					System.out.printf("You have successfully created Doctor: #%s\n", ID);
 					}
 					System.out.printf("\n");
 					
 				
 			}
 			else if(usrInput.equals("3")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Edit Doctor Details", LeftPad);
 				Scanner desc = new Scanner(System.in);
 				
 				System.out.print("Enter Employee ID: ");
 				String employee_id = desc.nextLine();
 				
 				System.out.print("Enter your Full Name: ");
 				String name = desc.nextLine();
 				
 				System.out.print("Enter your Activity: ");
 				String activity = desc.nextLine();
 				
 				String sql = "update DOCTORS set NAME = ?, ACTIVITY = ? WHERE EMPLOYEE_ID = ?";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, name);
 				myStmt.setString(2, activity);
 				myStmt.setString(3, employee_id);
 				myStmt.executeUpdate();
 				
 			}
 			else if(usrInput.equals("4")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Delete Doctor Details", LeftPad);
 				Scanner ddsc = new Scanner(System.in);
 				
 				System.out.print("Enter Employee ID: ");
 				String employee_id = ddsc.nextLine();
 				
 				String sql = "delete FROM DOCTORS WHERE EMPLOYEE_ID = ?";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, employee_id);
 				myStmt.executeUpdate();
 				
 			}
 			else if(usrInput.equals("5")){
 				MainMenu();
 			}
 			else;
 		}while(usrInput.equals("6") != true);
 		PrintItm("You have chosen to exit the program.", LeftPad);
 		
 		System.exit(0);
		return 0;
	}
	int Appointments() throws SQLException{
		String usrInput = "";
 		Scanner sc = new Scanner(System.in);
 		PrintItm("Appointments.", LeftPad);

 		do {
 			PrintItm("Please enter your numeric selection.", LeftPad);
 			PrintItm(" ", LeftPad);
 			PrintItm("1. Preview Appointment Information.", LeftPad);
 			PrintItm("2. Create Appointment Information.", LeftPad);
 			PrintItm("3. Edit Appointment Information.", LeftPad);
 			PrintItm("4. Delete Appointment Information.", LeftPad);
 			PrintItm("5. Go Back To Main Menu.", LeftPad);
 			PrintItm("6. Exit Program.", LeftPad);
 			usrInput = sc.next();
 			
 			if (usrInput.equals("1")){
 				String appointment_id, time, patients_ssn, doctors_employee_id;
 				String qry = "";
 				String choice = "";
 				String srch = "";
 				ResultSet rs;
 				PreparedStatement pStmt;
 				
 				PrintItm("I am in Preview Appointment Details", LeftPad);
 				
 				PrintItm("Enter the Appointment ID:", LeftPad);
 				srch = sc.next();
 				qry = "SELECT APPOINTMENT_ID, TIME, PATIENTS_SSN, DOCTORS_EMPLOYEE_ID FROM APPOINTMENTS WHERE APPOINTMENT_ID like ?";
 				pStmt = this.Con.prepareStatement(qry);
 				pStmt.setString(1, srch);	
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-15s %-20s %-20s %-20s\n", "APPOINTMENT_ID", "TIME", "PATIENTS_SSN", "DOCTORS_EMPLOYEE_ID");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						appointment_id = rs.getString(1);
 						time = rs.getString(2);
 						patients_ssn = rs.getString(3);
 						doctors_employee_id = rs.getString(4);
 						System.out.printf("          %-15s %-20s %-20s %-20s\n", appointment_id, time, patients_ssn, doctors_employee_id);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 			}	
 			else if(usrInput.equals("2")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Create Appointment Details", LeftPad);
 		 		Scanner asc = new Scanner(System.in);
 				
 		 		System.out.print("Enter the Appointment Time: ");
 				String time = asc.nextLine();
 				
 				System.out.print("Enter the Patient Ssn: ");
 				String patients_ssn = asc.nextLine();
 				
 				System.out.print("Enter the Doctor's Employee ID: ");
 				String doctors_employee_id = asc.nextLine();

 				String sql = "insert into APPOINTMENTS" + "(TIME, PATIENTS_SSN, DOCTORS_EMPLOYEE_ID)" + "values (TO_DATE(?, 'MM/DD/YYYY HH24:MI'),?,?)";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, time);
 				myStmt.setString(2, patients_ssn);
 				myStmt.setString(3, doctors_employee_id);
 				myStmt.executeUpdate();
 				
 				sql = "select appointments.appointment_id from appointments where appointments.appointment_id = (Select max(appointment_id) from appointments)";
 				myStmt = this.Con.prepareStatement(sql);
 				ResultSet rs = myStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					String ID = rs.getString(1);
 					System.out.printf("You have successfully created Appointment: #%s\n", ID);
 					}
 				System.out.printf("\n");
 				
 				
 			}
 			else if(usrInput.equals("3")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Edit Appointment Details", LeftPad);
 				Scanner aesc = new Scanner(System.in);
 				
 				System.out.print("Enter Appointment ID: ");
 				String appointment_id = aesc.nextLine();
 				
 				System.out.print("Enter the Appointment Time: ");
 				String time = aesc.nextLine();
 				
 				System.out.print("Enter the Patient Ssn: ");
 				String patients_ssn = aesc.nextLine();
 				
 				System.out.print("Enter the Doctor's Employee ID: ");
 				String doctors_employee_id = aesc.nextLine();
 				
 				String sql = "update APPOINTMENTS set TIME = ?, PATIENTS_SSN = ?, DOCTORS_EMPLOYEE_ID = ? WHERE APPOINTMENT_ID = ?";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, time);
 				myStmt.setString(2, patients_ssn);
 				myStmt.setString(3, doctors_employee_id);
 				myStmt.setString(4, appointment_id);
 				myStmt.executeUpdate();
 				
 			}
 			else if(usrInput.equals("4")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Delete Appointment Details", LeftPad);
 				Scanner adsc = new Scanner(System.in);
 				
 				System.out.print("Enter The Appointment ID: ");
 				String appointment_id = adsc.nextLine();
 				
 				String sql = "delete FROM APPOINTMENTS WHERE APPOINTMENT_ID = ?";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, appointment_id);
 				myStmt.executeUpdate();
 				
 			}
 			else if(usrInput.equals("5")){
 				MainMenu();
 			}
 			else;
 		}while(usrInput.equals("6") != true);
 		PrintItm("You have chosen to exit the program.", LeftPad);
 		
 		System.exit(0);
		return 0;
	}
	int Bills() throws SQLException{
		String usrInput = "";
 		Scanner sc = new Scanner(System.in);
 		PrintItm("Bills.", LeftPad);

 		do {
 			PrintItm("Please enter your numeric selection.", LeftPad);
 			PrintItm(" ", LeftPad);
 			PrintItm("1. Preview Bill Information.", LeftPad);
 			PrintItm("2. Create Bill Information.", LeftPad);
 			PrintItm("3. Edit Bill Information.", LeftPad);
 			PrintItm("4. Delete Bill Information.", LeftPad);
 			PrintItm("5. Go Back To Main Menu.", LeftPad);
 			PrintItm("6. Exit Program.", LeftPad);
 			usrInput = sc.next();
 			
 			if (usrInput.equals("1")){
 				String bill_id, toatl_due, paid_status, out_of_pocket, claim, app_id;
 				String qry = "";
 				String choice = "";
 				String srch = "";
 				ResultSet rs;
 				PreparedStatement pStmt;
 				
 				PrintItm("I am in Preview Bill Details", LeftPad);
 				
 				PrintItm("Enter the Bill ID:", LeftPad);
 				srch = sc.next();
 				qry = "SELECT BILL_ID, TOTAL_DUE, PAID_STATUS, OUT_OF_POCKET, CLAIM, APP_ID FROM BILLS WHERE BILL_ID like ?";
 				pStmt = this.Con.prepareStatement(qry);
 				pStmt.setString(1, srch);	
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-15s %-20s %-20s %-20s %-20s %-20s\n", "BILL_ID", "TOTAL_DUE", "PAID_STATUS", "OUT_OF_POCKET", "CLAIM", "APP_ID");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						bill_id = rs.getString(1);
 						toatl_due = rs.getString(2);
 						paid_status = rs.getString(3);
 						out_of_pocket = rs.getString(4);
 						claim = rs.getString(5);
 						app_id = rs.getString(6);
 						System.out.printf("          %-15s %-20s %-20s %-20s %-20s %-20s\n", bill_id, toatl_due, paid_status, out_of_pocket, claim, app_id);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 			}	
 			else if(usrInput.equals("2")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Create Bill Details", LeftPad);
 		 		Scanner bsc = new Scanner(System.in);
 				
 		 		System.out.print("Enter The Amount Due: ");
 				String toatl_due = bsc.nextLine();
 				
 				System.out.print("Enter The Paid Status: ");
 				String paid_status = bsc.nextLine();
 				
 				System.out.print("Enter The Amount Paid From Pocket: ");
 				String out_of_pocket = bsc.nextLine();
 				
 				System.out.print("Enter The Amount Paid From Insurance: ");
 				String claim = bsc.nextLine();
 				
 				System.out.print("Enter The Appointment ID: ");
 				String app_id = bsc.nextLine();

 				String sql = "insert into BILLS" + "(TOTAL_DUE, PAID_STATUS, OUT_OF_POCKET, CLAIM, APP_ID)" + "values (?,?,?,?,?)";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, toatl_due);
 				myStmt.setString(2, paid_status);
 				myStmt.setString(3, out_of_pocket);
 				myStmt.setString(4, claim);
 				myStmt.setString(5, app_id);
 				myStmt.executeUpdate();
 				
 				sql = "select bills.bill_id from bills where bills.bill_id = (Select max(bill_id) from bills)";
 				myStmt = this.Con.prepareStatement(sql);
 				ResultSet rs = myStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					String ID = rs.getString(1);
 					System.out.printf("You have successfully created Bill: #%s\n", ID);
 					}
 				System.out.printf("\n");
 				
 				
 			}
 			else if(usrInput.equals("3")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Edit Bill Details", LeftPad);
 				Scanner besc = new Scanner(System.in);
 				
 				System.out.print("Enter Bill ID: ");
 				String bill_id = besc.nextLine();
 				
 				System.out.print("Enter The Amount Due: ");
 				String toatl_due = besc.nextLine();
 				
 				System.out.print("Enter The Paid Status: ");
 				String paid_status = besc.nextLine();
 				
 				System.out.print("Enter The Amount Paid From Pocket: ");
 				String out_of_pocket = besc.nextLine();
 				
 				System.out.print("Enter The Amount Paid From Insurance: ");
 				String claim = besc.nextLine();
 				
 				System.out.print("Enter The Appointment ID: ");
 				String app_id = besc.nextLine();
 				
 				String sql = "update BILLS set TOTAL_DUE = ?, PAID_STATUS = ?, OUT_OF_POCKET = ?, CLAIM = ?, APP_ID = ? WHERE BILL_ID = ?";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, toatl_due);
 				myStmt.setString(2, paid_status);
 				myStmt.setString(3, out_of_pocket);
 				myStmt.setString(4, claim);
 				myStmt.setString(5, app_id);
 				myStmt.setString(6, bill_id);
 				myStmt.executeUpdate();
 				
 			}
 			else if(usrInput.equals("4")){
 				PreparedStatement myStmt = null;

 				PrintItm("I am in Delete Bill Details", LeftPad);
 				Scanner bdsc = new Scanner(System.in);
 				
 				System.out.print("Enter The Bill ID: ");
 				String bill_id = bdsc.nextLine();
 				
 				String sql = "delete FROM BILLS WHERE BILL_ID = ?";
 				
 				myStmt = Con.prepareStatement(sql);
 				
 				myStmt.setString(1, bill_id);
 				myStmt.executeUpdate();
 				
 			}
 			else if(usrInput.equals("5")){
 				MainMenu();
 			}
 			else;
 		}while(usrInput.equals("6") != true);
 		PrintItm("You have chosen to exit the program.", LeftPad);
 		
 		System.exit(0);
		return 0;
	}
	int Reports() throws SQLException{
		
		String qry = "";
		//String choice = "";
		//String srch = "";
		ResultSet rs;
		PreparedStatement pStmt;
		
		PrintItm("Please enter report type:", LeftPad);
			PrintItm(" ", LeftPad);
			PrintItm("1. Outstanding Appointments.", LeftPad);
			PrintItm("2. Unpaid Bills.", LeftPad);
			PrintItm("3. Paid Bills.", LeftPad);
			PrintItm("4. Active Doctors.", LeftPad);
			PrintItm("5. Active Patients.", LeftPad);
			Scanner sc = new Scanner(System.in);
			String usrInput = sc.next();
 			if (usrInput.equals("1")){
 				String appID, dateTime, pName, dName;
 				System.out.println("All Upcoming Appointments");
 				qry = "select  patients.name, doctors.name, appointments.appointment_id, appointments.time\r\n"
 						+ "from patients\r\n"
 						+ "inner join appointments\r\n"
 						+ "on patients.ssn = appointments.patients_ssn \r\n"
 						+ "inner join doctors\r\n"
 						+ "on doctors.employee_id = appointments.doctors_employee_id\r\n"
 						+ "where appointments.time >= CURRENT_DATE ORDER BY appointments.time";
 				pStmt = this.Con.prepareStatement(qry);
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-20s %-25s %-20s %-17s\n", "PATIENT_NAME", "DOCTOR_NAME","APPOINTMENT_ID", "DATE/TIME");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						pName = rs.getString(1);
 						dName = rs.getString(2);
 						appID= rs.getString(3);
 						dateTime = rs.getString(4);
 						System.out.printf("          %-20s %-25s %-20s %-17s\n", pName, dName, appID, dateTime);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 				
 			}
 			else if(usrInput.equals("2")){
 				String pName, billID, appID, paidStatus, oop, claim, total;
 				System.out.println("All Unpaid Bills");
 				qry = "select patients.name, bills.bill_id, bills.app_id, bills.paid_status, TO_CHAR (bills.out_of_pocket, '999,999.99'), TO_CHAR (bills.claim, '999,999.99'), TO_CHAR (bills.total_due, '999,999.99')\r\n"
 						+ "from patients\r\n"
 						+ "inner join appointments\r\n"
 						+ "on patients.ssn = appointments.patients_ssn\r\n"
 						+ "inner join bills\r\n"
 						+ "on appointments.appointment_id = bills.app_id\r\n"
 						+ "where bills.paid_status = 'Unpaid' order by patients.name";
 				pStmt = this.Con.prepareStatement(qry);
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-20s %-15s %-15s %-15s %-15s %-15s %-15s\n", "PATIENT_NAME", "BILL_ID", "APPOINTMENT_ID", "PAID_STATUS", "OUT_OF_POCKET", "INSURANCE", "TOTAL_DUE");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						pName = rs.getString(1);
 						billID = rs.getString(2);
 						appID= rs.getString(3);
 						paidStatus = rs.getString(4);
 						oop = rs.getString(5);
 						claim = rs.getString(6);
 						total = rs.getString(7);
 						System.out.printf("          %-20s %-15s %-15s %-10s %-15s %-15s %-10s\n", pName, billID, appID, paidStatus, oop, claim, total);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 			}
 			else if(usrInput.equals("3")){
 				String pName, billID, appID, paidStatus, oop, claim, total;
 				System.out.println("All Paid Bills");
 				qry = "select patients.name, bills.bill_id, bills.app_id, bills.paid_status, TO_CHAR (bills.out_of_pocket, '999,999.99'), TO_CHAR (bills.claim, '999,999.99'), TO_CHAR (bills.total_due, '999,999.99')\r\n"
 						+ "from patients\r\n"
 						+ "inner join appointments\r\n"
 						+ "on patients.ssn = appointments.patients_ssn\r\n"
 						+ "inner join bills\r\n"
 						+ "on appointments.appointment_id = bills.app_id\r\n"
 						+ "where bills.paid_status = 'Paid' order by patients.name";
 				pStmt = this.Con.prepareStatement(qry);
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-20s %-15s %-15s %-15s %-15s %-15s %-15s\n", "PATIENT_NAME", "BILL_ID", "APPOINTMENT_ID", "PAID_STATUS", "OUT_OF_POCKET", "INSURANCE", "TOTAL_DUE");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						pName = rs.getString(1);
 						billID = rs.getString(2);
 						appID= rs.getString(3);
 						paidStatus = rs.getString(4);
 						oop = rs.getString(5);
 						claim = rs.getString(6);
 						total = rs.getString(7);
 						System.out.printf("          %-20s %-15s %-15s %-10s %-15s %-15s %-10s\n", pName, billID, appID, paidStatus, oop, claim, total);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 			}
 			else if(usrInput.equals("4")){
 				String dName, dID, activity;
 				System.out.println("All Active Doctors");
 				qry = "select * from doctors where activity = 'Active' order by doctors.employee_id";
 				pStmt = this.Con.prepareStatement(qry);
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-25s %-20s %-20s\n", "DOCTOR_NAME", "DOCTOR_ID","ACTIVITY");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						dName = rs.getString(2);
 						dID = rs.getString(1);
 						activity= rs.getString(3);
 						System.out.printf("          %-25s %-20s %-20s\n", dName, dID, activity);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 			}
 			else if(usrInput.equals("5")){
 				String pName, pSSN, iName, iNum, activity;
 				System.out.println("All Active Patients");
 				qry = "select * from patients where activity = 'Active' order by patients.ssn";
 				pStmt = this.Con.prepareStatement(qry);
 				rs = pStmt.executeQuery();
 				
 				if(!rs.next()) {
 					PrintItm("No match found.", LeftPad);
 				}else {
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					System.out.printf("          %-25s %-20s %-20s %-20s %-20s\n", "PATIENT_NAME", "PATIENT_SSN", "INSURANCE_COMPANY", "INSURANCE_NUMBER", "ACTIVITY");
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");							
 					}
 					System.out.printf("\n");
 					do{
 						pName = rs.getString(2);
 						pSSN = rs.getString(1);
 						iName = rs.getString(3);
 						iNum = rs.getString(4);
 						activity= rs.getString(5);
 						System.out.printf("          %-25s %-20s %-20s %-20s %-20s\n", pName, pSSN, iName, iNum, activity);
 					}while(rs.next());
 					for(int i = 0; i < 125; i++) {
 						System.out.printf("*");	
 					}
 					System.out.printf("\n");
 				}
 			}
 			else
 				;//Must be time to quit
		
		return 0;
	}
	
	void PrintItm(String msg, int p) {
		String fmt = "%" + "p" + "s";
		String str = String.format("%"+ p +"s"," ") + msg;
		System.out.println(str);

		return;
	}
     
}





