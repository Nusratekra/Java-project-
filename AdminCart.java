package bookstore;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class AdminCart extends Book {
    private static final String password= "uap" ;
    int c=0;
    Scanner scan =new Scanner(System.in);
    public static ArrayList<Book> stock = new ArrayList<Book>();

  //constructor for AdminCart
    public AdminCart(int bookID, String bookName, double price, String authorName, int noOfCopies) {
        super(bookID, bookName, price, authorName, noOfCopies);
    }

    //canAccessOrNot
    public boolean can_access(String pass)throws InvalidAdminPasswordException
    {
        if(password.equalsIgnoreCase(pass))
        {
            return true;
        }
        else
        {
            throw new InvalidAdminPasswordException(password);
        }
    }
  //This add method is used by admin to add books to the stock.
    void addBook(int bookID, String bookName, double price, String authorName, int noOfCopies)
    { int c=0;
        for(int i=0;i<stock.size();i++)
        {
            if(stock.get(i).getBookName().equalsIgnoreCase(bookName) && stock.get(i).getAuthorName().equalsIgnoreCase(authorName))
            {
                int s=stock.get(i).getNoOfCopies();
                s=s+noOfCopies;
                stock.get(i).setNoOfCopies(s);
                c=1;
            }
        }
        if(c==0)
        {
            Book b1 = new Book(bookID,bookName,price,authorName,noOfCopies);
            stock.add(b1);
        }
        getStockItems();
    }
    //This remove method is used by admin to remove a specific number of copies of a single book from the stock.
    void removeSpecificAmountOfBook(String bookName,String authorName,int quantity)
    {
        int c=0;
        for(int i=0;i<stock.size();i++)
        {
            if(stock.get(i).getBookName().equalsIgnoreCase(bookName) && stock.get(i).getAuthorName().equalsIgnoreCase(authorName))
            { 
                int s=stock.get(i).getNoOfCopies();
                if(s<quantity)
                {
                	JOptionPane.showMessageDialog(null, " Can't remove "+quantity+" as Only copies: "+s+" of book name: "+bookName+" ,author name: "+authorName+" is available in the stock");
                	c=1;
                }
                else	
                {
                s=s-quantity;
                stock.get(i).setNoOfCopies(s);
                JOptionPane.showMessageDialog(null, "\n"+"after removing specific amount of copies,updated info:"+"\n"
                        +stock.get(i).toString());
                c=1;
                }
                
            }
        }
        if(c==0)
        {
            JOptionPane.showMessageDialog(null, "book name: "+bookName+" author name: "+authorName+" is unavailable in the stock");
        }
    }
  //This remove method is used by admin to remove one book from the stock.
    void removeOneBook( String bookName, String authorName)
    {
        int c=0;
        for( int i=0;i<stock.size();i++)
        {
            if(stock.get(i).getBookName().equalsIgnoreCase(bookName)&& stock.get(i).getAuthorName().equalsIgnoreCase(authorName))
            {
                stock.remove(stock.get(i));
                c=1;
            }
        }
        if(c==0)
        {
            JOptionPane.showMessageDialog(null, "book name: "+bookName+"author name: "+authorName+"is unavailable in the stock");
        }
        getStockItems();
    }
    //this method is used by admin to remove all book from stock.
    public void clearCart()
    {
        stock.clear();
    }
  //this method is used by admin to update price of books.
    void updateInfoOfBook(String bookName,String authorName)
    { int c=0;
        for(int i=0;i<stock.size();i++)
        {
            if(stock.get(i).getBookName().equalsIgnoreCase(bookName) && stock.get(i).getAuthorName().equalsIgnoreCase(authorName))
            {
                double d=stock.get(i).getPrice();
                int in = Integer.parseInt(JOptionPane.showInputDialog("1.Want to increase price.\n2.Want to Decrease price.\nenter your choice:"));
                double pr = Double.parseDouble(JOptionPane.showInputDialog("enter price:"));
                if(in==1)
                {
                    d=d+pr;
                    stock.get(i).setPrice(d);
                    JOptionPane.showMessageDialog(null, "after update:\n"+stock.get(i).toString());
                    c=1;
                }
                else if(in==2)
                {
                    d=d-pr;
                    stock.get(i).setPrice(d);
                    JOptionPane.showMessageDialog(null, "after update:\n"+stock.get(i).toString());
                    c=1;
                }
            }
        }
        if(c==0)
        {
            JOptionPane.showMessageDialog(null, "Book name: "+bookName+" Author name: "+authorName+" is unavailable in the stock");
        }
    }
    
    @Override
    public String toString() {
        return ("getBookID()=" + getBookID() +"\n"+ "getBookName()=" + getBookName() +"\n"+ "getPrice()=" + getPrice()
                + "\n"+"getAuthorName()=" + getAuthorName() +"\n"+"getNoOfCopies()=" + getNoOfCopies());
    }
  //this method is used by admin to see all the books in the stock.
    public void getStockItems()
    {
        if(stock.size()== 0)
        {
            JOptionPane.showMessageDialog(null, "Stock is empty");
        }
        else
        {
            String stockItems = "Stock's items:\n";
            for(int i=0;i<stock.size();i++)
            {
                stockItems += stock.get(i).toString() + "\n\n";
            }
            JOptionPane.showMessageDialog(null, stockItems);
        }
    }
}
