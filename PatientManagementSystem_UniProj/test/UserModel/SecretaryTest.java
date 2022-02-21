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
public class SecretaryTest {
    
    Secretary instance;
    Secretary instanceTwo;
    
    @AfterClass
    public static void tearDownClass() {
        Secretary sec1 = new Secretary("S1001", "secpassword", "Mr", "Norm", "Surly", new ArrayList<>());
        
        sec1.addSecToList(sec1);
        
        sec1.save();
    }
    
    @Before
    public void setUp() {
        instance = new Secretary("S1001", "secpassword", "They", "George", "Everyman", new ArrayList<>());
        instanceTwo = new Secretary("S1002", "2ndsecpassword", "Mrs.", "Chai", "Tea", new ArrayList<>());        
        instance.addSecToList(instance);
        instance.addSecToList(instanceTwo);
    }
    
    @After
    public void tearDown() {
        instance = null;
        instanceTwo = null;
        
        exceptionRule.equals(ExpectedException.none());
                
        assertNull(instance);
        assertNull(instance);
    }

    /**
     * Rule to allow exceptions to be set for test outcomes.
     */
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    /**
     * Test of save method, of class Secretary.
     */
    @Test
    public void testSaveandLoad() {
        System.out.println("save");
        
        instance.save();
        Secretary secSerialized = new Secretary();
        secSerialized.load();
        
        assertEquals(instance.getUserInfo().get(1).getUserID(), secSerialized.getUserInfo().get(1).getUserID());
    }
    
    /**
     * Test that checkLogin method succeeds with valid input, of class Secretary.
     */
    @Test
    public void testCheckLoginSuceeds() {
        System.out.println("checkLogin");
        String userID = "S1001";
        String password = "secpassword";
        
        boolean expResult = true;
        boolean result = instance.checkLogin(userID, password);
        assertEquals(expResult, result);
    }

    /**
     * Test that checkLogin method fails with invalid input, of class Secretary.
     */
    @Test
    public void testCheckLoginFails() {
        System.out.println("checkLogin");
        String userID = "Joe";
        String password = "password";
        
        boolean result = instance.checkLogin(userID, password);
        assertFalse(result);
    }
    
    /**
     * Test that deleteUser method successfully removes user. Of class Secretary.
     */
    @Test
    public void testDeleteUserSucceeds() {
        System.out.println("deleteUser");
        String userID = "S1002";
        
        Secretary testSec = new Secretary();
        testSec.addSecToList(instance);
        testSec.addSecToList(instanceTwo);        
        
        ArrayList<Secretary> expResult = instance.getUserInfo();
        expResult.remove(1);
        
        testSec.deleteUser(userID);
        ArrayList<Secretary> result = testSec.getUserInfo();
        
        assertEquals(expResult, result);
    }

    /**
     * Test that delete user method fails when unknown userID passed in. OF class Secretary.
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
     * Test of getUserInfo method, of class Secretary.
     */
    @Test
    public void testGetUserInfo() {
       System.out.println("getUserInfo");
       
        ArrayList<Secretary> expResult = new ArrayList<>();
        expResult.add(instance);
        expResult.add(instanceTwo);
        
        ArrayList<Secretary> result = instance.getUserInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIDList method, of class Secretary.
     */
    @Test
    public void testGetUserIDList() {
        System.out.println("getUserIDList");
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("S1001");
        expResult.add("S1002");
        
        ArrayList<String> result = instance.getUserIDList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIndex method, ensures correct user index is returned. Of class Secretary.
     */
    @Test
    public void testGetUserIndex() {
       System.out.println("getUserIndex");
        String userID = "S1002";
        
        int expResult = 1;
        int result = instance.getUserIndex(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIndex method, ensures return of correct user. Of class Secretary.
     */
    @Test
    public void testGetUserIndexFail() {
        System.out.println("getUserIndex");
        String userID = "S10012";
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
                
        int result = instance.getUserIndex(userID);
        assertNotNull(result);
    }
    
    /**
     * Test that returnUser method succeeds and returns valid user, of class Secretary.
     */
    @Test
    public void testReturnUserSucceeds() {
        System.out.println("returnUser");
        String userID = "S1001";
         
        String expResult = "S1001, They, George, Everyman";
        String result = instance.returnUser(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test that returnUser method fails if invalid user passed in
     */
    @Test
    public void testReturnUserFails() {
        System.out.println("returnUser");
        String userID = "k";
        
        String expResult = null;
        String result = instance.returnUser(userID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addSecToList method, of class Secretary.
     */
    @Test
    public void testAddSecToList() {
        System.out.println("addAdminToList");
        Secretary tempSec = new Secretary("S1003", "3rdadminpassword", "Mr.", "Phil", "Philman", new ArrayList<>());
       
        ArrayList<Secretary> result = instance.getUserInfo();
        ArrayList<Secretary> expResult = new ArrayList<>();
        expResult.add(instance);
        expResult.add(instanceTwo);
        expResult.add(tempSec);
        
        instance.addSecToList(tempSec);
        assertEquals(expResult, result);
    }

      /**
     * Test of setMessages method, of class Secretary.
     */
    @Test
    public void testSetMessages() {
       System.out.println("setMessages");
        String message = "Hello world";
        String secondMessage = "Yea buddy";
        
        instance.getUserInfo().get(0).setMessages(message);
        instance.getUserInfo().get(0).setMessages(secondMessage);
              
        assertEquals(secondMessage, instance.getUserInfo().get(0).getMessages().get(1));
    }

    /**
     * Test of getMessages method, of class Secretary.
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
     * Test of receiveMessage method, of class Secretary.
     */
    @Test
    public void testReceiveMessage() {
        System.out.println("receiveMessage");
        String userID = "S1002";
        String message = "This is a message";
         
        instance.receiveMessage(userID, message);
        String expResult = message;
        
        String result =  instance.getUserInfo().get(1).getMessages().get(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserMessages method, returns messages of userID passed in. Of class Secretary.
     */
    @Test
    public void testGetUserMessagesSucceed() {
        System.out.println("getUserMessages");
        String userID = "S1001";
        String message = "Something interesting";
        
        instance.getUserInfo().get(0).setMessages(message);
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(message);
        
        List<String> result = instance.getUserMessages(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserMessages method, returns null if incorrect ID passed. Of class Administrator.
     */
    @Test
    public void testGetUserMessagesFail() {
        System.out.println("getUserMessages");
        String userID = "";
        String message = "Something interesting";
        
        instance.getUserInfo().get(0).setMessages(message);
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
        
        List<String> result = instance.getUserMessages(userID);        
        assertNotNull(result);
    }
    
    /**
     * Test of deleteMessage method, deletes message if index is correct. Of class Secretary.
     */
    @Test
    public void testDeleteMessage() {
       System.out.println("deleteMessage");
        String userID = "S1001";
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
     * Of class Secretary.
     */
    @Test
    public void testDeleteMessage_messageIndexFail() {
        System.out.println("deleteMessage");
        String userID = "S1001";
        int messageIndex = 800;
        
        exceptionRule.expect(IndexOutOfBoundsException.class);
        
        instance.deleteMessage(userID, messageIndex);
        
        String result = instance.getUserInfo().get(0).getMessages().get(0);
        assertNotNull(result);
    }    
}
