/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

// Octave imports
import octave_player.OctaveController;
import octave_player.Playlist;
import octave_player.Queue;
import octave_player.Observable;
import octave_player.Observer;

// Containers
import java.util.ArrayList;

//JavaFX imports
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaView;


/**
 *
 * @author Regina Fanelli 
 * View class for the Octave media player. This call will function to provide
 * a GUI for the octave player. The core of the GUI should be a javafx mediaView 
 * instance containing the volume controls, media scroll bars, a playlist viewer,
 * and a queue viewer. 
 */
public class OctaveView implements Observer {
    Stage stage;
    OctaveController controller;
    
    /**
     * 
     * @param stage
     * @param controller 
     * @param q
     * @param playlists
     */
    public OctaveView(Stage stage, OctaveController controller, 
            Queue q,  ArrayList<Playlist> playlists) {
        this.controller = controller;
        this.stage = stage;
        // Once Observer and Observable interfaces are written, the constructor
        // will need to bind itself as an observer to each of the observables
        // Observables: The queue, and the playlists
        
        // Just some stuff Mike put in to test whether stages can be passed as
        // references. 
        Label lbl1 = new Label("Octave");
        GridPane pane = new GridPane();
        pane.add(lbl1, 0, 0);
        Scene mainScene = new Scene(pane,700,600);
        this.stage.setScene(mainScene);
        this.stage.show();
    }
    
    /**
     * 
     * @param o object implementing observable interface. This 
     */
    public void update(Observable o) {
        //
    }
}
