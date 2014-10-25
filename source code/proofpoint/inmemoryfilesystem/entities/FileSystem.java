package proofpoint.inmemoryfilesystem.entities;

import proofpoint.inmemoryfilesystem.exceptions.IllegalFileSystemOperationException;
import proofpoint.inmemoryfilesystem.exceptions.PathAlreadyExistsException;

/**
 * I have defined FileSystem as something which is the parent of all the drives.
 * It only allows the creation of drive as its child.
 *
 */
public class FileSystem extends Container {
	
	public void addChild(EntityType type, String name) throws IllegalFileSystemOperationException, PathAlreadyExistsException{
		checkForDuplicateNameAndThrowException(name);
		Entity entity = null;
		switch(type){
		case DRIVE:
			entity = new Drive(name);
			break;
		case TEXTFILE:
			throw new IllegalFileSystemOperationException(Rules.ruleD);
		case ZIPFILE:
			throw new IllegalFileSystemOperationException(Rules.ruleD);
		case FOLDER:
			throw new IllegalFileSystemOperationException(Rules.ruleD);
		}
		this.addNode(entity);
	}
	
	public void addChild(Entity entity) throws PathAlreadyExistsException, IllegalFileSystemOperationException{
		checkForDuplicateNameAndThrowException(entity.getName());
		if(entity.getType() == EntityType.DRIVE)
			this.addNode(entity);
		throw new IllegalFileSystemOperationException(Rules.ruleD);
	}
	
}
