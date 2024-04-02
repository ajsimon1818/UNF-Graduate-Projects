/**
 * 
 * - Enforces the getInfo() and getFileData() methods, allowing 
 * them to be overwritten for other class specific needs.
 *
 */
public interface DataHandler {
	public abstract String getInfo();
	public abstract String getFileData();
}
