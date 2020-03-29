/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

import javafx.util.Pair;
import java.lang.String;
import java.io.*;
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
    
}
