/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;




//IO
import java.io.*;
import java.util.Scanner;


//Containers
import java.util.ArrayList;

// Octave classes
import octave_player.OctaveView;
import octave_player.OctaveController;
import octave_player.Queue;
import octave_player.Playlist;

//JavaFX stuff

import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Stage;

/**
 *
 * @author Mike Moore, Trent Toyryla
 * This is the driver class for the Octave music player. It will contain the 
 * Controller and View instances, the playlist instance, the queue (Model class), 
 * and a javafx MediaPlayer object called AudioStream.
 */
public class Octave_Player extends Application {
    private ArrayList<Playlist> playlists;
    private Queue q;
    private OctaveView mainView;
    
    // Main method and javfx start method
    @Override
    public void start(Stage primaryStage) throws IOException { 
        primaryStage.setTitle("Octave Player");
        
        OctaveController controller = new OctaveController(this);
        mainView = new OctaveView(primaryStage, controller);
        q = new Queue();
        q.attach(mainView);
        playlists = getPlaylists(mainView);        

    }
   
    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    // Driver class methods
    
      /**
     * @throws IOException
     * Searches the runtime directory for .opl files. This extension is used
     * by Octave player to denote XML files containing Playlist data.
     */
    public ArrayList<Playlist> getPlaylists(OctaveView observer) throws IOException {
        ArrayList<Playlist> playlists = new ArrayList();
        File runtimeDir =  new File(".");
        // Filename filter is an interface. Create object implementing
        // interface to filter based on .opl extension
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept (File directory, String filename) {
                return filename.endsWith(".opl"); 
            }
        };
        
        if (runtimeDir.isDirectory()) {
            // Fill up the playlist list with Playlist objects
            String[] fileNames = runtimeDir.list(filter);
            for (String fileName : fileNames){
                playlists.add(new Playlist(fileName, observer));
            }
        } else {
            throw new IOException("Somehow runtimeDir is not a directory. :(");
        }
        
        return playlists;
    }
    
    public OctaveView getView() {
        return mainView;
    }
    
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    
    public Queue getQueue() {
        return q;
    }
}
