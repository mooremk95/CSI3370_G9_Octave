/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

/**
 *
 * @author NVala
 */
public class Song {
    private String name, filePath;  
    
    public Song(String name, String filePath){
        this.name=name;
        this.filePath = filePath;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getFilePath()
    {
        return filePath;
    }
    
    public void setName(String newName)
    {
        name = newName;
    }
    
    public void setFilePath(String newPath)
    {
        filePath = newPath;
    }
    
    public String toString() {
        return "Song| " + getName() + "\nLocation| " + getFilePath();
    }
            
}
