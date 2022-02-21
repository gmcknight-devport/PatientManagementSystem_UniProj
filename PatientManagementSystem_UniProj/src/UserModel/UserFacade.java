/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import SystemServices.ServicesFacade;
import SystemServices.Appointments;
import Controllers.IController;
import GUIUpdateObserver.GUIUpdate;
import SystemServices.MedicineStock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Facade pattern class to encapsulate all User subclasses and operations. 
 * @author Glenn McKnight
 */
public class UserFacade {
    
    private static volatile UserFacade userFacadeInstance;
   
    private final Patient patient = new Patient();
    private final Doctor doctor = new Doctor();
    private final Secretary sec = new Secretary();
    private final Administrator admin = new Administrator();
    private final SignUpUser signupUser = new SignUpUser();
    private final UserFactory factory = new UserFactory();
    private final MedicineStock medicines = new MedicineStock();
    private ILoginCheck loginCheck;
    private IReceiveMessages messageReceiver;
    
    private String loggedInUserID;
    private User userType;
    private boolean messageSent;    
       
    private final Map<Enum, User> users = new HashMap<Enum, User>(){{
        put(UserTypes.P, patient);
        put(UserTypes.D, doctor);
        put(UserTypes.A, admin);
        put(UserTypes.S, sec);
        put(UserTypes.T, signupUser);
    }};
    
    /**
     * Default constructor initialises all User subclass objects.
     */
    private UserFacade(){  
        if (userFacadeInstance != null){
            throw new RuntimeException("This class is a singleton, use getInstance() to access");
        }
        
        medicines.load();
        patient.load();       
        doctor.load();
        sec.load();
        admin.load();
        signupUser.load();
        
        userType = signupUser;
    }
    
    /**
     * Return a singleton instance of this class. Creates a new instance if one
     * doesn't already exist.     * 
     * @return UserFacade singleton instance
     */
    public static UserFacade getInstance(){
        if(userFacadeInstance == null){
            synchronized (UserFacade.class) {
                userFacadeInstance = new UserFacade();
            }    
        }
        return userFacadeInstance;
    }    
    ///////////////////////////////////////////////////////////////////////////
    //User operations         
    /**
     * Switch through UserTypes enum to find value of input param to set the 
     * user type to perform operations on.
     * @param userID identifies the user type to perform operations on
     */    
    
    /*
    private void setUserOperationType(String userID){
        try{
            switch(UserTypes.valueOf(userID.substring(0, 1))){
                case P:
                    userType = patient;
                    break;
                case D:
                    userType = doctor;
                    break;
                case S:
                    userType = sec;
                    break;
                case A:
                    userType = admin;
                    break;
                case T:
                    userType = signupUser;
                    break;
                default:
                    userType = null;
                    GUIUpdate.getInstance().notifyUpdateObserver("Unsupported user type");
                    break;                    
            }
        }catch(IllegalArgumentException | NullPointerException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Invalid user type");
        }
    }    
    */
    private void setUserOperationType(String userID){
        
        try{
            UserTypes type = UserTypes.valueOf(userID.substring(0, 1));
            
            userType = users.get(type);
            
        }catch(ClassCastException | IllegalArgumentException | NullPointerException ex){
            
            GUIUpdate.getInstance().notifyUpdateObserver("Invalid user type");
            userType = null;
        }
    }
    
    /**
     * Calls checkLogin methods of all User subclasses and passes them the
     * inputted parameters 
     * @param userID userID text input by user
     * @param password password text input by user
     * @return
     */
    public boolean checkLoginCredentials(String userID, String password){
        
        loggedInUserID = null;       
        setUserOperationType(userID);     
        
        try{
            loginCheck = (ILoginCheck)userType;
            
            if(loginCheck.checkLogin(userID, password)){
                loggedInUserID = userID;
                return true;
            }else{
                return false;
            }
        }
        catch(NullPointerException ex){
            ex.printStackTrace();
                return false;
        }
    }
    /** 
     * Calls setUserOperationType to select the required user from the userID, 
     * then calls the delete user method of that class and passes it the user to be
     * deleted.
     * @param userID the user to be deleted.
     */     
    public void deleteUser(String userID){        
        setUserOperationType(userID);
        userType.deleteUser(userID);        
    }
    /**
     * Calls setUserOperationType to select the required user from the userID, 
     * then calls the add user method of the user factory and passes all needed
     * information to create a new user of specified type.
     * @param user type of user to create.
     * @param password to be set.
     * @param title to be set.
     * @param forename to be set.
     * @param surname to be set.
     * @param address to be set.
     */
    public void createUser(String user, String password, 
            String title, String forename, String surname, String address){
               
        setUserOperationType(user);
        
        factory.addUser(user, patient, doctor, sec, admin, password, title, 
                forename, surname, 0, address);
    }    
   
    /**
     * Returns information held on all users of the specified type.
     * @param user set the user type to return information on.
     * @return Arraylist of information of all users of the specified type.
     */
    public ArrayList<String> getUserInfo(String user){
        ArrayList<String> userList = new ArrayList<>();
        
        setUserOperationType(user);
        
        for(User u : userType.getUserInfo()){
            userList.add(u.getUserID() + ", " + u.getTitle() + ", " + u.getFirstName() 
            + ", " + u.getSurname());
        }
        return userList;
    }
    /**
     * Calls setUserOperationType to select the required user from the uloggedInUserID, 
     * then calls the return user method of the specified class and returns basic 
     * information about that user.
     * @return loggedInUser's info
     */
    public String getUserInfo(){
        String info;
        
        setUserOperationType(loggedInUserID);
        
        info = userType.returnUser(loggedInUserID);        
        return info;
    }    
    /**
     * Calls setUserOperationType to select the required user from the userID, 
     * then calls the add getUserIDList method of that user's class and returns
     * a list of that type of user.
     * @param userID
     * @return list of specified class userIDs.
     */
    public List<String> getUserIDs(String userID){
        setUserOperationType(userID);
        return userType.getUserIDList();        
    }
    /**
     * Calls all individual classes save methods. 
     */
    public void saveAll(){
        if(messageSent == false){
            setUserOperationType(loggedInUserID);
            userType.save();
            ServicesFacade.getInstance().save();
            medicines.save(); 
            signupUser.save();
        }else{
            patient.save();
            doctor.save();
            admin.save();
            sec.save();
            ServicesFacade.getInstance().save();
            medicines.save();
            signupUser.save();
            messageSent = false;
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    //Sensitive Patient information not to be exposed widely to system
    /**
     * Call getPrescriptions method of a specified patient.
     * @param patientID specified patient.
     * @return list of specified user's prescriptions.
     */
    public LinkedList<String> getUserPrescription(String patientID){
        return patient.getPrescriptions(patientID);
    }
    /**
     * Calls getNotes method of the patient class and passes the specified 
     * userID.
     * @param patientID specified patient.
     * @return List of specified patient notes.
     */
    public ArrayList<String> getPatientNotes(String patientID){
        return patient.getNotes(patientID);
    }
    /**
     * Calls setNotes method of patient class and passes specified patient and 
     * notes to be set. 
     * @param patientID specified patient.
     * @param notes to be set.
     */
    public void addPatientNotes(String patientID, String notes){
        patient.setNotes(patientID, notes);  
    }        
    ////////////////////////////////////////////////////////////////////////////
    //User Signup    
    /**
     * Calls SignUpUser method to create a signup request. Will call
     * method to create a new SignUpUser after generating a userID. 
     * @param password to be set.
     * @param title to be set.
     * @param forename to be set.
     * @param surname to be set.
     * @param age to be set.
     * @param address to be set.
     */  
    public void signupRequest(String password, String title, String surname, 
            String forename, int age , String address){
        
        signupUser.signupRequest(password, title, forename, surname, age, address);
    }
    /**
     * When a signup is declined the deleteUser method of signUpUser class
     * is called and request is removed.
     * @param index of specified SignUpUser to be deleted.
     */
    public void signupDeclined(int index){
        signupUser.deleteUser(signupUser.getUser(index));        
    }
    /**
     * Calls removeSignupRequest method of SignUpUser class and passes the index
     * of the user to remove which will initiate the user creation process.
     * @param signupUserIndex SignUpUser to be approved.
     */
    public void signupApproved(int signupUserIndex){
        SignUpUser temp = signupUser.getApprovedPatient(signupUserIndex);
        createUser(UserTypes.P.toString(), temp.getUserPassword(), temp.getTitle(), 
                temp.getFirstName(), temp.getSurname(), temp.getAddress());
        
        signupUser.removeSignupRequest(signupUserIndex);
    }
    /**
     * Calls getUserInfo method of SignUpUser to get a list of account requests.
     * @return List of account sign up requests.
     */
    public ArrayList<SignUpUser> getAccountRequests(){
        return signupUser.getUserInfo();
    }
    ///////////////////////////////////////////////////////////////////////////
    //Messaging
    /**
     * Calls setUserOperationType to select the user type to send the message to. 
     * Casts message Receiver to this class to see if messaging is capable for this 
     * user and sends message to the specified user if successful. Reports results
     * to the user with GUIUpdate class.
     * @param userID to be messaged.
     * @param message to send.
     */
    public void sendMessage(String userID, String message){
        messageReceiver = null;
        setUserOperationType(userID);
        
        try{
            messageReceiver = (IReceiveMessages)userType;
            messageReceiver.receiveMessage(userID, message);
            
            messageSent = true;
            GUIUpdate.getInstance().notifyUpdateObserver("Message sent");
        }catch(NullPointerException | ClassCastException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Couldn't send message");
        }
    }
    /**
     * Calls setUserOperationType to select the user type to delete the message from. 
     * Casts message Receiver to this class to see if messaging is capable for this 
     * user and deletes message to the logged in user user if successful. Reports results
     * to the user with GUIUpdate class. 
     * @param messageIndex 
     */
    public void deleteMessage(int messageIndex){
        messageReceiver = null;
        setUserOperationType(loggedInUserID);
        
        try{
            messageReceiver = (IReceiveMessages)userType;
            messageReceiver.deleteMessage(loggedInUserID, messageIndex);
        }catch(ClassCastException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Couldn't delete message");
        }
    }
    /**
     * Calls setUserOperationType to select the user type to get all messages from. 
     * Casts message Receiver to this class to see if messaging is capable for this 
     * user and calls getUserMessages method. Reports results to the user with 
     * GUIUpdate class.
     * @return list of specified user's messages.
     */
    public ArrayList<String> getUserMessages(){
        ArrayList<String> messages = new ArrayList<>();
        
        try{
            setUserOperationType(loggedInUserID);        
            messageReceiver = (IReceiveMessages)userType;        

            messages.addAll(messageReceiver.getUserMessages(loggedInUserID)); 
            return messages;
        
        }catch(NullPointerException | ClassCastException ex){
            System.out.println("No user messages");
            return null;
        }
    }     
    ////////////////////////////////////////////////////////////////////////////
    ////// Appointments
    /**
     * Call getAppointmentHistory  method from the patient class and gets
     * a list of patient history.
     * @param patientID to get history of.
     * @return list of specified patient's history.
     */
    public List<String> getAppointmenthistory(String patientID){
        return patient.getAppointmentHistory(patientID);
    }
    /**
     * Calls addAppointmentToHistory of patient class to add appointment to 
     * specified patient's history when required.
     * @param appointment to add to history.
     * @param patientID to set history of.
     */
    public void addAppointmentToHistory(String appointment, String patientID){
        try{
            patient.addAppointmentToHistory(appointment, patientID);
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("No patient appointments");
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    ////// MedicineStock
    /**
     * Calls updateStock method of MedicineStck class and if action is 
     * successful sends a message with prescription to the specified patient.
     * @param medStockIndex medicine to update.
     * @param quantity to update for specified medicine/
     * @param patientID
     * @param message 
     */
    public void giveMedicine(int medStockIndex, int quantity, String patientID, String message){
       
        if(medicines.updateStock(medStockIndex, -quantity)){
            sendMessage(patientID, message);
            patient.setPrescriptions(patientID, 
                    medicines.getMedStock().get(medStockIndex).getMedicineName() + ", " 
                    + medicines.getMedStock().get(medStockIndex).getMedicineDosage() + ", "
                    + medicines.getMedStock().get(medStockIndex).getCommonUses());
        }else{
            GUIUpdate.getInstance().notifyUpdateObserver("Not enough of this "
                    + "medicine in stock to give to patient");
        }        
    }    
    ////////////////////////////////////////////////////////////////////////////
    // Getter methods for class references
    
     /**
     * Returns the logged in user which is set on successful completion of login.
     * @return current logged in user text
     */
    public String getLoggedInUser() {
        return loggedInUserID;
    }   
    
    ////////////////////////////////////////////////////////////////////////////
    // Doctor Ratings   
    /**
     * Calls doctor class getRating method. 
     * @return list of doctors ratings
     */
    public List<Integer> doctorsWithRatings(){
        return doctor.getRating();
    }
    /**
     * Calls doctor class setRating method. Adds rating to specified doctor.
     * @param docId to be rated.
     * @param rating to set.
     */
    public void setRating(String docId, int rating){
        doctor.setRating(docId, rating);
    }
    /**
     * Calls doctor class getRating method and specifies the user to get.
     * @param userID specified doctor to get ratings of.
     * @return list of  specified doctors ratings
     */
    public ArrayList<Integer>getRating(String userID){
        return doctor.getRating(userID);
    }
    /**
     * Calls get doctor with ratings method from doctor class and returns 
     * information on doctor and average rating.
     * @return List of doctor's information and average ratings.
     */
    public ArrayList<String> getDocsAndRatings(){
        return doctor.doctorsWithRatings();
    }
}
