/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;


// Containers
import java.util.ArrayList;


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
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author Regina Fanelli 
 * View class for the Octave media player. This call will function to provide
 * a GUI for the octave player. The core of the GUI should be a javafx mediaView 
 * instance containing the volume controls, media scroll bars, a playlist viewer,
 * and a queue viewer. 
 */
public class OctaveView implements Observer {
    final private Stage stage;
    final private OctaveController controller;
    final private MediaView mv = new MediaView();
    private String playingSongName = "";
    private String lastPlaylist;
    private ArrayList<Label> playlistSongs = new ArrayList<>(); // Contains the songs in the selected playlist
    private boolean isPlaying = false;
    // This clock runs when status is playing. Calls a method to progress the seek bar. 
    private ExecutorService playingClock = Executors.newCachedThreadPool(); 
    
    Label current;
    
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
    
    VBox queueBox = new VBox();
    VBox playlistBox = new VBox();
    VBox playlistSongsBox = new VBox();
    
    
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
        current = new Label("Now Playing:"); // changed this label to a data member so update can change it 
        current.setFont(new Font(25));
        current.setPadding(new Insets(0,0,10,0));
      
        HBox c = new HBox(current);
        c.setAlignment(Pos.CENTER);
        
    /********************Queue View**********************/
        Label queuel = new Label("Queue");
        queuel.setFont(new Font(20));
        queuel.setPadding(new Insets(5,0,0,0));
        VBox list1 = new VBox(queuel, queueBox);
        list1.setStyle("-fx-border-color: black");
        list1.setStyle("-fx-border-insets: 5");
        list1.setStyle("-fx-border-width: 2");
        list1.setStyle("-fx-border-style: solid inside");
        list1.setMinSize(166, 460);
        list1.setAlignment(Pos.CENTER);
        queueBox.setAlignment(Pos.TOP_CENTER);
        queueBox.setPadding(new Insets(20));
        queueBox.setPrefSize(300, 460);

    /********************Playlist View**********************/        
        Label playl = new Label("Playlists");
        playl.setFont(new Font(20));
        playl.setPadding(new Insets(5,0,0,0));
        VBox list2 = new VBox(playl, playlistBox);
        list2.setStyle("-fx-border-color: black");
        list2.setStyle("-fx-border-insets: 5");
        list2.setStyle("-fx-border-width: 2");
        list2.setStyle("-fx-border-style: solid inside");
        list2.setMinSize(166, 460);
        list2.setAlignment(Pos.CENTER);
        playlistBox.setAlignment(Pos.TOP_CENTER);
        playlistBox.setPadding(new Insets(20));
        playlistBox.setMinSize(166, 460);
        
    /********************Playlist Song View**********************/
        Label sl = new Label("Playlist Songs");
        sl.setFont(new Font(20));
        sl.setPadding(new Insets(5,0,0,0));
        VBox list3 = new VBox(sl, playlistSongsBox);
        list3.setStyle("-fx-border-color: black");
        list3.setStyle("-fx-border-insets: 5");
        list3.setStyle("-fx-border-width: 2");
        list3.setStyle("-fx-border-style: solid inside");
        list3.setMinSize(166, 460);
        list3.setAlignment(Pos.CENTER);
        playlistSongsBox.setAlignment(Pos.TOP_CENTER);
        playlistSongsBox.setPadding(new Insets(20));
        playlistSongsBox.setPrefSize(300, 460);
    
    /********************Control Bar**********************/
        //Skip Forward Button
        f = new Button();
        f.setGraphic(iv4);
        iv4.setFitHeight(60);
        iv4.setFitWidth(60);
        
        f.setOnAction(e -> {
            controller.skipSong();
        });
        
        //Play Button
        pl = new Button();
        pl.setGraphic(iv);
        iv.setFitHeight(60);
        iv.setFitWidth(60);
        
        pl.setOnAction(e -> {
            controller.setStatusPlay();
        });
        
        //Pause Button
        pa = new Button();
        pa.setGraphic(iv2);
        iv2.setFitHeight(60);
        iv2.setFitWidth(60);
        
        pa.setOnAction(e -> {
            controller.setStatusPaused();
        });
        
        //Stop Button
        s = new Button();
        s.setGraphic(iv6);
        iv6.setFitHeight(60);
        iv6.setFitWidth(60);
        
        s.setOnAction(e -> {
            controller.setStatusStopped();
        });
        
        // Note: Change this to re-play song button
        //Skip to Previous Button
        p = new Button();
        p.setGraphic(iv5);
        iv5.setFitHeight(60);
        iv5.setFitWidth(60);
        
        p.setOnAction(e -> {
            controller.replay();
        });
        
        
        //Seek Slider
        seek = new Slider();
        seek.setMinWidth(500);
        seek.setMinHeight(50);
        HBox hs = new HBox(seek);
        hs.setAlignment(Pos.CENTER);
        // stop the scrolling while user mouses down
        seek.setOnMousePressed(e -> {
            isPlaying = false;
        });
        seek.setOnMouseReleased(e -> {
            isPlaying = true;
            controller.seekSong(seek.getValue());
            System.out.println("Seek Value: " + seek.getValue());
        });
        
        //Volume Slider
        v = new Slider();
        v.setMinWidth(70);
        HBox hv = new HBox(iv3);
        hv.getChildren().add(v);
        hv.setAlignment(Pos.CENTER);
        iv3.setFitHeight(50);
        iv3.setFitWidth(50);
        v.setValue(66.0); // volume on program startup is 66%
        v.setOnMouseReleased(e -> {
            //System.out.println("Hello! Can you hear me?");
            controller.setVolume(v.getValue());
        });
        
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
        root.setPrefHeight(460);
        root.setPadding(new Insets(20));
        root.setBottom(gp);
        root.setTop(c);
        root.setCenter(list2);
        root.setLeft(list1);
        root.setRight(list3);
        
        // Set up the clock which increments the seeker
        seek.setBlockIncrement(0.50);
        playingClock.submit(() -> {
                        while (true) {
                            try{
                                Thread.sleep(500);
                                if (isPlaying)
                                    seek.increment();
                            } catch (InterruptedException e) {
                                System.out.println("Exception in clock" + e);
                            }
                        }
        });
        // clean up the clock on shutdown
        this.stage.setOnCloseRequest(e -> {
            isPlaying = false;
            playingClock.shutdown();
            System.exit(0);
        });
        
        
        Scene mainScene = new Scene(root,1000,800);
        this.stage.setScene(mainScene);
        this.stage.show();
    }
    
    
    /**
     * Populates the PlaylistSongs stack pane with the song names in the form
     * of labels. Existing songs are overwritten.
     * @param songs 
     */
    public void populatePlaylistSongs(ArrayList<Song> songs) {
        if (!playlistSongsBox.getChildren().isEmpty()){
            playlistSongsBox.getChildren().clear();
        }
        songs.forEach((song) -> {
            playlistSongsBox.getChildren().add(new Label(song.getName()));
        });
    }
    
    
    /**
     * 
     * @param o object implementing observable interface. This 
     */
    @Override
    public void update(Observable o) {
        // Call appropriate update helper method based on object's class name
        String type = o.getClass().getSimpleName();
        switch (type) {
            case "AudioStream":
                audioStreamUpdate((AudioStream)o); 
                break;
            case "Queue":
                queueUpdate((Queue)o);
                break;
            case "Playlist":
                playlistUpdate((Playlist)o);
        }
       
    }
    
    
    /**
     * @param stream: reference to the stream which we are updating. 
     */
    private void audioStreamUpdate(AudioStream stream){
        if (stream.getStatus() == null){
            if (stream.getSongName().equals("")) {
                isPlaying = false;
                seek.setValue(0.0);
            } else{
                System.out.println("Entered an error state");
                //Attempt to skip the song
                controller.skipSong();
            }
            return;
        }
        MediaPlayer.Status status = stream.getStatus();
        switch (status) {
            case READY:
                System.out.println("AudioStream is ready");
                // oh, it's a new song
                playingSongName = stream.getSongName();
                current.setText("Now Playing: " + playingSongName);
                //move the playback slider to position 0 (min position)
                seek.setValue(0.0);
                // Set the max seek value to the number of seconds in the song
                seek.setMax(stream.getSongDurationSeconds());
                controller.setStatusPlay();
                break;
            case PLAYING:
                System.out.println("AudioStream is playing");
                if (stream.songEnded()) {
                    isPlaying = false;
                    controller.loadNextFromQueue();
                } else {
                    isPlaying = true;
                }
            case STALLED:
                System.out.println("AudioStream is buffering.");
                // Player is buffering. 
                break;
            case PAUSED:
                System.out.println("AudioStream is paused");
                isPlaying = false;
                break;
            case STOPPED:
                System.out.println("AudioStream is stopped");
                isPlaying = false;
                seek.setValue(0.0);
                break;
            default:
        }
    }
    
    /**
     * 
     * @param q : Queue object which is alerting the view of an update 
     */
    private void queueUpdate(Queue q) {
        System.out.println("Updating the view per new data from the queue");
        // If queue's size is bigger, it has a new song
        if (q.getSongs().size() > queueBox.getChildren().size()){
            int size = q.getSongs().size();
            Label l = new Label(q.getSongs().get(size-1).getName());
            queueBox.getChildren().add(l);
        } // or if Queue is smaller than the queueBox's # of children
        else if (q.getSongs().size() < queueBox.getChildren().size()) {
            queueBox.getChildren().remove(0);
        }
    }
    
    private void playlistUpdate(Playlist p) {
        String name = p.getName();
        // If SongList is null, then playlist is new and it needs a label
        if (p.getSongList() == null){
            Label l = new Label(name);
            l.setOnMouseClicked(me -> {
                System.out.println("Click Count" + me.getClickCount());
                if (me.getClickCount() == 1){
                    controller.loadPlaylistSongs(l.getText());
                } else {
                    controller.loadPlaylist(l.getText());
                }
            });
            playlistBox.getChildren().add(l);
        }
        // otherwise playlist just read its songs
        else{
            playlistSongs = new ArrayList<>();
            p.getSongList().forEach((s) -> {
                playlistSongs.add(new Label(s.getName()));
            });
        }
    }
}
