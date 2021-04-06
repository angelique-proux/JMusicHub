/*
* Name of exception : NotAnExistingPlaylist
*
* Description   : Exception when a playlist does not exist in the list
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package business;

// Packages from java
import java.lang.Exception;


public class NotAnExistingPlaylist extends Exception {

	/**
	* Exception showing playlist error handling mechanism
	*/
	public NotAnExistingPlaylist() {
		System.out.printf("\n\nPlease, chose an excisting playlist.\n");
	}
}
