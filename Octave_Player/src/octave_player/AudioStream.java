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
        try{
            String uriString = new File(fp).toURI().toString();
            media = new Media(uriString);
            mp = new MediaPlayer(media);
            if (media.getError() == null && mp.getError() == null) {
                alert();
                return true;
            }
            media = null;
            mp = null;
            return false;
        } catch (Exception e) {
            System.out.println(e);
            media = null;
            return false;
        }
    }
    /**
     * 
     * @param song :  Song to be loaded from the queue
     */
    public void loadFromQueue(Song song) {
        if (newStream(song.getFilePath())) {
            mp.play();
        } else {
            // alert with null mediaplayer implies failure to load song from queue 
            alert();
        }
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
        //observer.update(this);
    }
    
    // Methods allowing controller to modify the stream
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
     * Toggles mute. Switches the mute state of the player on/off
     */
    public void toggleMute() {
        if (mp == null)
            return;
        if (mp.isMute()){
            mp.setMute(false);
        }
        else
            mp.setMute(true);
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
        Duration seekTime = new Duration(time);
        // Similarly, don't change seek time if time passed is beyond song duration
        if (media.getDuration().compareTo(seekTime) > 1)
            return;
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
    public double getSongtimeSeconds() {
        return media.getDuration().toSeconds();
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
     */
    public MediaPlayer.Status getStatus() {
        return mp.getStatus();
    }

}
