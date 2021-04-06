/*
* Name of exception : NotAnExistingAlbum
*
* Description   : Exception when an album does not exist in the list
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package business;

// Packages from java
import java.lang.Exception;


public class NotAnExistingAlbum extends Exception {

	/**
	* Exception showing album error handling mechanism
	*/
	public NotAnExistingAlbum() {
		System.out.printf("\n\nPlease, chose an excisting album.\n");
	}
}
