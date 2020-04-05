/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octave_player;

/**
 *
 * @author Mike
 */
public interface Observable {
    public void attach(Observer o);
    public void detach(Observer o);
    public void alert();
}
