/**
 * 
 * - Allows for the creation of Ticket objects
 * - Can format Ticket info for display
 * - Can format Ticket info for csv storage
 *
 */
public class Ticket implements DataHandler{

	private Customer customer;
	private String createdAt = "";
	private String ticketId = "";
	
	public Ticket(Customer customer, String createdAt, String ticketId)
	{
		this.setCustomer(customer);
		this.setCreatedAt(createdAt);
		this.setTicketId(ticketId);
	}
	
	@Override
	/**
	 * - Formats Ticket info for display
	 */
	public String getInfo()
	{
		String info = "";
		info = customer.getInfo() + "\nTicket Info:\nTicket ID: " + this.getTicketId() + "\n" + "Created At: " + this.getCreatedAt();
		return info;
	}
	
	@Override
	/**
	 * - Formats Ticket info for csv storage
	 */
	public String getFileData()
	{
		String data = "";
		data = customer.getFileData() + "," + this.getTicketId() + "," + this.getCreatedAt();
		return data;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
}
