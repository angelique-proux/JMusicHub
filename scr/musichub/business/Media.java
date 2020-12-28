/*
* Name of class : Medias
*
* Description   : Class abstract which groups songs and audio books
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.business;

import java.util.*;
import java.util.UUID;


public abstract class Media implements InterfaceTitle, InterfaceString, InterfaceDuration {
	protected String title;
	protected int duration;
	protected UUID id;
	protected String contents;

	public abstract String toString();

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return this.contents;
	}

	public String getId() {
		return this.id.toString();
	}

	public int getDuration() {
		return this.duration;
	}

}
