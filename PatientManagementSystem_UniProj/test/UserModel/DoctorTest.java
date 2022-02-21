/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import java.util.ArrayList;
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
public class DoctorTest {
   
    Doctor instance;
    Doctor instanceTwo;
    
    @AfterClass
    public static void tearDownClass() {
        Doctor doc1 = new Doctor("D1001", "doctorpassword", "Dr", "Pauline", "Health", 
                "This here surgery, some road, Exeter, EX2 8LB", new ArrayList<>(), new ArrayList<>());
        Doctor doc2 = new Doctor("D1002", "2nddoctorpassword", "Dr", "Jeremy", "Full", 
                "This here surgery, some road, Exeter, EX2 8LB", new ArrayList<>(),new ArrayList<>());
        Doctor doc3 = new Doctor("D1003", "3rddoctorpassword", "Dr", "Ellen", "Ellenson", 
                "This here surgery, some road, Exeter, EX2 8LB", new ArrayList<>(),new ArrayList<>());

        doc1.addDoctorToList(doc1);
        doc1.addDoctorToList(doc2);
        doc1.addDoctorToList(doc3);

        doc1.save();
    }
    
    @Before
    public void setUp() {
        instance = new Doctor("D1001", "docpassword", "They", "George", "Everyman", 
                "15 somewhere, someplace, PL6", new ArrayList<>(), new ArrayList<>());
        instanceTwo= new Doctor("D1002", "2nddocpassword", "Mrs.", "Chai", "Tea", 
                "17 elsewhere, wherever, EX5", new ArrayList<>(), new ArrayList<>());        
        instance.addDoctorToList(instance);
        instance.addDoctorToList(instanceTwo);
    }
    
    @After
    public void tearDown() {
        instance = null;
        instanceTwo = null;
        
        exceptionRule.equals(ExpectedException.none());
        
        assertNull(instance);
        assertNull(instanceTwo);
    }

    /**
     * Rule to allow exceptions to be set for test outcomes.
     */
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    /**
     * Test of save method, of class Doctor.
     */
    @Test
    public void testSaveAndLoad() {
        System.out.println("save");
        
        instance.save();
        Doctor docSerialized = new Doctor();
        docSerialized.load();
                
        assertEquals(instance.getUserInfo().get(0).getTitle(), docSerialized.getUserInfo().get(0).getTitle());
    }

    /**
     * Test that checkLogin method succeeds with valid input, of class Doctor.
     */
    @Test
    public void testCheckLoginSucceeds() {
        System.out.println("checkLogin");
        String userID = "D1001";
        String password = "docpassword";
        
        boolean expResult = true;
        boolean result = instance.checkLogin(userID, password);
        assertEquals(expResult, result);
    }

    /**
     * Test checkLogin method fails with invalid input, class Doctor.
     */
    @Test
    public void testCheckLoginFails() {
        System.out.println("checkLogin");
        String userID = "D101";
        String password = "docpassword";
                
        boolean result = instance.checkLogin(userID, password);
        assertFalse(result);
    }
    
    /**
     * Test that deleteUser method succeeds with correct userID. Of class Doctor.
     */
    @Test
    public void testDeleteUserSucceed() {
        System.out.println("deleteUser");
        String userID = "D1002";
        
        ArrayList<Doctor> expResult = instance.getUserInfo();
        expResult.remove(1);
        
        instance.deleteUser(userID);
        ArrayList<Doctor> result = instance.getUserInfo();
        
        assertEquals(expResult, result);
    }
    /**
     * Test that delete user method fails when unknown userID passed in. OF class doctor.
     */
    @Test
    public void deleteUserFail(){
        String userID = "test";
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
        instance.deleteUser(userID);
        
        int result = instance.getUserIndex(userID);
        assertNotNull(result);
    }
    /**
     * Test of getUserIDList method, of class Doctor.
     */
    @Test
    public void testGetUserIDList() {
       System.out.println("getUserIDList");
        
       ArrayList<String> expResult = new ArrayList<>();
       expResult.add("D1001");
       expResult.add("D1002");

       ArrayList<String> result = instance.getUserIDList();
       assertEquals(expResult, result);
    }

    /**
     * Test that returnUser method succeeds and returns valid user, of class Doctor.
     */
    @Test
    public void testReturnUserSucceeds() {
        System.out.println("returnUser");
        String userID = "D1001";
        
        String expResult = "D1001, They, George, Everyman";
        String result = instance.returnUser(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test that returnUser method fails if invalid user passed in
     */
    @Test
    public void testReturnUserFails() {
        System.out.println("returnUser");
        String userID = "D0000";
        
        String expResult = null;
        String result = instance.returnUser(userID);
        assertEquals(expResult, result);
    }
    /**
     * Test of getUserInfo method, of class Doctor.
     */
    @Test
    public void testGetUserInfo() {
        System.out.println("getUserInfo");
        
        ArrayList<Doctor> expResult = new ArrayList<>();
        expResult.add(instance);
        expResult.add(instanceTwo);
        
        ArrayList<Doctor> result = instance.getUserInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIndex method, ensures return of correct user index. Of class Doctor.
     */
    @Test
    public void testGetUserIndexSucceed() {
        System.out.println("getUserIndex");
        String userID = "D1002";
        
        int expResult = 1;
        int result = instance.getUserIndex(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIndex method, ensures return of correct user. Of class Doctor.
     */
    @Test
    public void testGetUserIndexFail() {
        System.out.println("getUserIndex");
        String userID = "Dtere";
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
                
        int result = instance.getUserIndex(userID);
        assertNotNull(result);
    }
    
    /**
     * Test of addDoctorToList method, of class Doctor.
     */
    @Test
    public void testAddDoctorToList() {
        System.out.println("addAdminToList");
        Doctor tempDoc = new Doctor("A1003", "3rdadminpassword", "Mr.", "Phil", "Philman","This is an address", new ArrayList<>(), new ArrayList<>());
       
        ArrayList<Doctor> result = instance.getUserInfo();
        ArrayList<Doctor> expResult = new ArrayList<>();
        expResult.add(instance);
        expResult.add(instanceTwo);
        expResult.add(tempDoc);
        
        instance.addDoctorToList(tempDoc);
        assertEquals(expResult, result);
    }

    /**
     * Test of doctorsWithRatings method, of class Doctor.
     */
    @Test
    public void testDoctorsWithRatings() {
        System.out.println("doctorsWithRatings");
        
        instance.setRating("D1001", 5);
        
        ArrayList<String> expResult = new ArrayList();
        expResult.add("D1001, George, Everyman, 5.0");
        expResult.add("D1002, Chai, Tea, 0.0");
        
        ArrayList<String> result = instance.doctorsWithRatings();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessages method, of class Doctor.
     */
    @Test
    public void testSetMessages() {
        System.out.println("setMessages");
        String message = "Hello world";
        String secondMessage = "Yea buddy";
              
        instance.getUserInfo().get(0).setMessages(message);
        instance.getUserInfo().get(0).setMessages(secondMessage);
        
        assertEquals(message, instance.getUserInfo().get(0).getMessages().get(0));
        assertEquals(secondMessage, instance.getUserInfo().get(0).getMessages().get(1));
    }

    /**
     * Test of getMessages method, of class Doctor.
     */
    @Test
    public void testGetMessages() {
        System.out.println("getMessages");
        
        String message = "Hello world";
        String secondMessage = "Yea buddy";
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(message);
        expResult.add(secondMessage);
        
        instance.getUserInfo().get(0).setMessages(message);
        instance.getUserInfo().get(0).setMessages(secondMessage);
        
        ArrayList<String> result = instance.getUserInfo().get(0).getMessages();
        assertEquals(expResult, result);
    }
    /**
     * Test of receiveMessage method, of class Doctor.
     */
    @Test
    public void testReceiveMessage() {
        System.out.println("receiveMessage");
        String userID = "D1002";
        String message = "This is a message";
         
        instance.receiveMessage(userID, message);
        String expResult = message;
        
        String result = instance.getUserInfo().get(1).getMessages().get(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserMessages method, returns messages of userID passed in. Of class Doctor.
     */
    @Test
    public void testGetUserMessagesSucceed() {
        System.out.println("getUserMessages");
        String userID = "D1001";
        String message = "Something interesting";
        
        instance.getUserInfo().get(0).setMessages(message);
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(message);
        
        List<String> result = instance.getUserMessages(userID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUserMessages method, returns null if incorrect ID passed. Of class Doctor.
     */
    @Test
    public void testGetUserMessagesFail() {
        System.out.println("getUserMessages");
        String userID = "AAAAA";
        String message = "Something interesting";
        
        instance.getUserInfo().get(0).setMessages(message);
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
        
        List<String> result = instance.getUserMessages(userID);        
        assertNotNull(result);
    }

    /**
     * Test of deleteMessage method, deletes message if index is correct. Of class Doctor.
     */
    @Test
    public void testDeleteMessageSucceed() {
        System.out.println("deleteMessage");
        String userID = "D1001";
        String message = "Something to say";
        String secondMessage = "Thing";
        int messageIndex = 0;
        
        instance.getUserInfo().get(0).setMessages(message);
        instance.getUserInfo().get(0).setMessages(secondMessage);
        instance.deleteMessage(userID, messageIndex);
                
        String expResult = secondMessage;
        String result = instance.getUserInfo().get(0).getMessages().get(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteMessage method, throws exception if message index is incorrect. 
     * Of class Doctor.
     */
    @Test
    public void testDeleteMessage_messageIndexFail() {
        System.out.println("deleteMessage");
        String userID = "D1001";
        int messageIndex = 90;
        
        exceptionRule.expect(IndexOutOfBoundsException.class);
        
        instance.deleteMessage(userID, messageIndex);
        
        String result = instance.getUserInfo().get(0).getMessages().get(0);
        assertNotNull(result);
    }    

    /**
     * Test of getAddress method, of class Doctor.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        
        String expResult = "15 somewhere, someplace, PL6";        
        String result = instance.getAddress();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Doctor.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "15 somewhere, someplace, PL6";
        
        instance.setAddress(address);
        String expResult = address;
        String result = instance.getAddress();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getRating method, of class Doctor.
     */
    @Test
    public void testGetRating_0args() {
        System.out.println("getRating");
        
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(5);
        expResult.add(4);
        expResult.add(4);
        instance.getUserInfo().get(0).setRating(expResult);
        
        ArrayList<Integer> result = instance.getRating();        
        assertEquals(expResult, result);
    }

    /**
     * Test of getRating method, of class Doctor.
     */
    @Test
    public void testGetRating_String() {
        System.out.println("getRating");
        String userID = "D1002";
        
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(2);
        expResult.add(3);
        instance.getUserInfo().get(1).setRating(expResult);
        
        ArrayList<Integer> result = instance.getRating(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of setRating method, of class Doctor.
     */
    @Test
    public void testSetRating_ArrayList() {
        System.out.println("setRating");
        
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(3);
        expResult.add(3);
        expResult.add(3);                
        instance.getUserInfo().get(0).setRating(expResult);
        
        ArrayList<Integer> result = instance.getUserInfo().get(0).getRating();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRating method, of class Doctor.
     */
    @Test
    public void testSetRating_String_int() {
        System.out.println("setRating");
        
        String doctorID = "D1001";
        int rating = 5;
           
        instance.setRating(doctorID, rating);
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(rating);
        
        ArrayList<Integer> result = instance.getRating(doctorID);
        assertEquals(expResult, result);
    }
}
