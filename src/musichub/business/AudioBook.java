
/*
* Name of class : AudioBooks
*
* Description   : Class which manages the audio books
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


public class AudioBook extends Media{

	private String author;	     // Author of audio books
	private Languages language;  // Language of audio books
	private Categories category; // Category of audio books

	/**
	* Builder 1 (existing audiobook)
	*/
	public AudioBook(String title, String author, int duration, String contents, Languages language, Categories category, String id) {

		this.title = title;
		this.author = author;
		this.duration = duration;
		this.id = UUID.fromString(id);
		this.contents = contents;
		this.language = language;
		this.category = category;

	}

	/**
	* Builder 2 (new audiobook)
	*/
	public AudioBook(String title, String author, int duration, String contents, Languages language, Categories category) {

		this.title = title;
		this.author = author;
		this.duration = duration;
		this.id = UUID.randomUUID();
		this.contents = contents;
		this.language = language;
		this.category = category;

	}


	/**
	* Method that returns the author
	*  @return The author of the audiobook
	*/
	public String getAuthor() {
		return this.author;
	}

	/**
	* Method that returns the audiobook category
	*  @return The category of the audiobook
	*/
	public String getCategory() {
		return this.category.getCategory();
	}

	/**
	* Method that returns the audiobook language
	*  @return The language of the audiobook
	*/
	public String getLanguage() {
		return this.language.getLanguage();
	}

	/**
	* Method that displays informations about the audiobook
	*  @return The audiobook
	*/
	public String toString() {
		String show = "\n\t->Title : " + this.title + "\t Author : " + this.author + "\n\tDuration : " + this.duration + "s" + "\t\t ID : " + this.id.hashCode() + "\n\tContents : " + this.contents + "\tLanguage : " + this.language.getLanguage() + "\n\tCategory : " + this.category.getCategory();

		return show;
	}

}
