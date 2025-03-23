package bookstore;
import javax.swing.JOptionPane;
public class PoS {
	// user cart management system
	public static void customerSystem() {
		int n;
		BookCart cart = new BookCart(0, null, 0.0, null, 0);
		AdminCart admin = new AdminCart(0, null, 0.0, null, 0);
		while (true) {
			String input4 = JOptionPane.showInputDialog(null, "1. Add Book to Cart.\n2. Remove Book from Cart.\n3. Get Total price.\n4. Apply Discount.\n5. View Cart Details.\n6. Exit.","Menu For User", JOptionPane.PLAIN_MESSAGE);

			try {
				n = Integer.parseInt(input4);

				if (n == 1) {
					JOptionPane.showMessageDialog(null, "///Add Book///");
					JOptionPane.showMessageDialog(null, "/// Available Books in the store /// ");
					admin.getStockItems();
					String name = JOptionPane.showInputDialog("Enter book name:");
					String authorName = JOptionPane.showInputDialog("Enter author name:");

					try {
						int Copies = Integer.parseInt(JOptionPane.showInputDialog("Enter number of copies:"));
						cart.addItem(name, authorName, Copies);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a valid number.");
					}
				} else if (n == 2) {
					String input2 = JOptionPane.showInputDialog(null, "///Removing book from Cart///\n"
							+ "1. Remove one book from the cart.\n"
							+ "2. Remove some copies of the specific book from the cart.\n"
							+ "3. Remove all books from the cart.","Menu Of Removing Book From Cart", JOptionPane.PLAIN_MESSAGE);

					int choice = Integer.parseInt(input2);

					if (choice == 1) {
						String bookName = JOptionPane.showInputDialog("Enter book name:");
						String authorName = JOptionPane.showInputDialog("Enter author name:");
						Book b = new Book(bookName, authorName);
						cart.removeOneItem(b);
						cart.getCartItems();
					} else if (choice == 2) {
						String bookName = JOptionPane.showInputDialog("Enter book name:");
						String authorName = JOptionPane.showInputDialog("Enter author name:");
						int q = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity of that product that's you want to remove:"));
						Book b = new Book(bookName, authorName);
						cart.removeSomecopiesOfBook(b, q);
					} else if (choice == 3) {
						cart.clearCart();
						JOptionPane.showMessageDialog(null, "All books remove successfully");
					}
				} else if (n == 3) {
					JOptionPane.showMessageDialog(null, "///Get total price///");
					JOptionPane.showMessageDialog(null, "Total Price : " + cart.getTotalPrice());
				} else if (n == 4) {
					JOptionPane.showMessageDialog(null, "///Applying Discount///");
					cart.applyDiscount();
				} else if (n == 5) {
					cart.getCartItems();
				} else if (n == 6) {
					JOptionPane.showMessageDialog(null, "Exiting...");
					break;
				} else {
					JOptionPane.showMessageDialog(null, "\nInvalid choice. Please enter a valid option.");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid choice.");
			}
		}
	}

	public static void main(String[] args) {
		BookCart cart = new BookCart(0, null, 0.0, null, 0);
		AdminCart admin = new AdminCart(0, null, 0.0, null, 0);

		while (true) {
			String input = JOptionPane.showInputDialog(null, "1. Owner account access\n"
					+ "2. User account access\n"
					+ "3. Exit"+"\nEnter Your choice:","Menu Of BookStore", JOptionPane.PLAIN_MESSAGE);
            try {
			int choice= Integer.parseInt(input);
            
			if (choice == 1) {
				try {
					String password = JOptionPane.showInputDialog("Enter owner password:");
					if (admin.can_access(password)) {
						while (true) {

							String input1 = JOptionPane.showInputDialog(null, 
									"1. Add books to store\n"
									+ "2. Remove books from store\n"
									+ "3. Update book's information\n"
									+ "4. Display the books\n"
									+ "5. Exit","Menu For Admin",JOptionPane.PLAIN_MESSAGE);
                            try {
							int choice1 = Integer.parseInt(input1);

							if (choice1 == 1) {
								JOptionPane.showMessageDialog(null, "///Add Book to the store///");
								int ID = Integer.parseInt(JOptionPane.showInputDialog("Enter book ID:"));
								String name = JOptionPane.showInputDialog("Enter book name:");
								String authorName = JOptionPane.showInputDialog("Enter author name:");
								double price = Double.parseDouble(JOptionPane.showInputDialog("Enter book price:"));
								int noOfCopies = Integer.parseInt(JOptionPane.showInputDialog("Enter number of copies:"));
								admin.addBook(ID, name, price, authorName, noOfCopies);
							} else if (choice1 == 2) {
								String input3 = JOptionPane.showInputDialog(null, "///Removing book from store///\n"
										+ "1. Remove one book from the store.\n"
										+ "2. Remove some copies of the specific book from the store.\n"
										+ "3. Remove all books from the store.","Menu For Femoving Book From Store",JOptionPane.PLAIN_MESSAGE);
                                try {
								int choice2 = Integer.parseInt(input3);

								if (choice2 == 1) {
									String bookName = JOptionPane.showInputDialog("Enter book name:");
									String authorName = JOptionPane.showInputDialog("Enter author name:");
									admin.removeOneBook(bookName, authorName);
								} else if (choice2 == 2) {
									String bookName = JOptionPane.showInputDialog("Enter book name:");
									String authorName = JOptionPane.showInputDialog("Enter author name:");
									int k = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity of that product that's you want to remove:"));
									admin.removeSpecificAmountOfBook(bookName, authorName, k);
								} else if (choice2 == 3) {
									admin.clearCart();
									JOptionPane.showMessageDialog(null, "All books remove successfully");
								}
                                }catch (NumberFormatException e) {
            						JOptionPane.showMessageDialog(null, "Please enter a valid choice.");
            					}
							} else if (choice1 == 3) {
								String bookName = JOptionPane.showInputDialog("Enter book name:");
								String authorName = JOptionPane.showInputDialog("Enter author name:");
								admin.updateInfoOfBook(bookName, authorName);
							} else if (choice1 == 4) {
								admin.getStockItems();
							} else if (choice1 == 5) {
								JOptionPane.showMessageDialog(null, "Exiting...");
								cart.clearCart();
								break;
							} else {
								JOptionPane.showMessageDialog(null, "Invalid choice...");
							}
                            }catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Please enter a valid choice.");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid access.");
					}
					} catch (InvalidAdminPasswordException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
				}
			} else if (choice == 2) {
				JOptionPane.showMessageDialog(null, "Check Customer (New or Existing)");
				int Id = Integer.parseInt(JOptionPane.showInputDialog("Enter customer id:"));
				String name = JOptionPane.showInputDialog("Enter customer name:");

				int b = cart.checkcustomerRegistratedOrNot(name, Id);

				if (b == 1) {
					JOptionPane.showMessageDialog(null, "Customer already existed");
					customerSystem();
				} else if (b == 3) {
					try {
						int Id1 = 0, pass = 0;
						String name1 = null, phone1 = null;

						JOptionPane.showMessageDialog(null, "The customer isn't registered. Register now");

						Id1 = Integer.parseInt(JOptionPane.showInputDialog("Enter customer id:"));
						name1 = JOptionPane.showInputDialog("Enter customer name:");
						//phone1 = JOptionPane.showInputDialog("Enter customer phone number:");
						pass = Integer.parseInt(JOptionPane.showInputDialog("Enter password:"));
						phone1 = JOptionPane.showInputDialog("Enter customer phone number:");
						cart.registerCustomer(name1, Id1, phone1, pass);
						customerSystem();

					} catch (InvalidCustomerPhoneNumberException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			} else if (choice == 3) {
				JOptionPane.showMessageDialog(null, "Exiting...");
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Invalid choice.");
			}
            }catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid choice.");
			}
		}
	}
}