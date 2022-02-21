/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import GUIUpdateObserver.GUIUpdate;
import Serializer.Serializer;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.Serializable;

/**
 *
 * @author Glenn McKnight
 */
public class SignUpUser extends User implements Serializable{
    
    private int age;
    private String address;
    
    private ArrayList <SignUpUser> signupRequestList = new ArrayList<>();
    /**
     * Default constructor.
     */
    public SignUpUser(){}
    /**
     * Protected constructor for SignUpUser. Requires full user details.
     * @param userID generated ID to be set.
     * @param userPassword password to be set.
     * @param title to be set.
     * @param firstName to be set.
     * @param surname to be set.
     * @param age to be set.
     * @param address to be set
     */
    protected SignUpUser(String userID, String userPassword, String title, 
            String firstName, String surname, int age, String address) {
        
        super(userID, userPassword, title, firstName, surname);
        this.age = age;
        this.address = address;
    }
    /**
     * Public method to allow other classes to create a signup request. Will call
     * method to create a new SignUpUser after generating a userID. Adds user to 
     * list of SignUpUsers.
     * @param password to be set.
     * @param title to be set.
     * @param forename to be set.
     * @param surname to be set.
     * @param age to be set.
     * @param address to be set.
     */  
    public void signupRequest(String password, String title, String forename, 
            String surname, int age, String address){
        
        SignUpUser tempPatient;
        String userID = generateID(getUserIDList(), UserTypes.T);
        tempPatient = new SignUpUser(userID, password, title, forename, surname, age, 
                address);
        
        signupRequestList.add(tempPatient);
        
    }
    /**
     * Removes the specified signupRequest from list. 
     * @param index of request to remove.
     */    
    protected void removeSignupRequest(int index){
        signupRequestList.remove(index);
    }
    /**
     * When sig up request has been approved return user to be created as a new Patient.
     * @param index of approved user.
     * @return approved user to be created as new Patient.
     */
    protected SignUpUser getApprovedPatient(int index){
        return signupRequestList.get(index);
    }
    /**
     * Implements the abstract save method from super class. Saves objects to 
     * file using Serializer class static method.
     */
    @Override
    protected void save() {
        Serializer.serializeObject(signupRequestList, getSaveFileName());
    }

    /**
     * Implements abstract load method from super class. Loads objects from XML
     * using Serializer class static method. 
     */
    @Override
    protected void load() {   
        try{            
            signupRequestList = (ArrayList<SignUpUser>)Serializer.deserializeObject(getSaveFileName()); 
        }catch(IllegalArgumentException ex){
            GUIUpdate.getInstance().notifyUpdateObserver("Sign up load failed");
        }
    }
    /**
     * Takes userID as a string and deletes user if it exists in the list.
     * @param userID the user to be deleted.
     */ 
    @Override
    protected void deleteUser(String userID){ 
        int index = getUserIndex(userID);
        signupRequestList.remove(index);             
    }
    /**
     * A list of all SignUpUsers is returned.
     * @return list of SignUpUsers.
     */
    @Override
    protected ArrayList<SignUpUser> getUserInfo() {
        return signupRequestList;
    }
    /**
     * Iterates through list of SignUpUsers and returns all the stored userIDs.
     * @return list of stored userIDs.
     */
    @Override
    protected ArrayList<String> getUserIDList(){
        ArrayList<String> userIDs = new ArrayList();
        
        signupRequestList.forEach((s) -> {
            userIDs.add(s.getUserID());
        });
        return userIDs;
    }
    /**
     * Iterates through list of SignUpUsers and returns string of basic information
     * stored about them. Throws an exception if userID doesn't exist.
     * @param userID the user to return basic information about.
     * @return specified basic user information(userID, title, first name, surname).
     */
    @Override
    protected String returnUser(String userID){
        
        try{
            String returnTempUser;
            int index = getUserIndex(userID);

            returnTempUser = signupRequestList.get(index).getUserID()+ ", " + 
                            signupRequestList.get(index).getTitle() + ", " + 
                            signupRequestList.get(index).getFirstName() + ", " + 
                            signupRequestList.get(index).getSurname();
                            
            return returnTempUser;
            
        }catch(IndexOutOfBoundsException ex){
            return null;
        }

    }
    /**
     * Accessor to get specified user by index.
     * @param index of desired user.
     * @return specified user.
     */
    public String getUser(int index){
        return signupRequestList.get(index).getUserID();
    }
    /**
     * Returns the index of the user specified by the userID param that is passed in.
     * @param userID 
     * @return specified user index
     */
    @Override
    protected int getUserIndex(String userID){
        int index = 0;
        for(SignUpUser s : signupRequestList){
                if(s.getUserID().equals(userID)){
                    
                    index = signupRequestList.indexOf(s);
                    return index;
                }
            }
        throw new ArrayIndexOutOfBoundsException();
    }
    /**
     * Accessor to get age of user.
     * @return age of user.
     */
    public int getAge() {
        return age;
    }
    /**
     * Accessor to get address of user.
     * @return address of user.
     */
    public String getAddress() {
        return address;
    }
    ////////////////////////////////////////////////////////////////////////////
    //  Serialization Proxy 
    /**
     * Blocks the readObject method to protect against serialzation attacks.
     * @param stream input stream from file.
     * @throws InvalidObjectException 
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException{
        throw new InvalidObjectException("Proxy Required");
    }
    /**
     * Returns new SignUpUser proxy instead of the original class.
     * @return instance of Administrator class as proxy.
     */ 
    private Object writeReplace(){
        return new SignupSerializationProxy(this);
    }
    /**
     * Static serialization proxy class used to protect against serialization attacks. 
     * A copy of the SignUpUser class constructor.
     */
    private static class SignupSerializationProxy implements Serializable{
        
        private final String userID;
        private final String userPassword;
        private final String title;
        private final String firstName;
        private final String surname;
        private final int age;
        private final String address;
        
        private SignupSerializationProxy(SignUpUser t){
            
            userID = t.getUserID();
            userPassword = t.getUserPassword();
            title = t.getTitle();
            firstName = t.getFirstName();
            surname = t.getSurname();
            age = t.getAge();
            address = t.getAddress();
        }
        
        private static final long serialUID = 9898787676565453L;
        
        protected Object readResolve(){
            return new SignUpUser(userID, userPassword, title, firstName, surname, 
                    age, address);
        }
    }
}
