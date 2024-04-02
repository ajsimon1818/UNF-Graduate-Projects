/* 
 * Author:    Andrew Simon (n00695969)
 * Course:    COP3503 
 * Project #: 3
 * Title  :  Work Order Generator
 * Due Date:  7/14/2022 
 * 
 * This program reads in a csv file of employee data and a csv file of ticket data. 
 * It then takes this data, creates employee, customer, and ticket objects and stores
 * the data in array lists. Then it takes the data from our employee and ticket array 
 * lists and delegates tickets as even as possible amongst the employees, creating a work 
 * order for each ticket. Finally, it creates a new csv file to store all the created work orders.
 * (All major actions and every work order created is logged in a new text file: log.txt)
 * 
 * Notes:
 * - Log output isn't 100% to the example, but I just added in some new lines for presentation. 
 * Same data is logged.
 * 
 * -My logic for ticket distribution might be different than what was mentioned in class. I didn't 
 * assign 9-10 tickets at a time to each employee. I assigned one ticket to every employee and went 
 * back to the start of the employee list when I got to the end. I continued that loop until I ran 
 * out of tickets. This logic seemed to get the most even split amongst all employees.
 */ 
import java.util.ArrayList;

/**
 * 
 * -Conveys program steps work order generation to the user console as well as to the log file.
 * -Reads in data from the employee file
 * -Reads in data from the ticket file
 * -Creates work orders for every ticket
 * -Creates a new csv file detailing new work orders.
 *
 */
public class MainGenerator {

	public static String employeeFile = "employee_data.csv";//input employees
	public static String ticketFile = "ticket_data.csv";//input tickets
	public static String workOrderFile = "workorder_data.csv"; //output file
	
	public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	public static ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
	public static ArrayList<WorkOrder> workOrderList = new ArrayList<WorkOrder>();
	
	public static void main(String[] args) {
		
		FileHandler fileHandler = new FileHandler();
		
		System.out.println("Project 3 Work Order Generator");
		System.out.println();
		System.out.println("Loading Employee Data");
		fileHandler.logger("Loading Employee Data");
		
		fileHandler.readEmployeeData(employeeFile);
		
		System.out.println("Loading Ticket Data");
		fileHandler.logger("Loading Ticket Data");
		
		fileHandler.readTicketData(ticketFile);
		
		System.out.println("Creating Work Orders");
		fileHandler.logger("Creating Work Orders");
		
		createWorkOrders();
		
		System.out.println("Writing Work Order Data to File");
		fileHandler.logger("Writing Work Order Data to File");
		
		fileHandler.writeData(workOrderFile);
		
		System.out.println("Work Orders Created. Program Exiting");
		fileHandler.logger("Work Orders Created. Program Exiting");
	}
	
	/**
	 * -Evenly distributes each ticket in the ticket file across every employee from the employee file.
	 * -New work orders are stored in the workOrderList array list.
	 */
	public static void createWorkOrders()
	{
		int tickNum = 0;//counts to total of tickets
		
		while( tickNum < ticketList.size())
		{
			for(int i = 0; i < employeeList.size() && tickNum < ticketList.size(); i++)//the && clause makes sure I can stop and not have to do another full loop of 100 employees.
			{
				WorkOrder order = new WorkOrder(employeeList.get(i), ticketList.get(tickNum), FileHandler.dateTime());
				workOrderList.add(order);
				tickNum++;
			}
		}
	}
}
