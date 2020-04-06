/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author ttoyr
 */
public class AudioStream implements Observable {
    private Media media;
    private MediaPlayer mp;
    private MediaView mv;
    private Observer observer = null;
    public AudioStream()
    {
        mp = null;
    }
    public void newStream(String fp)
    {
        String uriString = new File(fp).toURI().toString();
        media = new Media(uriString);
        mp = new MediaPlayer(media);
        mv = new MediaView(mp);
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
        observer.update(this);
    }
    public MediaPlayer getMediaPlayer()
    {
        return mp;
    }
    
}
