/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

import javafx.util.Pair;
import java.lang.String;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/* JDOM Imports
import org.w3c.dom.*;
import javax.xml.parsers.*;
*/


/**
 *
 * @author Nathan Valascho
 * This class is aggregated by the Playlist class, and functions to read the 
 * JSON or XML provided to it by it's aggregating Playlist instance. In turn 
 * it will parse the file into a list of Song pairs, the first string being
 * the song name, the second being the path to the song. 
 * 
 * notes for Class development
 *  - Should check that file extensions belong to the following list of 
 * file types supported by javafx MediaPlayer. This class should somehow alert 
 * the Playlist object of songs which are of incorrect file type. 
 *      - .mp3, .aif, .aiff, .wav
 *  - Apparently Java has multiple build in XML parser, but no native json parser. 
 *      - If you wanna go the json rout then this library may help: 
 *      https://github.com/stleary/JSON-java\
 *      - If you go the XML rout, then you can use the build in parser Com
 *      https://www.tutorialspoint.com/java_xml/java_dom_parser.htm
 */
public class PlaylistReader {
    
    
    
    public PlaylistReader(){
    }
    
    /*precondition:Assumes file is the name of an XML file. Assumes file has no 
      other tags nested within the "song" tags or within the "location" tags
    postcondition: returns a list of Songs from file. If a song is missing  name 
    or location it is not inlcuded in the songList. If the XML file has formatting 
    errors the song List that is returned is null
     */       
    public static ArrayList<Song> getPlaylist(String file) 
    {
        ArrayList<Song> playList = new ArrayList<Song>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList songList = doc.getElementsByTagName("song");
            for(int i = 0; i < songList.getLength(); i++)
            {
                Node loopSong = songList.item(i);
                if(loopSong.getNodeType() == Node.ELEMENT_NODE)
                {
                    NodeList children = loopSong.getChildNodes();
                    String songName = "";
                    String songLoc = "";
                    boolean foundName = false;
                    boolean foundLoc = false;
                    for(int x = 0; x < children.getLength(); x++)
                    {
                        Node childNode = children.item(x);
                        if(childNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            if(childNode.getNodeName().equals("name"))
                            {
                                songName = childNode.getTextContent();
                                foundName = true;
                            }
                            else if(childNode.getNodeName().equals("location"))
                            {
                                songLoc = childNode.getTextContent();
                                foundLoc = true;
                            }
                            
                        }                            
                    }
                    
                    if(foundName && foundLoc)
                    {
                        //System.out.println("SONG NAME; " + songName + " LOCATION: " + songLoc);
                        Song temp = new Song(songName, songLoc);
                        playList.add(temp);
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PlaylistReader.class.getName()).log(Level.SEVERE, null, ex);
            playList = null;
        } catch (SAXException ex) {
            Logger.getLogger(PlaylistReader.class.getName()).log(Level.SEVERE, null, ex);
            playList = null;
        } catch (IOException ex) {
            Logger.getLogger(PlaylistReader.class.getName()).log(Level.SEVERE, null, ex);
            playList = null;
        }
        return playList;
    }
}
