package proofpoint.inmemoryfilesystem.entities;

import java.util.List;

import proofpoint.inmemoryfilesystem.exceptions.IllegalFileSystemOperationException;
import proofpoint.inmemoryfilesystem.exceptions.NotATextFileException;
import proofpoint.inmemoryfilesystem.exceptions.PathAlreadyExistsException;
import proofpoint.inmemoryfilesystem.exceptions.PathNotFoundException;

/**
 * Represents an entity in the file system.
 *
 */
public abstract class Entity {
	
	private EntityType type;
	private String name;
	private String path;
	
	public EntityType getType() {
		return type;
	}
	public void setType(EntityType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public abstract void addChild(EntityType type, String Name) throws IllegalFileSystemOperationException, PathAlreadyExistsException;
	
	/*
	 * this method is for move operation. In this method whole entity and its child entities are made the child of another entity.
	 */
	public abstract void addChild(Entity entity) throws IllegalFileSystemOperationException, PathAlreadyExistsException;
	public abstract void deleteChild(String name) throws PathNotFoundException;
	public abstract void writeToFile(String content) throws NotATextFileException;
	public abstract String getFileContents() throws NotATextFileException;
	public abstract int getSize();
	public abstract Entity getChild(String name) throws PathNotFoundException;
	public abstract List<Entity> getChildren();
	
}
