/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import SystemServices.MedicineStock;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Glenn McKnight
 */
public class UserFacadeTest {
    
    private UserFacade instance = UserFacade.getInstance();
    
    private Patient patInstance = new PatientImpl();
    private Doctor docInstance = new DoctorImpl();
    private Secretary secInstance = new SecImpl();
    private Administrator adminInstance = new AdminImpl();
    private SignUpUser signupInstance = new SignupImpl();
    private UserFactory factoryInstance = new FactoryImpl();
    private MedicineStock medInstance = new MedicinesImpl();
    private String loggedInUserID;
    
    @Before
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        
        loggedInUserID = "P1009";
        
        Field field = UserFacade.class.getDeclaredField("patient");
        field.setAccessible(true); 
        
        Field field2 = UserFacade.class.getDeclaredField("doctor");
        field2.setAccessible(true); 
        
        Field field3 = UserFacade.class.getDeclaredField("sec");
        field3.setAccessible(true); 
        
        Field field4 = UserFacade.class.getDeclaredField("admin");
        field4.setAccessible(true); 
        
        Field field5 = UserFacade.class.getDeclaredField("signupUser");
        field5.setAccessible(true); 
        
        Field field6 = UserFacade.class.getDeclaredField("factory");
        field6.setAccessible(true); 
        
        Field field7 = UserFacade.class.getDeclaredField("medicines");
        field7.setAccessible(true); 
        
        Field field8 = UserFacade.class.getDeclaredField("loggedInUserID");
        field8.setAccessible(true); 
        
        Field modField = Field.class.getDeclaredField("modifiers");
        modField.setAccessible(true);
        modField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        
        field.set(instance, patInstance);
        field2.set(instance, docInstance);
        field3.set(instance, secInstance);
        field4.set(instance, adminInstance);
        field5.set(instance, signupInstance);
        field6.set(instance, factoryInstance);
        field7.set(instance, medInstance);
        field8.set(instance, loggedInUserID);
    }
    
    @After
    public void tearDown() {
        patInstance = null;
        docInstance = null;
        secInstance = null;
        adminInstance = null;
        signupInstance = null;
        factoryInstance = null;
        medInstance = null;
        
        assertNull(patInstance);
        assertNull(docInstance);
        assertNull(secInstance);
        assertNull(adminInstance);
        assertNull(signupInstance);
        assertNull(factoryInstance);
        assertNull(medInstance);
    }

    @Rule
    public ExpectedException ruleException = ExpectedException.none();
    /**
     * Test of getInstance method, of class UserFacade.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        
        UserFacade result = UserFacade.getInstance();
        assertTrue(result instanceof UserFacade);
    }

    /**
     * Test of checkLoginCredentials method, of class UserFacade.
     */
    @Test
    public void testCheckLoginCredentials() {
        System.out.println("checkLoginCredentials");
        String userID = "P1001";
        String password = "password";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.checkLoginCredentials(userID, password);
        
        assertNotNull(instance);
    }

    /**
     * Test of deleteUser method, of class UserFacade.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String userID = "P1001";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.deleteUser(userID);
        
        assertNotNull(instance);
    }

    /**
     * Test of createUser method, of class UserFacade.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        String user = "test";
        String password = "test";
        String title = "test";
        String forename = "test";
        String surname = "test";
        String address = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.createUser(user, password, title, forename, surname, address);
        
        assertNotNull(instance);
    }

    /**
     * Test of getUserInfo method, of class UserFacade.
     */
    @Test
    public void testGetUserInfo_String() {
        System.out.println("getUserInfo");
        String user = loggedInUserID;
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.getUserInfo(user);
        
        assertNotNull(instance);
    }

    /**
     * Test of getUserInfo method, of class UserFacade.
     */
    @Test
    public void testGetUserInfo_0args() {
        System.out.println("getUserInfo");
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.getUserInfo();
        
        assertNotNull(instance);
    }

    /**
     * Test of getUserIDs method, of class UserFacade.
     */
    @Test
    public void testGetUserIDs() {
        System.out.println("getUserIDs");
        String userID = "S1001";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.getUserIDs(userID);
        
        assertNotNull(instance);
    }
    
    /**
     * Test of getUserPrescription method, of class UserFacade.
     */
    @Test
    public void testGetUserPrescription() {
        System.out.println("getUserPrescription");
        String patientID = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.getUserPrescription(patientID);
        
        assertNotNull(instance);
    }

    /**
     * Test of getPatientNotes method, of class UserFacade.
     */
    @Test
    public void testGetPatientNotes() {
        System.out.println("getPatientNotes");
        String patientID = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.getPatientNotes(patientID);
        
        assertNotNull(instance);
    }

    /**
     * Test of addPatientNotes method, of class UserFacade.
     */
    @Test
    public void testAddPatientNotes() {
        System.out.println("addPatientNotes");
        String patientID = "test";
        String notes = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.addPatientNotes(patientID, notes);
        
        assertNotNull(instance);
    }

    /**
     * Test of signupRequest method, of class UserFacade.
     */
    @Test
    public void testSignupRequest() {
        System.out.println("signupRequest");
        String password = "test";
        String title = "test";
        String surname = "test";
        String forename = "test";
        int age = 0;
        String address = "est";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.signupRequest(password, title, surname, forename, age, address);
        
        assertNotNull(instance);
    }

    /**
     * Test of signupDeclined method, of class UserFacade.
     */
    @Test
    public void testSignupDeclined() {
        System.out.println("signupDeclined");
        int index = 0;
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.signupDeclined(index);
        
        assertNotNull(instance);
    }

    /**
     * Test of signupApproved method, of class UserFacade.
     */
    @Test
    public void testSignupApproved() {
        System.out.println("signupApproved");
        int signupUserIndex = 0;
        
        ruleException.expect(UnsupportedOperationException.class);
        instance.signupRequest("test", "test", "test", "test", 10, "test");
        
        instance.signupApproved(signupUserIndex);
        
        assertNotNull(instance);
    }

    /**
     * Test of getAccountRequests method, of class UserFacade.
     */
    @Test
    public void testGetAccountRequests() {
        System.out.println("getAccountRequests");
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.getAccountRequests();
        
        assertNotNull(instance);
    }

    /**
     * Test of sendMessage method, of class UserFacade.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        String userID = "P1007";
        String message = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.sendMessage(userID, message);
        
        assertNotNull(instance);                
    }

    /**
     * Test of deleteMessage method, of class UserFacade.
     */
    @Test
    public void testDeleteMessage() {
        System.out.println("deleteMessage");
        int messageIndex = 0;
                
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.deleteMessage(messageIndex);
        
        assertNotNull(instance);
    }

    /**
     * Test of getUserMessages method, of class UserFacade.
     */
    @Test
    public void testGetUserMessages() {
        System.out.println("getUserMessages");
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.getUserMessages();
        
        assertNotNull(instance);
    }

    /**
     * Test of getAppointmenthistory method, of class UserFacade.
     */
    @Test
    public void testGetAppointmenthistory() {
        System.out.println("getAppointmenthistory");
        String patientID = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
                
        instance.getAppointmenthistory(patientID);
        
        assertNotNull(instance); 
    }

    /**
     * Test of addAppointmentToHistory method, of class UserFacade.
     */
    @Test
    public void testAddAppointmentToHistory() {
        System.out.println("addAppointmentToHistory");
        String appointment = "test";
        String patientID = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
        
        instance.addAppointmentToHistory(appointment, patientID);
        assertNotNull(instance);        
    }

    /**
     * Test of giveMedicine method, of class UserFacade.
     */
    @Test
    public void testGiveMedicine() {
        System.out.println("giveMedicine");
        int medStockIndex = 0;
        int quantity = 2;
        String patientID = "test";
        String message = "test";
                
        ruleException.expect(UnsupportedOperationException.class);
        
        instance.giveMedicine(medStockIndex, quantity, patientID, message);
        assertNotNull(instance);
    }

    /**
     * Test of getLoggedInUser method, of class UserFacade.
     */
    @Test
    public void testGetLoggedInUser() {
        System.out.println("getLoggedInUser");
        
        String userID = "P1009";
        
        String expResult = userID;
        String result = instance.getLoggedInUser();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of doctorsWithRatings method, of class UserFacade.
     */
    @Test
    public void testDoctorsWithRatings() {
        System.out.println("doctorsWithRatings");
        
        String userID = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
        
        instance.doctorsWithRatings();
        assertNotNull(instance);
    }

    /**
     * Test of setRating method, of class UserFacade.
     */
    @Test
    public void testSetRating() {
        System.out.println("setRating");
        String docId = "test";
        int rating = 2;
        
        ruleException.expect(UnsupportedOperationException.class);
        
        instance.setRating(docId, rating);
        assertNotNull(instance);
    }

    /**
     * Test of getRating method, of class UserFacade.
     */
    @Test
    public void testGetRating() {
        System.out.println("getRating");
        String userID = "test";
        
        ruleException.expect(UnsupportedOperationException.class);
        
        instance.getRating(userID);
        assertNotNull(instance);
    }

    /**
     * Test of getDocsAndRatings method, of class UserFacade.
     */
    @Test
    public void testGetDocsAndRatings() {
        System.out.println("getDocsAndRatings");
        
        ruleException.expect(UnsupportedOperationException.class);
        
        instance.getDocsAndRatings();
        assertNotNull(instance);
    }
    
    public class PatientImpl extends Patient implements IReceiveMessages{
    
        @Override
        public LinkedList<String> getAppointmentHistory(String patientID){
            throw new UnsupportedOperationException();
        }
                
        @Override 
        public void addAppointmentToHistory(String patID, String appointment){
            throw new UnsupportedOperationException();
        }
        
        @Override
        public void receiveMessage(String userID, String message){
            throw new UnsupportedOperationException();
        }
        @Override
        public void deleteMessage(String userID, int index){
            throw new UnsupportedOperationException();
        }
        @Override
        public ArrayList<String> getUserMessages(String userID){
            throw new UnsupportedOperationException();
        }
        @Override
        public ArrayList<String> getNotes(String userID){
            throw new UnsupportedOperationException();
        }
        @Override 
        public void setNotes(String userID, String notes){
            throw new UnsupportedOperationException();
        }        
        @Override
        public LinkedList<String> getPrescriptions(String userID){
            throw new UnsupportedOperationException();
        }
        
        @Override
        public String returnUser(String userID){
            throw new UnsupportedOperationException();
        }
        
        @Override 
        public ArrayList<Patient> getUserInfo(){
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean checkLogin(String userID, String password){
            throw new UnsupportedOperationException();
        }
        @Override
        public void deleteUser(String userID){
            throw new UnsupportedOperationException();
        }
    }
    
    public class DoctorImpl extends Doctor{
    
        @Override
        public ArrayList<Integer> getRating() {
            throw new UnsupportedOperationException();        
        }
        @Override
        public void setRating(String doctorID, int rating) {
            throw new UnsupportedOperationException();
        }
        @Override
        public ArrayList<Integer> getRating(String doctorID) {
            throw new UnsupportedOperationException();
        }
        @Override
        public ArrayList<String> doctorsWithRatings() {
            throw new UnsupportedOperationException();
        }
    }
    
    public class SecImpl extends Secretary{
    
        @Override
        public ArrayList<String> getUserIDList(){
            throw new UnsupportedOperationException();
        }
    }
    
    public class AdminImpl extends Administrator{
    
    }
    
    public class SignupImpl extends SignUpUser{
        
        @Override
        public void signupRequest(String password, String title, String surname, 
            String forename, int age , String address){
            throw new UnsupportedOperationException();
        }
        @Override
        public void deleteUser(String userID){
            throw new UnsupportedOperationException();
        }
        @Override 
        public void removeSignupRequest(int index){
            throw new UnsupportedOperationException();
        }
        @Override
        public ArrayList<SignUpUser> getUserInfo(){
            throw new UnsupportedOperationException();
        }
        @Override
        public String getUser(int index){
            return "T1001";
        }
    }
    
    public class FactoryImpl extends UserFactory{
    
        @Override
        public void addUser(String user, Patient pat, Doctor doc, Secretary sec, 
                Administrator admin, String password, String title, 
                String forename, String surname, int age, String address){
            
            throw new UnsupportedOperationException();
        }
    }
    
    public class MedicinesImpl extends MedicineStock{
    
        @Override
        public boolean updateStock(int medIndex, int quantity){
            throw new UnsupportedOperationException();
        }
    }    
}
