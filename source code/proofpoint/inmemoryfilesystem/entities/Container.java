package proofpoint.inmemoryfilesystem.entities;

import java.util.ArrayList;
import java.util.List;

import proofpoint.inmemoryfilesystem.exceptions.IllegalFileSystemOperationException;
import proofpoint.inmemoryfilesystem.exceptions.NotATextFileException;
import proofpoint.inmemoryfilesystem.exceptions.PathAlreadyExistsException;
import proofpoint.inmemoryfilesystem.exceptions.PathNotFoundException;

/**
 * This class represents any entity that has the capacity of containing other entities.
 * Since container is also just a concept like Entity I have kept this class abstract as well. 
 */
public abstract class Container extends Entity {
	
	Container(){
		this.children = new ArrayList<Entity>();
	}
	
	/*
	 * the contents of this entity are kept in this list.
	 */
	private List<Entity> children;
	
	@Override
	public List<Entity> getChildren(){
		return children;
	}
	
	@Override
	public Entity getChild(String name) throws PathNotFoundException{
		for(Entity node : children){
			if(node.getName().equals(name)){
				return node;
			}
		}
		throw new PathNotFoundException();
	}

	protected void addNode(Entity node) {
		children.add(node);
	}

	private void removeNode(String name) throws PathNotFoundException {
		for(Entity node : children){
			if(node.getName().equals(name)){
				children.remove(node);
				return;
			}
		}
		throw new PathNotFoundException();
	}
	
	protected void checkForDuplicateNameAndThrowException(String name) throws PathAlreadyExistsException{
		for(Entity node : children){
			if(node.getName().equals(name)){
				throw new PathAlreadyExistsException();
			}
		}
	}
	
	@Override
	public void addChild(EntityType type, String name) throws IllegalFileSystemOperationException, PathAlreadyExistsException{
		checkForDuplicateNameAndThrowException(name);
		Entity entity = null;
		switch(type){
		case DRIVE:
			throw new IllegalFileSystemOperationException(Rules.ruleC);
		case TEXTFILE:
			entity = new TextFile(name, this.getPath()+"\\"+name);
			break;
		case ZIPFILE:
			entity = new ZipFile(name, this.getPath()+"\\"+name);
			break;
		case FOLDER:
			entity = new Folder(name, this.getPath()+"\\"+name);
			break;
		}
		this.addNode(entity);
	}
	
	/*
	 * this method is for move operation. In this method whole entity and its child entities are made the child of another entity. 
	 */
	@Override
	public void addChild(Entity entity) throws PathAlreadyExistsException, IllegalFileSystemOperationException{
		checkForDuplicateNameAndThrowException(entity.getName());
		if(entity.getType() == EntityType.DRIVE)
			throw new IllegalFileSystemOperationException(Rules.ruleC);
		this.addNode(entity);
	}
	
	@Override
	public void deleteChild( String name) throws PathNotFoundException{
		removeNode(name);
	}
	
	@Override
	public void writeToFile(String content) throws NotATextFileException {
		throw new NotATextFileException();
	}
	
	@Override
	public String getFileContents() throws NotATextFileException {
		throw new NotATextFileException();
	}
	
	@Override
	public int getSize(){
		int temp = 0;
		for(Entity node : children){
			temp+= node.getSize();
		}
		return temp;
	}
	
}
