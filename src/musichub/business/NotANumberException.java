/*
* Nom de l'exception : NotANumberException
*
* Description   : Exception which manages the numerical choices of the MenuSelection class
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package business;

// Packages from java
import java.lang.Exception;


public class NotANumberException extends Exception {

	/**
	* Exception showing number error handling mechanism for the MenuSelection class
	*/
	public NotANumberException() {
		System.out.printf("\n\nPlease, enter a number between 0 and 11.");
	}
}
