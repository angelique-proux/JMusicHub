/*
* Name of exception : NotALanguageException
*
* Description   : Exception that manages audiobook languages
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotALanguageException extends Exception{

       /**
       * Exception showing language error handling mechanism for audiobooks
       */
	
	public NotALanguageException(String titleSong) {
		System.out.println("False language in xml. Please change the language of " + titleSong + ".");
	}
}
