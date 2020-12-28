/*
* Nom de l'exception : NotAnExistingSong
*
* Description   : Exception lorsqu'une musique n'existe pas dans la liste
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotAnExistingSong extends Exception{

	public NotAnExistingSong() {
		System.out.printf("\n\nPlease, chose an excisting song.\n");
	}
}
