package bookstore;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class BookCart extends AdminCart implements DiscountProvider, CustomerRegistration {

    int i, j;
    Scanner s = new Scanner(System.in);
    Scanner p = new Scanner(System.in);

    public BookCart(int bookID, String bookName, double price, String authorName, int noOfCopies) {
        super(bookID, bookName, price, authorName, noOfCopies);
    }

    public static ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Customer> customerlist = new ArrayList<>();

    // for getting customer cart books details 
    public void getCartItems() {
        if (books.size() == 0) {
            JOptionPane.showMessageDialog(null, "Cart is empty");
        } else {
            String cartItems = "Cart's items:\n";
            for (int i = 0; i < books.size(); i++) {
                cartItems += books.get(i).viewUserCartDetails() + "\n\n";
            }
            JOptionPane.showMessageDialog(null, cartItems);
        }
    }

    // for customer registration 
    @Override
    public void registerCustomer(String name, int id, String phoneNumber, int pass) throws InvalidCustomerPhoneNumberException {
        if (phoneNumber.length() != 11) {
            throw new InvalidCustomerPhoneNumberException(phoneNumber);
        } else {
            Customer customer1 = new Customer(id, name, phoneNumber, pass);
            customerlist.add(customer1);
            for (Customer c : customerlist) {
                if (c.getCustomerID() == id) {
                    JOptionPane.showMessageDialog(null, c.toString());
                }
            }
        }
    }
  //This method is used to check whether the customer who wants to buy a book from this store is registered or not. 
    public int checkcustomerRegistratedOrNot(String name, int id) {
        int a = 1, b = 2, c = 3;
        for (int i = 0; i < customerlist.size(); i++) {
            if (customerlist.get(i).getCustomerID() == id && customerlist.get(i).getCustomerName().equalsIgnoreCase(name)) {
                String pass = JOptionPane.showInputDialog("Enter password:");
                if (Integer.parseInt(pass) == customerlist.get(i).getPass()) {
                    return a;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid password");
                    return b;
                }
            }
        }
        return c;
    }

    @Override
    // to apply discount
    public void applyDiscount() {
        double price = getTotalPrice();
        JOptionPane.showMessageDialog(null, "Your total price: " + price);
        double discountPrice;
        if (price < 1500) {
            JOptionPane.showMessageDialog(null, "Your total price range is less than $1500. So you got 5% discount.");
            discountPrice = price - ((price * 5) / 100);
        } else if (price >= 1500 && price <= 2000) {
            JOptionPane.showMessageDialog(null, "Your total price range is ($1500-$2000). So you got 10% discount.");
            discountPrice = price - ((price * 10) / 100);
        } else if (price > 2000 && price <= 3000) {
            JOptionPane.showMessageDialog(null, "Your total price range is ($1500-$2000). So you got 10% discount.");
            discountPrice = price - ((price * 15) / 100);
        } else {
            JOptionPane.showMessageDialog(null, "Your total price range is more than $3000. So you got 20% discount.");
            discountPrice = price - ((price * 20) / 100);
        }
        JOptionPane.showMessageDialog(null, "Discount Price: " + discountPrice);
    }

    // for adding books to customer cart.	  
    public void addItem(String bookName, String authorName, int quantity) {
        int c = 0,noOfCopies=0;
        for (int i=0;i<stock.size();i++)
        {
        	noOfCopies = stock.get(i).getNoOfCopies(); 
        }
        for (Book b : books) {
        	
            if (b.getBookName().equalsIgnoreCase(bookName) && b.getAuthorName().equalsIgnoreCase(authorName)) {
            	
            	if(noOfCopies<quantity)
            	{  
            		c=1;
            		JOptionPane.showMessageDialog(null, "Can't added to to the cart copies as Only copies: "+noOfCopies+" of book name: "+bookName+" ,author name: "+authorName+"is available in the stock");
            		
            	}
            	else
            	{
                  int copies = b.getCopies();
                  copies = copies + quantity;
                  b.setCopies(copies);
                  c = 1;
            	}
            }
        }
        if (c == 0) {
            int p = 0;
            for (Book b1 : AdminCart.stock) {
                if (b1.getBookName().equalsIgnoreCase(bookName) && b1.getAuthorName().equalsIgnoreCase(authorName)) {
                	
                	if(noOfCopies<quantity)
                	{   p=1;
                		JOptionPane.showMessageDialog(null, " Can't added to the cart as Only copies: "+noOfCopies+" of book name: "+bookName+" ,author name: "+authorName+" is available in the stock");
                	}
                	else
                	{
                    b1.setCopies(quantity);
                    int copy = b1.getCopies();
                    Book b2 = new Book(bookName, authorName, b1.getPrice(), copy);
                    books.add(b2);
                    int updatedCopies = b1.getNoOfCopies() - quantity;
                    b1.setNoOfCopies(updatedCopies);
                    c = 1;
                    p = 1;
                    JOptionPane.showMessageDialog(null, "Successfully added book to the cart:\n" + b1.viewUserCartDetails());
                	}
                }
            }
            if (p == 0) {
                JOptionPane.showMessageDialog(null, "Book name: " + bookName + ", Author name: " + authorName + " is unavailable in the stock");
            }
        }
    }

    // to remove one book from customer cart.
    public void removeOneItem(Book book) {
        int c = 0, copies = 0;
        for (i = 0; i < books.size(); i++) {
            if (books.get(i).getBookName().equalsIgnoreCase(book.getBookName()) && books.get(i).getAuthorName().equalsIgnoreCase(book.getAuthorName())) {
                copies = books.get(i).getCopies();
                books.remove(books.get(i));
                c = 1;
            }
        }
        if (c == 0) {
            JOptionPane.showMessageDialog(null, "Book name: " + book.getBookName() + ", Author name: " + book.getAuthorName() + " is unavailable in the stock");
        }
        for (Book books : AdminCart.stock) {
            if (books.getBookName().equalsIgnoreCase(book.getBookName()) && books.getAuthorName().equalsIgnoreCase(book.getAuthorName())) {
                int s = books.getNoOfCopies();
                s = s + copies;
                books.setNoOfCopies(s);
            }
        }
    }

    // to remove some copies of a specific book from customer cart.
    public void removeSomecopiesOfBook(Book book, int quantity) {
        int c = 0;
        for (i = 0; i < books.size(); i++) {
            if (books.get(i).getBookName().equalsIgnoreCase(book.getBookName()) && books.get(i).getAuthorName().equalsIgnoreCase(book.getAuthorName())) {
            	
                int p = books.get(i).getCopies();
                if(p<quantity) {
                	JOptionPane.showMessageDialog(null, " Can't remove "+quantity+" as Only copies: "+p+" of book name: "+book.getBookName()+" ,author name: "+book.getAuthorName()+" is available in the customer cart");
                	c=1;
                }
                else
                {
                p = p - quantity;
                books.get(i).setCopies(p);
                if (books.get(i).getCopies() == 0) {
                    books.remove(books.get(i));
                    JOptionPane.showMessageDialog(null, "Successfully remove book name: " + book.getBookName());
                  }
                }
                JOptionPane.showMessageDialog(null, "Update info of customer cart:\n");
                getCartItems();
                c = 1;
            }
        }
        if (c == 0) {
            JOptionPane.showMessageDialog(null, "Book name: " + book.getBookName() + ", Author name: " + book.getAuthorName() + " is unavailable in the stock");
        }
        for (Book books : AdminCart.stock) {
            if (books.getBookName().equalsIgnoreCase(book.getBookName()) && books.getAuthorName().equalsIgnoreCase(book.getAuthorName())) {
                int s = books.getNoOfCopies();
                s = s + quantity;
                books.setNoOfCopies(s);
            }
        }
    }

    // to clear the whole customer cart.
    public void clearCart() {
        books.clear();
    }

    // to get total price.
    public double getTotalPrice() {
        double totalprice = 0.0;
        for (i = 0; i < books.size(); i++) {
            totalprice = totalprice + ((books.get(i).getCopies()) * (books.get(i).getPrice()));
        }
        return totalprice;
    }
}