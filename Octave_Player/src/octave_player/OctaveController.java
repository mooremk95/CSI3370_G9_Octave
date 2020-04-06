/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

import java.io.IOException;

// octave imports
import octave_player.Playlist;
import octave_player.Queue;
import octave_player.OctaveView;
import octave_player.Song;

import java.lang.String;
// Containers
import java.util.ArrayList;

/**
 *
 * @author Kyle Sienkiewicz, Emily Locke
 * This is the controller for the Octave player. Its methods will edit the model
 * classes (Queue, and the AudioStream via the Octave_Player main class). 
 */
public class OctaveController {
    private int volume;
    private String timeStamp;
    final private Octave_Player driver; // access the models via the main class
    
    // Might need to add a reference to the Queue as well. This would be passe
    // by reference in the Constructor via the main function
    
    public OctaveController(Octave_Player mainInstance){
        driver = mainInstance;
    }
    
    // Implemented as a simple test function to test createPlaylist in 
    // driver class
    public void playlistCreation() throws IOException {
        ArrayList<Song> songlist = new ArrayList<Song>();  
        songlist.add(new Song("Break On Through","C:\\Users\\Mike\\Music\\The Doors - STUDIO DISCOGRAPHY\\1967 - The Doors\\The Doors - Break On Through.MP3"));
        songlist.add(new Song("The End","C:\\Users\\Mike\\Music\\The Doors - STUDIO DISCOGRAPHY\\1967 - The Doors\\The Doors - The End.MP3"));
        songlist.add(new Song("La Woman","C:\\Users\\Mike\\Music\\The Doors - STUDIO DISCOGRAPHY\\1971 - L.A.Woman\\The Doors - La Woman.MP3"));
        songlist.add(new Song("Riders On The Storm","C:\\Users\\Mike\\Music\\The Doors - STUDIO DISCOGRAPHY\\1971 - L.A.Woman\\The Doors - Riders On The Storm.MP3"));
        String playlistName = "The Doors";
        driver.createPlaylist(songlist, playlistName);
        
    }
}
