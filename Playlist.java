/**
 * Zachary Cytryn
 * ID: 114283379
 * Email: zachary.cytryn@stonybrook.edu
 * Homework #1
 * CSE 214 Recitation 30
 */


package musicManager;

/**
 * This class represents a playlist that stores all 
 * SongRecord objects relative to a specific playlist object.
 * 
 * @author Zachary Cytryn
 */
public class Playlist {
	
	/**
	 * This variable represents the maximum amount of SongRecords that could be on a playlist
	 */
	private static final int MAX_SONGS = 50;
	
	private SongRecord[] playlist;
	
	/**
	 * This variable represents the size of the playlist
	 */
	private int size = 0;
	
	/**
	 * This is a constructor used to create a new Playlist object.
	 */
	public Playlist() {
		playlist = new SongRecord[MAX_SONGS];
	}
	
	/**
	 * This method is used to clone playlists
	 * 
	 * @return
	 * Returns an exact copy of the playlist being cloned
	 */
	public Object clone() {
		Playlist newPlaylist = new Playlist();
		int counter = 0;
		try {
			for (SongRecord song:playlist) {
				newPlaylist.addSong(new SongRecord(song.getTitle(), song.getArtist(), song.getMinutes(), song.getSeconds()), counter);
				counter++;
			}
		} catch(FullPlaylistException e) {
			System.out.println(e.getMessage());
		}
		return newPlaylist;
	}
	
	/**
	 * This method is used to check if two playlists are equal
	 * 
	 * @param obj
	 * Playlist that is being compared
	 * 
	 * @return
	 * Returns a boolean showing if the two playlists being compared are equal
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Playlist) {
			int counter = 0;
			for (int i = 0; i < size; i++) {
				if(playlist[i] == ((Playlist)obj).getSong(i)) {
					counter++;
				}
			}
			if(counter == size) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method returns the size of the playlist.
	 * 
	 * @return
	 * Size of the playlist
	 */
	public int size() {
		return size;
	}
	
	public void addSong(SongRecord song, int position) throws IllegalArgumentException, FullPlaylistException {
		if(position < 1 || position > MAX_SONGS || position > size + 1) {
			throw new IllegalArgumentException("The position you entered is invalid. Please enter a position"
					+ " between 1 and " + (size + 1) + ".");
		}
		else if(size == MAX_SONGS) {
			throw new FullPlaylistException("Your playlist has reached the 50 song limit.");
		}
		else {
			size++;
			
			//Use to adjust array (array starts at 0, but user playlist starts at 1)
			position--;
			
			if(playlist[position] == null) {
				playlist[position] = song;
			}
			else {
				SongRecord temp = playlist[position];
				SongRecord temp1;
				playlist[position] = song;
				for (int i = position + 1; i < size; i++) {
					temp1 = playlist[i];
					playlist[i] = temp;
					temp = temp1;
				}
			}
		}
	}
	
	/**
	 * This method removes a song from the playlist
	 * 
	 * @param position
	 * Position of song that is going to be removed
	 * 
	 * @throws IllegalArgumentException
	 * Exception throw if position is below 1 or above 50 (out of range)
	 * 
	 * @throws NullPointerException
	 * Exception thrown if position does not contain a song (null)
	 */
	public void removeSong(int position) throws IllegalArgumentException, NullPointerException {
		if(position < 1 || position > 50) {
			throw new IllegalArgumentException("The position you entered is invalid. Please enter a position"
					+ " between 1 and " + size + ".");
		}
		else if(playlist[position] == null) {
			throw new NullPointerException("No song at position " + position + " to remove.");
		}
		else {
			//Adjust position for array syntax
			position--;
			SongRecord temp;
			SongRecord temp1;
			int i = size - 1;
			temp = playlist[i];
			playlist[i] = null;
			size--;
			while(i != position) {
				i--;
				temp1 = playlist[i];
				playlist[i] = temp;
				temp = temp1;
			}
		}
	}
	
	/**
	 * This method is used to retrieve a specific song from the playlist
	 * 
	 * @param position
	 * Position of song in the playlist
	 * 
	 * @return
	 * The desired song in the position entered
	 * 
	 * @throws IllegalArgumentException
	 * Exception thrown if position is below 1 or above 50 (out of range)
	 * 
	 * @throws NullPointerException
	 * Exception thrown if there is no song in the specified position
	 */
	public SongRecord getSong(int position) throws IllegalArgumentException, NullPointerException {
		if(position < 1 || position > 50) {
			throw new IllegalArgumentException("Please enter a number between 1 and " + size + ".");
		}
		else {
			//Position adjustment
			position--;
			if (playlist[position] == null) {
				throw new NullPointerException("There is no song in this position.");
			}
			else {
				return playlist[position];
			}
		}
	}
	
	/**
	 * This method prints the toString method to display all of the songs in the playlist
	 */
	public void printAllSongs() {
		System.out.println(toString());
	}
	
	/**
	 * This method prints only the songs from the specified artist in the playlist
	 * 
	 * @param originalList
	 * Playlist being searched for specified artist
	 * 
	 * @param artist
	 * Specified artist
	 * 
	 * @return
	 * Instance of a playlist with only the specified artist's songs
	 */
	public static Playlist getSongsByArtist(Playlist originalList, String artist) {
		Playlist artistPlaylist = new Playlist();
		int counter = 0;
		try {
			for(int i = 0; i < originalList.size(); i++) {
				if(originalList.getSong(i+1).getArtist().equals(artist)) {
					
					artistPlaylist.addSong(originalList.getSong(i+1), counter + 1);
					counter++;
				}
			}
		} catch(FullPlaylistException e) {
			System.out.println(e.getMessage());
		}
		return artistPlaylist;
	}
	
	/**
	 * This method is used to represent a playlist in string format
	 * 
	 * @return
	 * A properly formatted string representation of the playlist
	 */
	public String toString() {
		String playlistString = String.format("%-10s %-15s %-15s %-5s", "Song#", "Title", "Artist", "Length");
		playlistString += "\n------------------------------------------------";
		for(int i = 0; i < size; i++) {
			playlistString += "\n";
			if (playlist[i].getSeconds() < 10) {
				playlistString += String.format("%-10s %-15s %-15s %-5s", i + 1, playlist[i].getTitle(), playlist[i].getArtist(), playlist[i].getMinutes() + ":0" + playlist[i].getSeconds());
			}
			else {
				playlistString += String.format("%-10s %-15s %-15s %-5s", i + 1, playlist[i].getTitle(), playlist[i].getArtist(), playlist[i].getMinutes() + ":" + playlist[i].getSeconds());
			}
		}
		return playlistString;
	}
}
