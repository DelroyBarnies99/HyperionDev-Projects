// SoftwareEngineeringCamp subclass extends the Bootcamp superclass
package students;

public class SoftwareEngineeringCamp extends Bootcamp {
	
	// Registers a student to the currant bootcamp
	public SoftwareEngineeringCamp(Student bootcamp) {
		
		// super invokes the immediate parent class constructor
		super(bootcamp);
    }
	// overriding the describe method of the parent student interface 
    @Override
    public String describe() {
    	return super.describe() + placeThisBootcamp();
    }
 // returns the registered bootcamp
    private String placeThisBootcamp() {
    	return "- Registered for the Software Engineering Bootcamp\n";
    }
}
