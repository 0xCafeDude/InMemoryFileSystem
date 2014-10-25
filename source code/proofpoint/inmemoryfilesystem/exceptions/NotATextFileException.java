package proofpoint.inmemoryfilesystem.exceptions;

public class NotATextFileException extends FileSystemException {
	
	public NotATextFileException(){
		super("Not a text file exception");
	}
	
}
