/*
* Nom de l'exception : NotAnExistingAlbum
*
* Description   : Exception lorsqu'un album n'existe pas dans la liste
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotAnExistingAlbum extends Exception{

	public NotAnExistingAlbum() {
		System.out.printf("\n\nPlease, chose an excisting album.\n");
	}
}
