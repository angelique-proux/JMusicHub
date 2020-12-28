/*
* Name of class : Album
*
* Description   : Class which manages the albums
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/



package musichub.business;

import java.io.*;
import java.util.UUID;
import java.util.LinkedList;



public class Album implements InterfaceTitle, InterfaceString, InterfaceDuration {
  private String title;                    // title de l'album
  private String artist;                   // artiste de l'album
  private int duration;                    // durée totale de l'album
  private int date;                        // date de l'album
  private LinkedList<Song> songList;       // liste des chansons de l'album
  UUID idAlbum;                            // identifiant unique de l'album


  /**
  *  Constructeur 1 (album existant)
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
  *  Constructeur 2 (nouvel album)
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
  *  Méthode permettant d'obtenir le title de l'album
  */
  public String getTitle () {
    return title;
  }


  /**
  *  Méthode permettant d'obtenir l'artiste de l'album
  */
  public String getArtist() {
    return artist;
  }


  /**
  *  Méthode permettant d'obtenir la durée de l'album
  */
  public int getDuration() {
    this.setDuration();
    return this.duration;
  }

  /**
  *  Méthode permettant de changer la durée de l'album
  *  En additionnant celle des chansons qu'il contient
  */
  public void setDuration() {
    int val = 0;
    for (Song s : this.songList) {
      val = val + s.getDuration();
    }
    this.duration = val;
  }

  /**
  *  Méthode permettant d'obtenir la date de l'album
  */
  public int getDate() {
    return date;
  }

  /**
  *  Méthode permettant d'obtenir la clé d'identification de l'album
  */
    public String getId() {
  		return this.idAlbum.toString();
  	}

  /**
  *  Méthode permettant d'ajouter une chanson à un album
  */
  public void addSong(Song song) {
    this.songList.add(song);
  }



  /**
  *  Méthode permettant d'afficher les informations et les chansons de l'album
  *  Avant l'affichage, la durée réelle de l'album est calculée
  */
  public void showAlbum() {
    setDuration();
    System.out.println(toString());
  }

  /**
  *  Méthode permettant d'afficher les informations de l'album
  */
  public void showAlbumInformations() {
    System.out.println("\nTitle : " + this.title + "\tArtist : " + this.artist
    + "\nDuration : " + this.getDuration() + "\tDate : " + this.date + "\nId : "
    + this.idAlbum.toString());
  }

  /**
  *  Méthode permettant d'obtenir la liste des chansons de l'album
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
  *  Méthode permettant d'obtenir les informations et les chansons de l'album
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
  *  Méthode permettant d'obtenir les chansons de l'album
  */
  public LinkedList<Song> getSongsList() {
    return songList;
  }

}
