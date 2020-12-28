/*
* Nom de l'exception : NotAGenreException
*
* Description   : Exception qui gère les genres des chansons
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotAGenreException extends Exception{

	public NotAGenreException(String titleSong) {
		System.out.println("False genre in xml. Please change the genre of " + titleSong + ".");
	}
}
