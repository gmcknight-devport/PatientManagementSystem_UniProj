/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIUpdateObserver;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Glenn McKnight
 */
public final class GUIUpdate implements GUIUpdateObservable {
    
    private static volatile GUIUpdate updateInstance;    
    private List<GUIUpdateObserver> errorObservers = new ArrayList();
    /**
     * Private constructor throws exception if a second instance instantiation is 
     * attempted.
     */
    private GUIUpdate(){
        if (updateInstance != null){
            throw new RuntimeException("This class is a singleton, use getInstance() to access");
        }
    }
    /**
     * Static access method to return singleton of this class. Creates new instance
     * if one doesn't already exist.
     * @return instance of this class
     */
    public  static GUIUpdate getInstance(){
        if(updateInstance == null){
            synchronized (GUIUpdate.class){
                updateInstance = new GUIUpdate();
            }            
        }        
        return updateInstance;
    }
    /**
     * Allows observer to be passed in to be added to list.
     * @param observer the class to observe the actions of this one. 
     */
    @Override
    public void updateObserver(GUIUpdateObserver observer) { 
        if(!errorObservers.contains(observer)){
            errorObservers.add(observer);
        }
    }
    /**
     * Removes observer passed as parameter from list if it exists in it.
     * @param observer the element to be removed as an observer.
     */
    @Override
    public void removeUpdateObserver(GUIUpdateObserver observer) { 
        int index = errorObservers.indexOf(observer);
        if (index >= 0) errorObservers.remove(index);
    }
    /**
     * Notifies the observer with a string when called.
     * @param errorMessage the information to be sent to observers.
     */
    @Override
    public void notifyUpdateObserver(String errorMessage) {
        errorObservers.forEach((signupObserver) -> {
            signupObserver.update(errorMessage);
        });
    }
}
