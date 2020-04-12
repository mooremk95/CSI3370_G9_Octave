/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.lang.Runnable;


/**
 *
 * @author ttoyr 
 * @author Mike
 */
public class AudioStream implements Observable {
    private Media media = null;
    private MediaPlayer mp = null;
    // May need this, but for now let's true to use the GUI Regina created
    //private MediaView mv = null;
    private Observer observer = null;
    private String songName = null;
    
    public AudioStream(Observer o)
    {
        observer = o;
    }
    /**
     * 
     * @param fp : string to be converted to a URI for the current song 
     * Returns true if the new stream is created successfully, false otherwise.
     */
    private boolean newStream(String fp)
    {
        double currentVolume = 0.0;
        if (mp != null)
            currentVolume = mp.getVolume();
        String uriString = new File(fp).toURI().toString(); 
        media = new Media(uriString);
        mp = new MediaPlayer(media);
        // if mediaPlayer and media are clear of errors, continue
        if (media.getError() == null && mp.getError() == null) {
            mp.setVolume(currentVolume);
            // set alert as callback when mediaPlayer changes state
            mp.setOnReady(() -> { alert(); });
            mp.setOnPlaying(() -> { alert(); });
            mp.setOnPaused(() -> { alert(); });
            mp.setOnStopped(() -> { alert(); });
            mp.setOnStalled(() -> { alert(); });
            mp.setOnHalted(() -> { alert(); });
            mp.setOnEndOfMedia(() -> { 
                System.out.println("\nEnd of Media status: " + mp.getStatus() 
                        + "\nPlayback Time:" +mp.getCurrentTime() 
                        + "\nDuration: " + mp.getCycleDuration());
                alert(); });
            return true;
        }
        // othwrwise set them to null, return false
        media = null;
        mp = null;
        return false;
    }
    /**
     * 
     * @param song :  Song to be loaded from the queue
     */
    public void loadSong(Song song) {
        if (newStream(song.getFilePath())) {
            songName = song.getName();
            //alert();
            //mp.play();
            
        } else {
            alert(); // alert with null mediaplayer implies failure to load song from queue 
        }
        
    }
    /**
     * Sets the mediaPlayer and media to null. This state indicates that no 
     * songs are in the Queue, and that the player is ready to play whenever 
     * songs are added
     */
    public void emptyPlayer() {
        media = null;
        mp = null;
        songName = "";
        alert();
    }
    // Observable interface methods
    @Override
    public void attach(Observer o)
    {
        observer = o;
    }
    @Override
    public void detach(Observer o)
    {
        observer = null;
    }
    @Override
    public void alert()
    {
        observer.update(this);
    }
    
    public boolean songEnded() {
        if (mp != null)
            return mp.getCurrentTime() == mp.getCycleDuration();
        return false;
    }
    
    // Methods allowing controller to modify the stream (Setters)
    public void play()
    {
        if (mp != null && mp.getStatus() != MediaPlayer.Status.PLAYING)
            mp.play();
    }
    public void pause()
    {
        if (mp != null && mp.getStatus() == MediaPlayer.Status.PLAYING) 
            mp.pause();
    }
    
    /**
     * Stops the music stream, and resets it back to the start of the song in 
     * a paused state. 
     */
    public void stop() {
        if (mp != null){
            mp.stop();
        }
            
    }
    /**
     * 
     * @param value : Value to change volume by %. May range from 0.0 to 1.0 
     * If the value is within the range of 0.0 and 1.0 then the volume is 
     * adjusted. Otherwise it remains set to it's previous Value.
     */
    public void setVolume(double value) {
        if (mp == null)
            return;
        int overMaxVol = Double.compare(value, 1.0);
        int underMinVol = Double.compare(value,0.0);
        if (overMaxVol < 1 && underMinVol >= 0)
            mp.setVolume(value);
        // no nedd to alert 
    }
    
    /**
     * @param time: Double, the time to seek to in the song
     */
    public void setPlayback(double time) { 
        // Don't change seek time if argument passed is negative 
        if (Double.compare(time, 0.0) < 0)
            return;
        // Duration is ctor assumes time in milliseconds
        Duration seekTime = new Duration(time*1000.0); 
        // Similarly, don't change seek time if time passed is beyond song duration
        if (media.getDuration().compareTo(seekTime) > 1)
            return;
        System.out.println("Old playback time is: " + 
                mp.getCurrentTime().toSeconds() + " seconds\n New time: " + seekTime.toSeconds());
        mp.seek(seekTime);
    }
    
    // Getters
    
    /**
     * @return songName
     */
    public String getSongName() {
        return songName;
    }
    /**
     * 
     * @return runtime of the current song in seconds
     */
    public double getSongDurationSeconds() {
        return media.getDuration().toSeconds();
    }
    /**
     *  @return double current playback time of the mediaPlayer in seconds
     */
    public double getPlaybacktime() {
        if (mp != null){
            return mp.getCurrentTime().toSeconds();
        }
        return -1.0;
    }
    /**
     * 
     * @return Status of the audioStream as a mediaPlayer status. May be:
     *  - ready
     *  - paused
     *  - playing
     *  - stalled
     *  - stopped
     *  - halting
     *  - null on an uninitialized player
     */
    public MediaPlayer.Status getStatus() {
        if (mp != null)
            return mp.getStatus();
        else
            return null;
    }
}
