// DataScientistCamp subclass extends the Bootcamp superclass
package students;

public class DataScientistCamp extends Bootcamp {
	
	// Registers a student to the currant bootcamp
	public DataScientistCamp(Student bootcamp) {
		
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
    	return "- Registered for the Data Scienctist Bootcamp\n";
    }
}
