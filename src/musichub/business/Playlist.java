/*
 * Name of class : Playlist
 *
 * Description   : Class which manages playlists
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */


package business;


// Packages from java
import java.util.LinkedList;
import java.util.*;
import java.time.Duration;


public class Playlist implements InterfaceString {
	private String name;															// Name of the playlist
	private UUID id;																	// Identify key of the playlist
	private LinkedList<AudioBook> audiobooksList;			// AudioBooks list of the playlist
  private LinkedList<Song> songsList;								// Songs list of the playlist

	/**
  *  Builder 1 (existing playlist)
  */
	public Playlist(String name, UUID id, LinkedList<Song> songsList, LinkedList<AudioBook> audiobooksList) {
        this.name = name;
        this.id = id;
        this.songsList = songsList;
				this.audiobooksList = audiobooksList;
	}

	/**
  *  Builder 2 (new playlist)
  */
	public Playlist(String name, LinkedList<Song> songsList, LinkedList<AudioBook> audiobooksList) {
				this.name = name;
				this.id = UUID.randomUUID();
				this.songsList = songsList;
				this.audiobooksList = audiobooksList;
	}


	/**
	*  Method that returns the informations of the playlist
	*  @return The playlist
	*/
	public String toString() {
		String infos = "\nName : " + this.name + "\tId : " + this.id + "\nSongs :";
		for (Song s : songsList) {
			infos += "\t" + s.toString();
		}
		if (songsList.size()==0) {
			infos += "\n\tThere is no song in this playlist.";
		}
		infos += "\nAudioBooks :";
		for (AudioBook a : audiobooksList) {
			infos += "\t" + a.toString();
		}
		if (audiobooksList.size()==0) {
			infos += "\n\tThere is no audiobook in this playlist.";
		}
		return infos;
	}

	/**
  *  Method that gets the name of the playlist
  *  @return The name of the playlist
  */
	public String getName() {
	    return this.name;
	}

	/**
  *  Method that gets the identify key of the playlist
  *  @return The identify key of the playlist
  */
	public String getId() {
		return this.id.toString();
	}

	/**
  *  Method that gets the songs list of the playlist
  *  @return The songs list of the playlist
  */
	public LinkedList<Song> getSongsList() {
		return songsList;
	}

	/**
  *  Method that gets the audiobooks list of the playlist
  *  @return The audiobooks list of the playlist
  */
	public LinkedList<AudioBook> getAudioBooksList() {
		return audiobooksList;
	}


	/**
	*  Method that shows the songs of the playlist
	*/
  public void showSongList() {
    String string = "";
    int numSong = 1;
    for(Song s : songsList) {
      string = string + "\n\tSong " + numSong + " :" + s.toString() ;
      numSong++;
    }
    if (songsList.size()==0) {
      string = "\n\tThere is no songs in this playlist.";
    }
    System.out.println(string);
  }

	/**
	*  Method that shows the audiobooks of the playlist
	*/
  public void showAudioBookList() {
    String string = "";
    int numBook = 1;
    for(AudioBook a : audiobooksList) {
      string = string + "\n\tAudioBook " + numBook + " :" + a.toString() ;
      numBook++;
    }
    if (audiobooksList.size()==0) {
      string = "\n\tThere is no audiobook in this playlist.";
    }
    System.out.println(string);
  }

}
