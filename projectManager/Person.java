// person class initializes project objects  

package projectManager;

import java.util.ArrayList;
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
			String architectName = input12.nextLine();
			
			System.out.print("\nEnter the architects telephone number:");
			Scanner input13 = new Scanner(System.in);
			String architectNum = input13.nextLine();
			
			System.out.print("\nEnter the architects email:");
			Scanner input14 = new Scanner(System.in);
			String architectEmail = input14.nextLine();
			
			System.out.print("\nEnter the architects physical address:");
			Scanner input15 = new Scanner(System.in);
			String architectAddress = input15.nextLine();
		    
			// takes user input and creates new person object with architect position
			Person architect = new Person(name, "architect", architectName, architectNum, architectEmail, architectAddress);
			
			// returns person
			return architect;
			
	}	
	// method creates new person with contractor position
	public static Person newContractor(String name) {	
			
			System.out.print("\nEnter the contractors name:");
			Scanner input12 = new Scanner(System.in);
			String contractorName = input12.nextLine();
			
			System.out.print("\nEnter the contractors telephone number:");
			Scanner input13 = new Scanner(System.in);
			String contractorNum = input13.nextLine();
			
			System.out.print("\nEnter the contractors email:");
			Scanner input14 = new Scanner(System.in);
			String contractorEmail = input14.nextLine();
			
			System.out.print("\nEnter the contractors physical address:");
			Scanner input15 = new Scanner(System.in);
			String contractorAddress = input15.nextLine();
		    
			// takes user input and creates new person object with contractor position
			Person contractor = new Person(name, "contractor", contractorName, contractorNum, contractorEmail, contractorAddress);
			
			// returns person
			return contractor;
			
	}
	// method creates new person with customer position
	public static Person newCustomer(String name) {	
			
			System.out.print("\nEnter the customers name:");
			Scanner input12 = new Scanner(System.in);
			String customerName = input12.nextLine();
			
			System.out.print("\nEnter the customers telephone number:");
			Scanner input13 = new Scanner(System.in);
			String customerNum = input13.nextLine();
			
			System.out.print("\nEnter the customers email:");
			Scanner input14 = new Scanner(System.in);
			String customerEmail = input14.nextLine();
			
			System.out.print("\nEnter the customers physical address:");
			Scanner input15 = new Scanner(System.in);
			String customerAddress = input15.nextLine();
		    
			// takes user input and creates new person object with customer position
			Person customer = new Person(name, "customer", customerName, customerNum, customerEmail, customerAddress);
			
			// returns person
			return customer;
			
	}	
	// method updates contractors details
	public static Person contractorsDetails(Person person) {
	    	
	    	System.out.print("Enter the contractors new telephone number:");
			Scanner input10 = new Scanner(System.in);
			String newTelNum = input10.nextLine();
			
			System.out.print("Enter the contractors new email:");
			Scanner input11 = new Scanner(System.in);
			String newEmail = input11.nextLine();
			
			// updates contractors telephone number and email based on user input
			person.telephoneNum = newTelNum;
			person.email = newEmail;
			
			System.out.println("\nContractors contact details have been updated");
			
			// returns person
			return person;
			
	}
	// allows user to update any person objects details in an ArrayList 
	public static String updateClients(ArrayList<Person> people) {
		
		String client = "";
			
	    System.out.println("Enter the name of the Client:\n");
		Scanner input10 = new Scanner(System.in);
		client = input10.nextLine();
		
		// iterates through every person in the ArrayList until the person.name matches the input name 
		for (Person person : people) {
			String option = "";
			
			if (person.name.equals(client)){
				
				System.out.println("You have chosen "+person.name+" in position: "+person.position+" for project: "+person.projectName);
				
				// displays options, user inputs "e" to return to the main menu
				while (!(option.equals("e"))){
			
					Scanner input19 = new Scanner(System.in);
					System.out.print(  "\n\nWhat would you like to change? "
							         + "\nname                :n"
							         + "\ntelephoneNum        :no"
							         + "\nemail               :em"
							         + "\naddress             :a"
							         + "\nexit                :e"
							         + "\nEnter your option here:");
					
					option = input19.next();
					option = option.toLowerCase();
					
					// loops for each time the user enters incorrectly
					while (!(option.equals("n") || option.equals("e")|| option.equals("no") || option.equals("em") || option.equals("a"))){ 
						
						input19 = new Scanner(System.in);
						System.out.print(  "\n\nWhat would you like to change? "
						                 + "\nname                :n"
						                 + "\ntelephoneNum        :no"
						                 + "\nemail               :em"
						                 + "\naddress             :a"
						                 + "\nexit                :e"
						                 + "\nEnter your option here:");
						option = input19.next();
						option = option.toLowerCase();
					}
					// updates the person.name 
                    if (option.equals("n")) {
						
						System.out.print("\nEnter the new name:");
						Scanner input2 = new Scanner(System.in);
						String name = input2.nextLine();
						
						person.name = name;
					}
                    // updates the person.telephoneNum
					if (option.equals("no")) {
						
						System.out.print("\nChoose a new telephone number");
						Scanner input3 = new Scanner(System.in);
						String number = input3.nextLine();
						
						person.telephoneNum = number;
						
					}
					// updates the person.email
                    if (option.equals("em")) {
						
						System.out.print("\nEnter the new email:");
						Scanner input2 = new Scanner(System.in);
						String email = input2.nextLine();
						
						person.email = email;
					}
                    // updates the person.address
					if (option.equals("a")) {
						
						System.out.print("\nChoose a new address");
						Scanner input3 = new Scanner(System.in);
						String address = input3.nextLine();
						
						person.physicalAddress = address;
						
					}
				}			
			}
		}
		// returns back to the main menu
		System.out.println("\nBack to main menu");
		return null;
	}
	// returns a person objects details in an easily readable manner as a string
    public static String displayPerson(ArrayList<Person> people) {
    	
    	System.out.print("\nEnter the clients name:");
		Scanner input18 = new Scanner(System.in);
		String name = input18.nextLine();
		
		String details = "";
		
		// iterates through every person in the ArrayList until the person.name matches the input name 
		for (Person person : people) {
			if (person.name.equals(name)) {
				
				      details = ("\n\nproject:               "+ person.projectName
						         + "\nname:                  "+person.name 
						         + "\nposition:              "+person.position  
						         + "\ntelephone number:      "+person.telephoneNum 
						         + "\nemail:                 "+person.email
						         + "\naddress:               "+person.physicalAddress);		
			}
		}
        return details; 
    }
	// returns person details as a string to be stored in a text file for easy extraction
    public static String personToString(Person person) {
    	
		return person.projectName+", "+person.position+", "+person.name+", "+person.telephoneNum+", "+person.email+", "+
				person.physicalAddress;
    }
}