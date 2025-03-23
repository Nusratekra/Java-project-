package bookstore;

public class Book {
	private int bookID;
	private String bookName;
	private double price;
	private String authorName;
	private int noOfCopies;
	private int copies;
	
	//using this constructor for owner to add book in the stock
	public Book(int bookID, String bookName, double price, String authorName,int noOfCopies) {
		this.bookID = bookID;
		this.bookName = bookName;
		this.price = price;
		this.authorName = authorName;
		this.noOfCopies =noOfCopies;
	}
	// emnei use korsi.
	public Book(String bookName, String authorName) {
		this.bookName =bookName;
		this.authorName=authorName;
	}
	//using this constructor for user to add book in the customer cart
	public Book(String bookName, String authorName,double price, int copies) {
		this.bookName =bookName;
		this.authorName=authorName;
		this.price = price;
		this.copies =copies;
	}
	
	// start of all getter and setter method for all attributes.
	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	// end of all getter and setter method for all attributes.
	
	//for user cart detail;
	public String viewUserCartDetails()
	{
		return( "bookName=" + bookName +"\n"+ "price of one book =" + price +"\n"+ "authorName=" + authorName+"\n"+"No of copies= "+copies);
	}
	
	@Override
	public String toString() {
		return ("bookID=" + bookID +"\n"+ "bookName=" + bookName +"\n"+ "price=" + price +"\n"+ "authorName=" + authorName
				+ "\n"+"noOfCopies=" + noOfCopies);
	}
}
