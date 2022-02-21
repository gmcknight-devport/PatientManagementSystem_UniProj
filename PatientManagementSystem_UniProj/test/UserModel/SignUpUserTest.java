/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import java.util.ArrayList;
import java.util.LinkedList;
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
public class SignUpUserTest {
    SignUpUser instance;
    SignUpUser instanceTwo;
    
    @AfterClass
    public static void tearDownClass(){
        SignUpUser signUp = new SignUpUser();
        signUp.signupRequest("passpass12", "Mrs.", "Franki", "Franklin", 34, 
                "15, don't know where I live, Plymouth, PL3 5TG");
        signUp.signupRequest("passwordy1", "They", "Sky", "Auburn", 19, 
                "109, spiffing avenue, Plymouth, PL1 1PO");
        
        signUp.save();
    }
    
    @Before
    public void setUp() {
        instance = new SignUpUser("T1001", "temppassword", "They", "George", "Everyman", 20, 
                "15 somewhere, someplace, PL6");
        instanceTwo = new  SignUpUser("T1002", "2ndtemppassword", "Mrs.", "Chai", "Tea", 32,
                "17 elsewhere, wherever, EX5");
        
        instance.signupRequest(instance.getUserPassword(), instance.getTitle(), instance.getFirstName(), 
                instance.getSurname(), instance.getAge(), instance.getAddress());
        instance.signupRequest(instanceTwo.getUserPassword(), instanceTwo.getTitle(), instanceTwo.getFirstName(), 
                instanceTwo.getSurname(), instanceTwo.getAge(), instanceTwo.getAddress());
    }
    
    @After
    public void tearDown() {
        instance = null;
        instanceTwo = null;
        
        exceptionRule.equals(ExpectedException.none());
        
        assertNull(instance);
        assertNull(instanceTwo);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    /**
     * Test of signupRequest method, of class SignUpUser.
     */
    @Test
    public void testSignupRequest() {
        System.out.println("signupRequest");
        String password = "tempPASS";
        String title = "Mrs";
        String forename = "Gee";
        String surname = "Woman";
        int age = 24;
        String address = "15 somewhere, someplace, PL6";
       
        instance.signupRequest(password, title, forename, surname, age, address);
        
        int expResult = age;
        int result = instance.getUserInfo().get(2).getAge();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of removeSignupRequest method, of class SignUpUser.
     */
    @Test
    public void testRemoveSignupRequest() {
        System.out.println("removeSignupRequest");
        int index = 0;
        String surname = "Tea";
             
        instance.removeSignupRequest(index);
        
        String expResult = surname;
        String result = instance.getUserInfo().get(0).getSurname();
        
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getApprovedPatient method, of class SignUpUser.
     */
    @Test
    public void testGetApprovedPatient() {
        System.out.println("getApprovedPatient");
        int index = 1;
        
        String expResult = instanceTwo.getUserID();
        String result = instance.getApprovedPatient(index).getUserID();
        assertEquals(expResult, result);
    }

    /**
     * Test of save method, of class SignUpUser.
     */
    @Test
    public void testSaveAndLoad() {
        System.out.println("save");
        
        instance.save();
        SignUpUser userSerialized = new SignUpUser();
        userSerialized.load();
                
        assertEquals(instance.getUserInfo().get(1).getAddress(), userSerialized.getUserInfo().get(1).getAddress());
    }

    /**
     * Test of deleteUser method, of class SignUpUser.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String userID = "T1001";
        
        String expResult = instance.getUserInfo().get(1).getUserID();
        
        instance.deleteUser(userID);
        String result = instance.getUserInfo().get(0).getUserID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserInfo method, of class SignUpUser.
     */
    @Test
    public void testGetUserInfo() {
        System.out.println("getUserInfo");
        
        String userID = "T1001";
        String expResult = userID;
        
        String result = instance.getUserInfo().get(0).getUserID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIDList method, of class SignUpUser.
     */
    @Test
    public void testGetUserIDList() {
        System.out.println("getUserIDList");
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("T1001");
        expResult.add("T1002");
        
        ArrayList<String> result = instance.getUserIDList();
        assertEquals(expResult, result);
    }

    /**
     * Test that returnUser method succeeds if valid userID passed in, of class SignUpUser.
     */
    @Test
    public void testReturnUserSucceeds() {
        System.out.println("returnUser");
        String userID = "T1001";
        String expResult = "T1001, They, George, Everyman";
                
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
     * Test of getUser method, of class SignUpUser.
     */
    @Test
    public void testGetUser() {
        System.out.println("getIndividualUserID");
        int index = 1;
        String userID = "T1002";
        
        String expResult = userID;
        String result = instance.getUser(index);
        
        assertEquals(expResult, result);
    }

    /**
     * Test that getUserIndex method succeeds, of class SignUpUser.
     */
    @Test
    public void testGetUserIndexSucceed() {
        System.out.println("getUserIndex");
        String userID = "T1002";
        int expResult = 1;
        int result = instance.getUserIndex(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIndex method, fails and returns exception. Of class SignUpUser.
     */
    @Test
    public void testGetUserIndexFail() {
        System.out.println("getUserIndex");
        String userID = "S0088";
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
                
        int result = instance.getUserIndex(userID);
        assertNotNull(result);
    }
    
    /**
     * Test of getAge method, of class SignUpUser.
     */
    @Test
    public void testGetAge() {
        System.out.println("getAge");
        
        int expResult = 20;
        int result = instance.getUserInfo().get(0).getAge();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class SignUpUser.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        
        String address = "15 somewhere, someplace, PL6";
        String expResult = address;
        String result = instance.getUserInfo().get(0).getAddress();
        
        assertEquals(expResult, result);
    }
    
}
