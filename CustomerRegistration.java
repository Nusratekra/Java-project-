package bookstore;

public interface CustomerRegistration {
	public abstract void registerCustomer(String name ,int id,String phoneNumber,int pass)throws InvalidCustomerPhoneNumberException;
}
