/*
* Name of exception : NotAnExistingSong
*
* Description   : Exception when a music does not exist in the list
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotAnExistingSong extends Exception{

       /**
       * Exception showing song error handling mechanism for music
       */
	
	public NotAnExistingSong() {
		System.out.printf("\n\nPlease, chose an excisting song.\n");
	}
}
