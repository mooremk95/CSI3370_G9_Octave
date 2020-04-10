/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import octave_player.Song;
import java.net.URISyntaxException;

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
    public AudioStream()
    {
        mp = null;
    }
    /**
     * 
     * @param fp : string to be converted to a URI for the current song 
     */
    public void newStream(String fp)
    {
        try{
            String uriString = new File(fp).toURI().toString();
            media = new Media(uriString);
        } catch (Exception e) {
            System.out.println(e);
        }
        mp = new MediaPlayer(media);
        mp.play();
        
        alert();
    }
    
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
    public MediaPlayer getMediaPlayer()
    {
        return mp;
    }
    
    // Methods allowing controller to modofy the stream
    public void play()
    {
        if (mp != null){
            mp.play();
        }
    }
    public void pause()
    {
        if (mp != null) {
            mp.pause();
        }
    }
    
    /**
     * Stops the music stream, and resets it back to the start of the song in 
     * a paused state. 
     */
    public void stop() {
        mp.stop();
    }
    /**
     * Toggles mute. Switches the mute state of the player on/off
     */
    public void toggleMute() {
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
        int overMaxVol = Double.compare(value, 1.0);
        int underMinVol = Double.compare(value,0.0);
        if (overMaxVol < 1 && underMinVol >= 0)
            mp.setVolume(value);
        // no nedd to alert 
    }

    /**
     * Change the media stream to the song provided
     * @param s 
     */
}
