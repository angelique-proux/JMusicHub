/*
* Name of class : AudioBooks
*
* Description   : Class which manages the audio books
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/



package musichub.business;

import java.io.*;
import javax.sound.sampled.*;
import java.util.UUID;


public class AudioBook extends Media{

	private String author;
	private Languages language;
	private Categories category;
	//attributes

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


	public String getAuthor() {
		return this.author;
	}//Method that returns from author
	
	public String getCategory() {
		return this.category.getCategory();
	}//Method return audiobook category

	public String getLanguage() {
		return this.language.getLanguage();
	}//Method return the language of audiobooks

	public String toString() {
		String show = "\n\t->Title : " + this.title + "\t Author : " + this.author + "\n\tDuration : " + this.duration + "s" + "\t\t ID : " + this.id.hashCode() + "\n\tContents : " + this.contents + "\tLanguage : " + this.language.getLanguage() + "\n\tCategory : " + this.category.getCategory();

		return show;
	}//Method that displays information about the audiobook

}
