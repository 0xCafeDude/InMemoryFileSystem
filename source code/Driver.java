import proofpoint.inmemoryfilesystem.InMemoryFileSystem;
import proofpoint.inmemoryfilesystem.entities.EntityType;
import proofpoint.inmemoryfilesystem.exceptions.IllegalFileSystemOperationException;
import proofpoint.inmemoryfilesystem.exceptions.NotATextFileException;
import proofpoint.inmemoryfilesystem.exceptions.PathAlreadyExistsException;
import proofpoint.inmemoryfilesystem.exceptions.PathNotFoundException;


public class Driver {
	
	public static void main(String args[]){
		InMemoryFileSystem inMemFileSys = new InMemoryFileSystem();
		try {
			inMemFileSys.create(EntityType.DRIVE, "C", "");
			inMemFileSys.create(EntityType.FOLDER, "F1", "C");
			inMemFileSys.create(EntityType.FOLDER, "F2", "C\\F1");
			inMemFileSys.create(EntityType.ZIPFILE, "Z1", "C\\F1\\F2");
			inMemFileSys.create(EntityType.TEXTFILE, "T1", "C\\F1\\F2\\Z1");
			inMemFileSys.writeToFile("C\\F1\\F2\\Z1\\T1", "Hello");
			System.out.println(inMemFileSys.getTextFileContents("C\\F1\\F2\\Z1\\T1"));
			inMemFileSys.create(EntityType.DRIVE, "D", "");
			inMemFileSys.create(EntityType.FOLDER, "DF1", "D");
			inMemFileSys.create(EntityType.FOLDER, "DF2", "D\\DF1");
			inMemFileSys.create(EntityType.ZIPFILE, "DZ1", "D\\DF1\\DF2");
			inMemFileSys.create(EntityType.TEXTFILE, "T1", "D\\DF1\\DF2\\DZ1");
			inMemFileSys.writeToFile("D\\DF1\\DF2\\DZ1\\T1", "HelloD");
			System.out.println(inMemFileSys.getTextFileContents("D\\DF1\\DF2\\DZ1\\T1"));
			inMemFileSys.delete("D\\DF1\\DF2\\DZ1\\T1");
			//System.out.println(inMemFileSys.getTextFileContents("D\\DF1\\DF2\\DZ1\\T1"));
			inMemFileSys.move("C\\F1\\F2\\Z1\\T1", "D\\DF1\\DF2\\DZ1\\T1");
			System.out.println(inMemFileSys.getTextFileContents("D\\DF1\\DF2\\DZ1\\T1"));
			//inMemFileSys.create(EntityType.TEXTFILE, "T2", "D\\DF1\\DF2\\DZ1\\T1");
			//System.out.println(inMemFileSys.getTextFileContents("C\\F1\\F2\\Z1\\T1"));
			inMemFileSys.create(EntityType.DRIVE, "E", "");
			inMemFileSys.create(EntityType.TEXTFILE, "T2", "D\\DF1\\DF2\\DZ1");
			inMemFileSys.writeToFile("D\\DF1\\DF2\\DZ1\\T2", "HelloT2");
			System.out.println(inMemFileSys.getTextFileContents("D\\DF1\\DF2\\DZ1\\T2"));
			//inMemFileSys.create(EntityType.TEXTFILE, "T3", "D\\DF1\\DF2\\DZ1\\T2");
			//inMemFileSys.move("D\\DF1", "DF1");
			//inMemFileSys.create(EntityType.DRIVE, "F", "D\\DF1\\DF2\\DZ1");
			//inMemFileSys.create(EntityType.DRIVE, "F", "D\\DF1\\DF2\\Dz1");
			//inMemFileSys.create(EntityType.FOLDER, "F", "");
			//inMemFileSys.create(EntityType.ZIPFILE, "F", "");
			//inMemFileSys.create(EntityType.TEXTFILE, "F", "");
			
			/*
			 * property size has been utilized over here.
			 */
			System.out.println(inMemFileSys.getSize("D\\DF1\\DF2"));
			System.out.println(inMemFileSys.getSize("D\\DF1\\DF2\\DZ1"));
			System.out.println(inMemFileSys.getSize("D\\DF1\\DF2\\DZ1\\T1"));
			System.out.println(inMemFileSys.getSize("D\\DF1\\DF2\\DZ1\\T2"));
			System.out.println(inMemFileSys.getSize("E"));
			System.out.println(inMemFileSys.getSize("C"));
		} catch (PathNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (PathAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (IllegalFileSystemOperationException e) {
			System.out.println(e.getMessage());
		} catch (NotATextFileException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
