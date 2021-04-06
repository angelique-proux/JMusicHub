/*
 * Name of enumeration : Genres
 *
 * Description   : Enumeration which manages the genres of songs
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */

package business;

public enum Genres implements InterfaceGenre {

	/**
	*	Possible genre for songs
	*/
	JAZZ("Jazz"), CLASSIC("Classic"), HIPHOP("Hip-Hop"), ROCK("Rock"), POP("Pop"), RAP("Rap"), KPOP("K-pop");

	private String genre;	// Genre of the song

	/**
	*	Builder (new genre)
	*/
	private Genres(String genre) {
		this.genre = genre;
	}

	/**
	*	Method that gets the genre of the song
	*  @return The genre of the song
	*/
	public String getGenre() {
		return genre;
	}

}
