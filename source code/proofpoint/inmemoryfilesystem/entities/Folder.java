package proofpoint.inmemoryfilesystem.entities;




/**
 * 
 * represent a folder.
 */
public class Folder extends Container {
	
	Folder(String name, String path){
		this.setType(EntityType.FOLDER);
		this.setName(name);
		this.setPath(path);
	}
}
