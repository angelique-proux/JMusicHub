/*
* Nom de l'exception : NotANumberException
*
* Description   : Exception when an album does not exist in the list
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotANumberException extends Exception{

       /**
       * Exception showing number error handling mechanism for album
       */
	
	public NotANumberException() {
		System.out.printf("\n\nPlease, enter a number between 0 and 11.");
	}
}
