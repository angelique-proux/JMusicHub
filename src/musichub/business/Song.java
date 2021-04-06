/*
* Name of class : Song
*
* Description   : Class which manages the songs
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package business;

// Packages from java
import java.io.*;
import javax.sound.sampled.*;
import java.util.UUID;
import java.lang.String;


public class Song extends Media implements InterfaceGenre {

	private String artist;  // Artist of the song
	private Genres genre;  	// Genre of the song


	/**
	*  Builder 1 (existing song)
	*/
	public Song(String title, String artist, int duration, String contents, Genres genre, String id) {

		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.id = UUID.fromString(id);
		this.contents = contents;
		this.genre = genre;
	}

	/**
	* Builder 2 (new song)
	*/
	public Song(String title, String artist, int duration, String contents, Genres genre) {
		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.id = UUID.randomUUID();
		this.contents = contents;
		this.genre = genre;
	}


	/**
	* Method that returns the genre of the song
  *  @return The genre of the song
	*/
	public String getGenre() {
		return this.genre.getGenre();
	}

	/**
	* Method that returns the genre of the song for xml files
  *  @return The genre of the song writen for xml files
	*/
	public String getGenreForXML() {
		switch (this.genre.getGenre()) {
			case "Hip-Hop":
			return "HIPHOP";
			case "K-pop":
			return "KPOP";
			default:
			return this.genre.getGenre().toUpperCase();
		}
	}

	/**
	* Method that returns the artist of the song
  *  @return The artist of the song
	*/
	public String getArtist() {
		return this.artist;
	}

	/**
	* Method that returns the song informations
  *  @return The informations of the song
	*/
	public String toString() {
		String show = "\n\t-> Title : " + this.title + "\t Artist : " + this.artist + "\n\tDuration : " + this.duration + "s" + "\t\t ID : " + this.id.hashCode() + "\n\tContents : " + this.contents + "\t Genre : " + this.genre.getGenre();

		return show;
	}

}
