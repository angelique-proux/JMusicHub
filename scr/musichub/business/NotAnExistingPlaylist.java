/*
* Nom de l'exception : NotAnExistingPlaylist
*
* Description   : Exception lorsqu'une playlist n'existe pas dans la liste
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotAnExistingPlaylist extends Exception{

	public NotAnExistingPlaylist() {
		System.out.printf("\n\nPlease, chose an excisting playlist.\n");
	}
}
