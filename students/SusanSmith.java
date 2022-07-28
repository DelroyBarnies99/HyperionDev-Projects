// SusanSmith class implements the student interface
package students;

public class SusanSmith implements Student {
	
	// overriding the describe method of the parent student interface 
	@Override
	public String describe() {
		
		// returns the students name
		return "Susan Smith:\n"; 
	}
}