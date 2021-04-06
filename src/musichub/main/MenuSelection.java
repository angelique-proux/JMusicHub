/*
* Name of class : MenuSelection
*
* Description   : Class which manages the execution of the application at the user side
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/

package main;

//our packages
import business.*;
import util.*;


// Packages from java
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Collections;


public class MenuSelection {

	/**
	*	Method that runs the programme
	* @see Util
	* @see XmlFile
	*/
	public static void main(String[] args) {
		int userSelected = -1;
		System.out.println("\n\n\n\t\t  Welcome on this new application.");
		System.out.println("\t\t  It's a pleasure to meet you. \n");

		// initialising the Util by browsing through the three xml.
		XmlFile xmlfile = new XmlFile();
		Util util = new Util();
		util.fileTheAudioBooksList(xmlfile);
		util.fileTheSongsList(xmlfile);
		util.fileTheAlbumsList(xmlfile);
		util.fileThePlaylistsList(xmlfile);

		Scanner sc = new Scanner (System.in);
		try {
			userSelected = menuData(sc);
		} catch (NotANumberException exceptNumb) {
		}
		do	{
			System.out.println("\n\n\n");
			switch (userSelected) {
				case 0 : // Show xml files
				System.out.println("Please, choose the xml to see.");
				String choiceX;
				System.out.println("E : elements.xml\t\t|\tA : albums.xml");
				System.out.println("P : playlists.xml\t\t|\tM : The menu");
				choiceX = sc.nextLine();
				switch (choiceX) {
					case "E":
					util.fileTheAudioBooksList(xmlfile);
					util.fileTheSongsList(xmlfile);
					xmlfile.showXML("m");
					break;
					case "A":
					util.fileTheAlbumsList(xmlfile);
					xmlfile.showXML("a");
					break;
					case "P":
					util.fileThePlaylistsList(xmlfile);
					xmlfile.showXML("p");
					break;
					case "M":
					break;
				}
				break;

				case 1 : // Show albums
				System.out.println("\t\t Album titles sorted by them date:");
				System.out.println("Date:\t    Title:");
				util.showAlbumsNamesOrdered("yes");
				break;

				case 2 : // Show songs
				System.out.println("\t\t Song titles sorted by them genre:");
				System.out.println("Genre:\t    Title:");
				util.showSongsNamesOrdered("yes");
				break;

				case 3 : // Show audiobooks
				System.out.println("\t\t AudioBook titles sorted by them author:");
				System.out.println("Author:\t\t\t    Title:");
				util.showAudioBooksNamesOrdered("yes");
				break;

				case 4 : // Show playlists
				System.out.println("\t\t Playlist names sorted by alphabetical order:");
				System.out.println("Names:");
				util.showPlaylistsNamesOrdered("yes");
				break;

				case 5 : // Select an album
				selectAlbum5(util, sc);
				break;

				case 6 : // Select a playlist
				selectPlaylist6(util, sc);
				break;

				case 7 : // Select all the song of an artist
				selectArtist7(util, sc);
				break;

				case 8 : // Select all the song of an author
				selectAuthor8(util, sc);
				break;

				case 9 : // Change the content of the application
				changeContent9(util, sc, xmlfile);
				break;

				case 10 : // Listen to music
				chooseTheMusicToListen(util, sc);
				break;

				case 11 :// Quit the application
				System.out.println("\t\t Thank you for you time, have a nice day!\n");
				System.out.println("\t\t\t\t\tSigned by Manelle and Angélique.\n\n\n");
				System.exit(0);
				break;
			}
			System.out.println("\n\n\n");
			try {
				userSelected = menuData(sc);
			} catch (NotANumberException exceptNumb) {
				userSelected = -1;
			}
		} while (userSelected <= 11);
		sc.close();
	}



	// Methods needed for the programme

	/**
	*	Method that enables to make a choice in the menu
	* @param sc
	*						Scanner
	* @throws NotANumberException
	*						If the parameter entered by the user is not a number between 0 and 12
	* @return A number needed to choose an option
	*/
	public static int menuData(Scanner sc) throws NotANumberException {
		String selection;
		int mySelection=-1;
		System.out.println("Choose an option:");
		System.out.println("0) Show the xml files\t\t|\t6) Select a playlist");
		System.out.println("1) Show album titles\t\t|\t7) Select an artist");
		System.out.println("2) Show song titles\t\t|\t8) Select an author");
		System.out.println("3) Show audiobook titles\t|\t9) Change the content of the application");
		System.out.println("4) Show playlist names\t\t|\t10) Listen to music");
		System.out.println("5) Select an album\t\t|\t11) Quit the application");

		selection = sc.nextLine();
		for (int i=0; i<12; i++) {
			if (selection.equals(String.valueOf(i))) {
				mySelection=Integer.parseInt(selection);
			}
		} if (mySelection==-1) {
			throw new NotANumberException();
		}
		return mySelection;

	}

	/**
	*	Method that selects an album to see its content
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void selectAlbum5(Util util, Scanner sc) {
		String albumToSee;
		int thisIndexToSee;
		System.out.println(" Which album do you want to select ?");
		util.showAlbumsNamesOrdered("no");
		System.out.println(" Please, write its title.");
		// Verify if the album exists
		do {
			albumToSee = sc.nextLine();
			try {
				thisIndexToSee = util.getAlbumIndex(albumToSee);
			}
			catch (NotAnExistingAlbum NotAlbum) {
				thisIndexToSee = -1;
			}
		} while (thisIndexToSee == -1);
		int choice;
		System.out.println(" What do you want to see ?");
		System.out.println("1) Album's songs\t\t|\t3) Both, songs and informations");
		System.out.println("2) Album's informations\t\t|\t4) The menu");
		choice  = Integer.parseInt(sc.nextLine());
		switch (choice) {
			case 1:
			System.out.println("\n Here is the songs of the album " + albumToSee + ":");
			util.getAlbumsListForXml().get(thisIndexToSee).showSongList();
			break;
			case 2:
			System.out.println("\n Here is the informations of the album " + albumToSee + ":");
			util.getAlbumsListForXml().get(thisIndexToSee).showAlbumInformations();
			break;
			case 3:
			System.out.println("\n Here is the album " + albumToSee + ":");
			System.out.println(util.getAlbumsListForXml().get(thisIndexToSee).toString());
			break;
			default:
			System.out.println("\n You will be redirected to the menu.");
			break;
		}
	}

	/**
	*	Method that selects a playlist to see its content
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void selectPlaylist6(Util util, Scanner sc) {
		String playlistToSee;
		int thisIndexPToSee;
		System.out.println(" Which playlist do you want to select ?");
		util.showPlaylistsNamesOrdered("no");
		System.out.println(" Please, write its name.");
		// Verify if the album exists
		do {
			playlistToSee = sc.nextLine();
			try {
				thisIndexPToSee = util.getPlaylistIndex(playlistToSee);
			}
			catch (NotAnExistingPlaylist NotPlaylist) {
				thisIndexPToSee = -1;
			}
		} while (thisIndexPToSee == -1);
		int choiceP;
		System.out.println(" What do you want to see ?");
		System.out.println("1) Playlist's songs\t\t|\t3) Songs, audiobooks and informations");
		System.out.println("2) Playlist's audiobooks\t|\t4) The menu");
		choiceP  = Integer.parseInt(sc.nextLine());
		switch (choiceP) {
			case 1:
			System.out.println("\n Here is the songs of the playlist " + playlistToSee + ":");
			util.getPlaylistsListForXml().get(thisIndexPToSee).showSongList();
			break;

			case 2:
			System.out.println("\n Here is the audiobooks of the playlist " + playlistToSee + ":");
			util.getPlaylistsListForXml().get(thisIndexPToSee).showAudioBookList();
			break;

			case 3:
			System.out.println("\n Here is the playlist " + playlistToSee + ":");
			System.out.println(util.getPlaylistsListForXml().get(thisIndexPToSee).toString());
			break;

			default:
			System.out.println("\n You will be redirected to the menu.");
			break;
		}
	}

	/**
	*	Method that selects an artist to see its songs
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void selectArtist7(Util util, Scanner sc) {
		LinkedList<String> artistList = new LinkedList<String>();
		artistList = util.getArtists();
		Collections.sort(artistList);
		System.out.println(" Which artist do you want to select ?");
		String thisArtist;
		System.out.println(artistList.toString());
		System.out.println(" Please, write its name.");
		thisArtist = sc.nextLine();
		System.out.println(" Here is the songs of " + thisArtist + ":");
		for (int i=0; i<util.getSongsListForXml().size(); i++) {
			Song songI = util.getSongsListForXml().get(i);
			if (songI.getArtist().compareTo(thisArtist)==0) {
				System.out.println(songI.toString());
			}
		}
	}

	/**
	*	Method that selects an author to see its audiobooks
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void selectAuthor8(Util util, Scanner sc) {
		LinkedList<String> authorList = new LinkedList<String>();
		authorList = util.getAuthors();
		Collections.sort(authorList);
		System.out.println(" Which author do you want to select ?");
		String thisAuthor;
		System.out.println(authorList.toString());
		System.out.println(" Please, write its name.");
		thisAuthor = sc.nextLine();
		System.out.println(" Here is the audiobooks of " + thisAuthor + ":");
		for (int i=0; i<util.getAudioBooksListForXml().size(); i++) {
			AudioBook authorI = util.getAudioBooksListForXml().get(i);
			if (authorI.getAuthor().compareTo(thisAuthor)==0) {
				System.out.println(authorI.toString());
			}
		}
	}


	/**
	*	Method that proposes to make changes in the application
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	* @param xmlfile
	*						List of xml files
	* @see addASongToTheSongsList
	* @see addAnAlbumToTheAlbumsList
	* @see addASongToAnAlbum
	* @see addAnAudioBookToTheAudioBooksList
	* @see createAPlaylist
	* @see deleteAPlaylist
	* @see saveApplicationInXml
	*/
	public static void changeContent9(Util util, Scanner sc, XmlFile xmlfile) {
		String decision;
		do {
			System.out.println(" What you want to do ? Hit a button to make changes.");
			System.out.println("h : show the help\t\t|\tm : return to the menu");
			System.out.println("Don't forget to save all your modifications.");
			decision = sc.nextLine();
			System.out.println("\n\n\n");
			switch (decision) {

				case "h":
				System.out.println("Here are the commands you can use to make changes:");
				System.out.println("c : add a song\t\t\t|\tl : add an audiobook");
				System.out.println("a : add an album\t\t|\tp : create a new playlist");
				System.out.println("+ : add a song to an album\t|\t- : delete a playlist");
				System.out.println("s : save all modifications");
				break;

				case "c":  // ajouter une chanson // fonctionne
				addASongToTheSongsList(util, sc);
				break;

				case "a":		// ajouter un album // fonctionne
				addAnAlbumToTheAlbumsList(util, sc);
				break;

				case "+":		//ajout d'une chanson existante à un album existant
				// fonctionne
				addASongToAnAlbum(util, sc);
				break;

				case "l":   // ajouter un livre audio // fonctionne
				addAnAudioBookToTheAudioBooksList(util, sc);
				break;

				case "p": // fonctionne
				createAPlaylist(util, sc);
				break;

				case "-": // fonctionne
				deleteAPlaylist(util, sc);
				break;

				case "s":
				saveApplicationInXml(xmlfile,util);
				break;
			}
			System.out.println("\n\n\n");
		} while (decision.compareTo("m")!=0);
	}

	/**
	*	Method that adds a song to the list of songs
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void addASongToTheSongsList(Util util, Scanner sc) {
		System.out.println("Which song do you want to add?");
		String titleSong;
		System.out.println(" Write the title of the song.");
		titleSong = sc.nextLine();
		String artistSong;
		System.out.println(" Write the artist.");
		artistSong = sc.nextLine();
		int durationSong=-1;
		System.out.println(" Write the duration.");
		do {
			try {
				durationSong = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException NotANumber) {
				System.out.println("Please enter a number.");
			}
		} while (durationSong==-1);
		String contentSong = "audio/";
		System.out.println(" Write the content. (yourFile.wav)");
		contentSong += sc.nextLine();
		String genreSong;
		System.out.println(" Write the genre. Please, choose in the following list: JAZZ, CLASSIC, HIPHOP, ROCK, POP, RAP, KPOP.");
		genreSong = sc.nextLine();
		Song newSong = new Song(titleSong, artistSong, durationSong, contentSong, Genres.valueOf(genreSong));
		util.addSongToTheList(newSong);
		System.out.println("\nThe song " + newSong.getTitle() + " has been added successfully.");
	}

	/**
	*	Method that adds an album to the list of albums, creates with existing songs
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void addAnAlbumToTheAlbumsList(Util util, Scanner sc) {
		System.out.println("Which album do you want to add?");
		String title;
		System.out.println(" Write the title of the album.");
		title = sc.nextLine();
		String artist;
		System.out.println(" Write the artist.");
		artist = sc.nextLine();
		int date=-1;
		System.out.println(" Write the date of the album.");
		do {
			try {
				date = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException NotANumber) {
				System.out.println("Please enter a year.");
			}
		} while (date==-1);
		int nbSongs=-1;
		System.out.println("\n Here are the songs:");
		util.showSongsNamesOrdered("no");
		System.out.println("\n How many songs do you want to put in your album?");
		do {
			try {
				nbSongs = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException NotANumber) {
				System.out.println("Please enter a number.");
			}
		} while (nbSongs==-1);
		int i=1;
		LinkedList<Song> newSongsList = new LinkedList<Song>();
		while (nbSongs>0) {
			String titleS;
			String doneOrNot="no";
			System.out.println(" Write the title of the " + i + " song.");
			titleS = sc.nextLine();
			doneOrNot=util.addSongToNewAlbumOrPlaylist(titleS,newSongsList);
			// Verify if the song had been add
			if (doneOrNot.compareTo("yes")==0) {
				i = i +1;
				nbSongs--;
			}
		}
		Album newAlbum = new Album(title, date, newSongsList, artist);
		util.addAlbumToTheList(newAlbum);
		System.out.println("\nThe album " + title + " has been added successfully.");
	}

	/**
	*	Method that adds an existing song to an existing album
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void addASongToAnAlbum(Util util, Scanner sc) {
		System.out.println("Which song do you want to add and to which album?");
		String thisTitleSong;
		int thisIndexSong;
		// Verify if the song exists
		util.showSongsNamesOrdered("no");
		do {
			System.out.println(" Write the title of the song.");
			thisTitleSong = sc.nextLine();
			try {
				thisIndexSong = util.getSongIndex(thisTitleSong);
			}
			catch (NotAnExistingSong NotSong) {
				thisIndexSong = -1;
			}
		} while (thisIndexSong == -1);
		String thisTitleAlbum;
		int thisIndexAlbum;
		// Verify if the album exists
		util.showAlbumsNamesOrdered("no");
		do {
			System.out.println(" Write the title of the album.");
			thisTitleAlbum = sc.nextLine();
			try {
				thisIndexAlbum = util.getAlbumIndex(thisTitleAlbum);
			} catch (NotAnExistingAlbum NotAlbum) {
				thisIndexAlbum = -1;
			}
		} while (thisIndexAlbum == -1);
		util.addSongToAlbum(thisIndexSong, thisIndexAlbum);
	}

	/**
	*	Method that adds an audiobook to the list of audiobooks
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void addAnAudioBookToTheAudioBooksList(Util util, Scanner sc) {
		System.out.println("Which audiobook do you want to add?");
		String titleAudiobook;
		System.out.println(" Write the title of the audiobook.");
		titleAudiobook = sc.nextLine();
		String authorAudiobook;
		System.out.println(" Write the author.");
		authorAudiobook = sc.nextLine();
		int durationAudiobook=-1;
		System.out.println(" Write the duration.");
		do {
			try {
				durationAudiobook = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException NotANumber) {
				System.out.println("Please enter a number.");
			}
		} while (durationAudiobook==-1);
		String contentAudiobook = "audio/";
		System.out.println(" Write the content. (yourFile.wav)");
		contentAudiobook += sc.nextLine();
		String languageAudiobook;
		System.out.println(" Write the language. Please, choose in the following list: FRENCH, ENGLISH, ITALIAN, SPANISH, GERMAN, KOREAN, CHINESE, RUSSIAN.");
		languageAudiobook = sc.nextLine();
		String categoryAudiobook;
		System.out.println(" Write the category. Please, choose in the following list: YOUTH, NOVEL, THEATRE, SPEECH, DOCUMENTARY.");
		categoryAudiobook = sc.nextLine();
		AudioBook newAudiobook = new AudioBook(titleAudiobook, authorAudiobook, durationAudiobook, contentAudiobook, Languages.valueOf(languageAudiobook), Categories.valueOf(categoryAudiobook));
		util.addAudiobookToTheList(newAudiobook);
		System.out.println("The audiobook " + newAudiobook.getTitle() + " has been added successfully.");
	}

	/**
	*	Method that creates a playlist with existing songs and audiobooks
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void createAPlaylist(Util util, Scanner sc) {
		System.out.println("Which playlist do you want to create?");
		String name;
		System.out.println(" Write the name of the playlist.");
		name = sc.nextLine();
		int nbSongs=-1;
		System.out.println("\n Here are the songs:");
		util.showSongsNamesOrdered("no");
		System.out.println("\n How many songs do you want to put in your playlist?");
		do {
			try {
				nbSongs = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException NotANumber) {
				System.out.println("Please enter a number.");
			}
		} while (nbSongs==-1);
		int i=1;
		LinkedList<Song> newSongsList = new LinkedList<Song>();
		while (nbSongs>0) {
			String titleS;
			String doneOrNot="no";
			System.out.println(" Write the title of the " + i + " song.");
			titleS = sc.nextLine();
			doneOrNot=util.addSongToNewAlbumOrPlaylist(titleS,newSongsList);
			// Verify if the song had been add
			if (doneOrNot.compareTo("yes")==0) {
				i = i +1;
				nbSongs--;
			}
		}
		System.out.println("\n Here are the audiobooks:");
		util.showAudioBooksNamesOrdered("no");
		System.out.println("\n How many audiobooks do you want to put in your playlist?");
		int nbAudioBooks=-1;
		do {
			try {
				nbAudioBooks = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException NotANumber) {
				System.out.println("Please enter a number.");
			}
		} while (nbAudioBooks==-1);
		i=1;
		LinkedList<AudioBook> newAudiobooksList = new LinkedList<AudioBook>();
		while (nbAudioBooks>0) {
			String titleA;
			String doneOrNot="no";
			System.out.println(" Write the title of the " + i + " audiobook.");
			titleA = sc.nextLine();
			doneOrNot=util.addAudioBookToNewPlaylist(titleA,newAudiobooksList);
			// Verify if the song had been add
			if (doneOrNot.compareTo("yes")==0) {
				i = i +1;
				nbAudioBooks--;
			}
		}
		Playlist newPlaylist = new Playlist(name, newSongsList, newAudiobooksList);
		util.addPlaylistToTheList(newPlaylist);
		System.out.println("\nThe playlist " + name + " has been added successfully.");
	}

	/**
	*	Method that deletes an existing playlist
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void deleteAPlaylist(Util util, Scanner sc) {
		System.out.println(" Which playlist do you want to delete?");
		util.showPlaylistsNamesOrdered("no");
		String nameToDelete;
		int done=0;
		do {
			System.out.println(" Write the name of the playlist.");
			nameToDelete = sc.nextLine();
			try {
				int indexToDelete = util.getPlaylistIndex(nameToDelete);
				util.getPlaylistsListForXml().remove(indexToDelete);
				System.out.println("The playlist " + nameToDelete + " had been deleted.");
				done = 1;
			} catch (NotAnExistingPlaylist notAPlaylist) {
			}
		} while (done==0);
	}


	/**
	*	Method that saves the changes made in the xml files
	* @param xmlfile
	*						List of xml files
	* @param util
	*						List of audiobooks, songs, albums and playlists
	*/
	public static void saveApplicationInXml(XmlFile xmlfile, Util util) {
		xmlfile.setXmlWithUtilList(util);
		System.out.println("\nModifications are saved.\n");
	}

	/**
	*	Method that enables to listening to some music
	* @param util
	*						List of audiobooks, songs, albums and playlists
	* @param sc
	*						Scanner
	*/
	public static void chooseTheMusicToListen(Util util, Scanner sc) {
		System.out.println("What do you want to listen ?");
		String musicWanted;
		System.out.println("C) A song\t\t\t|\tL) An audiobook");
		System.out.println("A) An album\t\t\t|\tP) A playlist");
		musicWanted = sc.nextLine();
		String islistened="no";
		do {
			switch (musicWanted) {
				case "C": // fonctionne
				System.out.println("Here are the songs, which song do you want to listen ?");
				util.showSongsNamesOrdered("no");
				System.out.println("Write its title.");
				musicWanted = sc.nextLine();
				Music.listenToSomeMusic(util.musicToListen(musicWanted, "song"), sc);
				islistened ="yes";
				break;

				case "L": // fonctionne
				System.out.println("Here are the audiobooks, which audiobook do you want to listen ?");
				util.showAudioBooksNamesOrdered("no");
				System.out.println("Write its title.");
				musicWanted = sc.nextLine();
				Music.listenToSomeMusic(util.musicToListen(musicWanted, "audiobook"), sc);
				islistened ="yes";
				break;

				case "A": // fonctionne
				System.out.println("Here are the albums, which album do you want to listen ?");
				util.showAlbumsNamesOrdered("no");
				LinkedList<String> listOfMusicA = new LinkedList<String>();
				listOfMusicA = util.getAlbumSongsFilepath(musicWanted, sc);
				Music.listenToAListOfMusics(listOfMusicA, sc);
				islistened = "yes";
				break;

				case "P": // fonctionne
				System.out.println("Here are the playlists, which playlist do you want to listen ?");
				util.showPlaylistsNamesOrdered("no");
				LinkedList<String> listOfMusicP = new LinkedList<String>();
				listOfMusicP = util.getPlaylistMusicsFilepath(musicWanted, sc);
				Music.listenToAListOfMusics(listOfMusicP, sc);
				islistened = "yes";
				break;

				default:
				System.out.println("You will return to the menu.");
				islistened = "yes";
				break;

			}
		} while (islistened.compareTo("no")==0);
	}

}
