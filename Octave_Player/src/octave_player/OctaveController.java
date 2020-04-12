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

// javafx imports 
import javafx.scene.media.MediaPlayer;

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
    
    /**
     * Searches the playlists via the searchPlaylist driver method. Adds this
     * playlist to the Queue.
     */
    public void loadPlaylist() {
        
    }
    
    /**
     * Searches the playlists via the searchPlaylist driver method. Adds the
     * Songs in the playlist to the PlaylistsSongs container. This is the 
     * Container in the view which visually lists the songs in a playlist. 
     */
    public void loadPlaylistSongs() {
        
    }
    
    
    /**
     * 
     */
    public void loadNextFromQueue() {
        System.out.println("Loading next from the queue");
        // if queue is not empty, load the next song. 
    }
    
    public void setStatusPlay() {
        AudioStream stream = driver.getStream();
        if (stream == null)
            return; 
        switch (stream.getStatus()) {
            case PAUSED:
            case STOPPED:
            case READY:
                stream.play();
                break;
            case STALLED:
            case PLAYING:
                // stream buffering. If we had more time, we could implement a 
                // sleep, and then retry, or we could alert user media is buffering
                return;
            default:
                // in this case stream is halted, disposed, or unknown state
                // best to alert view so it can alert user and 
                //create a new stream can be created
                stream.alert();
        }
    }
    public void setStatusPaused() {
        AudioStream stream = driver.getStream();
        if (stream == null)
            return;
        switch (stream.getStatus()) {
            case PLAYING:
                stream.pause();
                return; 
            case PAUSED:
            case STOPPED:
            case READY:
            case STALLED:
                // stream already in a "paused" like state
                return;
            default:
                // in this case stream is halted, disposed, or unknown state
                // best to alert view so it can alert user and 
                //create a new stream can be created
                stream.alert();
        }
    }
    /**
     * Attempts to set the status of the AudioStream to stopped, pausing the 
     * song and setting it's playback to time 0. If the stream is in a failed
     * state (Halted, unknown, disposed), then alert if fired so view may 
     * alert the user and the stream can update its mediaPlayer/media. 
     */
    public void setStatusStopped() {
        AudioStream stream = driver.getStream();
        if (stream == null)
            return;
        switch (stream.getStatus()) {
            case PLAYING:
            case PAUSED:
            case STALLED:
                stream.stop();
                return; 
            case STOPPED:
            case READY:
                // stream already in a "stopped" like state (paused at start)
                return;
            default:
                // in this case stream is halted, disposed, or unknown state
                // best to alert view so it can alert user and 
                //create a new stream can be created
                stream.alert();
        }
    }
    /**
     * @param volumePercent
     */
    public void setVolume(double volumePercent) {
        AudioStream stream = driver.getStream();
        if (stream == null)
            return;
        stream.setVolume(volumePercent/100.0);
        System.out.println("Volume set to:" + (int)(volumePercent)+"%");
    }
    
    /**
     * 
     * @param seekTime: Time that the user scrolled the playback slider to. 
     */
    public void seekSong(double seekTime) {
        driver.getStream().setPlayback(seekTime);
    }   
    
    /**
     * Attempts to skip the Current song and begin playing the next
     *  - if no more songs in queue, then empty they AudioStream and set the 
     *   seeker to 0
     * - otherwise pop the next song off the queue, and load that into the 
     *  audioStream.
     */
    public void skipSong(){
        Queue q = driver.getQueue();
        AudioStream stream = driver.getStream();
        if(q.hasNext())
        {
            Song nextSong = q.popFromQueue();
            stream.stop();
            stream.loadSong(nextSong);
            stream.play();
        }
        else{
            stream.stop();
            stream.emptyPlayer();
        }     
        stream.alert();
    }
    
    /**
     * Re-plays the currently playing song. This can be implemented by calling 
     * the stop() and play() methods sequentially on the audioStream.
     */
    public void replay() {
        AudioStream stream = driver.getStream();
        if (stream == null)
            return;
        stream.stop();
        stream.play();
        stream.alert();
    }
    
}
