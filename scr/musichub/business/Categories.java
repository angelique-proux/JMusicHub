/*
 * Name of enumeration : Categories
 *
 * Description   : Enumeration which manages the categories of audio books
 *
 * Date          : 03/01/2021
 *
 * Copyright     : Manelle & Angélique
 */



package musichub.business;

public enum Categories{

	YOUTH("Youth"), NOVEL("Novel"), THEATRE("Theatre"), SPEECH("Speech"), DOCUMENTARY("Documentary");

	private String category;

	private Categories(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}
	
}
