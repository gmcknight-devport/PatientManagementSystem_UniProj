/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class PatientTest {
    Patient instance;
    Patient instanceTwo;
    
    @AfterClass
    public static void tearDownClass() {
        Patient patient1 = new Patient("P1001", "patientpassword", "Mr", "John", 
                "Sickness", 30, "5 Deathly Road, Plymouth, PL1 1EE", 'M', new ArrayList<>(), 
                new ArrayList<>(), new LinkedList<>(), new LinkedList<>());
        Patient patient2 = new Patient("P1002", "2ndpatientpassword", "They", 
                "Air", "Syphon", 40, "16 Unfortunate Ave, Plymouth, PL20 9IE", 
                'O', new ArrayList<>(), new ArrayList<>(), new LinkedList<>(), new LinkedList<>());
        Patient patient3 = new Patient("P1003", "3rdpatientpassword", "Mrs", "Philomena", 
                "Lynott", 80, "5 Emerald View, Plymouth, PL15 7BB", 'F', 
                new ArrayList<>(), new ArrayList<>(), new LinkedList<>(), new LinkedList<>());
        
        patient1.addPatientToList(patient1);
        patient1.addPatientToList(patient2);
        patient1.addPatientToList(patient3);
        
        patient1.save();
    }
    
    @Before
    public void setUp() {
        instance = new Patient("P1001", "patientpassword", "They", "George", "Everyman", 20, 
                "15 somewhere, someplace, PL6", 'T', new ArrayList<>(), new ArrayList<>(),
                new LinkedList<>(), new LinkedList<>());
        instanceTwo = new Patient("P1002", "2ndpatientpassword", "Mrs.", "Chai", "Tea", 32,
                "17 elsewhere, wherever, EX5", 'F', new ArrayList<>(), new ArrayList<>(),
                new LinkedList<>(), new LinkedList<>());  
        
        instance.addPatientToList(instance);
        instance.addPatientToList(instanceTwo);
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
     * Test of save method, of class Patient.
     */
    @Test
    public void testSaveAndLoad() {
        System.out.println("save");
       
        instance.save();
        Patient patSerialized = new Patient();
        patSerialized.load();
        
        assertEquals(instance.getUserInfo().get(0).getUserID(), patSerialized.getUserInfo().get(0).getUserID());
        assertEquals(instance.getUserInfo().get(1).getUserID(), patSerialized.getUserInfo().get(1).getUserID());
        assertEquals(instance.getUserInfo().get(1).getGender(), patSerialized.getUserInfo().get(1).getGender());
        assertEquals(instance.getUserInfo().get(0).getAge(), patSerialized.getUserInfo().get(0).getAge());
    }
    
    /**
     * Test that checkLogin method succeeds with correct input, of class Patient.
     */
    @Test
    public void testCheckLoginSucceeds() {
        System.out.println("checkLogin");
        String userID = "P1001";
        String password = "patientpassword";
        
        boolean expResult = true;
        boolean result = instance.checkLogin(userID, password);
        assertEquals(expResult, result);
    }

    /**
     * Test that checkLogin method fails with incorrect input, of class Patient.
     */
    @Test
    public void testCheckLoginFails() {
        System.out.println("checkLogin");
        String userID = "P3001";
        String password = "patpass";
        
        boolean result = instance.checkLogin(userID, password);
        assertFalse(result);
    }
    
    /**
     * Test that deleteUser method successfully removes user, of class Patient.
     */
    @Test
    public void testDeleteUserSucceeds() {
        System.out.println("deleteUser");
        String userID = "P1002";
                
        ArrayList<Patient> expResult = instance.getUserInfo();
        expResult.remove(1);
        
        instance.deleteUser(userID);
        ArrayList<Patient> result = instance.getUserInfo();
        
        assertEquals(expResult, result);
    }

    /**
     * Test that delete user method fails when unknown userID passed in. OF class Patient.
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
     * Test of getUserIDList method, of class Patient.
     */
    @Test
    public void testGetUserIDList() {
        System.out.println("getUserIDList");
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("P1001");
        expResult.add("P1002");
        
        ArrayList<String> result = instance.getUserIDList();
        assertEquals(expResult, result);
    }

    /**
     * Test that returnUser method succeeds and returns valid user, of class Patient.
     */
    @Test
    public void testReturnUserSucceeds() {
        System.out.println("returnUser");
        String userID = "P1001";
              
        String expResult = "P1001, They, George, Everyman";
        String result = instance.returnUser(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test that returnUser method fails if invalid user passed in.
     */
    @Test
    public void testReturnUserFails() {
        System.out.println("returnUser");
        String userID = "P0999";
        
        String expResult = null;
        String result = instance.returnUser(userID);
        assertEquals(expResult, result);
    }
    /**
     * Test of getUserInfo method, of class Patient.
     */
    @Test
    public void testGetUserInfo() {
        System.out.println("getUserInfo");
        
        ArrayList<Patient> expResult = new ArrayList<>();
        expResult.add(instance);
        expResult.add(instanceTwo);
        
        ArrayList<Patient> result = instance.getUserInfo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserIndex method,ensures return of correct user index. Of class Patient.
     */
    @Test
    public void testGetUserIndexSucceed() {
        System.out.println("getUserIndex");
        String userID = "P1002";
        
        int expResult = 1;
        int result = instance.getUserIndex(userID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUserIndex method, ensures return of correct user. Of class Patient.
     */
    @Test
    public void testGetUserIndexFail() {
        System.out.println("getUserIndex");
        String userID = "8";
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
                
        int result = instance.getUserIndex(userID);
        assertNotNull(result);
    }

    /**
     * Test of addPatientToList method, of class Patient.
     */
    @Test
    public void testAddPatientToList() {
        System.out.println("addAdminToList");
        Patient tempPat = new Patient("P1003", "3rdpatientpassword", "Mr.", "Phil", 
                "Philman", 40, "This is an address", 'M', new ArrayList<>(), new ArrayList<>(),
                new LinkedList<>(), new LinkedList<>());
               
        ArrayList<Patient> result = instance.getUserInfo();
        ArrayList<Patient> expResult = new ArrayList<>();
        expResult.add(instance);
        expResult.add(instanceTwo);
        expResult.add(tempPat);
        
        instance.addPatientToList(tempPat);
        assertEquals(expResult, result);
    }

    /**
     * Test of receiveMessage method, of class Patient.
     */
    @Test
    public void testReceiveMessage() {
        System.out.println("receiveMessage");
        String userID = "P1002";
        String message = "This is a message";
         
        instance.receiveMessage(userID, message);
        String expResult = message;
        
        String result = instance.getUserInfo().get(1).getMessages().get(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserMessages method, returns messages of specific userID. Of class Patient.
     */
    @Test
    public void testGetUserMessages() {
        System.out.println("getUserMessages");
        String userID = "P1001";
        String message = "Something interesting";
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(message);
        List<String> result = instance.getUserMessages(userID);
                
        instance.getUserInfo().get(0).setMessages(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserMessages method, returns null if incorrect ID passed. Of class Patient.
     */
    @Test
    public void testGetUserMessagesFail() {
        System.out.println("getUserMessages");
        String userID = "P10001";
        String message = "Something interesting";
        
        instance.getUserInfo().get(0).setMessages(message);
        
        exceptionRule.expect(ArrayIndexOutOfBoundsException.class);
        
        List<String> result = instance.getUserMessages(userID);        
        assertNotNull(result);
    }
    /**
     * Test of deleteMessage method, deletes message if index is correct. Of class Patient.
     */
    @Test
    public void testDeleteMessageSucceed() {
        System.out.println("deleteMessage");
        String userID = "P1001";
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
     * Of class Patient.
     */
    @Test
    public void testDeleteMessage_messageIndexFail() {
        System.out.println("deleteMessage");
        String userID = "P1001";
        int messageIndex = -120;
        
        exceptionRule.expect(IndexOutOfBoundsException.class);
        
        instance.deleteMessage(userID, messageIndex);
        
        String result = instance.getUserInfo().get(0).getMessages().get(0);
        assertNotNull(result);
    }    
    
    /**
     * Test of getAge method, of class Patient.
     */
    @Test
    public void testGetAge() {
        System.out.println("getAge");
        
        int expResult = 20;
        int result = instance.getUserInfo().get(0).getAge();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setAge method, of class Patient.
     */
    @Test
    public void testSetAge() {
        System.out.println("setAge");
        int age = 45;
        
        instance.getUserInfo().get(0).setAge(age);
        
        int expResult = age;
        int result = instance.getUserInfo().get(0).getAge();
                
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Patient.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        
        String address = "15 somewhere, someplace, PL6";
        String expResult = address;
        String result = instance.getUserInfo().get(0).getAddress();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Patient.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        
        String address = "Something else, BS23";
        
        instance.getUserInfo().get(0).setAddress(address); 
        
        String expResult = address;
        String result = instance.getUserInfo().get(0).getAddress();
                       
        assertEquals(expResult, result);
    }

    /**
     * Test of getGender method, of class Patient.
     */
    @Test
    public void testGetGender() {
        System.out.println("getGender");
        
        char expResult = 'F';
        char result = instance.getUserInfo().get(1).getGender();
                
        assertEquals(expResult, result);
    }

    /**
     * Test of setGender method, of class Patient.
     */
    @Test
    public void testSetGender() {
        System.out.println("setGender");
        char gender = 'T';
        
        instance.getUserInfo().get(1).setGender(gender);
        
        char[] expResult = new char[1];
        expResult[0] = gender;
        char[] result = new char[1];
        result[0] = instance.getUserInfo().get(1).getGender();
                
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getNotes method, of class Patient.
     */
    @Test
    public void testGetNotes_0args() {
        System.out.println("getNotes");
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("No helth problems");
        expResult.add("All results came back negative");
        instance.getUserInfo().get(1).setNotes(expResult);
        
        ArrayList<String> result = instance.getUserInfo().get(1).getNotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNotes method, of class Patient.
     */
    @Test
    public void testGetNotes_String() {
        System.out.println("getNotes");
        String patID = "P1001";
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("No helth problems");
        expResult.add("All results came back negative");
        instance.setNotes(patID, expResult.get(0));
        instance.setNotes(patID, expResult.get(1));
        
        ArrayList<String> result = instance.getNotes(patID);
        assertEquals(expResult, result);
    }

    /**
     * Test of setNotes method, of class Patient.
     */
    @Test
    public void testSetNotes_ArrayList() {
        System.out.println("setNotes");
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("No helth problems");
        expResult.add("All results came back negative");
        instance.getUserInfo().get(0).setNotes(expResult);
        
        ArrayList<String> result = instance.getUserInfo().get(0).getNotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNotes method, of class Patient.
     */
    @Test
    public void testSetNotes_String() {
        System.out.println("setNotes");
        String userID = "P1002";
        String notes = "All results came back negative";
        
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(notes);
        ArrayList<String> result = instance.getNotes(userID);
        
        instance.setNotes(userID, notes);
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessages method, of class Patient.
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
     * Test of getMessages method, of class Patient.
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
     * Test of getPrescriptions method, of class Patient.
     */
    @Test
    public void testGetPrescriptions_0args() {
        System.out.println("getPrescriptions");
        
        LinkedList<String> expResult = new LinkedList<>();
        expResult.addFirst("Medicine 1");
        expResult.addFirst("Second medicine");
        
        instance.getUserInfo().get(0).setPrescriptions(expResult);
        
        LinkedList<String> result = instance.getUserInfo().get(0).getPrescriptions();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrescriptions method, of class Patient.
     */
    @Test
    public void testGetPrescriptions_String() {
        System.out.println("getPrescriptions");
        String userID = "P1002";
        
        LinkedList<String> prescriptions = new LinkedList<>();
        prescriptions.addFirst("Medicine 1");
        prescriptions.addFirst("Second medicine");
        
        LinkedList<String> expResult = prescriptions;
        
        instance.setPrescriptions(userID, expResult.get(1));
        instance.setPrescriptions(userID, expResult.get(0));
        
        LinkedList<String> result = instance.getPrescriptions(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrescriptions method, of class Patient.
     */
    @Test
    public void testSetPrescriptions_LinkedList() {
        System.out.println("setPrescriptions");

        LinkedList<String> prescriptions = new LinkedList<>();
        prescriptions.addFirst("Medicine 1");
        prescriptions.addFirst("Second medicine");
        
        instance.getUserInfo().get(0).setPrescriptions(prescriptions);
        
        LinkedList<String> expResult = prescriptions;
        LinkedList result = instance.getUserInfo().get(0).getPrescriptions();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrescriptions method, of class Patient.
     */
    @Test
    public void testSetPrescriptions_String_String() {
        System.out.println("setPrescriptions");
        String patientID = "P1001";
        String prescription = "This is the prescription";
        
        instance.setPrescriptions(patientID, prescription);
        
        LinkedList<String> expResult = new LinkedList<>();
        expResult.add(prescription);
        
        LinkedList<String> result = instance.getPrescriptions(patientID);                
        assertEquals(expResult, result);
    }

    /**
     * Test of getAppointmentHistory method, of class Patient.
     */
    @Test
    public void testGetAppointmentHistory_0args() {
        System.out.println("getAppointmentHistory");
        String appointment = "D1001, 20/04/23, 13:00";
        String appointment2 = "D1002, 21/04/23, 13:00";
        String patID = "P1001";
        
        LinkedList<String> expResult = new LinkedList<>();
        expResult.addFirst(appointment);
        expResult.addFirst(appointment2);
        
        instance.getUserInfo().get(0).addAppointmentToHistory(appointment, patID);
        instance.getUserInfo().get(0).addAppointmentToHistory(appointment2, patID);
        
        LinkedList<String> result = instance.getUserInfo().get(0).getAppointmentHistory();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAppointmentHistory method, of class Patient.
     */
    @Test
    public void testGetAppointmentHistory_String() {
        System.out.println("getAppointmentHistory");
        String patientID = "P1002";
        String appointment = "D1001, 20/04/23, 13:00";
        String appointment2 = "D1002, 21/04/23, 13:00";
        
        LinkedList<String> expResult = new LinkedList<>();
        expResult.addFirst(appointment);
        expResult.addFirst(appointment2);
        
        instance.addAppointmentToHistory(appointment, patientID);
        instance.addAppointmentToHistory(appointment2, patientID);
        
        LinkedList<String> result = instance.getAppointmentHistory(patientID);
        assertEquals(expResult, result);
    }

    /**
     * Test of setAppointmentHistory method, of class Patient.
     */
    @Test
    public void testSetAppointmentHistory() {
        System.out.println("setAppointmentHistory");
        String appointment = "D1001, 20/04/23, 13:00";
        String appointment2 = "D1002, 21/04/23, 13:00";
        
        LinkedList<String> expResult = new LinkedList<>();
        expResult.add(appointment);
        expResult.add(appointment2);
        
        instance.getUserInfo().get(1).setAppointmentHistory(expResult);
        LinkedList<String> result = instance.getUserInfo().get(1).getAppointmentHistory();
        assertEquals(expResult, result);
    }

    /**
     * Test of addAppointmentToHistory method, of class Patient.
     */
    @Test
    public void testAddAppointmentToHistory() {
        System.out.println("addAppointmentToHistory");
        String appointment = "D1003, 05/05/05, 09:00";
        String appointment2 = "D1002, 06/06/06, 10:00";
        String patID = "P1002";
        
        instance.addAppointmentToHistory(appointment, patID); 
        instance.addAppointmentToHistory(appointment2, patID);
        
        LinkedList<String> expResult = new LinkedList<>();
        expResult.addFirst(appointment);
        expResult.addFirst(appointment2);
        
        LinkedList<String> result = instance.getAppointmentHistory(patID);
        assertEquals(expResult, result);
    }
    
}
