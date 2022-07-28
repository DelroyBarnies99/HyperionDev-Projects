/** 
 * This program creates, stores and manages books records from a MYSQL database called ebookstore through JDBC. 
 * <p>
 * @auther  Delroy Barnies
 * @version 1.0 2022/05/27
*/
package myBookstore;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  The main class
 *     
 *  @see   MyBookstore
 *  @since version 1.0
 */
public class MyBookstore {
	
	/**
	 * Main method mainly deals with the user options and interface.
	 * 
	 * @param args
	 * @since version 1.0
	 */
	public static void main(String[] args) {
		
		System.out.println("Hello, Login username is 'otheruser' and password is 'swordfish'");
		
		// adds login functionality
		Login();
		
		int option = 1;
		
		// while loop iterates through options until user exits the loop 
		while (option != 0){
			
			Scanner s = new Scanner(System.in);
			System.out.print("\nPlease select a number below..."
					       + "\n1. Enter book"
					       + "\n2. Update book"
					       + "\n3. Delete book"
					       + "\n4. Search books"
					       + "\n0. Exit"
					       + "\nEnter your number here:");
			
			// handles in case user enters any character that isn't an integer
			while (!(s.hasNextInt())) {
				System.out.print("\nPlease enter a valid number!:");
				     s = new Scanner(System.in);
			}
			option = s.nextInt();
			
			// handles in case user enters an invalid integer
			while (!(option == 0 || option == 1 || option == 2 || option == 3 || option == 4)) {
				System.out.print("\nPlease enter a valid number!:");
				s = new Scanner(System.in);
                option = s.nextInt();        		
			}
			
			if (option == 1) {
				
				EnterBook();
			}
			if (option == 2) {
				
				UpdateBook();
			}
			if (option == 3) {
				
				DeleteBook();
			}
			if (option == 4) {
				
				SearchBook();
			}
			// displays exit message 
			if (option == 0) {
				System.out.println("Thank You!!!");
			}
		}	
	}
	/**
	 * Method adds the functionality of logging in adding security to the database.
	 *  
	 * @since           version 1.0
	 */ 
	public static void Login() {
		
		Scanner n = new Scanner(System.in);
		System.out.print("Please enter your username:");
		String name = n.next();
		name = name.toLowerCase();
		
		// handles in case user enters invalid username.While loop iterates until user enters the correct username.
		while (!(name.equals("otheruser"))) {
			
			n = new Scanner(System.in);
			System.out.print("\nInvalid username!"
					       + "\nPlease enter a correct username:");
			name = n.next();
			name = name.toLowerCase();
			
		}
		
		Scanner p = new Scanner(System.in);
		System.out.print("Please enter your password:");
		String password = p.next();
		password = password.toLowerCase();
		
		// handles in case user enters invalid passoword.While loop iterates until user enters the correct password.
		while (!(password.equals("swordfish"))) {
			
			p = new Scanner(System.in);
			System.out.print("\nInvalid password!"
					       + "\nPlease enter a correct password:");
			password = p.next();
			password = password.toLowerCase();
		}
	}
	/**
	 * Method Allows user to add new book records to the MYSQL database 'ebookstore' through JDBC.
	 *  
	 * @since           version 1.0
	 */ 
	public static void EnterBook() {
		try {
			// Connect to the ebookstore database, via the jdbc:mysql:
	        // channel on localhost (this PC)
	        // Use username "otheruser", password "swordfish".
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebookstore?useSSL=false",
					"otheruser",
					"swordfish"
			        );
			
			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			ResultSet results;
			
			// input title for the books
			Scanner n = new Scanner(System.in);
			System.out.print("Please enter the name of the book:");
			String title = n.nextLine();
			
			// input author of the books
			Scanner a = new Scanner(System.in);
			System.out.print("Please enter the author of the book:");
			String author = a.nextLine();
			
			// input quantity of the books
			System.out.print("Please enter the quantity of books to be stored:");
			Scanner q = new Scanner(System.in);
			
			// handles incorrect string input for int
			while (!(q.hasNextInt())) {
				System.out.print("\nPlease refrain from using letters and/or symbols in your quantity:");
				q = new Scanner(System.in);
			}
			int qty = q.nextInt();
			
			int id = 0;
			
			// returns the max id of all the books 
			results = statement.executeQuery("SELECT MAX(id) FROM books");
			
			while (results.next()) {
				
				id = results.getInt("max(id)");
			}
			// adds one to the max id to get the id of the new book
			id += 1;
			
			// inserts new book record into database
			PreparedStatement input = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?)");
			
			input.setInt(1, id);
			input.setString(2, title);
			input.setString(3, author);
			input.setInt(4, qty);
			
			input.executeUpdate();
			
			System.out.println("The Book has been added successfully.");
			
			// Close up our connections
			results.close();
			statement.close();
			connection.close();
		}
		// catches errors
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Method Allows user to update existing book records on ebookstore through JDBC.
	 *  
	 * @since           version 1.0
	 */ 
	public static void UpdateBook() {
		try {
			// Connect to the ebookstore database, via the jdbc:mysql:
	        // channel on localhost (this PC)
	        // Use username "otheruser", password "swordfish".
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebookstore?useSSL=false",
					"otheruser",
					"swordfish"
			        );
			
			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			ResultSet results;
		
			// input id of the book
			System.out.print("\nPlease enter the id of books to be updated:");
			Scanner i = new Scanner(System.in);
			
			// handles incorrect string input for int
			while (!(i.hasNextInt())) {
				System.out.print("\nPlease refrain from using letters and/or symbols in your id:");
					i = new Scanner(System.in);
				}
			int id = i.nextInt();
			
			// selects all book records
			results = statement.executeQuery("SELECT * FROM books");
			
			// new ArrayList
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			// iterates through each book record and adds all the ids to the Arraylist 
			while (results.next()) {
				
				list.add(results.getInt("id"));
				
			}
			// handles incorrect id input
			while (!(list.contains(id))) {
			
				System.out.print("\nPlease enter a valid book id:");
				Scanner d = new Scanner(System.in);
				
				// handles incorrect string input for int
				while (!(d.hasNextInt())) {
					System.out.print("\nPlease refrain from using letters and/or symbols in your id:");
						d = new Scanner(System.in);
					}
				id = d.nextInt();
			}
			
			int option = 1;
			
			// while loop iterates through options until user exits the loop 
			while (option != 0){
				
				Scanner s = new Scanner(System.in);
				System.out.print("\nPlease select a number below"
						       + "\n1. Update Title"
						       + "\n2. Update Author"
						       + "\n3. Update Quantity"
						       + "\n0. Exit"
						       + "\nEnter your number here:");
				
				// handles incorrect character input
				while (!(s.hasNextInt())) {
					System.out.print("\nPlease enter a valid number!:");
					s = new Scanner(System.in);
				}
				option = s.nextInt();
				
				// handles incorrect int input
				while (!(option == 0 || option == 1 || option == 2 || option == 3)) {
					System.out.print("\nPlease enter a valid number!:");
					s = new Scanner(System.in);
	                option = s.nextInt();        		
				}
				// allows user to enter a new title for the book 
				if (option == 1) {
					
					// input new title
					Scanner n = new Scanner(System.in);
					System.out.print("\nPlease enter a new title for the book:");
					String title = n.nextLine();
					
					// command updates the books title on the database
					PreparedStatement input = connection.prepareStatement("UPDATE books SET title = ? WHERE id = ?");
					
					input.setString(1, title);
					input.setInt(2, id);
					
					// executes the command
					input.executeUpdate();
					
					System.out.println("The Title has been updated successfully!");
					
				}
				// allows user to enter a new author for the book 
				if (option == 2) {
					
					// input new author
					Scanner a = new Scanner(System.in);
					System.out.print("\nPlease enter a new author of the book:");
					String author = a.nextLine();
					
					// command updates the books author on the database
					PreparedStatement input = connection.prepareStatement("UPDATE books SET author = ? WHERE id = ?");
					
					input.setString(1, author);
					input.setInt(2, id);
					
					// executes the command
					input.executeUpdate();
					
					System.out.println("The Author has been updated successfully!");
				}
				// allows user to enter a new quantity for the book
			    if (option == 3) {
			    	
			    	System.out.print("\nPlease enter a new quantity:");
					Scanner q = new Scanner(System.in);
					
					// handles incorrect string input for int
					while (!(q.hasNextInt())) {
						System.out.print("\nPlease refrain from using letters and/or symbols in your quantity:");
							q = new Scanner(System.in);
						}
					int qty = q.nextInt();
					
					// command updates the books quantity on the database
					PreparedStatement input = connection.prepareStatement("UPDATE books SET qty = ? WHERE id = ?");
					
					input.setInt(1, qty);
					input.setInt(2, id);
					
					// executes the command
					input.executeUpdate();
					
					System.out.println("The Quantity has been updated successfully!");
			    	
			    }
			}
			
			// Close up our connections
			results.close();
			statement.close();
			connection.close();
		}
		// catches errors
		catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	/**
	 * Method Allows user to delete existing book records from ebookstore through JDBC.
	 *  
	 * @since           version 1.0
	 */ 
	public static void DeleteBook() {
		
		try {
			// Connect to the ebookstore database, via the jdbc:mysql:
	        // channel on localhost (this PC)
	        // Use username "otheruser", password "swordfish".
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebookstore?useSSL=false",
					"otheruser",
					"swordfish"
			        );
			
			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			ResultSet results;
		
			// input id of the book
			System.out.print("\nPlease enter the id of books to be updated:");
			Scanner i = new Scanner(System.in);
			
			// handles incorrect character input for int
			while (!(i.hasNextInt())) {
				System.out.print("\nPlease refrain from using letters and/or symbols in your id:");
					i = new Scanner(System.in);
				}
			int id = i.nextInt();
			
			// returns all books records
			results = statement.executeQuery("SELECT * FROM books");
			
			// new Array List
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			// iterates through each book record and adds all the ids to the Arraylist
			while (results.next()) {
				
				list.add(results.getInt("id"));
				
			}
			// handles incorrect id input
			while (!(list.contains(id))) {
			
				System.out.print("\nPlease enter a valid book id:");
				Scanner d = new Scanner(System.in);
				
				// handles incorrect string input for int
				while (!(d.hasNextInt())) {
					System.out.print("\nPlease refrain from using letters and/or symbols in your id:");
						d = new Scanner(System.in);
					}
				id = d.nextInt();
			}
			// command deletes records of book with the corresponding id
			PreparedStatement input = connection.prepareStatement("DELETE FROM books WHERE id = ?");
			
			input.setInt(1, id);
			
			// executes the command
			input.executeUpdate();
			
			System.out.println("The book has been deleted successfully!");
			
			// Close up our connections
			results.close();
			statement.close();
			connection.close();
		}
		// catches errors
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method Allows user to search and display existing book records from ebookstore through JDBC.
	 *  
	 * @since           version 1.0
	 */ 
	public static void SearchBook() {
		
		try {
			// Connect to the ebookstore database, via the jdbc:mysql:
	        // channel on localhost (this PC)
	        // Use username "otheruser", password "swordfish".
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebookstore?useSSL=false",
					"otheruser",
					"swordfish"
			        );
			
			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();
			ResultSet results;
		
			// input id of the book
			System.out.print("\nPlease enter the id of the book:");
			Scanner i = new Scanner(System.in);
			
			// handles incorrect string input for int
			while (!(i.hasNextInt())) {
				System.out.print("\nPlease refrain from using letters and/or symbols in your id:");
					i = new Scanner(System.in);
				}
			int id = i.nextInt();
			
			// new Array List
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			// returns all books records
			results = statement.executeQuery("SELECT * FROM books");
			
			// iterates through each book record and adds all the ids to the Arraylist
		    while (results.next()) {
					
				list.add(results.getInt("id"));
					
			}
			// handles incorrect id input
			while (!(list.contains(id))) {
			
				System.out.print("\nPlease enter a valid book id:");
				Scanner d = new Scanner(System.in);
				
				// handles incorrect string input for int
				while (!(d.hasNextInt())) {
					System.out.print("\nPlease refrain from using letters and/or symbols in your id:");
						d = new Scanner(System.in);
					}
				id = d.nextInt();
			}
			// command returns records of book with the corresponding id
			results = statement.executeQuery("SELECT * FROM books WHERE id = "+id);
			
			// displays the selected books records 
			while (results.next()) {
				System.out.println("\n"
						+ results.getInt("id") + ", "
						+ results.getString("title") + ", "
						+ results.getString("author") + ", "
						+ results.getInt("qty")
						);
			}
			
			// Close up our connections
			results.close();
			statement.close();
			connection.close();
		}
		// catches errors
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}