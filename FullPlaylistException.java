package musicManager;

/**
 * This class is used for FullPlaylistExceptions, which is when
 * the user tries to exceed the capacity of the playlist
 * 
 * @author Zachary Cytryn
 */
public class FullPlaylistException extends Exception {
	
	/**
	 * This is the constructor for the exception
	 * 
	 * @param message
	 * Used to input a message to be printed out to the user
	 */
	public FullPlaylistException(String message) {
		super(message);
	}
}
