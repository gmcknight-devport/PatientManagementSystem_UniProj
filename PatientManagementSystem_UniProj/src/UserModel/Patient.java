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
import java.util.LinkedList;
import java.util.List;

//@table(patient)
/**
 * User type Patient inherits from abstract super class User and 
 * implements Serializable for saving and loading data from file. Responsible 
 * for Patient CRUD operations.
 * @author Glenn McKnight
 */

public class Patient extends User implements Serializable, ILoginCheck, 
        IReceiveMessages {
       
    private int age;
    private String address;
    private char gender;  
    private ArrayList<String> notes = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();
    private LinkedList<String> prescriptions;
    private LinkedList<String> appointmentHistory;
    private ArrayList <Patient> patientList = new ArrayList<>();  
    
    /**
     * Default constructor
     */
    public Patient() {}
    
    /**
     * Constructor with all required parameters including additional specific to
     * Patient class
     * @param userID userID text
     * @param userPassword password text
     * @param title title text
     * @param firstName forename text
     * @param surname surname text
     * @param age age number
     * @param address address text
     * @param gender gender character
     * @param notes array of notes/patient history
     * @param messages
     * @param prescriptions
     * @param appointmentHistory
     */
    protected Patient(String userID, String userPassword, String title, String firstName, 
            String surname, int age, String address, char gender, ArrayList<String> notes, 
            ArrayList<String> messages, LinkedList<String> prescriptions, 
            LinkedList<String>appointmentHistory) {
        
        super(userID, userPassword, title, firstName, surname);
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.notes = notes;   
        this.messages = messages;
        this.prescriptions = prescriptions;
        this.appointmentHistory = appointmentHistory;
    }   
    
    /**
     * Implements the abstract save method from super class. Saves objects to 
     * file using Serializer class static method.
     */
    @Override
    protected void save() {     
        Serializer.serializeObject(patientList, getSaveFileName());        
    }

   /**
     * Implements abstract load method from super class. Loads objects from file
     * using Serializer class static method. 
     */
    @Override
    protected void load() {   
        try{            
            patientList = (ArrayList<Patient>)Serializer.deserializeObject(getSaveFileName()); 
        }catch(IllegalArgumentException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Patient load failed");
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
        return patientList.stream().anyMatch((p) -> (p.getUserID().equals(userID) 
                && p.getUserPassword().equals(password)));
    }
     /**
     * Takes userID as a string and deletes user if it exists in the list.
     * @param userID the user to be deleted.
     */ 
    @Override
    protected void deleteUser(String userID){         
        try{
            int index = getUserIndex(userID);
            patientList.remove(index);   
            GUIUpdate.getInstance().notifyUpdateObserver("User deleted");
            
        }catch(ArrayIndexOutOfBoundsException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("This user doesn't "
                    + "exist or couldn't be deleted");
        }
    }
    /**
     * Iterates through list of Patient users and returns all the stored userIDs.
     * @return list of stored userIDs.
     */
    @Override
    protected ArrayList<String> getUserIDList(){
        ArrayList<String> userIDs = new ArrayList();
        
        patientList.forEach((p) -> {
            userIDs.add(p.getUserID());
        });
        return userIDs;
    }
    /**
     * Iterates through list of Patient users and returns string of basic information
     * stored about them. Throws an exception if userID doesn't exist.
     * @param userID the user to return basic information about.
     * @return specified basic user information(userID, title, first name, surname).
     */
    @Override
    protected String returnUser(String userID){
        
        try{
            String returnPat;
            int index = getUserIndex(userID);

            returnPat = patientList.get(index).getUserID() + ", " + 
                            patientList.get(index).getTitle() + ", " + 
                            patientList.get(index).getFirstName() + ", " + 
                            patientList.get(index).getSurname();

            return returnPat;
            
        }catch(IllegalArgumentException | ArrayIndexOutOfBoundsException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Failed to add patient to list");
            return null;
        }
    }
    /**
     * A list of all Administrator users is returned.
     * @return list of Administrators.
     */
    @Override
    protected ArrayList<Patient> getUserInfo(){
        return patientList;
    }
    /**
     * Returns the index of the user specified by the userID param that is passed in.
     * @param userID 
     * @return specified user index
     */
    @Override
    protected int getUserIndex(String userID){
        int index;
        
        for(Patient p : patientList){
                if(p.getUserID().equals(userID)){
                    index = patientList.indexOf(p);
                    return index;
                }
            }
        throw new ArrayIndexOutOfBoundsException();
    }
    /**
     * Adds a Patient user to the stored list of users.
     * @param patient the user to be added.
     */ 
    protected void addPatientToList(Patient patient){
        patientList.add(patient);
    } 
    ///////////////////////////////////////////////////////////////////////////
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
        patientList.get(index).setMessages(message);
    }
    /**
     * Get all of the messages for a specified user.
     * @param userID The user for the messages
     * @return list of messages for the userID.
     */
    @Override
    public List<String> getUserMessages(String userID){
        int index = getUserIndex(userID);
        return patientList.get(index).getMessages();    
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
            patientList.get(index).messages.remove(messageIndex);
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
            GUIUpdate.getInstance().notifyUpdateObserver("Select a message");
        }     
    }    
    ////////////////////////////////////////////////////////////////////////////
    //Accessors and mutators
    /**
     * Accessor method gets user age.
     * @return age of user.
     */
    protected int getAge() {
        return age;
    }
    /**
     * Mutator method to set age of user.
     * @param age to be set for user.
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Accessor method to get the address of the user.
     * @return the user's address.
     */
    protected String getAddress() {
        return address;
    }
    /**
     * Mutator method to set the address of the user.
     * @param address to be set.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Accessor method to get the gender of the user.
     * @return the user's gender.
     */
    protected char getGender() {
        return gender;
    }
    /**
     * Mutator method to set the gender of the user.
     * @param gender to be set.
     */
    public void setGender(char gender) {
        this.gender = gender;
    }
    /**
     * Accessor method to get the notes of the patient.
     * @return list of patient notes. 
     */
    protected ArrayList<String> getNotes() {
        return notes;
    }
    /**
     * Accessor method to get the notes for a specified patient.
     * @param patID the ID of the user required.
     * @return list of specified user's notes.
     */
    protected ArrayList<String> getNotes(String patID){
        for(int i = 0; i < patientList.size(); i++){
            if(patientList.get(i).getUserID().equals(patID)){
                return patientList.get(i).getNotes(); 
            }
        }
        return null;
    }
    /**
     * Mutator to set the notes for a patient.
     * @param notes to be added.
     */
    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }
    /**
     * Mutator method to set notes for a specified user.
     * @param userID the ID of the user required.
     * @param notes to be added to user.
     */
    protected void setNotes(String userID, String notes){
        int index = getUserIndex(userID);
        patientList.get(index).notes.add(notes);
    }
    /**
     * Mutator to set messages of user.
     * @param message to be set.
     */
    public void setMessages(String message){
        messages.add(message);
    }
    /**
     * Accessor to get user messages.
     * @return list of messages for user.
     */
    public ArrayList<String> getMessages(){
        return messages;
    }  
    /**
     * Accessor to get list of patient prescriptions.
     * @return list of patient prescriptions.
     */
    public LinkedList<String> getPrescriptions() {
        return prescriptions;
    }
    /**
     * Accessor to get list of prescription for specified user.
     * @param userID of specified patient. 
     * @return list of specified patient's prescriptions. 
     */
    protected LinkedList<String> getPrescriptions(String userID){
        for(Patient p : patientList){
            if(p.getUserID().equals(userID)){
                return p.getPrescriptions();
            }
        }
        return null;
    }
    /**
     * Mutator to set user prescription. 
     * @param prescriptions list of prescriptions to add.
     */
    public void setPrescriptions(LinkedList<String> prescriptions) {
        this.prescriptions = prescriptions;
    }    
    /**
     * Add a new prescription to the linked last like a stack so the last in is 
     * first out.
     * @param patientID the specified user.
     * @param prescription to be added.
     */    
    public void setPrescriptions(String patientID, String prescription){
        int index = getUserIndex(patientID);
        patientList.get(index).prescriptions.addFirst(prescription);
    }
    /**
     * Accessor to get list of appointments in from history. 
     * @return list of previous appointments.
     */
    public LinkedList<String> getAppointmentHistory() {
        return appointmentHistory;
    }
    /**
     * Accessor to get appointment history for a specified patient. 
     * @param patientID the specified user.
     * @return list of specified user's previous appointments.
     */
    public LinkedList<String> getAppointmentHistory(String patientID){
        int index = getUserIndex(patientID);
        return patientList.get(index).getAppointmentHistory();
    }
    /**
     * Mutator to set all appointments in user's history.
     * @param appointmentHistory list to be set.
     */
    public void setAppointmentHistory(LinkedList<String> appointmentHistory) {
        this.appointmentHistory = appointmentHistory;
    }    
    /**
     * Add last appointment to the linked list like a stack so it is last in 
     * first out.
     * @param appointment to be added to list.
     * @param patID specified user's appointment.
     */    
    public void addAppointmentToHistory(String appointment, String patID){
        int index = getUserIndex(patID);
        patientList.get(index).appointmentHistory.addFirst(appointment);
    }  
    ///////////////////////////////////////////////////////////////////////////
    //SerializationProxy
    /**
     * Blocks the readObject method to protect against serialzation attacks.
     * @param stream input stream from file.
     * @throws InvalidObjectException 
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException{
        throw new InvalidObjectException("Proxy Required");
    }    
    /**
     * Creates a new PatientproxySerialiaztion object to be used for safely
     * serialising patient objects.
     * @return new PatientSerializationProxy object 
     */    
    private Object writeReplace(){
        return new PatientSerializationProxy(this);
    }
    /**
     * Static serialization proxy class used to protect against serialization attacks. 
     * A copy of the Patient class constructor.
     */
    private static class PatientSerializationProxy implements Serializable{
        
        private final String userID;
        private final String userPassword;
        private final String title;
        private final String firstName;
        private final String surname;
        private final int age;
        private final String address;
        private final char gender;
        private final ArrayList<String> notes;
        private final ArrayList<String> messages;
        private final LinkedList<String> prescriptions;
        private final LinkedList<String> appointmentHistory;
                
        private PatientSerializationProxy(Patient p){
            
            userID = p.getUserID();
            userPassword = p.getUserPassword();
            title = p.getTitle();
            firstName = p.getFirstName();
            surname = p.getSurname();
            age = p.getAge();
            address = p.getAddress();
            gender = p.getGender();
            notes = p.getNotes();
            messages = p.getMessages();
            prescriptions = p.getPrescriptions();
            appointmentHistory = p.getAppointmentHistory();
        }
        
        private static final long serialUID = 7865241329909876L;
        
        private Object readResolve(){              
            return new Patient(userID, userPassword, title, firstName, surname, 
                   age, address, gender, notes, messages, prescriptions, 
                   appointmentHistory);
        }
    }

}