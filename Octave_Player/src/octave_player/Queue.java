/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package octave_player;

import octave_player.Observable;
import octave_player.Observer;

import java.lang.String;

//Containers
import javafx.util.Pair;
import java.util.ArrayList;
import javafx.util.Pair; // Songs are pairs of strings

/**
 *
 * @author Ricky Pak
 * This class is uses a queue data structure (First-in, first-out). Class is the
 * main model class. It should consist of a list of "Song" pairs. These are 
 * pair classes with the first element being a String representing the song name,
 * and case he second being a string of it's file path. 
 */
public class Queue implements Observable {
    
    public Queue() {
        
    }
    
    public void attach(Observer o) {
        // 
    }
    
    public void detach(Observer o) {
        
    }
    
    public void alert() {
        
    }
}
