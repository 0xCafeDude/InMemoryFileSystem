package proofpoint.inmemoryfilesystem.entities;

import java.util.ArrayList;
import java.util.List;

import proofpoint.inmemoryfilesystem.exceptions.IllegalFileSystemOperationException;
import proofpoint.inmemoryfilesystem.exceptions.NotATextFileException;
import proofpoint.inmemoryfilesystem.exceptions.PathNotFoundException;

/**
 *
 * represents a text file
 */
public class TextFile extends Entity {
	
	private String content;

	TextFile(String name, String path) {
		this.setType(EntityType.TEXTFILE);
		this.setName(name);
		this.setPath(path);
	}

	@Override
	public void addChild(EntityType type, String Name)
			throws IllegalFileSystemOperationException {
		throw new IllegalFileSystemOperationException(Rules.ruleB);
	}
	
	@Override
	public void addChild(Entity entity)
			throws IllegalFileSystemOperationException {
		throw new IllegalFileSystemOperationException(Rules.ruleB);
	}

	@Override
	public void deleteChild(String name) throws PathNotFoundException {
		throw new PathNotFoundException();
	}

	@Override
	public void writeToFile(String content)
			throws NotATextFileException {
		setContent(content);
	}

	@Override
	public int getSize() {
		if(content != null)
			return content.length();
		return 0;
	}
	
	@Override
	public Entity getChild(String name) throws PathNotFoundException{
		throw new PathNotFoundException();
	}
	
	@Override
	public List<Entity> getChildren(){
		return new ArrayList<Entity>();
	}
	
	private String getContent() {
		return content;
	}
	
	@Override
	public String getFileContents() throws NotATextFileException {
		return getContent();
	}

	private void setContent(String content) {
		this.content = content;
	}
	
	
}
