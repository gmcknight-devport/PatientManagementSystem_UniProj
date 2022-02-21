/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import Serializer.Serializer;
import GUIUpdateObserver.GUIUpdate;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User type Administrator inherits from abstract super class User and 
 * implements Serializable for saving and loading data from file. Responsible 
 * for admin CRUD operations.
 * @author Glenn McKnight
 */
public class Administrator extends User implements Serializable, ILoginCheck, 
        IReceiveMessages{

    private ArrayList<String> messages;
    private ArrayList <Administrator> adminList = new ArrayList<Administrator>();    
    /**
     * Default empty constructor
     */
    public Administrator(){}    
    /**
     * Constructor with all required parameters - same ones as super class.
     * @param userID userID text
     * @param userPassword password text
     * @param title title text
     * @param firstName forename text
     * @param surname surname text
     */
    protected Administrator(String userID, String userPassword, String title, 
            String firstName, String surname, ArrayList<String> messages) {
        super(userID, userPassword, title, firstName, surname);
        this.messages = messages;
    } 
     /**
     * Implements the abstract save method from super class. Saves objects to 
     * file using Serializer class static method.
     */
    @Override
    protected void save() {        
        Serializer.serializeObject(adminList, getSaveFileName());        
    }
   /**
     * Implements abstract load method from super class. Loads objects from file
     * using Serializer class static method. 
     */
    @Override
    protected void load() {   
        try{            
            adminList = (ArrayList<Administrator>)Serializer.deserializeObject(getSaveFileName());            
        }catch(IllegalArgumentException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Administrator load failed");
        }
    }    
    /**
     * Compares the parameters to the userID and password for each Administrator 
     * object to authenticate user. 
     * @param userID userID text 
     * @param password password text 
     * @return success of login attempt
     */
    @Override
    public boolean checkLogin(String userID, String password) {
        return adminList.stream().anyMatch((a) -> (a.getUserID().equals(userID) 
                && a.getUserPassword().equals(password)));
    }        
    /**
     * Takes userID as a string and deletes user if it exists in the list.
     * @param userID the user to be deleted.
     */ 
    @Override
    protected void deleteUser(String userID){         
        try{
            if(adminList.size() > 1){
                int index = getUserIndex(userID);
                adminList.remove(index);   
                GUIUpdate.getInstance().notifyUpdateObserver("User deleted");
                
            }else{
                GUIUpdate.getInstance().notifyUpdateObserver("Can't delete account, "
                        + "must have at least 1 admin user");
            }
            
        }catch(ArrayIndexOutOfBoundsException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("This user doesn't "
                    + "exist or couldn't be deleted");
        }
     }
    /**
     * A list of all Administrator users is returned.
     * @return list of Administrators.
     */
    @Override
    protected ArrayList<Administrator> getUserInfo() {
        return adminList;
    }
    /**
     * Iterates through list of Administrator users and returns all the stored userIDs.
     * @return list of stored userIDs.
     */
    @Override
    protected ArrayList<String> getUserIDList(){
        ArrayList<String> userIDs = new ArrayList();
        
        adminList.forEach((a) -> {
            userIDs.add(a.getUserID());
        });
        return userIDs;
    }
    /**
     * Iterates through list of Administrator users and returns string of basic information
     * stored about them. Throws an exception if userID doesn't exist.
     * @param userID the user to return basic information about.
     * @return specified basic user information(userID, title, first name, surname).
     */
    @Override
    protected String returnUser(String userID){
        
        String returnAdmin;
        try{
            int index = getUserIndex(userID);
            
            returnAdmin = adminList.get(index).getUserID()+ ", " + 
                    adminList.get(index).getTitle() + ", " + 
                    adminList.get(index).getFirstName() + ", " + 
                    adminList.get(index).getSurname();

            return returnAdmin;
              
        }catch(IllegalArgumentException | ArrayIndexOutOfBoundsException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Failed to add patient to list");
            return null;
        }                
    }
    /**
     * Returns the index of the user specified by the userID param that is passed in.
     * @param userID 
     * @return specified user index
     */
    @Override
    protected int getUserIndex(String userID){
        int index = 0;
        
        for(Administrator a : adminList){
                if(a.getUserID().equals(userID)){
                    index = adminList.indexOf(a);
                    return index;
                }
            }
        throw new ArrayIndexOutOfBoundsException();
    }
    /**
     * Adds an Administrator user to the stored list of users.
     * @param admin the user to be added.
     */    
    protected void addAdminToList(Administrator admin){
        adminList.add(admin);
    }
    ////////////////////////////////////////////////////////////////////////////
    //Messaging
    /**
     * Allows messages to be sent to a specific user. Iterates through list of users 
     * to verify that userID parameter exists, Adds the message to this users 
     * messages.
     * @param userID the user to receive the message.
     * @param message the message to be sent to the user.
     */
    @Override
    public void receiveMessage(String userID, String message) {
       
        if(userID.equals(UserTypes.A.toString())){
            adminList.forEach((a) -> {
                a.setMessages(message);
            });
        }else{
            int index = getUserIndex(userID);
            adminList.get(index).setMessages(message);
        } 
    }
    /**
     * Get all of the messages for a specified user.
     * @param userID The user for the messages
     * @return list of messages for the userID.
     */
    @Override
    public List<String> getUserMessages(String userID){        
        int index = getUserIndex(userID);
        return adminList.get(index).getMessages();            
    }
    /**
     * Deletes the message at the specified index for the specified user. Throws 
     * exception if message index doesn't exist.
     * @param userID The user which has the message to be deleted.
     * @param messageIndex The index for the message to be deleted.
     */
    @Override
    public void deleteMessage(String userID, int messageIndex){
        
        int index = getUserIndex(userID);
            
        try{
            adminList.get(index).messages.remove(messageIndex);   
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
            GUIUpdate.getInstance().notifyUpdateObserver("Select a message");
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    //Accessors and Mutators
    /**
     * Mutator to add user message.
     * @param message the message to add.
     */
    public void setMessages(String message){
        messages.add(message);
    }
    /**
     * Accessor to return list of user messages.
     * @return list of messages
     */
    protected ArrayList<String> getMessages(){
        return messages;
    }
    ////////////////////////////////////////////////////////////////////////////
    //Serialization Proxy
    /**
     * Blocks the readObject method to protect against serialzation attacks.
     * @param stream input stream from file.
     * @throws InvalidObjectException 
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException{
        throw new InvalidObjectException("Proxy Required");
    }
    /**
     * Returns new Administrator proxy instead of the original class.
     * @return instance of Administrator class as proxy.
     */ 
    private Object writeReplace(){
        return new AdministratorSerializationProxy(this);
    }
    /**
     * Static serialization proxy class used to protect against serialization attacks. 
     * A copy of the Administrator class constructor.
     */
    private static class AdministratorSerializationProxy implements Serializable{
        
        private final String userID;
        private final String userPassword;
        private final String title;
        private final String firstName;
        private final String surname;
        private final ArrayList<String> messages;
        
        private AdministratorSerializationProxy(Administrator a){
            
            userID = a.getUserID();
            userPassword = a.getUserPassword();
            title = a.getTitle();
            firstName = a.getFirstName();
            surname = a.getSurname();
            messages = a.getMessages();
        }
        
        private static final long serialUID = 7776665558889874L;
        
        private Object readResolve(){            
            return new Administrator(userID, userPassword, title, firstName, 
                    surname, messages);
        }
    }
}
