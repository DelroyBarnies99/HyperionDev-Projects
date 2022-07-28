// bootcamp class will decorate the different student objects
package students;

public abstract class Bootcamp implements Student {
	protected Student bootcamp;
	
	// method initializes new bootcamp field 
    public Bootcamp(Student bootcamp){
    	this.bootcamp = bootcamp;
    }
    // overriding the describe method of the parent student interface
    @Override
    public String describe() {
    	return bootcamp.describe();
    }
}
