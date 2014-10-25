package proofpoint.inmemoryfilesystem.exceptions;

public class PathNotFoundException extends PathException {

	public PathNotFoundException(){
		super("The path does not exist");
	}
	
}
