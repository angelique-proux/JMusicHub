/*
* Name of class : Album
*
* Description   : Class which manages the albums
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/



package business;

// Packages from java
import java.io.*;
import java.util.UUID;
import java.util.LinkedList;



public class Album implements InterfaceTitle, InterfaceString, InterfaceDuration {

  private String title;                    // Title of the album
  private String artist;                   // Artist of the album
  private int duration;                    // Duration of the album
  private int date;                        // Date of the album
  private LinkedList<Song> songList;       // Songs list of the album
  UUID idAlbum;                            // Identify key of the album


  /**
  * Builder 1 (existing album)
  * @see getDuration
  */
  public Album(String title, int date, int duration, LinkedList<Song> songs, String artist, String id) {
    this.title = title;
    this.artist = artist;
    this.duration = duration;
    this.date = date;
    this.songList = new LinkedList<Song>();
    for(Song s : songs)
    {
      this.songList.add(s);
    }
    this.duration = getDuration();
    this.idAlbum = UUID.fromString(id);
  }

  /**
  * Builder 2 (new album)
  */
  public Album(String title, int date, LinkedList<Song> songs, String artist) {
    this.title = title;
    this.artist = artist;
    this.date = date;
    this.songList = new LinkedList<Song>();
    for(Song s : songs)
    {
      this.songList.add(s);
    }
    this.duration = getDuration();
    this.idAlbum = UUID.randomUUID();
  }



  /**
  * Method that gets the title of the album
  * @return The title of the album
  */
  public String getTitle () {
    return title;
  }


  /**
  * Method that gets the artist of the album
  * @return The artist of the album
  */
  public String getArtist() {
    return artist;
  }


  /**
  * Method that gets the duration of the album
  * @return The duration of the album
  * @see setDuration
  */
  public int getDuration() {
    this.setDuration();
    return this.duration;
  }

  /**
  * Method that changes the duration of the album
  * By adding the songs duration
  */
  public void setDuration() {
    int val = 0;
    for (Song s : this.songList) {
      val = val + s.getDuration();
    }
    this.duration = val;
  }

  /**
  * Method that gets the date of the album
  * @return The date of the album
  */
  public int getDate() {
    return date;
  }

  /**
  * Method that gets the identify key of the album
  * @return The identify key of the album
  */
    public String getId() {
  		return this.idAlbum.toString();
  	}

  /**
  * Method that adds a song to the album
  * @param song
  *              The new song to add
  */
  public void addSong(Song song) {
    this.songList.add(song);
  }



  /**
  * Method that shows the informations and the songs of the album
  * The duration is setting before that
  * @see setDuration
  */
  public void showAlbum() {
    setDuration();
    System.out.println(toString());
  }

  /**
  * Method that shows the informations of the album
  * @see getDuration
  */
  public void showAlbumInformations() {
    System.out.println("\nTitle : " + this.title + "\tArtist : " + this.artist
    + "\nDuration : " + this.getDuration() + "\tDate : " + this.date + "\nId : "
    + this.idAlbum.toString());
  }

  /**
  * Method that shows the songs of the album
  */
  public void showSongList() {
    String string = "";
    int numSong = 1;
    for(Song s : songList) {
      string = string + "\n\tSong " + numSong + " :" + s.toString() ;
      numSong++;
    }
    if (songList.size()==0) {
      string = "\n\tThere is no songs in this album.";
    }
    System.out.println(string);
  }

  /**
  * Method that returns the informations of the album
  * @return The album
  */
  public String toString() {
		String infos = "\nTitle : " + this.title + "\tArtist : " + this.artist
    + "\tDuration : " + this.duration + "\nDate : " + this.date + "\tId : "
    + this.idAlbum.toString() + "\nSongs :";
		for (Song s : songList) {
			infos += "\t" + s.toString();
		}
    if (songList.size()==0) {
      infos += "There is no songs in this album";
    }
		return infos;
	}

  /**
  * Method that returns the list of the songs of the album
  * @return The songs list of the album
  */
  public LinkedList<Song> getSongsList() {
    return songList;
  }

}
