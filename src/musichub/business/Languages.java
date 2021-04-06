/*
 * Name of enumeration : Languages
 *
 * Description   : Enumerations which manages the languages of audio books
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */


package business;

public enum Languages{

	/**
	*	Possible languages for audiobooks
	*/
	FRENCH("French"), ENGLISH("English"), ITALIAN("Italian"), SPANISH("Spanish"), GERMAN("German"), KOREAN("Korean"), CHINESE("Chinese"), RUSSIAN("Russian");

	private String language;	// language of the audiobook

	/**
	*	Builder (new category)
	*/
	private Languages(String language) {
		this.language = language;
	}

	/**
	*	Method that gets the language of the audiobook
	*  @return The language of the audiobook
	*/
	public String getLanguage() {
		return language;
	}

}
