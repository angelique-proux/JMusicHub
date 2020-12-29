/*
* Name of exception : NotACategoryException
*
* Description   : Exception that manages audiobook categories
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotACategoryException extends Exception{
	
       /**
       * Exception showing category error handling mechanism for audiobooks
       */
	
	public NotACategoryException(String titleSong) {
		System.out.println("False category in xml. Please change the category of " + titleSong + ".");
	}
}
