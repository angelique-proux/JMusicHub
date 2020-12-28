/*
* Nom de l'exception : NotAnExistingAudioBook
*
* Description   : Exception lorsqu'un livre audio n'existe pas dans la liste
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotAnExistingAudioBook extends Exception{

	public NotAnExistingAudioBook() {
		System.out.printf("\n\nPlease, chose an excisting audiobook.\n");
	}
}
