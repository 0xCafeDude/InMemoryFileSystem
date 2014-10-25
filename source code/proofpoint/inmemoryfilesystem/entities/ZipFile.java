package proofpoint.inmemoryfilesystem.entities;




/**
 * 
 * represents a zipfile
 */
public class ZipFile extends Container {
	
	ZipFile(String name, String path) {
		this.setType(EntityType.ZIPFILE);
		this.setName(name);
		this.setPath(path);
	}
	
	public int getSize(){
		return super.getSize()/2;
	}
}
