/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

// octave imports
import octave_player.Playlist;
import octave_player.Queue;
import octave_player.OctaveView;

import java.lang.String;
// Containers
import java.util.AbstractList;

/**
 *
 * @author Kyle Sienkiewicz, Emily Locke
 * This is the controller for the Octave player. Its methods will edit the model
 * classes (Queue, and the AudioStream via the Octave_Player main class). 
 */
public class OctaveController {
    int volume;
    String timeStamp;
    Octave_Player driver; // access the models via the main class
    
    // Might need to add a reference to the Queue as well. This would be passe
    // by reference in the Constructor via the main function
    
    public OctaveController(Octave_Player mainInstance){
        driver = mainInstance;
    }
    
    public void testAccess() {
        for (Playlist p: driver.getPlaylists())
            System.out.println(p);
    }
}
