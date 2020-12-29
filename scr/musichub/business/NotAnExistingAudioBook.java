/*
* Name of exception : NotAnExistingAudioBook
*
* Description   : Exception when an audiobook does not exist in the list
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotAnExistingAudioBook extends Exception{
	
       /**
       * Exception showing audioBook error handling mechanism 
       */

	public NotAnExistingAudioBook() {
		System.out.printf("\n\nPlease, chose an excisting audiobook.\n");
	}
}
