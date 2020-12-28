/*
 * Name of enumeration : Languages
 *
 * Description   : Enumerations which manages the languages of audio books
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */


package musichub.business;

public enum Languages{

	FRENCH("French"), ENGLISH("English"), ITALIAN("Italian"), SPANISH("Spanish"), GERMAN("German"), KOREAN("Korean"), CHINESE("Chinese"), RUSSIAN("Russian");

	private String language;

	private Languages(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

}
