/*
* Name of class : XmlFile
*
* Description   : Class which manages the passage of xml file to class and vice versa
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


package util;

// Our packages
import business.*;
import util.*;
import main.*;

// Packages from java
import java.io.File;
import java.io.IOException;

//sax
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//build the file
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//modify the file
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.*;

//create the body of the xmlfile
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//read the file
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

//delete file
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.*;
import java.util.*;
import java.lang.String;

public class XmlFile{
  Element root;
  private static String filepathPlaylist = "files/playlists.xml";
  private static String filepathAlbum = "files/albums.xml";
  private static String filepathMedias= "files/elements.xml";
  private TransformerFactory transformerFactory;
  private Transformer transformer;
  private DocumentBuilderFactory docFactory;
  private DocumentBuilder docBuilder;
  private static Document docPlaylist;
  private static Document docAlbum;
  private static Document docMedias;

  /**
  * Builder
  */
  public XmlFile() {
    try {
      docFactory = DocumentBuilderFactory.newInstance();
      docBuilder = docFactory.newDocumentBuilder();

      transformerFactory = TransformerFactory.newInstance();
      transformer = transformerFactory.newTransformer();

      // Construction of documents for each xml
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


  /**
  * Method that shows xml files
  * @param xmlWanted
  *             The xml file to show
  * @see getContent
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


  /**
  * Method that returns the contents of a node
  */
  public String getContent(Node n, String tab) {
    String str = new String();
    // We make sure that the node passed as a parameter is an instance of Element
    if (n instanceof Element) {
      // We cast the object from type Node to type Element
      Element element = (Element)n;

      // We can retrieve the name of the currently browsed node
      // Using this method, we therefore open our tag
      str += "<" + n.getNodeName();

      // We check the list of attributes present
      if (n.getAttributes() != null && n.getAttributes().getLength() > 0) {

        // We can retrieve the list of attributes of an element
        NamedNodeMap att = n.getAttributes();
        int nbAtt = att.getLength();

        // We browse through all the nodes to display them
        for(int j = 0; j < nbAtt; j++) {
          Node noeud = att.item(j);
          // We retrieve the name of the attribute and its value thanks to these two methods
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

  /**
  * Method that gets an audiobook and checks if the category and language are correct
  * @param node
  *           Node from which we retrieve audiobooks
  * @throws NotACategoryException
  *						If the parameter entered by the user is not an existing category
  * @throws NotALanguageException
  *						If the parameter entered by the user is not an existing language
  * @return An audiobook
  */
  private AudioBook getAudioBooks(Node node) throws NotACategoryException, NotALanguageException {
    String title = "";
    String auteur ="";
    int duree = 1;
    String contenu = "";
    String id = "";
    String language = "";
    String categorie = "";

    // We are now going to process the child nodes of the node being processed
    int nbChild = node.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = node.getChildNodes();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node secondNode = list.item(i);

      // If the child node is an Element, we treat it
      if (secondNode instanceof Element) {
        switch (secondNode.getNodeName()) {
          case "titlebook":
          title = secondNode.getTextContent();
          break;
          case "authorbook":
          auteur = secondNode.getTextContent();
          break;
          case "durationbook":
          duree =Integer.parseInt(secondNode.getTextContent());
          break;
          case "idbook":
          id = secondNode.getTextContent();
          break;
          case "contentsbook":
          contenu = secondNode.getTextContent();
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
            if (secondNode.getTextContent().equals(languages.get(l))) {
              language = secondNode.getTextContent();
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
            if (secondNode.getTextContent().equals(categories.get(c))) {
              categorie = secondNode.getTextContent();
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

  /**
  * Method that gets the list of audiobooks
  * @return The list of audiobooks
  * @see getAudioBooks
  */
  public LinkedList<AudioBook> getListAudioBooks() {
    Node audios = docMedias.getFirstChild();
    int nbChild = audios.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = audios.getChildNodes();

    LinkedList<AudioBook> audioBooksList = new LinkedList<AudioBook>();
    // We go through the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node nodeAudioBook = list.item(i);

      // If the child node is an Element, we treat it
      if (nodeAudioBook instanceof Element) {
        if (nodeAudioBook.getNodeName().equals("audiobook")) {
          try {
            audioBooksList.add(getAudioBooks(nodeAudioBook));
          } catch (NotACategoryException exceptCat) {
          } catch (NotALanguageException exceptLang) {
          }
        }
      }
    }
    return audioBooksList;
  }


  /**
  * Method that gets a song and checks if the gender is correct
  * @param node
  *           Node from which we retrieve songs
  * @throws NotAGenreException
  *						If the parameter entered by the user is not an existing genre
  * @return A song
  */
  private Song getSong(Node node) throws NotAGenreException {
    String title = "";
    String artiste = "";
    int duree = 1;
    String contenu = "";
    String id = "";
    String genre = "";

    // We are now going to process the child nodes of the node being processed
    int nbChild = node.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = node.getChildNodes();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node secondNode = list.item(i);

      // If the child node is an Element, we treat it
      if (secondNode instanceof Element) {
        switch (secondNode.getNodeName()) {
          case "titlesong":
          title = secondNode.getTextContent();
          break;
          case "artistsong":
          artiste = secondNode.getTextContent();
          break;
          case "durationsong":
          duree = Integer.parseInt(secondNode.getTextContent());
          break;
          case "idsong":
          id = secondNode.getTextContent();
          break;
          case "contentssong":
          contenu = secondNode.getTextContent();
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
            if (secondNode.getTextContent().equals(genres.get(g))) {
              genre = secondNode.getTextContent();
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

  /**
  * Method to get the song list
  * @return The list of songs
  * @see getSong
  */
  public LinkedList<Song> getListSongs() {
    Node songs = docMedias.getFirstChild();

    // We retrieve the list of child nodes
    NodeList list = songs.getChildNodes();

    LinkedList<Song> songsList = new LinkedList<Song>();

    // We go through the list of nodes
    for(int i = 0; i < list.getLength(); i++) {
      Node nodeSong = list.item(i);

      // If the child node is an Element, we treat it
      if (nodeSong instanceof Element) {
        if (nodeSong.getNodeName().equals("song")) {
          try {
            songsList.add(getSong(nodeSong));
          } catch (NotAGenreException exceptGen) {
          }
        }
      }
    }
    return songsList;
  }


  /**
  * Method that gets an album
  * @param node
  *           Node from which we retrieve albums
  * @return An album
  * @see getSong
  */
  private Album getAlbum(Node node) {
    String title = "";
    String artist = "";
    int duration = 0;
    int date = 0;
    String id = "0-0-0-0-0";
    LinkedList<Song> songs = new LinkedList<Song>();

    // We are now going to process the child nodes of the node being processed
    int nbChild = node.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = node.getChildNodes();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node secondNode = list.item(i);

      // If the child node is an Element, we treat it
      if (secondNode instanceof Element) {
        switch (secondNode.getNodeName()) {
          case "title":
          title = secondNode.getTextContent();
          break;
          case "artist":
          artist = secondNode.getTextContent();
          break;
          case "duration":
          duration = Integer.parseInt(secondNode.getTextContent());
          break;
          case "date":
          date = Integer.parseInt(secondNode.getTextContent());
          break;
          case "id":
          id = secondNode.getTextContent();
          break;
          case "song":
          try {
            songs.add(getSong(secondNode));
          } catch (NotAGenreException exceptGen) {
          }
          break;
        }
      }
    }

    return new Album(title, date, duration, songs, artist, id);
  }


  /**
  * Method to get the album list
  * @return The list of albums
  * @see getAlbum
  */
  public LinkedList<Album> getListAlbum() {
    Node albums = docAlbum.getFirstChild();
    int nbChild = albums.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = albums.getChildNodes();

    LinkedList<Album> albumsList = new LinkedList<Album>();

    // We browse the list of nodes
    for(int i = 0; i < nbChild; i++) {
      Node nodeAlbum = list.item(i);

      // If the child node is an Element, we treat it
      if (nodeAlbum instanceof Element) {
        albumsList.add(getAlbum(nodeAlbum));
      }
    }
    return albumsList ;
  }


  /**
  * Method that gets a playlist
  * @param node
  *           Node from which we retrieve playlists
  * @return A playlist
  * @see getAudioBooks
  * @see getSong
  */
  private Playlist getPlaylist(Node node) {
    String name = "";
    UUID id = UUID.randomUUID();
    LinkedList<Song> songs = new LinkedList<Song>();
    LinkedList<AudioBook> audiobooks = new LinkedList<AudioBook>();

    // We are now going to process the child nodes of the node being processed
    int nbChild = node.getChildNodes().getLength();
    // We retrieve the list of child nodes
    NodeList list = node.getChildNodes();

    // We browse the list of nodes
    for(int i = 0; i < list.getLength(); i++) {
      Node secondNode = list.item(i);

      // If the child node is an Element, we treat it
      if (secondNode instanceof Element) {
        switch (secondNode.getNodeName()) {
          case "name":
          name = secondNode.getTextContent();
          break;
          case "id":
          id = UUID.fromString(secondNode.getTextContent());
          break;
          case "book":
          try {
            audiobooks.add(getAudioBooks(secondNode));
          } catch (NotACategoryException exceptCat) {
          } catch (NotALanguageException exceptLang) {
          }
          break;
          case "song":
          try {
            songs.add(getSong(secondNode));
          } catch (NotAGenreException exceptGen) {
          }
          break;
        }
      }
    }

    return new Playlist(name, id, songs, audiobooks);
  }

  /**
  * Method to get the playlist list
  * @return The list of playlists
  * @see getPlaylist
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






  /**
  * Save the util lists in the xml files
  * @param util
  *           The util lists that contains the lists of audiobooks, songs, albums and playlists
  * @see writeXml
  */
  public void setXmlWithUtilList(Util util) {
    writeXml(filepathMedias, util);
    writeXml(filepathAlbum, util);
    writeXml(filepathPlaylist, util);
  }

  /**
  * Write the xml file with the util content
  * @param whereToWrite
  *           The name of the filepath of the xml where we gonna write
  * @param util
  *           The util lists that contains the lists of audiobooks, songs, albums and playlists
  */
  public void writeXml(String whereToWrite, Util util) {
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
          // Create an element playlist
          Element playlist = document.createElement("playlist");
          // Create an element name
          Element name = document.createElement("name");
          name.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getName()));
          playlist.appendChild(name);
          // Create an element id
          Element id = document.createElement("id");
          id.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getId()));
          playlist.appendChild(id);
          // Add the songs
          for (int j=0 ; j<util.getPlaylistsListForXml().get(i).getSongsList().size(); j++) {
            Element song = document.createElement("song");
            playlist.appendChild(song);
            // Create an element titlesong
            Element titlesong = document.createElement("titlesong");
            titlesong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getTitle()));
            song.appendChild(titlesong);
            // Create an element artistsong
            Element artistsong = document.createElement("artistsong");
            artistsong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getArtist()));
            song.appendChild(artistsong);
            // Create an element durationsong
            Element durationsong = document.createElement("durationsong");
            String duration = String.valueOf(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getDuration());
            durationsong.appendChild(document.createTextNode(duration));
            song.appendChild(durationsong);
            // Create an element idsong
            Element idsong = document.createElement("idsong");
            idsong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getId()));
            song.appendChild(idsong);
            // Create an element contentssong
            Element contentssong = document.createElement("contentssong");
            contentssong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getContent()));
            song.appendChild(contentssong);
            // Create an element genresong
            Element genresong = document.createElement("genresong");
            genresong.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getSongsList().get(j).getGenreForXML()));
            song.appendChild(genresong);
          }
          // Add the audiobooks
          for (int j=0 ; j<util.getPlaylistsListForXml().get(i).getAudioBooksList().size(); j++) {
            Element book = document.createElement("book");
            playlist.appendChild(book);
            // Create an element titlesong
            Element titlebook = document.createElement("titlebook");
            titlebook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getTitle()));
            book.appendChild(titlebook);
            // Create an element artistsong
            Element authorbook = document.createElement("authorbook");
            authorbook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getAuthor()));
            book.appendChild(authorbook);
            // Create an element durationsong
            Element durationbook = document.createElement("durationbook");
            String duration = String.valueOf(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getDuration());
            durationbook.appendChild(document.createTextNode(duration));
            book.appendChild(durationbook);
            // Create an element idsong
            Element idbook = document.createElement("idbook");
            idbook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getId()));
            book.appendChild(idbook);
            // Create an element contentssong
            Element contentsbook = document.createElement("contentsbook");
            contentsbook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getContent()));
            book.appendChild(contentsbook);
            // Create an element languagebook
            Element languagebook = document.createElement("languagebook");
            languagebook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getLanguage().toUpperCase()));
            book.appendChild(languagebook);
            // Create an element categorybook
            Element categorybook = document.createElement("categorybook");
            categorybook.appendChild(document.createTextNode(util.getPlaylistsListForXml().get(i).getAudioBooksList().get(j).getCategory().toUpperCase()));
            book.appendChild(categorybook);
          }
          // Add the playlist
          playlists.appendChild(playlist);
        }
      } else if (whereToWrite.compareTo(filepathAlbum)==0) {
        Element albums = document.createElement("albums");
        document.appendChild(albums);
        for (int i=0 ; i < util.getAlbumsListForXml().size() ; i++) {
          // Create an element album
          Element album = document.createElement("album");
          // Create an element title
          Element title = document.createElement("title");
          title.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getTitle()));
          album.appendChild(title);
          // Create an element artist
          Element artist = document.createElement("artist");
          artist.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getArtist()));
          album.appendChild(artist);
          // Create an element duration
          Element duration = document.createElement("duration");
          duration.appendChild(document.createTextNode(String.valueOf(util.getAlbumsListForXml().get(i).getDuration())));
          album.appendChild(duration);
          // Create an element date
          Element date = document.createElement("date");
          date.appendChild(document.createTextNode(String.valueOf(util.getAlbumsListForXml().get(i).getDate())));
          album.appendChild(date);
          // Create an element id
          Element id = document.createElement("id");
          id.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getId()));
          album.appendChild(id);
          // Add the songs
          for (int j=0 ; j<util.getAlbumsListForXml().get(i).getSongsList().size(); j++) {
            Element song = document.createElement("song");
            album.appendChild(song);
            // Create an element titlesong
            Element titlesong = document.createElement("titlesong");
            titlesong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getTitle()));
            song.appendChild(titlesong);
            // Create an element artistsong
            Element artistsong = document.createElement("artistsong");
            artistsong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getArtist()));
            song.appendChild(artistsong);
            // Create an element durationsong
            Element durationsong = document.createElement("durationsong");
            durationsong.appendChild(document.createTextNode(String.valueOf(util.getAlbumsListForXml().get(i).getSongsList().get(j).getDuration())));
            song.appendChild(durationsong);
            // Create an element idsong
            Element idsong = document.createElement("idsong");
            idsong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getId()));
            song.appendChild(idsong);
            // Create an element contentssong
            Element contentssong = document.createElement("contentssong");
            contentssong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getContent()));
            song.appendChild(contentssong);
            // Create an element genresong
            Element genresong = document.createElement("genresong");
            genresong.appendChild(document.createTextNode(util.getAlbumsListForXml().get(i).getSongsList().get(j).getGenreForXML()));
            song.appendChild(genresong);
          }
          // Add the album
          albums.appendChild(album);
        }
      } else {
        Element elements = document.createElement("elements");
        document.appendChild(elements);
        for (int j=0 ; j<util.getSongsListForXml().size() ; j++) {
          Element song = document.createElement("song");
          // Create an element titlesong
          Element titlesong = document.createElement("titlesong");
          titlesong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getTitle()));
          song.appendChild(titlesong);
          // Create an element artistsong
          Element artistsong = document.createElement("artistsong");
          artistsong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getArtist()));
          song.appendChild(artistsong);
          // Create an element durationsong
          Element durationsong = document.createElement("durationsong");
          String duration = String.valueOf(util.getSongsListForXml().get(j).getDuration());
          durationsong.appendChild(document.createTextNode(duration));
          song.appendChild(durationsong);
          // Create an element idsong
          Element idsong = document.createElement("idsong");
          idsong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getId()));
          song.appendChild(idsong);
          // Create an element contentssong
          Element contentssong = document.createElement("contentssong");
          contentssong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getContent()));
          song.appendChild(contentssong);
          // Create an element genresong
          Element genresong = document.createElement("genresong");
          genresong.appendChild(document.createTextNode(util.getSongsListForXml().get(j).getGenreForXML()));
          song.appendChild(genresong);

          // Add the song
          elements.appendChild(song);
        }
        for (int j=0 ; j<util.getAudioBooksListForXml().size() ; j++) {
          Element audiobook = document.createElement("audiobook");
          // Create an element titlesong
          Element titlebook = document.createElement("titlebook");
          titlebook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getTitle()));
          audiobook.appendChild(titlebook);
          // Create an element artistsong
          Element authorbook = document.createElement("authorbook");
          authorbook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getAuthor()));
          audiobook.appendChild(authorbook);
          // Create an element durationsong
          Element durationbook = document.createElement("durationbook");
          durationbook.appendChild(document.createTextNode(String.valueOf(util.getAudioBooksListForXml().get(j).getDuration())));
          audiobook.appendChild(durationbook);
          // Create an element idsong
          Element idbook = document.createElement("idbook");
          idbook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getId()));
          audiobook.appendChild(idbook);
          // Create an element contentssong
          Element contentsbook = document.createElement("contentsbook");
          contentsbook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getContent()));
          audiobook.appendChild(contentsbook);
          // Create an element languagebook
          Element languagebook = document.createElement("languagebook");
          languagebook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getLanguage().toUpperCase()));
          audiobook.appendChild(languagebook);
          // Create an element categorybook
          Element categorybook = document.createElement("categorybook");
          categorybook.appendChild(document.createTextNode(util.getAudioBooksListForXml().get(j).getCategory().toUpperCase()));
          audiobook.appendChild(categorybook);
          // Add the audiobook
          elements.appendChild(audiobook);
        }
      }
      // Create a xml file with identation
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
