/*
 * Name of enumeration : Categories
 *
 * Description   : Enumeration which manages the categories of audio books
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */



package business;

public enum Categories{

	/**
	*	Possible categories for audiobooks
	*/
	YOUTH("Youth"), NOVEL("Novel"), THEATRE("Theatre"), SPEECH("Speech"), DOCUMENTARY("Documentary");

	private String category;	// Category of the audiobook

	/**
	*	Builder (new category)
	*/
	private Categories(String category) {
		this.category = category;
	}

	/**
	*	Method that gets the category of the audiobook
	*  @return The category of the audiobook
	*/
	public String getCategory() {
		return category;
	}

}
