// Level class will decorate the different student objects
package students;

public abstract class Level implements Student {
	protected Student level;
	
	// method initializes new level field 
    public Level(Student level){
    	this.level = level;
    }
    // overriding the describe method of the parent student interface
    @Override
    public String describe() {
    	return level.describe();
    }
}