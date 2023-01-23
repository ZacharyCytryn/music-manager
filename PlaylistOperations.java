/**
 * Zachary Cytryn
 * ID: 114283379
 * Email: zachary.cytryn@stonybrook.edu
 * Homework #1
 * CSE 214 Recitation 30
 */

package musicManager;
import java.util.Scanner;
/**
 * This class contains the main method, where the user inputs information to create a playlist
 * 
 * @author zacharycytryn
 */
public class PlaylistOperations {

	/**
	 * This is the main method. It lists the choices that the user can choose and utilizes methods in other
	 * classes to create a playlist based on the user's inputs.
	 * 
	 * @param args
	 * Command line arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Playlist playlist = new Playlist();
		System.out.println("Welcome to the Music Manager!\n");
		String choice = "";
		while(!choice.toUpperCase().equals("Q")) {
			System.out.println("\nMenu:"
					+ "\nA) Add Song"
					+ "\nB) Print Songs by Artist"
					+ "\nG) Get Song"
					+ "\nR) Remove Song"
					+ "\nP) Print All Songs"
					+ "\nS) Size"
					+ "\nQ) Quit");
			System.out.println("\nSelect a Menu Option: ");
			choice = sc.nextLine();
			System.out.println();
			switch(choice.toUpperCase()) {
				case "A":
					System.out.println("Enter the song title: ");
					String title = sc.nextLine();
					System.out.println("Enter the song artist: ");
					String artist = sc.nextLine();
					System.out.println("Enter the song length (minutes): ");
					String minutes = sc.nextLine();
					int numMinutes = Integer.parseInt(minutes);
					System.out.println("Enter the song length (seconds): ");
					String seconds = sc.nextLine();
					int numSeconds = Integer.parseInt(seconds);
					System.out.println("Enter the position: ");
					String position = sc.nextLine();
					int numPosition = Integer.parseInt(position);
					try {
						playlist.addSong(new SongRecord(title, artist, numMinutes, numSeconds), numPosition);
						System.out.println("\nSong added: " + title + " by " + artist);
					} catch (FullPlaylistException e) {
						System.out.println(e.getMessage());
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case "B":
					System.out.println("Enter the artist: ");
					String printedArtist = sc.nextLine();
					System.out.println();
					Playlist.getSongsByArtist(playlist, printedArtist).printAllSongs();
					break;
				
				case "G":
					System.out.println("Enter the position: ");
					String getPos = sc.nextLine();
					int numGetPos = Integer.parseInt(getPos);
					try {
						System.out.println(playlist.getSong(numGetPos).toString(numGetPos));
					} catch (NullPointerException e) {
						System.out.println(e.getMessage());
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case "R":
					System.out.println("Enter the position: ");
					String removePos = sc.nextLine();
					int numRemovePos = Integer.parseInt(removePos);
					try {
						playlist.removeSong(numRemovePos);
						System.out.println("Song removed at position " + numRemovePos);
					} catch (NullPointerException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case "P":
					playlist.printAllSongs();
					break;
					
				case "S":
					System.out.println("There are " + playlist.size() + " song(s) in the current playlist.");
					break;
					
				case "Q":
					System.out.println("Program terminating normally...");
					break;
					
				default:
					System.out.println("Please enter a proper input.");
					break;
			}
		}
		sc.close();
	}
}