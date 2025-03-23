package bookstore;

public class InvalidAdminPasswordException  extends Exception{
	 
	public InvalidAdminPasswordException(String password) {
		super("Wrong Admin Password! \nPlease, Provide the correct password.");
	}
}
