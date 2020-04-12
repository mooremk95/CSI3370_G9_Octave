/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;


import java.lang.RuntimeException;

//IO
import java.io.*;
import java.util.Scanner;


//Containers
import java.util.ArrayList;
import java.util.Arrays;

// Octave classes
import octave_player.OctaveView;
import octave_player.OctaveController;
import octave_player.Queue;
import octave_player.Playlist;

//JavaFX stuff
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
    // private Playlist allSongs;
    private AudioStream as;
    private Queue q;
    private OctaveView mainView;
    
    // Filename filter is an interface. Create object implementing
    // interface to filter based on .opl extension
    final private FilenameFilter filter = new FilenameFilter() {
            public boolean accept (File directory, String filename) {
                return filename.endsWith(".opl"); 
            }
    };
    
    // Main method and javfx start method
    @Override
    public void start(Stage primaryStage) throws IOException { 
        primaryStage.setTitle("Octave Player");
        
        OctaveController controller = new OctaveController(this);
        mainView = new OctaveView(primaryStage, controller);
                
        q = new Queue(mainView);
	as = new AudioStream(mainView);
        playlists = getPlaylists(mainView); 
        // Replace the songName and file path strings with your own to test
        // the player out. 

        Song s = new Song("You Shook Me All Night Long",
                          "C:\\Users\\ttoyr\\Desktop\\You Shook Me All Night Long.MP3");
        as.loadSong(s); // Normally controller would call this when loading the next song in loadNextFromQueue()
        as.setVolume(0.66); // volume on program startup is 66/
    }
    
    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Playlist Stuff ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    // Driver class methods
    
      /**
     * @throws IOException
     * @return ArrayList of Playlist objects
     * Searches the runtime directory for .opl files. This extension is used
     * by Octave player to denote XML files containing Playlist data.
     */
    public ArrayList<Playlist> getPlaylists(OctaveView observer) throws IOException {
        ArrayList<Playlist> playlists = new ArrayList();
        File runtimeDir =  new File(".");
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
    
    public void createPlaylist(ArrayList<Song> songList, String name) throws IOException, RuntimeException {
        // throw on empty argument errors
        if (songList.isEmpty()) {
            throw new RuntimeException("Songlist passed to CreatePlaylist was empty.");
        } else if (name.isEmpty()) {
            throw new RuntimeException("Playlist name passed to CreatePlaylist was the empty string.");
        }
        
        try {
            File playlist = new File(name+".opl");
            if (playlist.createNewFile()) {
                FileWriter writer = new FileWriter(name+".opl");
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
                             "<playlist>");
                for (Song song:songList) {
                    writer.write("\n\t<song>\n\t\t<name>" + song.getName()+ 
                                "</name>\n\t\t<location>" + song.getFilePath()+
                                "</location>\n\t</song>");
                }
                writer.write("\n</playlist>");
                writer.close();
                // Add as a new playlist to current Octave session
                playlists.add(new Playlist(name+".opl",mainView));
            } else {
                // Call view and alert user of existing playlist
                // ask for overwrite permission 
            }
        } catch (IOException e) {
            // call view, alert user name of their playlist is invalid (eg. contains \)
        }
    } 
    
    public void deletePlaylist(String name) throws IOException {
        File playlistFile = new File(name+".opl");
        if (playlistFile.exists()) {
            playlistFile.delete();
            for (Playlist playlist : playlists) {
                if (name.equals(playlist.getName())){
                    playlists.remove(playlist);
                    break;
                }
            }
        } else {
            // Call view, alert playlist to delete DNE
        }
    }
    
    public Playlist searchPlaylists(String name){
        for(int i = 0; i < playlists.size(); i++)
        {
            if(playlists.get(i).getName().equals(name))
            {
                return playlists.get(i);
            }
            else
            {
                System.out.println("Selected playlist not found by searchPlaylists method");
            }
        }
        return null;
    }
    
    // getters
    public OctaveView getView() {
        return mainView;
    }
    
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    
    public Queue getQueue() {
        return q;
    }
    
    public AudioStream getStream() {
        return as;
    }
}
