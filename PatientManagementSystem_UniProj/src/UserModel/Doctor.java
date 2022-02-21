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
 * User type Doctor inherits from abstract super class User and 
 * implements Serializable for saving and loading data from file. Responsible 
 * for Doctor CRUD operations.
 * @author Glenn McKnight
 */
public class Doctor extends User implements Serializable, ILoginCheck, IReceiveMessages{

    private String address;
    private ArrayList<Integer> rating;
    private ArrayList<String> messages;
    
    private ArrayList <Doctor> doctorList = new ArrayList<>();
    
    /**
     * Default constructor
     */
    public Doctor(){}
    
    /**     
    * Constructor with all required parameters including additional specific to 
    * doctor class
    * @param userID userID text
    * @param userPassword password text
    * @param title title text
    * @param firstName forename text
    * @param surname surname text
    * @param address address text
    * @param rating doctor rating number
    * @param messages list of messages sent to user
    */
    protected Doctor(String userID, String userPassword, String title, String firstName, 
            String surname, String address, ArrayList<Integer> rating, ArrayList<String> messages) {
        super(userID, userPassword, title, firstName, surname);
        this.address = address;
        this.rating = rating;
        this.messages = messages;        
    }   
    
    /**
     * Implements the abstract save method from super class. Saves objects to 
     * file using Serializer class static method.
     */
    @Override
    protected void save() {        
        Serializer.serializeObject(doctorList, getSaveFileName());        
    }

   /**
     * Implements abstract load method from super class. Loads objects from file
     * using Serializer class static method. 
     */
    @Override
    protected void load() {   
        try{            
            doctorList = (ArrayList<Doctor>)Serializer.deserializeObject(getSaveFileName());            
        }catch(IllegalArgumentException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Doctor load failed");
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
         return doctorList.stream().anyMatch((d) -> (d.getUserID().equals(userID) 
                && d.getUserPassword().equals(password)));
    }
    /**
     * Takes userID as a string and deletes user if it exists in the list.
     * @param userID the user to be deleted.
     */     
    @Override
    protected void deleteUser(String userID){         
        try{
            int index = getUserIndex(userID);
            doctorList.remove(index);   
            GUIUpdate.getInstance().notifyUpdateObserver("User deleted");
            
        }catch(ArrayIndexOutOfBoundsException | IllegalArgumentException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("This user doesn't "
                    + "exist or couldn't be deleted");
        }
    }
    /**
     * Iterates through list of Doctor users and returns all the stored userIDs.
     * @return list of stored userIDs.
     */
    @Override
    protected ArrayList<String> getUserIDList(){
        ArrayList<String> userIDs = new ArrayList();
        
        doctorList.forEach((d) -> {
            userIDs.add(d.getUserID());
        });
        return userIDs;
    }
    /**
     * Iterates through list of Doctor users and returns string of basic information
     * stored about them. Throws an exception if userID doesn't exist.
     * @param userID the user to return basic information about.
     * @return specified basic user information(userID, title, first name, surname).
     */
    @Override
    protected String returnUser(String userID){
        
        try{
            String returnDoc;
            int index = getUserIndex(userID);

            returnDoc = doctorList.get(index).getUserID()+ ", " + 
                            doctorList.get(index).getTitle() + ", " + 
                            doctorList.get(index).getFirstName() + ", " + 
                            doctorList.get(index).getSurname();

            return returnDoc;
            
        }catch(IllegalArgumentException | ArrayIndexOutOfBoundsException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Failed to add patient to list");
            return null;
        }
    }
    /**
     * A list of all Doctor users is returned.
     * @return list of Doctors.
     */
    @Override
    protected ArrayList<Doctor> getUserInfo() {
        return doctorList;
    }
    /**
     * Returns the index of the user specified by the userID param that is passed in.
     * @param userID 
     * @return specified user index
     */
    @Override
    protected int getUserIndex(String userID){
        int index = 0;
        
        for(Doctor d : doctorList){
                if(d.getUserID().equals(userID)){
                    index = doctorList.indexOf(d);
                    return index;
                }
            }
        throw new ArrayIndexOutOfBoundsException();
    }
    /**
     * Adds an Doctor user to the stored list of users.
     * @param doctor the user to be added.
     */ 
    protected void addDoctorToList(Doctor doctor){
        doctorList.add(doctor);
    }
    /**
     * Concatenates a string of basic doctor information with their average rating.
     * @return list of doctors and average ratings.
     */
    public ArrayList<String> doctorsWithRatings(){
            ArrayList<String> docs = new ArrayList<>(doctorList.size());

            doctorList.forEach((d) -> {
                docs.add(d.getUserID() + ", " + d.getFirstName() + ", " + d.getSurname() 
                        + ", " + averageRating(doctorList.indexOf(d)));
            });
        return docs;
    }
    /**
     * Calculates the average rating of a specified doctor.
     * @param doctorIndex specifies which doctor. 
     * @return double representing the average rating.
     */
    private double averageRating(int doctorIndex){
        
        if(doctorList.get(doctorIndex).getRating() != null){
            
           return Math.round(doctorList.get(doctorIndex).getRating().stream()
                   .mapToDouble(Integer::intValue).average().orElse(0) * 100.0) / 100.0;           
        }else{
            return 0;
        }
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
        int index = getUserIndex(userID);
        doctorList.get(index).setMessages(message);
    }
    /**
     * Get all of the messages for a specified user.
     * @param userID The user for the messages
     * @return list of messages for the userID.
     */
    @Override
    public List<String> getUserMessages(String userID){
        int index = getUserIndex(userID);
        return doctorList.get(index).getMessages();  
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
            doctorList.get(index).messages.remove(messageIndex);
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
            GUIUpdate.getInstance().notifyUpdateObserver("Select a message");
        }
    }    
    ////////////////////////////////////////////////////////////////////////////
    //Accessors and Mutators   
    /**
     * Accessor method for address.
     * @return doctor's address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Mutator method to set doctor's address.
     * @param address to be set for doctor.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Accessor for doctor's rating.
     * @return rating of doctor.
     */
    public ArrayList<Integer> getRating() {
        return rating;
    }
    /**
     * Accessor for all of a specified doctor's ratings. 
     * @param userID for specific doctor.
     * @return list of the ratings of specified doctor
     */
    public ArrayList<Integer>getRating(String userID){
        int index = getUserIndex(userID);
        return doctorList.get(index).getRating();
    }
    /**
     * Mutator to set all doctor's ratings at once.
     * @param rating an Integer ArrayList of ratings.
     */
    public void setRating(ArrayList<Integer> rating) {
        this.rating.addAll(rating);
    }
    /**
     * Mutator to add to a specific doctor's ratings.
     * @param doctorID the specified doctor.
     * @param rating the rating to be set.
     */
    public void setRating(String doctorID, int rating){
        int index = getUserIndex(doctorID);
        doctorList.get(index).rating.add(rating);
        save();
    }
    /**
     * Mutator to set the user mesages.
     * @param message to be set.
     */
    public void setMessages(String message){
        messages.add(message);
    }
    /**
     * Accessor to return user messages.
     * @return list of messages
     */
    public ArrayList<String> getMessages(){
        return messages;
    }    
    ///////////////////////////////////////////////////////////////////////////
    //Serialization
    /**
     * Blocks the readObject method to protect against serialzation attacks.
     * @param stream input stream from file.
     * @throws InvalidObjectException 
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException{
        throw new InvalidObjectException("Proxy Required");
    }
    /**
     * Returns new Doctor proxy instead of the original class.
     * @return instance of Administrator class as proxy.
     */
    private Object writeReplace(){       
        return new DoctorSerializationProxy(this);
    }
    /**
     * Static serialization proxy class used to protect against serialization attacks. 
     * A copy of the Doctor class constructor.
     */
    private static class DoctorSerializationProxy implements Serializable{
        
        private final String userID;
        private final String userPassword;
        private final String title;
        private final String firstName;
        private final String surname;
        private final String address;
        private final ArrayList<Integer> rating;
        private final ArrayList<String> messages;
        
        private DoctorSerializationProxy(Doctor d){
            
            this.userID = d.getUserID();
            this.userPassword = d.getUserPassword();
            this.title = d.getTitle();
            this.firstName = d.getFirstName();
            this.surname = d.getSurname();
            this.address = d.getAddress();
            this.rating = d.getRating();
            this.messages = d.getMessages();                    
        }
        
        private static final long serialUID = 559084773625563711L;
        
        private Object readResolve(){
            return new Doctor(userID, userPassword, title, firstName, surname, address, rating, messages);
        }
    }
}
