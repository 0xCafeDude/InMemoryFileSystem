package proofpoint.inmemoryfilesystem;

import proofpoint.inmemoryfilesystem.entities.Entity;
import proofpoint.inmemoryfilesystem.entities.EntityType;
import proofpoint.inmemoryfilesystem.entities.FileSystem;
import proofpoint.inmemoryfilesystem.exceptions.IllegalFileSystemOperationException;
import proofpoint.inmemoryfilesystem.exceptions.NotATextFileException;
import proofpoint.inmemoryfilesystem.exceptions.PathAlreadyExistsException;
import proofpoint.inmemoryfilesystem.exceptions.PathNotFoundException;

/**
 * A filesystem in our case is nothing but a k-ary trees.
 * 
 * Path assumption
 * if drive C contains folder abc and folder abc contains a file xyz I am assuming the path to xyz to be
 * C\abc\xyz
 * 
 * Assumptions for move
 * 1. there is no renaming involved. Hence a drive cannot be moved because that would be essentially a rename operation
 * 2. Suppose a file xyz has to be moved then the sample sourcePath and destinationPath will be as following
 *    sourcePath 	  C/pqr/xyz
 *    destinationPath D/pqr/abc/xyz
 *    basically destinationPath will contain the name of the file being moved which over here is xyz.
 *
 */
public class InMemoryFileSystem {
	
	private FileSystem fileSystem;
	
	public InMemoryFileSystem(){
		fileSystem = new FileSystem();
	}
	
	/**
	 * traverses down to the parent entity and then calls addChild on it.
	 * @param type
	 * @param name
	 * @param pathOfParent
	 * @throws IllegalFileSystemOperationException
	 * @throws PathNotFoundException
	 * @throws PathAlreadyExistsException
	 */
	public void create(EntityType type, String name, String pathOfParent) throws IllegalFileSystemOperationException, PathNotFoundException, PathAlreadyExistsException{
		Entity entity = traversePath(pathOfParent);
		entity.addChild(type, name);
	}
	
	/**
	 * traverses down to the parent of the entity to be deleted and then calls delete child on that entity.
	 * @param path
	 * @throws PathNotFoundException
	 */
	public void delete(String path) throws PathNotFoundException{
		if(path == null || path.equals(""))
			throw new PathNotFoundException();
		int namePos = path.lastIndexOf("\\");
		if(namePos == -1)
			fileSystem.deleteChild(path);
		else{
			String pathOfParent = path.substring(0, namePos);
			String entityName = path.substring(namePos+1, path.length());
			Entity entity = traversePath(pathOfParent);
			entity.deleteChild(entityName);
		}
	}
	
	/**
	 * traverses down the path and calls writeToFile on that entity.
	 * @param path
	 * @param content
	 * @throws PathNotFoundException
	 * @throws NotATextFileException
	 */
	public void writeToFile(String path, String content) throws PathNotFoundException, NotATextFileException{
		if(path == null || path.equals(""))
			throw new PathNotFoundException();
		int namePos = path.lastIndexOf("\\");
		if(namePos == -1)
			throw new PathNotFoundException();
		else{
			Entity entity = traversePath(path);
			entity.writeToFile(content);
		}
	}
	
	/**
	 * this api was added just to test the code
	 */
	public String getTextFileContents(String path) throws PathNotFoundException, NotATextFileException{
		if(path == null || path.equals(""))
			throw new PathNotFoundException();
		int namePos = path.lastIndexOf("\\");
		if(namePos == -1)
			throw new NotATextFileException();
		else{
			Entity entity = traversePath(path);
			return entity.getFileContents();
		}
	}
	
	/**
	 * api added to test the code
	 */
	public int getSize(String path) throws PathNotFoundException{
		if(path == null || path.equals(""))
			throw new PathNotFoundException();
		Entity entity = traversePath(path);
		return entity.getSize();
	}
	
	/**
	 * the move operation takes the entity that is to be moved and adds it to its correct position
	 * and then deletes the entity from its older position.
	 * 
	 * Assumptions for move
	 * 1. there is no renaming involved. Hence a drive cannot be moved because that would be essentially a rename operation
	 * 2. Suppose a file xyz has to be moved then the sample sourcePath and destinationPath will be as following
	 *    sourcePath 	  C/pqr/xyz
	 *    destinationPath D/pqr/abc/xyz
	 *    basically destinationPath will contain the name of the file being moved which over here is xyz.
	 * 
	 * @param sourcePath
	 * @param destinationPath
	 * @throws PathNotFoundException
	 * @throws PathAlreadyExistsException
	 * @throws IllegalFileSystemOperationException
	 */
	public void move(String sourcePath, String destinationPath) throws PathNotFoundException, PathAlreadyExistsException, IllegalFileSystemOperationException{
		if(sourcePath == null || sourcePath.equals("") || destinationPath == null || destinationPath.equals(""))
			throw new PathNotFoundException();
		Entity entityToBeMoved = traversePath(sourcePath);
		int namePos = destinationPath.lastIndexOf("\\");
		Entity entity = fileSystem;
		if(namePos != -1){
			String pathOfParent = destinationPath.substring(0, namePos);
			entity = traversePath(pathOfParent);
		}
		entity.addChild(entityToBeMoved);
		changePaths(entity,entityToBeMoved);
		delete(sourcePath);
	}
	
	/*
	 * this method recursively iterates through the contents(children) of the second argument and changes their path; so that
	 * the first argument becomes an ancestor in the path to the contents(children) of the second argument.
	 */
	private void changePaths(Entity entity, Entity entityWhosePathHaveToBeChanged){
		entityWhosePathHaveToBeChanged.setPath(entity.getPath()+"\\"+entityWhosePathHaveToBeChanged.getName());
		for(Entity childEntity : entityWhosePathHaveToBeChanged.getChildren()){
			changePaths(entityWhosePathHaveToBeChanged, childEntity);
		}
	}
	
	/*
	 * traverses down the path given as argument. It then returns the entity for which the path
	 * is given as the argument.
	 */
	private Entity traversePath(String path) throws PathNotFoundException{
		Entity entity = fileSystem;
		if(path == null || path.equals(""))
			return entity;
		String[] pathEntities = path.split("\\\\");
		for(String pathEntity : pathEntities)
			entity = entity.getChild(pathEntity);
		return entity;
	}
	
}
