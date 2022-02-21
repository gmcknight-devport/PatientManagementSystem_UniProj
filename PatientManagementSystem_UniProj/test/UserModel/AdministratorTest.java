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
public class AdministratorTest {
    Administrator instance;
    Administrator instanceTwo;
    
    @AfterClass
    public static void tearDownClass() {
        Administrator admin1 = new Administrator("A1001", "adminpassword", "Miss", "Melanie", "Lady", new ArrayList<>());        
        admin1.addAdminToList(admin1);
        
        admin1.save();
    }
    
    @Before
    public void setUp() {
        instance = new Administrator("A1001", "adminpassword", "They", "George", "Everyman", new ArrayList<>());
        instanceTwo = new Administrator("A1002", "2ndadminpassword", "Mrs.", "Chai", "Tea", new ArrayList<>());        
        instance.addAdminToList(instance);
        instance.addAdminToList(instanceTwo);
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
     * Test of save method, of class Administrator.
     */
    @Test
    public void testSaveAndLoad() {
        System.out.println("save");
                
        instance.save();
        Administrator adminSerialized = new Administrator();
        adminSerialized.load();
        
        String expResult = "Tea";
        String result = adminSerialized.getUserInfo().get(1).getSurname();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test that checkLogin method succeeds with correct input, of class Administrator.
     */
    @Test
    public void testCheckLoginSucceeds() {
        System.out.println("checkLoginSucceeds");
        String userID = "A1001";
        String password = "adminpassword";
        
        boolean expResult = true;
        boolean result = instance.checkLogin(userID, password);
        assertEquals(expResult, result);
    }

    /**
     * Test that checkLogin method fails with incorrect input. Administrator class.
     */
    @Test
    public void testCheckLoginFails(){
        System.out.println("checkLoginFails");
        
        String userID = "A1002";
        String password = "adminspassword";
                
        boolean result = instance.checkLogin(userID, password);
        assertFalse(result);
    }
    /**
     * Test that deleteUser method successfully removes user, of class Administrator.
     */
    @Test
    public void testDeleteUserSucceeds() {
        System.out.println("deleteUser");
        String userID = "A1002";
              
        ArrayList<Administrator> expResult = instance.getUserInfo();
        expResult.remove(1);
        
        instance.deleteUser(userID);
        ArrayList<Administrator> result = instance.getUserInfo();
        
        assertEquals(expResult, result);
    }

    /**
     * Test that delete user method fails when unknown userID passed in. OF class Administrator.
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
     * Test of getUserInfo method, of class Administrator.
     */
    @Test
    public void testGetUserInfo() {
        System.out.println("getUserInfo");
        
        ArrayList<Administrator> expResult = new ArrayList<>();
        expResult.add(instance);
        expResult.add(instanceTwo);
        
        ArrayList<Administrator> result = instance.getUserInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIDList method, of class Administrator.
     */
    @Test
    public void testGetUserIDList() {
        System.out.println("getUserIDList");
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("A1001");
        expResult.add("A1002");
        
        ArrayList<String> result = instance.getUserIDList();
        assertEquals(expResult, result);
    }

    /**
     * Test that returnUser method succeeds, of class Administrator.
     */
    @Test
    public void testReturnUserSucceeds() {
        System.out.println("returnUser");
        String userID = "A1001";
        
        String expResult = "A1001, They, George, Everyman";
        String result = instance.returnUser(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test that returnUser method fails if invalid user passed in
     */
    @Test
    public void testReturnUserFails() {
        System.out.println("returnUser");
        String userID = "B7767";
        
        String expResult = null;
        String result = instance.returnUser(userID);
        assertEquals(expResult, result);
    }
    /**
     * Test of getUserIndex method, ensures return of correct user. Of class Administrator.
     */
    @Test
    public void testGetUserIndexSucceed() {
        System.out.println("getUserIndex");
        String userID = "A1002";
        
        int expResult = 1;
        int result = instance.getUserIndex(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIndex method, ensures return of correct user. Of class Administrator.
     */
    @Test
    public void testGetUserIndexFail() {
        System.out.println("getUserIndex");
        String userID = "A0088";
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
                
        int result = instance.getUserIndex(userID);
        assertNotNull(result);
    }
    
    /**
     * Test of addAdminToList method, of class Administrator.
     */
    @Test
    public void testAddAdminToList() {
        System.out.println("addAdminToList");
        Administrator tempAdmin = new Administrator("A1003", "3rdadminpassword", "Mr.", "Phil", "Philman", new ArrayList<>());
       
        ArrayList<Administrator> result = instance.getUserInfo();
        ArrayList<Administrator> expResult = new ArrayList<>();
        expResult.add(instance);
        expResult.add(instanceTwo);
        expResult.add(tempAdmin);
        
        instance.addAdminToList(tempAdmin);
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessages method, of class Administrator.
     */
    @Test
    public void testSetMessages() {
        System.out.println("setMessages");
        String message = "Hello world";
        String secondMessage = "Yea buddy";
        
        instance.getUserInfo().get(0).setMessages(message);
        instance.getUserInfo().get(0).setMessages(secondMessage);
        
        String expResult = secondMessage;
        String result = instance.getUserInfo().get(0).getMessages().get(1);
                
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessages method, of class Administrator.
     */
    @Test
    public void testGetMessages() {
        System.out.println("getMessages");
        
        String message = "Hello world";
        String secondMessage = "Yea buddy";
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(message);
        expResult.add(secondMessage);
        ArrayList<String> result = instance.getUserInfo().get(0).getMessages();
        
        instance.getUserInfo().get(0).setMessages(message);
        instance.getUserInfo().get(0).setMessages(secondMessage);
        
        assertEquals(expResult, result);
    }    
    
    /**
     * Test of receiveMessage method, of class Administrator.
     */
    @Test
    public void testReceiveMessage() {
        System.out.println("receiveMessage");
        String userID = "A1002";
        String message = "This is a message";
         
        instance.receiveMessage(userID, message);
        
        String expResult = message;        
        String result = instance.getUserInfo().get(1).getMessages().get(0);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserMessages method, returns messages of specific userID. Of class Administrator.
     */
    @Test
    public void testGetUserMessagesSucceed() {
        System.out.println("getUserMessages");
        String userID = "A1001";
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
        String userID = "A0000";
        String message = "Something interesting";
        
        instance.getUserInfo().get(0).setMessages(message);
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
        
        List<String> result = instance.getUserMessages(userID);        
        assertNotNull(result);
    }

    /**
     * Test of deleteMessage method, deletes message if correct userID and index.
     * Of class Administrator.
     */
    @Test
    public void testDeleteMessageSucceed() {
        System.out.println("deleteMessage");
        String userID = "A1001";
        String message = "Something to say";
        String secondMessage = "Thing";
        int messageIndex = 0;
        
        instance.getUserInfo().get(0).setMessages(message);
        instance.getUserInfo().get(0).setMessages(secondMessage);
        instance.getUserInfo().get(0).setMessages(secondMessage);
        instance.deleteMessage(userID, messageIndex);
        
        String expResult = secondMessage;
        String result = instance.getUserInfo().get(0).getMessages().get(0);
        assertEquals(expResult, result);
    }    
    /**
     * Test of deleteMessage method, throws exception if message index is incorrect. 
     * Of class Administrator.
     */
    @Test
    public void testDeleteMessage_messageIndexFail() {
        System.out.println("deleteMessage");
        String userID = "A1001";
        int messageIndex = -120;
        
        exceptionRule.expect(IndexOutOfBoundsException.class);
        
        instance.deleteMessage(userID, messageIndex);
        
        String result = instance.getUserInfo().get(0).getMessages().get(0);
        assertNotNull(result);
    }    
}
