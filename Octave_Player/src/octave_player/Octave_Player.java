/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Mike Moore, Trent Toyryla
 * This is the driver class for the Octave music player. It will contain the 
 * Controller and View instances, the playlist instance, the queue (Model class), 
 * and a javafx MediaPlayer object called AudioStream.
 */
public class Octave_Player extends Application {
    
    @Override
    public void start(Stage primaryStage) { 
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Octave Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        PlaylistReader x = new PlaylistReader();
        ArrayList<Song> songList = x.getPlaylist("samplePlaylist.xml");
        if(songList == null)
            System.out.println("it was null");
        else{
            for(int i = 0; i < songList.size(); i++)
            {
            System.out.println("name: " + songList.get(i).getName() + " filePath: " + songList.get(i).getFilePath());
            }
        }
        System.out.println("YOU GOT TO THIS POINT");
    }
    
}
