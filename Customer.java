package bookstore;

public class Customer {
	private  int customerID;
	private  String customerName;
	private int pass;
	private  String customerPhoneNumber;
	
    //using this constructor for creating new customer account
	public Customer(int customerID, String customerName, String customerPhoneNumber,int pass) {
		
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.pass =pass;
	
	}
	
	// start of all getter and setter method for all attributes.
	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getCustomerID(){
	return customerID;
	}
	
	public void setCustomerID(int customerID) {
	this.customerID = customerID;
	}
	
	public String getCustomerName() {
	return customerName;
	}
	
	public void setCustomerName(String customerName) {
	this.customerName = customerName;
	}
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}
	// end of all getter and setter method for all attributes.
	
	
	@Override
	public String toString() {
		return ("customerID=" + customerID +"\n"+ "customerName=" + customerName +"\n"+"customerPhoneNumber="
				+ customerPhoneNumber);
	}
	
}
