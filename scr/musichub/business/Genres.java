/*
 * Name of enumeration : Genres
 *
 * Description   : Enumeration which manages the genres of songs
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */

package musichub.business;

public enum Genres implements InterfaceGenre {

	JAZZ("Jazz"), CLASSIC("Classic"), HIPHOP("Hip-Hop"), ROCK("Rock"), POP("Pop"), RAP("Rap"), KPOP("K-pop");

	private String genre;

	private Genres(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

}
