// This program uses a Decorator Design Pattern to store students information on their coding bootcamp
package students;

// defines the interface
public interface Student{
	
	// interface method used to print all information on student
    String describe();

// defines the main method
public static void main(String[] args) {
	
	// student 1 object 
	Student student1 = new SusanSmith();
	student1 = new WebDeveloperCamp(student1);
	student1 = new CompletedLevel1(student1);
	
	// student 2 object
	Student student2 = new MichaelJackson();
	student2 = new SoftwareEngineeringCamp(student2);
	student2 = new CompletedLevel1(student2);
	student2 = new CompletedLevel2(student2);
	
	// student 3 object
	Student student3 = new DukeNukam();
	student3 = new DataScientistCamp(student3);
	student3 = new CompletedLevel1(student3);
	student3 = new CompletedLevel2(student3);
	student3 = new CompletedLevel3(student3);
	student3 = new SoftwareEngineeringCamp(student3);
	
	// prints all the student objects information using the .describe method
	System.out.println(student1.describe());
	System.out.println(student2.describe());
	System.out.println(student3.describe());
		
}	
}