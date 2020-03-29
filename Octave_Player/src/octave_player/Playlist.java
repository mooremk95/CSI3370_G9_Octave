/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

import javafx.util.Pair;


/**
 *
 * @author Nathan Valascho
 * This class implements a playlist. The constructor is passed a file path in
 * the form of a string, and in checks that this is indeed a valid path to a 
 * JSON/XML file. On being added to the queue, the playlist creates an instance 
 * of PlaylistRearder to parse the playlist file's JSON or XML into a list of 
 * "songs". Songs are simply a pair of strings, the first string being the 
 * song title, the second being a path to the file location. The playlist's list
 * of songs is then appended to the queue to be played by Octave. 
 */
public class Playlist {
    
}
