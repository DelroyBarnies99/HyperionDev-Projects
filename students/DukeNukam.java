// DukeNukam class implements the student interface
package students;

public class DukeNukam implements Student {
	
	//overriding the describe method of the parent student interface 
	@Override
	public String describe() {
		
		// returns the students name
		return "Duke Nukam:\n";
	}
}