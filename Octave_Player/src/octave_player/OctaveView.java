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
import java.util.List;

//JavaFX imports
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;


/**
 *
 * @author Regina Fanelli 
 * View class for the Octave media player. This call will function to provide
 * a GUI for the octave player. The core of the GUI should be a javafx mediaView 
 * instance containing the volume controls, media scroll bars, a playlist viewer,
 * and a queue viewer. 
 */
public class OctaveView implements Observer {
    private Stage stage;
    private OctaveController controller;
    
    Image play = new Image("file:play.png");
    Image pause = new Image("file:pause.png");
    Image vol = new Image("file:volume.png");
    Image forward = new Image("file:skipF.png");
    Image previous = new Image("file:skipR.png");
    Image stop = new Image("file:stop.png");
    
    ImageView iv = new ImageView(play);
    ImageView iv2 = new ImageView(pause);
    ImageView iv3 = new ImageView(vol);
    ImageView iv4 = new ImageView(forward);
    ImageView iv5 = new ImageView(previous);
    ImageView iv6 = new ImageView(stop);
    
    
    Button pl, pa, f, p, s;
    Slider seek, v;
    /**
     * 
     * @param stage
     * @param controller 
     */
    public OctaveView(Stage stage, OctaveController controller) {
        this.controller = controller;
        this.stage = stage;
        // Once Observer and Observable interfaces are written, the constructor
        // will need to bind itself as an observer to each of the observables
        // Observables: The queue, and the playlists
    
    /********************Currently Playing**********************/     
        Label current = new Label("Now Playing:");
        current.setFont(new Font(25));
        
        HBox c = new HBox(current);
        c.setAlignment(Pos.CENTER);

    /********************Playlist View**********************/        
        StackPane playlist = new StackPane();
        playlist.setAlignment(Pos.CENTER);
        playlist.setPadding(new Insets(20));
        
    /********************Control Bar**********************/
        //Skip Forward Button
        f = new Button();
        f.setGraphic(iv4);
        iv4.setFitHeight(60);
        iv4.setFitWidth(60);
        
        f.setOnAction(e -> {
            
        });
        
        //Play Button
        pl = new Button();
        pl.setGraphic(iv);
        iv.setFitHeight(60);
        iv.setFitWidth(60);
        
        pl.setOnAction(e -> {
            
        });
        
        //Pause Button
        pa = new Button();
        pa.setGraphic(iv2);
        iv2.setFitHeight(60);
        iv2.setFitWidth(60);
        
        pa.setOnAction(e -> {
            
        });
        
        //Stop Button
        s = new Button();
        s.setGraphic(iv6);
        iv6.setFitHeight(60);
        iv6.setFitWidth(60);
        
        s.setOnAction(e -> {
            
        });
        
        //Skip to Previous Button
        p = new Button();
        p.setGraphic(iv5);
        iv5.setFitHeight(60);
        iv5.setFitWidth(60);
        
        p.setOnAction(e -> {
            
        });
        
        //Seek Slider
        seek = new Slider();
        seek.setMinWidth(500);
        seek.setMinHeight(50);
        HBox hs = new HBox(seek);
        hs.setAlignment(Pos.CENTER);
        
        //Volume Slider
        v = new Slider();
        v.setMinWidth(70);
        HBox hv = new HBox(iv3);
        hv.getChildren().add(v);
        hv.setAlignment(Pos.CENTER);
        iv3.setFitHeight(50);
        iv3.setFitWidth(50);
        
        //Empty Space for Row Spacing
        HBox hb = new HBox();
        hb.setMinHeight(5);
        
        HBox btn_bar = new HBox();
        btn_bar.getChildren().addAll(p, pl, pa, s, f);
        btn_bar.setAlignment(Pos.CENTER);
        btn_bar.setSpacing(20);
        
        
        //Empty Space for Column Spacing
        VBox vb = new VBox();
        vb.setMinWidth(100);
        
        // Just some stuff Mike put in to test whether stages can be passed as
        // references. 
        GridPane gp = new GridPane();
        gp.setStyle("-fx-background-color: grey");
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(40);
        gp.setHgap(5);
        gp.addRow(0,hb);
        gp.add(hv, 4, 1);
        gp.add(btn_bar, 2, 1);
        gp.addColumn(0, vb);
        gp.add(hs, 2, 2);
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: lightgrey");
        root.setPadding(new Insets(20));
        root.setBottom(gp);
        root.setTop(c);
        root.setCenter(playlist);
        
        Scene mainScene = new Scene(root,1000,800);
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
