package proofpoint.inmemoryfilesystem.entities;

/**
 * 
 * this class contains the rules that the file system should follow. These rules are sent as message in the exception thrown.
 */
public class Rules {
	public static final String ruleA = "A folder, a drive or a zip file, may contain zero to many other folders, or files (text or zip).";
	public static final String ruleB = "A text file does not contain any other entity.";
	public static final String ruleC = "A drive is not contained in any entity.";
	public static final String ruleD = "Any non-drive entity must be contained in another entity.";
}
