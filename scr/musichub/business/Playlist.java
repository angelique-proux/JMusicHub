/*
 * Name of class : Playlist
 *
 * Description   : Class which manages playlists
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */


package musichub.business;


import java.util.LinkedList;

import java.util.*;
import java.time.Duration;


public class Playlist implements InterfaceString {
	private String name;
	private UUID id;
	private LinkedList<AudioBook> audiobooksList;
  private LinkedList<Song> songsList;

	public Playlist(String name, UUID id, LinkedList<Song> songsList, LinkedList<AudioBook> audiobooksList) {
        this.name = name;
        this.id = id;
        this.songsList = songsList;
				this.audiobooksList = audiobooksList;
	} // constructeur

	public Playlist(String name, LinkedList<Song> songsList, LinkedList<AudioBook> audiobooksList) {
				this.name = name;
				this.id = UUID.randomUUID();
				this.songsList = songsList;
				this.audiobooksList = audiobooksList;
	} // constructeur 2

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

	public String getName() {
	    return this.name;
	}

	public String getId() {
		return this.id.toString();
	}

	public LinkedList<Song> getSongsList() {
		return songsList;
	}

	public LinkedList<AudioBook> getAudioBooksList() {
		return audiobooksList;
	}

	/**
  *  Méthode permettant d'obtenir la liste des chansons de la playlist
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
  *  Méthode permettant d'obtenir la liste des livres audio de la playlist
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
