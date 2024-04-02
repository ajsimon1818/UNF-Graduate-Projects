import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 
 * - Allows for file reading and writing
 * - Specifically:
 * 		- Reading from employee/ticket csv files
 * 		- Writing to a new work order csv file
 * 		- Writing to a log file
 * - Allows retrieval of current date and time
 *
 */
public class FileHandler {

	private Scanner lineRead = new Scanner(System.in);
	private int offset = 0;
	
	/**
	 * - Writes the csv file formatted work order info to a new csv file
	 * @param workOrderFile - The csv file the info is being written into
	 * @param workOrderList - The array list of work orders created in main
	 */
	public void writeData(String workOrderFile)
	{
		try {
			FileWriter fileWrite = new FileWriter(workOrderFile);
			PrintWriter out = new PrintWriter(fileWrite);
			
			out.println("employee_id,employee_first_name,employee_last_name,clocked_in,customer_id,customer_first_name,customer_last_name,ticket_id,ticket_createdAt,workorder_createdAt");
			for(WorkOrder order : MainGenerator.workOrderList)
			{
				out.println(order.getFileData());//print to new file
				logger(order.getInfo());//log each work order in the log file
			}
				
			out.close();
			fileWrite.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(" Work Order File Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * - Read in data from the employee csv file and add each employee to an array list
	 * @param employeeFile - The csv file the employee info is being read from
	 * @param employeeList - The array list the employee info is being stored in
	 */
	public void readEmployeeData(String employeeFile)
	{
		File inputFile = new File(employeeFile);
		
		
			try {
				FileReader fileRead = new FileReader(inputFile);
				lineRead = new Scanner(inputFile);
			 
				while(lineRead.hasNextLine())
				{
					String line = lineRead.nextLine();
					String[]lineSplit = line.split(",");

					if(offset > 0)
					{	
					Employee emp = new Employee(lineSplit[1], lineSplit[2], lineSplit[4], lineSplit[5], lineSplit[3], lineSplit[0], lineSplit[6], lineSplit[7]);
					MainGenerator.employeeList.add(emp);
					}
					offset++;
				}
				offset = 0;
				lineRead.close();
				fileRead.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("Employee File Not Found");
				MainGenerator.employeeList.clear();
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	/**
	 * - Read in data from the ticket csv file and add each ticket to an array list
	 * @param ticketFile - The csv file the ticket info is being read from
	 * @param ticketList - The array list the ticket info is being stored in
	 */
	public void readTicketData (String ticketFile)
	{
		File inputFile = new File(ticketFile);
		
		
			try {
				FileReader fileRead = new FileReader(inputFile);
				Scanner lineRead = new Scanner(inputFile);
			 
				while(lineRead.hasNextLine())
				{
					String line = lineRead.nextLine();
					String[]lineSplit = line.split(",");

					if(offset >0)
					{
						
					Customer cust = new Customer(lineSplit[1], lineSplit[2], lineSplit[4], lineSplit[5], lineSplit[3], lineSplit[0], lineSplit[6]);
					Ticket tick = new Ticket(cust, lineSplit[8], lineSplit[7]);
					
					MainGenerator.ticketList.add(tick);
					}
					offset++;
				}
				offset = 0;
				lineRead.close();
				fileRead.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("Ticket File Not Found");
				MainGenerator.ticketList.clear();
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * - Prints the current date/time into the log file, followed by what event occurred.
	 * @param log - The event that the user wishes to log
	 */
	public void logger(String log)
	{
		String logFile = "log.txt";
		try {
			FileWriter fileWrite = new FileWriter(logFile, true);
			PrintWriter out = new PrintWriter(fileWrite);
			
			out.println(dateTime() + ": " + log + "\n");
			out.println();
			
			out.close();
			fileWrite.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(" Log File Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * - Returns the current date and time
	 * @return dateTime - The current date and time
	 */
	public static String dateTime()
	{
		String dateTime = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm");
		
		Date date = new Date();
		
		dateTime = dateFormat.format(date);
		
		return dateTime;
	}

	public Scanner getScan() {
		return lineRead;
	}

	public void setScan(Scanner scan) {
		this.lineRead = scan;
	}
}
