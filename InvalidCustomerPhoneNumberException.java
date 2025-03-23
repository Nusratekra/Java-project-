package bookstore;

public class InvalidCustomerPhoneNumberException extends Exception {

	public InvalidCustomerPhoneNumberException(String CustomerPhoneNumber) {
        super(CustomerPhoneNumber + " is invalid phone number. Please provide phone number with 11 digit.");
    }
}
