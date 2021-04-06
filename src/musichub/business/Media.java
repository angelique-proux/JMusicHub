/*
* Name of class : Medias
*
* Description   : Class abstract which groups songs and audio books
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package business;

// Packages from java
import java.util.*;
import java.util.UUID;


public abstract class Media implements InterfaceTitle, InterfaceString, InterfaceDuration {
	protected String title;    // Title of songs and audiobooks
	protected int duration;    // Total duration of songs and audiobooks
	protected UUID id;	   // Songs and audiobooks identify key
	protected String contents; // Content of songs and audiobooks

	public abstract String toString(); // Returns a string describing this abstract method

	/**
	* Method returns title of songs and audiobooks
	*  @return The title
	*/
	public String getTitle() {
		return this.title;
	}

	/**
	* Method for obtaining content from songs and audiobooks
	*  @return The content
	*/
	public String getContent() {
		return this.contents;
	}

	/**
	* Method for obtaining the songs and audiobooks identifier
	*  @return The identify key
	*/
	public String getId() {
		return this.id.toString();
	}

	/**
	* Method for obtaining the duration of songs and audiobooks
	*  @return The duration
	*/
	public int getDuration() {
		return this.duration;
	}

}
