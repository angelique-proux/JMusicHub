/*
* Name of class : util
*
* Description   : Class which manages the lists useful for the application
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package util;

// Our packages
import business.*;
import util.*;

// Packages from java
import java.util.LinkedList;
import java.util.Scanner;

import java.util.Arrays;


public class Util {
  // Keeps albums, songs, audiobooks and playlists to use them in the programme

  private LinkedList<AudioBook> audiobooksList;   // Audiobook list
  private LinkedList<Song> songsList;             // Song list
  private LinkedList<Album> albumsList;           // Album list
  private LinkedList<Playlist> playlistsList;     // Playlist list

  // Builders

  /**
  *  Builder 1 (new util with no value)
  */
  public Util() {
    //constructor
    audiobooksList = new LinkedList<AudioBook>();
    songsList = new LinkedList<Song>();
    albumsList = new LinkedList<Album>();
    playlistsList = new LinkedList<Playlist>();
  }

  /**
  *  Builder 2 (new util with xml values)
  */
  public Util(LinkedList<AudioBook> au, LinkedList<Song> so, LinkedList<Album> al, LinkedList<Playlist> pl) {
    //constructor
    audiobooksList = au;
    songsList = so;
    albumsList = al;
    playlistsList = pl;
  }


  // Add by xml

  /**
  *	Method that adds the xml audiobook's list to the util list
  * @param xmlfile
  *						List of xml files
  */
  public void fileTheAudioBooksList(XmlFile xmlfile) {
    audiobooksList = xmlfile.getListAudioBooks();
  }

  /**
  *	Method that adds the xml songs's list to the util list
  * @param xmlfile
  *						List of xml files
  */
  public void fileTheSongsList(XmlFile xmlfile) {
    songsList = xmlfile.getListSongs();
  }

  /**
  *	Method that adds the xml album's list to the util list
  * @param xmlfile
  *						List of xml files
  */
  public void fileTheAlbumsList(XmlFile xmlfile) {
    albumsList = xmlfile.getListAlbum();
  }

  /**
  *	Method that adds the xml playlist's list to the util list
  * @param xmlfile
  *						List of xml files
  */
  public void fileThePlaylistsList(XmlFile xmlfile) {
    playlistsList = xmlfile.getListPlaylist();
  }



  // Add by the user

  /**
  *	Method that adds an audiobook to the util list
  * The audiobook is added by the user
  * @param audiobookToAdd
  *						The audiobook to add
  */
  public void addAudiobookToTheList(AudioBook audiobookToAdd) {
    this.audiobooksList.add(audiobookToAdd);
  }

  /**
  *	Method that adds a song to the util list
  * The song is added by the user
  * @param songToAdd
  *						The song to add
  */
  public void addSongToTheList(Song songToAdd) {
    this.songsList.add(songToAdd);
  }

  /**
  *	Method that adds an album to the util list
  * The album is added by the user
  * @param albumToAdd
  *						The album to add
  */
  public void addAlbumToTheList(Album albumToAdd) {
    this.albumsList.add(albumToAdd);
  }

  /**
  *	Method that adds a playlist to the util list
  * The playlist is added by the user
  * @param playlistToAdd
  *						The playlist to add
  */
  public void addPlaylistToTheList(Playlist playlistToAdd) {
    playlistsList.add(playlistToAdd);
  }



  // Methods to give acces to xml

  /**
  *	Method that returns the util audiobook's list needed to build the xml
  * @return The util's list of audiobooks
  */
  public LinkedList<AudioBook> getAudioBooksListForXml() {
    return audiobooksList;
  }

  /**
  *	Method that returns the util song's list needed to build the xml
  * @return The util's list of songs
  */
  public LinkedList<Song> getSongsListForXml() {
    return songsList;
  }

  /**
  *	Method that returns the util album's list needed to build the xml
  * @return The util's list of albums
  */
  public LinkedList<Album> getAlbumsListForXml() {
    return albumsList;
  }

  /**
  *	Method that returns the util playlist's list needed to build the xml
  * @return The util's list of playlists
  */
  public LinkedList<Playlist> getPlaylistsListForXml() {
    return playlistsList;
  }



  // Methods with audiobooks

  /**
  * Method that shows the audiobooks of the util list
  * If the audiobooks are ordered by them author, them title and author are showed
  * If the audiobooks aren't ordered, only them title are showed
  * @param orderedOrNot
  *               If the audiobooks are ordered or not
  */
  public void showAudioBooksNamesOrdered(String orderedOrNot) {
    int j = audiobooksList.size();
    String[] tabNoms = new String[j];
    for(int i=0; i<j; i++) {
      if (orderedOrNot.compareTo("yes")==0) {
        tabNoms[i]="    " + audiobooksList.get(i).getAuthor() + "\t\t" + audiobooksList.get(i).getTitle() + "\n";
      } else {
        tabNoms[i]=audiobooksList.get(i).getTitle() + "    ";
      }
    }
    Arrays.sort(tabNoms); // Alphabetical ordered
    for(int i=0; i<j; i++) {
      System.out.printf(tabNoms[i]);
      if ((i==(j-1) || (i+1)%5==0) && orderedOrNot.compareTo("no")==0) {
        System.out.printf("\n");
      }
    }
  }

  /**
  * Method that returns the audiobook's index
  * @param thisAudioBook
  *           The audiobook whose index we want to find in the list Util of audiobooks
  * @throws NotAnExistingAudioBook
  *						If the parameter entered by the user is not an existing audiobook
  * @return   The index of the audiobook wanted
  */
  public int getAudioBookIndex(String thisAudioBook) throws NotAnExistingAudioBook {
    int indexL;
    for (indexL=0; indexL<audiobooksList.size(); indexL++) {
      if (audiobooksList.get(indexL).getTitle().compareTo(thisAudioBook)==0) {
        return indexL;
      }
    }
    throw new NotAnExistingAudioBook();
  }

  /**
  * Method that adds audiobooks to a new playlist and returns if it's made or not
  * @param thisAudioBook
  *           The audiobook we want to add
  * @param listAudioBookPlaylist
  *           The list of audiobooks which are in the new playlist
  * @return   If the audiobook is added or not
  * @see getAudioBookIndex
  */
  public String addAudioBookToNewPlaylist(String thisAudioBook, LinkedList<AudioBook> listAudioBookPlaylist) {
    try {
      int index = getAudioBookIndex(thisAudioBook);
      listAudioBookPlaylist.add(audiobooksList.get(index));
      return "yes";
    } catch (NotAnExistingAudioBook NotAudioBook) {
      return "no";
    }
  }

  /**
  * Method that returns the audiobooks' author
  * @return   All the authors
  */
  public LinkedList<String> getAuthors() {
    LinkedList<String> authorList = new LinkedList<String>();
    for (int i=0 ; i<audiobooksList.size() ; i++) {
      if (authorList.contains(audiobooksList.get(i).getAuthor())==false) {
        authorList.add(audiobooksList.get(i).getAuthor());
      }
    }
    return authorList;
  }



  // Methods with songs

  /**
  * Method that shows the songs of the util list
  * If the songs are ordered by them genre, them title and genre are showed
  * If the songs aren't ordered, only them title are showed
  * @param orderedOrNot
  *               If the songs are ordered or not
  */
  public void showSongsNamesOrdered(String orderedOrNot) {
    int j = songsList.size();
    String[] tabNoms = new String[j];
    for(int i=0; i<j; i++) {
      if (orderedOrNot.compareTo("yes")==0) {
        tabNoms[i]="    " + songsList.get(i).getGenre()+ " \t" + songsList.get(i).getTitle() + "\n";
      } else {
        tabNoms[i]=songsList.get(i).getTitle()+ "    ";
      }
    }
    Arrays.sort(tabNoms); // Alphabetical ordered
    for(int i=0; i<j; i++) {
      System.out.printf(tabNoms[i]);
      if ((i==(j-1) || (i+1)%5==0) && orderedOrNot.compareTo("no")==0) {
        System.out.printf("\n");
      }
    }
  }

  /**
  * Method that returns the song's index
  * @param thisSong
  *           The song whose index we want to find in the list Util of songs
  * @throws NotAnExistingSong
  *						If the parameter entered by the user is not an existing song
  * @return   The index of the song wanted
  */
  public int getSongIndex(String thisSong) throws NotAnExistingSong {
    int indexS;
    for (indexS=0; indexS<songsList.size(); indexS++) {
      if (songsList.get(indexS).getTitle().compareTo(thisSong)==0) {
        return indexS;
      }
    }
    throw new NotAnExistingSong();
  }

  /**
  * Method that adds song to a new album or a new playlist and returns if it's made or not
  * @param thisSong
  *           The song we want to add
  * @param listSongAlbumOrPlaylist
  *           The list of songs which are in the new album or the new playlist
  * @return   If the song is added or not
  * @see getSongIndex
  */
  public String addSongToNewAlbumOrPlaylist(String thisSong, LinkedList<Song> listSongAlbumOrPlaylist) {
    try {
      int index = getSongIndex(thisSong);
      listSongAlbumOrPlaylist.add(songsList.get(index));
      return "yes";
    } catch (NotAnExistingSong NotSong) {
      return "no";
    }
  }

  /**
  * Method that adds song to an existing album
  * @param thisSong
  *           The song we want to add
  * @param thisAlbum
  *           The album where the song must be added
  */
  public void addSongToAlbum(int thisSong, int thisAlbum) {
    albumsList.get(thisAlbum).addSong(songsList.get(thisSong));
  }

  /**
  * Method that returns the songs's artists
  * @return   All the artists
  */
  public LinkedList<String> getArtists() {
    LinkedList<String> artistList = new LinkedList<String>();
    for (int i=0 ; i<songsList.size() ; i++) {
      if (artistList.contains(songsList.get(i).getArtist())==false) {
        artistList.add(songsList.get(i).getArtist());
      }
    }
    return artistList;
  }



  // Methods with songs and audiobooks

  /**
  * Method that returns the content of the music
  * @param thisMusic
  *           The music we want the content
  * @param kindOfMusic
  *           If the music is an audiobook or a song
  * @return   The content of the music
  * @see getSongIndex
  * @see getAudioBookIndex
  */
  public String musicToListen(String thisMusic, String kindOfMusic) {
    switch (kindOfMusic) {
      case "song":
      try {
        int thisIndex = getSongIndex(thisMusic);
        return songsList.get(thisIndex).getContent();
      } catch (NotAnExistingSong NotASong) {
        return "noContent";
      }

      case "audiobook":
      try {
        int thisIndex = getAudioBookIndex(thisMusic);
        return audiobooksList.get(thisIndex).getContent();
      } catch (NotAnExistingAudioBook NotAnAudio) {
        return "noContent";
      }

      default:
      return "noContent";
    }
  }



  // Methods with albums

  /**
  * Method that shows the albums of the util list
  * If the albums are ordered by them date, them title and date are showed
  * If the albums aren't ordered, only them title are showed
  * @param orderedOrNot
  *               If the albums are ordered or not
  */
  public void showAlbumsNamesOrdered(String orderedOrNot) {
    //trie les albums par date et affiche ces derniers
    int j = albumsList.size();
    String[] tabNoms = new String[j];
    for(int i=0; i<j; i++) {
      if (orderedOrNot.compareTo("yes")==0) {
        tabNoms[i]="    " + albumsList.get(i).getDate()+ "\t" + albumsList.get(i).getTitle() + "\n";
      } else {
        tabNoms[i]=albumsList.get(i).getTitle()+ "    ";
      }
    }
    Arrays.sort(tabNoms); // alpha-numeric ordered
    for(int i=0; i<j; i++) {
      System.out.printf(tabNoms[i]);
      if ((i==(j-1) || (i+1)%5==0)&& orderedOrNot.compareTo("no")==0) {
        System.out.printf("\n");
      }
    }
  }

  /**
  * Method that returns the album's index
  * @param thisAlbum
  *           The album whose index we want to find in the list Util of albums
  * @throws NotAnExistingAlbum
  *						If the parameter entered by the user is not an existing album
  * @return   The index of the album wanted
  */
  public int getAlbumIndex(String thisAlbum) throws NotAnExistingAlbum {
    int indexA;
    for (indexA=0; indexA<albumsList.size(); indexA++) {
      if (albumsList.get(indexA).getTitle().compareTo(thisAlbum)==0) {
        return indexA;
      }
    }
    throw new NotAnExistingAlbum();
  }

  /**
  * Method that returns the content of the songs of a specific album
  * @param albumWanted
  *           The album we wanted to listen to
  * @param sc
  *           Scanner
  * @return   The list of the songs filepath
  * @see getAlbumIndex
  * @see musicToListen
  */
  public LinkedList<String> getAlbumSongsFilepath(String albumWanted, Scanner sc) {
    LinkedList<String> listOfMusic = new LinkedList<String>();
    LinkedList<Song> listOfSong = new LinkedList<Song>();
    int thisIndexAlbum=-1;
    do {
      System.out.println("Write its title.");
      albumWanted = sc.nextLine();
      try {
        thisIndexAlbum = getAlbumIndex(albumWanted);
      } catch (NotAnExistingAlbum NotAnAlbum) {
        thisIndexAlbum = -1;
      }
    } while (thisIndexAlbum == -1);
    listOfSong = albumsList.get(thisIndexAlbum).getSongsList();
    for (int i=0; i<listOfSong.size();i++) {
      listOfMusic.add(musicToListen(listOfSong.get(i).getTitle(), "song"));
    }
    return listOfMusic;
  }



  // Methods with playlists

  /**
  * Method that shows the playlists of the util list
  * If the playlists are alphabetically ordered, them name are showed
  * If the playlists aren't ordered, them name are showed too
  * @param orderedOrNot
  *               If the playlists are ordered or not
  */
  public void showPlaylistsNamesOrdered(String orderedOrNot) {
    //trie les playlists par names et affiche ces dernières
    int j = playlistsList.size();
    String[] tabNoms = new String[j];
    for(int i=0; i<j; i++) {
      tabNoms[i]=playlistsList.get(i).getName();
    }
    Arrays.sort(tabNoms); // alphabetical ordered
    for(int i=0; i<j; i++) {
      if (orderedOrNot.compareTo("yes")==0) {
        System.out.println("    " + tabNoms[i]);
      } else {
        System.out.printf(tabNoms[i] + "    ");
        if (i==(j-1)) {
          System.out.printf("\n");
        }
      }
    }
  }

  /**
  * Method that returns the playlist's index
  * @param thisPlaylist
  *           The playlist whose index we want to find in the list Util of playlists
  * @throws NotAnExistingPlaylist
  *						If the parameter entered by the user is not an existing playlist
  * @return   The index of the playlist wanted
  */
  public int getPlaylistIndex(String thisPlaylist) throws NotAnExistingPlaylist {
    int indexP;
    for (indexP=0; indexP<playlistsList.size(); indexP++) {
      if (playlistsList.get(indexP).getName().compareTo(thisPlaylist)==0) {
        return indexP;
      }
    }
    throw new NotAnExistingPlaylist();
  }

  /**
  * Method that returns the content of the songs and audiobooks of a specific playlist
  * @param playlistWanted
  *           The playlist we wanted to listen to
  * @param sc
  *           Scanner
  * @return   The list of the songs and audiobooks filepath
  * @see getPlaylistIndex
  * @see musicToListen
  */
  public LinkedList<String> getPlaylistMusicsFilepath(String playlistWanted, Scanner sc) {
    LinkedList<String> listOfMusic = new LinkedList<String>();
    LinkedList<Song> listOfSong = new LinkedList<Song>();
    LinkedList<AudioBook> listOfAudioBook = new LinkedList<AudioBook>();
    int thisIndexPlaylist=-1;
    do {
      System.out.println("Write its title.");
      playlistWanted = sc.nextLine();
      try {
        thisIndexPlaylist = getPlaylistIndex(playlistWanted);
      } catch (NotAnExistingPlaylist NotAPlaylist) {
        thisIndexPlaylist = -1;
      }
    } while (thisIndexPlaylist == -1);
    listOfSong = playlistsList.get(thisIndexPlaylist).getSongsList();
    for (int i=0; i<listOfSong.size();i++) {
      listOfMusic.add(musicToListen(listOfSong.get(i).getTitle(), "song"));
    }
    listOfAudioBook = playlistsList.get(thisIndexPlaylist).getAudioBooksList();
    for (int i=0; i<listOfAudioBook.size();i++) {
      listOfMusic.add(musicToListen(listOfAudioBook.get(i).getTitle(), "audiobook"));
    }
    return listOfMusic;
  }

}
