/*
 * Name of class : Song
 *
 * Description   : Class which manages the songs
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */


package musichub.business;

import java.io.*;
import javax.sound.sampled.*;
import java.util.UUID;

import java.lang.String;

public class Song extends Media implements InterfaceGenre {

	private String artist;
	private Genres genre;
	//Attributes

	public Song(String title, String artist, int duration, String contents, Genres genre, String id) {

		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.id = UUID.fromString(id);
		this.contents = contents;
		this.genre = genre;

	} //builder 1 (existing song)

	public Song(String title, String artist, int duration, String contents, Genres genre) {

		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.id = UUID.randomUUID();
		this.contents = contents;
		this.genre = genre;

	} //builder 2 (new song)


	public String getGenre() {
		return this.genre.getGenre();
	} //Method return the genre of songs

	public String getGenreForXML() {
		switch (this.genre.getGenre()) {
			case "Hip-Hop":
			return "HIPHOP";
			case "K-pop":
			return "KPOP";
			default:
			return this.genre.getGenre().toUpperCase();
		}
	} //Method to have the genre of a sound in xml

	public String getArtist() {
		return this.artist;
	} //Method for obtaining the artist of the album

	public String toString() {
		String show = "\n\t-> Title : " + this.title + "\t Artist : " + this.artist + "\n\tDuration : " + this.duration + "s" + "\t\t ID : " + this.id.hashCode() + "\n\tContents : " + this.contents + "\t Genre : " + this.genre.getGenre();

		return show;
	} //Method to display song information

}
