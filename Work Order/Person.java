/**
 * 
 * - Allows for the creation of Person objects
 * - Primary serves as a superclass to reduce redundancy.
 *
 */
public class Person {

	private String firstName = "";
	private String lastName = "";
	private String address = "";
	private String phoneNumber = "";
	private String email = "";
	
	public Person(String firstName, String lastName, String address, String phoneNumber, String email)
	{
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setPhoneNumber(phoneNumber);
		this.setEmail(email);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
