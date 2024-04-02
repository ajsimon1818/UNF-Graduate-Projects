/**
 * 
 * - Allows for the creation of Customer objects
 * - Can format Customer info for display
 * - Can format Customer info for csv storage
 *
 */
public class Customer extends Person implements DataHandler{

	private String customerId = "";
	private String accountNumber = "";
	
	public Customer(String firstName, String lastName, String address, String phoneNumber, String email, String customerId, String accountNumber)
	{
		super(firstName, lastName, address, phoneNumber, email);
		
		this.setCustomerId(customerId);
		this.setAccountNumber(accountNumber);
	}
	
	@Override
	/**
	 * - Formats Customer info for display
	 */
	public String getInfo()
	{
		String info = "";
		info = "\nCustomer Info:\nCustomer Name: " + this.getFirstName() + " " + this.getLastName() + "\n" + "Customer ID: " + this.getCustomerId() + "\n" + "Account Number: " + this.getAccountNumber();
		return info;
	}
	
	@Override
	/**
	 * - Formats Customer info for csv storage
	 */
	public String getFileData()
	{
		String data = "";
		data = this.getCustomerId() + "," + super.getFirstName() + "," + super.getLastName();
		return data;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
