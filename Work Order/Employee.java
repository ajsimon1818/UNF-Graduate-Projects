/**
 * 
 * - Allows for the creation of Employee objects
 * - Can format Employee info for display
 * - Can format Employee info for csv storage
 *
 */
public class Employee extends Person implements DataHandler{

	private String employeeId = "";
	private String clockedIn = "";
	private String hiredDate = "";
	
	public Employee(String firstName, String lastName, String address, String phoneNumber, String email, String employeeId, String clockedIn, String hiredDate)
	{
		super(firstName, lastName, address, phoneNumber, email);
		
		this.setEmployeeId(employeeId);
		this.setClockedIn(clockedIn);
		this.setHiredDate(hiredDate);
	}
	
	@Override
	/**
	 * - Formats Employee info for display
	 */
	public String getInfo()
	{
		String info = "";
		info = "\nEmployee Info:\nEmployee Name: " + this.getFirstName() + " " + this.getLastName() + "\n" + "Employee ID: " + this.getEmployeeId() + "\n" + "Time Clocked In: " + this.getClockedIn();
		return info;
	}
	
	@Override
	/**
	 * - Formats Employee info for csv storage
	 */
	public String getFileData()
	{
		String data = "";
		data = this.getEmployeeId() + "," + super.getFirstName() + "," + super.getLastName() + ","  + this.getClockedIn() + ",";
		return data;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getClockedIn() {
		return clockedIn;
	}

	public void setClockedIn(String clockedIn) {
		this.clockedIn = clockedIn;
	}

	public String getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}
	
}
