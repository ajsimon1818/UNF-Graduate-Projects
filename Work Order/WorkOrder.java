/**
 * 
 * - Allows for the creation of WorkOrder objects
 * - Can format WorkOrder info for display
 * - Can format WorkOrder info for csv storage
 *
 */
public class WorkOrder implements DataHandler{

	private Employee employee;
	private Ticket ticket;
	private String createdAt = "";
	
	public WorkOrder(Employee employee, Ticket ticket, String createdAt)
	{
		this.setEmployee(employee);
		this.setTicket(ticket);
		this.setCreatedAt(createdAt);
	}
	
	@Override
	/**
	 * - Formats WorkOrder info for display
	 */
	public String getInfo()
	{
		String info = "";
		info = employee.getInfo() + ticket.getInfo() + "\nWork Order Info:\nCreated At: " + this.getCreatedAt();
		return info;
	}
	
	@Override
	/**
	 * - Formats WorkOrder info for csv storage
	 */
	public String getFileData()
	{
		String data = "";
		data = employee.getFileData() + ticket.getFileData() + "," + this.getCreatedAt();
		return data;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
