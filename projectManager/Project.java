// Project class initializes project objects  

package projectManager;

import java.util.Scanner;
import java.util.Locale;

public class Project{
	
	// attribute variables
	String name;
	int number;
	String buildingType;
	String address;
	int erf;
	float fee;
	float amountPaid;
	String deadline;
	
	// constructor initializes Project objects
	public Project(String name, int number, String buildingType, String address, int erf
			    , float fee, float amountPaid, String deadline) {
		
		// attributes 
		this.name = name;
		this.number = number;
		this.buildingType = buildingType;
		this.address = address;
		this.erf = erf;
		this.fee = fee;
		this.amountPaid = amountPaid;
		this.deadline = deadline;
		
	}
	// method used for creating new project objects
    public static Project newProject() {
			
			System.out.print("\nEnter the project name:");
			Scanner input = new Scanner(System.in);
			String name = input.next();
			
			System.out.print("\nEnter the project number:");
			Scanner input1 = new Scanner(System.in);
			
			// handles incorrect string input for int
			while (!(input1.hasNextInt())) {
			System.out.print("\nPlease refrain from using letters and/or symbols in your project number:");
				input1 = new Scanner(System.in);
			}
			int number = input1.nextInt();
			
			System.out.print("\nEnter the building type:");
			Scanner input2 = new Scanner(System.in);
			String buildingType = input2.next();
			
			System.out.print("\nEnter the address:");
			Scanner input3 = new Scanner(System.in);
			String address = input3.next();
			
			System.out.print("\nEnter the ERF number:");
			Scanner input4 = new Scanner(System.in);
			
			// handles incorrect string input for int
			while (!(input4.hasNextInt())) {
				System.out.print("\nPlease refrain from using letters and/or symbols in your ERF number:");
					input4 = new Scanner(System.in);
				}
			int erf = input4.nextInt();
			
			System.out.print("\nEnter the charging fee:");
			Scanner input5 = new Scanner(System.in).useLocale(Locale.US);
			
			// handles incorrect string input for float
			while (!(input5.hasNextFloat())) {
				System.out.print("\nPlease refrain from using letters and/or symbols in your charging fee:");
					input5 = new Scanner(System.in).useLocale(Locale.US);
			}
			float fee = input5.nextFloat();
				
			System.out.print("\nEnter the amount paid:");
			Scanner input6 = new Scanner(System.in).useLocale(Locale.US);
				
			// handles incorrect string input for float
			while (!(input6.hasNextFloat())) {
				System.out.print("\nPlease refrain from using letters and/or symbols in your amount paid:");
					input6 = new Scanner(System.in).useLocale(Locale.US);
			}
			float amountPaid = input6.nextFloat();
				
			System.out.print("\nEnter the deadline:");
			Scanner input7 = new Scanner(System.in);
			String deadline = input7.next();
				
		    // takes user input and creates Project object
			Project thisProject = new Project(name, number, buildingType, address, erf, fee, amountPaid, deadline);
				
			System.out.println("\nNew Project has been added\n");
				
			// returns project
			return thisProject;
			
	}
    // method updates a projects due date
    public static Project dueDate(Project project) {
		
		System.out.print("Enter the new date:");
		Scanner input8 = new Scanner(System.in);
		String newdate = input8.next();
		
		// updates the deadline based on the input due date
		project.deadline = newdate;
		
		System.out.println("\nDue date has been updated");
		
		// returns project
		return project;
		
	}
    // method updates a projects amount paid to date
    public static Project paidToDate(Project project) {
		
		System.out.print("Enter the new amount paid to date:");
		Scanner input9 = new Scanner(System.in).useLocale(Locale.US);
		
		// handles incorrect string input for float
 		while (!(input9.hasNextFloat())) {
			System.out.print("\nPlease refrain from using letters and/or symbols in your amount paid:");
				input9 = new Scanner(System.in).useLocale(Locale.US);
			}
		float newAmount = input9.nextFloat();
		
		// updates amount paid to date based on user input
		project.amountPaid = newAmount;
		
		System.out.println("\nAmount paid to date has been updated");
		
		// returns project
		return project;
		
	}
    public static String projectToString(Project project) {
    	
		return "\n"+project.name+", "+project.number+", "+project.buildingType+", "+project.address+", "+project.erf+", "+
				project.fee+", "+project.amountPaid+", "+project.deadline;
    }
}