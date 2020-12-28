/*
* Name of class : util
*
* Description   : Class which manages the lists useful for the application
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.util;

// our packages
import musichub.business.*;
import musichub.util.*;

import java.util.LinkedList;
import java.util.Scanner;

import java.util.Arrays;

public class Util {
  // keep albums, songs, audiobooks and playlists
  // to use them in the programm
  private LinkedList<AudioBook> audiobooksList;
  private LinkedList<Song> songsList;
  private LinkedList<Album> albumsList;
  private LinkedList<Playlist> playlistsList;

  // Constructors
  public Util() {
    //constructor
    audiobooksList = new LinkedList<AudioBook>();
    songsList = new LinkedList<Song>();
    albumsList = new LinkedList<Album>();
    playlistsList = new LinkedList<Playlist>();
  }

  public Util(LinkedList<AudioBook> au, LinkedList<Song> so, LinkedList<Album> al, LinkedList<Playlist> pl) {
    //constructor
    audiobooksList = au;
    songsList = so;
    albumsList = al;
    playlistsList = pl;
  }


  // Add by xml
  public void fileTheAudioBooksList(XmlFile xmlfile) {
    audiobooksList = xmlfile.getListAudioBooks();
  }

  public void fileTheSongsList(XmlFile xmlfile) {
    songsList = xmlfile.getListSongs();
  }

  public void fileTheAlbumsList(XmlFile xmlfile) {
    albumsList = xmlfile.getListAlbum();
  }

  public void fileThePlaylistsList(XmlFile xmlfile) {
    playlistsList = xmlfile.getListPlaylist();
  }


  // Add by the user
  public void addAudiobookToTheList(AudioBook audiobookToAdd) {
    this.audiobooksList.add(audiobookToAdd);
  }

  public void addSongToTheList(Song songToAdd) {
    this.songsList.add(songToAdd);
  }

  public void addAlbumToTheList(Album albumToAdd) {
    this.albumsList.add(albumToAdd);
  }

  public void addPlaylistToTheList(Playlist playlistToAdd) {
    playlistsList.add(playlistToAdd);
    // + ajout dans XML
  }

  // Methods to give acces to xml
  public LinkedList<AudioBook> getAudioBooksListForXml() {
    return audiobooksList;
  }

  public LinkedList<Song> getSongsListForXml() {
    return songsList;
  }

  public LinkedList<Album> getAlbumsListForXml() {
    return albumsList;
  }

  public LinkedList<Playlist> getPlaylistsListForXml() {
    return playlistsList;
  }


  // Methods with audiobooks
  public void showAudioBooksNamesOrdered(String orderedOrNot) {
    //trie les livres audio par auteur et affiche ces derniers
    int j = audiobooksList.size();
    String[] tabNoms = new String[j];
    for(int i=0; i<j; i++) {
      if (orderedOrNot.compareTo("yes")==0) {
        tabNoms[i]="    " + audiobooksList.get(i).getAuthor() + "\t\t" + audiobooksList.get(i).getTitle() + "\n";
      } else {
        tabNoms[i]=audiobooksList.get(i).getTitle() + "    ";
      }
    }
    Arrays.sort(tabNoms); //trie par ordre alphabétique
    for(int i=0; i<j; i++) {
      System.out.printf(tabNoms[i]);
      if ((i==(j-1) || (i+1)%5==0) && orderedOrNot.compareTo("no")==0) {
        System.out.printf("\n");
      }
    }
  }

  public int getAudioBookIndex(String thisAudioBook) throws NotAnExistingAudioBook {
    int indexL;
    for (indexL=0; indexL<audiobooksList.size(); indexL++) {
      if (audiobooksList.get(indexL).getTitle().compareTo(thisAudioBook)==0) {
        return indexL;
      }
    }
    throw new NotAnExistingAudioBook();
  }

  public String addAudioBookToNewPlaylist(String thisAudioBook, LinkedList<AudioBook> listAudioBookPlaylist) {
    try {
      int index = getAudioBookIndex(thisAudioBook);
      listAudioBookPlaylist.add(audiobooksList.get(index));
      return "yes";
    } catch (NotAnExistingAudioBook NotAudioBook) {
      return "no";
    }
  }

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
  public void showSongsNamesOrdered(String orderedOrNot) {
    //trie les chansons par genre et affiche ces dernières
    int j = songsList.size();
    String[] tabNoms = new String[j];
    for(int i=0; i<j; i++) {
      if (orderedOrNot.compareTo("yes")==0) {
        tabNoms[i]="    " + songsList.get(i).getGenre()+ " \t" + songsList.get(i).getTitle() + "\n";
      } else {
        tabNoms[i]=songsList.get(i).getTitle()+ "    ";
      }
    }
    Arrays.sort(tabNoms); //trie par ordre alphabtique
    for(int i=0; i<j; i++) {
      System.out.printf(tabNoms[i]);
      if ((i==(j-1) || (i+1)%5==0) && orderedOrNot.compareTo("no")==0) {
        System.out.printf("\n");
      }
    }
  }

  public int getSongIndex(String thisSong) throws NotAnExistingSong {
    int indexS;
    for (indexS=0; indexS<songsList.size(); indexS++) {
      if (songsList.get(indexS).getTitle().compareTo(thisSong)==0) {
        return indexS;
      }
    }
    throw new NotAnExistingSong();
  }

  public String addSongToNewAlbumOrPlaylist(String thisSong, LinkedList<Song> listSongAlbumOrPlaylist) {
    try {
      int index = getSongIndex(thisSong);
      listSongAlbumOrPlaylist.add(songsList.get(index));
      return "yes";
    } catch (NotAnExistingSong NotSong) {
      return "no";
    }
  }

  public void addSongToAlbum(int thisSong, int thisAlbum) {
    albumsList.get(thisAlbum).addSong(songsList.get(thisSong));
  }

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
    Arrays.sort(tabNoms); //trie par ordre alpha-numérique
    for(int i=0; i<j; i++) {
      System.out.printf(tabNoms[i]);
      if ((i==(j-1) || (i+1)%5==0)&& orderedOrNot.compareTo("no")==0) {
        System.out.printf("\n");
      }
    }
  }

  public int getAlbumIndex(String thisAlbum) throws NotAnExistingAlbum {
    int indexA;
    for (indexA=0; indexA<albumsList.size(); indexA++) {
      if (albumsList.get(indexA).getTitle().compareTo(thisAlbum)==0) {
        return indexA;
      }
    }
    throw new NotAnExistingAlbum();
  }

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
  public void showPlaylistsNamesOrdered(String orderedOrNot) {
    //trie les playlists par names et affiche ces dernières
    int j = playlistsList.size();
    String[] tabNoms = new String[j];
    for(int i=0; i<j; i++) {
      tabNoms[i]=playlistsList.get(i).getName();
    }
    Arrays.sort(tabNoms); //trie par ordre alphabtique
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

  public int getPlaylistIndex(String thisPlaylist) throws NotAnExistingPlaylist {
    int indexP;
    for (indexP=0; indexP<playlistsList.size(); indexP++) {
      if (playlistsList.get(indexP).getName().compareTo(thisPlaylist)==0) {
        return indexP;
      }
    }
    throw new NotAnExistingPlaylist();
  }

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
