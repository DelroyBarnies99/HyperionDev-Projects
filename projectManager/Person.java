// person class initializes project objects  

package projectManager;

import java.util.Scanner;

public class Person{
	
	// attribute variables
	String projectName;
	String position;
	String name;
	String telephoneNum;
	String email;
	String physicalAddress;
	
	// constructor initializes person objects
	public Person(String projectName, String position, String name, String telephoneNum
			, String email, String physicalAddress) {
		
		// attributes
		this.projectName = projectName;
		this.position = position;
		this.name = name;
		this.telephoneNum = telephoneNum;
		this.email = email;
		this.physicalAddress = physicalAddress;
	}
	// method creates new person with architect position
	public static Person newArchitect(String name) {	
			
			System.out.println("Please enter the employees and clients details");
			
			System.out.print("\nEnter the architects name:");
			Scanner input12 = new Scanner(System.in);
			String architectName = input12.next();
			
			System.out.print("\nEnter the architects telephone number:");
			Scanner input13 = new Scanner(System.in);
			String architectNum = input13.next();
			
			System.out.print("\nEnter the architects email:");
			Scanner input14 = new Scanner(System.in);
			String architectEmail = input14.next();
			
			System.out.print("\nEnter the architects physical address:");
			Scanner input15 = new Scanner(System.in);
			String architectAddress = input15.next();
		    
			// takes user input and creates new person object with architect position
			Person architect = new Person(name, "architect", architectName, architectNum, architectEmail, architectAddress);
			
			// returns person
			return architect;
			
	}	
	// method creates new person with contractor position
	public static Person newContractor(String name) {	
			
			System.out.print("\nEnter the contractors name:");
			Scanner input12 = new Scanner(System.in);
			String contractorName = input12.next();
			
			System.out.print("\nEnter the contractors telephone number:");
			Scanner input13 = new Scanner(System.in);
			String contractorNum = input13.next();
			
			System.out.print("\nEnter the contractors email:");
			Scanner input14 = new Scanner(System.in);
			String contractorEmail = input14.next();
			
			System.out.print("\nEnter the contractors physical address:");
			Scanner input15 = new Scanner(System.in);
			String contractorAddress = input15.next();
		    
			// takes user input and creates new person object with contractor position
			Person contractor = new Person(name, "contractor", contractorName, contractorNum, contractorEmail, contractorAddress);
			
			// returns person
			return contractor;
			
	}
	// method creates new person with customer position
	public static Person newCustomer(String name) {	
			
			System.out.print("\nEnter the customers name:");
			Scanner input12 = new Scanner(System.in);
			String customerName = input12.next();
			
			System.out.print("\nEnter the customers telephone number:");
			Scanner input13 = new Scanner(System.in);
			String customerNum = input13.next();
			
			System.out.print("\nEnter the customers email:");
			Scanner input14 = new Scanner(System.in);
			String customerEmail = input14.next();
			
			System.out.print("\nEnter the customers physical address:");
			Scanner input15 = new Scanner(System.in);
			String customerAddress = input15.next();
		    
			// takes user input and creates new person object with customer position
			Person customer = new Person(name, "customer", customerName, customerNum, customerEmail, customerAddress);
			
			// returns person
			return customer;
			
	}	
	// method updates contractors details
	public static Person contractorsDetails(Person person) {
	    	
	    	System.out.print("Enter the contractors new telephone number:");
			Scanner input10 = new Scanner(System.in);
			String newTelNum = input10.next();
			
			System.out.print("Enter the contractors new email:");
			Scanner input11 = new Scanner(System.in);
			String newEmail = input11.next();
			
			// updates contractors telephone number and email based on user input
			person.telephoneNum = newTelNum;
			person.email = newEmail;
			
			System.out.println("\nContractors contact details have been updated");
			
			// returns person
			return person;
			
	}
    public static String personToString(Person person) {
    	
		return "\n"+person.projectName+", "+person.position+", "+person.name+", "+person.telephoneNum+", "+person.email+", "+
				person.physicalAddress;
    }
}