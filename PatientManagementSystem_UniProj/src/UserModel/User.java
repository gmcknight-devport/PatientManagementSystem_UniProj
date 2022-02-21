/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import GUIUpdateObserver.GUIUpdate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The abstract superclass used as the base for all users. 
 * @author Glenn McKnight
 */
public abstract class User {
    
    private String userID;
    private String userPassword;
    private String title;
    private String firstName;
    private String surname;
    private String saveFileName;
        
    /**
     * Constructor with nosaveFileName; parameters. Sets save file name.
     */    
    public User(){        
        this.saveFileName = this.getClass().getName();
    }
    
    /**
     * User constructor with all required base parameters
     * @param userID identification of user text
     * @param userPassword user text
     * @param title title text
     * @param firstName first name text
     * @param surname surname text
     */
    public User(String userID, String userPassword, String title, 
            String firstName, String surname) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.saveFileName = this.getClass().getName();
    }      
      
    abstract void save();
    abstract void load();
    abstract void deleteUser(String userID); 
    abstract List<String> getUserIDList();
    abstract String returnUser(String userID);
    abstract List<? extends User> getUserInfo();
    protected abstract int getUserIndex(String userID);
      
    /**
     * Generate an ID for a new user based on the list of current users in the system
     * and the userType
     * @param userIDList The list of current users for that class type.
     * @param userType an Enum denoting the type of user.
     * @return the userID that has been generated for the new user.
     */
    protected String generateID(ArrayList<String> userIDList, UserTypes userType){
        int nextID = 0;
        String type = userType.toString();
        
        try{   
            if(userIDList.isEmpty()){
                return type + 1001;
            }else{
                 for(String s: userIDList){            
                    int currID = Integer.parseInt(s.substring(1,5));

                    if(currID < 9999 && currID >= nextID){
                        nextID = currID + 1;
                    }
                }
                 if(nextID != 0){
                    return type + Integer.toString(nextID);
                }else{
                    GUIUpdate.getInstance().notifyUpdateObserver("Couldn't "
                             + "generate ID");
                    throw new NullPointerException();
                }
            }
        }catch(NumberFormatException | NullPointerException ex){
            System.out.println(ex);
            return type + Integer.toString(nextID);
        }catch(ArrayIndexOutOfBoundsException ex){
            return type + 1001;
        }
    }
    
    /**
     * Accessor for userID.
     * @return userID for this user.
     */
    public String getUserID() {
        return userID;
    }
    /**
     * Accessor for userPassword.
     * @return password for this user.
     */
    public String getUserPassword() {
        return userPassword;
    }
    /**
     * Accessor for title.
     * @return title for this user.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Accessor for first name.
     * @return first name for this user
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Accessor for surname.
     * @return surname for this user.
     */
    public String getSurname() {
        return surname;
    }   
    /**
     * Accessor for save file name - name file is to be serialized under.
     * @return the save name for this user type file
     */
    public String getSaveFileName() {
        return saveFileName;
    }
    /**
     * Mutator for userID.
     * @param userID to be set.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    /**
     * Mutator for userPassword.
     * @param userPassword to be set.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     * Mutator for title.
     * @param title to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Mutator for first name.
     * @param firstName to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Mutator for surname
     * @param surname to be set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * File name to be set for saving user type to file.
     * @param saveFileName to be set.
     */
    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }
}
