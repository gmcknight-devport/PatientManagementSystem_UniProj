/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIUpdateObserver;

/**
 *
 * @author Glenn McKnight
 */
public interface GUIUpdateObservable {
    public void updateObserver(GUIUpdateObserver observer);
    public void removeUpdateObserver(GUIUpdateObserver observer);
    public void notifyUpdateObserver(String errorMessage);
}
