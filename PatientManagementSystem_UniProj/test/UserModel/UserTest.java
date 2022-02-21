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

/**
 *
 * @author Glenn McKnight
 */
public class UserTest {
    User instance;
    User instanceTwo;
      
    @Before
    public void setUp() {         
        instance = new UserImpl();
    }
    
    @After
    public void tearDown() {
        instance = null;
        assertNull(instance);
    }

    /**
     * Test of generateID method, of class User.
     */
    @Test
    public void testGenerateID() {
        System.out.println("generateID");
        
        ArrayList<String> userIDList = new ArrayList<>();
        userIDList.add("A1001");
        userIDList.add("A1002");
        userIDList.add("A1003");
        userIDList.add("A1004");
                
        UserTypes userType = UserTypes.A;
                
        String expResult = "A1005";        
        String result = instance.generateID(userIDList, userType);
        
        assertEquals(expResult, result);
    }

    /**
     * Test to check a default ID is generated if no users currently exist
     */
    @Test
    public void testGenerateID_defaultID(){
        System.out.println("Generate default ID");
        
        ArrayList<String> userIDList = new ArrayList<>();
        UserTypes userType = UserTypes.D;
        
        String expResult = "D1001";
        String result = instance.generateID(userIDList, userType);
        
        assertEquals(expResult, result);
    }
    /**
     * Test of getUserID method, of class User.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID");
        
        String expResult = "U1001";
        instance.setUserID(expResult);
        
        String result = instance.getUserID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserPassword method, of class User.
     */
    @Test
    public void testGetUserPassword() {
        System.out.println("getUserPassword");
        
        String expResult = "password";
        instance.setUserPassword(expResult);
        
        String result = instance.getUserPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class User.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        
        String expResult = "They";
        instance.setTitle("They");
        
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        
        String expResult = "Phil";
        instance.setFirstName(expResult);
        
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSurname method, of class User.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        
        String expResult = "Philothedump";
        instance.setSurname(expResult);
        
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSaveFileName method, of class User.
     */
    @Test
    public void testGetSaveFileName() {
        System.out.println("getSaveFileName");
        
        String expResult = instance.getClass().getName();
        instance.setSaveFileName(expResult);
        
        String result = instance.getSaveFileName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserID method, of class User.
     */
    @Test
    public void testSetUserID() {
        System.out.println("setUserID");
        
        String userID = "U1001";
        
        String expResult = userID;
        instance.setUserID(userID);
              
        String result = instance.getUserID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserPassword method, of class User.
     */
    @Test
    public void testSetUserPassword() {
        System.out.println("setUserPassword");
        
        String userPassword = "password";
        
        String expResult = userPassword;
        instance.setUserPassword(userPassword);
        
        String result = instance.getUserPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class User.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        
        String title = "Mrs.";
        
        String expResult = title;
        instance.setTitle(title);
        
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class User.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        
        String firstName = "Elma";
        
        String expResult = firstName;
        instance.setFirstName(firstName);
        
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSurname method, of class User.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname");
        
        String surname = "Roberts";
        
        String expResult = surname;
        instance.setSurname(surname);
        
        String result = instance.getSurname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSaveFileName method, of class User.
     */
    @Test
    public void testSetSaveFileName() {
        System.out.println("setSaveFileName");
        
        String saveFileName = this.getClass().getName();
        
        String expResult = saveFileName;
        instance.setSaveFileName(saveFileName);
        
        String result = instance.getSaveFileName();
        assertEquals(expResult, result);
    }

    public class UserImpl extends User {

        public void save() {
        }

        public void load() {
        }

        public void deleteUser(String userID) {
        }

        public List<String> getUserIDList() {
            return null;
        }

        public String returnUser(String userID) {
            return "";
        }

        public List<? extends User> getUserInfo() {
            return null;
        }

        public int getUserIndex(String userID) {
            return 0;
        }
    }
    
}
