/*
* Name of class : XmlFile
*
* Description   : Class which manages the passage of xml file to class and vice versa
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package musichub.util;

// Our packages
import musichub.business.*;
import musichub.util.*;
import musichub.main.*;


import java.io.File;
import java.io.IOException;

// Sax
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// Build the file
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

// Modify the file
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.*;

// Create the body of the xmlfile
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Read the file
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

// Delete file
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.*;
import java.util.*;
import java.lang.String;

public class XmlFile {
  Element root;                                                   // Have the elements at the root
  private static String filepathPlaylist = "files/playlists.xml"; // Playlist path in XML
  private static String filepathAlbum = "files/albums.xml";       // Album path in XML
  private static String filepathMedias= "files/elements.xml";     // Medias path in XML
  private TransformerFactory transformerFactory;                  // A TransformerFactory instance can be used to create Transformer and Templates objects
  private Transformer transformer;                                // Reset this Transformer to its original configuration
  private DocumentBuilderFactory docFactory;                      // Set a feature for this DocumentBuilderFactory and DocumentBuilders created by this factory.
  private DocumentBuilder docBuilder;                             // Creates a new instance of a DocumentBuilder using the currently configured parameters
  private static Document docPlaylist;                            // Documents on the playlist
  private static Document docAlbum;                               // Documents on the Album
  private static Document docMedias;                              // Documents on the Medias

  /*
  * Construction of documents for each xml
  */
  
  public XmlFile() {
    try {
      docFactory = DocumentBuilderFactory.newInstance();
      docBuilder = docFactory.newDocumentBuilder();

      transformerFactory = TransformerFactory.newInstance();
      transformer = transformerFactory.newTransformer();

      docPlaylist = docBuilder.parse(filepathPlaylist);
      docAlbum = docBuilder.parse(filepathAlbum);
      docMedias = docBuilder.parse(filepathMedias);

    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    } catch (SAXException sae) {
      sae.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  /*
  * Method for displaying an xml
  */
  
  public void showXML(String xmlWanted) {
    Document thisDocumentMustBeShowed;
    switch (xmlWanted) {
      case "p":
      thisDocumentMustBeShowed = docPlaylist;
      break;
      case "a":
      thisDocumentMustBeShowed = docAlbum;
      break;
      case "m":
      thisDocumentMustBeShowed = docMedias;
      break;
      default:
      thisDocumentMustBeShowed = docMedias;
      break;
    }
    Node firstNodeFromTheDocument = thisDocumentMustBeShowed.getFirstChild();
    String desc = getContent(firstNodeFromTheDocument, "");
    System.out.println(desc);
  }

  /*
  * Method that will retrieve the content of a node
  */
  
  public String getContent(Node n, String tab) {
    String str = new String();
    // We make sure that the node passed in parameter is an Element instance
    if (n instanceof Element) {
      // We cast the object of type Node to type Element
      Element element = (Element)n;

      // We can retrieve the name of the node currently browsed
      // So we open our beacon
      str += "<" + n.getNodeName();

      // We check the list of attributes present
      if (n.getAttributes() != null && n.getAttributes().getLength() > 0) {

        // We can retrieve the list of attributes of an element
        NamedNodeMap att = n.getAttributes();
        int nbAtt = att.getLength();

        // We browse all nodes to display them
        for(int j = 0; j < nbAtt; j++) {
          Node noeud = att.item(j);
          // We get the name of the attribute and its value using these two methods
          str += " " + noeud.getNodeName() + "=\"" + noeud.getNodeValue() + "\" ";
        }
      }

      // We close our tag because we have processed the different attributes
      str += ">";

      // The getChildNodes method returning the content of the node + the child nodes
      // We get text content only when there is only text, so only one child
      if (n.getChildNodes().getLength() == 1)
      str += n.getTextContent();

      // We are now going to process the child nodes of the node being processed
      int nbChild = n.getChildNodes().getLength();
      // We retrieve the list of child nodes
      NodeList list = n.getChildNodes();
      String tab2 = tab + "\t";

      // We go through the list of nodes
      for(int i = 0; i < nbChild; i++) {
        Node n2 = list.item(i);
        // If the child is an Element, we treat it
        if (n2 instanceof Element) {
          // Recursive call to the method for processing the node and its children
          str += "\n " + tab2 + getContent(n2, tab2);
        }
      }
      // We now close the tag
      if (n.getChildNodes().getLength() < 2)
      str += "</" + n.getNodeName() + ">";
      else
      str += "\n" + tab +"</" + n.getNodeName() + ">";
    }
    return str;
  }

  /*
  * Method for obtaining an audiobook and checking if the category and language are correct
  */
  
  private AudioBook getAudioBooks(Node n) throws NotACategoryException, NotALanguageException {
    String title = "";
    String auteur ="";
    int duree = 1; 
    String contenu = "";
    String id = "";
    String language = "";
    String categorie = "";
    
    // We are now going to process the child nodes of the node being processed
    int nbChild = n.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = n.getChildNodes();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node n2 = list.item(i);

      // If the child node is an Element, we treat it
      if (n2 instanceof Element) {
        switch(n2.getNodeName()) {
          case "titlebook":
          title = n2.getTextContent();
          break;
          case "authorbook":
          auteur = n2.getTextContent();
          break;
          case "durationbook":
          duree =Integer.parseInt(n2.getTextContent());
          break;
          case "idbook":
          id = n2.getTextContent();
          break;
          case "contentsbook":
          contenu = n2.getTextContent();
          break;
          case "languagebook":
          LinkedList<String> languages = new LinkedList<String>();
          languages.add("FRENCH");
          languages.add("ENGLISH");
          languages.add("ITALIAN");
          languages.add("SPANISH");
          languages.add("GERMAN");
          languages.add("KOREAN");
          languages.add("CHINESE");
          languages.add("RUSSIAN");
          for (int l=0; l<languages.size(); l++) {
            if (n2.getTextContent().equals(languages.get(l))) {
              language = n2.getTextContent();
              break;
            }
          } if (language == "") {
            throw new NotALanguageException(title);
          }
          break;
          case "categorybook":
          LinkedList<String> categories = new LinkedList<String>();
          categories.add("YOUTH");
          categories.add("NOVEL");
          categories.add("THEATRE");
          categories.add("SPEECH");
          categories.add("DOCUMENTARY");
          for (int c=0; c<categories.size(); c++) {
            if (n2.getTextContent().equals(categories.get(c))) {
              categorie = n2.getTextContent();
              break;
            }
          } if (categorie == "") {
            throw new NotACategoryException(title);
          }
        }
      }
    }
    return new AudioBook(title, auteur, duree, contenu, Languages.valueOf(language), Categories.valueOf(categorie), id);
  }

  /*
  * Method for obtaining the list of audiobooks
  */
  
  public LinkedList<AudioBook> getListAudioBooks() {
    Node audios = docMedias.getFirstChild();
    int nbChild = audios.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = audios.getChildNodes();

    LinkedList<AudioBook> audioBooksList = new LinkedList<AudioBook>();
    // We go through the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node n2 = list.item(i);

      // If the child node is an Element, we treat it
      if (n2 instanceof Element) {
        if (n2.getNodeName().equals("audiobook")) {
          try {
            audioBooksList.add(getAudioBooks(n2));
          } catch (NotACategoryException exceptCat) {
          } catch (NotALanguageException exceptLang) {
          }
        }
      }
    }
    return audioBooksList;
  }

  /*
  * Method for obtaining a song and check if the gender is correct
  */
  
  private Song getSong(Node n) throws NotAGenreException {
    String title = "";
    String artiste = "";
    int duree = 1;
    String contenu = "";
    String id = "";
    String genre = ""; 

    // We are now going to process the child nodes of the node being processed
    int nbChild = n.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = n.getChildNodes();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node n2 = list.item(i);

      // If the child node is an Element, we treat it
      if (n2 instanceof Element) {
        switch(n2.getNodeName()) {
          case "titlesong":
          title = n2.getTextContent();
          break;
          case "artistsong":
          artiste = n2.getTextContent();
          break;
          case "durationsong":
          duree = Integer.parseInt(n2.getTextContent());
          break;
          case "idsong":
          id = n2.getTextContent();
          break;
          case "contentssong":
          contenu = n2.getTextContent();
          break;
          case "genresong":
          LinkedList<String> genres = new LinkedList<String>();
          genres.add("JAZZ");
          genres.add("CLASSIC");
          genres.add("HIPHOP");
          genres.add("ROCK");
          genres.add("POP");
          genres.add("RAP");
          genres.add("KPOP");
          for (int g=0; g<genres.size(); g++) {
            if (n2.getTextContent().equals(genres.get(g))) {
              genre = n2.getTextContent();
              break;
            }
          } if (genre == "") {
            throw new NotAGenreException(title);
          }
          break;
        }
      }
    }
    return new Song(title, artiste, duree, contenu, Genres.valueOf(genre), id);
  }

  /*
  * Method to get the song list
  */
  public LinkedList<Song> getListSongs() {
    Node songs = docMedias.getFirstChild();

    // We retrieve the list of child nodes
    NodeList list = songs.getChildNodes();

    LinkedList<Song> songsList = new LinkedList<Song>();

    // We go through the list of nodes
    for(int i = 0; i < list.getLength(); i++) {
      Node n2 = list.item(i);

      // If the child node is an Element, we treat it
      if (n2 instanceof Element) {
        if (n2.getNodeName().equals("song")) {
          try {
            songsList.add(getSong(n2));
          } catch (NotAGenreException exceptGen) {
          }
        }
      }
    }
    return songsList;
  }


  /*
  * Method for obtaining an album
  */
  private Album getAlbum(Node n) {
    String title = "";
    String artist = "";
    int duration = 0;
    int date = 0;
    String id = "0-0-0-0-0";
    LinkedList<Song> songs = new LinkedList<Song>();

    // We are now going to process the child nodes of the node being processed
    int nbChild = n.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = n.getChildNodes();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node n2 = list.item(i);

      // If the child node is an Element, we treat it
      if (n2 instanceof Element) {
        switch(n2.getNodeName()) {
          case "title":
          title = n2.getTextContent();
          break;
          case "artist":
          artist = n2.getTextContent();
          break;
          case "duration":
          duration = Integer.parseInt(n2.getTextContent());
          break;
          case "date":
          date = Integer.parseInt(n2.getTextContent());
          break;
          case "id":
          id = n2.getTextContent();
          break;
          case "song":
          try {
            songs.add(getSong(n2));
          } catch (NotAGenreException exceptGen) {
          }
          break;
        }
      }
    }

    return new Album(title, date, duration, songs, artist, id);
  }

  /*
  * Method for obtaining the list of albums
  */
  public LinkedList<Album> getListAlbum() {
    Node albums = docAlbum.getFirstChild();
    int nbChild = albums.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = albums.getChildNodes();

    LinkedList<Album> albumsList = new LinkedList<Album>();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node n2 = list.item(i);

      // If the child node is an Element, we treat it
      if (n2 instanceof Element) {
        albumsList.add(getAlbum(n2));
      }
    }
    return albumsList ;
  }


  /*
  * Method for obtaining a playlist
  */
  private Playlist getPlaylist(Node n) {
    String name = "";
    UUID id = UUID.randomUUID();
    LinkedList<Song> songs = new LinkedList<Song>();
    LinkedList<AudioBook> audiobooks = new LinkedList<AudioBook>();

    // We are now going to process the child nodes of the node being processed
    int nbChild = n.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = n.getChildNodes();

    // We browse the list of nodes
    for(int i = 0; i < list.getLength(); i++) {
      Node n2 = list.item(i);

      // If the child node is an Element, we treat it
      if (n2 instanceof Element) {
        switch(n2.getNodeName()) {
          case "name":
          name = n2.getTextContent();
          break;
          case "id":
          id = UUID.fromString(n2.getTextContent());
          break;
          case "book":
          try {
            audiobooks.add(getAudioBooks(n2));
          } catch (NotACategoryException exceptCat) {
          } catch (NotALanguageException exceptLang) {
          }
          break;
          case "song":
          try {
            songs.add(getSong(n2));
          } catch (NotAGenreException exceptGen) {
          }
          break;
        }
      }
    }

    return new Playlist(name, id, songs, audiobooks);
  }

  /*
  * Method to get the list of playlists
  */
  
  public LinkedList<Playlist> getListPlaylist() {
    Node playlists = docPlaylist.getFirstChild();
    int nbChild = playlists.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = playlists.getChildNodes();

    LinkedList<Playlist> playlistList = new LinkedList<Playlist>();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node n2 = list.item(i);

      // If the child node is an Element, we treat it
      if (n2 instanceof Element) {
        playlistList.add(getPlaylist(n2));
      }
    }
    return playlistList ;
  }

  /*
  * Method to save utility lists in xml files
  */
  
  public void setXmlWithUtilList(Util util) {
    writeXml(filepathMedias, util);
    writeXml(filepathAlbum, util);
    writeXml(filepathPlaylist, util);
  }

  /*
  * Method to write the xml file with the util content
  */
  
  public void writeXml(String whereToWrite, Util util) {
    //write a new xml with the content of the application
    try {
      DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document
      document = documentBuilder.newDocument();
      if (document == null) {
        return;
      }
      if (whereToWrite.compareTo(filepathPlaylist)==0) {
        Element playlists = document.createElement("playlists");
        document.appendChild(playlists);
        for (int i=0 ; i < util.getPlaylistsListForXml().size() ; i++) {
          // create an element playlist
          Element playlist = document.createElement("playlist");
          // create an element name
          Element name = document.createElement("name");
          name.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getName()));
          playlist.appendChild(name);
          // create an element id
          Element id = document.createElement("id");
          id.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getId()));
          playlist.appendChild(id);
          // add the songs
          for (int j=0 ; j<util.getPlaylistsListForXml().get(i).getSongsList().size(); j++) {
            Element song = document.createElement("song");
            playlist.appendChild(song);
            // create an element titlesong
            Element titlesong = document.createElement("titlesong");
            titlesong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getTitle()));
            song.appendChild(titlesong);
            // create an element artistsong
            Element artistsong = document.createElement("artistsong");
            artistsong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getArtist()));
            song.appendChild(artistsong);
            // create an element durationsong
            Element durationsong = document.createElement("durationsong");
            String duration = String.valueOf(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getDuration());
            durationsong.appendChild(document.createTextNode(duration));
            song.appendChild(durationsong);
            // create an element idsong
            Element idsong = document.createElement("idsong");
            idsong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getId()));
            song.appendChild(idsong);
            // create an element contentssong
            Element contentssong = document.createElement("contentssong");
            contentssong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getContent()));
            song.appendChild(contentssong);
            // create an element genresong
            Element genresong = document.createElement("genresong");
            genresong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getGenreForXML()));
            song.appendChild(genresong);
          }
          // add the audiobooks
          for (int j=0 ; j<util.getPlaylistsListForXml().get(i).getAudioBooksList().size(); j++) {
            Element book = document.createElement("book");
            playlist.appendChild(book);
            // create an element titlesong
            Element titlebook = document.createElement("titlebook");
            titlebook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getTitle()));
            book.appendChild(titlebook);
            // create an element artistsong
            Element authorbook = document.createElement("authorbook");
            authorbook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getAuthor()));
            book.appendChild(authorbook);
            // create an element durationsong
            Element durationbook = document.createElement("durationbook");
            String duration = String.valueOf(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getDuration());
            durationbook.appendChild(document.createTextNode(duration));
            book.appendChild(durationbook);
            // create an element idsong
            Element idbook = document.createElement("idbook");
            idbook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getId()));
            book.appendChild(idbook);
            // create an element contentssong
            Element contentsbook = document.createElement("contentsbook");
            contentsbook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getContent()));
            book.appendChild(contentsbook);
            // create an element languagebook
            Element languagebook = document.createElement("languagebook");
            languagebook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getLanguage().toUpperCase()));
            book.appendChild(languagebook);
            // create an element categorybook
            Element categorybook = document.createElement("categorybook");
            categorybook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getCategory().toUpperCase()));
            book.appendChild(categorybook);
          }
          playlists.appendChild(playlist);
        }
      } else if (whereToWrite.compareTo(filepathAlbum)==0) {
        Element albums = document.createElement("albums");
        document.appendChild(albums);
        for (int i=0 ; i < util.getAlbumsListForXml().size() ; i++) {
          // create an element album
          Element album = document.createElement("album");
          // create an element title
          Element title = document.createElement("title");
          title.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getTitle()));
          album.appendChild(title);
          // create an element artist
          Element artist = document.createElement("artist");
          artist.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getArtist()));
          album.appendChild(artist);
          // create an element duration
          Element duration = document.createElement("duration");
          duration.appendChild(document.createTextNode(String.valueOf(util.getAlbumsListForXml().get(i).getDuration())));
          album.appendChild(duration);
          // create an element date
          Element date = document.createElement("date");
          date.appendChild(document.createTextNode(String.valueOf(util.getAlbumsListForXml().get(i).getDate())));
          album.appendChild(date);
          // create an element id
          Element id = document.createElement("id");
          id.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getId()));
          album.appendChild(id);
          // add the songs
          for (int j=0 ; j<util.getAlbumsListForXml().get(i).getSongsList().size(); j++) {
            Element song = document.createElement("song");
            album.appendChild(song);
            // create an element titlesong
            Element titlesong = document.createElement("titlesong");
            titlesong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getTitle()));
            song.appendChild(titlesong);
            // create an element artistsong
            Element artistsong = document.createElement("artistsong");
            artistsong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getArtist()));
            song.appendChild(artistsong);
            // create an element durationsong
            Element durationsong = document.createElement("durationsong");
            durationsong.appendChild(document.createTextNode(String.valueOf(util.getAlbumsListForXml().get(i).getSongsList().get(j).getDuration())));
            song.appendChild(durationsong);
            // create an element idsong
            Element idsong = document.createElement("idsong");
            idsong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getId()));
            song.appendChild(idsong);
            // create an element contentssong
            Element contentssong = document.createElement("contentssong");
            contentssong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getContent()));
            song.appendChild(contentssong);
            // create an element genresong
            Element genresong = document.createElement("genresong");
            genresong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getGenreForXML()));
            song.appendChild(genresong);
          }
          albums.appendChild(album);
        }
      } else {
        Element elements = document.createElement("elements");
        document.appendChild(elements);
        for (int j=0 ; j<util.getSongsListForXml().size() ; j++) {
          Element song = document.createElement("song");
          // create an element titlesong
          Element titlesong = document.createElement("titlesong");
          titlesong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getTitle()));
          song.appendChild(titlesong);
          // create an element artistsong
          Element artistsong = document.createElement("artistsong");
          artistsong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getArtist()));
          song.appendChild(artistsong);
          // create an element durationsong
          Element durationsong = document.createElement("durationsong");
          String duration = String.valueOf(util.getSongsListForXml().get(j).getDuration());
          durationsong.appendChild(document.createTextNode(duration));
          song.appendChild(durationsong);
          // create an element idsong
          Element idsong = document.createElement("idsong");
          idsong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getId()));
          song.appendChild(idsong);
          // create an element contentssong
          Element contentssong = document.createElement("contentssong");
          contentssong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getContent()));
          song.appendChild(contentssong);
          // create an element genresong
          Element genresong = document.createElement("genresong");
          genresong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getGenreForXML()));
          song.appendChild(genresong);

          // add the song
          elements.appendChild(song);
        }
        for (int j=0 ; j<util.getAudioBooksListForXml().size() ; j++) {
          Element audiobook = document.createElement("audiobook");
          // create an element titlesong
          Element titlebook = document.createElement("titlebook");
          titlebook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getTitle()));
          audiobook.appendChild(titlebook);
          // create an element artistsong
          Element authorbook = document.createElement("authorbook");
          authorbook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getAuthor()));
          audiobook.appendChild(authorbook);
          // create an element durationsong
          Element durationbook = document.createElement("durationbook");
          durationbook.appendChild(document.createTextNode(String.valueOf(util.getAudioBooksListForXml().get(j).getDuration())));
          audiobook.appendChild(durationbook);
          // create an element idsong
          Element idbook = document.createElement("idbook");
          idbook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getId()));
          audiobook.appendChild(idbook);
          // create an element contentssong
          Element contentsbook = document.createElement("contentsbook");
          contentsbook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getContent()));
          audiobook.appendChild(contentsbook);
          // create an element languagebook
          Element languagebook = document.createElement("languagebook");
          languagebook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getLanguage().toUpperCase()));
          audiobook.appendChild(languagebook);
          // create an element categorybook
          Element categorybook = document.createElement("categorybook");
          categorybook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getCategory().toUpperCase()));
          audiobook.appendChild(categorybook);
          //add the audiobook
          elements.appendChild(audiobook);
        }
      }
      try {
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(whereToWrite));
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xlt}indent-amount", "2");
        t.transform(domSource, streamResult);

      } catch (TransformerException tfe) {
        tfe.printStackTrace();
      }
      System.out.println("Done creating XML File");
    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    }
  }

}
