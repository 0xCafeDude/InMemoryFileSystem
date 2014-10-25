package proofpoint.inmemoryfilesystem.entities;




/**
 * 
 * represents a Drive.
 */
public class Drive extends Container {
	
	Drive(String name){
		this.setType(EntityType.DRIVE);
		this.setName(name);
		this.setPath(name);
	}
	
}
