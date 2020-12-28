/*
* Nom de l'exception : NotANumberException
*
* Description   : Exception qui gère les choix numériques de la class MenuSelection
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.lang.Exception;

public class NotANumberException extends Exception{

	public NotANumberException() {
		System.out.printf("\n\nPlease, enter a number between 0 and 11.");
	}
}
