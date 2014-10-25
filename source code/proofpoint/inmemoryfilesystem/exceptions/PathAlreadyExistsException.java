package proofpoint.inmemoryfilesystem.exceptions;

public class PathAlreadyExistsException extends PathException {
	
	public PathAlreadyExistsException(){
		super("Path already exists");
	}
	
}
