/*
* Name of class : Medias
*
* Description   : Class abstract which groups songs and audio books
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.util.*;
import java.util.UUID;


public abstract class Media implements InterfaceTitle, InterfaceString, InterfaceDuration {
	protected String title;    // Title of sound and audiobooks
	protected int duration;    // Total duration of sound and audiobooks
	protected UUID id;	   // Sound and audiobooks identifier
	protected String contents; // Content of sound and audiobooks

	public abstract String toString(); // Returns a string describing this abstract method
	
	 /**
  	   * Method returns title of sound and audiobooks
         */
	
	public String getTitle() {
		return this.title;
	} 

	 /**
  	   * Method for obtaining content from sounds and audio books
         */
	
	public String getContent() {
		return this.contents;
	}  

	 /**
  	   * Method for obtaining the sound and audiobooks identifier
         */
	
	public String getId() {
		return this.id.toString();
	} 
	
	 /**
  	   * Method for obtaining the duration of sound and audiobooks
         */
	
	public int getDuration() {
		return this.duration;
	} 

}
