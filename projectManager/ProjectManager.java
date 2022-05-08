// this program creates,stores and manages structural engineering projects  

package projectManager;
import java.util.Scanner;
import java.util.ArrayList;

// defines the class
public class ProjectManager {
	
	// main method mainly deals with the user interface
	public static void main(String[] args) {
		
		// projects ArrayList contains all user created Project objects
	    ArrayList<Project> projects = new ArrayList<>();
	    
	    // people ArrayList contains all user created Person objects
	    ArrayList<Person> people = new ArrayList<>();
	    
	    String option = "";
	    
	    // if user enters the "f" option the program is finalized and exited  
		while (!option.equals("f")) {
		   
			// user types the letters dealing with the corresponding option
		    System.out.print("\nPlease choose an option below\n"
			    		+ "\nnew projects                        : np"
			    		+ "\nupdate due date                     : dd"
			    		+ "\nupdate total amount paid            : ap"
			    		+ "\nupdate contractors contact details  : cd"
			    		+ "\nfinalize                            : f"
			    		+ "\n\nEnter your option here:");
			Scanner input1 = new Scanner(System.in);
			option = input1.next();
			
			option.toLowerCase();
			
			// while loop iterates for every time the user enters an invalid option
			while (!(option.equals("np") || option.equals("dd")|| option.equals("ap") || option.equals("cd") || option.equals("f"))) {
				
				input1 = new Scanner(System.in);
				System.out.print("\nPlease enter a valid option:\n"
			    		+ "\nnew projects                        : np"
			    		+ "\nupdate due date                     : dd"
			    		+ "\nupdate total amount paid            : ap"
			    		+ "\nupdate contractors contact details  : cd"
			    		+ "\nfinalize                            : f"
			    		+ "\n\nEnter your option here:");
				option = input1.next();
				option.toLowerCase();
			}
			input1.close();
					
		    // creates new projects
		    if (option.equals("np")) {
		    	
		    	// adds the created project to the projects list
		    	projects.add(Project.newProject());
		    	
		    	System.out.print("\nEnter the project name:");
				Scanner input16 = new Scanner(System.in);
				String name = input16.next();
				input16.close();
				
				// adds the created people to the people list
		    	people.add(Person.newArchitect(name));
		    	people.add(Person.newContractor(name));
		    	people.add(Person.newCustomer(name));
		    	
		    }
		    // changes the due date of a project
		    if (option.equals("dd")) {
		    	
		    	System.out.print("\nEnter the project name:");
				Scanner input3 = new Scanner(System.in);
				String name = input3.next();
				input3.close();
				
				// iterates through every project to find the input project name 
				for (Project project : projects) {
				    if (name.equals(project.name)) {
				    	
				    	// changes the projects g=date
				    	Project.dueDate(project);
				    }
				    else {
				    	System.out.println("This project dosent exist");
				    }
				}
				
		    }
		    // updates the total amount paid to date
		    if (option.equals("ap")) {
		    	
		    	System.out.print("\nEnter the project name:");
				Scanner input3 = new Scanner(System.in);
				String name = input3.next();
				input3.close();
				
				// locates the correct project from projects list and changes the amount paid to date
				for (Project project : projects) {
				    if (name.equals(project.name)) {
				    	Project.paidToDate(project);
				    }
				    else {
				    	System.out.println("This project dosent exist");
				    }
				}
		    }
		    // updates contractors contact details
		    if (option.equals("cd")) {
		    	
		    	System.out.print("\nEnter the contractors name:");
				Scanner input3 = new Scanner(System.in);
				String name = input3.next();
				input3.close();
				
				// locates the correct contractor and updates their details 
				for (Person person : people) {
				    if (name.equals(person.name) && (person.position == "architect")) {
				    	Person.contractorsDetails(person);
				    }
				    else {
				    	System.out.println("This person dosent exist");
				    }
				}	
		    }
		    // finalizes project and exits the program
		    if (option.equals("f")) {
		    	
		    	System.out.print("\nEnter the project name:");
				Scanner input16 = new Scanner(System.in);
				String projectName = input16.next();
				input16.close();
				
				System.out.print("\nEnter the customers name:");
				Scanner input17 = new Scanner(System.in);
				String customerName = input17.next();
				input17.close();
				
				// locates the correct project and customer based on user input from the projects and 
				// people lists, finalizes the project
				for (Person person : people) {
				    if (customerName.equals(person.name) && (person.position == "customer")) {
				    	for (Project project : projects) {
						    if (projectName.equals(project.name)) {
						    	finalise(person, project);
						    }
				        }
				    }
				    else {
				    	System.out.println("This person dosent exist.");
				    }
				}	
		    }    
		}  
	}
    // method finalizes the project 
    public static void finalise(Person customer, Project project) {
    	
    	// total unpaid value of the project is calculated
    	float totalUnpaid = project.fee - project.amountPaid;
    	
    	// if total unpaid returns 0 no invoice is created
    	if (totalUnpaid == 0) {
    		System.out.println("full fee has already been paid");
    	}
    	// else invoice is generated
    	else {
    	    System.out.println("INVOICE\n"
    			+ "name: " + customer.name + "\n"
    			+ "telephone number: " + customer.telephoneNum + "\n"
    			+ "email: " + customer.email + "\n"
    			+ "physical address: " + customer.physicalAddress + "\n"
    			+ "\n"
    			+ "amount unpaid: R" + totalUnpaid);
    	    
        }    
    }
}