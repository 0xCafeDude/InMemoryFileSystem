package proofpoint.inmemoryfilesystem.exceptions;

public class PathException extends FileSystemException {
	
	public PathException(){
		super("An exception related to incorrect path occured");
	}
	
	public PathException(String message){
		super(message);
	}
}
