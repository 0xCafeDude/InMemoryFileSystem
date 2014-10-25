package proofpoint.inmemoryfilesystem.exceptions;

public class IllegalFileSystemOperationException extends FileSystemException {

	public IllegalFileSystemOperationException(){
		super("An illegal file system operation was attempted");
	}
	
	public IllegalFileSystemOperationException(String rule){
		super("The following rule was violated \n"+rule);
	}
	
}
