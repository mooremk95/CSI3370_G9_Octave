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
import java.util.ArrayList;

/**
 *
 * @author Ricky Pak
 * This class is uses a queue data structure (First-in, first-out). Class is the
 * main model class. It should consist of a list of "Song" pairs. These are 
 * pair classes with the first element being a String representing the song name,
 * and case he second being a string of it's file path. 
 */
public class Queue implements Observable {
    private Observer observer = null;
    private ArrayList<Song> songs = null; 
    
    
    public Queue(Observer o) {
        attach(o);
        songs = new ArrayList<Song>();
        // create empty arrayList
    }
    
    // observable methods
            
    @Override
    public void attach(Observer o) {
        observer = o;
    }
    
    @Override    
    public void detach(Observer o) {
        observer = null;
    }
    
    @Override      
    public void alert() {
        if (observer != null)
            observer.update(this);
    }
    
    // Main Funcitons 
    /**
     * Adds song to the Queue's ArraayList
     * @param song 
     */
    public void addToQueue(Song song) {
        songs.add(song);

    }
    /**
     * 
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
    
    /**
     * Returns the oldest song in the queue. 
     * @return Oldest song in the queue
     */
    public Song popFromQueue() {
        if( songs.size() > 0 ){
            return songs.remove(0);
        }
        return null;
    }
    
    /**
     * 
     */
    public boolean hasNext() {
        if(songs.size()-1 > 0){
            return true;
        }
        return false;
    }
    
    public String toString(){
        String string = "Queue";
        for (Song s:songs){
            string += "\n\t" + s.getName();
        }
        return string;
    }
}
