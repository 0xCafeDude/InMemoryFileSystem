package proofpoint.inmemoryfilesystem.exceptions;

public class FileSystemException extends Exception {
	
	public FileSystemException(){
		super("A file system exception occured");
	}
	
	public FileSystemException(String message){
		super(message);
	}
}
