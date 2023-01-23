package musicManager;

/** 
* This class represents a song record that contains information 
* about a song, including the title, artist, 
* and length (in minutes and seconds)
* 
* @author Zachary Cytryn
*/
public class SongRecord {

	//Member variables
	/**
	 * Stores title of song as a string
	 */
	private String title;
	
	/**
	 * Stores artist of song as a string
	 */
	private String artist;
	
	/**
	 * Stores the length in minutes of a song as an int
	 */
	private int minutes;
	
	/**
	 * Stores the length of seconds of a song as an int 
	 * (not total amount of seconds, but supplement to minutes)
	 */
	private int seconds;
	
	/**
	 * This is a constructor is used to create a new SongRecord object
	 * 
	 * @param title
	 * Title of the song
	 * @param artist
	 * Artist of the song
	 * @param minutes
	 * Amount of minutes in the song
	 * @param seconds
	 * Amount of seconds in the song supplement to minutes
	 * 
	 * @throws IllegalArgumentException
	 * Exception thrown if invalid amount of seconds is entered
	 */
	public SongRecord(String title, String artist, int minutes, int seconds) throws IllegalArgumentException{
		this.title = title;
		this.artist = artist;
		this.minutes = minutes;
		if (seconds < 0 || seconds > 59) {
			throw new IllegalArgumentException("Invalid Song Length");
		}
		else {
			this.seconds = seconds;
		}
	}
	
	//Accessor methods
	/**
	 * This method returns the title of the song
	 * 
	 * @return
	 * The title of the song
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This method returns the artist of the song
	 * 
	 * @return
	 * The artist of the song
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * This method returns the amount of minutes in a song
	 * 
	 * @return
	 * The amount of minutes in a song
	 */
	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * This method returns the amount of seconds in a song supplement to minutes
	 * 
	 * @return
	 * The amount of seconds supplement to minutes in a song
	 */
	public int getSeconds() {
		return seconds;
	}
	
	//Mutator methods
	/**
	 * This method changes the title
	 * 
	 * @param newTitle
	 * New title name
	 */
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	
	/**
	 * This method changes the artist
	 * 
	 * @param newArtist
	 * New artist name
	 */
	public void setArtist(String newArtist) {
		artist = newArtist;
	}
	
	/**
	 * This method changes the amount of minutes
	 * 
	 * @param newMinutes
	 * New amount of minutes
	 */
	public void setMinutes(int newMinutes) {
		minutes = newMinutes;
	}
	
	/**
	 * This method changes the amount of seconds supplement to minutes
	 * 
	 * @param newSeconds
	 * New amount of seconds
	 * 
	 * @throws IllegalArgumentException
	 * When new seconds value is less than 0 or greater than 59
	 */
	public void setSeconds(int newSeconds) throws IllegalArgumentException {
		if (newSeconds < 0 || newSeconds > 59) {
			throw new IllegalArgumentException("Error: Please enter a number greater than 0 and less than 59.");
		}
		seconds = newSeconds;
	}
	
	/**
	 * This method creates a properly formatted string representation of the song
	 * 
	 * @param position
	 * position of the song in the playlist
	 * 
	 * @return
	 * The properly formatted string representation of the song
	 */
	public String toString(int position) {
		String songRecordString = String.format("%-10s %-15s %-15s %-5s", "Song#", "Title", "Artist", "Length");
		songRecordString += "\n------------------------------------------------\n";
		if (seconds < 10) {
			songRecordString += String.format("%-10s %-15s %-15s %-5s", position, title, artist, minutes + ":0" + seconds);
		}
		else {
			songRecordString += String.format("%-10s %-15s %-15s %-5s", position, title, artist, minutes + ":" + seconds);
		}
		return songRecordString;
	}
}
