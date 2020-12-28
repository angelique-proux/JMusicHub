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
	//attributs

	public Song(String title, String artist, int duration, String contents, Genres genre, String id) {

		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.id = UUID.fromString(id);
		this.contents = contents;
		this.genre = genre;

	} //constructeur 1 (chanson existante)

	public Song(String title, String artist, int duration, String contents, Genres genre) {

		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.id = UUID.randomUUID();
		this.contents = contents;
		this.genre = genre;

	} //constructeur 2 (nouvelle chanson)


	public String getGenre() {
		return this.genre.getGenre();
	} // méthode 7 : obtention du genre

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

	public String getArtist() {
		return this.artist;
	}

	public String toString() {
		String show = "\n\t-> Title : " + this.title + "\t Artist : " + this.artist + "\n\tDuration : " + this.duration + "s" + "\t\t ID : " + this.id.hashCode() + "\n\tContents : " + this.contents + "\t Genre : " + this.genre.getGenre();

		return show;
	}// méthode 5 : affichage des informations concernant la chanson

}
